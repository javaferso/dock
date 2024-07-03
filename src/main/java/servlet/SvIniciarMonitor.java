/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author JFerreira
 */
@WebServlet (name = "SvIniciarMonitor", urlPatterns = {"/IniciarMonitor"})
public class SvIniciarMonitor extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ipCaja = request.getParameter("ipCaja");
        String caja = request.getParameter("caja");
        String local = request.getParameter("local");
        String formato = request.getParameter("formato");
        String nombreTienda = request.getParameter("nombreTienda");
        
        HttpSession session = request.getSession();
        
        System.out.println("ipCaja obtenida para SvIniciarMonitor: " + ipCaja);
        System.out.println("caja seleccionada en SvIniciarMonitor: " + caja);
        System.out.println("local seleccionado en SvIniciarMonitor: " + local);
        System.out.println("formato seleccionado en SvIniciarMonitor: " + formato);
        System.out.println("nombre de local seleccionado SvIniciarMonitor: " + nombreTienda);
        
        session.setAttribute("ipCaja", ipCaja);
        session.setAttribute("caja", caja);
        session.setAttribute("local", local);
        session.setAttribute("formato", formato);
        session.setAttribute("nombreTienda", nombreTienda);
        System.out.println("Session ID: " + session.getId());

        
        response.sendRedirect("monitor.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
