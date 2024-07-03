/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "SvDetailServerOffline", urlPatterns = {"/SvDetailServerOffline"})
public class SvDetailServerOffline extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formato = request.getParameter("formato");
        out.println("Servlet Details Server Offline recibe formato: " + formato);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
        EntityManager em = emf.createEntityManager();
        try {
            String queryStr = "SELECT s.local, s.formato, s.nombreTienda, 'n/a' as Caja, s.ipAddress, s.estadoIp, s.updatedAt FROM Servidores s WHERE s.estadoIp = 'offline' AND s.formato = :formato";
            List<Object[]> results = em.createQuery(queryStr)
                    .setParameter("formato", formato)
                    .getResultList();

            JSONArray json = new JSONArray();
            for (Object[] result : results) {
                JSONObject obj = new JSONObject();
                obj.put("local", result[0]);
                obj.put("formato", result[1]);
                obj.put("nombreTienda", result[2]);
                obj.put("caja", result[3]);
                obj.put("ipAddress", result[4]);
                obj.put("estadoIp", result[5]);
                Date updatedAt = (Date) result[6];
                Timestamp timestamp = new Timestamp(updatedAt.getTime());
                obj.put("duracion", calculateDuration(timestamp));
                json.put(obj);
            }

            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(SvDetailServerOffline.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }

    private String calculateDuration(Timestamp updatedAt) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime updatedTime = updatedAt.toLocalDateTime();
        Duration duration = Duration.between(updatedTime, now);

        long days = duration.toDays();
        duration = duration.minusDays(days);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();

        return String.format("%dd %02d:%02d", days, hours, minutes);
    }
}
