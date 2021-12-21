/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Empleado;
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
@WebServlet(name = "SvClienteEdit", urlPatterns = {"/SvClienteEdit"})
public class SvClienteEdit extends HttpServlet {
    Controladora control= new Controladora();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          int id_cliente= Integer.parseInt(request.getParameter("id_cliente")); 
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
              
        Cliente cli = new Cliente ();
        cli.setApellido(apellido);
        cli.setNombre(nombre);
        cli.setDireccion(direccion);
        cli.setDni(dni);
        cli.setEmail(email);
        cli.setFecha_nacimiento(fecha_nacimiento);
        cli.setCelular(celular);
        cli.setNacionalidad(nacionalidad);
        cli.setId_cliente(id_cliente);
        try {
            control.EditarCliente(cli);
        } catch (Exception ex) {
            Logger.getLogger(SvClienteEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("listacliente",control.traercliente());
        response.sendRedirect("listarcliente.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_cliente= Integer.parseInt(request.getParameter("id_cliente")); 
        Cliente cli = control.buscarcliente(id_cliente);
        HttpSession misession = request.getSession();
        misession.setAttribute("cliente", cli);
        response.sendRedirect("ModificarCliente.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// 

}
