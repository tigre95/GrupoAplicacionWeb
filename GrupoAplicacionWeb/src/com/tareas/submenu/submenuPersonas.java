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

public class submenuPersonas extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
		@Wire
		Button buttonnuevoPersona, buttonbusquedaPersona,buttonedicionPersona;
		Window winPersonas;
		Center centro= null;
		
		usuarios usuario=null;
		tiposusuarios tipousuario = null;
		permisos permiso_personas = null;
		
	    @Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);
			 centro = (Center)winPersonas.getAttribute("centro");
			 
			 
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
