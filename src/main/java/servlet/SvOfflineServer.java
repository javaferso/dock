/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvOfflineServer", urlPatterns = {"/SvOfflineServer"})
public class SvOfflineServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formato = request.getParameter("formato");
        out.println("Servlet Offline Server recibe formato: " + formato);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
        EntityManager em = emf.createEntityManager();

        try {
            String queryStr = "SELECT s.formato, \n"
                    + "       SUM(CASE WHEN s.estadoIp = 'online' THEN 1 ELSE 0 END) AS online,\n"
                    + "       SUM(CASE WHEN s.estadoIp = 'offline' THEN 1 ELSE 0 END) AS offline\n"
                    + "FROM Servidores s \n"
                    + "WHERE s.formato != 'MFC' \n"
                    + "GROUP BY s.formato";
            List<Object[]> results = em.createQuery(queryStr).getResultList();
                    
            JSONArray json = new JSONArray();
            for (Object[] result : results) {
                JSONObject obj = new JSONObject();
                obj.put("formato", result[0]);
                obj.put("online", result[1]);
                obj.put("offline", result[2]);
                json.put(obj);
            }

            response.setContentType("application/json");
            response.getWriter().write(json.toString());

        } catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);

        } catch (JSONException ex) {
            Logger.getLogger(SvOfflineServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

    }

}
