/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import com.smu.vision.BashExecutor;
import com.smu.vision.TicketService;
import com.smu.vision.RolloutService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

@WebServlet(name = "SvTickets", urlPatterns = {"/SvTickets"})
public class SvTickets extends HttpServlet {

    private final TicketService ticketService = new TicketService();
    private final RolloutService rolloutService = new RolloutService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Map<String, Map<String, String>> resultados = new HashMap<>();
        String local = request.getParameter("local");
        System.out.println("SvTickets llamado con parametro " + local);
        Controladora controladora = new Controladora();
        List<String> cajas = new ArrayList<>();

        if (local != null && !local.isEmpty()) {
            cajas.addAll(controladora.obtenerCajas(local));
        }

        if (cajas.isEmpty()) {
            out.print("{vacio}");
            out.flush();
            return; // Salir del método si no hay cajas
        }

        List<CompletableFuture<Map<String, String>>> futures = cajas.stream().map(caja -> CompletableFuture.supplyAsync(() -> {
            String ip = controladora.findIpByCaja(caja, local);
            String tickets = obtenerInfoTickets(ip);
            String reverse = obtenerReversePos(ip);
            boolean online = false;
            try {
                online = InetAddress.getByName(ip).isReachable(1000); // 1000 ms timeout
            } catch (UnknownHostException ex) {
                Logger.getLogger(SvTickets.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SvTickets.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map<String, String> detallesCaja = new HashMap<>();
            detallesCaja.put("ip", ip);
            detallesCaja.put("caja", caja);
            detallesCaja.put("tickets", tickets);
            detallesCaja.put("online", String.valueOf(online));
            detallesCaja.put("reverse", reverse);
            return detallesCaja;
        })).collect(Collectors.toList());

        // Esperar a que todos los CompletableFuture se completen y recopilar los resultados
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
            for (CompletableFuture<Map<String, String>> future : futures) {
                Map<String, String> detallesCaja = future.get();
                resultados.put(detallesCaja.get("caja"), detallesCaja);
            }
        } catch (InterruptedException | ExecutionException e) {
            Logger.getLogger(SvTickets.class.getName()).log(Level.SEVERE, null, e);
            out.print("No data");
            out.flush();
            return; // Salir del método en caso de error
        }
        out.print(gson.toJson(resultados));
        out.flush();
    }

    private String obtenerInfoTickets(String ip) {
    StringBuilder logContent = new StringBuilder();
    try {
        // Pasar la IP al método getTicketCount de TicketService
        String ticketCount = ticketService.getTicketCount(ip);
        logContent.append(ticketCount);
    } catch (Exception e) {
        logContent.append("Error: " + e.getMessage());
    }
    return logContent.toString();
    }
    
    private String obtenerReversePos(String ip) {
        StringBuilder logContent = new StringBuilder();
        try {
            String reverseCount = ticketService.getReverseCount(ip);
            logContent.append(reverseCount);
        } catch (Exception e) {
            logContent.append("Error: " + e.getMessage());
        }
        return logContent.toString();
    }
}

