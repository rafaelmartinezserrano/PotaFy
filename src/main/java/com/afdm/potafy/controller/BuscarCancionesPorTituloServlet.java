package com.afdm.potafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.afdm.potafy.model.Cancion;
import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.facade.Fachada;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BuscarCancionesPorTituloServlet
 */
public class BuscarCancionesPorTituloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String busqueda = request.getParameter("busqueda");
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		Fachada fachada = new Fachada();
		try {
			List<Cancion> listaCanciones = fachada.buscarCancionesPorTitulo(busqueda, usuario.getIdUsuario());
			//Utilizo la librería JACKSON para convertir Java a JSON
			ObjectMapper mapeador = new ObjectMapper();
			String json = mapeador.writeValueAsString(listaCanciones);
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter salida = response.getWriter();
			salida.write(json);
			salida.close();
			
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}






