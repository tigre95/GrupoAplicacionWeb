package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.zkoss.zul.ListModelList;
import com.controlador.entidades.personas;

public class DBPersonas {
	
	public personas mostrarpersona(int id_persona){
		personas persona = new personas();
		int cont = 0;
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return null;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("SELECT * FROM personas where estado = 'A'"
					+ " and 	id_persona = "+id_persona+" ;");
			while(resultado.next()){
				cont = cont + 1;
				persona.setId_persona(resultado.getInt(1));
				persona.setNombres(resultado.getString(2));
				persona.setId_departamento(resultado.getInt(3));
				persona.setApellidos(resultado.getString(4));
				persona.setCedula(resultado.getString(5));
				persona.setDireccion(resultado.getString(6));
				persona.setEmail(resultado.getString(7));
				persona.setEstado(resultado.getString(8));
			}
			if(cont>0){
				return persona;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ListModelList<personas> cargarpersona(int id_departamento){
		ListModelList<personas> lista_personas = new ListModelList<personas>();
		int cont = 0;
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return null;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("select p.id_persona, p.nombres, p.id_departamento, p.apellidos,"
					+ " p.cedula, p.direccion, p.email, p.estado from personas p, usuarios u where "
					+ "p.estado = 'A' and u.estado = 'A' and p.id_departamento = "+id_departamento+" and "
					+ "(u.id_tipousuario = 14)=false and u.id_persona = p.id_persona;");
			while(resultado.next()){
				cont = cont + 1;
				personas persona = new personas();
				persona.setId_persona(resultado.getInt(1));
				persona.setNombres(resultado.getString(2));
				persona.setId_departamento(resultado.getInt(3));
				persona.setApellidos(resultado.getString(4));
				persona.setCedula(resultado.getString(5));
				persona.setDireccion(resultado.getString(6));
				persona.setEmail(resultado.getString(7));
				persona.setEstado(resultado.getString(8));
				lista_personas.add(persona);
			}
			if(cont>0){
				return lista_personas;
			}else{	 
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public personas mostrarpersonas(int id_persona){			
		personas persona= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from personas where estado = 'A' and"
		+ " id_persona =" + id_persona + ";";		
		System.out.println(query);		
		try {
			sentencia= con.createStatement();
			resultados = sentencia.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error en ejecucion de sentencia");
			e.printStackTrace();
		}
		try {
			while(resultados.next()){
				personas persona2 = new personas();
				persona2.setId_persona(resultados.getInt(1));
				persona2.setNombres(resultados.getString(2));
				persona2.setId_departamento(resultados.getInt(3));
				persona2.setApellidos(resultados.getString(4));
				persona2.setCedula(resultados.getString(5));
				persona2.setDireccion(resultados.getString(6));
				persona2.setEmail(resultados.getString(7));
				persona2.setEstado(resultados.getString(8));
				persona = persona2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		return persona;
	}
	
	
	public ListModelList<personas> cargarpersonafiltrado(int id_departamento, String cedula, String nombre,
			String apellido){
		ListModelList<personas> lista_personas = new ListModelList<personas>();
		int cont = 0;
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return null;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("select p.id_persona, p.nombres, p.id_departamento, p.apellidos,"
					+ " p.cedula, p.direccion, p.email, p.estado from personas p, usuarios u, "
					+ "tiposusuarios tu where u.id_tipousuario = tu.id_tipousuario and "
					+ "p.estado = 'A' and u.estado = 'A' and p.id_departamento = "+id_departamento+" and "
					+ "tu.descripcion = 'empleado' and u.id_persona = p.id_persona and "
					+ "p.cedula like '%"+ cedula +"%' and p.nombres like '%"+nombre+"%' and "
					+ "p.apellidos like '%"+apellido+"%';");
			while(resultado.next()){
				cont = cont + 1;
				personas persona = new personas();
				persona.setId_persona(resultado.getInt(1));
				persona.setNombres(resultado.getString(2));
				persona.setId_departamento(resultado.getInt(3));
				persona.setApellidos(resultado.getString(4));
				persona.setCedula(resultado.getString(5));
				persona.setDireccion(resultado.getString(6));
				persona.setEmail(resultado.getString(7));
				persona.setEstado(resultado.getString(8));
				lista_personas.add(persona);
			}
			if(cont>0){
				return lista_personas;
			}else{	 
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DBPersonas() {
		// TODO Auto-generated constructor stub
	}

}
