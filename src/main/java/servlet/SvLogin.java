package servlet;

import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {
  
    public static final String SESSION_USER =  "user";
    public static final String LOGIN_ATTEMPTS = "loginAttempts";
    public static final int MAX_ATTEMPTS = 4;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute(LOGIN_ATTEMPTS);
        if (count == null) {
            count = 0;
        }
        
        if (count >= MAX_ATTEMPTS) {
            response.sendRedirect("bloqueousuarios.jsp");
            return;
        }

        Controladora controladora = new Controladora();
        Usuario user = controladora.obtenerUsuario(username);
       
        if (user != null && password != null) { 
            if (password.equals(user.getPassword())) {
                session.setAttribute(SESSION_USER, user);
                session.setAttribute(LOGIN_ATTEMPTS, 0);
                if (user.getIdRole().equals(5)) {
                    response.sendRedirect("dashboard.jsp");
                } else {
                    response.sendRedirect("controlpos.jsp");
                }
                
                System.out.println("Usuario Conectado: " + user.getNombre());
                System.out.println("Usuario Rol: " + user.getIdRole());
                
            } else {
                count++;
                session.setAttribute(LOGIN_ATTEMPTS, count);
                session.setAttribute("loginError","Contraseña Incorrecta");
                response.sendRedirect("login.jsp");
            }
        } else {
            count++;
            session.setAttribute(LOGIN_ATTEMPTS, count);
            session.setAttribute("incorrectUser", true);
            response.sendRedirect("login.jsp");
        }    
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
