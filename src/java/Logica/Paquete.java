
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Paquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo_paquete;
    @Basic
    private double costo_paquete;
    @OneToMany
    private List <Servicio> listaservicio;
    
    @ManyToMany
    private List <Venta> listaventa;
    
       public Paquete() {
    }

    public Paquete(int codigo_paquete, double costo_paquete, List<Servicio> listaservicio, List<Venta> listaventa) {
        this.codigo_paquete = codigo_paquete;
        this.costo_paquete = costo_paquete;
        this.listaservicio = listaservicio;
        this.listaventa = listaventa;
    }

    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    public List<Servicio> getListaservicio() {
        return listaservicio;
    }

    public void setListaservicio(List<Servicio> listaservicio) {
        this.listaservicio = listaservicio;
    }

    public List<Venta> getListaventa() {
        return listaventa;
    }

    public void setListaventa(List<Venta> listaventa) {
        this.listaventa = listaventa;
    }

       
       
       
}

