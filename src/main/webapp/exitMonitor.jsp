<%-- 
    Document   : exitMonitor
    Created on : Jul 10, 2023, 1:50:27 PM
    Author     : JFerreira
--%>

<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.InputStreamReader" %> 

<%
    String ipCaja = (String) session.getAttribute("ipCaja");
    String mensaje_1 = "Ejecutando exitMonitor";
    System.out.println("Session ID en exitMonitor.jsp: " + session.getId());
    // Lógica para detener la réplica y mostrar el mensaje de salida
    try {
        String ip = ipCaja;
        String usuario = "root";
        System.out.println(mensaje_1 + "para la ip:" + ip);
        Process process = Runtime.getRuntime().exec("rm -f /tmp/" + ip + "-sale.log");
        process.waitFor();
        // Cerrar la conexión SSH y detener la réplica
        Process process2 = Runtime.getRuntime().exec("ssh " + usuario + "@" + ip + " pkill -f 'tail -f /home/geocom/geopos/current/logs/sale.log'");
        process2.waitFor();
        Process process3 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "-replica.log");
        process3.waitFor();
        Process process4 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "-sale.log");
        process4.waitFor();
        Process process5 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "-ultBoleta.log");
        process5.waitFor();
        Process process6 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "-ticket.log");
        process6.waitFor();
        Process process8 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "-audit.log");
        process8.waitFor();
        Process process9 = Runtime.getRuntime().exec("rm -f /home/supervision_caja/" + ip + "*-ecommerce.log");
        process9.waitFor();
        //String scriptPath = "./opt/tomcat/webapps/dockpos/stop_replica.exp";
        //String [] cmd = {"expect", scriptPath, ip};
        //Process process7 = Runtime.getRuntime().exec(cmd);
        //int exitCode = process7.waitFor();
        //System.out.println("Process execution completed with exit code:" + exitCode);
        // Mostrar mensaje de salida
        String mensaje = "Réplica finalizada. ¡Hasta luego!";
        out.println(mensaje);
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        String mensaje = "Error al detener la réplica.";
        out.println(mensaje);
    }
%>

