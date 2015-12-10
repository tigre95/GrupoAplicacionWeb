package com.tareas.modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.controlador.entidades.NivelTareas;

public class DBnivelTarea {

	public ArrayList<NivelTareas>Validarniveltarea(String criterio, int criterio2){			
		ArrayList<NivelTareas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM niveltareas as niv where niv.estado='A' and (niv.descripcion = '" + criterio + "' and niv.id_tipotarea <>"+criterio2+") order by niv.descripcion";
		
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		NivelTareas categorias= null;				
		lista= new ArrayList<NivelTareas>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				categorias= new NivelTareas();
				categorias.setId_tipotarea(resultados.getInt("id_tipotarea"));
				categorias.setDescripcion(resultados.getString("descripcion"));
				lista.add(categorias);
								
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
	
	public boolean editarNivelTarea(NivelTareas NivelTareas){
		boolean resultado = false;
		Connection con = null;
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
		String sql = "UPDATE niveltareas SET  descripcion=? where id_tipotarea=?";

		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, NivelTareas.getDescripcion());
			sentencia.setInt(2, NivelTareas.getId_tipotarea());
			int numFilasAfectadas = sentencia.executeUpdate();
			if(numFilasAfectadas >0){									
				con.commit();
				resultado =true;
			}else{
				con.rollback();
				}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean crearNivelTarea(NivelTareas NivelTareas){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();							
		String sql ="INSERT INTO niveltareas (descripcion,estado) VALUES (?,'A')";			
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			stmt.setString(1, NivelTareas.getDescripcion());
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0){
				con.commit();
				resultado = true;
			}
			else {
				    System.out.println("No se puedo crear un nuevo Tipo Usuarios");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al crear una nuevo  NivelTareas" + e.getMessage());
			}
			return resultado;
			}
	
	public ArrayList<NivelTareas>buscarNivelTarea(String criterio){			
		ArrayList<NivelTareas> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("") ){
		query = "SELECT * FROM niveltareas where estado='A'";
			
			
		}
		else{
		query = "SELECT * FROM niveltareas as niv where niv.estado='A' and (niv.descripcion like '%" + criterio + "%' ) order by niv.descripcion";
			
		System.out.println(query);
		}
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		NivelTareas NivelTareas= null;				
		lista= new ArrayList<NivelTareas>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				NivelTareas= new NivelTareas();
				NivelTareas.setId_tipotarea(resultados.getInt("id_tipotarea"));
				NivelTareas.setDescripcion(resultados.getString("descripcion"));
				lista.add(NivelTareas);
								
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

	public boolean EliminarNivelTarea(NivelTareas NivelTareas){
		boolean resultado = false;
		String sql="";
			Connection con = null;							
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
	
		sql = "UPDATE niveltareas SET estado=?"
			+ " where id_tipotarea=? ";

		
		System.out.println(sql);
		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, NivelTareas.getEstado());
			sentencia.setInt(2, NivelTareas.getId_tipotarea());
			int numFilasAfectadas = sentencia.executeUpdate();
			if(numFilasAfectadas > 0){
					resultado = true;
					con.commit();
			}else{
				con.rollback();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}


