package com.afdm.potafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.facade.Fachada;

/**
 * Servlet implementation class BorrarCancionServlet
 */
public class BorrarCancionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String txtCancion = request.getParameter("cancion");
		String fichero = request.getParameter("fichero");
		//System.out.println("URLFICHERO: " + fichero);
		int idCancion = Integer.parseInt(txtCancion);
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		Fachada fachada = new Fachada();
		try {
			boolean borrada = fachada.borrarCancion(idCancion, usuario.getIdUsuario());
			if (borrada) {
				String ruta = request.getServletContext().getRealPath("") + "Canciones" + File.separator + fichero;
				System.out.println(ruta);
				File ficheroCancion = new File(ruta);
				boolean deleted = ficheroCancion.delete();
				//System.out.println("DELETED: " + deleted);
			}
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter salida = response.getWriter();
			salida.write(Boolean.toString(borrada));
			salida.close();
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




