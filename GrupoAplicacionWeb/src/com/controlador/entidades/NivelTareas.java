package com.controlador.entidades;

public class NivelTareas {
	private int id_tipotarea;
	private String descripcion;
	private String estado;
	public int getId_tipotarea() {
		return id_tipotarea;
	}
	public void setId_tipotarea(int id_tipotarea) {
		this.id_tipotarea = id_tipotarea;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public NivelTareas(int id_tipotarea, String descripcion, String estado) {
		super();
		this.id_tipotarea = id_tipotarea;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	public NivelTareas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
