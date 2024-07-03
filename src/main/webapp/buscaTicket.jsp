<%--
    Document   : buscaTicket.jsp
    Created on : Ago 03, 2023, 2:09:51 PM
    Author     : JFerreira
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%
    System.out.println("buscaTicket.jsp called");
    
    try {
        
        String ip = request.getParameter("ip");
        String numTicket = request.getParameter("numTicket");
        String replicaFileName = ip + "-ticket.log";
        String replicaFilePath = "/home/supervision_caja/" + replicaFileName;
        String[] cmd = {"sudo", "-u", "root", "/opt/tomcat/webapps/dockpos/buscaBoletas.exp", ip, numTicket};
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
    } catch (IOException e) {
        e.printStackTrace();
        out.println("Error al obtener los datos de la boleta.\nVerifique que los datos sean correctos");
    }
%>
