
	
package com.tareas.modelos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.commons.codec.binary.Hex;

import com.cambio.contraseña.Encriptacion;
import com.controlador.entidades.usuarios;
public class DBDatosUsuario {

	public String EncriptarPassword(String dpassword){
		Encriptacion e=new Encriptacion("Encriptar");
		String passwordEncriptado=e.encrypt(dpassword);
		return passwordEncriptado;
	}
	public usuarios autenticarUsuario(String user, String dpasswrod){			
		//MessageDigest md=null;
		//String encriptado=null;

		usuarios usuario= null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return null;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
//		try {				
//			md=MessageDigest.getInstance("SHA-1");//<- este es el q estoy usando :)
//			md.update(pass.getBytes());
//			byte[] mb = md.digest();
//			mb = md.digest();
//			encriptado=String.valueOf(Hex.encodeHex(mb));
//		}
//		catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(encriptado);
		String passwordenc=EncriptarPassword(dpasswrod);
		System.out.println("encriptacdo= "+passwordenc);
		
		
	//	String passwordenc=pass;
				//EncriptarPassword(pass);
		String query="select u.id_usuario, u.id_tipousuario, u.id_persona, u.estado from datosusuarios as du,"
				+ " personas as p, usuarios as u, tiposusuarios as tu"
		+ " where du.estado = 'A' and p.estado = 'A' and u.estado = 'A' and tu.estado = 'A' and "
		+ "du.id_usuario=u.id_usuario and u.id_persona=p.id_persona and u.id_tipousuario=tu.id_tipousuario and "
		+ " du.usuario ='" + user + "' and du.contraseña = '" + passwordenc + "'";		
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
				usuarios usuario2 = new usuarios();
				usuario2.setId_usuario(resultados.getInt(1));
				usuario2.setId_tipousuario(resultados.getInt(2));
				usuario2.setId_persona(resultados.getInt(3));
				usuario2.setEstado(resultados.getString(4));
				usuario = usuario2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return usuario;
	}
}
	
	