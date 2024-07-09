/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.Tipos;

/**
 *
 * @author JFerreira
 */
public class TiposJpaController {
    
    public TiposJpaController(EntityManagerFactory emf){
        this.emf = emf;
    }
    public TiposJpaController() {
        emf = Persistence.createEntityManagerFactory("ServiciosPU");
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    Object findTiposByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipos.class, nombre);
        } finally {
            em.close();
        }
    }
    
    public List<Tipos> findTiposEntities(int maxResults, int firstResult) {
        return findTiposEntities(false, maxResults, firstResult);
    }

    private List<Tipos> findTiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipos.class));
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
}
