package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controlador.entidades.personas;

public class DBPersonas {

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
	
	public DBPersonas() {
		// TODO Auto-generated constructor stub
	}

}
