package com.afdm.potafy.model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	
	private int idPlayList;
	private String nombre;
	private Usuario usuario;
	private List<Cancion> listaCanciones;
	
	public PlayList(int idPlayList, String nombre, Usuario usuario, List<Cancion> listaCanciones) {
		this.idPlayList = idPlayList;
		this.nombre = nombre;
		this.usuario = usuario;
		this.listaCanciones = listaCanciones;
	}

	public PlayList(String nombre, Usuario usuario, List<Cancion> listaCanciones) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.listaCanciones = listaCanciones;
	}

	public PlayList(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.listaCanciones = new ArrayList<>();
	}

	public int getIdPlayList() {
		return idPlayList;
	}

	public void setIdPlayList(int idPlayList) {
		this.idPlayList = idPlayList;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(List<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj != null && obj instanceof PlayList) {
			PlayList otro = (PlayList)obj;
			iguales = this.idPlayList == otro.idPlayList;
		}
		return iguales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayList [idPlayList=").append(idPlayList).append(", nombre=").append(nombre)
				.append(", usuario=").append(usuario).append(", listaCanciones=").append(listaCanciones).append("]");
		return builder.toString();
	}
	
	public void insertarCancion(Cancion cancion) {
		this.listaCanciones.add(cancion);
	}
	
	public void quitarCancion(Cancion cancion) {
		this.listaCanciones.remove(cancion);
	}
}
