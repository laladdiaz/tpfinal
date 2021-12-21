<%-- 
    Document   : CienteA
    Created on : 20-dic-2021, 12:11:39
    Author     : Carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<title>Alta Cliente</title>
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

    
       <form action="SvClienteA" method="POST">
         
           <p><label>Nombre:</label><input type="text" name="nombre"></p><br>
        <p><label>Apellido:</label><input type="text" name="apellido"></p><br>
        <p><label>Direccion:</label><input type="text" name="direccion"></p><br>
        <p><label>DNI:</label><input type="text" name="dni"></p><br>
         <p><label>Fecha Nacimiento formato dd/mm/aaaa:</label><input type="text" name="fecha_nacimiento"></p><br>
         <p><label>Nacionalidad:</label><input type="text" name="nacionalidad"></p><br>
         <p><label>Celular:</label><input type="text" name="celular"></p><br>
         <p><label>EMAIL:</label><input type="text" name="email"></p><br>
                 
         <button type="submit"> Enviar</button>
        
 </form>
        <%}%> 
    </body>
</html>
