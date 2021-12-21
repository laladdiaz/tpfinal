<%-- 
    Document   : listarservicio
    Created on : 19-dic-2021, 18:12:15
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style3.css" />
        <link rel="stylesheet" href="css/style.css" />
        <title>alta</title>
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
 
   
     <div class="container">
	<table>
		<thead>
			<tr>
				<th>COD SERVICIO</th>
				<th>NOMBRE</th>
				<th>DESCRIPCION</th>
				<th>DESTINO</th>
				<th>COSTO</th>
                                <th>FECHA</th>
                                <th>EDITAR</th>
                                <th>ELIMINAR</th>
                                
			</tr>
		</thead>
		<tbody>
                   <% Controladora control = new Controladora();
                    List<Servicio> listaservicio = control.listarservicio();
                    for(Servicio ser : listaservicio){
                    %>
        <tr>
               <%
                    String nombre = ser.getNombre();
                    String descripcion = ser.getDescripcion_breve();
                    String destino_servicio = ser.getDestino_servicio();
                    String pattern = "dd-MM-yyyy";
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String fecha_servicio = simpleDateFormat.format(ser.getFecha_servicio());
                    double costo_servicio = ser.getCosto_servicio();
                    int id = ser.getCod_servicio();
                  %>
                  <td><%=id%></td>
                 <td><%=nombre%></td>
                <td><%=descripcion%></td>
                <td><%=destino_servicio%></td>
                <td><%=costo_servicio%></td>
                <td><%=fecha_servicio%></td>
                
         
         <td>  
            
          <form name="editar" action="SvServicioEdit" method="POST" style="display:inline" >  
          <input type="hidden" name ="id" value="<%=id%>">
         <button type="submit" class="btn-editar" data-title="Edit" style="display:inline">Editar</button>
          </form>
          </td>
         
           <td>                
            <form name="borrar" action="SvServicioE" method="POST" style="display:inline" >  
            <input type="hidden" name ="id" value="<%=id%>">
            <button type="submit" class="btn-borrar" data-title="Delete" style="display:inline">Borrar</button>
            </form>
             </td>
    
	</tr>
        <%}%>	
		</tbody>
	</table>
</div> <%}%>	
                 </body>
</html>
   

