
package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int cod_servicio;
    @Basic
    private String nombre;
    private String descripcion_breve;
    private String destino_servicio;
    @Temporal(TemporalType.DATE)
    private Date fecha_servicio;
    @Basic
    private double costo_servicio;
    @ManyToMany
    private List <Paquete> listapaquete;
    @OneToMany
    private List <Venta> venta;
   

    public Servicio() {
    }

    public Servicio(int cod_servicio, String nombre, String descripcion_breve, String destino_servicio, Date fecha_servicio, double costo_servicio, List<Paquete> listapaquete, List<Venta> venta) {
        this.cod_servicio = cod_servicio;
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.destino_servicio = destino_servicio;
        this.fecha_servicio = fecha_servicio;
        this.costo_servicio = costo_servicio;
        this.listapaquete = listapaquete;
        this.venta = venta;
    }

    public int getCod_servicio() {
        return cod_servicio;
    }

    public void setCod_servicio(int cod_servicio) {
        this.cod_servicio = cod_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion_breve() {
        return descripcion_breve;
    }

    public void setDescripcion_breve(String descripcion_breve) {
        this.descripcion_breve = descripcion_breve;
    }

    public String getDestino_servicio() {
        return destino_servicio;
    }

    public void setDestino_servicio(String destino_servicio) {
        this.destino_servicio = destino_servicio;
    }

    public Date getFecha_servicio() {
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }

    public List<Paquete> getListapaquete() {
        return listapaquete;
    }

    public void setListapaquete(List<Paquete> listapaquete) {
        this.listapaquete = listapaquete;
    }

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

    
    
}
