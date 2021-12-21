
package Logica;

import Persistencia.ControladoraPersistencia;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;



public class Controladora {
    
    ControladoraPersistencia controlp= new ControladoraPersistencia();
    List<Usuario> ListUsuarios = new LinkedList();

    public void crearempleado(Usuario user,String nombre, String apellido, String direccion, String dni,Date fecha_nacimiento, String nacionalidad, String celular, String email, String cargo, Double sueldo) {
        Empleado emple = new Empleado();
        emple.setUsu(user);
        emple.setNombre(nombre);
         emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setFecha_nacimiento(fecha_nacimiento);
        emple.setNacionalidad(nacionalidad);
        emple.setCelular(celular);
        emple.setEmail(email);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        
        
        controlp.crearemple(emple);
                
        
        
        
    }

    public void crearusuario(String usuario, String contras) {
        Usuario usu = new Usuario();
        usu.setUsua(usuario);
        usu.setPass(contras);
        controlp.crearuser(usu);
    }

    public Usuario UltimoUsuario() {
       
            ListUsuarios = TraerUsuarios();
            Usuario usuario= new Usuario ();
     for(Usuario unUsuario:ListUsuarios){
              usuario = unUsuario;
     }
    return usuario;
    }
    
     public List <Usuario> TraerUsuarios(){
       
       return controlp.TraerUsuarios(); 
       
   }

    public boolean validarusuario(String usuario, String password) {
         List<Usuario> listaUsuarios = TraerUsuarios();
        if (listaUsuarios != null){
            for (Usuario user : listaUsuarios){
                if (user.getPass().equals(password) && user.getUsua().equals(usuario)){
                    return true;
                }
            }
        }
                   return false;
   }
    
     public List <Empleado> Traerempleados(){
       
       return controlp.TraerEmpleados(); 
       
   }
     
      public List <Cliente> traercliente(){
       
       return controlp.traercliente(); 
       
   }
     
     
     
     

    public void Borrarempleado(int id_empleado, int id_usuario) throws NonexistentEntityException {
        controlp.Borrarempleado(id_empleado,id_usuario);
        
        
    }

    public void EditarUsuario(Usuario user) throws Exception {
         controlp.EditarUsuario(user);
        
    }

    public Usuario BuscarUsuario(int id_usuario) {
        Usuario user = controlp.BuscarUsuario(id_usuario);
       
       return user;
    }

    public void EditarEmpleado(Empleado emp) throws Exception {
        controlp.EditarEmpleado(emp);
        
    }

    public Empleado BuscarEmpleado(int id_empleado) {
         Empleado emp = controlp.BuscarEmpleado(id_empleado);
         return emp;
        
            }

    public void crearservicio(String nombre, String descripcion, String destino, Date fecha_servicio, Double costo) {
        Servicio ser = new Servicio();
        ser.setNombre(nombre);
        ser.setDescripcion_breve(descripcion);
        ser.setDestino_servicio(destino);
        ser.setFecha_servicio(fecha_servicio);
        ser.setCosto_servicio(costo);
        
     //  
        controlp.CrearServicio(ser);
        
    }
    public List <Servicio> listarservicio(){
       
       return controlp.listarservicio(); 
       
   }

    public void crearpaquete(String[] listarServicio) {
        Servicio ser = new Servicio();
        double costopaquete = 0;
        
        Paquete paq = new Paquete();
        
        List<Servicio> lista = new ArrayList<>();
        for(String list : listarServicio){
            ser = buscarservicio(Integer.parseInt(list));
            costopaquete+= ser.getCosto_servicio();
            lista.add(ser);
            
        }
            costopaquete= costopaquete -(costopaquete*0.10);
            if(lista.size()>=2){
            paq.setCosto_paquete(costopaquete);
            paq.setListaservicio(lista);
            controlp.crearPaquete(paq);
            }
 
    }

    private Servicio buscarservicio(int parseInt) {
        Servicio serv = controlp.buscarservicio(parseInt);
       
       return serv;
        
    }
public List <Paquete> listapaquete(){
       
       return controlp.listapaquete(); 
       
   }

