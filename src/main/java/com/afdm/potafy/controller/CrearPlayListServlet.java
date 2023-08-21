package com.afdm.potafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.afdm.potafy.model.PlayList;
import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.exception.PlayListRepetidaException;
import com.afdm.potafy.model.facade.Fachada;

/**
 * Servlet implementation class CrearPlayListServlet
 */
public class CrearPlayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombrePlayList = request.getParameter("nombrePlayList");
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		
		Fachada fachada = new Fachada();
		try {
			PlayList playList = fachada.crearPlayList(nombrePlayList, usuario);
			//Sacamos la lista de PlayList de la sessión
			List<PlayList> lista = (List<PlayList>)request.getSession().getAttribute("playLists");
			if (lista == null) {
				lista = new ArrayList<PlayList>();
			}
			//Se añade la nueva PlayList a la lista
			lista.add(playList);
			//Se actualiza la lista que estaba guardada en la sesión
			request.getSession().setAttribute("playLists", lista);
			
			request.setAttribute("mensaje", "PlayList " + nombrePlayList + " creada correctamente");
			request.getRequestDispatcher("crearPlayList.jsp").forward(request, response);
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlayListRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("crearPlayList.jsp").forward(request, response);
		}
	}

}






