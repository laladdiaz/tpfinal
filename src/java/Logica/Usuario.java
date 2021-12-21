
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String usua;
    private String pass;
    @OneToMany
    private List <Venta> listaventa;

    public Usuario() {
    }

    public Usuario(int id, String usua, String pass, List<Venta> listaventa) {
        this.id = id;
        this.usua = usua;
        this.pass = pass;
        this.listaventa = listaventa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsua() {
        return usua;
    }

    public void setUsua(String usua) {
        this.usua = usua;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Venta> getListaventa() {
        return listaventa;
    }

    public void setListaventa(List<Venta> listaventa) {
        this.listaventa = listaventa;
    }


   
   
}
