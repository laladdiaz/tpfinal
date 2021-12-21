/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
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
@WebServlet(name = "SvServicioA", urlPatterns = {"/SvServicioA"})
public class SvServicioA extends HttpServlet {
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
        String descripcion= request.getParameter("descripcion");
        String destino= request.getParameter("destino");
                
        String fecha = request.getParameter("fecha_servicio");
        Date fecha_servicio = new Date();
        try {  
           fecha_servicio=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(SvValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double costo =Double.parseDouble(request.getParameter("costo_servicio"));
         control.crearservicio(nombre,descripcion,destino,fecha_servicio,costo);
        response.sendRedirect("inicio.jsp");
        
        
        
    }
       

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
