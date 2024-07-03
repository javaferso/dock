<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.io.InputStreamReader" %>


<%
    System.out.println("instal_vnc.jsp called");
    try {
        String local_vnc = (String) session.getAttribute("local");
        String local_sin0 = local_vnc.replaceFirst("^0+(?!$)", "");//Elimina Ceros a la izquierda en caso que vengan asi
        // Rellenar con ceros a la izquierda hasta completar 4 dígitos
        String local_con0 = String.format("%04d", Integer.parseInt(local_sin0));
        String caja_vnc = (String) session.getAttribute("caja");
        String ip_vnc = (String) session.getAttribute("ipCaja");
        String[] cmd = {"sudo", "-u", "root","/home/soportetienda/vnc/instalar_vnc.exp", local_con0, ip_vnc};

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
        String file = "instalar_vnc_" + local_con0 + ".log";
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
