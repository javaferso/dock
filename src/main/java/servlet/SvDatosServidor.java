package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.smu.vision.NetworkUtils;
import com.smu.vision.ServFlejesElectronicos;
import com.smu.vision.TiendasNagios;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;
import logica.Controladora;
import logica.Servidores;
import persistencia.ControladoraPersistencia;

@WebServlet(name = "SvDatosServidor", urlPatterns = {"/SvDatosServidor"})
public class SvDatosServidor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Controladora controladora = new Controladora();
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

        if (action == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Acción no definida\"}");
            out.flush();
            out.close();
            return;
        }

        switch (action) {
            case "pingEnlace":
                handlePingEnlace(request, response, out);
                break;
            case "ping":
                handlePing(request, response, out);
                break;
            case "getLocal":
                handleGetLocal(request, response, out, controladora, controladoraPersistencia);
                break;
            case "searchLocals":
                handleSearchLocals(request, response, out, controladora, controladoraPersistencia);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Acción no definida\"}");
                out.flush();
                out.close();
                break;
        }
    }

    private void handlePingEnlace(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        boolean online = false;
        String ipEnlace = request.getParameter("ipEnlace");
        try {
            if (ipEnlace == null || ipEnlace.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Faltan datos para realizar el ping.\"}");
            } else {
                online = InetAddress.getByName(ipEnlace).isReachable(1000);
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("estadoEnlace", online ? "online" : "offline");
                out.print(new Gson().toJson(responseJson));
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno del servidor\"}");
            Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, "No se pudo realizar el ping", ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private void handlePing(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String ipServidor = request.getParameter("ipServidor");
        try {
            if (ipServidor == null || ipServidor.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Faltan datos para realizar el ping.\"}");
            } else {
                boolean isIpAddressReachable = NetworkUtils.isReachable(ipServidor);
                System.out.println("Resultado del ping a IP Servidor: " + isIpAddressReachable);
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("estadoIp", isIpAddressReachable ? "online" : "offline");
                out.print(new Gson().toJson(responseJson));
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno del servidor\"}");
            Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, "No se pudo realizar el ping", ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private void handleGetLocal(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Controladora controladora, ControladoraPersistencia controladoraPersistencia) {
        String datosLocal = request.getParameter("datosLocal");
        System.out.println("Dato recibido desde SvDatosServidor: " + datosLocal);
        try {
            if (datosLocal == null || datosLocal.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Identificador del local no proporcionado.\"}");
            } else {
                Servidores tienda = (Servidores) controladora.findServidorByLocal(datosLocal);
                if (tienda != null) {
                    actualizarEstadoDesdeNagios(tienda, controladoraPersistencia); 
                    
                    JsonObject responseJson = new JsonObject();
                    responseJson.add("tienda", new Gson().toJsonTree(tienda));

                    if (tienda.getFlejeElectronico()) {
                        ServFlejesElectronicos servFlejesElectronicos = new ServFlejesElectronicos();
                        JsonObject flejeData = servFlejesElectronicos.getDatosFlejeElectronico(datosLocal);
                        responseJson.add("flejeData", flejeData);
                    }

                    out.print(new Gson().toJson(responseJson));

                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Servidor no encontrado\"}");
                }
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno del servidor\"}");
            Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, "No se pudo encontrar el servidor", ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private void handleSearchLocals(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Controladora controladora, ControladoraPersistencia controladoraPersistencia) {
        String query = request.getParameter("query");
        try {
            if (query == null || query.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Identificador del local no proporcionado.\"}");
            } else {
                if (!isValidQuery(query)) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Consulta no válida.\"}");
                    return;
                }
                List<Servidores> locales = controladora.buscarServidoresPorCriterio(query);
                if (locales != null) {
                    List<JsonObject> resultados = locales.stream().map(local -> {
                        actualizarEstadoDesdeNagios(local, controladoraPersistencia);

                        JsonObject localJson = new JsonObject();
                        localJson.add("local", new Gson().toJsonTree(local));
                        if (local.getFlejeElectronico()) {
                            ServFlejesElectronicos servFlejesElectronicos = new ServFlejesElectronicos();
                            JsonObject flejeData = servFlejesElectronicos.getDatosFlejeElectronico(local.getLocal());
                            localJson.add("flejeData", flejeData);
                        }

                        try {
                            controladora.updateServidor(local);
                        } catch (Exception ex) {
                            Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return localJson;
                    }).collect(Collectors.toList());

                    JsonArray responseArray = new JsonArray();
                    resultados.forEach(responseArray::add);

                    out.print(new Gson().toJson(responseArray));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Locales no encontrados\"}");
                }
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno del servidor\"}");
            Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, "No se pudo encontrar el servidor", ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private boolean isValidQuery(String query) {
        return query.matches("[a-zA-Z0-9 ]+");
    }

    private void actualizarEstadoDesdeNagios(Servidores servidor, ControladoraPersistencia controladoraPersistencia) {
        try {
            // Actualizar estado IP
            String hostnameIp = servidor.getHostname();
            List<Object[]> estadoIpNagios = controladoraPersistencia.obtenerEstadoNagios(hostnameIp);
            if (!estadoIpNagios.isEmpty()) {
                for (Object[] registro : estadoIpNagios) {
                    System.out.println("Estado Nagios IP obtenido " + registro[0] + " Registro: " + registro[2]);
                    String serviceDescription = (String) registro[2];
                    if ("Ping".equals(serviceDescription)) {
                        int returnCode = (int) registro[0];
                        servidor.setEstadoIp(returnCode == 0 ? "online" : "offline");
                    }
                }
            }

            // Actualizar estado Enlace
            String hostnameEnlace = extraerTipo(hostnameIp);
            List<Object[]> estadoEnlaceNagios = controladoraPersistencia.obtenerEstadoNagios(hostnameEnlace);
            if (!estadoEnlaceNagios.isEmpty()) {
                for (Object[] registro : estadoEnlaceNagios) {
                    System.out.println("Estado Nagios Enlace obtenido " + registro[0] + " Registro: " + registro[2]);
                    String serviceDescription = (String) registro[2];
                    if ("Ping".equals(serviceDescription)) {
                        int returnCode = (int) registro[0];
                        servidor.setEstadoEnlace(returnCode == 0 ? "online" : "offline");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el estado desde Nagios: " + e.getMessage());
        }
    }

    private String extraerTipo(String hostname) {
        if (hostname.endsWith("SRV")) {
            return hostname.replace("SRV", "ENL").trim();
        }
        return hostname; // Devolver el string original si no se puede extraer la marca
    }
}
