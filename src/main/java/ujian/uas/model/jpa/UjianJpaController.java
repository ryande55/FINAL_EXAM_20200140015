/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.uas.model.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ujian.uas.exceptions.NonexistentEntityException;
import ujian.uas.exceptions.PreexistingEntityException;
import ujian.uas.model.entity.Ujian;

/**
 *
 * @author ASUS
 */
public class UjianJpaController implements Serializable {

    public UjianJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ujian_uas_jar_0.0.1-SNAPSHOTPU");
    
    public UjianJpaController() {}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ujian ujian) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ujian);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUjian(ujian.getId()) != null) {
                throw new PreexistingEntityException("Ujian " + ujian + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ujian ujian) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ujian = em.merge(ujian);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ujian.getId();
                if (findUjian(id) == null) {
                    throw new NonexistentEntityException("The ujian with id " + id + " no longer exists.");
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
            Ujian ujian;
            try {
                ujian = em.getReference(Ujian.class, id);
                ujian.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ujian with id " + id + " no longer exists.", enfe);
            }
            em.remove(ujian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ujian> findUjianEntities() {
        return findUjianEntities(true, -1, -1);
    }

    public List<Ujian> findUjianEntities(int maxResults, int firstResult) {
        return findUjianEntities(false, maxResults, firstResult);
    }

    private List<Ujian> findUjianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ujian.class));
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

    public Ujian findUjian(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ujian.class, id);
        } finally {
            em.close();
        }
    }

    public int getUjianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ujian> rt = cq.from(Ujian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
