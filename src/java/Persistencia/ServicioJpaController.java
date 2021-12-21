/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Paquete;
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
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ServicioJpaController (){
        emf = Persistence.createEntityManagerFactory("Tpfinal_PU");
            }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getListapaquete() == null) {
            servicio.setListapaquete(new ArrayList<Paquete>());
        }
        if (servicio.getVenta() == null) {
            servicio.setVenta(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paquete> attachedListapaquete = new ArrayList<Paquete>();
            for (Paquete listapaquetePaqueteToAttach : servicio.getListapaquete()) {
                listapaquetePaqueteToAttach = em.getReference(listapaquetePaqueteToAttach.getClass(), listapaquetePaqueteToAttach.getCodigo_paquete());
                attachedListapaquete.add(listapaquetePaqueteToAttach);
            }
            servicio.setListapaquete(attachedListapaquete);
            List<Venta> attachedVenta = new ArrayList<Venta>();
            for (Venta ventaVentaToAttach : servicio.getVenta()) {
                ventaVentaToAttach = em.getReference(ventaVentaToAttach.getClass(), ventaVentaToAttach.getNum_venta());
                attachedVenta.add(ventaVentaToAttach);
            }
            servicio.setVenta(attachedVenta);
            em.persist(servicio);
            for (Paquete listapaquetePaquete : servicio.getListapaquete()) {
                listapaquetePaquete.getListaservicio().add(servicio);
                listapaquetePaquete = em.merge(listapaquetePaquete);
            }
            for (Venta ventaVenta : servicio.getVenta()) {
                Servicio oldSerOfVentaVenta = ventaVenta.getSer();
                ventaVenta.setSer(servicio);
                ventaVenta = em.merge(ventaVenta);
                if (oldSerOfVentaVenta != null) {
                    oldSerOfVentaVenta.getVenta().remove(ventaVenta);
                    oldSerOfVentaVenta = em.merge(oldSerOfVentaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCod_servicio());
            List<Paquete> listapaqueteOld = persistentServicio.getListapaquete();
            List<Paquete> listapaqueteNew = servicio.getListapaquete();
            List<Venta> ventaOld = persistentServicio.getVenta();
            List<Venta> ventaNew = servicio.getVenta();
            List<Paquete> attachedListapaqueteNew = new ArrayList<Paquete>();
/*            for (Paquete listapaqueteNewPaqueteToAttach : listapaqueteNew) {
                listapaqueteNewPaqueteToAttach = em.getReference(listapaqueteNewPaqueteToAttach.getClass(), listapaqueteNewPaqueteToAttach.getCodigo_paquete());
                attachedListapaqueteNew.add(listapaqueteNewPaqueteToAttach);
            }*/
            listapaqueteNew = attachedListapaqueteNew;
            servicio.setListapaquete(listapaqueteNew);
            List<Venta> attachedVentaNew = new ArrayList<Venta>();
   /*         for (Venta ventaNewVentaToAttach : ventaNew) {
                ventaNewVentaToAttach = em.getReference(ventaNewVentaToAttach.getClass(), ventaNewVentaToAttach.getNum_venta());
                attachedVentaNew.add(ventaNewVentaToAttach);
            }*/
            ventaNew = attachedVentaNew;
            servicio.setVenta(ventaNew);
            servicio = em.merge(servicio);
            for (Paquete listapaqueteOldPaquete : listapaqueteOld) {
                if (!listapaqueteNew.contains(listapaqueteOldPaquete)) {
                    listapaqueteOldPaquete.getListaservicio().remove(servicio);
                    listapaqueteOldPaquete = em.merge(listapaqueteOldPaquete);
                }
            }
            for (Paquete listapaqueteNewPaquete : listapaqueteNew) {
                if (!listapaqueteOld.contains(listapaqueteNewPaquete)) {
                    listapaqueteNewPaquete.getListaservicio().add(servicio);
                    listapaqueteNewPaquete = em.merge(listapaqueteNewPaquete);
                }
            }
            for (Venta ventaOldVenta : ventaOld) {
                if (!ventaNew.contains(ventaOldVenta)) {
                    ventaOldVenta.setSer(null);
                    ventaOldVenta = em.merge(ventaOldVenta);
                }
            }
            for (Venta ventaNewVenta : ventaNew) {
                if (!ventaOld.contains(ventaNewVenta)) {
                    Servicio oldSerOfVentaNewVenta = ventaNewVenta.getSer();
                    ventaNewVenta.setSer(servicio);
                    ventaNewVenta = em.merge(ventaNewVenta);
                    if (oldSerOfVentaNewVenta != null && !oldSerOfVentaNewVenta.equals(servicio)) {
                        oldSerOfVentaNewVenta.getVenta().remove(ventaNewVenta);
                        oldSerOfVentaNewVenta = em.merge(oldSerOfVentaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCod_servicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCod_servicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Paquete> listapaquete = servicio.getListapaquete();
            for (Paquete listapaquetePaquete : listapaquete) {
                listapaquetePaquete.getListaservicio().remove(servicio);
                listapaquetePaquete = em.merge(listapaquetePaquete);
            }
            List<Venta> venta = servicio.getVenta();
            for (Venta ventaVenta : venta) {
                ventaVenta.setSer(null);
                ventaVenta = em.merge(ventaVenta);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
