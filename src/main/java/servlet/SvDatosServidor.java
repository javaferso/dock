/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smu.vision.NetworkUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Servidores;

/**
 *
 * @author JFerreira
 */
@WebServlet(
        name = "SvDatosServidor",
        urlPatterns = {"/SvDatosServidor"}
)
public class SvDatosServidor extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Controladora controladora = new Controladora();

        if ("getLocal".equals(action)) {
            String datosLocal = request.getParameter("datosLocal");
            System.out.println("Dato recibido desde SvDatosServidor: " + datosLocal);

            try {

                if (datosLocal == null || datosLocal.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Identificador del local no proporcionado.\"}");
                } else {
                    Servidores tienda = (Servidores) controladora.findServidorByLocal(datosLocal);
                    if (tienda != null) {
                        CompletableFuture<Boolean> ipAddressFuture = NetworkUtils.isReachableAsync(tienda.getIpAddress());
                        CompletableFuture<Boolean> ipEnlaceFuture = NetworkUtils.isReachableAsync(tienda.getIpEnlace());

                        CompletableFuture.allOf(ipAddressFuture, ipEnlaceFuture).join();

                        boolean isIpAddressReachable = ipAddressFuture.get();
                        boolean isIpEnlaceReachable = ipEnlaceFuture.get();

                        tienda.setEstadoIp(isIpAddressReachable ? "online" : "offline");
                        tienda.setEstadoEnlace(isIpEnlaceReachable ? "online" : "offline");
                        out.print(new Gson().toJson(tienda));
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
                        // Usar CompletableFuture para hacer ping a todas las IPs en paralelo
                        List<CompletableFuture<Void>> futures = locales.stream().map(local -> CompletableFuture.runAsync(() -> {
                            try {
                                CompletableFuture<Boolean> ipAddressFuture = NetworkUtils.isReachableAsync(local.getIpAddress());
                                CompletableFuture<Boolean> ipEnlaceFuture = NetworkUtils.isReachableAsync(local.getIpEnlace());

                                CompletableFuture.allOf(ipAddressFuture, ipEnlaceFuture).join();

                                boolean isIpAddressReachable = ipAddressFuture.get();
                                boolean isIpEnlaceReachable = ipEnlaceFuture.get();

                                local.setEstadoIp(isIpAddressReachable ? "online" : "offline");
                                local.setEstadoEnlace(isIpEnlaceReachable ? "online" : "offline");

                                // Actualizar los cambios en la base de datos
                                controladora.updateServidor(local);
                            } catch (InterruptedException | ExecutionException e) {
                                Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, null, e);
                            } catch (Exception ex) {
                                Logger.getLogger(SvDatosServidor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        })).collect(Collectors.toList());

                        // Esperar a que todos los futures se completen
                        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

                        out.print(new Gson().toJson(locales));
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
