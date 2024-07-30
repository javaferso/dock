/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import logica.Controladora;
import logica.Usuario;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author JFerreira
 */
@WebServlet(
        name = "SvUsuarios",
        urlPatterns = {"/SvUsuarios"}
)
public class SvUsuarios extends HttpServlet {

    public SvUsuarios() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            listarUsuarios(request, response);
        } else if ("loadForm".equals(action)) {
            cargarFormulario(request, response);
        }
        // Aquí puedes añadir más acciones como agregar, eliminar, etc.
    }

    private void cargarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("crud/agregarUsuarios.jsp");
        rd.forward(request, response);
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora controladora = new Controladora();
        List<Usuario> listaUsuarios = controladora.obtenerUsuarios();

        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher rd = request.getRequestDispatcher("crud/listarUsuarios.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                agregarUsuarios(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("Accion Agregar Usuario");
        } else if ("uploadImage".equals(action)) {
            try {
                uploadImage(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("changePassword".equals(action)) {
            try {
                changePassword(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("delete-user".equals(action)) {
            try {
                deleteUser(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } else if ("delete-form".equals(action)) {
            try {
                deleteForm(request, response);
                System.out.println("delete-form es la action llamada");
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void agregarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Controladora controladora = new Controladora();
        String idUsuario = request.getParameter("idUsuario");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String sexo = request.getParameter("sexo");
        String idRoleString = request.getParameter("idRole");
        int idRole = Integer.parseInt(idRoleString);
        String habilitado = request.getParameter("habilitado");
        PrintWriter out = response.getWriter();
        out.println("idUsuario recibido: " + idUsuario);
        out.println("idUsuario recibido: " + password);
        out.println("idUsuario recibido: " + nombre);
        out.println("idUsuario recibido: " + apellido);
        out.println("idUsuario recibido: " + sexo);
        out.println("idUsuario recibido: " + habilitado);
        out.println("idUsuario recibido: " + idRole);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setSexo(sexo);
        usuario.setIdRole(idRole);
        usuario.setHabilitado(habilitado);
        controladora.crearUsuario(idUsuario, usuario.getPassword(), nombre, apellido, sexo, idRole, habilitado);

    }
    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        // Lógica para cambiar la contraseña
        String contrasenaActual = request.getParameter("contrasenaActual");
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        System.out.println("Recibiendo cambio de contraseña actual " + contrasenaActual);
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");

        if (usuario != null) {
            if (BCrypt.checkpw(contrasenaActual, usuario.getPassword())) {
                usuario.setPassword(BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt()));
                Controladora controladora = new Controladora();
                controladora.actualizarUsuario(usuario);
                session.setAttribute("changePassword", "Contraseña Cambiada!!");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("La contraseña actual no coincide");
            }
            
        } else {
            System.out.println("No hay usuario logueado para cambiar contraseña");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {

        Part filePart = request.getPart("imagenPerfil"); // Obtiene el archivo subido
        System.out.println("uploadImage called...");
        if (filePart != null) {
            InputStream inputStream = filePart.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");

            Controladora controladora = new Controladora();
            controladora.editarImagenUsuario(usuario, imageBytes);
        } else {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No se pudo cargar la imagen');");
            out.println("window.location.href = 'perfil.jsp';");
            out.println("</script>");
        }

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        String idUser = request.getParameter("idUsuario");
        if (usuario != null) {
            if (usuario.getIdUsuario().equals(idUser)) {

                Controladora controladora = new Controladora();
                controladora.eliminarUsuario(idUser);

            }
        } else {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Usuario no logueado');");
            out.println("window.location.href = 'admin.jsp';");
            out.println("</script>");
        }

    }

    private void deleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, Exception {
        System.out.println("deleteForm called");
        RequestDispatcher rd = request.getRequestDispatcher("crud/eliminarUsuarios.jsp");
        rd.forward(request, response);
    }

}
