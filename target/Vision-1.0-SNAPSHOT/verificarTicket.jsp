<%-- 
    Document   : verificaTicket.jsp
    Created on : Ago 16, 2023, 6:09:51 PM
    Author     : JFerreira
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %> 
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<% 
   System.out.println("verTicket.jsp called");

   try {
        String numeroBoleta = request.getParameter("numeroBoleta");
        System.out.println("numeroBoleta: " + numeroBoleta);
        String ip = (String) session.getAttribute("ipCaja");
        System.out.println("numero IP: " + ip);
        session.setAttribute("numeroBoleta", numeroBoleta);
        String[] cmd = {"sudo", "-u", "root", "/opt/tomcat/webapps/dockpos/buscaBoletas.exp", ip, numeroBoleta};
        System.out.println("Comando: " + Arrays.toString(cmd));
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
        String file = ip + "-ticket.log";
        String filePath = "/home/supervision_caja/" + file;
        StringBuilder logContent;
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
                String line;
                logContent = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    logContent.append(line);
                    logContent.append("\n");
                }   
            } 
        System.out.println("Log content:\n" + logContent.toString());
        out.println(logContent.toString());     
             

    } catch (IOException e) {
        e.printStackTrace();
        out.println("Error al obtener los datos de la boleta.");
    }
%>
    

