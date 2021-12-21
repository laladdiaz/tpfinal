/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controladora;
import java.io.IOException;
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
@WebServlet(name = "SvValidation", urlPatterns = {"/SvValidation"})
public class SvValidation extends HttpServlet {
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
        String usuario= request.getParameter("usuario");
        String password = request.getParameter("password");
        boolean validar = control.validarusuario(usuario,password);
        
        if(validar == true){
            HttpSession misession = request.getSession(true);
            misession.setAttribute("usuario", usuario);
            misession.setAttribute("password", password);
            response.sendRedirect ("inicio.jsp");
       
    }else{
            response.sendRedirect ("index.jsp");
        }
  }      

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
