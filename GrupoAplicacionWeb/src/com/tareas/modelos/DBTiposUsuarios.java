package com.tareas.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.controlador.entidades.tiposusuarios;

public class DBTiposUsuarios {

	public tiposusuarios mostrartipousuarios(int id_tipo){			
		tiposusuarios tipousuario= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from tiposusuarios where estado = 'A' and"
		+ " id_tipousuario =" + id_tipo + ";";		
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
				tiposusuarios tipousuario2 = new tiposusuarios();
				tipousuario2.setId_tipousuario(resultados.getInt(1));
				tipousuario2.setDescripcion(resultados.getString(2));
				tipousuario2.setEstado(resultados.getString(3));
				tipousuario = tipousuario2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return tipousuario;
	}
	public DBTiposUsuarios() {
		// TODO Auto-generated constructor stub
	}
	
	
}
