package com.controlador.entidades;

public class tarearealizada {

	private int id_Tarea_realizada;
	private int id_tarea;
	private String descipcion;
	private String fecha_fin;
	private String archivo_env;
	private String estado;
	
	public int getId_Tarea_realizada() {
		return id_Tarea_realizada;
	}

	public void setId_Tarea_realizada(int id_Tarea_realizada) {
		this.id_Tarea_realizada = id_Tarea_realizada;
	}

	public int getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getArchivo_env() {
		return archivo_env;
	}

	public void setArchivo_env(String archivo_env) {
		this.archivo_env = archivo_env;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public tarearealizada(int id_Tarea_realizada, int id_tarea, String descipcion, String fecha_fin, String archivo_env,
			String estado) {
		super();
		this.id_Tarea_realizada = id_Tarea_realizada;
		this.id_tarea = id_tarea;
		this.descipcion = descipcion;
		this.fecha_fin = fecha_fin;
		this.archivo_env = archivo_env;
		this.estado = estado;
	}

	public tarearealizada() {
		// TODO Auto-generated constructor stub
	}

}
