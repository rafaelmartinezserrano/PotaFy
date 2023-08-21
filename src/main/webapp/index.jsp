<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link type="image/x-icon" rel="icon" href="imagenes/favicon.png"/>
		<link type="text/css" rel="stylesheet" href="estilos/potafy.css"/>
		<title>Potafy</title>
	</head>
	<body>
		<header class="cabeceraIndex">
			<h1><img src="imagenes/notaMusical.jfif" alt="Nota musical"/>PotaFy<img src="imagenes/notaMusical.jfif" alt="Nota musical"/></h1>
		</header>		
		<main class="contenedorIndex">
			<% String error = (String)request.getAttribute("error"); %>
			<form action="IniciarSesion" method="post">
				<div>
					<label for="idNombreUsuario">Nombre de usuario:</label>
					<input type="text" name="nombreUsuario" id="idNombreUsuario"/>
				</div>
				<div>
					<label for="idClave">Clave:</label>
					<input type="password" name="clave" id="idClave"/>
				</div>
				<div>
					<input type="submit" value="Iniciar Sesión"/>
				</div>
				<% if (error != null) { %>
					<div class="error">
						<%=error %>
					</div>
				<% } %>
			</form>
		</main>
	</body>
</html>