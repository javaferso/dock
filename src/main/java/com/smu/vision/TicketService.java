package com.smu.vision;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getReverseCount(String ip) {
        String resultado = "Registro vacío"; // Valor por defecto
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Construir la URL de la base de datos dinámicamente usando la IP proporcionada
            String dbUrl = "jdbc:mysql://" + ip + ":3306/geopos";

            conn = DriverManager.getConnection(dbUrl, USER, PASS);
            stmt = conn.createStatement();

            String mysqlQuery = "SELECT spdhContext FROM spdhreverse;";
            rs = stmt.executeQuery(mysqlQuery);

            if (rs.next()) {
                String dateReverse = rs.getString(1);
                if (dateReverse != null && !dateReverse.isEmpty()) {
                    // Recortar la cadena a los primeros 14 caracteres
                    String trimmedDate = dateReverse.substring(0, 14);

                    // Formato de la fecha en la cadena
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

                    // Convertir la cadena de fecha al objeto LocalDateTime
                    LocalDateTime reverseDateTime = LocalDateTime.parse(trimmedDate, formatter);

                    // Obtener la hora actual
                    LocalDateTime now = LocalDateTime.now();

                    // Calcular la diferencia entre las fechas
                    Duration duration = Duration.between(reverseDateTime, now);

                    // Calcular segundos de diferencia
                    long diffSeconds = duration.getSeconds();

                    if (diffSeconds > 1800) { // Si excede los 3600 segundos (1 hora)
                        long hours = diffSeconds / 3600;
                        long minutes = (diffSeconds % 3600) / 60;
                        long seconds = diffSeconds % 60;

                        // Formatear el resultado como "01h:04m:05s"
                        resultado = String.format("Caja con reversa de: %02dh:%02dm:%02ds", hours, minutes, seconds);
                    } else {
                        resultado = "Sin reversa";
                    }

                    // Crear y escribir en el archivo de log
                    String dir = "/home/supervision_caja/" + ip + "-reversePos.log";
                    try {
                        Files.write(Paths.get(dir), (dateReverse).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                }
            } else {
                resultado = "Sin reversas";
            }
        } catch (SQLException e) {
            System.err.println("SQL Error for IP " + ip + ": " + e.getMessage());
            resultado = "s/cnx";
        } catch (Exception e) {
            System.err.println("General error for IP " + ip + ": " + e.getMessage());
            resultado = "s/cnx";
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return resultado;
    }
    
    public String deleteReverse(String ip) {
        return null;
    }

}
