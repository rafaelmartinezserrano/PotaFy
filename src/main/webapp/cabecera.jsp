<%@page import="com.afdm.potafy.model.Usuario"%>
<header class="cabecera">
	<%Usuario usuario = (Usuario)session.getAttribute("usuario");%>
	<h1><img src="imagenes/notaMusical.jfif" alt="Nota musical"/>PotaFy</h1>
	<h2><%=usuario.getNombre() %> <a href="CerrarSesion">Salir</a></h2>
</header>