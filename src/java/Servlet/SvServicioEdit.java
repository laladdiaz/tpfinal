/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
import Logica.Servicio;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carla
 */
@WebServlet(name = "SvServicioEdit", urlPatterns = {"/SvServicioEdit"})
public class SvServicioEdit extends HttpServlet {
Controladora control = new Controladora();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        String nombre= request.getParameter("nombre");
        String descripcion= request.getParameter("descripcion");
        String destino= request.getParameter("destino");
                
        String fecha = request.getParameter("fecha_servicio");
        Date fecha_servicio = new Date();
        try {  
           fecha_servicio=new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(SvValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double costo =Double.parseDouble(request.getParameter("costo_servicio"));
        String nombrev = request.getParameter("nombreviejo");
        
        Servicio ser = new Servicio();
        ser.setCod_servicio(id);
        ser.setNombre(nombre);
        ser.setDescripcion_breve(descripcion);
        ser.setDestino_servicio(destino);
        ser.setCosto_servicio(costo);
        ser.setFecha_servicio(fecha_servicio);
       
        
    try {
        control.Editarservicio(ser);
    } catch (Exception ex) {
        Logger.getLogger(SvServicioEdit.class.getName()).log(Level.SEVERE, null, ex);
    }
        request.getSession().setAttribute("listaservicio", control.listarservicio());
         response.sendRedirect("listarservicio.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id")); 
        Servicio ser = control.BuscarServicio(id);
        HttpSession misession = request.getSession();
        misession.setAttribute("servicio", ser);
        response.sendRedirect("ModificarServicio.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
