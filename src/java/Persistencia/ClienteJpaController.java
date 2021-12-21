/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Cliente;
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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
     public ClienteJpaController (){
        emf = Persistence.createEntityManagerFactory("Tpfinal_PU");
            }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaventa() == null) {
            cliente.setListaventa(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedListaventa = new ArrayList<Venta>();
            for (Venta listaventaVentaToAttach : cliente.getListaventa()) {
                listaventaVentaToAttach = em.getReference(listaventaVentaToAttach.getClass(), listaventaVentaToAttach.getNum_venta());
                attachedListaventa.add(listaventaVentaToAttach);
            }
            cliente.setListaventa(attachedListaventa);
            em.persist(cliente);
            for (Venta listaventaVenta : cliente.getListaventa()) {
                Cliente oldCliOfListaventaVenta = listaventaVenta.getCli();
                listaventaVenta.setCli(cliente);
                listaventaVenta = em.merge(listaventaVenta);
                if (oldCliOfListaventaVenta != null) {
                    oldCliOfListaventaVenta.getListaventa().remove(listaventaVenta);
                    oldCliOfListaventaVenta = em.merge(oldCliOfListaventaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId_cliente());
            List<Venta> listaventaOld = persistentCliente.getListaventa();
            List<Venta> listaventaNew = cliente.getListaventa();
            List<Venta> attachedListaventaNew = new ArrayList<Venta>();
            for (Venta listaventaNewVentaToAttach : listaventaNew) {
                listaventaNewVentaToAttach = em.getReference(listaventaNewVentaToAttach.getClass(), listaventaNewVentaToAttach.getNum_venta());
                attachedListaventaNew.add(listaventaNewVentaToAttach);
            }
            listaventaNew = attachedListaventaNew;
            cliente.setListaventa(listaventaNew);
            cliente = em.merge(cliente);
            for (Venta listaventaOldVenta : listaventaOld) {
                if (!listaventaNew.contains(listaventaOldVenta)) {
                    listaventaOldVenta.setCli(null);
                    listaventaOldVenta = em.merge(listaventaOldVenta);
                }
            }
            for (Venta listaventaNewVenta : listaventaNew) {
                if (!listaventaOld.contains(listaventaNewVenta)) {
                    Cliente oldCliOfListaventaNewVenta = listaventaNewVenta.getCli();
                    listaventaNewVenta.setCli(cliente);
                    listaventaNewVenta = em.merge(listaventaNewVenta);
                    if (oldCliOfListaventaNewVenta != null && !oldCliOfListaventaNewVenta.equals(cliente)) {
                        oldCliOfListaventaNewVenta.getListaventa().remove(listaventaNewVenta);
                        oldCliOfListaventaNewVenta = em.merge(oldCliOfListaventaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId_cliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId_cliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Venta> listaventa = cliente.getListaventa();
            for (Venta listaventaVenta : listaventa) {
                listaventaVenta.setCli(null);
                listaventaVenta = em.merge(listaventaVenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
