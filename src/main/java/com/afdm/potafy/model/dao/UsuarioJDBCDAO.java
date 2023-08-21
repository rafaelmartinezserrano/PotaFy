package com.afdm.potafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.afdm.potafy.model.Usuario;

public class UsuarioJDBCDAO {
	
	private final static String BUSCAR_POR_NOMBRE_USUARIO = "select * from usuario where nombreUsuario = ? and deBaja = false";

	public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) throws SQLException {
		Usuario usuario = null;
		//1º Obtener la conexión
		Connection conexion = DaoUtility.getConnection();
		//2º Obtener la sentencia
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_POR_NOMBRE_USUARIO);
		//2º A - Darle valor a los parámetros
		sentencia.setString(1, nombreUsuario);
		//3º Ejecutar la sentencia
		ResultSet resultado = sentencia.executeQuery();
		//4º Procesar el resultado
		if (resultado.next()) {
			int idUsuario = resultado.getInt("idUsuario");
			String nombre = resultado.getString("nombre");
			String apellidos = resultado.getString("apellidos");
			String clave = resultado.getString("clave");
			LocalDate fechaNacimiento = null;
			if (resultado.getDate("fechaNacimiento") != null) {
				fechaNacimiento = resultado.getDate("fechaNacimiento").toLocalDate();
			}
			String email = resultado.getString("email");
			boolean deBaja = resultado.getBoolean("deBaja");
			usuario = new Usuario(idUsuario, nombre, apellidos, nombreUsuario, clave, fechaNacimiento, email, deBaja);
		}
		conexion.close();
		return usuario;
	}
	
}
