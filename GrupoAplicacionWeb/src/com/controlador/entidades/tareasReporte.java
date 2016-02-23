package com.controlador.entidades;

public class tareasReporte {
		
	private String descripcion_jefe;
	private String fecha_inicio;
	private String fecha_fin;
	private int cantidad;
	private String nivel;
	private String nombre;
	private String apellidos;
	private String cedula;
	private String descripcion_empleado;
	private String fecha_terminada;
	private String estado;
	
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion_jefe() {
		return descripcion_jefe;
	}
	public void setDescripcion_jefe(String descripcion_jefe) {
		this.descripcion_jefe = descripcion_jefe;
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getDescripcion_empleado() {
		return descripcion_empleado;
	}
	public void setDescripcion_empleado(String descripcion_empleado) {
		this.descripcion_empleado = descripcion_empleado;
	}
	public String getFecha_terminada() {
		return fecha_terminada;
	}
	public void setFecha_terminada(String fecha_terminada) {
		this.fecha_terminada = fecha_terminada;
	}


	public tareasReporte(String descripcion_jefe, String fecha_inicio, String fecha_fin, int cantidad, String nivel,
			String nombre, String apellidos, String cedula, String descripcion_empleado, String fecha_terminada,
			String estado) {
		super();
		this.descripcion_jefe = descripcion_jefe;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.cantidad = cantidad;
		this.nivel = nivel;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.descripcion_empleado = descripcion_empleado;
		this.fecha_terminada = fecha_terminada;
		this.estado = estado;
	}
	public tareasReporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
