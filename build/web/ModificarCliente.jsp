<%-- 
    Document   : ClienteEditar
    Created on : 20-dic-2021, 18:00:36
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Cliente</title>
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
          
       <form action="SvClienteEdit" method="GET">
         <% HttpSession misession = request.getSession();
                    Cliente cli = (Cliente) misession.getAttribute("cliente");
             %>
              <input type='hidden' required name='id_cliente' value='<%=cli.getId_cliente()%>'>
           <p><label>Nombre:</label><input type="text" name="nombre" value="<%=cli.getNombre()%>"></p>
        <p><label>Apellido:</label><input type="text" name="apellido" value="<%=cli.getApellido()%>"></p>
        <p><label>Direccion:</label><input type="text" name="direccion" value="<%=cli.getDireccion()%>"></p>
        <p><label>DNI:</label><input type="text" name="dni" value="<%=cli.getDni()%>"></p>
        <% String pattern = "dd-MM-yyyy";
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String fecha_nac = simpleDateFormat.format(cli.getFecha_nacimiento());
            %>
         <p><label>Fecha Nacimiento formato dd/mm/aaaa:</label><input type="text" name="fecha_nacimiento" value="<%=fecha_nac%>"></p>
         <p><label>Nacionalidad:</label><input type="text" name="nacionalidad" value="<%=cli.getNacionalidad()%>"></p>
         <p><label>Celular:</label><input type="text" name="celular" value="<%=cli.getCelular()%>"></p>
         <p><label>EMAIL:</label><input type="text" name="email" value="<%=cli.getEmail()%>"></p>
     
         <button type="submit"> Enviar</button>
        
 </form>
          <% }%> 
    </body>
</html>