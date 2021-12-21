<%-- 
    Document   : listarcliente
    Created on : 20-dic-2021, 18:00:53
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Cliente</title>
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
                    <li><a href="listarpaquetes.jsp">Lista Paquete<span class="flecha">&#9660;</span></a></li>
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
    <body>
    
   <table>
		<thead>
			<tr>
				<th>ID Cliente</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
				<th>DNI</th>
				<th>CELULAR</th>
                                <th>direccion</th>
                                <th>email</th>
                                 <th>Fecha Nacimiento</th>
                                <th>EDITAR</th>
                                <th>ELIMINAR</th>
                                
			</tr>
		</thead>
		<tbody>
                   <% Controladora control = new Controladora();
                    List<Cliente> listacliente = control.traercliente();
                    for(Cliente cli : listacliente){
                    %>
        <tr>
               <% int id_cliente = cli.getId_cliente();
               String nombre =cli.getNombre();
               String apellido= cli.getApellido();
               String dni = cli.getDni();
              String celular = cli.getCelular();
               String direccion =cli.getDireccion();
               String email = cli.getEmail();
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                 String fecha_n = simpleDateFormat.format(cli.getFecha_nacimiento());
         %>
                            
                            
                            
	 <td><%=id_cliente%> </td>
        <td><%=nombre%></td>
        <td><%=apellido%></td>
    	<td><%=dni%></td>
         <td><%=celular%></td>
         <td><%=direccion%></td>
         <td><%=email%></td>
         <td><%=fecha_n%></td>
         
         
         <td>                
          <form name="editar" action="SvClienteEdit" method="POST" style="display:inline" >  
          <input type="hidden" name ="id_cliente" value="<%=id_cliente%>">
          <button type="submit" class="btn-editar" data-title="Edit" style="display:inline">Editar</button>
          </form>
          </td>
         
           <td>                
            <form name="borrar" action="SvClienteE" method="POST" style="display:inline" >  
            <input type="hidden" name ="id_cliente" value="<%=id_cliente%>">
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