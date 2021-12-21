/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carla
 */
@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
public class SvEditar extends HttpServlet {
Controladora control = new Controladora ();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id_usuario = Integer.parseInt(request.getParameter ("id_usuario"));
        int id_empleado = Integer.parseInt(request.getParameter ("id_empleado"));
        String nombre= request.getParameter("nombre");
        String apellido= request.getParameter("apellido");
        String direccion= request.getParameter("direccion");
        String dni= request.getParameter("dni");
        String fecha = request.getParameter("fecha_nacimiento");
        Date fecha_nacimiento = new Date();
        try {  
           fecha_nacimiento=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(SvValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        Double sueldo= Double.parseDouble(request.getParameter("sueldo"));
        String usuario = request.getParameter("usuario");
        String contras = request.getParameter("contra");
        Empleado emp = new Empleado ();
        Usuario user = new Usuario();
        emp.setApellido(apellido);
        emp.setNombre(nombre);
        emp.setDni(dni);
        emp.setCargo(cargo);
        emp.setCelular(celular);
        emp.setSueldo(sueldo);
        emp.setDireccion(direccion);
        emp.setNacionalidad(nacionalidad);
        emp.setFecha_nacimiento(fecha_nacimiento);
        emp.setEmail(email);
        emp.setId_empleado(id_empleado);
        user.setId(id_usuario);
        user.setUsua(usuario);
        user.setPass(contras);
         try {
            control.EditarUsuario(user);
        } catch (Exception ex) {
            Logger.getLogger(SvEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
         user = control.BuscarUsuario(id_usuario);
         emp.setUsu(user);
         
         try {
            control.EditarEmpleado(emp);
        } catch (Exception ex) {
            Logger.getLogger(SvEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         request.getSession().setAttribute("listaempleados", control.Traerempleados());
         response.sendRedirect("listaempleado.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id_empleado= Integer.parseInt(request.getParameter("id_empleado")); 
        Empleado emp = control.BuscarEmpleado(id_empleado);
        HttpSession misession = request.getSession();
        misession.setAttribute("empleado", emp);
        response.sendRedirect("ModificarEmpleado.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
