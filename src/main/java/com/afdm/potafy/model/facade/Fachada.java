package com.afdm.potafy.model.facade;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.afdm.potafy.model.Cancion;
import com.afdm.potafy.model.PlayList;
import com.afdm.potafy.model.Usuario;
import com.afdm.potafy.model.dao.CancionJDBCDAO;
import com.afdm.potafy.model.dao.PlayListJDBCDAO;
import com.afdm.potafy.model.dao.UsuarioJDBCDAO;
import com.afdm.potafy.model.exception.ConexionException;
import com.afdm.potafy.model.exception.PlayListRepetidaException;

public class Fachada {
	
	public Usuario iniciarSesion(String nombreUsuario, String clave) throws ConexionException {
		UsuarioJDBCDAO dao = new UsuarioJDBCDAO();
		Usuario usuario;
		try {
			usuario = dao.buscarUsuarioPorNombreUsuario(nombreUsuario);
			if (usuario != null && usuario.getClave().equals(clave)) {
				return usuario;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConexionException("No hay conexión con los datos");
		}

	}
	
	public void subirCancion(Cancion cancion) throws ConexionException {
		CancionJDBCDAO dao = new CancionJDBCDAO();
		try {
			dao.insertarCancion(cancion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConexionException("No hay conexión con los datos");
		}
	}
	
	public List<Cancion> buscarCancionesPorTitulo(String busqueda, int idUsuario) throws ConexionException {
		CancionJDBCDAO dao = new CancionJDBCDAO();
		try {
			return dao.buscarCancionesPorTitulo(busqueda, idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConexionException("No hay conexión con los datos");
		}
	}
	
	public boolean borrarCancion(int idCancion, int idUsuario) throws ConexionException {
		CancionJDBCDAO dao = new CancionJDBCDAO();
		try {
			return dao.borrarCancion(idCancion, idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConexionException("No hay conexión con los datos");
		}
	}
	
	public PlayList crearPlayList(String nombre, Usuario usuario) throws ConexionException, PlayListRepetidaException {
		PlayListJDBCDAO dao = new PlayListJDBCDAO();
		try {
			return dao.insertarPlayList(nombre, usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (e.getErrorCode() == 1062) {
				throw new PlayListRepetidaException("La playList " + nombre + " ya existe. Usa tu imaginación un poquito más");
			} else {
				throw new ConexionException("No hay conexión con los datos");
			}
		}
	}

	public Cancion buscarCancionAleatoria(int idUsuario) throws ConexionException {
		CancionJDBCDAO dao = new CancionJDBCDAO();
		Cancion aleatoria = null;
		try {
			List<Cancion> canciones = dao.buscarCancionesPorTitulo("", idUsuario);
			if (!canciones.isEmpty()) {
				Collections.shuffle(canciones);
				aleatoria = canciones.get(0);
			}
			return aleatoria;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConexionException("No hay conexión con los datos");
		}
	}
}







