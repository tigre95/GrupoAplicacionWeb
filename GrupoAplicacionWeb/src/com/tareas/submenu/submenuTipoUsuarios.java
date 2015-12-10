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
   	   	Window win=(Window) Executions.createComponents("ConfiguracionesTipoUsuarios/RegistroTipoUsuario.zul", centro, null );
  		win.setTitle("Nuevo tipo Usuario");
  		win.setAttribute("op", "1");
  		wintipoUsu.detach();
  		wintipoUsu.onClose();
  		
   }             
                
   public void onClick$buttonbusquedatipoUsu(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("ConfiguracionesTipoUsuarios/BuscarTipoUsuario.zul", centro, null );
  		win.setTitle("Busqueda tipo Usuario"); 
  		wintipoUsu.detach();
   }  
	
   public void onClick$buttonediciontipoUsu(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("ConfiguracionesTipoUsuarios/EdiciontipoUsuario.zul", centro, null );  		
  		win.setTitle("Listar tipo Usuario");
  		wintipoUsu.detach();
   }
}



