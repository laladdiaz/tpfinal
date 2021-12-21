
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
@WebServlet(name = "SvPaqueteE", urlPatterns = {"/SvPaqueteE"})
public class SvPaqueteE extends HttpServlet {
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
        processRequest(request, response);
        
        int id = Integer.parseInt(request.getParameter("id"));
    try {
        control.BorrarPaquete(id);
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(SvPaqueteE.class.getName()).log(Level.SEVERE, null, ex);
    }
        request.getSession().setAttribute("listapaquete", control.listapaquete());
        response.sendRedirect("listarpaquetes.jsp");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
