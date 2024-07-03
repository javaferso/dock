package servlet;

import java.io.BufferedReader;
import java.io.FileReader;
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

@WebServlet(name = "SvRoll", urlPatterns = {"/SvRoll"})
public class SvRoll extends HttpServlet {

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
        String filePath = request.getParameter("filePath");
        String filePathCommands = request.getParameter("filePathCommands");
        String flagValida = request.getParameter("flagValida").toUpperCase();

        if (filePath != null && filePathCommands != null) {
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

            // Clase interna para mantener variables finalizables
            class TaskContext {
                int total;
                int processedCount = 0;
            }

            final TaskContext context = new TaskContext();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    context.total++;
                }

                if (context.total == 0) {
                    throw new ServletException("El archivo está vacío");
                }

                try (BufferedReader readerIPs = new BufferedReader(new FileReader(filePath))) {
                    String[] lines = readerIPs.lines().toArray(String[]::new);

                    for (String ipLine : lines) {
                        executor.submit(() -> {
                            try {
                                processLine(ipLine, filePathCommands, flagValida);

                                synchronized (out) {
                                    context.processedCount++;
                                    int progreso = context.processedCount * 100 / context.total;
                                    out.print(String.valueOf(progreso));
                                    out.flush(); // Vacía el buffer para enviar el fragmento al cliente
                                    response.setContentType("text/plain");
                                    response.setCharacterEncoding("UTF-8");
                                    response.getWriter().write(String.valueOf(progreso));
                                    System.out.println("Avance: " + progreso);
                                }
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                                System.out.println("Error al procesar la IP: " + ipLine);
                            }
                        });
                    }
                }
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

    private void processLine(String ipLine, String filePathCommands, String flagValida) throws IOException, InterruptedException {
        String[] parts = ipLine.split(";");
        String tiendaT = parts[0];
        String ipSrvT = parts[1];
        String tipoT = parts[2].toUpperCase();
        String filtroT = parts[3].toUpperCase();
        String nombreT = String.join(" ", Arrays.copyOfRange(parts, 4, parts.length));

        // Log de inicio
        log(String.format("Inicio Recorrido: %s %s", tiendaT, nombreT));

        boolean generalOK = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathCommands))) {
            String commandLine;
            while ((commandLine = reader.readLine()) != null) {
                String[] commandParts = commandLine.split(";");
                String pathOri = commandParts[0].split("#")[0].trim();
                String pathDest = commandParts[1].split("#")[0].trim();
                String perm = commandParts[2].trim();
                String user = commandParts[3].trim();
                String md5 = commandParts[4].trim();
                String scriptSrv = commandParts[6].trim();

                // Ejecutar los comandos equivalentes en Java
                executeCommands(ipSrvT, pathOri, pathDest, user, perm, md5, flagValida, tiendaT, tipoT);
            }
        }

        if (generalOK) {
            log(String.format("Fin recorrido: %s %s", tiendaT, nombreT));
        }
    }

    private void executeCommands(String ipSrvT, String pathOri, String pathDest, String user, String perm, String md5, String flagValida, String tiendaT, String tipoT) throws IOException, InterruptedException {
        if (tipoT.equals("SRV")) {
            // Aquí puedes realizar operaciones específicas para SRV
            if (!ping(ipSrvT)) {
                System.out.println("Servidor OFFLINE: " + ipSrvT);
                return;
            }

            ensureKnownHost(ipSrvT);

            scpFile(pathOri, ipSrvT, pathDest);
            setPermissions(ipSrvT, pathDest, user, perm);

            if (flagValida.equals("SIVAL")) {
                validateFile(ipSrvT, pathDest, user, perm, md5, tiendaT);
            }
        } else {
            // Aquí puedes manejar otros tipos como POS, etc.
        }
    }

    private boolean ping(String ip) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ping", "-c2", ip);
        Process process = builder.start();
        int exitCode = process.waitFor();
        return exitCode == 0;
    }

    private void ensureKnownHost(String ipSrvT) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ssh-keyscan", "-H", ipSrvT);
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Error ensuring known host: " + ipSrvT);
        }
    }

    private void scpFile(String pathOri, String ipSrvT, String pathDest) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("scp", "-o StrictHostKeyChecking=no", pathOri, String.format("root@%s:%s", ipSrvT, pathDest));
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Error copying file to: " + ipSrvT);
        }
    }

    private void setPermissions(String ipSrvT, String pathDest, String user, String perm) throws IOException, InterruptedException {
        String fileName = pathDest.substring(pathDest.lastIndexOf('/') + 1);
        String remotePath = pathDest + fileName;

        String chownCmd = String.format("chown %s %s", user, remotePath);
        String chmodCmd = String.format("chmod %s %s", convertPermissions(perm), remotePath);

        executeRemoteCommand(ipSrvT, chownCmd);
        executeRemoteCommand(ipSrvT, chmodCmd);
    }

    private void validateFile(String ipSrvT, String pathDest, String user, String perm, String md5, String tiendaT) throws IOException, InterruptedException {
        String fileName = pathDest.substring(pathDest.lastIndexOf('/') + 1);
        String remotePath = pathDest + fileName;

        String statCmd = String.format("stat -c '%%A' %s; stat -c '%%U:%%G' %s; md5sum %s", remotePath, remotePath, remotePath);
        String validationOutput = executeRemoteCommand(ipSrvT, statCmd);

        // Validar permisos, propietario y MD5
        // ... (aquí puedes agregar la lógica de validación similar al script Bash)
    }

    private String executeRemoteCommand(String ipSrvT, String command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ssh", "-o StrictHostKeyChecking=no", String.format("root@%s", ipSrvT), command);
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Error executing remote command: " + command);
        }

        try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String s;
            while ((s = stdInput.readLine()) != null) {
                output.append(s).append("\n");
            }
            return output.toString();
        }
    }

    private String convertPermissions(String perm) {
        // Convierte permisos a formato numérico (ej. rwxr-xr-x a 755)
        // Implementa la lógica de conversión aquí
        return "755"; // Ejemplo
    }

    public void log(String message) {
        // Implementa la lógica de logging aquí
        System.out.println(message);
    }
}

