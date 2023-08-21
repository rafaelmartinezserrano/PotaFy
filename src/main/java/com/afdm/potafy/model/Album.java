package com.afdm.potafy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
	
	private int idAlbum;
	private String nombre;
	private String portada;
	private String interprete;
	private LocalDate fechaPublicacion;
	private List<Cancion> canciones;
	
	public Album(int idAlbum, String nombre, String portada, String interprete, LocalDate fechaPublicacion, List<Cancion> canciones) {
		this.idAlbum = idAlbum;
		this.nombre = nombre;
		this.portada = portada;
		this.interprete = interprete;
		this.fechaPublicacion = fechaPublicacion;
		this.canciones = canciones;
	}

	public Album(String nombre, String portada, String interprete, LocalDate fechaPublicacion, List<Cancion> canciones) {
		this.nombre = nombre;
		this.portada = portada;
		this.interprete = interprete;
		this.fechaPublicacion = fechaPublicacion;
		this.canciones = canciones;
	}
	
	public Album(String nombre, String portada, String interprete, LocalDate fechaPublicacion) {
		this.nombre = nombre;
		this.portada = portada;
		this.interprete = interprete;
		this.fechaPublicacion = fechaPublicacion;
		this.canciones = new ArrayList<>();
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj != null && obj instanceof Album) {
			Album otro = (Album)obj;
			iguales = this.idAlbum == otro.idAlbum;
		}
		return iguales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [idAlbum=").append(idAlbum).append(", nombre=").append(nombre).append(", portada=")
				.append(portada).append(", interprete=").append(interprete).append(", fechaPublicacion=")
				.append(fechaPublicacion).append(", canciones=").append(canciones).append("]");
		return builder.toString();
	}


}
