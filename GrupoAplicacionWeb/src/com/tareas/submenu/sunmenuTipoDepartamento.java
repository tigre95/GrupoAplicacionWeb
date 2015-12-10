package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class sunmenuTipoDepartamento extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevotipodepart, buttonbusquedatipodepart,buttonedicionttipodepart;
	Window wintipodepart;
	Center centro= null;
	int roles;

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wintipodepart.getAttribute("centro");
		 
		}

    
	
	public void crearMenu(){
		if(roles!=16){
		//	buttonnuevoli.setDisabled(true);
			//buttonedicionli.setDisabled(true);
		}

	}

    
	public void onClick$buttonnuevotipodepart(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("ConfiguracionesDepartamento/RegistrarDepartamento.zul", centro, null );
  		win.setTitle("Nuevo Tipo Departamento");
  		win.setAttribute("op", "1");
  		wintipodepart.detach();
   }             
                
   public void onClick$buttonbusquedatipodepart(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("ConfiguracionesDepartamento/BuscarDEpartamento.zul", centro, null );
  		win.setTitle("Busqueda  Tipo Departamento"); 
  		wintipodepart.detach();
   }  
	
   public void onClick$buttonedicionttipodepart(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("ConfiguracionesDepartamento/edicionDepartamento.zul", centro, null );  		
  		win.setTitle("Listar  Tipo Departamento");
  		wintipodepart.detach();
   }
}

