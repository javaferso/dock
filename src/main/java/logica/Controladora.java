/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import static java.lang.System.out;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

import persistencia.ControladoraPersistencia;

/**
 *
 * @author JFerreira
 */
public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    

    public Usuario obtenerUsuario(String id) {
        System.out.println("Controladora recibió el username: " + id);
        return controlPersis.obtenerUsuario(id);
        
    }
    
    public List<Usuario> obtenerUsuarios() {
        return controlPersis.obtenerUsuariosHabilitados();
    }

    public List<String> obtenerFormatos() {
        return controlPersis.obtenerFormatos();
    }

    public List<String> obtenerLocales(String formato) {
        return controlPersis.obtenerLocales(formato);
    }

    public List<String> obtenerCajas(String local) {
        return controlPersis.obtenerCajas(local);
    }

    public String findIpByCaja(String caja, String local) {
        return controlPersis.obtenerIp(caja, local);
    }

    public String obtenerNombreLocal(String local) {
        return controlPersis.obtenerNombreporLocal(local);
    }

    public Usuario crearUsuario(String idUsuario, String password, String nombre, String apellido, String sexo, int idRole, String habilitado) throws Exception {
        System.out.println("Controladora recibio el valor de user: " + idUsuario + " y nombre: " + nombre);
        return controlPersis.crearUsuario(idUsuario, password, nombre, apellido, sexo, idRole, habilitado);
}

    public void actualizarUsuario(Usuario usuario) throws Exception {
        controlPersis.editarUsuario(usuario);
    }

    public void editarImagenUsuario(Usuario usuario, byte[] imageBytes) throws Exception {
        controlPersis.editarImagenUsuario(usuario, imageBytes);
    }

    public void eliminarUsuario(String idUsuario) throws Exception {
        controlPersis.eliminarUsuario(idUsuario);
    }

    public List<String> obtenerNombreporLocaloNombre(String tiendaInput) {
        return controlPersis.obtenerNombreporLocaloNombre(tiendaInput);
       
    }

    public Object findServidorByLocal(String datosLocal) {
        return controlPersis.findServidorByLocal(datosLocal);  
    }

    public List<String> buscarLocalesPorCriterio(String query) {
         return controlPersis.findLocalesPorCriterio(query);
    }

    public List<String> buscarServidoresPorCriterio(String query) {
        return controlPersis.findServidoresPorCriterio(query);
    }

    public Integer findByCajaTicket(String caja, String local) {
        return controlPersis.findByCajaTicket(caja, local);
    }
    
    public long contarPorEstadoIp(String estado) {
        return controlPersis.contarPorEstadoIp(estado);
    }

    public long contarPorEstadoEnlace(String estado) {
        return controlPersis.contarPorEstadoEnlace(estado);
    }

    public List<Locales> obtenerLocales() {
        return controlPersis.obtenerLocales();
    }

    public List<Servidores> obtenerServidores() {
        return controlPersis.obtenerServidores();
    }

    public void obtenerTiendas() {
    }

    public List<Balanza> obtenerBalanzas() {
        return controlPersis.obtenerBalanzas();
    }

    public List<Incidentes> listarIncidentes() {
        return controlPersis.obtenerIncidentes();
    }

//    public void crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigInteger monto, String moneda, String proveedor, Date fAutorizar, String oc, Date fEnvioProv, String hes, String sociedad, String ordenEstadistica, String textoBreve, String cotizacion, boolean activo, String usuario) throws Exception {
//        controlPersis.crearIncidente(tipo, mes, formato, inc, sap, tienda, detalle, monto, moneda, proveedor, fAutorizar, oc, fEnvioProv, hes, sociedad, ordenEstadistica, textoBreve, cotizacion, activo, usuario);
//}

    public List<Tipos> getAllTipos() {
        return controlPersis.getAllTipos();
    }

    public Moneda obtenerMonedaporCodigo(String monedaStr) {
        return controlPersis.obtenerMonedaporCodigo(monedaStr);
    }

    public Proveedores obtenerNombreProveedor(String proveedorStr) {
        return controlPersis.obtenerProveedorporNombre(proveedorStr);
    }

    public List<Moneda> listarMonedas() {
        return controlPersis.obtenerMonedas();
    }

    public List<Proveedores> listarProveedores() {
        return controlPersis.findProveedoresEntities();
    }

    public List<Sociedades> listarSociedades() {
        return controlPersis.findSociedadesEntities();
    }

    public List<Usuario> listarUsuarios() {
        return controlPersis.obtenerUsuarios();
    }

    public Sociedades obtenerSociedadPorNombre(String sociedadStr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Usuario obtenerUsuarioPorId(String usuarioId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void crearIncidente(Incidentes incidente) {
        controlPersis.crearNuevoIncidente(incidente);
    }

    public Object obtenerTiposporNombre(String tipoStr) {
        return controlPersis.obtenerTiposporNombre(tipoStr);
    }
 
   

   
}