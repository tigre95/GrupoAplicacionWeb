package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.controlador.entidades.tareasReporte;

public class DBReporteNivel {
	
		public ArrayList<tareasReporte>ReportePorAño2(String criterio,String criterio2, String criterio3){			
			ArrayList<tareasReporte> lista= null;
			//conectar a la bd
			DBManager dbmanager = new DBManager();
			Connection con = dbmanager.getConection();
			if(con==null){return lista;}
			
			Statement sentencia,sentencia2;
			ResultSet resultados= null;
			ResultSet resultados2= null;
			String query="";
			String query2="";
			if(criterio.equals("General") ){
				if(criterio2.equals("Alto")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Alto' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
				
				}else{if(criterio2.equals("Medio")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Medio' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
				}
				
				else{if(criterio2.equals("Bajo")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Bajo' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
				}
				}
				}
				
				try {
					sentencia= con.createStatement();
					resultados= sentencia.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
			}
			else{
				query2="select id_tipotarea,descripcion from niveltareas where estado='A' order by descripcion asc";
				try {
					sentencia2= con.createStatement();
					resultados2= sentencia2.executeQuery(query2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}

				
			}
			
			tareasReporte ped= null;				
			lista= new ArrayList<tareasReporte>();
			if(criterio.equals("General") ){
				try {
					while (resultados.next()){
						ped= new tareasReporte();
						ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
						ped.setFecha_inicio(Integer.toString(resultados.getInt("anio")));
						System.out.println("lol: "+ped.getFecha_inicio());
						ped.setFecha_fin(resultados.getString("fecha_fin"));
						ped.setCantidad(resultados.getInt("ct"));
						ped.setNombre(resultados.getString("nombres"));
						ped.setApellidos(resultados.getString("apellidos"));
						ped.setCedula(resultados.getString("cedula"));
						ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
						ped.setFecha_terminada(resultados.getString("fecha_terminada"));
						ped.setNivel(resultados.getString("nivel"));
						lista.add(ped);
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
			}else{
				try {
					while (resultados2.next()){
						int idcat=resultados2.getInt("id_tipotarea");
						if(criterio2.equals("Alto")){
							query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A' and niv.descripcion='Alto'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
								
						}
						else
						{
							if(criterio2.equals("Medio")){
							query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A'and niv.descripcion='Medio'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea   and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
						}
						
					else
					{if(criterio2.equals("Bajo")){
						query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A' and niv.descripcion='Bajo'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
					}
					}
						}

						try {
							sentencia= con.createStatement();
							resultados= sentencia.executeQuery(query);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en ejecucion de sentencia" + e.getMessage());
						}
						
						try {
							while (resultados.next()){
								ped= new tareasReporte();
								ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
								ped.setFecha_inicio(Integer.toString(resultados.getInt("anio")));
								System.out.println("lol: "+ped.getFecha_inicio());
								ped.setFecha_fin(resultados.getString("fecha_fin"));
								ped.setCantidad(resultados.getInt("ct"));
								ped.setNombre(resultados.getString("nombres"));
								ped.setApellidos(resultados.getString("apellidos"));
								ped.setCedula(resultados.getString("cedula"));
								ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
								ped.setFecha_terminada(resultados.getString("fecha_terminada"));
								ped.setNivel(resultados.getString("nivel"));
								lista.add(ped);
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en recorrer los resultados");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
			}
			
			//recorrer los resultados
				
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al cerrar la conexion");
			}
			
			return lista;	
		}
		
		public ArrayList<tareasReporte> ReportePorMes2(String criterio,String criterio2,int criterio3,String criterio4){
			int anio=Integer.parseInt(criterio4);
			ArrayList<tareasReporte> lista= null;
			//conectar a la bd
			DBManager dbmanager = new DBManager();
			Connection con = dbmanager.getConection();
			if(con==null){return lista;}
			
			Statement sentencia,sentencia2;
			ResultSet resultados= null; ResultSet resultados2= null;
			
			String query=""; String query2="";
			tareasReporte ped= null;				
			lista= new ArrayList<tareasReporte>();
		
			if(criterio.equals("General") ){
				if(criterio2.equals("Alto")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio, Month(tar.fecha_inicio) as fecha2,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Alto' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Month(tar.fecha_inicio)="+criterio3+" and Year(tar.fecha_inicio)="+criterio4+" group by anio,per.id_persona order by ct desc limit 10";
					
				}else{if(criterio2.equals("Medio")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio, Month(tar.fecha_inicio) as fecha2,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Medio' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Month(tar.fecha_inicio)="+criterio3+" and Year(tar.fecha_inicio)="+criterio4+" group by anio,per.id_persona order by ct desc limit 10";
					
				}else{if(criterio2.equals("Bajo")){
					query="select tar.descripcion as descripcion_jefe,Year(tar.fecha_inicio )as anio, Month(tar.fecha_inicio) as fecha2,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and niv.descripcion='Bajo' and tar.estado='A' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and Month(tar.fecha_inicio)="+criterio3+" and Year(tar.fecha_inicio)="+criterio4+" group by anio,per.id_persona order by ct desc limit 10";
					
				}
				}}
				try {
					sentencia= con.createStatement();
					resultados= sentencia.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				//recorrer los resultados
				try {
					while (resultados.next()){
						ped= new tareasReporte();
						ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
						ped.setFecha_inicio(Integer.toString(resultados.getInt("anio")));
						System.out.println("lol: "+ped.getFecha_inicio());
						ped.setFecha_fin(ObtenerMes(resultados.getInt("fecha2")));
						ped.setCantidad(resultados.getInt("ct"));
						ped.setNombre(resultados.getString("nombres"));
						ped.setApellidos(resultados.getString("apellidos"));
						ped.setCedula(resultados.getString("cedula"));
						ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
						ped.setFecha_terminada(resultados.getString("fecha_terminada"));
						ped.setNivel(resultados.getString("nivel"));
						
						lista.add(ped);		
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}}
			
			else{
				query2="select id_tipotarea,descripcion from niveltareas where estado='A' order by descripcion asc";
				try {
					sentencia2= con.createStatement();
					resultados2= sentencia2.executeQuery(query2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				try {
					while (resultados2.next()){
						int idcat=resultados2.getInt("id_tipotarea");
						if(criterio2.equals("Alto")){
							query="select tar.descripcion as descripcion_jefe, Month(tar.fecha_inicio) as fecha2,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A' and niv.descripcion='Alto'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio4+"  and month(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
								
						}
						else
						{
							if(criterio2.equals("Medio")){
								query="select tar.descripcion as descripcion_jefe, Month(tar.fecha_inicio) as fecha2,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A' and niv.descripcion='Medio'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio4+"  and month(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
						}
						
						
					
					else
					{if(criterio2.equals("Bajo")){
						query="select tar.descripcion as descripcion_jefe, Month(tar.fecha_inicio) as fecha2,Year(tar.fecha_inicio )as anio,tar.id_tarea as ct,tar.fecha_fin as fecha_fin ,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A'  and tar.estado='A' and niv.descripcion='Bajo'  and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and niv.id_tipotarea="+idcat+" and Year(tar.fecha_inicio)="+criterio4+"  and month(tar.fecha_inicio)="+criterio3+" group by anio,per.id_persona order by ct desc limit 10";
					}
					}
						}
						
						
						try {
							sentencia= con.createStatement();
							resultados= sentencia.executeQuery(query);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en ejecucion de sentencia" + e.getMessage());
						}
						
						try {
							while (resultados.next()){
								ped= new tareasReporte();
								ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
								ped.setFecha_inicio(Integer.toString(resultados.getInt("anio")));
								System.out.println("lol: "+ped.getFecha_inicio());
								ped.setFecha_fin(ObtenerMes(resultados.getInt("fecha2")));
								ped.setCantidad(resultados.getInt("ct"));
								ped.setNombre(resultados.getString("nombres"));
								ped.setApellidos(resultados.getString("apellidos"));
								ped.setCedula(resultados.getString("cedula"));
								ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
								ped.setFecha_terminada(resultados.getString("fecha_terminada"));
								ped.setNivel(resultados.getString("nivel"));
								lista.add(ped);
								
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en recorrer los resultados");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
				
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
		
	public ArrayList<tareasReporte> ReportePorFecha(String criterio,String criterio2, String criterio3,String criterio4){
			
			ArrayList<tareasReporte> lista= null;
			//conectar a la bd
			DBManager dbmanager = new DBManager();
			Connection con = dbmanager.getConection();
			if(con==null){return lista;}
			
			Statement sentencia,sentencia2;
			ResultSet resultados= null; ResultSet resultados2= null;
			String query=""; String query2="";
			tareasReporte ped= null;				
			lista= new ArrayList<tareasReporte>();
			if(criterio.equals("General") ){
				if(criterio2.equals("Alto")){
					query="select tar.fecha_inicio as fecha_inicio ,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and niv.descripcion='Alto' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea and ((tar.fecha_inicio >='"+criterio3+"') and (tar.fecha_inicio<='"+criterio4+"'))  group by per.id_persona order by ct desc limit 10";
					
				}else{if(criterio2.equals("Medio")){
					query="select tar.fecha_inicio as fecha_inicio ,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and niv.descripcion='Medio' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea and ((tar.fecha_inicio >='"+criterio3+"') and (tar.fecha_inicio<='"+criterio4+"'))  group by per.id_persona order by ct desc limit 10";
					
				}else{if(criterio2.equals("Bajo")){
					query="select tar.fecha_inicio as fecha_inicio ,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and niv.descripcion='Bajo' and tar.id_persona= per.id_persona and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea and ((tar.fecha_inicio >='"+criterio3+"') and (tar.fecha_inicio<='"+criterio4+"'))  group by per.id_persona order by ct desc limit 10";
					
				}
				}}
			
			
				try {
					sentencia= con.createStatement();
					resultados= sentencia.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				try {
						while (resultados.next()){
							ped= new tareasReporte();
							ped.setFecha_inicio(resultados.getString("fecha_inicio"));
							ped.setFecha_fin(resultados.getString("fecha_fin"));
							ped.setCantidad(0);
							ped.setNombre(resultados.getString("nombres"));
							ped.setApellidos(resultados.getString("apellidos"));
							ped.setCedula(resultados.getString("cedula"));
							ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
							
							ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
							ped.setFecha_terminada(resultados.getString("fecha_terminada"));
							ped.setNivel(resultados.getString("nivel"));
								lista.add(ped);
							
											
						}
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error en recorrer los resultados");
				}

			}
			else{
				query2="select id_tipotarea,descripcion from niveltareas where estado='A' order by descripcion asc";
				try {
					sentencia2= con.createStatement();
					resultados2= sentencia2.executeQuery(query2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en ejecucion de sentencia" + e.getMessage());
				}
				
				try {
					while (resultados2.next()){
						int idcat=resultados2.getInt("id_tipotarea");
						if(criterio2.equals("Alto")){
							query="select tar.fecha_inicio,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and tar.id_persona= per.id_persona and niv.descripcion='Alto' and niv.id_tipotarea="+idcat+" and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and ((tar.fecha_inicio >='"+criterio2+"') and (tar.fecha_inicio<='"+criterio3+"'))  group by per.id_persona order by ct desc limit 10";
								
						}
						else
						{
							if(criterio2.equals("Medio")){
								query="select tar.fecha_inicio,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and tar.id_persona= per.id_persona and niv.descripcion='Medio' and niv.id_tipotarea="+idcat+" and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and ((tar.fecha_inicio >='"+criterio2+"') and (tar.fecha_inicio<='"+criterio3+"'))  group by per.id_persona order by ct desc limit 10";
						}
						
						
					
					else
					{if(criterio2.equals("Bajo")){
						query="select tar.fecha_inicio,tar.fecha_fin as fecha_fin,tar.descripcion as descripcion_jefe,tar.id_tarea as ct,per.nombres as nombres ,per.apellidos as apellidos,per.cedula as cedula,niv.descripcion as nivel ,tr.descipcion as descripcion_empleado ,tr.fecha_fin as fecha_terminada  from tareas as tar ,personas as per, niveltareas niv ,tarearealizada tr where tar.estado='A' and per.estado='A' and niv.estado='A' and tar.estado='A' and tar.id_persona= per.id_persona and niv.descripcion='Bajo' and niv.id_tipotarea="+idcat+" and niv.id_tipotarea=tar.id_tipotarea and tr.id_tarea=tar.id_tarea  and ((tar.fecha_inicio >='"+criterio2+"') and (tar.fecha_inicio<='"+criterio3+"'))  group by per.id_persona order by ct desc limit 10";
					}
					}
						}
						
						
						
						try {
							sentencia= con.createStatement();
							resultados= sentencia.executeQuery(query);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en ejecucion de sentencia" + e.getMessage());
						}
						
						try {
							while (resultados.next()){
								ped= new tareasReporte();
								ped.setFecha_inicio(resultados.getString("fecha_inicio"));
								ped.setFecha_fin(resultados.getString("fecha_fin"));
								ped.setCantidad(0);
								ped.setNombre(resultados.getString("nombres"));
								ped.setApellidos(resultados.getString("apellidos"));
								ped.setCedula(resultados.getString("cedula"));
								ped.setDescripcion_jefe(resultados.getString("descripcion_jefe"));
								
								ped.setDescripcion_empleado(resultados.getString("descripcion_empleado"));
								ped.setFecha_terminada(resultados.getString("fecha_terminada"));
								ped.setNivel(resultados.getString("nivel"));
									lista.add(ped);
								lista.add(ped);
												
							}
					    } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Error en recorrer los resultados");
					    }
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
				}
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
		
		public String ObtenerMes(int mes){
			String month2="";
			switch(mes){
				case 1:
					month2="Enero";
					break;
				case 2: 
					month2="Febrero";
					break;
				case 3: 
					month2="Marzo";
					break;
				case 4: 
					month2="Abril";
					break;
				case 5: 
					month2="Mayo";
					break;
				case 6: 
					month2="Junio";
					break;
				case 7: 
					month2="Julio";
					break;
				case 8: 
					month2="Agosto";
					break;
				case 9: 
					month2="Septiembre";
					break;
				case 10: 
					month2="Octubre";
					break;
				case 11: 
					month2="Noviembre";
					break;
				case 12: 
					month2="Diciembre";
					break;
			}
			return month2;
		}}
		
	