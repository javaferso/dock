/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ConsultoresPrecios;
import logica.Controladora;

/**
 *
 * @author JFerreira
 */
public class SvConsultaPrecios extends HttpServlet {

    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String localStr = request.getParameter("local");
        Controladora controladora = new Controladora();
        List<ConsultoresPrecios> consultaprecios = new ArrayList<>();
        Logger logger = Logger.getLogger(SvConsultaPrecios.class.getName());
        try {
            if (localStr != null && !localStr.isEmpty()) {
                int local = Integer.parseInt(localStr);
                consultaprecios = controladora.obtenerConsultaPreciosByLocal(local);
                
                if(consultaprecios != null && !consultaprecios.isEmpty()) {
                    out.print(gson.toJson(consultaprecios));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Locales no encontrados\"}");
                }
            }
            
            
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"El parámetro 'local' debe ser un número entero\"}");
            logger.log(Level.SEVERE, "El parámetro 'local' no es un número entero: " + localStr, ex);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error interno de Consulta Precios\"}");
            logger.log(Level.SEVERE, "No se pudo encontrar el consulta precios", ex);
        } finally {
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
