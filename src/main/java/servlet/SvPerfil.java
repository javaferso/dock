package servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import logica.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "SvPerfil", urlPatterns = {"/SvPerfil"})
public class SvPerfil extends HttpServlet {

    private final String DIRECTORY_PATH = "/home/supervision_caja/archivos/";
    private static final int MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB
    private static final int MAX_IMG_WIDTH = 600;
    private static final int MAX_IMG_HEIGHT = 600;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ejemplo para manejar la carga o actualización de la imagen
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute(SvLogin.SESSION_USER);
        if (user != null && ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);

            try {
                List<FileItem> formItems = upload.parseRequest(request);
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        System.out.println("Entramos al if del LIST");
                        // Verificar si el archivo es una imagen y sus dimensiones
                        if (item.getSize() > 0 && isImage(item) && isValidSize(item.getInputStream())) {
                            System.out.println("Paso los requisitos:");
                            String fileName = user.getIdUsuario() + getFileExtension(item.getName()); // Asignar nombre según el ID del usuario
                            String filePath = DIRECTORY_PATH + fileName;
                            File storeFile = new File(filePath);
                            System.out.println("Nombre de archivo de imagen: " + storeFile);
                            // Guarda el archivo en el disco
                            item.write(storeFile);
                            System.out.println("Archivo escrito");
                            response.sendRedirect("perfil.jsp?update=success");
                            System.out.println("Se ha cargado imagen exitosamente!");
                        } else {
                            // Manejo de error si la imagen no cumple los requisitos
                            response.sendRedirect("perfil.jsp?error=invalid_image");
                            System.out.println("Error al cargar imagen");
                            System.out.println("Id de Usuario: " + user.getIdUsuario());
                        }
                    }
                }
            } catch (Exception ex) {
                response.sendRedirect("perfil.jsp?error=uploading");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    // Función para determinar si el archivo es una imagen válida
    private boolean isImage(FileItem item) {
        String fileName = item.getName();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return fileExt.matches("png|jpg|jpeg|svg|tif|tiff");
    }

    // Función para verificar el tamaño de la imagen
    private boolean isValidSize(InputStream input) throws IOException {
        BufferedImage image = ImageIO.read(input);
        return image != null && image.getWidth() <= MAX_IMG_WIDTH && image.getHeight() <= MAX_IMG_HEIGHT;
    }

    // Función para obtener la extensión del archivo
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute(SvLogin.SESSION_USER);
        if (user != null) {
            String userId = user.getIdUsuario();
            String[] supportedExtensions = {".jpg", ".png", ".svg", ".jpeg"};
            File imageFile = null;

            // Buscar el archivo de imagen con cualquier extensión soportada
            for (String ext : supportedExtensions) {
                String fileName = userId + ext;
                String filePath = DIRECTORY_PATH + fileName;
                File tempImageFile = new File(filePath);
                if (tempImageFile.exists()) {
                    imageFile = tempImageFile;
                    break; // Encuentra la primera imagen que existe
                }
            }

            if (imageFile != null && imageFile.exists()) {
                // Establecer el tipo de contenido basado en la extensión del archivo
                
                setContentTypeAndStreamImage(response, imageFile);
            } else {
                File defaultImage = new File(DIRECTORY_PATH + "man.png");
                setContentTypeAndStreamImage(response, defaultImage);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    private void setContentTypeAndStreamImage(HttpServletResponse response, File imageFile) throws IOException {           
                String contentType = "image/jpeg"; // Valor por defecto
                String fileExtension = imageFile.getName().substring(imageFile.getName().lastIndexOf(".") + 1).toLowerCase();
                switch (fileExtension) {
                    case "png":
                        contentType = "image/png";
                        break;
                    case "svg":
                        contentType = "image/svg+xml";
                        break;
                    case "jpeg":
                        contentType = "image/jpeg";
                        break;
                    // Puedes agregar más casos si necesitas soportar otros formatos
                }
                response.setContentType(contentType);

                FileInputStream fis = new FileInputStream(imageFile);
                OutputStream out = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                fis.close();
                out.close();
      
    }

    @Override
    public String getServletInfo() {
        return "SvPerfil Servlet";
    }
}
