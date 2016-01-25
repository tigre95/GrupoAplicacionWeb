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

	public ArrayList<TareasAsignadas>buscaryNivel(String criterio ,int  criterio10){			
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
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persotarea= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea and (per.nombres like '%"+criterio+"%' and per.apellidos "
				+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%') and niv.id_tipotarea = "+criterio10+";";
			
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
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ "tar.id_persotarea= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
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
	
	
	
	public ArrayList<TareasAsignadas>buscarytareaatrazada(String criterio,int criterio10){			
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
				+ "tr.id_tarea=tar.id_tarea and tar.estado_tarea=4 and (per.nombres like '%"+criterio+"%' or per.apellidos "
				+ "like '%"+criterio+"%' or per.cedula like '%"+criterio+"%') and niv.id_tipotarea = "+criterio10+";";
			
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
	
	
	public ArrayList<TareasAsignadas>buscarytareaatrazada2(){			
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
				+ "tr.id_tarea=tar.id_tarea and tar.estado_tarea=4 ";
			
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

	
	public ArrayList<TareasAsignadas>buscarytareasRealizada(String criterio , int  criterio10,int criterio11){			
		ArrayList<TareasAsignadas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String query2="";
		
		if(con==null){return lista;}
		
		int uno =criterio10;
		int  dos =criterio11;
		String coje =criterio;
	
		


		
//if( criterio.toString().equals(coje) && uno==criterio10 &&  criterio11==0 ){
//			
//			Statement sentencia;
//			ResultSet resultados= null;
//			
//			String query="";
//			
//			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
//					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
//					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
//					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
//					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
//					+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' or per.apellidos "
//					+ "like '%"+criterio+"%' or per.cedula like '%"+criterio+"%' and niv.id_tipotarea="+criterio10+") ";
//			
//				
//			System.out.println(query);
//			
//			
//			try {
//				sentencia= con.createStatement();
//				resultados= sentencia.executeQuery(query);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
//			}
//			
//			TareasAsignadas us= null;				
//			lista= new ArrayList<TareasAsignadas>();
//			
//			//recorrer los resultados
//			try {
//				while (resultados.next()){
//					us= new TareasAsignadas();
//					
//					
//					us.setDescripcion(resultados.getString("descripcion"));
//					us.setFecha_inicio(resultados.getString("fecha_inicio"));
//					us.setFecha_fin(resultados.getString("fecha_fin"));
//					us.setNombres(resultados.getString("nombres"));
//					//departamentodb dep = new departamentodb();
//					//dep.setDescripciondp(resultados.getString("descripciondep"));
//				//	us.setDepartamentodb(dep);
//					us.setApellidos(resultados.getString("apellidos"));
//					
//					us.setCedula(resultados.getString("cedula"));
//					us.setNivel(resultados.getString("nivel"));
//					us.setDescripcion_re(resultados.getString("descripcion_re"));
//					us.setFecha_terminada(resultados.getString("fecha_terminada"));
//
//					lista.add(us);
//									
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Error en recorrer los resultados");
//			}
//
//			
//			
//			
//		}else {
		
if( criterio.toString().equals(coje) && criterio10==3 &&  criterio11==0 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=2) ";
			
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

			
			
			
		}else {
		
		
	
if( criterio.toString().equals(coje) && criterio10==2 &&  criterio11==0 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=2) ";
			
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

			
			
			
		}else {
		
if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==0 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=1) ";
			
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

			
			
			
		}else {
		
		if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==5 ){
			
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
				+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
				+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=1 and tar.estado='E' ) ";
		
				
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

			
			
			
		}else {

if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==4 ){
		
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
		query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
				+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea="+criterio10+" and tar.estado='T' ) ";
			
				
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

			
			
			
		}else {

		if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==3 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
				+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
				+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea="+criterio10+" and tar.estado='A' ) ";
			
				
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

			
			
			
		}else {

		
if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==2 ){
			
			Statement sentencia;
		ResultSet resultados= null;
			
			String query="";
			
		query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=2 and tar.estado='A') ";
			
				
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

			
			
			
		}else {

		
		
	if( criterio.toString().equals(coje) && criterio10==1 &&  criterio11==1 ){
					
					Statement sentencia;
					ResultSet resultados= null;
					
					String query="";
					
					query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
							+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
							+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
							+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
							+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
							+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
							+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%' and niv.id_tipotarea=1 and tar.estado='P' ) ";
					
						
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

					
					
					
				}else {
		

if( criterio.toString().equals(coje) && criterio10==0 &&  criterio11==0 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea  and (per.nombres like '%"+criterio+"%' and per.apellidos "
					+ "like '%"+criterio+"%' and per.cedula like '%"+criterio+"%') ";
			
				
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

			
			
			
		}else {
if( criterio.equals("") && criterio10==0 &&  criterio11==0 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea ";
				
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

			
			
			
		}else {
if( criterio.equals("") && criterio10==3 &&  criterio11==5 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=3 and tar.estado='E'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==3 &&  criterio11==4 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=3 and tar.estado='T'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==3 &&  criterio11==3 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=3 and tar.estado='R'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==3 &&  criterio11==2 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=3 and tar.estado='A'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==3 &&  criterio11==1 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=3 and tar.estado='P'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==2 &&  criterio11==5 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=2 and tar.estado='E'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==2 &&  criterio11==4 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=2 and tar.estado='T'";
				
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

			
			
			
		}else {
		
		
		
if( criterio.equals("") && criterio10==2 &&  criterio11==3 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=2 and tar.estado='R'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==2 &&  criterio11==2 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=2 and tar.estado='A'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==2 &&  criterio11==1 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=2 and tar.estado='P'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==1 &&  criterio11== 5){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=1 and tar.estado='E'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==1 &&  criterio11==4 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=1 and tar.estado='T'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==1 &&  criterio11==3 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=1 and tar.estado='R'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==1 &&  criterio11==2 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=1 and tar.estado='A'";
				
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

			
			
			
		}else {
if( criterio.equals("") && criterio10==1 &&  criterio11==1 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and niv.id_tipotarea=1 and tar.estado='P'";
				
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

			
			
			
		}else {
		
if( criterio.equals("") && criterio10==0 &&  criterio11==5 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and tar.estado=E";
				
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

			
			
			
		}else {
		
		
if( criterio.equals("") && criterio10==0 &&  criterio11==4 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and tar.estado='T'";
				
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

			
			
			
		}else {
		
		
if( criterio.equals("") && criterio10==0 &&  criterio11==3 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and tar.estado='R'";
				
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

			
			
			
		}else {
		
		
if( criterio.equals("") && criterio10==0 &&  criterio11==2 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and tar.estado='A'";
				
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

			
			
			
		}else {
		
		if( criterio.equals("") && criterio10==0 &&  criterio11==1 ){
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			
			query = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea and tar.estado='P'";
				
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

			
			
			
		}else {
			
		
		
		
		if( criterio.equals("") && criterio10==3 &&  criterio11==0 ){
				query2 = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
						+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
						+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
						+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
						+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
						+ "tr.id_tarea=tar.id_tarea   and niv.id_tipotarea = 3";
				Statement sentencia2;
				ResultSet resultados2= null;
				
				
				
					
			//	System.out.println(query);
				
				
				try {
					sentencia2= con.createStatement();
					resultados2= sentencia2.executeQuery(query2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				TareasAsignadas us= null;				
				lista= new ArrayList<TareasAsignadas>();
				
				//recorrer los resultados
				try {
					while (resultados2.next()){
						us= new TareasAsignadas();
						
						
						us.setDescripcion(resultados2.getString("descripcion"));
						us.setFecha_inicio(resultados2.getString("fecha_inicio"));
						us.setFecha_fin(resultados2.getString("fecha_fin"));
						us.setNombres(resultados2.getString("nombres"));
						//departamentodb dep = new departamentodb();
						//dep.setDescripciondp(resultados.getString("descripciondep"));
					//	us.setDepartamentodb(dep);
						us.setApellidos(resultados2.getString("apellidos"));
						
						us.setCedula(resultados2.getString("cedula"));
						us.setNivel(resultados2.getString("nivel"));
						us.setDescripcion_re(resultados2.getString("descripcion_re"));
						us.setFecha_terminada(resultados2.getString("fecha_terminada"));

						lista.add(us);
										
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
				
		}else{
		
		
		
		if( criterio.equals("") && criterio10==2 &&  criterio11==0 ){
				query2 = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
						+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
						+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
						+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
						+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
						+ "tr.id_tarea=tar.id_tarea   and niv.id_tipotarea = 2";
				Statement sentencia2;
				ResultSet resultados2= null;
				
				
				
					
			//	System.out.println(query);
				
				
				try {
					sentencia2= con.createStatement();
					resultados2= sentencia2.executeQuery(query2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				TareasAsignadas us= null;				
				lista= new ArrayList<TareasAsignadas>();
				
				//recorrer los resultados
				try {
					while (resultados2.next()){
						us= new TareasAsignadas();
						
						
						us.setDescripcion(resultados2.getString("descripcion"));
						us.setFecha_inicio(resultados2.getString("fecha_inicio"));
						us.setFecha_fin(resultados2.getString("fecha_fin"));
						us.setNombres(resultados2.getString("nombres"));
						//departamentodb dep = new departamentodb();
						//dep.setDescripciondp(resultados.getString("descripciondep"));
					//	us.setDepartamentodb(dep);
						us.setApellidos(resultados2.getString("apellidos"));
						
						us.setCedula(resultados2.getString("cedula"));
						us.setNivel(resultados2.getString("nivel"));
						us.setDescripcion_re(resultados2.getString("descripcion_re"));
						us.setFecha_terminada(resultados2.getString("fecha_terminada"));

						lista.add(us);
										
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
				
		}else{
		
		
		if( criterio.equals("") && criterio10==1 &&  criterio11==0 ){
			
			query2 = "select tar.descripcion as descripcion,tar.fecha_inicio as fecha_inicio,tar.fecha_fin as fecha_fin ,"
					+ "per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,"
					+ "tr.descipcion as descripcion_re ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per,"
					+ " niveltareas niv ,tarearealizada tr where  per.estado='A' and niv.estado='A' and "
					+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
					+ "tr.id_tarea=tar.id_tarea   and niv.id_tipotarea = 1";
			
				
		
			
			Statement sentencia2;
			ResultSet resultados2= null;
			
			
			
				
		//	System.out.println(query);
			
			
			try {
				sentencia2= con.createStatement();
				resultados2= sentencia2.executeQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
			
			TareasAsignadas us= null;				
			lista= new ArrayList<TareasAsignadas>();
			
			//recorrer los resultados
			try {
				while (resultados2.next()){
					us= new TareasAsignadas();
					
					
					us.setDescripcion(resultados2.getString("descripcion"));
					us.setFecha_inicio(resultados2.getString("fecha_inicio"));
					us.setFecha_fin(resultados2.getString("fecha_fin"));
					us.setNombres(resultados2.getString("nombres"));
					//departamentodb dep = new departamentodb();
					//dep.setDescripciondp(resultados.getString("descripciondep"));
				//	us.setDepartamentodb(dep);
					us.setApellidos(resultados2.getString("apellidos"));
					
					us.setCedula(resultados2.getString("cedula"));
					us.setNivel(resultados2.getString("nivel"));
					us.setDescripcion_re(resultados2.getString("descripcion_re"));
					us.setFecha_terminada(resultados2.getString("fecha_terminada"));

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
		}
		}
		}}}}}}}
	}}}}}}}}}}}}}}
		}
}}
//}
//		}
//		}
//		}
//		}
				}
		}
		}
		
		}	}	}	}
		
		return lista;	
	}
	
	
	
	
	public ArrayList<TareasAsignadas>buscarytareasRealizada2(){			
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
				+ " niveltareas niv ,tarearealizada tr where  niv.estado='A' and "
				+ " tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and "
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