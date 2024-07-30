package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.smu.vision.NetworkUtils;
import com.smu.vision.ServFlejesElectronicos;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "SvDatosServidor", urlPatterns = {"/SvDatosServidor"})
public class SvDatosServidor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Controladora controladora = new Controladora();

        if ("ping".equals(action)) {
            String ipServidor = request.getParameter("ipServidor");
            String ipEnlace = request.getParameter("ipEnlace");

            try {
                if (ipServidor == null || ipServidor.trim().isEmpty() ||
                    ipEnlace == null || ipEnlace.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Faltan datos para realizar el ping.\"}");
                } else {
                    boolean isIpAddressReachable = NetworkUtils.isReachable(ipServidor);
                    boolean isIpEnlaceReachable = NetworkUtils.isReachable(ipEnlace);

                    System.out.println("Resultado del ping a IP Servidor: " + isIpAddressReachable);
                    System.out.println("Resultado del ping a IP Enlace: " + isIpEnlaceReachable);

                    JsonObject responseJson = new JsonObject();
                    responseJson.addProperty("estadoIp", isIpAddressReachable ? "online" : "offline");
                    responseJson.addProperty("estadoEnlace", isIpEnlaceReachable ? "online" : "offline");

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
        } else if ("getLocal".equals(action)) {
            String datosLocal = request.getParameter("datosLocal");
            System.out.println("Dato recibido desde SvDatosServidor: " + datosLocal);

            try {
                if (datosLocal == null || datosLocal.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Identificador del local no proporcionado.\"}");
                } else {
                    Servidores tienda = (Servidores) controladora.findServidorByLocal(datosLocal);
                    if (tienda != null) {
                        boolean isIpAddressReachable = NetworkUtils.isReachable(tienda.getIpAddress());
                        boolean isIpEnlaceReachable = NetworkUtils.isReachable(tienda.getIpEnlace());

                        tienda.setEstadoIp(isIpAddressReachable ? "online" : "offline");
                        tienda.setEstadoEnlace(isIpEnlaceReachable ? "online" : "offline");

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
        } else if ("searchLocals".equals(action)) {
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
                            JsonObject localJson = new JsonObject();
                            boolean isIpAddressReachable = NetworkUtils.isReachable(local.getIpAddress());
                            boolean isIpEnlaceReachable = NetworkUtils.isReachable(local.getIpEnlace());

                            local.setEstadoIp(isIpAddressReachable ? "online" : "offline");
                            local.setEstadoEnlace(isIpEnlaceReachable ? "online" : "offline");

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
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Acción no definida\"}");
            out.flush();
            out.close();
        }
    }

    private boolean isValidQuery(String query) {
        return query.matches("[a-zA-Z0-9 ]+");
    }
}
