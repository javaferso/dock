package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

@WebServlet(name = "SvKpi", urlPatterns = {"/SvKpi"})
public class SvKpi extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
        EntityManager em = emf.createEntityManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mm");

        try {
            String queryStr = "SELECT formato, COUNT(*) FILTER (WHERE estado_ip = 1) AS online, COUNT(*) FILTER (WHERE estado_ip = 0) AS offline, MAX(updated_at) AS updatedAt FROM supervision.locales WHERE formato != 'MFC' GROUP BY formato";
            List<Object[]> results = em.createNativeQuery(queryStr).getResultList();

            JSONArray json = new JSONArray();
            for (Object[] result : results) {
                JSONObject obj = new JSONObject();
                obj.put("formato", result[0]);
                obj.put("online", result[1]);
                obj.put("offline", result[2]);
                Timestamp timestamp = (Timestamp) result[3];
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                obj.put("updatedAt", updatedAt.format(formatter)); 
                json.put(obj);
            }

            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(SvKpi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}

    
    

