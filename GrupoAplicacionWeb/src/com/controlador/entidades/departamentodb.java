package com.controlador.entidades;

public class departamentodb {
	private int id_tipodepartamento;
	private String descripciondp;
	private String estado;
	public departamentodb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public departamentodb(int id_tipodepartamento, String descripciondp, String estado) {
		super();
		this.id_tipodepartamento = id_tipodepartamento;
		this.descripciondp = descripciondp;
		this.estado = estado;
	}
	public int getId_tipodepartamento() {
		return id_tipodepartamento;
	}
	public void setId_tipodepartamento(int id_tipodepartamento) {
		this.id_tipodepartamento = id_tipodepartamento;
	}
	public String getDescripciondp() {
		return descripciondp;
	}
	public void setDescripciondp(String descripciondp) {
		this.descripciondp = descripciondp;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
