/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import logica.Moneda;

/**
 *
 * @author JFerreira
 */
public class MonedaJpaController {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiciosPU");

    MonedaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MonedaJpaController() {
        emf = Persistence.createEntityManagerFactory("ServiciosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    Moneda obtenerMonedaporCodigo(String codigo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Moneda> query = em.createQuery("SELECT m FROM Moneda m WHERE m.codigo = :codigo", Moneda.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
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

    public List<Moneda> findAll() {
        return findAll(true, -1, -1);
    }

    public List<Moneda> findAll(int maxResults, int firstResult) {
        return findAll(false, maxResults, firstResult);
    }

    private List<Moneda> findAll(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq
                    .select(cq.from(Moneda.class
                    ));
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

    Moneda findMonedaById(int Id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moneda.class, Id);
        } finally {
            em.close();
        }
    }

}
