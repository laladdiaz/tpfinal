/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
import Logica.Usuario;
import java.io.IOException;
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

/**
 *
 * @author Carla
 */
@WebServlet(name = "SvEmpleadoA", urlPatterns = {"/SvEmpleadoA"})
public class SvEmpleadoA extends HttpServlet {
Controladora control = new Controladora();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
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
        
        
        control.crearusuario(usuario,contras);
        Usuario user = new Usuario();
        try {
            user = control.UltimoUsuario();
                   } catch (Exception ex) {
            Logger.getLogger(SvEmpleadoA.class.getName()).log(Level.SEVERE, null, ex);
        }                
        try {
            control.crearempleado(user,nombre,apellido,direccion,dni,fecha_nacimiento,nacionalidad,celular,email,cargo,sueldo);
        } catch (Exception ex) {
            Logger.getLogger(SvEmpleadoA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               response.sendRedirect("inicio.jsp");
       
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
