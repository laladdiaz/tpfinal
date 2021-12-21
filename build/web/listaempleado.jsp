<%-- 
    Document   : pruebaver
    Created on : 16-dic-2021, 20:38:18
    Author     : Carla
--%>

<%@page import="Logica.Empleado"%>
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
         <br><br><br>
         <br><br><br>
         <br><br><br>
         <br><br><br>
	<table>
		<thead>
			<tr>
				<th>ID EMPLEADO</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
				<th>DNI</th>
				<th>CELULAR</th>
                                <th>Cargo</th>
                                <th>direccion</th>
                                <th>ID USUARIO</th>
                                <th>email</th>
                                <th>EDITAR</th>
                                <th>ELIMINAR</th>
                                
			</tr>
		</thead>
		<tbody>
                   <% Controladora control = new Controladora();
                    List<Empleado> listaempleados = control.Traerempleados();
                    for(Empleado emp : listaempleados){
                    %>
        <tr>
               <% int id_empleado = emp.getId_empleado();
               String nombre =emp.getNombre();
               String apellido= emp.getApellido();
               String dni = emp.getDni();
               String cargo= emp.getCargo();
               String celular = emp.getCelular();
               String direccion =emp.getDireccion();
               int id_usuario= emp.getUsu().getId();
               String email = emp.getEmail();
         %>
                            
                            
                            
	 <td><%=id_empleado%> </td>
        <td><%=nombre%></td>
        <td><%=apellido%></td>
    	<td><%=dni%></td>
         <td><%=celular%></td>
         <td><%=cargo%></td>
         <td><%=direccion%></td>
         <td><%=id_usuario%></td>
         <td><%=email%></td>
         
         
         <td>                
          <form name="editar" action="SvEditar" method="POST" style="display:inline" >  
          <input type="hidden" name ="id_empleado" value="<%=id_empleado%>">
          <input type="hidden" name ="id_usuario" value="<%=id_usuario%>">
         <button type="submit" class="btn-editar" data-title="Edit" style="display:inline">Editar</button>
          </form>
          </td>
         
           <td>                
            <form name="borrar" action="SvBorrar" method="POST" style="display:inline" >  
            <input type="hidden" name ="id_empleado" value="<%=id_empleado%>">
            <input type="hidden" name ="id_usuario" value="<%=id_usuario%>">
            <button type="submit" class="btn-borrar" data-title="Delete" style="display:inline">Borrar</button>
            </form>
             </td>
          
         
         
         
	</tr>
        <%}%>	
		</tbody>
	</table>
</div>
                  <%}%> 
                 </body>
</html>
   

