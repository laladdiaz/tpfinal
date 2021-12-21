<%-- 
    Document   : listarpaquetes
    Created on : 19-dic-2021, 16:31:08
    Author     : Carla
--%>
<%@page import="java.util.Date"%>
<%@page import="Logica.Servicio"%>
<%@page import="Logica.Paquete"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style3.css" />
        <link rel="stylesheet" href="css/style.css" />
        <title>Listado Paquetes</title>
    </head>
 <body>
    <% HttpSession missesion = request.getSession();
        String usu = (String) missesion.getAttribute("usuario");
        if(usu== null){
            response.sendRedirect("index.jsp");
        }
            else{

        %>

<ul class="nav">
	<li>
                <a href="inicio.jsp">Bienvenido<span class="flecha">&#9660;</span></a>
            </li>
            <li>
                <a href="#">Empleado<span class="flecha">&#9660;</span></a>
                <ul>
                    <li><a href="empleado.jsp">Crear nuevo Empleado<span class="flecha">&#9660;</span></a></li>
                    <li><a href="listaempleado.jsp">Listar Empleado<span class="flecha">&#9660;</span></a></li>
                </ul>
            </li>
            <li>
                <a href="#">Cliente<span class="flecha">&#9660;</span></a>
                <ul>
                    <li><a href="ClienteA.jsp">Cliente<span class="flecha">&#9660;</span></a></li>
                    <li><a href="listarcliente.jsp">listado cliente<span class="flecha">&#9660;</span></a></li>
                </ul>
            </li>
            
            <li>
                <a href="#">Servicios<span class="flecha">&#9660;</span></a>
                <ul>
                     <li><a href="Servicio.jsp">Alta Servicio<span class="flecha">&#9660;</span></a></li>
                    <li><a href="listarservicio.jsp">Listado Servicio<span class="flecha">&#9660;</span></a></li>
                </ul>
            </li>
            
            <li>
                <a href="#">Paquetes<span class="flecha">&#9660;</span></a>
                <ul>
                     <li><a href="Paquete.jsp">Alta Paquete<span class="flecha">&#9660;</span></a></li>
                    <li><a href="listarpaquetes.jsp">Listar Paquete<span class="flecha">&#9660;</span></a></li>
                </ul>
            </li>
            
           <li>
                <a href="#">Venta<span class="flecha">&#9660;</span></a>
                <ul>
                      <li><a href="VentaPaqA.jsp">Venta Paquete<span class="flecha">&#9660;</span></a></li>
                    <li><a href="VentaA.jsp">Venta Servicio<span class="flecha">&#9660;</span></a></li>
                     <li><a href="listaventas.jsp">listado venta<span class="flecha">&#9660;</span></a></li>
                </ul>
            </li>
            <br><br><br>
        
    </head>
    
     <div class="container">
	<table>
		<thead>
			<tr>
				<th>Id</th>
                             
				<th>COSTO PAQUETE</th>
                                <th>EDITAR</th>
                                <th>ELIMINAR</th>
                           </tr>
		</thead>
		<tbody>
                   <% Controladora control = new Controladora();
                    List<Paquete> listapaquete = control.listapaquete();
                    for(Paquete paq : listapaquete){%>
                    
        <tr>
               <% int id = paq.getCodigo_paquete();
               double costo = paq.getCosto_paquete();
               %>
                <br>
                       
	 <td><%=id%> </td>
        <td><%=costo%></td>
   
         <td>                
          <form name="editar" action="SvPaqueteEdit" method="POST" style="display:inline" >  
          <input type="hidden" name ="id" value="<%=id%>">
          <button type="submit" class="btn-editar" data-title="Edit" style="display:inline">Editar</button>
          </form>
          </td>
         
           <td>                
            <form name="borrar" action="SvPaqueteE" method="POST" style="display:inline" >  
            <input type="hidden" name ="id" value="<%=id%>">
            <button type="submit" class="btn-borrar" data-title="Delete" style="display:inline">Borrar</button>
            </form>
             </td>
       
	</tr>
        <% }%>	
		</tbody>
	</table>
</div>  <% }%>
                 </body>
</html>