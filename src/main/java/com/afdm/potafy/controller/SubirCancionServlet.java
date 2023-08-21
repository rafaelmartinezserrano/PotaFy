package com.afdm.potafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.afdm.potafy.model.Cancion;
import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.facade.Fachada;

/**
 * Servlet implementation class SubirCancionServlet
 */
public class SubirCancionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rutaAplicacion = request.getServletContext().getRealPath("");
		String rutaCanciones = rutaAplicacion + "Canciones";
		/*File carpetaCanciones = new File(rutaCanciones);
		if (carpetaCanciones.exists() == false) {
			carpetaCanciones.mkdir();
		}*/
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		String rutaUsuario = rutaCanciones + File.separator + usuario.getIdUsuario() + File.separator;
		File carpetaUsuario = new File(rutaUsuario);
		if (carpetaUsuario.exists() == false) {
			carpetaUsuario.mkdirs();
		}
		String titulo = request.getParameter("titulo");
		String genero = request.getParameter("genero");
		String txtPublica = request.getParameter("publica");
		boolean publica = txtPublica != null;
		/*boolean publica = false;
		if (txtPublica != null) {
			publica = true;
		} else {
			publica = false;
		}*/
		//********* GUARDAR DATOS DEL FICHERO *************
		Part fichero = request.getPart("ficheroCancion");
		String nombreFichero = extraerNombreFichero(fichero);
		System.out.println(nombreFichero);
		String rutaFichero = rutaUsuario + nombreFichero;
		fichero.write(rutaFichero);
		//******** INSERTAR EN LA BASE DE DATOS
		String urlGuardar = usuario.getIdUsuario() + "/" + nombreFichero;
		Cancion cancion = new Cancion(titulo, null, genero, urlGuardar, publica, null, usuario);
		Fachada fachada = new Fachada();
		try {
			fachada.subirCancion(cancion);
			request.setAttribute("mensaje", "Canción " + titulo + " subida correctamente!!");
			request.getRequestDispatcher("principal.jsp").forward(request, response);
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String extraerNombreFichero(Part part) {
    	String nombreFichero = "";
        String contenido = part.getHeader("content-disposition");
        System.out.println("cabecera del contenido= "+ contenido);
        String[] tokens = contenido.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                nombreFichero = token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return nombreFichero;
    }
	
}
