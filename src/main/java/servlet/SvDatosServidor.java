/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Locales;
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
                    Object servidor = controladora.findServidorByLocal(datosLocal);
                    if (servidor != null) {
                        out.print(new Gson().toJson(servidor));
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
        } else if("searchLocals".equals(action)) {
            String query = request.getParameter("query");
            
            try {
                if(query == null || query.trim().isEmpty()){
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Identificador del local no proporcionado.\"}");
                } else {
                    List<String> locales = controladora.buscarServidoresPorCriterio(query);
                    if(locales != null){
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
