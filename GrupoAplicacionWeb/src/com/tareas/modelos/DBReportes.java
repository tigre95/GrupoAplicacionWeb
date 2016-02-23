package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.controlador.entidades.tareasReporte;

public class DBReportes {
	
	public ArrayList<tareasReporte>ReportePorAnioRealizadas(int anio,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as "
		+ "ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' "
		+ "and niv.descripcion like'%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					
						ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
						ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
						ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorAnioNoRealizadas(int anio,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,"
		+ "tar.id_tarea as ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,"
		+ "per.cedula as cedula,niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' "
		+ "and niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "tar.id_persotarea = per.id_persona  and tar.id_tipotarea = niv.id_tipotarea "
		+ "and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorMesRealizadas(int anio,int mes,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as "
		+ "ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' and "
		+ "niv.descripcion like'%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and Month(tar.fecha_inicio)="
		+mes+" and tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorMesNoRealizadas(int anio,int mes,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "Month(tar.fecha_inicio)="+mes+" and tar.id_persotarea = per.id_persona  and tar.id_tipotarea = "
		+ "niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorFechaRealizadas(String fecha1,String fecha2,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' and "
		+ "niv.descripcion like'%"+nivel+"%' and (tar.fecha_inicio BETWEEN '"+fecha1+"' and '"+fecha2+"') and "
		+ "tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);	
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorFechaNoRealizadas(String fecha1,String fecha2,String nivel, String estado, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per, "
		+ "niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and "
		+ "(tar.fecha_inicio BETWEEN '"+fecha1+"' and '"+fecha2+"') and tar.id_persotarea = per.id_persona  and "
		+ "tar.id_tipotarea = niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);	
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorAnioNivel(int anio,String nivel, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,"
		+ "tar.id_tarea as ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,"
		+ "per.cedula as cedula,niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where per.estado='A' "
		+ "and niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "tar.id_persotarea = per.id_persona  and tar.id_tipotarea = niv.id_tipotarea "
		+ "and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorMesNivel(int anio,int mes,String nivel, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "Month(tar.fecha_inicio)="+mes+" and tar.id_persotarea = per.id_persona  and tar.id_tipotarea = "
		+ "niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorFechaNivel(String fecha1,String fecha2,String nivel, int id_departamento){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per, "
		+ "niveltareas niv where per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and "
		+ "(tar.fecha_inicio BETWEEN '"+fecha1+"' and '"+fecha2+"') and tar.id_persotarea = per.id_persona  and "
		+ "tar.id_tipotarea = niv.id_tipotarea and per.id_departamento = "+id_departamento+";";
		System.out.println(query);	
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorAnioRealizadasAdministrador(int anio,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as "
		+ "ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' "
		+ "and niv.descripcion like'%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea;";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorAnioNoRealizadasAdministrador(int anio,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,"
		+ "tar.id_tarea as ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,"
		+ "per.cedula as cedula,niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' "
		+ "and niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "tar.id_persotarea = per.id_persona  and tar.id_tipotarea = niv.id_tipotarea;";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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
	
	public ArrayList<tareasReporte>ReportePorMesRealizadasAdministrador(int anio,int mes,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as "
		+ "ct,tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' and "
		+ "niv.descripcion like'%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and Month(tar.fecha_inicio)="
		+mes+" and tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea;";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorMesNoRealizadasAdministrador(int anio,int mes,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per,"
		+ " niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and Year(tar.fecha_inicio)="+anio+" and "
		+ "Month(tar.fecha_inicio)="+mes+" and tar.id_persotarea = per.id_persona  and tar.id_tipotarea = "
		+ "niv.id_tipotarea;";
		System.out.println(query);
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorFechaRealizadasAdministrador(String fecha1,String fecha2,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel,tr.descipcion as descripcion_empleado,tr.fecha_fin as fecha_terminada,"
		+ "tar.estado as estadotarea  from tareas tar ,personas per, niveltareas niv ,tarearealizada tr where "
		+ "tar.estado like '%"+estado+"%' and per.estado='A' and niv.estado='A' and tr.estado='A' and "
		+ "niv.descripcion like'%"+nivel+"%' and (tar.fecha_inicio BETWEEN '"+fecha1+"' and '"+fecha2+"') and "
		+ "tar.id_persotarea = per.id_persona and tr.id_tarea = tar.id_tarea and "
		+ "tar.id_tipotarea = niv.id_tipotarea;";
		System.out.println(query);	
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					ped.setFecha_terminada(ped.getFecha_terminada().substring(0, 10));
					lista.add(ped);
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

	public ArrayList<tareasReporte>ReportePorFechaNoRealizadasAdministrador(String fecha1,String fecha2,String nivel, String estado){			
		ArrayList<tareasReporte> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="select tar.descripcion as descripcion_jefe,tar.fecha_inicio as anio,tar.id_tarea as ct,"
		+ "tar.fecha_fin as fecha_fin,per.nombres as nombres,per.apellidos as apellidos,per.cedula as cedula,"
		+ "niv.descripcion as nivel, concat('no se puede mostrar') as descripcion_empleado,"
		+ "concat('no se puede mostrar') as fecha_terminada,tar.estado as estadotarea  from tareas tar ,personas per, "
		+ "niveltareas niv where tar.estado like '%"+estado+"%' and per.estado='A' and "
		+ "niv.estado='A' and niv.descripcion like '%"+nivel+"%' and "
		+ "(tar.fecha_inicio BETWEEN '"+fecha1+"' and '"+fecha2+"') and tar.id_persotarea = per.id_persona  and "
		+ "tar.id_tipotarea = niv.id_tipotarea;";
		System.out.println(query);	
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
		
		tareasReporte ped= null;				
		lista= new ArrayList<tareasReporte>();
			try {
				while (resultados.next()){
					ped= new tareasReporte();
					ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
					ped.setFecha_inicio(resultados.getString("anio"));
					ped.setFecha_fin(resultados.getString("fecha_fin"));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
					ped.setFecha_terminada(resultados.getString("fecha_terminada"));
					ped.setNivel(resultados.getString("nivel"));
					ped.setEstado(resultados.getString("estadotarea"));
					ped.setFecha_inicio(ped.getFecha_inicio().substring(0, 10));
					ped.setFecha_fin(ped.getFecha_fin().substring(0, 10));
					lista.add(ped);
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
