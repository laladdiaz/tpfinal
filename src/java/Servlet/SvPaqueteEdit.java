
package Servlet;

import Logica.Controladora;
import Logica.Paquete;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvPaqueteEdit", urlPatterns = {"/SvPaqueteEdit"})
public class SvPaqueteEdit extends HttpServlet {
Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String [] listarServicio  =request.getParameterValues("id");
       Paquete paq = new Paquete();
        
    try {
        control.EditarPaquete(listarServicio);
    } catch (Exception ex) {
        Logger.getLogger(SvPaqueteEdit.class.getName()).log(Level.SEVERE, null, ex);
    }
        request.getSession().setAttribute("listapaquete", control.listapaquete());
        response.sendRedirect("listarpaquetes.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Paquete paq = control.BuscarPaquete(id);
        HttpSession misession = request.getSession();
        misession.setAttribute("paquete", paq);
        response.sendRedirect("ModificarPaquete.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
