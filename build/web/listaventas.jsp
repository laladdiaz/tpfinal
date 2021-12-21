<%-- 
    Document   : listaventas
    Created on : 20-dic-2021, 16:31:24
    Author     : Carla
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Venta"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de ventas</title>
    </head>
    <body>
         <div class="container">
       <table>
		<thead>
			<tr>
				<th>ID VENTA</th>
				<th>Medio de pago</th>
				<th>Fecha de venta</th>
				<th>Cliente</th>
				<th>Servicio</th>
                                <th>Paquete</th>
                                <th>Usuario</th>
                                <th>EDITAR</th>
                                <th>ELIMINAR</th>
                                
			</tr>
		</thead>
		<tbody>
                    <% Controladora control = new Controladora();
                    List<Venta> listaventa = control.traerventa();
                    for(Venta vta : listaventa){
                    %>     
                    <tr>
                       <% int id_venta = vta.getNum_venta();
                          int id_servicio = vta.getSer().getCod_servicio();
                          int id_paquete = vta.getPaque().getCodigo_paquete();
                          int id_usuario = vta.getUsu().getId();
                          int id_cliente = vta.getCli().getId_cliente();
                          String pattern = "dd-MM-yyyy";
                          SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                          String fecha_venta = simpleDateFormat.format(vta.getFecha_venta());
                          String mp = vta.getMedio_pago();
                        %>
                        <td><%=id_venta%></td>
                        <td><%=mp%></td>
                        <td><%=fecha_venta%></td>
                         <td><%=id_cliente%></td>
                        <td><%=id_servicio%></td>
                        <td><%=id_paquete%></td>
                        <td><%=id_usuario%></td>
                        
                        <td>                
          <form name="editar" action="SvPaqueteEdit" method="POST" style="display:inline" >  
          <input type="hidden" name ="id" value="<%=id_venta%>">
          <button type="submit" class="btn-editar" data-title="Edit" style="display:inline">Editar</button>
          </form>
          </td>
         
           <td>                
            <form name="borrar" action="SvPaqueteE" method="POST" style="display:inline" >  
            <input type="hidden" name ="id" value="<%=id_venta%>">
            <button type="submit" class="btn-borrar" data-title="Delete" style="display:inline">Borrar</button>
            </form>
             </td>
      </tr>
        <% }%>	
		</tbody>
	</table>
</div>
                 </body>
</html>