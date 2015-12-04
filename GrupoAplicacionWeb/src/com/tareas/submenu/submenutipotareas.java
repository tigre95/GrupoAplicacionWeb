package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenutipotareas extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevotipotareas, buttonbusquedatipotareas,buttonedicionPermisos;
	Window wintipotareas;
	Center centro= null;

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wintipotareas.getAttribute("centro");
		 
		}


	public void onClick$buttonnuevotipotareas(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Nuevo Productos");
  		win.setAttribute("op", "1");
  		wintipotareas.detach();
   }             
                
   public void onClick$buttonbusquedatipotareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Busqueda Productos"); 
  		wintipotareas.detach();
   }  
	
   public void onClick$buttonediciontipotareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("", centro, null );  		
  		win.setTitle("Listar Productos");
  		wintipotareas.detach();
   }
}

