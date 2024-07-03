package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @autor JFerreira
 */
@WebServlet(name = "SvProgreso", urlPatterns = {"/SvProgreso"})
public class SvProgreso extends HttpServlet {

    private static final int MAX_THREADS = 10; // Máximo de hilos en el pool

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configurar la cabecera para transmitir respuestas en fragmentos
        response.setHeader("Transfer-Encoding", "chunked");

        PrintWriter out = response.getWriter();
        // Iniciar el procesamiento de las IPs y la transmisión de la barra de progreso
        String contenidoIP = request.getParameter("contenidoIP");
        if (contenidoIP != null) {
            String[] ips = contenidoIP.split("\\r?\\n");
            int total = ips.length;
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
            int[] progress = {0};

            for (String ip : ips) {
                executor.submit(() -> {
                    try {
                        String[] cmd = {"sudo", "-u", "root", "/home/soportetienda/rollout/sco_weights/archivos/sco_peso_definido.sh", ip};
                        System.out.println("Comando ejecutado: " + Arrays.toString(cmd));
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
                            output.append(outputLine).append("\n");
                            System.out.println(outputLine);
                        }
                        int exitCode = process.waitFor();
                        System.out.println("Script ejecutado con exit code: " + exitCode);

                        // Actualizar progreso de manera thread-safe
                        synchronized (out) {
                            progress[0]++;
                            int progreso = progress[0] * 100 / total;
                            out.print(String.valueOf(progreso));
                            out.flush(); // Vacía el buffer para enviar el fragmento al cliente
                            response.setContentType("text/plain");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(String.valueOf(progreso));
                            System.out.println("Avance: " + progreso);
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al procesar la IP de SCO: " + ip);
                    }
                });
            }

            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.MINUTES)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
