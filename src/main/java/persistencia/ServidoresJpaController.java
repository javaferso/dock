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
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servidores;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author JFerreira
 */
public class ServidoresJpaController implements Serializable {

    public ServidoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ServidoresJpaController(){
        emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servidores servidores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servidores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servidores servidores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servidores = em.merge(servidores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servidores.getId();
                if (findServidores(id) == null) {
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servidores servidores;
            try {
                servidores = em.getReference(Servidores.class, id);
                servidores.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servidores with id " + id + " no longer exists.", enfe);
            }
            em.remove(servidores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servidores> findServidoresEntities() {
        return findServidoresEntities(true, -1, -1);
    }

    public List<Servidores> findServidoresEntities(int maxResults, int firstResult) {
        return findServidoresEntities(false, maxResults, firstResult);
    }

    private List<Servidores> findServidoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servidores.class));
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

    public Servidores findServidores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servidores.class, id);
        } finally {
            em.close();
        }
    }

    public int getServidoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servidores> rt = cq.from(Servidores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Object findServidorByLocal(String datosLocal) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Servidores.findByLocal");
            q.setParameter("local", datosLocal);
            return q.getSingleResult();
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

    List<String> findServidoresPorCriterio(String query) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Servidores.findServidoresPorCriterio");
            q.setParameter("query", "%" + query + "%");
            return q.getResultList();
            
        } catch (NoResultException ex) {
            System.err.println("No se encontró lista de locales: " + query);
            out.print("No se encontró ningun numero de local con: " + query);
            return (List<String>) ex;
        }catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        } finally {
            em.close();
        }
    }

    long countByEstadoIp(String estado) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(s) FROM Servidores s WHERE s.estadoIp = :estado",
                Long.class
            );
            query.setParameter("estado", estado);
            return query.getSingleResult();
        } catch (NoResultException ex){
            System.err.println("No se encuentra la columna estado_ip");
            return 0;
        } catch (PersistenceException ex){
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        } finally {
            em.close();
        }
    }

    long countByEstadoEnlace(String estado) {
       EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(s) FROM Servidores s WHERE s.estadoEnlace = :estado",
                Long.class
            );
            query.setParameter("estado", estado);
            return query.getSingleResult();
        } catch (NoResultException ex){
            System.err.println("No se encuentra la columna estado_enlace");
            return 0;
        } catch (PersistenceException ex){
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        }
        finally {
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
