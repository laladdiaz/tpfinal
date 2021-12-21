<%-- 
    Document   : index
    Created on : 12-dic-2021, 22:41:25
    Author     : Carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="css/style2.css" />
</head>


<form action="SvValidation" class="signin-form" method="POST">
  
  <p><label>Usuario:</label><input type="text" name="usuario"></p>
  <p><label>Contraseña:</label><input type="password" name="password"></p>
  <button type="submit"> Enviar</button>
</form>
</html>