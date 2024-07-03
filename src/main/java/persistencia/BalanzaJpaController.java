/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.Balanza;

/**
 *
 * @author JFerreira
 */
public class BalanzaJpaController implements Serializable {

    public BalanzaJpaController(EntityManagerFactory emb) {
        this.emb = emb;
    }
    
    public BalanzaJpaController() {
        emb = Persistence.createEntityManagerFactory("TiOperacionesPU");
    }
    
    private EntityManagerFactory emb = null;
    
    public EntityManager getEntityManager() {
        return emb.createEntityManager();
    }

    public void create(Balanza balanzas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(balanzas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Balanza> findBalanzaEntities() {
        return findBalanzaEntities(true, -1, -1);
    }
    
    public List<Balanza> findBalanzaEntities(int maxResults, int firstResult) {
        return findBalanzaEntities(false, maxResults, firstResult);
    }
    
    private List<Balanza> findBalanzaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Balanza.class));
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
        EntityManager em = emb.createEntityManager();
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
