package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zul.ListModelList;

import com.controlador.entidades.tareas;


public class DBTareas {

	
	
	public String actualizarestadotareas(tareas objeto,String estado) throws SQLException{
		int resultado = 0;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "update tareas set " +
				"estado = '" + estado+"'"+" where "+
				"id_tarea = " + objeto.getId_tarea() + ";";
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
	
	public tareas tareasxid(int id_tarea) throws SQLException{
		tareas tarea = new tareas();		
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
			resultado = state.executeQuery("select * from tareas t where t.estado='A' and t.id_tarea="+id_tarea+";");
			while(resultado.next()){
				cont = cont + 1;
				tarea.setId_tarea(resultado.getInt(1));
				tarea.setId_tipotarea(resultado.getInt(2));
				tarea.setId_persona(resultado.getInt(3));
				tarea.setId_persotarea(resultado.getInt(4));
				tarea.setDescripcion(resultado.getString(5));
				tarea.setArchivo(resultado.getString(6));
				tarea.setComentario(resultado.getString(7));
				tarea.setFecha_inicio(resultado.getString(8));
				tarea.setFecha_fin(resultado.getString(9));
				tarea.setEstado(resultado.getString(10));
				tarea.setEstado_tarea(resultado.getInt(11));
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
	
	public String guardartareas(tareas objeto) throws SQLException{
		int resultado = 0;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "insert into tareas (id_tipotarea,id_persona,id_persotarea," +
				"descripcion,archivo,comentario,fecha_inicio,fecha_fin,estado," +
				"estado_tarea) values ("+
				+objeto.getId_tipotarea()+","+objeto.getId_persona()+","+objeto.getId_persotarea()+",'"+
				objeto.getDescripcion()+"','"+objeto.getArchivo()+"','"+objeto.getComentario()+"','"+
				objeto.getFecha_inicio()+"','"+objeto.getFecha_fin()+"','"+objeto.getEstado()+"',"+
				objeto.getEstado_tarea()+");";
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
	
	public String actualizartareas(tareas objeto) throws SQLException{
		int resultado = 0;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "update tareas set " +
				"id_tipotarea = " + objeto.getId_tipotarea()+","+
				"id_persona = " + objeto.getId_persona()+","+
				"id_persotarea = " + objeto.getId_persotarea()+","+
				"descripcion = '" + objeto.getDescripcion()+"',"+
				"archivo = '" + objeto.getArchivo()+"',"+
				"comentario = '" + objeto.getComentario()+"',"+
				"fecha_inicio = '" + objeto.getFecha_inicio()+"',"+
				"fecha_fin = '" + objeto.getFecha_fin()+"',"+
				"estado = '" + objeto.getEstado()+"',"+
				"estado_tarea = " + objeto.getEstado_tarea()+" where "+
				"id_tarea = " + objeto.getId_tarea() + ";";
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
	
	public String eliminartareas(int id_tarea) throws SQLException{
		int resultado = 0;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return "0";
		}
		Statement sentencia = null;
		String query = "update tareas set " +
				"estado = 'E' where "+
				"id_tarea = " + id_tarea + ";";
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

	public ListModelList<tareas> lista_tareas_busqueda(int id_persona, String estado){
		int cont = 0;
		ListModelList<tareas> lista_tareas = new ListModelList<tareas>();
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
			resultado = state.executeQuery("SELECT * FROM tareas where (estado = 'E')=false and "
					+ "estado like '%"+estado+"%' and id_persotarea = "+id_persona+";");
			while(resultado.next()){
				cont = cont + 1;
				tareas tarea1 = new tareas();
				tarea1.setId_tarea(resultado.getInt(1));
				tarea1.setId_tipotarea(resultado.getInt(2));
				tarea1.setId_persona(resultado.getInt(3));
				tarea1.setId_persotarea(resultado.getInt(4));
				tarea1.setDescripcion(resultado.getString(5));
				tarea1.setArchivo(resultado.getString(6));
				tarea1.setComentario(resultado.getString(7));
				tarea1.setFecha_inicio(resultado.getString(8));
				tarea1.setFecha_fin(resultado.getString(9));
				tarea1.setEstado(resultado.getString(10));
				tarea1.setEstado_tarea(resultado.getInt(11));
				lista_tareas.add(tarea1);
			}
			if(cont>0){
				return lista_tareas;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ListModelList<tareas> lista_tareas_guardar(int id_persona, String estado){
		int cont = 0;
		ListModelList<tareas> lista_tareas = new ListModelList<tareas>();
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
			resultado = state.executeQuery("SELECT * FROM tareas where (estado = 'E' or "
					+ "estado = 'T' or estado = 'R' )=false and "
					+ "estado like '%"+estado+"%' and id_persotarea = "+id_persona+";");
			while(resultado.next()){
				cont = cont + 1;
				tareas tarea1 = new tareas();
				tarea1.setId_tarea(resultado.getInt(1));
				tarea1.setId_tipotarea(resultado.getInt(2));
				tarea1.setId_persona(resultado.getInt(3));
				tarea1.setId_persotarea(resultado.getInt(4));
				tarea1.setDescripcion(resultado.getString(5));
				tarea1.setArchivo(resultado.getString(6));
				tarea1.setComentario(resultado.getString(7));
				tarea1.setFecha_inicio(resultado.getString(8));
				tarea1.setFecha_fin(resultado.getString(9));
				tarea1.setEstado(resultado.getString(10));
				tarea1.setEstado_tarea(resultado.getInt(11));
				lista_tareas.add(tarea1);
			}
			if(cont>0){
				return lista_tareas;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ListModelList<tareas> lista_tareas_edicion(int id_persona, String estado){
		int cont = 0;
		ListModelList<tareas> lista_tareas = new ListModelList<tareas>();
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
			resultado = state.executeQuery("SELECT * FROM tareas where (estado = 'A' or estado = 'P' or"
					+ " estado = 'E')=false and estado like '%"+estado+"%' and id_persotarea = "+id_persona+";");
			while(resultado.next()){
				cont = cont + 1;
				tareas tarea1 = new tareas();
				tarea1.setId_tarea(resultado.getInt(1));
				tarea1.setId_tipotarea(resultado.getInt(2));
				tarea1.setId_persona(resultado.getInt(3));
				tarea1.setId_persotarea(resultado.getInt(4));
				tarea1.setDescripcion(resultado.getString(5));
				tarea1.setArchivo(resultado.getString(6));
				tarea1.setComentario(resultado.getString(7));
				tarea1.setFecha_inicio(resultado.getString(8));
				tarea1.setFecha_fin(resultado.getString(9));
				tarea1.setEstado(resultado.getString(10));
				tarea1.setEstado_tarea(resultado.getInt(11));
				lista_tareas.add(tarea1);
			}
			if(cont>0){
				return lista_tareas;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int ultima_tarea(){
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
			resultado = state.executeQuery("SELECT max(id_tarea) FROM bd_sistemacompleto.tareas;");
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
	
	public DBTareas() {
		// TODO Auto-generated constructor stub
	}

}
