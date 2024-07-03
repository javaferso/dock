/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvBuscarBoleta", urlPatterns = "/SvBuscarBoleta")
public class SvBuscarBoleta extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroBoleta = request.getParameter("numeroBoleta");

        // Aquí puedes guardar el número de boleta en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("numeroBoleta", numeroBoleta);

        // Ejecuta tu comando de script bash aquí
    try {    
        String ip = (String) session.getAttribute("ip");
        String[] cmd = {"sudo", "-u", "root", "/opt/tomcat/webapps/visionboletas/buscarBoletas.exp", ip, " ", numeroBoleta};
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String outputLine;
        StringBuilder output = new StringBuilder();
        System.out.println("Output:");
        while ((outputLine = stdInput.readLine()) != null) {
            output.append(outputLine).append("\n");
            System.out.println(outputLine);
        }
        System.out.println("Errores:");
        while ((outputLine = stdError.readLine()) != null) {
            output.append(outputLine).append("/n");
            System.out.println(outputLine);
        }
        int exitCode = process.waitFor();
        System.out.println("Script ejecutandose con exit code:" + exitCode);
        String file = ip + "boletas.txt";
        String filePath = "/opt/tomcat/webapps/visionboleta/" + file;
        StringBuilder logContent;
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
                String line;
                logContent = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    logContent.append(line);
                    logContent.append("\n");
                }   }
        System.out.println("Log content:\n" + logContent.toString());
        out.println(logContent.toString());
    } catch (IOException e) {
        out.println("Error al obtener los datos de la boleta.");
    }   catch (InterruptedException ex) {
            Logger.getLogger(SvBuscarBoleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
