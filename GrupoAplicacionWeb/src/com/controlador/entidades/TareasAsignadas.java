package com.controlador.entidades;

public class TareasAsignadas {
	private String descripcion;
	public TareasAsignadas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TareasAsignadas(String descripcion, String fecha_inicio, String fecha_fin, String nombres, String apellidos,
			String cedula, String nivel, String descripcion_re, String fecha_terminada) {
		super();
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.nivel = nivel;
		this.descripcion_re = descripcion_re;
		this.fecha_terminada = fecha_terminada;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getDescripcion_re() {
		return descripcion_re;
	}
	public void setDescripcion_re(String descripcion_re) {
		this.descripcion_re = descripcion_re;
	}
	public String getFecha_terminada() {
		return fecha_terminada;
	}
	public void setFecha_terminada(String fecha_terminada) {
		this.fecha_terminada = fecha_terminada;
	}
	private String fecha_inicio;
	private String fecha_fin;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String nivel;
	private String descripcion_re;
	private String fecha_terminada;
	
}
