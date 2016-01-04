package com.tareas.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.controlador.entidades.permisos;
import com.controlador.entidades.personas;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTiposUsuarios;

public class MenuPrincipal_Controlador extends GenericForwardComposer<Component>{

	//enlazar los componentes de la interfaz
	@Wire
	Label label_usuario;
	Button buttonpersonas, buttonjefe, buttonempleados, buttonpermisos, buttonconsultas, buttonreportes, buttonconfiguraciones;
	Center centro;
	
	//declarar variables
	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	personas persona = null;
	permisos permiso_personas = null;
	permisos permiso_tareas = null;
	permisos permiso_tareasjefe = null;
	permisos permiso_permisos = null;
	permisos permiso_consultas = null;
	permisos permiso_reportes = null;
	permisos permiso_configuraciones = null;
	
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPersonas dbpersonas = new DBPersonas();
	DBPermisos dbpermisos = new DBPermisos();
	
	int roles;
	String tipo= "Usuario: ";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	Session session = Sessions.getCurrent();
	usuario = (usuarios) session.getAttribute("usuario");
	if(usuario!=null){
		tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
		persona = dbpersonas.mostrarpersonas(usuario.getId_persona());
		permiso_personas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "personas");
		permiso_tareas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "empleadostareas");
		permiso_tareasjefe = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "tareasjefe");
		permiso_permisos = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "permisos");
		permiso_consultas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "consultas");
		permiso_reportes = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "reportes");
		permiso_configuraciones = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "configuracion");
		String nombrec= tipo+tipousuario.getDescripcion()+": " + persona.getNombres() +" "+ persona.getApellidos();
		label_usuario.setValue(nombrec);
		if(permiso_personas.getCrear()==0 && permiso_personas.getBuscar()==0 && permiso_personas.getEditar()==0 && permiso_personas.getEliminar()==0){
				buttonpersonas.setVisible(false);
		}else{
			buttonpersonas.setVisible(true);
		}
		if(permiso_tareas.getCrear()==0 && permiso_tareas.getBuscar()==0 && permiso_tareas.getEditar()==0 && permiso_tareas.getEliminar()==0){
			buttonjefe.setVisible(false);
		}else{
			buttonjefe.setVisible(true);
		}
		if(permiso_tareasjefe.getCrear()==0 && permiso_tareasjefe.getBuscar()==0 && permiso_tareasjefe.getEditar()==0 && permiso_tareasjefe.getEliminar()==0){
			buttonempleados.setVisible(false);
		}else{
			buttonempleados.setVisible(true);
		}
		if(permiso_permisos.getCrear()==0 && permiso_permisos.getBuscar()==0 && permiso_permisos.getEditar()==0 && permiso_permisos.getEliminar()==0){
			buttonpermisos.setVisible(false);
		}else{
			buttonpermisos.setVisible(true);
		}
		if(permiso_consultas.getCrear()==0 && permiso_consultas.getBuscar()==0 && permiso_consultas.getEditar()==0 && permiso_consultas.getEliminar()==0){
			buttonconsultas.setVisible(false);
		}else{
			buttonconsultas.setVisible(true);
		}
		if(permiso_reportes.getCrear()==0 && permiso_reportes.getBuscar()==0 && permiso_reportes.getEditar()==0 && permiso_reportes.getEliminar()==0){
			buttonreportes.setVisible(false);
		}else{
			buttonreportes.setVisible(true);
		}
		if(permiso_configuraciones.getCrear()==0 && permiso_configuraciones.getBuscar()==0 && permiso_configuraciones.getEditar()==0 && permiso_configuraciones.getEliminar()==0){
			buttonconfiguraciones.setVisible(false);
		}else{
			buttonconfiguraciones.setVisible(true);
		}
	}else{	
		Executions.sendRedirect("login.zul");
	}
	

	//creamos menu en base al tipo de usuario y el permiso que le demos
	//crearMenu();
	}

	public void onClick$button_cerrarsesion(){
	Session session = Sessions.getCurrent();
	session.invalidate();
	Executions.sendRedirect("login.zul");
	}
		
		
	public void onClick$buttonconsultas(){
		Window win=(Window) Executions.createComponents("Submenues/submenuConsulta.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttonpermisos(){
		Window win=(Window) Executions.createComponents("Submenues/submenuPermisos.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}

	public void onClick$buttonpersonas(){
		Window win=(Window) Executions.createComponents("Submenues/submenuPersonas.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttonjefe(){
		Window win=(Window) Executions.createComponents("Submenues/submenuTareasjefe.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttonempleados(){
		Window win=(Window) Executions.createComponents("Submenues/submenuTareasEmpleado.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	public void onClick$buttonconfiguraciones(){
		Window win=(Window) Executions.createComponents("Submenues/submenuConfiguracion.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	public void onClick$buttonreportes(){
		Window win=(Window) Executions.createComponents("Submenues/submenuReporte.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	public void crearMenu(){
//procedimiento crear menu ocultando los botones sino cuenta con los privilegios
		/*if(roles!=16){
			buttonusuarios.setVisible(false);
			buttonparametros.setVisible(false);
		}
*/
	}

}