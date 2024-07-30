package servlet;

import com.google.gson.Gson;
import com.smu.vision.BalanzaNagios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Balanza;
import logica.Controladora;
import persistencia.ControladoraPersistencia;

/**
 *
 * Author JFerreira
 */
public class SvInfoBalanzas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String localStr = request.getParameter("local");

        Controladora controladora = new Controladora();
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
        List<Balanza> balanzas = new ArrayList<>();
        Logger logger = Logger.getLogger(SvInfoBalanzas.class.getName());
        logger.info("Local para elegir balanzas: " + localStr);

        try {
            if (localStr != null && !localStr.isEmpty()) {
                int local = Integer.parseInt(localStr);
                balanzas = controladora.obtenerBalanza(local);

                if (balanzas != null && !balanzas.isEmpty()) {
                    List<BalanzaNagios> balanzaNagiosList = new ArrayList<>();

                    // Agregar estado de Nagios a cada balanza
                    for (Balanza balanza : balanzas) {
                        BalanzaNagios balanzaNagios = new BalanzaNagios();
                        balanzaNagios.setId(balanza.getBalanzaPK().getId());
                        balanzaNagios.setTiendaNumero(balanza.getBalanzaPK().getTiendaNumero());
                        balanzaNagios.setDireccionIp(balanza.getDireccionIp());
                        balanzaNagios.setHabilitado(balanza.getHabilitado());
                        balanzaNagios.setFechaActualizacion(balanza.getFechaActualizacion());
                        balanzaNagios.setHostName(balanza.getHostName());
                        balanzaNagios.setNombre(balanza.getNombre());
                        balanzaNagios.setPuerto(balanza.getPuerto());

                        try {
                            List<Object[]> estadoNagios = controladoraPersistencia.obtenerEstadoNagios(balanza.getHostName());
                            if (estadoNagios.size() == 3) {
                                for (Object[] registro : estadoNagios) {
                                    String serviceDescription = (String) registro[2];
                                    switch (serviceDescription) {
                                        case "Ping":
                                            int returnCode = (int) registro[0];
                                            balanzaNagios.setEstadoPing(returnCode == 0 ? "Online" : "Offline");
                                            break;
                                        case "MTI Actualizacion Balanzas":
                                            int returnCodeMTI = (int) registro[0];
                                            if (returnCodeMTI == 0){
                                                String fechaActualizacion = (String) registro[1];
                                                balanzaNagios.setFechaActualizacionNagios(parseDate(fechaActualizacion));
                                                break;
                                            } else {
                                                balanzaNagios.setFechaActualizacionNagios("Sin Datos");
                                                break;
                                            }
                                            
                                        case "Actualizacion Balanzas":
                                            int returnCodeAB = (int) registro[0];
                                            if (returnCodeAB == 0){
                                                String output = (String) registro[1];
                                                balanzaNagios.setMarca(extraerMarca(output));
                                                break;
                                            }
                                            
                                    }
                                }
                            } else {
                                balanzaNagios.setEstadoPing("Desconocido");
                                balanzaNagios.setFechaActualizacionNagios("N/A");
                                balanzaNagios.setMarca("N/A");
                            }
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Error obteniendo estado de Nagios para la balanza " + balanza.getHostName(), e);
                        }

                        balanzaNagiosList.add(balanzaNagios);
                    }

                    out.print(gson.toJson(balanzaNagiosList));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Locales no encontrados\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Acción no definida\"}");
            }
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"El parámetro 'local' debe ser un número entero\"}");
            logger.log(Level.SEVERE, "El parámetro 'local' no es un número entero: " + localStr, ex);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno de Balanzas\"}");
            logger.log(Level.SEVERE, "No se pudo encontrar la balanza", ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private String parseDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            return dateString; // Devolver el string original si no se puede parsear
        }
    }

    private String extraerMarca(String output) {
        String[] partes = output.split(",");
        for (String parte : partes) {
            parte = parte.trim();
            if (parte.startsWith("Marca ")) {
                return parte.replace("Marca ", "").trim();
            }
        }
        return output; // Devolver el string original si no se puede extraer la marca
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
