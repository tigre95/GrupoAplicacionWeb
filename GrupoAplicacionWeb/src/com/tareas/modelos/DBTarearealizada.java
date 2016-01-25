package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.controlador.entidades.tarearealizada;
import com.controlador.entidades.tareas;

public class DBTarearealizada {

	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = (diferenciaEn_ms / (1000 * 60 * 60 * 24))+1;
		return (int) dias;
		}
	
	public int ultima_tarea_realizada(){
		int id = 0;
		int cont = 0;
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return id;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("SELECT max(idTarea_realizada) FROM tarearealizada;");
			while(resultado.next()){
				cont = cont + 1;
				id = resultado.getInt(1);
			}
			if(cont>0){
				return id;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public String guardartarearealizada(tarearealizada objeto) throws SQLException{
		int resultado = 0;
		int diferencia_dias = 0;
		
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
				String query = "insert into tarearealizada (id_tarea,descipcion,fecha_fin," +
				"archivo_env,estado) values ("+objeto.getId_tarea()+",'"+
				objeto.getDescipcion()+"','"+objeto.getFecha_fin()+"','"+objeto.getArchivo_env()+"','"+
				objeto.getEstado()+"');";
		try {
			sentencia = conexion.createStatement();
			resultado = sentencia.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al crear la sentencia" + e.getMessage());
		}
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "" + resultado;
	}
	
	public String actualizartarearealizada(tarearealizada objeto) throws SQLException{
		int resultado = 0;
		int diferencia_dias = 0;
		
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "update tarearealizada set " +
				"id_tarea = " + objeto.getId_tarea()+","+
				"descipcion = '" + objeto.getDescipcion()+"',"+
				"fecha_fin = '" + objeto.getFecha_fin()+"',"+
				"archivo_env = '" + objeto.getArchivo_env()+"',"+
				"estado = '" + objeto.getEstado()+"'"+" where "+
				"idTarea_realizada = " + objeto.getId_Tarea_realizada() + ";";
		try {
			sentencia = conexion.createStatement();
			resultado = sentencia.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al crear la sentencia" + e.getMessage());
		}
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ""+resultado;
		
	}
	
	public String eliminartarearealizada(tarearealizada objeto) throws SQLException{
		int resultado = 0;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "update tarearealizada set " +
				"estado = 'E'"+" where "+
				"idTarea_realizada = " + objeto.getId_Tarea_realizada() + ";";
		try {
			sentencia = conexion.createStatement();
			resultado = sentencia.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al crear la sentencia" + e.getMessage());
		}
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ""+resultado;
		
	}
	
	public tarearealizada tareasxid_tarea(int id_tarea) throws SQLException{
		tarearealizada tarea = new tarearealizada();		
		int cont = 0;
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return tarea;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("select * from tarearealizada t where t.estado='A' "
					+ "and t.id_tarea="+id_tarea+";");
			while(resultado.next()){
				cont = cont + 1;
				tarea.setId_Tarea_realizada(resultado.getInt(1));
				tarea.setId_tarea(resultado.getInt(2));
				tarea.setDescipcion(resultado.getString(3));
				tarea.setFecha_fin(resultado.getString(4));
				tarea.setArchivo_env(resultado.getString(5));
				tarea.setEstado(resultado.getString(6));
			}
			if(cont>0){
				return tarea;
			}else{
				return tarea;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tarea;
		}
	}
	
	public DBTarearealizada() {
		// TODO Auto-generated constructor stub
	}

}
