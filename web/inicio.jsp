<%-- 
    Document   : inicio
    Created on : 15-dic-2021, 20:34:20
    Author     : Carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<title>Menu desplegable usando solo CSS</title>
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
  <%}%>      
</body>
</html>