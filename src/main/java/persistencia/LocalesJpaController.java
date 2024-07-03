/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Locales;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author JFerreira
 */
public class LocalesJpaController implements Serializable {

    private Object em;
    Integer findByCajaTicket;

    public LocalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LocalesJpaController() {
        emf = Persistence.createEntityManagerFactory("VisorBoletasPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locales locales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(locales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locales locales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            locales = em.merge(locales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locales.getId();
                if (findLocales(id) == null) {
                    throw new NonexistentEntityException("The locales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locales locales;
            try {
                locales = em.getReference(Locales.class, id);
                locales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locales with id " + id + " no longer exists.", enfe);
            }
            em.remove(locales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locales> findLocalesEntities() {
        return findLocalesEntities(true, -1, -1);
    }

    public List<Locales> findLocalesEntities(int maxResults, int firstResult) {
        return findLocalesEntities(false, maxResults, firstResult);
    }

    private List<Locales> findLocalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locales.class));
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

    public Locales findLocales(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locales.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locales> rt = cq.from(Locales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    List<Locales> findLocalesEntitiesHabilitados() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findByHabilitado");
            q.setParameter("habilitado", true);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    List<String> findLocalesEntitiesPorFormatoHabilitados(String formato) {
         EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findByFormatoHabilitado");
            q.setParameter("formato", formato);
            q.setParameter("habilitado", true);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

   public List<String> findFormatos() {
        EntityManager em = getEntityManager();
        try {
            System.out.println("LocalesJpaController está buscando formato: ");
            return em.createNamedQuery("Locales.findDistinctFormato", String.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al buscar formato " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    List<String> findDistinctLocalesByFormato(String formato) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findDistinctLocalesByFormato");
            q.setParameter("formato", formato);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    List<String> findCajasPorLocal(String local) {
       EntityManager em = getEntityManager();
       try {
           Query q = em.createNamedQuery("Locales.findByLocal");
           q.setParameter("local", local);
           return q.getResultList();
       } catch (NoResultException ex) {
            System.err.println("No se encontró lista de cajas para: " + local);
            out.print("No se encontró ningun numero de caja con: " + local);
            return (List<String>) ex;
        }catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        } finally {
            em.close();
        }
    }

    List<Locales> findLocalesEntitiesPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    List<Locales> findLocalesEntitiesPorFormato(String formato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String findIpByCaja(String caja, String local) {
            EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT l.ip FROM Locales l WHERE l.caja = :caja AND l.local = :local");
            query.setParameter("caja", caja);
            query.setParameter("local", local);
            return (String) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        }

    String findNombreporLocal(String local) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findDistinctNombreTiendaByLocal");
            q.setParameter("local", local);
            return (String) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    List<String> findLocalesEntitiesPorNombreoLocal(String tiendaInput) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findLocalesporNombreoLocal");
            q.setParameter("tiendaInput", tiendaInput);
            return q.getResultList();
        } catch (NoResultException e) {
        } finally {
            em.close();
        }
        return null;
    }

    List<String> findLocalesporNombreoLocal(String query) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findLocalesporNombreoLocal");
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

    Integer findByCajaTicket(String caja, String local) {
         EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Locales.findByTicket");
            q.setParameter("caja", caja);
            q.setParameter("local", local);
            Integer result = (Integer) q.getSingleResult();
            return result != null ? result : 0;
        
        }catch (PersistenceException ex) {
            System.err.println("Error de conexión o problema con la base de datos: " + ex.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos.", ex);
        }catch (Exception e) {
            System.err.println("Error al obtener los tickets: " + e.getMessage());
            return 0; 
        } 
        finally {
            em.close();
        }
    }
    
}
