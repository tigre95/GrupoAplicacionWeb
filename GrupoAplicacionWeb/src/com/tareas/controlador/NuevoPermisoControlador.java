package com.tareas.controlador;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

import com.controlador.entidades.permisos;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBTiposUsuarios;

import java.sql.SQLException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class NuevoPermisoControlador extends GenericForwardComposer<Component>{

	@Wire
	Button button_opcion;
	Combobox cmb_tipo;
	
	String opcion;
	usuarios usuario;
	permisos permiso_personas = null;
	permisos permiso_tareas = null;
	permisos permiso_departamentos = null;
	permisos permiso_permisos = null;
	permisos permiso_consultas = null;
	permisos permiso_reportes = null;
	permisos permiso_configuraciones = null;	
	DBPermisos dbpermisos = new DBPermisos();
	ListModelList<tiposusuarios> tipos_usuarios;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Session session = Sessions.getCurrent();
		opcion = (String) session.getAttribute("opcion_permisos");
		usuario = (usuarios) session.getAttribute("usuario");
			cargarComboRolesNuevo();
			cargarComboRoles();
		
	}
	
	public void onClick$button_opcion(){
		DBPermisos dbpermisos = new DBPermisos();
		if(button_opcion.getLabel().equals("Guardar")){
			int respuesta = 0;
			try {
				respuesta = dbpermisos.guardarpermisos(permiso_personas);
				respuesta = dbpermisos.guardarpermisos(permiso_permisos);
				respuesta = dbpermisos.guardarpermisos(permiso_configuraciones);
				respuesta = dbpermisos.guardarpermisos(permiso_consultas);
				respuesta = dbpermisos.guardarpermisos(permiso_departamentos);
				respuesta = dbpermisos.guardarpermisos(permiso_tareas);
				respuesta = dbpermisos.guardarpermisos(permiso_reportes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(button_opcion.getLabel().equals("Actualizar")){
				int respuesta = 0;
				try {
					respuesta = dbpermisos.actualizarpermisos(permiso_personas);
					respuesta = dbpermisos.actualizarpermisos(permiso_permisos);
					respuesta = dbpermisos.actualizarpermisos(permiso_configuraciones);
					respuesta = dbpermisos.actualizarpermisos(permiso_consultas);
					respuesta = dbpermisos.actualizarpermisos(permiso_departamentos);
					respuesta = dbpermisos.actualizarpermisos(permiso_tareas);
					respuesta = dbpermisos.actualizarpermisos(permiso_reportes);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void cargarComboRoles(){
		
		DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
		tipos_usuarios = dbtiposusuarios.listartipousuarios();
		if(tipos_usuarios.isEmpty()==false){
			cmb_tipo.setModel(tipos_usuarios);
		}else{
			alert("no existen roles");
		}
	}
	
	public void cargarComboRolesNuevo(){
		
		DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
		tipos_usuarios = dbtiposusuarios.listartipousuariossinpermisos();
		if(tipos_usuarios == null){
			alert("no existen roles");
		}else{
			if(tipos_usuarios.isEmpty()==false){
				cmb_tipo.setModel(tipos_usuarios);
			}else{
				alert("no existen roles");
			}
		}
	}
}
