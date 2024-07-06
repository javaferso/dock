/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import logica.Balanza;
import logica.Incidentes;
import logica.Locales;
import logica.Servidores;


import logica.Usuario;

/**
 *
 * @author JFerreira
 */
public class ControladoraPersistencia {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
    private final EntityManagerFactory emb = Persistence.createEntityManagerFactory("TiOperacionesPU");
    UsuarioJpaController usuJpa = new UsuarioJpaController(emf);
    LocalesJpaController locJpa = new LocalesJpaController(emf);
    ServidoresJpaController servJpa = new ServidoresJpaController(emf);
    BalanzaJpaController balJpa = new BalanzaJpaController(emb);
    IncidentesJpaController incJpa = new IncidentesJpaController(emf);
   
    
    private int idRole;
    public List<Servidores> obtenerServidores;
    public List<Usuario> obtenerUsuarios () {
        return usuJpa.findUsuarioEntities();
    }
    
    public Usuario obtenerUsuario (String username) {
        System.out.println("ControladoraPersistencia recibió el username: " + username);
        return usuJpa.findUsuario(username);
    }


    public void editarUsuario(Usuario usuario) throws Exception {
        usuJpa.edit(usuario);
    }

    public void eliminarUsuario(String idUsuario) throws Exception {
        usuJpa.destroy(idUsuario);
    }

    public List<Usuario> obtenerUsuariosHabilitados() {
        return usuJpa.findUsuarioEntitiesHabilitados();
    }

    public List<Usuario> obtenerUsuariosDeshabilitados() {
        return usuJpa.findUsuarioEntitiesDeshabilitados();
    }

    public List<Usuario> obtenerUsuariosPorTipo(String tipo) {
        return usuJpa.findUsuarioEntitiesPorTipo(tipo);
    }

    public List<Usuario> obtenerUsuariosPorNombre(String nombre) {
        return usuJpa.findUsuarioEntitiesPorNombre(nombre);
    }

    public List<Usuario> obtenerUsuariosPorApellido(String apellido) {
        return usuJpa.findUsuarioEntitiesPorApellido(apellido);
    }

    public List<Usuario> obtenerUsuariosPorNombreApellido(String nombre, String apellido) {
        return usuJpa.findUsuarioEntitiesPorNombreApellido(nombre, apellido);
    }

    public List<Usuario> obtenerUsuariosPorNombreApellidoTipo(String nombre, String apellido, String tipo) {
        return usuJpa.findUsuarioEntitiesPorNombreApellidoTipo(nombre, apellido, tipo);
    }

    public List<Usuario> obtenerUsuariosPorNombreTipo(String nombre, String tipo) {
        return usuJpa.findUsuarioEntitiesPorNombreTipo(nombre, tipo);
    }

    public List<Locales> obtenerLocales() {
        return locJpa.findLocalesEntities();
    }

    public List<Locales> obtenerLocalesHabilitados() {
        return locJpa.findLocalesEntitiesHabilitados();
    }

    public List<Locales> obtenerLocalesPorNombre(String nombre) {
        return locJpa.findLocalesEntitiesPorNombre(nombre);
    }


    public List<Locales> obtenerLocalesPorFormato(String formato) {
        return locJpa.findLocalesEntitiesPorFormato(formato);
    }

    public List<String> obtenerLocalesPorFormatoHabilitados(String formato) {
        return locJpa.findLocalesEntitiesPorFormatoHabilitados(formato);
    }

    public List<String> obtenerCajas(String local) {
        return locJpa.findCajasPorLocal(local);
    }

    public List<String> obtenerFormatos() {
        return locJpa.findFormatos();
        
    }

    public List<String> obtenerLocales(String formato) {
        return locJpa.findDistinctLocalesByFormato(formato);
        
    }

    public String obtenerIp(String caja, String local) {
        return locJpa.findIpByCaja(caja, local);
    }

    public String obtenerNombreporLocal(String local) {
        return locJpa.findNombreporLocal(local);
    }

    public Usuario crearUsuario(String idUsuario, String password, String nombre, String apellido, String sexo, int idRole, String habilitado) throws Exception {
        return UsuarioJpaController.createUsuario(idUsuario, password, nombre, apellido, sexo, idRole, habilitado);
    }

    public void editarImagenUsuario(Usuario usuario, byte[] imageBytes) throws Exception {
        usuJpa.editarImagenUsuario(usuario, imageBytes);
    }

    public List<String> obtenerNombreporLocaloNombre(String tiendaInput) {
        return locJpa.findLocalesEntitiesPorNombreoLocal(tiendaInput);
    }

    public Object findServidorByLocal(String datosLocal) {
        return servJpa.findServidorByLocal(datosLocal);
    }

    public List<String> findLocalesPorCriterio(String query) {
         return locJpa.findLocalesporNombreoLocal(query);
    }

    public List<String> findServidoresPorCriterio(String query) {
        return servJpa.findServidoresPorCriterio(query);
    }

    public Integer findByCajaTicket(String caja, String local) {
        return locJpa.findByCajaTicket(caja, local);

    }

    public long contarPorEstadoIp(String estado) {
        return servJpa.countByEstadoIp(estado);
    }

    public long contarPorEstadoEnlace(String estado) {
        return servJpa.countByEstadoEnlace(estado);
    }

    public List<Servidores> obtenerServidores() {
        return servJpa.findServidoresEntities();
    }

    public List<Balanza> obtenerBalanzas() {
        return balJpa.findBalanzaEntities();
    }

    public List<Incidentes> obtenerIncidentes() {
        return incJpa.findIncidentesEntities(idRole, idRole);
    }

    public void crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigInteger monto, String moneda, String proveedor, Date fAutorizar, String oc, Date fEnvioProv, String hes, String sociedad, String ordenEstadistica, String textoBreve, String cotizacion, boolean activo) throws Exception {
        incJpa.crearIncidente(tipo, mes, formato, inc, sap, tienda, detalle, monto, moneda, proveedor, fAutorizar, oc, fEnvioProv, hes, sociedad, ordenEstadistica, textoBreve, cotizacion, activo);
    }

}
