/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.Balanza;
import logica.ConsultoresPrecios;

/**
 *
 * @author JFerreira
 */
public class ConsultoresPreciosJpaController implements Serializable {

    ConsultoresPreciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ConsultoresPreciosJpaController () {
        emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(ConsultoresPrecios consultaprecios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(consultaprecios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ConsultoresPrecios> findConsultoresPreciosEntities() {
        return findConsultoresPreciosEntities(true, -1, -1);
    }
    
    public List<ConsultoresPrecios> findConsultoresPreciosEntities(int maxResults, int firstResult) {
        return findConsultoresPreciosEntities(false, maxResults, firstResult);
    }
    
    private List<ConsultoresPrecios> findConsultoresPreciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConsultoresPrecios.class));
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
    
    List<ConsultoresPrecios>obtenerConsultaPreciosByLocal(int local) {
        EntityManager em = emf.createEntityManager();
        try {
             Query q = em.createNamedQuery("ConsultoresPrecios.findByNumber");
             q.setParameter("number", local);
             return q.getResultList();
        } catch (NoResultException ex) {
            System.err.println("No se encontró lista de consultores para: " + local);
            out.print("No se encontró ningun numero de consultor con: " + local);
            return (List<ConsultoresPrecios>) ex;
            
        } catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        } finally {
            em.close();
        }
    }
    
}
