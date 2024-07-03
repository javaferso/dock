/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.smu.vision.BalanzaService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author JFerreira
 */
public class SvBalanzas extends HttpServlet {
    
    
    
    private BalanzaService balanzaService;
    
    @Override
    public void init() throws ServletException {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TiOperacionesPU");
        balanzaService = new BalanzaService(emf); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            out.println("Servlet Balanzas called");
            //balanzaService.borrarDatosBalanza();
            balanzaService.llenarDatosBalanza();
            response.getWriter().println("Datos actualizados correctamente.");
        } 
        catch (IOException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
