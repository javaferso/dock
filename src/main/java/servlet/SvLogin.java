package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    public static final String SESSION_USER = "user";
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

        if (user != null) {
            String storedPassword = user.getPassword();
            System.out.println("storedPassword: " + storedPassword);

            // Verificar si la contraseña almacenada está hasheada con Bcrypt
            if (storedPassword.length() == 60 && storedPassword.startsWith("$2a$")) {
                System.out.println("La contraseña está hasheada, usar BCrypt para verificar");
                System.out.println("Contraseña ingresada: " + password);

                // Depuración adicional para comparar hashes
                boolean passwordMatches = BCrypt.checkpw(password, storedPassword);
                System.out.println("Resultado de BCrypt.checkpw: " + passwordMatches);

                if (passwordMatches) {
                    autenticarUsuario(session, user, response);
                } else {
                    manejarErrorAutenticacion(session, response, count);
                }
            } else {
                // La contraseña no está hasheada, comparar directamente y actualizar si coincide
                if (password.equals(storedPassword)) {
                    System.out.println("La contraseña no está hasheada, coincide con la ingresada");

                    // Actualizar la contraseña almacenada con Bcrypt
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    System.out.println("Hashed password to store: " + hashedPassword);
                    user.setPassword(hashedPassword);
                    try {
                        controladora.actualizarUsuario(user);
                        System.out.println("Contraseña actualizada a: " + user.getPassword());
                    } catch (Exception ex) {
                        Logger.getLogger(SvLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    autenticarUsuario(session, user, response);
                } else {
                    manejarErrorAutenticacion(session, response, count);
                }
            }
        } else {
            manejarErrorAutenticacion(session, response, count);
        }
    }

    private void autenticarUsuario(HttpSession session, Usuario user, HttpServletResponse response) throws IOException {
        session.setAttribute(SESSION_USER, user);
        session.setAttribute(LOGIN_ATTEMPTS, 0);
        if (user.getIdRole().getIdRole().equals(5)) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("controlpos.jsp");
        }

        System.out.println("Usuario Conectado: " + user.getNombre());
        System.out.println("Usuario Rol: " + user.getIdRole());
        System.out.println("Usuario password: " + user.getPassword());
    }

    private void manejarErrorAutenticacion(HttpSession session, HttpServletResponse response, Integer count) throws IOException {
        count++;
        session.setAttribute(LOGIN_ATTEMPTS, count);
        session.setAttribute("loginError", "Usuario o contraseña incorrectos");
        response.sendRedirect("login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
