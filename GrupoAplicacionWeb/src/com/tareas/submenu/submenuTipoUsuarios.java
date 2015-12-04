package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenuTipoUsuarios extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevotipoUsu, buttonbusquedatipoUsu,buttonediciontipoUsu;
	Window wintipoUsu;
	Center centro= null;

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wintipoUsu.getAttribute("centro");
			}

	public void onClick$buttonnuevotipoUsu(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Nuevo Productos");
  		win.setAttribute("op", "1");
  		wintipoUsu.detach();
   }             
                
   public void onClick$buttonbusquedatipoUsu(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Busqueda Productos"); 
  		wintipoUsu.detach();
   }  
	
   public void onClick$buttonediciontipoUsu(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("", centro, null );  		
  		win.setTitle("Listar Productos");
  		wintipoUsu.detach();
   }
}

