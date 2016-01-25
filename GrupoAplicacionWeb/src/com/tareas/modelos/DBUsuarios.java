package com.tareas.modelos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.TipoUsuario;
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.controlador.entidades.departamentodb;




public class DBUsuarios {
	public boolean CrearUsuario(Usuariodb us){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="call sp_ingresar_usuarios(?,?,?,?,?,?,?,?,?);";			
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			stmt.setString(1, us.getNombres());
			stmt.setInt(2,us.getIdTipoDepartamento());
			stmt.setString(3,us.getApellidos());
			stmt.setString(4,us.getCedula());
			stmt.setString(5,us.getEmail());
			stmt.setString(6,us.getDireccion());
			
			stmt.setInt(7,us.getId_tipousuario());
			stmt.setString(8,us.getAlias());
			stmt.setString(9,us.getDpasssword());
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0){
				con.commit();
				resultado = true;
			}
			else {
	   		    System.out.println("No se puedo crear nuevo usuario");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al crear nuevo usuario" + e.getMessage());
			}
			return resultado;
	}
	public boolean editareliminarUsuario(Usuariodb us,int opcion){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="call sp_modulo_usuarios(?,?,?,?,?,?,?,?,?,?,?);";
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			   stmt.setInt(1, us.getId_persona());
			stmt.setString(2, us.getNombres());
			stmt.setString(3,us.getApellidos());
			stmt.setString(4,us.getCedula());
			stmt.setString(5,us.getEmail());
			stmt.setString(6,us.getDireccion());
			stmt.setInt(7,us.getIdTipoDepartamento());
			stmt.setInt(8,us.getId_tipousuario());
			stmt.setString(9,us.getAlias());
			
			stmt.setString(10,us.getDpasssword());
			stmt.setInt(11,opcion);
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0 ){
				con.commit();
				resultado = true;
			}
			else {
	   		    System.out.println("No se puedo eliminar/editar usuario");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al eliminar/editar usuario" + e.getMessage());
			}
			return resultado;
	}


