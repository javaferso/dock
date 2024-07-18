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
import logica.Tienda;

/**
 *
 * @author JFerreira
 */
public class TiendaJpaController implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MonitorTIPU");
    
    TiendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TiendaJpaController() {
        emf = Persistence.createEntityManagerFactory("MonitorTIPU");
    }
    public List<Tienda> findTiendaEntities() {
        return findTiendaEntities(true, -1, -1);
    }

    public List<Tienda> findTiendaEntities(int maxResults, int firstResult) {
        return findTiendaEntities(false, maxResults, firstResult);
    }

    private List<Tienda> findTiendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tienda.class));
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
    
    Tienda findTiendaById(int Id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tienda.class, Id);
        } finally {
            em.close();
        }
        
    }
    
     public Tienda findTiendaByLocal(String datosLocal) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Tienda.findByNumero");
            q.setParameter("numero", datosLocal);
            return (Tienda) q.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("No se encontró el servidor con numero de local: " + datosLocal);
            out.print("No se encontró el servidor con numero de local: " + datosLocal);
            return null;
        } catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        } finally {
            em.close();
        }
    }
     
     List<Tienda> findTiendaPorCriterio(String query) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Tienda.findTiendaPorCriterio");
            q.setParameter("query", "%" + query + "%");
            return q.getResultList();        
        } catch (NoResultException ex) {
            System.err.println("No se encontró lista de locales: " + query);
            out.print("No se encontró ningun numero de local con: " + query);
            return (List<Tienda>) ex;
        }catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
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
    
    
    
    
}
