<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="image/x-icon" rel="icon" href="imagenes/favicon.png"/>
		<link type="text/css" rel="stylesheet" href="estilos/potafy.css"/>
		<link type="text/css" rel="stylesheet" href="estilos/menu.css"/>
		<link type="text/css" rel="stylesheet" href="estilos/cabecera.css"/>
		<title>PotaFy</title>
	</head>
	</head>
	<body>
		<%@ include file="cabecera.jsp" %>
		<%@ include file="menu.jsp" %>
		<%String mensaje = (String)request.getAttribute("mensaje"); %>
		<form action="SubirCancion" method="post" enctype="multipart/form-data" class="contenedorFormulario">
			<div>
				<label for="idTitulo">Título:</label>
				<input type="text" name="titulo" id="idTitulo" required="required"/>
			</div>
			<div>
				<label for="idGenero">Género:</label>
				<input type="text" name="genero" id="idGenero"/>
			</div>
			<div>
				<input type="checkbox" name="publica" id="idPublica"/>
				<label for="idPublica">Quiero compartir la canción</label>
			</div>
			<div>
				<label for="idFicheroCancion">Selecciona un fichero:</label>
				<input type="file" name="ficheroCancion" id="idFicheroCancion" accept=".mp3, audio/mpeg3" required="required"/>
			</div>
			<div>
				<input type="submit" value="Subir Canción" class="boton"/>
			</div>
			<% if (mensaje != null) { %>
				<div><%=mensaje %></div>
			<% } %>
		</form>
	</body>
</html>