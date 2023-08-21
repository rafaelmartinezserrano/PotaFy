package com.afdm.potafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.afdm.potafy.model.Cancion;

public class CancionJDBCDAO {

	private final static String INSERTAR_CANCION = "insert into cancion(titulo, duracion, genero, urlFichero, publica, idAlbum, idUsuario) values (?, ?, ?, ?, ?, ?, ?)";
	private final static String BUSCA_POR_TITULO = "select * from cancion where (idUsuario = ? or publica = true) and titulo like ?";
	private final static String BORRAR_CANCION = "delete from cancion where idCancion = ? and idUsuario = ?";
	
	public void insertarCancion(Cancion cancion) throws SQLException {
		Connection conexion = DaoUtility.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_CANCION);
		sentencia.setString(1, cancion.getTitulo());
		if (cancion.getDuracion() == null) {
			sentencia.setNull(2, Types.TIME);
		} else {
			sentencia.setTime(2, Time.valueOf(cancion.getDuracion()));
		}
		sentencia.setString(3, cancion.getGenero());
		sentencia.setString(4, cancion.getUrlFichero());
		sentencia.setBoolean(5, cancion.isPublica());
		if (cancion.getAlbum() == null) {
			sentencia.setNull(6, Types.INTEGER);
		} else {
			sentencia.setInt(6, cancion.getAlbum().getIdAlbum());
		}
		sentencia.setInt(7, cancion.getUsuario().getIdUsuario());
		int numFilas = sentencia.executeUpdate();
		conexion.close();
	}
	
	public List<Cancion> buscarCancionesPorTitulo(String busqueda, int idUsuario) throws SQLException {
		List<Cancion> listaCanciones = new ArrayList<Cancion>();
		Connection conexion = DaoUtility.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCA_POR_TITULO);
		sentencia.setInt(1, idUsuario);
		sentencia.setString(2, "%" + busqueda + "%");
		ResultSet resultado = sentencia.executeQuery();
		while (resultado.next()) {
			int idCancion = resultado.getInt("idCancion");
			String titulo = resultado.getString("titulo");
			LocalTime duracion = null;
			if (resultado.getTime("duracion") != null) {
				duracion = resultado.getTime("duracion").toLocalTime();
			}
			String genero = resultado.getString("genero");
			String urlFichero = resultado.getString("urlFichero");
			boolean publica = resultado.getBoolean("publica");
			//TODO: SACAR EL ALBUM
			Cancion cancion = new Cancion(idCancion, titulo, duracion, genero, urlFichero, publica, null, null);
			listaCanciones.add(cancion);
		}
		conexion.close();
		return listaCanciones;
	}
	
	public boolean borrarCancion(int idCancion, int idUsuario) throws SQLException {
		Connection conexion = DaoUtility.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BORRAR_CANCION);
		sentencia.setInt(1, idCancion);
		sentencia.setInt(2, idUsuario);
		int numFilas = sentencia.executeUpdate();
		conexion.close();
		return numFilas == 1;
	}
}








