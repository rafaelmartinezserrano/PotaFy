package com.afdm.potafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.afdm.potafy.model.PlayList;
import com.afdm.potafy.model.Usuario;

public class PlayListJDBCDAO {

	private final static String INSERTAR_PLAYLIST = "insert into playlist (nombre, idUsuario) values (?, ?)";
	
	public PlayList insertarPlayList(String nombre, Usuario usuario) throws SQLException {
		PlayList playList = null;
		Connection conexion = DaoUtility.getConnection();
		//Preparo la sentencia, avisando que voy a recuperar la clave autogenerada
		PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_PLAYLIST, Statement.RETURN_GENERATED_KEYS);
		sentencia.setString(1, nombre);
		sentencia.setInt(2, usuario.getIdUsuario());
		sentencia.executeUpdate();
		ResultSet claves = sentencia.getGeneratedKeys();
		if (claves.next()) {
			int idPlayList = claves.getInt(1);
			playList = new PlayList(idPlayList, nombre, usuario, null);
		}
		conexion.close();
		return playList;
	}
	
}
