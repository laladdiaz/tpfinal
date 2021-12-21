<%-- 
    Document   : Paquete
    Created on : 17-dic-2021, 23:20:11
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<head>
	<title>Alta Paquetes</title>
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
<table>
    <thead>
        <tr> 
    <th>Nombre del servicio</th>
    <th>Descripcion breve</th>
    <th>Destino del servicio</th> 
    <th>Fecha de servicio</th>
    <th>Costo de servicio</th>
    <th>Añadir servicio</th>
    
    </tr> 
    </thead>
    
    <tbody>
        <%Controladora control = new Controladora();
        List<Servicio> listarServicio= control.listarservicio();%>
        <form action="SvPaqueteA" method="POST">
            <%for (Servicio ser : listarServicio){%>
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
                <td><%=nombre%></td>
                <td><%=descripcion%></td>
                <td><%=destino_servicio%></td>
                <td><%=fecha_servicio%></td>
                <td><%=costo_servicio%></td>
                
                 <td>
                     <label>
                         <input type ="checkbox" name ="aservicio" value="<%=id%>">
                     </label>
                   </td>
              </tr>
             
              <td>                
            
            <input type="hidden" name ="costo_paquete" value="<%=costo_servicio%>">
              <%}%>
           <button type="submit" class="btn-editar" data-title="Crear_Paquete" style>Crear </button>
         
          </td>
              </form>

              
     
 <%}%>
</tbody>
</table>
      
    
    
   
    


