package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class submenuConsultas extends GenericForwardComposer<Component>{
	@Wire
	Button buttontareasA,buttonRepGeneral,buttontareasR,buttontareasN,buttontareasG,buttonTareaEliminada,buttonTareaAtrasada;
	Center centro= null;
	Window winconsultas;
	int roles;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winconsultas.getAttribute("centro");
		
	}
	
	public void crearMenu(){
		if(roles!=16){
		//	buttonnuevoli.setDisabled(true);
			//buttonedicionli.setDisabled(true);
		}

	}

    
	public void onClick$buttontareasA(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("Consultas/ConsultaTareaActiva.zul", centro, null );
  		win.setTitle("Consulta Tarea Activa");
  		winconsultas.detach();
   }             
                
   public void onClick$buttonRepGeneral(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("Consultas/ConsultaTareasPendientes.zul", centro, null );
  		win.setTitle("Consulta Tarea Pendiente"); 
  		winconsultas.detach();
   }  
	
   public void onClick$buttontareasR(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("Consultas/ConsultaTareasRealizadas.zul", centro, null );  		
  		win.setTitle("Consultas Tareas Realizada");
  		winconsultas.detach();
   }
   
   public void onClick$buttontareasN(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("Consultas/ConsultaNivelTareas.zul", centro, null );  		
	  		win.setTitle("Cosulta Nivel Tareas");
	  		winconsultas.detach();
	   }
   
   
   public void onClick$buttonTareaEliminada(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("Consultas/ConsultaTareaEliminada.zul", centro, null );  		
	  		win.setTitle("Consulta Tarea Eliminada");
	  		winconsultas.detach();
	   }
   
   
   public void onClick$buttonTareaAtrasada(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("Consultas/ConsultaTareaAtrazada.zul", centro, null );  		
	  		win.setTitle("Consulta Tarea Atrazada");
	  		winconsultas.detach();
	   }
   
   
}
