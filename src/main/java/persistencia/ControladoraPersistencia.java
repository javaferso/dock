/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import logica.Balanza;
import logica.ConsultoresPrecios;
import logica.Incidentes;
import logica.Locales;
import logica.Moneda;
import logica.Proveedores;
import logica.Servidores;
import logica.Sociedades;
import logica.Tienda;
import logica.Tipos;


import logica.Usuario;
import persistencia.exceptions.PreexistingEntityException;

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
    TiposJpaController tipJpa = new TiposJpaController(emf);
    MonedaJpaController monJpa = new MonedaJpaController(emf);
    ProveedoresJpaController provJpa = new ProveedoresJpaController(emf);
    SociedadesJpaController socJpa = new SociedadesJpaController(emf);
    TiendaJpaController tienJpa = new TiendaJpaController(emf);
    ConsultoresPreciosJpaController consulJpa = new ConsultoresPreciosJpaController(emf);
    
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

    public List<Servidores> findServidoresPorCriterio(String query) {
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

    public void crearIncidente(String formato, int mes, String inc, String tienda, int sap, String detalle, String oc, BigDecimal monto, String hes, String ordenEstadistica, Date fAutorizar, String textoBreve, Date fEnvioProv, String cotizacion, String ordenEstadistica1, String textoBreve1, String cotizacion1, String sociedad, boolean activo, String usuario) throws Exception {
        incJpa.crearIncidente(formato, mes, inc, tienda, sap, detalle, oc, monto, hes, ordenEstadistica, fAutorizar, textoBreve, fEnvioProv, cotizacion, sociedad, ordenEstadistica, textoBreve, cotizacion, activo, usuario);
    }

    public List<Tipos> getAllTipos() {
        return tipJpa.findTiposEntities(idRole, idRole);
    }

    public Moneda obtenerMonedaporCodigo(String monedaStr) {
        return monJpa.obtenerMonedaporCodigo(monedaStr);
    }

    public Proveedores obtenerProveedorporNombre(String proveedorStr) {
        return provJpa.obtenerProveedorporNombre(proveedorStr);
    }

    public void crearNuevoIncidente(Incidentes incidente) {
        System.out.println("crear Incidente e ControladoraPersistencia");
        incJpa.crearNuevoIncidente(incidente);
    }

    public Object obtenerTiposporNombre(String tipoStr) {
        return tipJpa.findTiposByNombre(tipoStr);
    }

    public List<Moneda> obtenerMonedas() {
        return monJpa.findAll();
    }

    public List<Proveedores> findProveedoresEntities() {
        return provJpa.findProveedoresdEntities();
    }

    public List<Sociedades> findSociedadesEntities() {
        return socJpa.findSociedadesEntities();
    }

    //public void crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigInteger monto, String moneda, String proveedor, Date fAutorizar, String oc, Date fEnvioProv, String hes, String sociedad, String ordenEstadistica, String textoBreve, String cotizacion, boolean activo, String usuario) {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    public Sociedades findSociedadName(String sociedadStr) {
        return socJpa.findSociedadesName(sociedadStr);
    }

    public Usuario findIdUsuario(String usuarioId) {
        return usuJpa.findUsuario(usuarioId);
    }

    public Tipos findTiposById(int Id) {
        return tipJpa.findTiposById(Id);
    }

    public Sociedades findSociedadesById(int Id) {
        return socJpa.findSociedades(Id);
    }

    public Proveedores findProveedoresById(int Id) {
        return provJpa.findProveedoresById(Id);
    }

    public Moneda findMonedaById(int Id) {
        return monJpa.findMonedaById(Id);
    }

    //public void crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigDecimal monto, String moneda, String proveedor, Date fAutorizar, String Oc, Date fEnvioProv, String Hes, String sociedad, String OrdenEstadistica, String TextoBreve, String Cotizacion, boolean activo, String usuario) throws PreexistingEntityException {
   //     incJpa.crearIncidente(tipo, mes, formato, inc, sap, tienda, detalle, monto, moneda, proveedor, fAutorizar, Oc, fEnvioProv, Hes, sociedad, OrdenEstadistica, TextoBreve, Cotizacion, activo, usuario);
   // }

    public Tienda findTiendaByLocal(String datosLocal) {
        return tienJpa.findTiendaByLocal(datosLocal);
    }

    public List<Tienda> findTiendasPorCriterio(String query) {
        return tienJpa.findTiendaPorCriterio(query);
    }

    public Incidentes findIncidenteById(int idIncidente) {
        return incJpa.findIncidenteById(idIncidente);
    }

    public void editarIncidente(Incidentes incidente) throws Exception {
        incJpa.edit(incidente);
    }

    public void editServidores(Servidores local) throws Exception {
        servJpa.edit(local);
    }

    public List<Balanza> obtenerBalanzasporLocal(int local) {
        return balJpa.getBalanzasByLocal(local);
    }
    
    public List<Object[]> obtenerEstadoNagios(String hostName) {
        EntityManager em = emb.createEntityManager();
        try {
            String sql = "SELECT return_code, output, service_description FROM monitor.nagios_chequeo_estado WHERE host_name = '" + hostName + "' ORDER BY service_description DESC;";
            Query query = em.createNativeQuery(sql);
            query.setParameter("hostName", hostName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ConsultoresPrecios> obtenerConsultaPreciosByLocal(int local) {
        return consulJpa.obtenerConsultaPreciosByLocal(local);
    }

}
