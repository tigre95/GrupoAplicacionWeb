package com.tareas.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class MenuPrincipal_Controlador extends GenericForwardComposer<Component> {

	//enlazar los componentes de la interfaz
		@Wire
		Label label_usuario;
		Button buttonpersonas, buttontareas, buttonpermisos, buttonconsultas, buttonreportes, buttonconfiguraciones;
		Center centro;
	

		public void onClick$button_cerrarsesion(){
			Session session = Sessions.getCurrent();
			session.invalidate();
			Executions.sendRedirect("login.zul");
			}
				
				
			public void onClick$buttonconsultas(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}
			
			public void onClick$buttonpermisos(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}

			public void onClick$buttonpersonas(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}
			
			public void onClick$buttontareas(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}
			
			
			public void onClick$buttonconfiguraciones(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}
			public void onClick$buttonreportes(){
				Window win=(Window) Executions.createComponents("", null, null );
				win.setAttribute("centro", centro);
				win.setTitle("Opciones");
				win.doModal();		
			}
		
		
}


