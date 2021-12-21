/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Paquete;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carla
 */
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PaqueteJpaController (){
        emf = Persistence.createEntityManagerFactory("Tpfinal_PU");
            }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getListaservicio() == null) {
            paquete.setListaservicio(new ArrayList<Servicio>());
        }
        if (paquete.getListaventa() == null) {
            paquete.setListaventa(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedListaservicio = new ArrayList<Servicio>();
            for (Servicio listaservicioServicioToAttach : paquete.getListaservicio()) {
                listaservicioServicioToAttach = em.getReference(listaservicioServicioToAttach.getClass(), listaservicioServicioToAttach.getCod_servicio());
                attachedListaservicio.add(listaservicioServicioToAttach);
            }
            paquete.setListaservicio(attachedListaservicio);
            List<Venta> attachedListaventa = new ArrayList<Venta>();
            for (Venta listaventaVentaToAttach : paquete.getListaventa()) {
                listaventaVentaToAttach = em.getReference(listaventaVentaToAttach.getClass(), listaventaVentaToAttach.getNum_venta());
                attachedListaventa.add(listaventaVentaToAttach);
            }
            paquete.setListaventa(attachedListaventa);
            em.persist(paquete);
            for (Servicio listaservicioServicio : paquete.getListaservicio()) {
                listaservicioServicio.getListapaquete().add(paquete);
                listaservicioServicio = em.merge(listaservicioServicio);
            }
            for (Venta listaventaVenta : paquete.getListaventa()) {
                Paquete oldPaqueOfListaventaVenta = listaventaVenta.getPaque();
                listaventaVenta.setPaque(paquete);
                listaventaVenta = em.merge(listaventaVenta);
                if (oldPaqueOfListaventaVenta != null) {
                    oldPaqueOfListaventaVenta.getListaventa().remove(listaventaVenta);
                    oldPaqueOfListaventaVenta = em.merge(oldPaqueOfListaventaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getCodigo_paquete());
            List<Servicio> listaservicioOld = persistentPaquete.getListaservicio();
            List<Servicio> listaservicioNew = paquete.getListaservicio();
            List<Venta> listaventaOld = persistentPaquete.getListaventa();
            List<Venta> listaventaNew = paquete.getListaventa();
            List<Servicio> attachedListaservicioNew = new ArrayList<Servicio>();
            for (Servicio listaservicioNewServicioToAttach : listaservicioNew) {
                listaservicioNewServicioToAttach = em.getReference(listaservicioNewServicioToAttach.getClass(), listaservicioNewServicioToAttach.getCod_servicio());
                attachedListaservicioNew.add(listaservicioNewServicioToAttach);
            }
            listaservicioNew = attachedListaservicioNew;
            paquete.setListaservicio(listaservicioNew);
            List<Venta> attachedListaventaNew = new ArrayList<Venta>();
            for (Venta listaventaNewVentaToAttach : listaventaNew) {
                listaventaNewVentaToAttach = em.getReference(listaventaNewVentaToAttach.getClass(), listaventaNewVentaToAttach.getNum_venta());
                attachedListaventaNew.add(listaventaNewVentaToAttach);
            }
            listaventaNew = attachedListaventaNew;
            paquete.setListaventa(listaventaNew);
            paquete = em.merge(paquete);
            for (Servicio listaservicioOldServicio : listaservicioOld) {
                if (!listaservicioNew.contains(listaservicioOldServicio)) {
                    listaservicioOldServicio.getListapaquete().remove(paquete);
                    listaservicioOldServicio = em.merge(listaservicioOldServicio);
                }
            }
            for (Servicio listaservicioNewServicio : listaservicioNew) {
                if (!listaservicioOld.contains(listaservicioNewServicio)) {
                    listaservicioNewServicio.getListapaquete().add(paquete);
                    listaservicioNewServicio = em.merge(listaservicioNewServicio);
                }
            }
            for (Venta listaventaOldVenta : listaventaOld) {
                if (!listaventaNew.contains(listaventaOldVenta)) {
                    listaventaOldVenta.setPaque(null);
                    listaventaOldVenta = em.merge(listaventaOldVenta);
                }
            }
            for (Venta listaventaNewVenta : listaventaNew) {
                if (!listaventaOld.contains(listaventaNewVenta)) {
                    Paquete oldPaqueOfListaventaNewVenta = listaventaNewVenta.getPaque();
                    listaventaNewVenta.setPaque(paquete);
                    listaventaNewVenta = em.merge(listaventaNewVenta);
                    if (oldPaqueOfListaventaNewVenta != null && !oldPaqueOfListaventaNewVenta.equals(paquete)) {
                        oldPaqueOfListaventaNewVenta.getListaventa().remove(listaventaNewVenta);
                        oldPaqueOfListaventaNewVenta = em.merge(oldPaqueOfListaventaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paquete.getCodigo_paquete();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getCodigo_paquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> listaservicio = paquete.getListaservicio();
            for (Servicio listaservicioServicio : listaservicio) {
                listaservicioServicio.getListapaquete().remove(paquete);
                listaservicioServicio = em.merge(listaservicioServicio);
            }
            List<Venta> listaventa = paquete.getListaventa();
            for (Venta listaventaVenta : listaventa) {
                listaventaVenta.setPaque(null);
                listaventaVenta = em.merge(listaventaVenta);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
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

    public Paquete findPaquete(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
