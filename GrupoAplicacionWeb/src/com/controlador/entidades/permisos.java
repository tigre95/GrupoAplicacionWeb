package com.controlador.entidades;

public class permisos {

	private int id_permisos;
	private int id_tipo_usuario;
	private String descripcion;
	private int crear;
	private int buscar;
	private int editar;
	private int eliminar;
	private String estado;
	
	public permisos(int id_permisos, int id_tipo_usuario, String descripcion, int crear, int buscar, int editar,
			int eliminar, String estado) {
		super();
		this.id_permisos = id_permisos;
		this.id_tipo_usuario = id_tipo_usuario;
		this.descripcion = descripcion;
		this.crear = crear;
		this.buscar = buscar;
		this.editar = editar;
		this.eliminar = eliminar;
		this.estado = estado;
	}


	public int getId_permisos() {
		return id_permisos;
	}


	public void setId_permisos(int id_permisos) {
		this.id_permisos = id_permisos;
	}


	public int getId_tipo_usuario() {
		return id_tipo_usuario;
	}


	public void setId_tipo_usuario(int id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getCrear() {
		return crear;
	}


	public void setCrear(int crear) {
		this.crear = crear;
	}


	public int getBuscar() {
		return buscar;
	}


	public void setBuscar(int buscar) {
		this.buscar = buscar;
	}


	public int getEditar() {
		return editar;
	}


	public void setEditar(int editar) {
		this.editar = editar;
	}


	public int getEliminar() {
		return eliminar;
	}


	public void setEliminar(int eliminar) {
		this.eliminar = eliminar;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public permisos() {
		// TODO Auto-generated constructor stub
	}

}
