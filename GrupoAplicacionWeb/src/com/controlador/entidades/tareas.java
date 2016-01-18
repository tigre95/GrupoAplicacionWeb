package com.controlador.entidades;

public class tareas {

	private int id_tarea;
	private int id_tipotarea;
	private int id_persona;
	private int id_persotarea;
	private String descripcion;
	private String archivo;
	private String comentario;
	private String fecha_inicio;
	private String fecha_fin;
	private String estado;
	private int estado_tarea;
	
	public int getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}

	public int getId_tipotarea() {
		return id_tipotarea;
	}

	public void setId_tipotarea(int id_tipotarea) {
		this.id_tipotarea = id_tipotarea;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public int getId_persotarea() {
		return id_persotarea;
	}

	public void setId_persotarea(int id_persotarea) {
		this.id_persotarea = id_persotarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getEstado_tarea() {
		return estado_tarea;
	}

	public void setEstado_tarea(int estado_tarea) {
		this.estado_tarea = estado_tarea;
	}

	public tareas(int id_tarea, int id_tipotarea, int id_persona, int id_persotarea, String descripcion, String archivo,
			String comentario, String fecha_inicio, String fecha_fin, String estado, int estado_tarea) {
		super();
		this.id_tarea = id_tarea;
		this.id_tipotarea = id_tipotarea;
		this.id_persona = id_persona;
		this.id_persotarea = id_persotarea;
		this.descripcion = descripcion;
		this.archivo = archivo;
		this.comentario = comentario;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.estado = estado;
		this.estado_tarea = estado_tarea;
	}

	public tareas() {
		// TODO Auto-generated constructor stub
	}

}
