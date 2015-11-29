package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenuPermisos extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevoPermisos, buttonbusquedaPermisos,buttonedicionPermisos;
	Window winPermisos;
	Center centro= null;

	
	public void onClick$buttonnuevoPermisos(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("", centro, null );
	  		win.setTitle("Nuevo Productos");
	  		win.setAttribute("op", "1");
	  		winPermisos.detach();
	   }             
	                
	   public void onClick$buttonbusquedaPermisos(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	  		Window win=(Window) Executions.createComponents("", centro, null );
	  		win.setTitle("Busqueda Productos"); 
	  		winPermisos.detach();
	   }  
		
	   public void onClick$buttonedicionPermisos(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("", centro, null );  		
	  		win.setTitle("Listar Productos");
	  		winPermisos.detach();
	   }
	

}
