package com.controlador.entidades;

public class Usuariodb {
	private int id_persona,id_tipousuario,idTipoDepartamento;
	private String nombres,apellidos,cedula,email,direccion,telefono;
	private String descripcionTU,descripciondep,alias,dpasssword;
	private departamentodb departamentodb ;
	public int getId_persona() {
		return id_persona;
	}
	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
	public int getId_tipousuario() {
		return id_tipousuario;
	}
	public void setId_tipousuario(int id_tipousuario) {
		this.id_tipousuario = id_tipousuario;
	}
	public int getIdTipoDepartamento() {
		return idTipoDepartamento;
	}
	public void setIdTipoDepartamento(int idTipoDepartamento) {
		this.idTipoDepartamento = idTipoDepartamento;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDescripcionTU() {
		return descripcionTU;
	}
	public void setDescripcionTU(String descripcionTU) {
		this.descripcionTU = descripcionTU;
	}
	public String getDescripciondep() {
		return descripciondep;
	}
	public void setDescripciondep(String descripciondep) {
		this.descripciondep = descripciondep;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDpasssword() {
		return dpasssword;
	}
	public void setDpasssword(String dpasssword) {
		this.dpasssword = dpasssword;
	}
	public departamentodb getDepartamentodb() {
		return departamentodb;
	}
	public void setDepartamentodb(departamentodb departamentodb) {
		this.departamentodb = departamentodb;
	}
	public Usuariodb(int id_persona, int id_tipousuario, int idTipoDepartamento, String nombres, String apellidos,
			String cedula, String email, String direccion, String telefono, String descripcionTU, String descripciondep,
			String alias, String dpasssword, com.controlador.entidades.departamentodb departamentodb) {
		super();
		this.id_persona = id_persona;
		this.id_tipousuario = id_tipousuario;
		this.idTipoDepartamento = idTipoDepartamento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.descripcionTU = descripcionTU;
		this.descripciondep = descripciondep;
		this.alias = alias;
		this.dpasssword = dpasssword;
		this.departamentodb = departamentodb;
	}
	public Usuariodb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	}
