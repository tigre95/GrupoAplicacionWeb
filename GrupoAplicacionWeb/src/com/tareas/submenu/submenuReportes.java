package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenuReportes extends GenericForwardComposer<Component>{
	@Wire
	Button buttonRtareasA,buttonRE,buttonRtareasR,buttonRtareasN,buttonRtareasG;
	Center centro= null;
	Window winReportes;
	int roles;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winReportes.getAttribute("centro");
		
	}
	
	public void crearMenu(){
		if(roles!=16){
		//	buttonnuevoli.setDisabled(true);
			//buttonedicionli.setDisabled(true);
		}

	}

    
	public void onClick$buttonRtareasA(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Nueva Categoria");
  		winReportes.detach();
   }             
                
   public void onClick$buttonRE(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Busqueda Categorias"); 
  		winReportes.detach();
   }  
	
   public void onClick$buttonRtareasR(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("", centro, null );  		
  		win.setTitle("Listar Categorias");
  		winReportes.detach();
   }
   
   public void onClick$buttonRtareasN(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("", centro, null );  		
	  		win.setTitle("Listar Categorias");
	  		winReportes.detach();
	   }
   
   
   public void onClick$buttonRtareasG(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("", centro, null );  		
	  		win.setTitle("Listar Categorias");
	  		winReportes.detach();
	   }
}
