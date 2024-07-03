<%-- 
    Document   : logRollout
    Created on : Jun 10, 2024, 5:13:39 PM
    Author     : JFerreira
--%>

<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>

<%

    BufferedReader reader = null;
    try {
        String ip = (String) session.getAttribute("ipCaja");
        String file = "rollFlejes.log";
        String filePath = "/home/supervision_caja/" + file;
        reader = new BufferedReader(new FileReader(new File(filePath)));
        String outputLine;
        StringBuilder output = new StringBuilder();
        while ((outputLine = reader.readLine()) != null) {
            output.append(outputLine).append("\n");
        }
        
        out.println(output.toString());
    } catch (IOException e) {
        e.printStackTrace();
        out.println("Error al obtener los datos del log");
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
%>
