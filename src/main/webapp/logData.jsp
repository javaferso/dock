<%-- 
    Document   : logData
    Created on : Jul 4, 2023, 2:25:19 AM
    Author     : JFerreira
--%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.io.InputStreamReader" %>

<%
    String ipCaja = (String) session.getAttribute("ipCaja");
    try {
        String ip = ipCaja;
        System.out.println("Selected IP: " + ip);

        String scriptPath = "./opt/tomcat/webapps/dockpos/visionBoletas.exp";
        String[] cmd = {"expect", scriptPath, ip};

        Process process = Runtime.getRuntime().exec(cmd);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        StringBuilder output = new StringBuilder();

        String outputLine;
        while ((outputLine = stdInput.readLine()) != null) {
            output.append(outputLine).append("\n");
System.out.println(outputLine);
        }

        while ((outputLine = stdError.readLine()) != null) {
            output.append(outputLine).append("\n");
            System.out.println(outputLine);
        }

        int exitCode = process.waitFor();
        System.out.println("Script execution completed with exit code: " + exitCode);
        String file = ip + "-sale.log";
        String filePath = "/home/supervision_caja/" + file;
        System.out.println("Leyendo informacion de :" + filePath);
        File f = new File(filePath);

        if (f.exists()) {
            System.out.println("El archivo existe.");
        } else {
            System.out.println("El archivo no existe o está vacío.");
        }
        
        
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String line;
        StringBuilder logContent = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            logContent.append(line);
            logContent.append("\n");
        }

        reader.close();
        System.out.println("Log content:\n" + logContent.toString());

        out.println(logContent.toString());
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        out.println("Error al ejecutar el script.");
    }
%>
