package servlet;

import java.io.IOException;
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

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvOffline", urlPatterns = {"/SvOffline"})
public class SvOffline extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formato = request.getParameter("formato");
        out.println("Servlet Offline recibe formato: " + formato);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
        EntityManager em = emf.createEntityManager();

        try {
            String queryStr = "SELECT l.local, l.formato, l.nombreTienda, l.caja, l.ip, l.estadoIp, l.updatedAt FROM Locales l WHERE l.estadoIp = 0 AND l.formato = :formato";
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
                obj.put("ip", result[4]);
                obj.put("estadoIp", result[5]);
                Date updatedAt = (Date) result[6];
                Timestamp timestamp = new Timestamp(updatedAt.getTime());
                obj.put("duracion", calculateDuration(timestamp)); 
                json.put(obj);
            }

            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(SvOffline.class.getName()).log(Level.SEVERE, null, ex);
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

            return String.format("%dd %02dh:%02dm", days, hours, minutes);
        }
}
