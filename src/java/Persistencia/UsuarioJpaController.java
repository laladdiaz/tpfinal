/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Usuario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carla
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public UsuarioJpaController (){
        emf = Persistence.createEntityManagerFactory("Tpfinal_PU");
            }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getListaventa() == null) {
            usuario.setListaventa(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedListaventa = new ArrayList<Venta>();
            for (Venta listaventaVentaToAttach : usuario.getListaventa()) {
                listaventaVentaToAttach = em.getReference(listaventaVentaToAttach.getClass(), listaventaVentaToAttach.getNum_venta());
                attachedListaventa.add(listaventaVentaToAttach);
            }
            usuario.setListaventa(attachedListaventa);
            em.persist(usuario);
            for (Venta listaventaVenta : usuario.getListaventa()) {
                Usuario oldUsuOfListaventaVenta = listaventaVenta.getUsu();
                listaventaVenta.setUsu(usuario);
                listaventaVenta = em.merge(listaventaVenta);
                if (oldUsuOfListaventaVenta != null) {
                    oldUsuOfListaventaVenta.getListaventa().remove(listaventaVenta);
                    oldUsuOfListaventaVenta = em.merge(oldUsuOfListaventaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Venta> listaventaOld = persistentUsuario.getListaventa();
            List<Venta> listaventaNew = usuario.getListaventa();
            List<Venta> attachedListaventaNew = new ArrayList<Venta>();
       /*     for (Venta listaventaNewVentaToAttach : listaventaNew) {
                listaventaNewVentaToAttach = em.getReference(listaventaNewVentaToAttach.getClass(), listaventaNewVentaToAttach.getNum_venta());
                attachedListaventaNew.add(listaventaNewVentaToAttach);
            }*/
            listaventaNew = attachedListaventaNew;
            usuario.setListaventa(listaventaNew);
            usuario = em.merge(usuario);
            for (Venta listaventaOldVenta : listaventaOld) {
                if (!listaventaNew.contains(listaventaOldVenta)) {
                    listaventaOldVenta.setUsu(null);
                    listaventaOldVenta = em.merge(listaventaOldVenta);
                }
            }
            for (Venta listaventaNewVenta : listaventaNew) {
                if (!listaventaOld.contains(listaventaNewVenta)) {
                    Usuario oldUsuOfListaventaNewVenta = listaventaNewVenta.getUsu();
                    listaventaNewVenta.setUsu(usuario);
                    listaventaNewVenta = em.merge(listaventaNewVenta);
                    if (oldUsuOfListaventaNewVenta != null && !oldUsuOfListaventaNewVenta.equals(usuario)) {
                        oldUsuOfListaventaNewVenta.getListaventa().remove(listaventaNewVenta);
                        oldUsuOfListaventaNewVenta = em.merge(oldUsuOfListaventaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Venta> listaventa = usuario.getListaventa();
            for (Venta listaventaVenta : listaventa) {
                listaventaVenta.setUsu(null);
                listaventaVenta = em.merge(listaventaVenta);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
