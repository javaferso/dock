/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JFerreira
 */
public class NetworkUtils {
       public static CompletableFuture<Boolean> isReachableAsync(String ipAddress) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                InetAddress inet = InetAddress.getByName(ipAddress);
                return inet.isReachable(2000); // 2000 ms = 2 segundos de timeout
            } catch (IOException e) {
                Logger.getLogger(NetworkUtils.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        });
    }
    
}
