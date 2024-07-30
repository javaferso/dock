package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Incidentes;
import logica.Moneda;
import logica.Proveedores;
import logica.Servidores;
import logica.Sociedades;
import logica.Tipos;
import logica.Usuario;

/**
 *
 * @author JFerreira
 */
@WebServlet(name = "SvIncidente", urlPatterns = {"/SvIncidente"})
public class SvIncidente extends HttpServlet {

    Controladora controladora = new Controladora();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listarIncidentes(request, response);
                break;
            case "loadForm":
                cargarFormulario(request, response);
                break;
            case "delete-form":
                cargarFormularioEliminar(request, response);
                break;
            case "edit-form":
                cargarFormularioEditar(request, response);
                break;
            default:
                listarIncidentes(request, response);
                break;
        }
    }

    private void listarIncidentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para listar incidentes
        List<Incidentes> incidentes = controladora.listarIncidentes();
        if (incidentes != null) {
            request.setAttribute("incidentes", incidentes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/listarIncidentes.jsp");
            dispatcher.forward(request, response);
        } else {
            // Manejar el caso cuando no hay incidentes o hubo un error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    private void cargarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de agregar incidente
        List<Tipos> tipos = controladora.getAllTipos(); // Obtén la lista de tipos desde la base de datos
        List<Moneda> monedas = controladora.listarMonedas();
        List<Proveedores> proveedores = controladora.listarProveedores();
        List<Sociedades> sociedades = controladora.listarSociedades();
        List<Usuario> usuarios = controladora.listarUsuarios();
        request.setAttribute("tipos", tipos);
        request.setAttribute("monedas", monedas);
        request.setAttribute("proveedores", proveedores);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("sociedades", sociedades);
        List<Servidores> listaServidores = controladora.obtenerServidores();
        request.setAttribute("listaServidores", listaServidores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/agregarIncidente.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarFormularioEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de eliminar incidente
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/eliminarIncidente.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para cargar formulario de editar incidente
        String idIncidenteStr = request.getParameter("id");
        try {
            int idIncidente = Integer.parseInt(idIncidenteStr);
            Incidentes incidente = controladora.findIncidenteById(idIncidente);
            if (incidente != null) {
                List<Tipos> tipos = controladora.getAllTipos();
                List<Moneda> monedas = controladora.listarMonedas();
                List<Proveedores> proveedores = controladora.listarProveedores();
                List<Sociedades> sociedades = controladora.listarSociedades();
                List<Usuario> usuarios = controladora.listarUsuarios();

                request.setAttribute("incidente", incidente);
                request.setAttribute("tipos", tipos);
                request.setAttribute("monedas", monedas);
                request.setAttribute("proveedores", proveedores);
                request.setAttribute("usuarios", usuarios);
                request.setAttribute("sociedades", sociedades);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/crud/editarIncidente.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Incidente no encontrado");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de incidente inválido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                agregarIncidente(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("Accion Agregar Incidente");
        } else if ("update".equals(action)) {
            try {
                actualizarIncidente(request, response);
            } catch (Exception ex) {
                Logger.getLogger(SvUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void agregarIncidente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, Exception {

        String tipo = request.getParameter("tipo");
        String mesStr = request.getParameter("mes");
        String formato = request.getParameter("formato");
        String inc = request.getParameter("inc");
        String sapStr = request.getParameter("sap");
        String tienda = request.getParameter("tienda");
        String detalle = request.getParameter("detalle");
        String montoStr = request.getParameter("monto");
        String moneda = request.getParameter("moneda");
        String proveedor = request.getParameter("proveedor");
        String fAutorizarStr = request.getParameter("f_autorizar");
        String Oc = request.getParameter("oc");
        String fEnvioProvStr = request.getParameter("f_envio_prov");
        String Hes = request.getParameter("hes");
        String sociedad = request.getParameter("sociedad");
        String OrdenEstadistica = request.getParameter("orden_estadistica");
        String TextoBreve = request.getParameter("texto_breve");
        String Cotizacion = request.getParameter("cotizacion");
        String activoStr = request.getParameter("activo");
        String usuario = request.getParameter("usuario");

        int mes = 0;
        int sap = 0;

        //Integer tipoId = null;
        //Integer proveedorId = null;
        //Integer monedaId = null;
        //Integer sociedadId = null;
        BigDecimal monto = BigDecimal.ZERO;
        Date fAutorizar = null;
        Date fEnvioProv = null;
        boolean activo = false;

        try {
            mes = Integer.parseInt(mesStr);
        } catch (NumberFormatException e) {
            mes = 0; // Valor por defecto
        }

        try {
            sap = Integer.parseInt(sapStr);
        } catch (NumberFormatException e) {
            sap = 0; // Valor por defecto
        }

        try {
            // Eliminar símbolo de dólar y comas
            String cleanedMontoStr = montoStr.replace("$", "").replace(",", "");
            monto = new BigDecimal(cleanedMontoStr);
        } catch (NumberFormatException e) {
            monto = BigDecimal.ZERO; // Valor por defecto
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (fAutorizarStr != null && !fAutorizarStr.isEmpty()) {
                fAutorizar = dateFormat.parse(fAutorizarStr);
            }
            if (fEnvioProvStr != null && !fEnvioProvStr.isEmpty()) {
                fEnvioProv = dateFormat.parse(fEnvioProvStr);
            }
        } catch (ParseException e) {
            // Manejar error de parseo
        }

        activo = "true".equalsIgnoreCase(activoStr) || "on".equalsIgnoreCase(activoStr);

        //Moneda moneda1 = controladora.findMonedaById(monedaId);
        //Proveedores proveedor1 = controladora.findProveedoresById(proveedorId);
        //Sociedades sociedad1 = controladora.findSociedadesById(sociedadId);
        //Usuario usuario1 = controladora.obtenerUsuarioPorId(usuario);
        //out.println("moneda obtenido de la base de datos: " + moneda1);
        //out.println("proveedor obtenido de la base de datos: " + proveedor1);
        //out.println("sociedad obtenido de la base de datos: " + sociedad1);
        //out.println("usuario obtenido de la base de datos: " + usuario1);
        Incidentes incidente = new Incidentes();

        incidente.setTipo(tipo);
        incidente.setMes(mes);
        incidente.setFormato(formato);
        incidente.setInc(inc);
        incidente.setSap(sap);
        incidente.setTienda(tienda);
        incidente.setDetalle(detalle);
        incidente.setMonto(monto);
        incidente.setMoneda(moneda);
        incidente.setProveedor(proveedor);
        incidente.setfAutorizar(fAutorizar);
        incidente.setOc(Oc);
        incidente.setfEnvioProv(fEnvioProv);
        incidente.setHes(Hes);
        incidente.setSociedad(sociedad);
        incidente.setOrdenEstadistica(OrdenEstadistica);
        incidente.setTextoBreve(TextoBreve);
        incidente.setCotizacion(Cotizacion);
        incidente.setActivo(activo);
        incidente.setUsuarioId(usuario);

        try {
            controladora.crearIncidente(tipo, mes, formato, inc, sap, tienda, detalle, monto, moneda, proveedor, fAutorizar, Oc, fEnvioProv, Hes, sociedad, OrdenEstadistica, TextoBreve, Cotizacion, activo, usuario);
            //controladora.crearIncidente(incidente);
            response.sendRedirect("servicios.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al agregar el incidente: " + e.getMessage());
            request.getRequestDispatcher("agregarIncidente.jsp").forward(request, response);
        }
    }

    private void actualizarIncidente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        String idIncidenteStr = request.getParameter("id");
        String tipo = request.getParameter("tipo");
        String mesStr = request.getParameter("mes");
        String formato = request.getParameter("formato");
        String inc = request.getParameter("inc");
        String sapStr = request.getParameter("sap");
        String tienda = request.getParameter("tienda");
        String detalle = request.getParameter("detalle");
        String montoStr = request.getParameter("monto");
        String moneda = request.getParameter("moneda");
        String proveedor = request.getParameter("proveedor");
        String fAutorizarStr = request.getParameter("f_autorizar");
        String Oc = request.getParameter("oc");
        String fEnvioProvStr = request.getParameter("f_envio_prov");
        String Hes = request.getParameter("hes");
        String sociedad = request.getParameter("sociedad");
        String OrdenEstadistica = request.getParameter("orden_estadistica");
        String TextoBreve = request.getParameter("texto_breve");
        String Cotizacion = request.getParameter("cotizacion");
        String activoStr = request.getParameter("activo");
        String usuario = request.getParameter("usuario");

        int mes = 0;
        int sap = 0;
        BigDecimal monto = BigDecimal.ZERO;
        Date fAutorizar = null;
        Date fEnvioProv = null;
        boolean activo = false;

        try {
            int idIncidente = Integer.parseInt(idIncidenteStr);
            mes = Integer.parseInt(mesStr);
            sap = Integer.parseInt(sapStr);

            String cleanedMontoStr = montoStr.replace("$", "").replace(",", "");
            monto = new BigDecimal(cleanedMontoStr);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (fAutorizarStr != null && !fAutorizarStr.isEmpty()) {
                fAutorizar = dateFormat.parse(fAutorizarStr);
            }
            if (fEnvioProvStr != null && !fEnvioProvStr.isEmpty()) {
                fEnvioProv = dateFormat.parse(fEnvioProvStr);
            }

            activo = "true".equalsIgnoreCase(activoStr) || "on".equalsIgnoreCase(activoStr);

            Incidentes incidente = controladora.findIncidenteById(idIncidente);
            if (incidente != null) {
                incidente.setTipo(tipo);
                incidente.setMes(mes);
                incidente.setFormato(formato);
                incidente.setInc(inc);
                incidente.setSap(sap);
                incidente.setTienda(tienda);
                incidente.setDetalle(detalle);
                incidente.setMonto(monto);
                incidente.setMoneda(moneda);
                incidente.setProveedor(proveedor);
                incidente.setfAutorizar(fAutorizar);
                incidente.setOc(Oc);
                incidente.setfEnvioProv(fEnvioProv);
                incidente.setHes(Hes);
                incidente.setSociedad(sociedad);
                incidente.setOrdenEstadistica(OrdenEstadistica);
                incidente.setTextoBreve(TextoBreve);
                incidente.setCotizacion(Cotizacion);
                incidente.setActivo(activo);
                incidente.setUsuarioId(usuario);

                controladora.actualizarIncidente(incidente);
                response.sendRedirect("servicios.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Incidente no encontrado");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en los datos numéricos del formulario: " + e.getMessage());
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en el formato de fecha: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor: " + e.getMessage());
        }
    }

}
