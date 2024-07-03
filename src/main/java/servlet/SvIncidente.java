
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Incidentes;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvIncidente", urlPatterns = {"/SvIncidente"})
public class SvIncidente extends HttpServlet {
    Controladora controladora = new Controladora();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listarIncidentes(request, response);
                break;
            case "loadForm":
                cargarFormulario(request, response);
                break;
            case "delete-form":
                cargarFormularioEliminar(request, response);
                break;
            case "edit-form":
                cargarFormularioEditar(request, response);
                break;
            default:
                listarIncidentes(request, response);
                break;
        }
    }

    private void listarIncidentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para listar incidentes
        List<Incidentes> incidentes = controladora.listarIncidentes();
        if (incidentes != null) {
            request.setAttribute("incidentes", incidentes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/listarIncidentes.jsp");
            dispatcher.forward(request, response);
        } else {
            // Manejar el caso cuando no hay incidentes o hubo un error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
        
        request.setAttribute("incidentes", incidentes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/listarIncidentes.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de agregar incidente
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/agregarIncidente.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarFormularioEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de eliminar incidente
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/eliminarIncidente.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de editar incidente
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/editarIncidente.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para manejar las solicitudes POST
    }
}

