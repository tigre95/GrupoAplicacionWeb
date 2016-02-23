package com.tareas.modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;

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
	
	
	public ArrayList<NivelTareas> listarTareasnivel() {
		
		ArrayList<NivelTareas> tipo_usuario = new ArrayList<NivelTareas>();
		
		NivelTareas tipo_user = null;
		//busqueda de existencia de usuario para conectarse a la Base de Datos
		DBManager dbm= new DBManager();
		Connection con= dbm.getConection();
		if(con==null){
			System.out.println("error de Conexion");
			return tipo_usuario;
		}
		//sentencia a ejecutar
		Statement sentencia;
		
		//objeto para almacenar resultados
		ResultSet resultados;
		
		String sql="SELECT * FROM niveltareas as niv where niv.estado='A' order by niv.descripcion" ;
		//ejecutar sentencia
		try {
			sentencia=con.createStatement();
			resultados=sentencia.executeQuery(sql);
			//recorrer el ResultSet=conjunto de registros que se obtiene como resultado de la sentencia
			while(resultados.next()){
				tipo_user=new NivelTareas();
				tipo_user.setId_tipotarea(resultados.getInt("id_tipotarea"));
				tipo_user.setDescripcion(resultados.getString("descripcion"));
					tipo_user.setEstado(resultados.getString("estado"));
								
				tipo_usuario.add(tipo_user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tipo_usuario;
	}

public ListModelList<NivelTareas> cargar_NivelTareas(){
	Statement state = null;
	Integer cont = 0;
	ResultSet resultado = null;
	ListModelList<NivelTareas> niveltareas = new ListModelList<NivelTareas>();
	DBManager dbmanager = new DBManager();
	Connection con = dbmanager.getConection();
	if(con==null){return niveltareas;}
	try{
	state = (Statement) con.createStatement();
	resultado = state.executeQuery("select * from niveltareas where estado = 'A';");
	
			while(resultado.next()){
			cont = cont + 1;
			NivelTareas niveltarea = new NivelTareas();
			niveltarea.setId_tipotarea(resultado.getInt(1));
			niveltarea.setDescripcion(resultado.getString(2));
			niveltarea.setEstado(resultado.getString(3));
			niveltareas.add(niveltarea);
		}
	if(cont>0){
		return niveltareas;
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


