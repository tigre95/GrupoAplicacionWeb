package com.controlador.entidades;

public class datosusuarios {
	private int id_datousuario;
	private int id_usuario;
	private String usuario;
	private String contrasena;
	private String estado;
	

	public datosusuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public datosusuarios(int id_datousuario, int id_usuario, String usuario, String contrasena, String estado) {
		super();
		this.id_datousuario = id_datousuario;
		this.id_usuario = id_usuario;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.estado = estado;
	}

	public int getId_datousuario() {
		return id_datousuario;
	}

	public void setId_datousuario(int id_datousuario) {
		this.id_datousuario = id_datousuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
