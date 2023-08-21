<%@page import="com.afdm.potafy.model.Cancion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link type="image/x-icon" rel="icon" href="imagenes/favicon.png"/>
		<link type="text/css" rel="stylesheet" href="estilos/potafy.css"/>
		<link type="text/css" rel="stylesheet" href="estilos/menu.css"/>
		<link type="text/css" rel="stylesheet" href="estilos/cabecera.css"/>
		<title>PotaFy</title>
	</head>
	<body>
		<%@ include file="cabecera.jsp" %>
		<%@ include file="menu.jsp" %>
		<% Cancion aleatoria = (Cancion)session.getAttribute("aleatoria"); %>
		<div class="sugerencia">
			<h2>Sugerencia del dia</h2>
		<% if (aleatoria != null) { %>
			<div class="contenedorCanciones">
				<div class="cancion">
					<span class="tituloCancion"><%=aleatoria.getTitulo() %></span>
					<audio controls="controls" controlsList="nodownload">
						<source src="<%="Canciones/"+aleatoria.getUrlFichero()%>" type="audio/mpeg"/>
					</audio>
				</div>
			</div>
		<% } else { %>
			<h3>Aún no has subido ninguna canción. Sube tus canciones favoritas y disfrútalas!!!</h3>
		<% } %>
		</div>
	</body>
</html>