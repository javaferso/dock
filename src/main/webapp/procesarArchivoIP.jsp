<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>

<%

    BufferedReader reader = null;
    try {
        String ip = (String) session.getAttribute("ipCaja");
        String file = "scoPeso.log";
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
        out.println("Error al obtener los datos del script SCO");
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
