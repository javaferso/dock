/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvSSHCommand", urlPatterns = {"/SvSSHCommand"})
public class SvSSHCommand extends HttpServlet {
    
    // Contraseña fija para la sesión SSH
    private static final String FIXED_PASSWORD = "geocom";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().log("¡Estoy conectado al servlet!");
        // Obtener el comando SSH desde la solicitud
        response.setContentType("application/json;charset=UTF-8");
        
        PrintWriter out = response.getWriter();

        String ip = request.getParameter("ip");
        String username = request.getParameter("username");
        String command = request.getParameter("command");

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, ip, 22);
            session.setPassword(FIXED_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel channel = session.openChannel("shell");
            InputStream in = channel.getInputStream();
            OutputStream outStream = channel.getOutputStream();
            channel.connect();

            //Enviar comando al shell remoto
            PrintWriter writer = new PrintWriter(outStream);
            writer.println(command);
            writer.flush();
            
            // Leer la salida del shell remoto
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            //Enviar salida como respuesta json
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(Collections.singletonMap("output", output.toString()));
            out.println(jsonResponse);
     
            // Cerrar los flujos y la sesión
            writer.close();
            reader.close();
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

}
