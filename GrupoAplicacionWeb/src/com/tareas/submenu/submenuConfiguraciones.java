package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenuConfiguraciones extends GenericForwardComposer<Component>{
	@Wire
	Button buttontipotareas,buttonequipoTrabajo,buttontipoUsuarios,buttontipoDepart,buttonNuevos;
	Center centro;
	Window winConfiguraciones;
	int roles;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winConfiguraciones.getAttribute("centro");
		
	}
	
	
	public void onClick$buttontipotareas(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("", null, null  );
   	 win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();
		
   }             
                
   public void onClick$buttontipoUsuarios(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("", null, null  );		
   	 win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();
   }
   
   public void onClick$buttontipoDepart(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("", null, null  );
	   		win.setAttribute("centro", centro);
			win.setTitle("Opciones");
			win.doModal();
	   }
   
   
 
}

