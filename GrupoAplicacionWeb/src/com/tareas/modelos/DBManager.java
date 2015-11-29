package com.tareas.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/* Clase  que se encarga de gestionar
 * la conexion  con la base de datos libreria*/
 

public class DBManager {
	Connection conection;
	
	//Parametros de conexion
	final String cadenaConexion="jdbc:mysql://localhost/bd_sistemacompleto";
	final String User="root";

	final String password="upse";

	//constructor para la clase
	public DBManager(){
		this.conection=null;
	}
	//metodo para obtener la conexion
	public Connection getConection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class mysql no encontrada");
		}
		
		try {
			this.conection= DriverManager.getConnection(cadenaConexion,this.User,this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en conexion" + e.getMessage());
		}
		
		return this.conection;
	}
}