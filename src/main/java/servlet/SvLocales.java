
package servlet;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Locales;
import logica.Servidores;

/**
 *
 * @author e_jferreira
 */
public class SvLocales extends HttpServlet {
    
    @PersistenceContext
    EntityManager em;
    public SvLocales(){
        
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            listarLocales(request, response);
        }   else if ("listServer".equals(action)){
            listarServidores(request, response);
        }
    }
    
    
    protected void listarLocales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora controladora = new Controladora();
        List<Locales> listaLocales = controladora.obtenerLocales();
        request.setAttribute("listaLocales", listaLocales);
        RequestDispatcher rd = request.getRequestDispatcher("crud/listarLocales.jsp");
        rd.forward(request, response);  
    }
    
    protected void listarServidores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora controladora = new Controladora();
        List<Servidores> listaServidores = controladora.obtenerServidores();
        request.setAttribute("listaServidores", listaServidores);
        RequestDispatcher rd = request.getRequestDispatcher("crud/listarServidores.jsp");
        rd.forward(request, response);  
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
