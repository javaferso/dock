/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvPing", urlPatterns = {"/SvPing"})
public class SvPing extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Map<String, Map<String, String>> resultados = new HashMap<>();
        String local = request.getParameter("local");
        
     
       
        
        Controladora controladora = new Controladora();
        List<String> cajas = new ArrayList<>();

        if (local != null && !local.isEmpty()) {
            cajas.addAll(controladora.obtenerCajas(local));
            System.out.println("CAJAS: " + cajas.addAll(cajas));
        }
       
        if (cajas.isEmpty()) {
            out.print("{vacio}");
            out.flush();
            return;
        }
       
        List<CompletableFuture<Map<String, String>>> futures = cajas.stream().map(caja -> CompletableFuture.supplyAsync(() -> {
            String ip = controladora.findIpByCaja(caja, local);
            int tickets = controladora.findByCajaTicket(caja, local);
            boolean online = false;
            try {
                online = InetAddress.getByName(ip).isReachable(1000); // 1000 ms timeout
            } catch (UnknownHostException ex) {
                Logger.getLogger(SvPing.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SvPing.class.getName()).log(Level.SEVERE, null, ex);
            }
            Map<String, String> detallesCaja = new HashMap<>();
            detallesCaja.put("estado", online ? "online" : "offline");
            detallesCaja.put("ip", ip);
            detallesCaja.put("caja", caja);
            detallesCaja.put("tickets", Integer.toString(tickets));
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
            // Manejar excepciones
            out.print("No data");
        }
        out.print(gson.toJson(resultados));
        out.flush();

    }

    
}