public boolean validarUsuario(String cedula, String user){
	boolean resultado = false;
	Connection con =null;
	int existe=0;
	//PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();
	String sql ="select p.id_persona from personas as p,usuarios as u, datosusuarios as du where p.estado='A' and u.estado='A' and du.estado='A' and p.id_persona=u.id_persona and u.id_usuario=du.id_usuario and (p.cedula ='"+cedula+"' or du.usuario='"+user+"');";
	System.out.println(sql);
	PreparedStatement sentencia;
	ResultSet resul= null;
	try {
		con.setAutoCommit(false);
	    sentencia = con.prepareStatement(sql);
		resul = sentencia.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		while (resul.next()){
			existe=existe+1;
			System.out.println(""+existe);
		}
		if(existe>=1){
			resultado = true;
			con.commit();
		}else{
			con.rollback();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Error en recorrer los resultados");
	}
	
	
	return resultado;
}



public boolean validarEliminacion(){
	boolean resultado = false;
	Connection con =null;
	int existe=0;
	//PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();
	String sql ="select id_usuario from usuarios where estado='A' and id_tipousuario=14";
	System.out.println(sql);
	PreparedStatement sentencia;
	ResultSet resul= null;
	try {
		con.setAutoCommit(false);
	    sentencia = con.prepareStatement(sql);
		resul = sentencia.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		while (resul.next()){
			existe=existe+1;
			System.out.println(""+existe);
		}
		if(existe>1){
			resultado = true;
			con.commit();
		}else{
			con.rollback();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Error en recorrer los resultados");
	}
	
	
	return resultado;
}

public boolean validarEdicion(Usuariodb us){
	boolean resultado = false;
	Connection con =null;
	int existe=0;
	//PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();
	String sql ="select u.id_usuario from usuarios as u,personas as p where u.estado='A' and p.estado='A' and p.cedula='"+us.getCedula()+"' and p.id_persona<>"+us.getId_persona()+";";
	System.out.println(sql);
	PreparedStatement sentencia;
	ResultSet resul= null;
	try {
		con.setAutoCommit(false);
	    sentencia = con.prepareStatement(sql);
		resul = sentencia.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		while (resul.next()){
			existe=existe+1;
			System.out.println(""+existe);
		}
		if(existe>0){
			resultado = true;
			con.commit();
		}else{
			con.rollback();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Error en recorrer los resultados");
	}
	
	
	return resultado;
}

public ArrayList<Usuariodb>buscarUsuarios(String criterio){			
	ArrayList<Usuariodb> lista= null;
	//conectar a la bd
	DBManager dbmanager = new DBManager();
	Connection con = dbmanager.getConection();
	if(con==null){return lista;}
	
	Statement sentencia;
	ResultSet resultados= null;
	
	String query="";
	if(criterio.equals("") ){
		query = "SELECT p.nombres as nombres,p.apellidos as apellidos, du.usuario as alias,tu.descripcion as descrip , dp.descripcion as descripciondep   ,p.cedula as cedula,p.email as email,p.direccion as direccion FROM personas as p,usuarios as u, tiposusuarios as tu,datosusuarios as du ,departamento as dp where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and du.id_usuario=u.id_usuario and dp.idTipoDepartamento=p.id_departamento and p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A'and dp.estado='A' order by tu.descripcion";
	}
	else{
	query = "SELECT p.nombres as nombres,p.apellidos as apellidos, du.usuario as alias,tu.descripcion as descrip , dp.descripcion as descripciondep ,p.cedula as cedula ,p.email as email,p.direccion as direccion FROM personas as p,usuarios as u, tiposusuarios as tu,datosusuarios as du ,departamento as dp where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and du.id_usuario=u.id_usuario and dp.idTipoDepartamento=p.id_departamento and p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A'and dp.estado='A' and (p.nombres like '%"+criterio+"%' or p.apellidos like '%"+criterio+"%' or tu.descripcion like '%"+criterio+"%' or du.usuario like '%"+criterio+"%' or p.cedula like '%"+criterio+"%' or p.direccion like '%"+criterio+"%') order by tu.descripcion";
		
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
	
	Usuariodb us= null;				
	lista= new ArrayList<Usuariodb>();
	
	//recorrer los resultados
	try {
		while (resultados.next()){
			us= new Usuariodb();
			
			
			us.setNombres(resultados.getString("nombres"));
			us.setApellidos(resultados.getString("apellidos"));
			us.setAlias(resultados.getString("alias"));
			us.setDescripcionTU(resultados.getString("descrip"));
			//departamentodb dep = new departamentodb();
			//dep.setDescripciondp(resultados.getString("descripciondep"));
		//	us.setDepartamentodb(dep);
			us.setDescripciondep(resultados.getString("descripciondep"));
			
			us.setCedula(resultados.getString("cedula"));
			us.setEmail(resultados.getString("email"));
			us.setDireccion(resultados.getString("direccion"));
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

public int ObtenerIdPersona(String cedula){
	int id=0;
	DBManager dbmanager = new DBManager();
	Connection con = dbmanager.getConection();
	if(con==null){id=0;}
	String query= "select id_persona from personas where estado='A' and cedula='"+cedula+"'";
	System.out.println(query);
	Statement sentencia;
	ResultSet resultados= null;
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
			id=resultados.getInt("id_persona");
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
	return id;		
}



public Usuariodb logonear(String user, String dpasswrod){
	MessageDigest md=null;
	String encriptado=null;
	Usuariodb us=new Usuariodb();
	DBManager dbmanager = new DBManager();
	Connection con = dbmanager.getConection();
	String passwordenc=dpasswrod;
	System.out.println("encriptacdo= "+passwordenc);
	int cont=0;
	try {				
		md=MessageDigest.getInstance("SHA-1");//<- este es el q estoy usando :)
		md.update(dpasswrod.getBytes());
		byte[] mb = md.digest();
		mb = md.digest();
		encriptado=String.valueOf(Hex.encodeHex(mb));
	}
	catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println(encriptado);
	if(con==null){us.setId_persona(0);}
	String query= "select p.id_persona as idpersona,du.usuario as usuario,p.cedula as cedula, p.nombres as nombres,p.apellidos as apellidos,tu.descripcion as descripcion,tu.id_tipousuario as idtipousuario from personas as p,datosusuarios as du,tiposusuarios as tu,usuarios as u where p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A' and p.id_persona=u.id_persona and tu.id_tipousuario=u.id_tipousuario and du.id_usuario=u.id_usuario and u.id_tipousuario<>15 and du.contraseña='"+encriptado+"' and du.usuario='"+user+"'";
	System.out.println(query);
	Statement sentencia;
	ResultSet resultados= null;
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
			cont=cont+1;
			System.out.println("cont: "+cont);
			us.setId_persona(resultados.getInt("idpersona"));
			us.setAlias(resultados.getString("usuario"));
			us.setCedula(resultados.getString("cedula"));
			us.setApellidos(resultados.getString("apellidos"));
			us.setNombres(resultados.getString("nombres"));
			us.setDescripcionTU(resultados.getString("descripcion"));
			us.setId_tipousuario(resultados.getInt("idtipousuario"));
			System.out.println("name: "+us.getNombres());
		}
		
		if(cont>1){
			us.setId_persona(0);
		}else{
			us.setId_persona(1);
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
	return us;
}

public boolean modificarContrasena(int id_persona,String alias, String nuevaContrasena){
    boolean resultado = false;
	Connection con =null;
	PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();
	String sql ="call sp_modificar_datos_usuario(?,?,?);";			
	try {
		con.setAutoCommit(false);								
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id_persona);
		stmt.setString(2,alias);
		stmt.setString(3,nuevaContrasena);
		System.out.println(stmt);
		int numerofilas = stmt.executeUpdate();
		if(numerofilas>0){
			con.commit();
			resultado = true;
		}
		else {
   		    System.out.println("No se puedo modificar datos usuario");
			con.rollback();
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al modificar usuario" + e.getMessage());
		}
		return resultado;
}



public ArrayList<TipoUsuarios> listarTipousuario() {
	
	ArrayList<TipoUsuarios> tipo_usuario = new ArrayList<TipoUsuarios>();
	
	TipoUsuarios tipo_user = null;
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
	
	String sql="select * from tiposusuarios as tu where tu.estado='A'" ;
	//ejecutar sentencia
	try {
		sentencia=con.createStatement();
		resultados=sentencia.executeQuery(sql);
		//recorrer el ResultSet=conjunto de registros que se obtiene como resultado de la sentencia
		while(resultados.next()){
			tipo_user=new TipoUsuarios();
			tipo_user.setId_tipousuario(resultados.getInt("id_tipousuario"));
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



public ArrayList<Departamento> listarSucursal() {
	
	ArrayList<Departamento> sucursal = new ArrayList<Departamento>();
	
	Departamento departamento = null;
	//busqueda de existencia de usuario para conectarse a la Base de Datos
	DBManager dbm= new DBManager();
	Connection con= dbm.getConection();
	if(con==null){
		System.out.println("error de Conexion");
		return sucursal;
	}
	//sentencia a ejecutar
	Statement sentencia;
	
	//objeto para almacenar resultados
	ResultSet resultados;
	
	String sql="select * from departamento as de where de.estado='A'" ;
	//ejecutar sentencia
	try {
		sentencia=con.createStatement();
		resultados=sentencia.executeQuery(sql);
		//recorrer el ResultSet=conjunto de registros que se obtiene como resultado de la sentencia
		while(resultados.next()){
			departamento=new Departamento();
			departamento.setId_tipodepartamento(resultados.getInt("idTipoDepartamento"));
			departamento.setDescripcion(resultados.getString("descripcion"));
			departamento.setEstado(resultados.getString("estado"));
							
			sucursal.add(departamento);
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
	
	return sucursal;
}



}
