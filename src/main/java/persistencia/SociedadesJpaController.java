/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import logica.Sociedades;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author JFerreira
 */
public class SociedadesJpaController {

    SociedadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SociedadesJpaController() {
        emf = Persistence.createEntityManagerFactory("ServiciosPU");
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Sociedades sociedades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sociedades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
     public void edit(Sociedades sociedades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sociedades = em.merge(sociedades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sociedades.getId();
                if (findSociedades(id) == null) {
                    throw new NonexistentEntityException("The servidores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Sociedades findSociedades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sociedades.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Sociedades> findSociedadesEntities() {
        return findSociedadesEntities(true, -1, -1);
    }

    public List<Sociedades> findSociedadesEntities(int maxResults, int firstResult) {
        return findSociedadesEntities(false, maxResults, firstResult);
    }

    private List<Sociedades> findSociedadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sociedades.class));
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

    Sociedades findSociedadesName(String sociedadStr) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sociedades> query = em.createQuery("SELECT s FROM Sociedades s WHERE s.nombre = :nombre", Sociedades.class);
            query.setParameter("nombre", sociedadStr);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
