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
@WebServlet(name = "SvClienteE", urlPatterns = {"/SvClienteE"})
public class SvClienteE extends HttpServlet {
    Controladora control = new Controladora ();

    
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
         int id_cliente= Integer.parseInt(request.getParameter("id_cliente"));
        try {
            control.borrarcliente(id_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvClienteE.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("listacliente", control.traercliente());
        response.sendRedirect("listarcliente.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }//

}
