<%-- 
    Document   : verBoleta.jsp
    Created on : Jul 10, 2023, 6:09:51 PM
    Author     : JFerreira
    Proposito  : Desplegar en pantalla la ultima boleta emitida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %> 
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%
    System.out.println("verbBoleta.jsp called");
    try {
        String ipCaja = (String) session.getAttribute("ipCaja");
        String ip = ipCaja;
        String replicaFileName = ip + "-audit.log";
        String replicaFilePath = "/opt/tomcat/logs/" + replicaFileName;
        String[] cmd = {"sudo", "-u", "root", "/opt/tomcat/webapps/dockpos/auditBoletas.exp", ip};
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
        String file = ip + "-ultBoleta.log";
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
        out.println("Error al obtener los datos de la boleta.");
    }
%>
