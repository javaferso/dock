/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.Incidentes;
import logica.Moneda;
import logica.Proveedores;
import logica.Servidores;
import logica.Sociedades;
import logica.Tipos;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author JFerreira
 */
public class IncidentesJpaController implements Serializable {
    
    public IncidentesJpaController(EntityManagerFactory emf){
        this.emf = emf;
    }

    public IncidentesJpaController() {
        emf = Persistence.createEntityManagerFactory("ServiciosPU");
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
 
    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public void edit(Incidentes incidentes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incidentes = em.merge(incidentes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incidentes.getId();
                if (findIncidenteById(id) == null) {
                    throw new NonexistentEntityException("The Incidents with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Incidentes> findIncidentesEntities(int maxResults, int firstResult) {
        return findIncidentesEntities(false, maxResults, firstResult);
    }

    private List<Incidentes> findIncidentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Incidentes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

//    public Incidentes crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigInteger monto, String moneda, String proveedor, Date fAutorizar, String oc, Date fEnvioProv, String hes, String sociedad, String ordenEstadistica, String textoBreve, String cotizacion, boolean activo, String usuarioId) throws PreexistingEntityException {
//        EntityManager emf = null;
//        Incidentes incidente = new Incidentes();
//        
//        try {
//            emf = getEntityManager();
//            emf.getTransaction().begin();
//            incidente.setTipo(tipo);
//            incidente.setMes(mes);
//            incidente.setFormato(formato);
//            incidente.setInc(inc);
//            incidente.setSap(sap);
//            incidente.setTienda(tienda);
//            incidente.setDetalle(detalle);
//            incidente.setMonto(monto);
//            incidente.setMoneda(moneda);
//            incidente.setProveedor(proveedor);
//            incidente.setfAutorizar(fAutorizar);
//            incidente.setOc(oc);
//            incidente.setfEnvioProv(fEnvioProv);
//            incidente.setHes(hes);
//            incidente.setSociedad(sociedad);
//            incidente.setOrdenEstadistica(ordenEstadistica);
//            incidente.setTextoBreve(textoBreve);
//            incidente.setCotizacion(cotizacion);
//            incidente.setActivo(activo);
//            incidente.setUsuarioId(usuarioId);
//            incidente.setFechaCreacion(new Date()); // Set current date for creation
//            incidente.setFechaActualizacion(new Date()); // Set current date for update
//            emf.persist(incidente);
//            emf.getTransaction().commit();
//            return incidente;
//            } catch (Exception e) {
//            if (findIncidenteById(incidente.getId()) != null) {
//                throw new PreexistingEntityException("Incidente " + incidente + " already exists.", e);
//            } 
//            throw e;
//        } finally {
//            if (emf != null) {
//                emf.close();
//            }
//        }
//    }

    public Incidentes findIncidenteById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Incidentes.class, id);
        } finally {
            em.close();
        }
    }

    public void crearNuevoIncidente(Incidentes incidente) {
        EntityManager em = null;
        EntityTransaction tx = null;
        System.out.println("crearNuevoIncidente called desde JpaController");
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(incidente);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear Incidente", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, String detalle, BigDecimal monto, String moneda, String proveedor, Date fAutorizar, String Oc, Date fEnvioProv, String Hes, String sociedad, String OrdenEstadistica, String TextoBreve, String Cotizacion, boolean activo, String usuario) throws PreexistingEntityException {
        EntityManager emf = null;
        Incidentes incidente = new Incidentes();
        
        try {
            emf = getEntityManager();
            emf.getTransaction().begin();
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
            incidente.setFechaCreacion(new Date()); // Set current date for creation
            incidente.setFechaActualizacion(new Date()); // Set current date for update
            emf.persist(incidente);
            emf.getTransaction().commit();
            } catch (Exception e) {
            if (findIncidenteById(incidente.getId()) != null) {
                throw new PreexistingEntityException("Incidente " + incidente + " already exists.", e);
            } 
            throw e;
        } finally {
           if (emf != null) {
                emf.close();
            }
       }
    }

    void crearIncidente(String formato, int mes, String inc, String tienda, int sap, String detalle, String oc, BigInteger monto, String hes, String ordenEstadistica, Date fAutorizar, String textoBreve, Date fEnvioProv, String cotizacion, Sociedades sociedad, String ordenEstadistica0, String textoBreve0, String cotizacion0, boolean activo, Usuario usuarioId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    
}