    public void BorrarPaquete(int id) throws NonexistentEntityException {
        controlp.BorrarPaquete(id);
    }

    public Paquete BuscarPaquete(int id) {
        Paquete paq = controlp.BuscarPaquete(id);
         return paq;
    }

    public void EditarPaquete(String[] listarServicio) throws Exception {
         Servicio ser = new Servicio();
        double costopaquete = 0;
        Paquete paq = new Paquete();
        
        List<Servicio> lista = new ArrayList<>();
        for(String list : listarServicio){
            ser = buscarservicio(Integer.parseInt(list));
            costopaquete+= ser.getCosto_servicio();
            lista.add(ser);
            
        }
            costopaquete= costopaquete -(costopaquete*0.10);
            if(lista.size()>=2){
            paq.setCosto_paquete(costopaquete);
            paq.setListaservicio(lista);
            controlp.EditarPaquete(paq);
            }
    }

    public void BorrarServicio(int id) throws NonexistentEntityException {
        controlp.BorrarServicio(id);
    }

    

    public Servicio BuscarServicio(int id) {
        Servicio ser = controlp.BuscarServicio(id);
         return ser;
    }

    public void Editarservicio(Servicio ser) throws Exception {
        controlp.Editarservicio(ser);
    }

    public void crearcliente(String nombre, String apellido, String direccion, String dni, Date fecha_nacimiento, String nacionalidad, String celular, String email) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDireccion(direccion);
        cli.setDni(dni);
        cli.setFecha_nacimiento(fecha_nacimiento);
        cli.setNacionalidad(nacionalidad);
        cli.setCelular(celular);
        cli.setEmail(email);
        controlp.crearcliente(cli);
        
    }

    public Cliente buscarcliente(int id_cliente) {
       Cliente cli = controlp.buscarcliente(id_cliente);
       
       return cli;
    }


    public void crearventaservicio(String mpago, Date fecha_p, Usuario usu, Cliente cli, Servicio ser) {
    Venta vent= new Venta();
    vent.setMedio_pago(mpago);
    vent.setFecha_venta(fecha_p);
    vent.setCli(cli);
    
    vent.setUsu(usu);
    vent.setSer(ser);
   
       List<Venta> listacliente = cli.getListaventa();
       listacliente.add(vent);
       cli.setListaventa(listacliente);
       
       List<Venta> listausuario = usu.getListaventa();
       listausuario.add(vent);
       usu.setListaventa(listausuario);
       
 //      List<Venta> listapaquete = paq.getListaventa();
 //      listapaquete.add(vent);
 //      paq.setListaventa(listapaquete);
       
       List<Venta> listaservi = ser.getVenta();
       listaservi.add(vent);
       ser.setVenta(listaservi);
       
       controlp.crearventaS(vent);
    
    
    
    
    }

    public void crearventapaquete(String mpago, Date fecha_p, Usuario usu, Cliente cli, Paquete paq) {
        Venta vent= new Venta();
    vent.setMedio_pago(mpago);
    vent.setFecha_venta(fecha_p);
    vent.setCli(cli);
    vent.setUsu(usu);
    vent.setPaque(paq);
   
   
       List<Venta> listacliente = cli.getListaventa();
       listacliente.add(vent);
       cli.setListaventa(listacliente);
       
       List<Venta> listausuario = usu.getListaventa();
       listausuario.add(vent);
       usu.setListaventa(listausuario);
       
       List<Venta> listapaquete = paq.getListaventa();
      listapaquete.add(vent);
      paq.setListaventa(listapaquete);
       
      
       
       controlp.crearventaP(vent);
     }
    
   public List <Venta> traerventa(){
       
       return controlp.traerventa(); 
       
   }  

    public void EditarCliente(Cliente cli) throws Exception {
       controlp.EditarCliente(cli);
    }

    public void borrarcliente(int id_cliente) throws NonexistentEntityException {
        controlp.Borrarcliente(id_cliente);
    }

  }
    
    
        
