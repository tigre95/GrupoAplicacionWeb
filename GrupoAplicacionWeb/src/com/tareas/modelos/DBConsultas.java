package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.controlador.entidades.TareasAsignadas;
import com.controlador.entidades.Usuariodb;
import com.controlador.entidades.personas;

public class DBConsultas {

	public ArrayList<TareasAsignadas>consultastareasestado_realizadas(String estado, String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, tr.descipcion as descripcion_re, tr.fecha_fin as fecha_terminada, tar.estado as estado_tarea from "
		+ "tareas as tar, personas as per, niveltareas niv, tarearealizada tr where  per.estado='A' and niv.estado='A' "
		+ "and tar.estado like '%"+estado+"%' and tr.estado = 'A' and tar.id_persotarea= per.id_persona and "
		+ "niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' or "
		+ "per.apellidos like '%"+criterio+"%' or per.cedula like '%"+criterio+"%')=true and "
		+ "niv.descripcion like '%"+nivel+"%' and per.id_departamento = "+id_departamento+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				us.setFecha_terminada(us.getFecha_terminada().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}
	
	public ArrayList<TareasAsignadas>consultastareasestado_norealizadas(String estado, String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_re, concat('no se puede mostrar') "
		+ "as fecha_terminada, tar.estado as estado_tarea from tareas as tar ,personas as per, niveltareas niv where  per.estado='A' and "
		+ "niv.estado='A' and tar.estado like '%"+estado+"%' and tar.id_persotarea = per.id_persona and "
		+ "niv.id_tipotarea = tar.id_tipotarea and (per.nombres like '%"+criterio+"%' or per.apellidos like '%"+criterio+
		"%' or per.cedula like '%"+criterio+"%')=true and niv.descripcion like '%"+nivel+"%' and "
		+ "per.id_departamento = "+id_departamento+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}
	
	public ArrayList<TareasAsignadas>consultastareasnivel(String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_re, concat('no se puede mostrar') "
		+ "as fecha_terminada, tar.estado as estado_tarea from tareas as tar ,personas as per, niveltareas niv where  per.estado='A' and "
		+ "niv.estado='A' and tar.id_persotarea = per.id_persona and "
		+ "niv.id_tipotarea = tar.id_tipotarea and (per.nombres like '%"+criterio+"%' or per.apellidos like '%"+criterio+
		"%' or per.cedula like '%"+criterio+"%')=true and niv.descripcion like '%"+nivel+"%' and "
		+ "per.id_departamento = "+id_departamento+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}

	public ArrayList<TareasAsignadas>consultastareasestado_realizadasAdministrador(String estado, String nivel, String criterio){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, tr.descipcion as descripcion_re, tr.fecha_fin as fecha_terminada, tar.estado as estado_tarea from "
		+ "tareas as tar, personas as per, niveltareas niv, tarearealizada tr where  per.estado='A' and niv.estado='A' "
		+ "and tar.estado like '%"+estado+"%' and tr.estado = 'A' and tar.id_persotarea= per.id_persona and "
		+ "niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' or "
		+ "per.apellidos like '%"+criterio+"%' or per.cedula like '%"+criterio+"%')=true and "
		+ "niv.descripcion like '%"+nivel+"%';";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				us.setFecha_terminada(us.getFecha_terminada().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}

	public ArrayList<TareasAsignadas>consultastareasestado_norealizadasAdministrador(String estado, String nivel, String criterio){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_re, concat('no se puede mostrar') "
		+ "as fecha_terminada, tar.estado as estado_tarea from tareas as tar ,personas as per, niveltareas niv where  per.estado='A' and "
		+ "niv.estado='A' and tar.estado like '%"+estado+"%' and tar.id_persotarea = per.id_persona and "
		+ "niv.id_tipotarea = tar.id_tipotarea and (per.nombres like '%"+criterio+"%' or per.apellidos like '%"+criterio+
		"%' or per.cedula like '%"+criterio+"%')=true and niv.descripcion like '%"+nivel+"%';";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}

	public ArrayList<TareasAsignadas>consultastareasestado_realizadas_empleado(int id_empleado, String estado, String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, tr.descipcion as descripcion_re, tr.fecha_fin as fecha_terminada, tar.estado as estado_tarea from "
		+ "tareas as tar, personas as per, niveltareas niv, tarearealizada tr where  per.estado='A' and niv.estado='A' "
		+ "and tar.estado like '%"+estado+"%' and tr.estado = 'A' and tar.id_persotarea= per.id_persona and "
		+ "niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' or "
		+ "per.apellidos like '%"+criterio+"%' or per.cedula like '%"+criterio+"%')=true and "
		+ "niv.descripcion like '%"+nivel+"%' and per.id_departamento = "+id_departamento+" and per.id_persona = "+id_empleado+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				us.setFecha_terminada(us.getFecha_terminada().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}
	
	public ArrayList<TareasAsignadas>consultastareasestado_norealizadas_empleado(int id_empleado,String estado, String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_re, concat('no se puede mostrar') "
		+ "as fecha_terminada, tar.estado as estado_tarea from tareas as tar ,personas as per, niveltareas niv where  per.estado='A' and "
		+ "niv.estado='A' and tar.estado like '%"+estado+"%' and tar.id_persotarea = per.id_persona and "
		+ "niv.id_tipotarea = tar.id_tipotarea and (per.nombres like '%"+criterio+"%' or per.apellidos like '%"+criterio+
		"%' or per.cedula like '%"+criterio+"%')=true and niv.descripcion like '%"+nivel+"%' and "
		+ "per.id_departamento = "+id_departamento+" and per.id_persona = "+id_empleado+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}
	
	public ArrayList<TareasAsignadas>consultastareasnivel_empleado(int id_empleado, String nivel, String criterio, int id_departamento){
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		if(con==null){
			return lista;
		}
		Statement sentencia;
		ResultSet resultados= null;
		String query = "select tar.descripcion as descripcion, tar.fecha_inicio as fecha_inicio, "
		+ "tar.fecha_fin as fecha_fin ,per.nombres as nombres, per.apellidos as apellidos, per.cedula as cedula, "
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_re, concat('no se puede mostrar') "
		+ "as fecha_terminada, tar.estado as estado_tarea from tareas as tar ,personas as per, niveltareas niv where  per.estado='A' and "
		+ "niv.estado='A' and tar.id_persotarea = per.id_persona and "
		+ "niv.id_tipotarea = tar.id_tipotarea and (per.nombres like '%"+criterio+"%' or per.apellidos like '%"+criterio+
		"%' or per.cedula like '%"+criterio+"%')=true and niv.descripcion like '%"+nivel+"%' and "
		+ "per.id_departamento = "+id_departamento+" and per.id_persona = "+id_empleado+";";
		
		System.out.println(query);
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		TareasAsignadas us= null;				
		lista= new ArrayList<TareasAsignadas>();
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				us.setDescripcion(resultados.getString(1));
				us.setFecha_inicio(resultados.getString(2));
				us.setFecha_fin(resultados.getString(3));
				us.setNombres(resultados.getString(4));
				us.setApellidos(resultados.getString(5));
				us.setCedula(resultados.getString(6));
				us.setNivel(resultados.getString(7));
				us.setDescripcion_re(resultados.getString(8));
				us.setFecha_terminada(resultados.getString(9));
				us.setEstado(resultados.getString(10));
				us.setFecha_inicio(us.getFecha_inicio().substring(0, 10));
				us.setFecha_fin(us.getFecha_fin().substring(0, 10));
				lista.add(us);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
			return lista;
		}
	}
}