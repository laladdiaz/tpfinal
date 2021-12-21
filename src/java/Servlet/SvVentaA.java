/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Paquete;
import Logica.Servicio;
import Logica.Usuario;
import Logica.Venta;
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

/**
 *
 * @author Carla
 */
@WebServlet(name = "SvVentaA", urlPatterns = {"/SvVentaA"})
public class SvVentaA extends HttpServlet {
Controladora control = new Controladora();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_servicio= Integer.parseInt(request.getParameter("servicio"));
       int id_cliente = Integer.parseInt(request.getParameter("cliente"));
        int id_usu =Integer.parseInt(request.getParameter("id_usuario"));
        String mpago= request.getParameter("mpago");
        int valor=0;
        String fecha = request.getParameter("fecha");
        Date fecha_p = new Date();
        try {  
           fecha_p=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(SvValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    Usuario usu = control.BuscarUsuario(id_usu);
    Cliente cli = control.buscarcliente(id_cliente);
    Servicio ser = control.BuscarServicio(id_servicio);
   
    
   
    
    control.crearventaservicio(mpago,fecha_p,usu,cli,ser);
    response.sendRedirect("inicio.jsp");
 }   

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
