package com.tareas.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zul.ListModelList;

import com.controlador.entidades.tareas;


public class DBTareas {

	
	
	public ListModelList<tareas> historial_tareas(int id_persona){
		int cont = 0;
		ListModelList<tareas> lista_tareas = new ListModelList<tareas>();
		ResultSet resultado = null;
		DBManager dbmanager = new DBManager();
		Connection conexion = dbmanager.getConection();
		if (conexion == null) {
			System.out.println("Conexion no se pudo realizar");
			return null;
		}
		Statement state = null;
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery("SELECT * FROM tareas where estado = 'A' and "
					+ "id_persona = "+ id_persona +" ;");
			while(resultado.next()){
				cont = cont + 1;
				tareas tarea1 = new tareas();
				tarea1.setId_tarea(resultado.getInt(1));
				tarea1.setId_tipotarea(resultado.getInt(2));
				tarea1.setId_persona(resultado.getInt(3));
				tarea1.setId_persotarea(resultado.getInt(4));
				tarea1.setDescripcion(resultado.getString(5));
				tarea1.setArchivo(resultado.getString(6));
				tarea1.setComentario(resultado.getString(7));
				tarea1.setFecha_inicio(resultado.getString(8));
				tarea1.setFecha_fin(resultado.getString(9));
				tarea1.setEstado(resultado.getString(10));
				tarea1.setEstado_tarea(resultado.getInt(11));
				lista_tareas.add(tarea1);
			}
			if(cont>0){
				return lista_tareas;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DBTareas() {
		// TODO Auto-generated constructor stub
	}

}
