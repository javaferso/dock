/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author JFerreira
 */
public class TicketService {
    public String getTicketCount(String ip) throws IOException, InterruptedException {
        String mysqlQuery = "SELECT COUNT(sendstate) FROM tickets WHERE date(ticketdate) BETWEEN date(now() - INTERVAL 7 DAY) AND date(now()) AND sendstate IN ('F', 'P');";
        String dir = "/home/supervision_caja/" + ip + "-ticketP.log";

        ProcessBuilder pb = new ProcessBuilder("ssh", "root@" + ip, "mysql", "-u", "root", "-pgocom", "geopos", "-e", "\"" + mysqlQuery + "\"");
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            // Guardar el resultado en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir))) {
                writer.write(output.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar errores
            System.err.println("Error executing command.");
        }

        return output.toString();
    }
    
}
