/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.Incidentes;
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

    public Incidentes crearIncidente(String tipo, int mes, String formato, String inc, int sap, String tienda, BigInteger monto, String moneda, String proveedor, Date fAutorizar, String oc, Date fEnvioProv, String hes, String sociedad, String ordenEstadistica, String textoBreve, String cotizacion, boolean activo) throws PreexistingEntityException {
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
            incidente.setMonto(monto);
            incidente.setMoneda(moneda);
            incidente.setProveedor(proveedor);
            incidente.setFAutorizar(fAutorizar);
            incidente.setOc(oc);
            incidente.setFEnvioProv(fEnvioProv);
            incidente.setHes(hes);
            incidente.setSociedad(sociedad);
            incidente.setOrdenEstadistica(ordenEstadistica);
            incidente.setTextoBreve(textoBreve);
            incidente.setCotizacion(cotizacion);
            incidente.setActivo(activo);
            incidente.setFechaCreacion(new Date()); // Set current date for creation
            incidente.setFechaActualizacion(new Date()); // Set current date for update
            emf.persist(incidente);
            emf.getTransaction().commit();
            return incidente;
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

    private Object findIncidenteById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Incidentes.class, id);
        } finally {
            em.close();
        }
    }
   
    
    
}
