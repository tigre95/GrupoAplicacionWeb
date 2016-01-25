package com.tareas.controlador;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Checkbox;
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

public class NuevoPermisoControlador2 extends GenericForwardComposer<Component>{
	@Wire
	Button button_opcion;
	Combobox cmb_tipo;
	Checkbox Pers_nuevo, Pers_editar, Pers_eliminar, Pers_buscar,
	Tar_nuevo, Tar_editar, Tar_eliminar, Tar_buscar, 
	tjef_nuevo, tjef_editar, tjef_eliminar, tjef_buscar,
	Perm_nuevo, Perm_editar, Perm_eliminar, Perm_buscar,
	Cons_nuevo, Cons_editar, Cons_eliminar, Cons_buscar,
	Rep_nuevo, Rep_editar, Rep_eliminar, Rep_buscar;
	
	String opcion;
	usuarios usuario;
	permisos permiso_personas = null;
	permisos permiso_tareas = null;
	permisos permiso_tareasjefe = null;
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
		if(opcion.equals("nuevo")){
			button_opcion.setLabel("Guardar");
			cargarComboRolesNuevo();
		}
		if(opcion.equals("buscar")){
			button_opcion.setVisible(false);
			cargarComboRoles();
		}
		if(opcion.equals("editar")){
			button_opcion.setLabel("Actualizar");
			cargarComboRoles();
			cambiar_permisos_defecto();
		}		
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
				respuesta = dbpermisos.guardarpermisos(permiso_tareasjefe);
				respuesta = dbpermisos.guardarpermisos(permiso_tareas);
				respuesta = dbpermisos.guardarpermisos(permiso_reportes);
				alert("Guardado exitosamente!!!");
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
					respuesta = dbpermisos.actualizarpermisos(permiso_tareasjefe);
					respuesta = dbpermisos.actualizarpermisos(permiso_tareas);
					respuesta = dbpermisos.actualizarpermisos(permiso_reportes);
					alert("Actualizado exitosamente!!!");
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
			cmb_tipo.setSelectedText(1, 1, "administrador", true);
		}else{
			alert("no existen temas");
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
	
	public void cambiar_permisos(){
		permiso_personas = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "personas");
		permiso_tareas = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "empleadostareas");
		permiso_tareasjefe = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "tareasjefe");
		permiso_permisos = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "permisos");
		permiso_consultas = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "consultas");
		permiso_reportes = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "reportes");
		permiso_configuraciones = dbpermisos.mostrarpermisos(tipos_usuarios.get(cmb_tipo.getSelectedIndex()).getId_tipousuario(), "configuracion");
		if(permiso_personas.getCrear()==0){
			Pers_nuevo.setChecked(false);
		}else{
			Pers_nuevo.setChecked(true);
		}
		if(permiso_personas.getBuscar()==0){
			Pers_buscar.setChecked(false);
		}else{
			Pers_buscar.setChecked(true);
		}
		if(permiso_personas.getEditar()==0){
			Pers_editar.setChecked(false);
		}else{
			Pers_editar.setChecked(true);
		}
		if(permiso_personas.getEliminar()==0){
			Pers_eliminar.setChecked(false);
		}else{
			Pers_eliminar.setChecked(true);
		}
		
		if(permiso_tareas.getCrear()==0){
			Tar_nuevo.setChecked(false);
		}else{
			Tar_nuevo.setChecked(true);
		}
		if(permiso_tareas.getBuscar()==0){
			Tar_buscar.setChecked(false);
		}else{
			Tar_buscar.setChecked(true);
		}
		if(permiso_tareas.getEditar()==0){
			Tar_editar.setChecked(false);
		}else{
			Tar_editar.setChecked(true);
		}
		if(permiso_tareas.getEliminar()==0){
			Tar_eliminar.setChecked(false);
		}else{
			Tar_eliminar.setChecked(true);
		}
		
		if(permiso_tareasjefe.getCrear()==0){
			tjef_nuevo.setChecked(false);
		}else{
			tjef_nuevo.setChecked(true);
		}
		if(permiso_tareasjefe.getBuscar()==0){
			tjef_buscar.setChecked(false);
		}else{
			tjef_buscar.setChecked(true);
		}
		if(permiso_tareasjefe.getEditar()==0){
			tjef_editar.setChecked(false);
		}else{
			tjef_editar.setChecked(true);
		}
		if(permiso_tareasjefe.getEliminar()==0){
			tjef_eliminar.setChecked(false);
		}else{
			tjef_eliminar.setChecked(true);
		}
		
		if(permiso_permisos.getCrear()==0){
			Perm_nuevo.setChecked(false);
		}else{
			Perm_nuevo.setChecked(true);
		}
		if(permiso_permisos.getBuscar()==0){
			Perm_buscar.setChecked(false);
		}else{
			Perm_buscar.setChecked(true);
		}
		if(permiso_permisos.getEditar()==0){
			Perm_editar.setChecked(false);
		}else{
			Perm_editar.setChecked(true);
		}
		if(permiso_permisos.getEliminar()==0){
			Perm_eliminar.setChecked(false);
		}else{
			Perm_eliminar.setChecked(true);
		}
		
		if(permiso_consultas.getCrear()==0){
			Cons_nuevo.setChecked(false);
		}else{
			Cons_nuevo.setChecked(true);
		}
		if(permiso_consultas.getBuscar()==0){
			Cons_buscar.setChecked(false);
		}else{
			Cons_buscar.setChecked(true);
		}
		if(permiso_consultas.getEditar()==0){
			Cons_editar.setChecked(false);
		}else{
			Cons_editar.setChecked(true);
		}
		if(permiso_consultas.getEliminar()==0){
			Cons_eliminar.setChecked(false);
		}else{
			Cons_eliminar.setChecked(true);
		}
		
		if(permiso_reportes.getCrear()==0){
			Rep_nuevo.setChecked(false);
		}else{
			Rep_nuevo.setChecked(true);
		}
		if(permiso_reportes.getBuscar()==0){
			Rep_buscar.setChecked(false);
		}else{
			Rep_buscar.setChecked(true);
		}
		if(permiso_reportes.getEditar()==0){
			Rep_editar.setChecked(false);
		}else{
			Rep_editar.setChecked(true);
		}
		if(permiso_reportes.getEliminar()==0){
			Rep_eliminar.setChecked(false);
		}else{
			Rep_eliminar.setChecked(true);
		}
	}
	
	public void cambiar_permisos_defecto(){
		permiso_personas = dbpermisos.mostrarpermisos(1, "personas");
		permiso_tareas = dbpermisos.mostrarpermisos(1, "empleadostareas");
		permiso_tareasjefe = dbpermisos.mostrarpermisos(1, "tareasjefe");
		permiso_permisos = dbpermisos.mostrarpermisos(1, "permisos");
		permiso_consultas = dbpermisos.mostrarpermisos(1, "consultas");
		permiso_reportes = dbpermisos.mostrarpermisos(1, "reportes");
		permiso_configuraciones = dbpermisos.mostrarpermisos(1, "configuracion");
		if(permiso_personas.getCrear()==0){
			Pers_nuevo.setChecked(false);
		}else{
			Pers_nuevo.setChecked(true);
		}
		if(permiso_personas.getBuscar()==0){
			Pers_buscar.setChecked(false);
		}else{
			Pers_buscar.setChecked(true);
		}
		if(permiso_personas.getEditar()==0){
			Pers_editar.setChecked(false);
		}else{
			Pers_editar.setChecked(true);
		}
		if(permiso_personas.getEliminar()==0){
			Pers_eliminar.setChecked(false);
		}else{
			Pers_eliminar.setChecked(true);
		}
		
		if(permiso_tareas.getCrear()==0){
			Tar_nuevo.setChecked(false);
		}else{
			Tar_nuevo.setChecked(true);
		}
		if(permiso_tareas.getBuscar()==0){
			Tar_buscar.setChecked(false);
		}else{
			Tar_buscar.setChecked(true);
		}
		if(permiso_tareas.getEditar()==0){
			Tar_editar.setChecked(false);
		}else{
			Tar_editar.setChecked(true);
		}
		if(permiso_tareas.getEliminar()==0){
			Tar_eliminar.setChecked(false);
		}else{
			Tar_eliminar.setChecked(true);
		}
		
		if(permiso_tareasjefe.getCrear()==0){
			tjef_nuevo.setChecked(false);
		}else{
			tjef_nuevo.setChecked(true);
		}
		if(permiso_tareasjefe.getBuscar()==0){
			tjef_buscar.setChecked(false);
		}else{
			tjef_buscar.setChecked(true);
		}
		if(permiso_tareasjefe.getEditar()==0){
			tjef_editar.setChecked(false);
		}else{
			tjef_editar.setChecked(true);
		}
		if(permiso_tareasjefe.getEliminar()==0){
			tjef_eliminar.setChecked(false);
		}else{
			tjef_eliminar.setChecked(true);
		}
		
		if(permiso_permisos.getCrear()==0){
			Perm_nuevo.setChecked(false);
		}else{
			Perm_nuevo.setChecked(true);
		}
		if(permiso_permisos.getBuscar()==0){
			Perm_buscar.setChecked(false);
		}else{
			Perm_buscar.setChecked(true);
		}
		if(permiso_permisos.getEditar()==0){
			Perm_editar.setChecked(false);
		}else{
			Perm_editar.setChecked(true);
		}
		if(permiso_permisos.getEliminar()==0){
			Perm_eliminar.setChecked(false);
		}else{
			Perm_eliminar.setChecked(true);
		}
		
		if(permiso_consultas.getCrear()==0){
			Cons_nuevo.setChecked(false);
		}else{
			Cons_nuevo.setChecked(true);
		}
		if(permiso_consultas.getBuscar()==0){
			Cons_buscar.setChecked(false);
		}else{
			Cons_buscar.setChecked(true);
		}
		if(permiso_consultas.getEditar()==0){
			Cons_editar.setChecked(false);
		}else{
			Cons_editar.setChecked(true);
		}
		if(permiso_consultas.getEliminar()==0){
			Cons_eliminar.setChecked(false);
		}else{
			Cons_eliminar.setChecked(true);
		}
		
		if(permiso_reportes.getCrear()==0){
			Rep_nuevo.setChecked(false);
		}else{
			Rep_nuevo.setChecked(true);
		}
		if(permiso_reportes.getBuscar()==0){
			Rep_buscar.setChecked(false);
		}else{
			Rep_buscar.setChecked(true);
		}
		if(permiso_reportes.getEditar()==0){
			Rep_editar.setChecked(false);
		}else{
			Rep_editar.setChecked(true);
		}
		if(permiso_reportes.getEliminar()==0){
			Rep_eliminar.setChecked(false);
		}else{
			Rep_eliminar.setChecked(true);
		}
	}
	
	public void onChange$cmb_tipo(){
		cambiar_permisos();
	}
	
	public void onClick$Pers_nuevo(){
		if(Pers_nuevo.isChecked()==true){
			permiso_personas.setCrear(1);
		}else{
			permiso_personas.setCrear(0);
		}
	}
	
	public void onClick$Pers_editar(){
		if(Pers_editar.isChecked()==true){
			permiso_personas.setEditar(1);
		}else{
			permiso_personas.setEditar(0);
		}
	}
	
	public void onClick$Pers_eliminar(){
		if(Pers_eliminar.isChecked()==true){
			permiso_personas.setEliminar(1);
		}else{
			permiso_personas.setEliminar(0);
		}
	}
	
	public void onClick$Pers_buscar(){
		if(Pers_buscar.isChecked()==true){
			permiso_personas.setBuscar(1);
		}else{
			permiso_personas.setBuscar(0);
		}
	}

	public void onClick$Tar_nuevo(){
		if(Tar_nuevo.isChecked()==true){
			permiso_tareas.setCrear(1);
		}else{
			permiso_tareas.setCrear(0);
		}
	}
	
	public void onClick$Tar_buscar(){
		if(Tar_buscar.isChecked()==true){
			permiso_tareas.setBuscar(1);
		}else{
			permiso_tareas.setBuscar(0);
		}
	}
	
	public void onClick$Tar_editar(){
		if(Tar_editar.isChecked()==true){
			permiso_tareas.setEditar(1);
		}else{
			permiso_tareas.setEditar(0);
		}
	}
	
	public void onClick$Tar_eliminar(){
		if(Tar_eliminar.isChecked()==true){
			permiso_tareas.setEliminar(1);
		}else{
			permiso_tareas.setEliminar(0);
		}
	}
	
	public void onClick$tjef_nuevo(){
		if(tjef_nuevo.isChecked()==true){
			permiso_tareasjefe.setCrear(1);
		}else{
			permiso_tareasjefe.setCrear(0);
		}
	}
	
	public void onClick$tjef_buscar(){
		if(tjef_buscar.isChecked()==true){
			permiso_tareasjefe.setBuscar(1);
		}else{
			permiso_tareasjefe.setBuscar(0);
		}
	}
	
	public void onClick$tjef_editar(){
		if(tjef_editar.isChecked()==true){
			permiso_tareasjefe.setEditar(1);
		}else{
			permiso_tareasjefe.setEditar(0);
		}
	}
	
	public void onClick$tjef_eliminar(){
		if(tjef_eliminar.isChecked()==true){
			permiso_tareasjefe.setEliminar(1);
		}else{
			permiso_tareasjefe.setEliminar(0);
		}
	}
	
	public void onClick$Perm_nuevo(){
		if(Perm_nuevo.isChecked()==true){
			permiso_permisos.setCrear(1);
		}else{
			permiso_permisos.setCrear(0);
		}
	}
	
	public void onClick$Perm_editar(){
		if(Perm_editar.isChecked()==true){
			permiso_permisos.setEditar(1);
		}else{
			permiso_permisos.setEditar(0);
		}
	}
	
	public void onClick$Perm_eliminar(){
		if(Perm_eliminar.isChecked()==true){
			permiso_permisos.setEliminar(1);
		}else{
			permiso_permisos.setEliminar(0);
		}
	}
	
	public void onClick$Perm_buscar(){
		if(Perm_buscar.isChecked()==true){
			permiso_permisos.setBuscar(1);
		}else{
			permiso_permisos.setBuscar(0);
		}
	}
	
	public void onClick$Cons_nuevo(){
		if(Cons_nuevo.isChecked()==true){
			permiso_consultas.setCrear(1);
		}else{
			permiso_consultas.setCrear(0);
		}
	}
	
	public void onClick$Cons_buscar(){
		if(Cons_buscar.isChecked()==true){
			permiso_consultas.setBuscar(1);
		}else{
			permiso_consultas.setBuscar(0);
		}
	}
	
	public void onClick$Cons_editar(){
		if(Cons_editar.isChecked()==true){
			permiso_consultas.setEditar(1);
		}else{
			permiso_consultas.setEditar(0);
		}
	}
	
	public void onClick$Cons_eliminar(){
		if(Cons_eliminar.isChecked()==true){
			permiso_consultas.setEliminar(1);
		}else{
			permiso_consultas.setEliminar(0);
		}
	}
	
	public void onClick$Rep_nuevo(){
		if(Rep_nuevo.isChecked()==true){
			permiso_reportes.setCrear(1);
		}else{
			permiso_reportes.setCrear(0);
		}
	}
	
	public void onClick$Rep_buscar(){
		if(Rep_buscar.isChecked()==true){
			permiso_reportes.setBuscar(1);
		}else{
			permiso_reportes.setBuscar(0);
		}
	}
	
	public void onClick$Rep_eliminar(){
		if(Rep_eliminar.isChecked()==true){
			permiso_reportes.setEliminar(1);
		}else{
			permiso_reportes.setEliminar(0);
		}
	}
	
	public void onClick$Rep_editar(){
		if(Rep_editar.isChecked()==true){
			permiso_reportes.setEditar(1);
		}else{
			permiso_reportes.setEditar(0);
		}
	}
}
