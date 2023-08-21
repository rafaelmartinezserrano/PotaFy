package com.afdm.potafy.model;

import java.time.LocalTime;

public class Cancion {

	private int idCancion;
	private String titulo;
	private LocalTime duracion;
	private String genero;
	private String urlFichero;
	private boolean publica;
	private Album album;
	private Usuario usuario;
	
	public Cancion(int idCancion, String titulo, LocalTime duracion, String genero, String urlFichero, boolean publica,
			Album album, Usuario usuario) {
		this.idCancion = idCancion;
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.urlFichero = urlFichero;
		this.publica = publica;
		this.album = album;
		this.usuario = usuario;
	}

	public Cancion(String titulo, LocalTime duracion, String genero, String urlFichero, boolean publica, Album album,
			Usuario usuario) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.urlFichero = urlFichero;
		this.publica = publica;
		this.album = album;
		this.usuario = usuario;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getUrlFichero() {
		return urlFichero;
	}

	public void setUrlFichero(String urlFichero) {
		this.urlFichero = urlFichero;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj != null && obj instanceof Cancion) {
			Cancion otro = (Cancion)obj;
			iguales = this.idCancion == otro.idCancion;
		}
		return iguales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cancion [idCancion=").append(idCancion).append(", titulo=").append(titulo).append(", duracion=")
				.append(duracion).append(", genero=").append(genero).append(", urlFichero=").append(urlFichero)
				.append(", publica=").append(publica).append(", album=").append(album).append(", usuario=")
				.append(usuario).append("]");
		return builder.toString();
	}
	
}
