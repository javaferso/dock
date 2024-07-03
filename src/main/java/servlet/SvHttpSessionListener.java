/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author JFerreira
 */

public class SvHttpSessionListener extends HttpServlet {


    public void sessionCreated(HttpSessionEvent se) {
      HttpSession session = se.getSession();
      session.setMaxInactiveInterval(30*60); // 30 minutes
    }
    public void sessionDestroyed(HttpSessionEvent se, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Redirection logic can be added here
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
   
   

}
