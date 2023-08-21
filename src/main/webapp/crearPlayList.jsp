<%@page import="com.afdm.potafy.model.PlayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	<body>
		<%@ include file="cabecera.jsp" %>
		<%@ include file="menu.jsp" %>
		<main>
			<form action="CrearPlayList" method="post" class="contenedorFormulario">
				<div>
					<label for="txtNombre">Nombre de la PlayList:</label>
					<input type="text" id="txtNombre" name="nombrePlayList" required="required"/>
				</div>
				<div>
					<input type="submit" value="Crear PlayList" class="boton"/>
				</div>
			</form>
			<% String mensaje = (String)request.getAttribute("mensaje"); %>
			<% if (mensaje != null) {%>
				<div>
					<%=mensaje %>
				</div>
			<% } %>
			
			<% List<PlayList> lista = (List<PlayList>)session.getAttribute("playLists");%>
			<% if (lista != null && !lista.isEmpty()) { %>
				<% for (PlayList pl : lista) { %>
					<div>
						<%=pl.getNombre() %>
						<input type="button" value="Reproducir"/>
						<input type="button" value="Eliminar"/>
					</div>
				<% } %>
			<% } else { %>
				<div>Todavía no hay PlayList</div>
			<% } %>
		</main>
	</body>
</html>