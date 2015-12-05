package com.controlador.entidades;

public class personas {

	private int id_persona;
	private String nombres;
	private int id_departamento;
	private String apellidos;
	private String cedula;
	private String direccion;
	private String email;
	private String estado;

	
	public personas(int id_persona, String nombres, int id_departamento, String apellidos, String cedula,
			String direccion, String email, String estado) {
		super();
		this.id_persona = id_persona;
		this.nombres = nombres;
		this.id_departamento = id_departamento;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
	}



	public int getId_persona() {
		return id_persona;
	}



	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public int getId_departamento() {
		return id_departamento;
	}



	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
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



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	public personas() {
		// TODO Auto-generated constructor stub
	}

}
