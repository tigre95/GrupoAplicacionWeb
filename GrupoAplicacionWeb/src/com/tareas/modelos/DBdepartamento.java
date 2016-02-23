package com.tareas.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.zkoss.zul.ListModelList;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;

public class DBdepartamento {
	public Departamento mostrardepartamento(int id_departamento){			
		Departamento departamento= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from departamento where estado = 'A' and"
		+ " idTipoDepartamento =" + id_departamento + ";";		
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
				Departamento departamento2 = new Departamento();
				departamento2.setId_tipodepartamento(resultados.getInt(1));
				departamento2.setDescripcion(resultados.getString(2));
				departamento2.setEstado(resultados.getString(3));
				departamento = departamento2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		return departamento;
	}
	
	public ArrayList<Departamento>ValidarDepartamento(String criterio, int criterio2){			
		ArrayList<Departamento> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM departamento as dep where dep.estado='A' and (dep.descripcion = '" + criterio + "' and dep.idTipoDepartamento<>"+criterio2+") order by dep.descripcion";
		
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		Departamento categorias= null;				
		lista= new ArrayList<Departamento>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				categorias= new Departamento();
				categorias.setId_tipodepartamento(resultados.getInt("idTipoDepartamento"));
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

	public boolean editardepartamento(Departamento categorias){
		boolean resultado = false;
		Connection con = null;
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
		String sql = "UPDATE departamento SET   descripcion=? where idTipoDepartamento=?";

		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, categorias.getDescripcion());
			sentencia.setInt(2, categorias.getId_tipodepartamento());
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

	public boolean creardepartamento(Departamento categorias){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();							
		String sql ="INSERT INTO departamento (descripcion,estado) VALUES (?,'A')";			
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categorias.getDescripcion());
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0){
				con.commit();
				resultado = true;
			}
			else {
				    System.out.println("No se puedo crear un nueva categoria");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al crear una nuevo departamento" + e.getMessage());
			}
			return resultado;
			}
			
	public ArrayList<Departamento>buscardepartamento(String criterio){			
		ArrayList<Departamento> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("") ){
		query = "SELECT * FROM departamento as dep where dep.estado='A'";
		}
		else{
		query = "SELECT * FROM departamento as dep where dep.estado='A' and (dep.descripcion like '%" + criterio + "%' ) order by dep.descripcion";
			
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
		
		Departamento categorias= null;				
		lista= new ArrayList<Departamento>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				categorias= new Departamento();
				categorias.setId_tipodepartamento(resultados.getInt("idTipoDepartamento"));
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

	public boolean EliminardepartamentoLogico(Departamento categorias){
		boolean resultado = false;
		String sql="";
			Connection con = null;							
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
	
		sql = "UPDATE departamento SET estado=?"
			+ " where idTipoDepartamento=? ";

		
		System.out.println(sql);
		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, categorias.getEstado());
			sentencia.setInt(2, categorias.getId_tipodepartamento());
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
	
	public ListModelList<Departamento> cargar_departamentos(){
		Statement state = null;
		Integer cont = 0;
		ResultSet resultado = null;
		ListModelList<Departamento> departamentos = new ListModelList<Departamento>();
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return departamentos;}
		try{
		state = (Statement) con.createStatement();
		resultado = state.executeQuery("select * from departamento where estado = 'A';");
		
				while(resultado.next()){
				cont = cont + 1;
				Departamento categorias= new Departamento();
				categorias.setId_tipodepartamento(resultado.getInt("idTipoDepartamento"));
				categorias.setDescripcion(resultado.getString("descripcion"));
				departamentos.add(categorias);
			}
		if(cont>0){
			return departamentos;
		}else{
			return null;
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
