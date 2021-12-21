<%-- 
    Document   : SvVentaPaq
    Created on : 20-dic-2021, 15:17:13
    Author     : Carla
--%>

<%@page import="Logica.Controladora"%>
<%@page import="Logica.Paquete"%>
<%@page import="Logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Alta Venta - Paquete</title>
        <link rel="stylesheet" href="css/style.css" />
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
                     <li><a href="Paquete.jsp">Editar<span class="flecha">&#9660;</span></a></li>
                    <li><a href="listarpaquetes.jsp">Paquete<span class="flecha">&#9660;</span></a></li>
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
          
      
        <form action="SvVentaP" method="POST">
                      
            <p><label>Medio de pago:</label><input type="text" name="mpago"></p><br>
        <p><label>Fecha  formato dd/mm/aaaa:</label><input type="text" name="fecha"></p><br>
         <p><label>id_usuario:</label><input type="text" name="id_usuario"></p><br>
                        <label>Paquetes: </label>
            <select name ="paquete">
            <% Controladora control = new Controladora();
                List<Paquete> listapaquete = control.listapaquete();
            for (Paquete paq : listapaquete  ){
                int id_p =paq.getCodigo_paquete();
                double costo_p =paq.getCosto_paquete();
              
               %>
               <option value="<%=id_p%>"> Codigo: <%=id_p%> Costo: <%=costo_p%> </option> 
                
           <% }%>
            </select>
             <br><br>
                     
           <label>Cliente: </label>
            <select name ="cliente">
               <%
            List<Cliente> listacliente = control.traercliente();
            for (Cliente cli : listacliente ){
                String dni =cli.getDni();
                String nombre = cli.getNombre();
                String apellido = cli.getApellido();
                int id_c = cli.getId_cliente();
               %>
               <option value="<%=id_c%>"><%=nombre%><%=apellido%>Dni: <%=dni%>  </option> 
                
           <% }%>
            </select>
           <br><br>
            <button type="submit"> Enviar</button>
         <% }%>     
           
    </body>
</html>
