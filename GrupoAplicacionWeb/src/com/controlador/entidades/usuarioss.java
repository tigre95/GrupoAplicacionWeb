package com.controlador.entidades;



public class usuarioss {
	private int id;
	private TipoUsuario tipousuario;
	private personas persona;
	private String estado;
	public usuarioss() {
		super();
		// TODO Auto-generated constructor stub
	}
	public usuarioss(int id, TipoUsuario tipousuario, personas persona, String estado) {
		super();
		this.id = id;
		this.tipousuario = tipousuario;
		this.persona = persona;
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoUsuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	public personas getPersona() {
		return persona;
	}
	public void setPersona(personas persona) {
		this.persona = persona;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
