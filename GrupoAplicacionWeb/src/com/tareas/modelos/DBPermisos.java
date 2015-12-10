package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.controlador.entidades.permisos;

public class DBPermisos {

	public permisos mostrarpermisos(int id_tipousuario, String descripcion){			
		permisos permiso= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from permisos where estado = 'A' and"
		+ " id_tipousuario =" + id_tipousuario + " and descripcion = '"+ descripcion +"';";		
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
				permisos permiso2 = new permisos();
				permiso2.setId_permisos(resultados.getInt(1));
				permiso2.setId_tipo_usuario(resultados.getInt(2));
				permiso2.setDescripcion(resultados.getString(3));
				permiso2.setCrear(resultados.getInt(4));
				permiso2.setBuscar(resultados.getInt(5));
				permiso2.setEditar(resultados.getInt(6));
				permiso2.setEliminar(resultados.getInt(7));
				permiso2.setEstado(resultados.getString(8));
				permiso = permiso2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		return permiso;
	}
	
	public DBPermisos() {
		// TODO Auto-generated constructor stub
	}
	
	
}
