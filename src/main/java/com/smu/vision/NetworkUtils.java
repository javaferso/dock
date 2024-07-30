
package com.smu.vision;

/**
 *
 * @author JFerreira
 */
import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

public class NetworkUtils {

    // Método asíncrono para realizar ping
    public static CompletableFuture<Boolean> isReachableAsync(String ipAddress) {
        return CompletableFuture.supplyAsync(() -> isReachable(ipAddress));
    }

    // Método síncrono para realizar ping
    public static boolean isReachable(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            boolean reachable = address.isReachable(7000); // Timeout de 7000ms (7 segundos)
            System.out.println("Ping a " + ipAddress + ": " + (reachable ? "online" : "offline"));
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
