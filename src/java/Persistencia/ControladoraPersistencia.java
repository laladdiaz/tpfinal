
package Persistencia;

import Logica.Cliente;
import Logica.Empleado;
import Logica.Paquete;
import Logica.Servicio;
import Logica.Usuario;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;


public class ControladoraPersistencia {
    EmpleadoJpaController Ejpa = new EmpleadoJpaController();
    UsuarioJpaController Ujpa= new UsuarioJpaController();
    ServicioJpaController Sjpa= new ServicioJpaController();
    PaqueteJpaController Pjpa= new PaqueteJpaController();
    ClienteJpaController Cjpa = new ClienteJpaController ();
    VentaJpaController Vjpa = new VentaJpaController();
    
    public void crearemple(Empleado emple) {
        Ejpa.create(emple);
       
    }
    


public void crearuser(Usuario usu){
       Ujpa.create(usu);
}

 public List<Usuario> TraerUsuarios (){
           
      return Ujpa.findUsuarioEntities();
     }

public List<Empleado> TraerEmpleados(){
    return Ejpa.findEmpleadoEntities();
}

    public void Borrarempleado(int id_empleado, int id_usuario) throws NonexistentEntityException {
        Ejpa.destroy(id_empleado);
        Ujpa.destroy(id_usuario);
        
    }

    public void EditarUsuario(Usuario user) throws Exception {
        Ujpa.edit(user);
        
    }

    public Usuario BuscarUsuario(int id_usuario) {
        return Ujpa.findUsuario(id_usuario);
        
    }

    public void EditarEmpleado(Empleado emp) throws Exception {
        Ejpa.edit(emp);
        
    }

    public Empleado BuscarEmpleado(int id_empleado) {
        return Ejpa.findEmpleado(id_empleado);
        
    }

    public void CrearServicio(Servicio ser) {
        Sjpa.create(ser);
        
    }

    public List<Servicio> listarservicio() {
        return Sjpa.findServicioEntities();
    }

    public void crearPaquete(Paquete paq) {
        Pjpa.create(paq);
       }

    public Servicio buscarservicio(int parseInt) {
        return Sjpa.findServicio(parseInt);
        
    }

    public List<Paquete> listapaquete() {
        return Pjpa.findPaqueteEntities();
    }

    public void BorrarPaquete(int id) throws NonexistentEntityException {
        Pjpa.destroy(id);
      
    }

    public Paquete BuscarPaquete(int id) {
        return Pjpa.findPaquete(id);
                 
    }

    public void EditarPaquete(Paquete paq) throws Exception {
        Pjpa.edit(paq);
    }

    public void BorrarServicio(int id) throws NonexistentEntityException {
        Sjpa.destroy(id);
        
    }

    public Servicio BuscarServicio(int id) {
       return Sjpa.findServicio(id);
    }

    public void Editarservicio(Servicio ser) throws Exception {
        Sjpa.edit(ser);
    }

    public void crearcliente(Cliente cli) {
        Cjpa.create(cli);
    }

    public List<Cliente> traercliente() {
        return Cjpa.findClienteEntities();
    }

    public Cliente buscarcliente(int id_cliente) {
        return Cjpa.findCliente(id_cliente);
    }

    public void crearventaS(Venta vent) {
        Vjpa.create(vent);
    }

    public void crearventaP(Venta vent) {
        Vjpa.create(vent);
    }

    public List<Venta> traerventa() {
        return Vjpa.findVentaEntities();
        
    }

    public void EditarCliente(Cliente cli) throws Exception {
       Cjpa.edit(cli);
    }

    public void Borrarcliente(int id_cliente) throws NonexistentEntityException {
        Cjpa.destroy(id_cliente);
    }

    
 
}
