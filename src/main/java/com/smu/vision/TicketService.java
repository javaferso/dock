package com.smu.vision;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TicketService {

    private static final String USER = "root";
    private static final String PASS = "geocom";

    public String getTicketCount(String ip) {
        try {
            // Construir la URL de la base de datos dinámicamente usando la IP proporcionada
            String dbUrl = "jdbc:mysql://" + ip + ":3306/geopos";
            
            Connection conn = DriverManager.getConnection(dbUrl, USER, PASS);
            Statement stmt = conn.createStatement();
            
            String mysqlQuery = "SELECT COUNT(sendstate) FROM tickets WHERE date(ticketdate) BETWEEN date(now() - INTERVAL 7 DAY) AND date(now()) AND sendstate IN ('F', 'P');";
            ResultSet rs = stmt.executeQuery(mysqlQuery);
            
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Ticket count for IP " + ip + ": " + count);
                
                // Crear y escribir en el archivo de log
                String dir = "/home/supervision_caja/" + ip + "-ticketP.log";
                try {
                    Files.write(Paths.get(dir), Integer.toString(count).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
                
                return Integer.toString(count);
            }
        } catch (Exception e) {
            System.err.println("Error obtaining ticket count for IP " + ip + ": " + e.getMessage());
            return "s/cnx";
        }
        
        return null;
    }
}
