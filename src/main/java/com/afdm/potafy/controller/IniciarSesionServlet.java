package com.afdm.potafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.afdm.potafy.model.Cancion;
import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.facade.Fachada;

/**
 * Servlet implementation class IniciarSesionServlet
 */
public class IniciarSesionServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7258295933608874400L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = request.getParameter("nombreUsuario");
		String clave = request.getParameter("clave");
		Fachada fachada = new Fachada();
		try {
			Usuario usuario = fachada.iniciarSesion(nombreUsuario, clave);
			if (usuario != null) {
				request.getSession().setAttribute("usuario", usuario);
				Cancion aleatoria = fachada.buscarCancionAleatoria(usuario.getIdUsuario());
				request.getSession().setAttribute("aleatoria", aleatoria);
				request.getRequestDispatcher("principal.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Datos no encontrados. Si es nuevo, regístrese");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}





