/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BashExecutor {

    public static String executeBashScript(String scriptPath, String... args) {
        StringBuilder result = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(scriptPath + " " + String.join(" ", args));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return result.toString();
            } else {
                throw new RuntimeException("Bash script execution failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute bash script", e);
        }
        
    }
}

