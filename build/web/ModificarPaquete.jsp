<%-- 
    Document   : ModificarPaquete
    Created on : 19-dic-2021, 17:31:20
    Author     : Carla
--%>

<%@page import="Logica.Paquete"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<title>Alta Paquete Turistico</title>
	
</head>
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
        <form action="SvPaqueteEdit" method="GET">
             <% HttpSession misession = request.getSession();
                    Paquete paq = (Paquete) misession.getAttribute("paquete");
             %>
            
            
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
            
            
              <%}%>
           <button type="submit" class="btn-editar" data-title="Crear_Paquete" style>Crear </button>
         
          </td>
              </form>

              
     
 
</tbody>
</table>