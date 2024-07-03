
package com.smu.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JFerreira
 */
public class ScoPesoDefinido {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String registroArchivo = "registro.log";

    public boolean validIp(String ip) {
        String PATTERN = 
            "^(([0-9]{1,3})\\.){3}([0-9]{1,3})$";
        return ip.matches(PATTERN);
    
    }
    
    private void executeCommand(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }
        process.waitFor();
    }
    public void limpiarTablaWeights(String ip) throws Exception {
        String command = String.format("mysql -u root -pgeocom -h %s geopos -e 'TRUNCATE weights'", ip);
        executeCommand(command);
    }

    public void insertarDatosTablaWeights(String ip, String archivoInsert) throws Exception {
        String command = String.format("mysql -u root -pgeocom -h %s geopos < %s", ip, archivoInsert);
        executeCommand(command);
    }

    public void updateWeightTablaArticles(String ip, String archivoUpdate) throws Exception {
        String command = String.format("mysql -u root -pgeocom -h %s geopos < %s", ip, archivoUpdate);
        executeCommand(command);
    }

    public int consultarCantidadWeights(String ip) throws Exception {
        String query = "SELECT COUNT(*) FROM weights";
        return executeQuery(ip, query);
    }

    public int consultarCantidadArticles(String ip) throws Exception {
        String query = "SELECT COUNT(*) FROM articles WHERE weight != '0' AND weight != '-1'";
        return executeQuery(ip, query);
    }

    private int executeQuery(String ip, String query) throws Exception {
        int count = 0;
        String url = "jdbc:mysql://" + ip + ":3306/geopos";
        try (Connection conn = DriverManager.getConnection(url, "root", "geocom"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
}
