<%-- 
    Document   : ModificarServicio
    Created on : 19-dic-2021, 18:58:18
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Modificar Servicio</title>
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
          
    
        <form action="SvServicioEdit" method="GET">
      <% HttpSession misession = request.getSession();
                   Servicio ser = (Servicio) misession.getAttribute("servicio");
                  
             %>
           
           <input type='hidden' required name='id' value='<%=ser.getCod_servicio()%>'>
           <p><label>Nombre:</label><input type="text" name="nombre" value="<%=ser.getNombre()%>"></p>
           <p><label>Descripcion breve:</label><input type="text" name="descripcion" value="<%=ser.getDescripcion_breve()%>"></p>
           <p><label>Destino Servicio:</label><input type="text" name="destino" value ="<%=ser.getDestino_servicio()%>"></p>
           <% String pattern = "dd-MM-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String fecha = simpleDateFormat.format(ser.getFecha_servicio());
            %>
       
           <p><label>Fecha Servicio:</label><input type="text" name="fecha_servicio" value="<%=fecha%>"></p>
           <p><label>Costo Servicio:</label><input type="text" name="costo_servicio" value="<%=ser.getCosto_servicio()%>"></p>
            <button type="submit">Modificar</button>
        </form>
       <% }%>         
    </body>
</html>
