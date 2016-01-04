package com.tareas.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;

import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.tiposusuarios;

public class DBTiposUsuarios {

	public tiposusuarios mostrartipousuarios(int id_tipo){			
		tiposusuarios tipousuario= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from tiposusuarios where estado = 'A' and"
		+ " id_tipousuario =" + id_tipo + ";";		
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
				tiposusuarios tipousuario2 = new tiposusuarios();
				tipousuario2.setId_tipousuario(resultados.getInt(1));
				tipousuario2.setDescripcion(resultados.getString(2));
				tipousuario2.setEstado(resultados.getString(3));
				tipousuario = tipousuario2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return tipousuario;
	}
	public DBTiposUsuarios() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<tiposusuarios>ValidarTipoUsuarios(String criterio, int criterio2){			
		ArrayList<tiposusuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM tiposusuarios as tip where tip.estado='A' and (tip.descripcion = '" + criterio + "' and tip.id_tipousuario <>"+criterio2+") order by tip.descripcion";
		
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		tiposusuarios categorias= null;				
		lista= new ArrayList<tiposusuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				categorias= new tiposusuarios();
				categorias.setId_tipousuario(resultados.getInt("id_tipousuario"));
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

	public boolean editarTipoUsuario(tiposusuarios tiposusuarios){
		boolean resultado = false;
		Connection con = null;
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
		String sql = "UPDATE tiposusuarios SET  descripcion=? where id_tipousuario=?";

		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, tiposusuarios.getDescripcion());
			sentencia.setInt(2, tiposusuarios.getId_tipousuario());
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
	

	
	public boolean creartipousuario(tiposusuarios tiposusuarios){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();							
		String sql ="INSERT INTO tiposusuarios (descripcion,estado) VALUES (?,'A')";			
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tiposusuarios.getDescripcion());
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
				System.out.println("Error al crear una nuevo  Tipo Usuarios" + e.getMessage());
			}
			return resultado;
			}
	
	
	public ArrayList<tiposusuarios>buscartipoUsuarionormal(){			
		ArrayList<tiposusuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM tiposusuarios where estado='A'";
			
			
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		tiposusuarios tiposusuarios= null;				
		lista= new ArrayList<tiposusuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				tiposusuarios= new tiposusuarios();
				tiposusuarios.setId_tipousuario(resultados.getInt("id_tipousuario"));
				tiposusuarios.setDescripcion(resultados.getString("descripcion"));
				lista.add(tiposusuarios);
								
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
	
	
	
	
	public ArrayList<tiposusuarios>buscartipoUsuario(String criterio){			
		ArrayList<tiposusuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("") ){
		query = "SELECT * FROM tiposusuarios where estado='A'";
			
			
		}
		else{
		query = "SELECT * FROM tiposusuarios as tip where tip.estado='A' and (tip.descripcion like '%" + criterio + "%' ) order by tip.descripcion";
			
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
		
		tiposusuarios tiposusuarios= null;				
		lista= new ArrayList<tiposusuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				tiposusuarios= new tiposusuarios();
				tiposusuarios.setId_tipousuario(resultados.getInt("id_tipousuario"));
				tiposusuarios.setDescripcion(resultados.getString("descripcion"));
				lista.add(tiposusuarios);
								
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
	
	
	public boolean EliminarTipoUSuario(tiposusuarios tiposusuarios){
		boolean resultado = false;
		String sql="";
			Connection con = null;							
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
	
		sql = "UPDATE tiposusuarios SET estado=?"
			+ " where id_tipousuario=? ";

		
		System.out.println(sql);
		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, tiposusuarios.getEstado());
			sentencia.setInt(2, tiposusuarios.getId_tipousuario());
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
	
	public ListModelList<tiposusuarios> listartipousuarios(){			
		ListModelList<tiposusuarios> tipousuario= new ListModelList<tiposusuarios>();
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
		}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from tiposusuarios where estado = 'A';";		
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
				tiposusuarios tipousuario2 = new tiposusuarios();
				tipousuario2.setId_tipousuario(resultados.getInt(1));
				tipousuario2.setDescripcion(resultados.getString(2));
				tipousuario2.setEstado(resultados.getString(3));
				tipousuario.add(tipousuario2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return tipousuario;
	}
	
	public ListModelList<tiposusuarios> listartipousuariossinpermisos(){			
		ListModelList<tiposusuarios> tipousuario= new ListModelList<tiposusuarios>();
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
		}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
				//EncriptarPassword(pass);
		String query="select * from tiposusuarios tu where tu.estado = 'A' and not exists(select * from permisos p"
				+ " where p.id_tipousuario = tu.id_tipousuario and p.estado = 'A');";		
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
				tiposusuarios tipousuario2 = new tiposusuarios();
				tipousuario2.setId_tipousuario(resultados.getInt(1));
				tipousuario2.setDescripcion(resultados.getString(2));
				tipousuario2.setEstado(resultados.getString(3));
				tipousuario.add(tipousuario2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		if (tipousuario.isEmpty()==true){
			return null;
		}else{
			return tipousuario;
		}
	}
	public ArrayList<TipoUsuarios>CargarTipoUsuarios(){			
		ArrayList<TipoUsuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM tiposusuarios as tu where tu.estado='A' and ((tu.id_tipousuario=1) or (tu.id_tipousuario=3))";
			
		System.out.println(query);
		
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		TipoUsuarios tus= null;				
		lista= new ArrayList<TipoUsuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				tus= new TipoUsuarios();
				tus.setId_tipousuario(resultados.getInt("id_tipousuario"));
				tus.setDescripcion(resultados.getString("descripcion"));
				tus.setEstado(resultados.getString("estado"));
				lista.add(tus);
								
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
	

