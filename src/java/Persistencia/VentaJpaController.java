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
import Logica.Cliente;
import Logica.Servicio;
import Logica.Paquete;
import Logica.Usuario;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carla
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VentaJpaController (){
        emf = Persistence.createEntityManagerFactory("Tpfinal_PU");
            }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cli = venta.getCli();
            if (cli != null) {
                cli = em.getReference(cli.getClass(), cli.getId_cliente());
                venta.setCli(cli);
            }
            Servicio ser = venta.getSer();
            if (ser != null) {
                ser = em.getReference(ser.getClass(), ser.getCod_servicio());
                venta.setSer(ser);
            }
            Paquete paque = venta.getPaque();
            if (paque != null) {
                paque = em.getReference(paque.getClass(), paque.getCodigo_paquete());
                venta.setPaque(paque);
            }
            Usuario usu = venta.getUsu();
            if (usu != null) {
                usu = em.getReference(usu.getClass(), usu.getId());
                venta.setUsu(usu);
            }
            em.persist(venta);
            if (cli != null) {
                cli.getListaventa().add(venta);
                cli = em.merge(cli);
            }
            if (ser != null) {
                ser.getVenta().add(venta);
                ser = em.merge(ser);
            }
            if (paque != null) {
                paque.getListaventa().add(venta);
                paque = em.merge(paque);
            }
            if (usu != null) {
                usu.getListaventa().add(venta);
                usu = em.merge(usu);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNum_venta());
            Cliente cliOld = persistentVenta.getCli();
            Cliente cliNew = venta.getCli();
            Servicio serOld = persistentVenta.getSer();
            Servicio serNew = venta.getSer();
            Paquete paqueOld = persistentVenta.getPaque();
            Paquete paqueNew = venta.getPaque();
            Usuario usuOld = persistentVenta.getUsu();
            Usuario usuNew = venta.getUsu();
            if (cliNew != null) {
                cliNew = em.getReference(cliNew.getClass(), cliNew.getId_cliente());
                venta.setCli(cliNew);
            }
            if (serNew != null) {
                serNew = em.getReference(serNew.getClass(), serNew.getCod_servicio());
                venta.setSer(serNew);
            }
            if (paqueNew != null) {
                paqueNew = em.getReference(paqueNew.getClass(), paqueNew.getCodigo_paquete());
                venta.setPaque(paqueNew);
            }
            if (usuNew != null) {
                usuNew = em.getReference(usuNew.getClass(), usuNew.getId());
                venta.setUsu(usuNew);
            }
            venta = em.merge(venta);
            if (cliOld != null && !cliOld.equals(cliNew)) {
                cliOld.getListaventa().remove(venta);
                cliOld = em.merge(cliOld);
            }
            if (cliNew != null && !cliNew.equals(cliOld)) {
                cliNew.getListaventa().add(venta);
                cliNew = em.merge(cliNew);
            }
            if (serOld != null && !serOld.equals(serNew)) {
                serOld.getVenta().remove(venta);
                serOld = em.merge(serOld);
            }
            if (serNew != null && !serNew.equals(serOld)) {
                serNew.getVenta().add(venta);
                serNew = em.merge(serNew);
            }
            if (paqueOld != null && !paqueOld.equals(paqueNew)) {
                paqueOld.getListaventa().remove(venta);
                paqueOld = em.merge(paqueOld);
            }
            if (paqueNew != null && !paqueNew.equals(paqueOld)) {
                paqueNew.getListaventa().add(venta);
                paqueNew = em.merge(paqueNew);
            }
            if (usuOld != null && !usuOld.equals(usuNew)) {
                usuOld.getListaventa().remove(venta);
                usuOld = em.merge(usuOld);
            }
            if (usuNew != null && !usuNew.equals(usuOld)) {
                usuNew.getListaventa().add(venta);
                usuNew = em.merge(usuNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getNum_venta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente cli = venta.getCli();
            if (cli != null) {
                cli.getListaventa().remove(venta);
                cli = em.merge(cli);
            }
            Servicio ser = venta.getSer();
            if (ser != null) {
                ser.getVenta().remove(venta);
                ser = em.merge(ser);
            }
            Paquete paque = venta.getPaque();
            if (paque != null) {
                paque.getListaventa().remove(venta);
                paque = em.merge(paque);
            }
            Usuario usu = venta.getUsu();
            if (usu != null) {
                usu.getListaventa().remove(venta);
                usu = em.merge(usu);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
