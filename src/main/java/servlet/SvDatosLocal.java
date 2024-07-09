/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

@WebServlet(
   name = "SvDatosLocal",
   urlPatterns = {"/SvDatosLocal"}
)
public class SvDatosLocal extends HttpServlet {
   @PersistenceContext
   EntityManager em;

   public SvDatosLocal() {
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("application/json");
      PrintWriter out = resp.getWriter();
      String action = req.getParameter("action");
      Controladora controladora = new Controladora();
      if (action.equals("getFormatos")) {
         List<String> formatos = controladora.obtenerFormatos();
         out.print((new Gson()).toJson(formatos));
         req.setAttribute("formatos", formatos);
      } else {
         List cajas;
         String caja;
         if (action.equals("getLocales")) {
            caja = req.getParameter("formato");
            cajas = controladora.obtenerLocales(caja);
            out.print((new Gson()).toJson(cajas));
         } else if (action.equals("getCajas")) {
            caja = req.getParameter("local");
            cajas = controladora.obtenerCajas(caja);
            out.print((new Gson()).toJson(cajas));
         } else if (action.equals("getIp")) {
            caja = req.getParameter("caja");
            String local = req.getParameter("local");
            String ipCaja = controladora.findIpByCaja(caja, local);
            String tienda = controladora.obtenerNombreLocal(local);
           
            
            out.print((new Gson()).toJson(ipCaja));
            System.out.println("Ip obtenida desde SvDatosLocal: " + ipCaja);
            System.out.println("caja obtenida desde SvDatosLocal: " + caja);
            System.out.println("tienda obtenida desde SvDatosLocal: " + tienda);
         } else if (action.equals("getNombreTienda")){
            String local = req.getParameter("local");
            String tienda = controladora.obtenerNombreLocal(local);
            out.print((new Gson()).toJson(tienda));
            
            System.out.println("tienda obtenida desde SvDatosLocal: " + tienda);
         }
      }

      out.flush();
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }
}
