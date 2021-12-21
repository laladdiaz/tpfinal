/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvBorrar", urlPatterns = {"/SvBorrar"})
public class SvBorrar extends HttpServlet {
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
            int id_empleado= Integer.parseInt(request.getParameter("id_empleado")); 
            int id_usuario= Integer.parseInt(request.getParameter("id_usuario"));
            try{
                control.Borrarempleado(id_empleado,id_usuario);
            }catch(NonexistentEntityException ex){
               Logger.getLogger(SvBorrar.class.getName()).log(Level.SEVERE, null, ex); 
             }
                request.getSession().setAttribute("listaempleados", control.Traerempleados());
               response.sendRedirect("listaempleado.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
