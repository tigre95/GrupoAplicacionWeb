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
		}

	}

	public void onClick$buttontareasR(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("Consultas/ConsultaEstadoTareas.zul", centro, null );  		
  		win.setTitle("Consulta por estado de tareas");
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
}
