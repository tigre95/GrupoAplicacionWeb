package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

import com.controlador.entidades.permisos;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBTiposUsuarios;


public class submenuPersonas extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
		@Wire
		Button buttonnuevoPersona, buttonbusquedaPersona,buttonedicionPersona;
		Window winPersonas;
		Center centro= null;
		
		usuarios usuario=null;
		tiposusuarios tipousuario = null;
		permisos permiso_personas = null;
		DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
		DBPermisos dbpermisos = new DBPermisos();

	    @Override
	   
			public void doAfterCompose(Component comp) throws Exception {
				// TODO Auto-generated method stub
				super.doAfterCompose(comp);
				 centro = (Center)winPersonas.getAttribute("centro");
				 
				 Session session = Sessions.getCurrent();
					usuario = (usuarios) session.getAttribute("usuario");
					if(usuario!=null){
						tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
						permiso_personas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "personas");
						if(permiso_personas.getCrear()==0){
							buttonnuevoPersona.setVisible(false);
						}else{
							buttonnuevoPersona.setVisible(true);
						}
						if(permiso_personas.getBuscar()==0){
							buttonbusquedaPersona.setVisible(false);
						}else{
							buttonbusquedaPersona.setVisible(true);
						}
						if(permiso_personas.getEditar()==0){
							buttonedicionPersona.setVisible(false);
						}else{
							buttonedicionPersona.setVisible(true);
						}
					}else{	
						Executions.sendRedirect("login.zul");
					}
			}
	    
		public void onClick$buttonnuevoPersona(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("", centro, null );
	  		win.setTitle("Nuevo Usuario");
	  		winPersonas.detach();
	   }             
	                
	   public void onClick$buttonbusquedaPersona(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	  		Window win=(Window) Executions.createComponents("", centro, null );
	  		win.setTitle("Busqueda Usuarios"); 
	  		winPersonas.detach();
	   }  
		
	   public void onClick$buttonedicionPersona(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("", centro, null );  		
	  		win.setTitle("Listar Usuarios");
	  		winPersonas.detach();
	   }
	}

