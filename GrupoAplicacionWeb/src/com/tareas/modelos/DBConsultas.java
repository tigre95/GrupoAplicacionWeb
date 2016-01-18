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

	public ArrayList<TareasAsignadas>buscaryNivel(int  criterio10){			
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		
		query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
				+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
				+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and "
				+ "tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  and niv.id_tipotarea = "+criterio10+";";
			
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
		
		//recorrer los resultados
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				
				
				us.setDescripcion(resultados.getString("descripcion"));
				us.setFecha_inicio(resultados.getString("fecha_inicio"));
				us.setFecha_fin(resultados.getString("fecha_fin"));
				us.setNombres(resultados.getString("nombres"));
				//departamentodb dep = new departamentodb();
				//dep.setDescripciondp(resultados.getString("descripciondep"));
			//	us.setDepartamentodb(dep);
				us.setApellidos(resultados.getString("apellidos"));
				
				us.setCedula(resultados.getString("cedula"));
				us.setNivel(resultados.getString("nivel"));
				us.setDescripcion_re(resultados.getString("descripcion_re"));
				us.setFecha_terminada(resultados.getString("fecha_terminada"));

				lista.add(us);
								
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al cerrar la conexion");
		}
		
		return lista;	
	}
	
	
	
	
	public ArrayList<TareasAsignadas>buscaryNivel2(){			
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		
		query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
				+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
				+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and "
				+ "tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  ";
			
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
		
		//recorrer los resultados
		try {
			while (resultados.next()){
				us= new TareasAsignadas();
				
				
				us.setDescripcion(resultados.getString("descripcion"));
				us.setFecha_inicio(resultados.getString("fecha_inicio"));
				us.setFecha_fin(resultados.getString("fecha_fin"));
				us.setNombres(resultados.getString("nombres"));
				//departamentodb dep = new departamentodb();
				//dep.setDescripciondp(resultados.getString("descripciondep"));
			//	us.setDepartamentodb(dep);
				us.setApellidos(resultados.getString("apellidos"));
				
				us.setCedula(resultados.getString("cedula"));
				us.setNivel(resultados.getString("nivel"));
				us.setDescripcion_re(resultados.getString("descripcion_re"));
				us.setFecha_terminada(resultados.getString("fecha_terminada"));

				lista.add(us);
								
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al cerrar la conexion");
		}
		
		return lista;	
	}
	
	
	
	
	

}
	


