/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Services;

@WebServlet(name = "SvServices", urlPatterns = {"/SvServices"})
public class SvServices extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SvServices.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apiUrl = "http://localhost:5000/api/incidents"; // Cambia esto por la URL de tu API de FastAPI

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }

            conn.disconnect();

            LOGGER.log(Level.INFO, "API Response: {0}", output.toString());

            JSONArray jsonArray = new JSONArray(output.toString());
            List<Services> servicesList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Services service = new Services();
                service.setId(jsonObject.getInt("id"));
                service.setTipo(jsonObject.getString("tipo"));
                service.setMes(jsonObject.getInt("mes"));
                service.setFormato(jsonObject.getString("formato"));
                service.setInc(jsonObject.getString("inc"));
                service.setSap(jsonObject.getInt("sap"));
                service.setTienda(jsonObject.getString("tienda"));
                service.setDetalle(jsonObject.getString("detalle"));
                service.setMonto(jsonObject.getDouble("monto"));
                service.setMoneda(jsonObject.getString("moneda"));
                service.setProveedor(jsonObject.getString("proveedor"));
                service.setfAutorizar(formatDate(jsonObject.getString("f_autorizar"), sdf));
                service.setOc(jsonObject.optString("oc", null));
                service.setfEnvioProv(formatDate(jsonObject.optString("f_envio_prov", null), sdf));
                service.setHes(jsonObject.optString("hes", null));
                service.setSociedad(jsonObject.getString("sociedad"));
                service.setOrdenEstadistica(jsonObject.getString("orden_estadistica"));
                service.setTextoBreve(jsonObject.getString("texto_breve"));
                service.setCotizacion(jsonObject.getString("cotizacion"));
                service.setActivo(jsonObject.getBoolean("activo"));
                service.setFechaCreacion(formatDate(jsonObject.getString("fecha_creacion"), sdf));
                service.setFechaActualizacion(formatDate(jsonObject.getString("fecha_actualizacion"), sdf));
                service.setFechaCierre(formatDate(jsonObject.optString("fecha_cierre", null), sdf));

                servicesList.add(service);
            }

            LOGGER.log(Level.INFO, "Services List Size: {0}", servicesList.size());

            request.setAttribute("services", servicesList);
            request.getRequestDispatcher("/services.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error consuming API", e);
            throw new ServletException("Error al consumir la API", e);
        }
    }

    private String formatDate(String dateStr, SimpleDateFormat sdf) {
        if (dateStr == null || dateStr.equals("null")) {
            return null;
        }
        try {
            Date date = sdf.parse(dateStr);
            return sdf.format(date);
        } catch (Exception e) {
            return dateStr;
        }
    }
}
