/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.smu.vision.ScoPesoDefinido;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JFerreira
 */
public class SvScoPesoDefinido extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ip = request.getParameter("ip");
        String action = request.getParameter("action");
        String archivo = request.getParameter("archivo");
        ScoPesoDefinido scoPesoDefinido = new ScoPesoDefinido();
        
        try {
            if (!scoPesoDefinido.validIp(ip)) {
                response.getWriter().println("Invalid IP address.");
                return;
            }
            String resultado = null;

            switch (action) {
                case "limpiar":
                    scoPesoDefinido.limpiarTablaWeights(ip);
                    resultado = "Tabla weights limpiada.";
                    break;
                case "insertar":
                    scoPesoDefinido.insertarDatosTablaWeights(ip, archivo);
                    resultado = "Datos insertados en tabla weights.";
                    
                    break;
                case "actualizar":
                    scoPesoDefinido.updateWeightTablaArticles(ip, archivo);
                    resultado = "Peso actualizado en tabla articles.";
                    break;
                case "consultar_weights":
                    int countWeights = scoPesoDefinido.consultarCantidadWeights(ip);
                    resultado = "Cantidad de pesos en tabla weights: " + countWeights;
                    break;
                case "consultar_articles":
                    int countArticles = scoPesoDefinido.consultarCantidadArticles(ip);
                    resultado = "Cantidad de artículos en tabla articles: " + countArticles;
                    break;
                default:
                    resultado = "Acción no válida.";
                    break;
                       
            }
            response.setContentType("text/html");
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<div id='resultado'>");
                out.println(resultado);
                out.println("</div></body></html>");
            }
            
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
        
    }
    
}
