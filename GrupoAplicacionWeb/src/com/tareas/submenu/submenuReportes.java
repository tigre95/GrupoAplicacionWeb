package com.tareas.submenu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

import com.controlador.entidades.usuarios;

public class submenuReportes extends GenericForwardComposer<Component>{
	@Wire
			
	Button buttonNivelTarea,buttonEstadoTareas,buttonAdministrador;
	Center centro= null;
	Window winReportes;
	int roles;
	
	usuarios usuario;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winReportes.getAttribute("centro");
		Session session = Sessions.getCurrent();
		usuario = (usuarios) session.getAttribute("usuario");
		if(usuario.getId_tipousuario()==1){
			buttonAdministrador.setVisible(true);
			buttonNivelTarea.setVisible(false);
			buttonEstadoTareas.setVisible(false);
		}else{
			buttonAdministrador.setVisible(false);
			buttonNivelTarea.setVisible(true);
			buttonEstadoTareas.setVisible(true);
		}
	}
	
	public void onClick$buttonNivelTarea(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("Reportes/ReporteNivelImportancia.zul", centro, null );
	  		win.setTitle("Reporte por Nivel de Importancia");
	  		winReportes.detach();
	}   
    
	public void onClick$buttonEstadoTareas(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("Reportes/ReporteEstadoTareas.zul", centro, null );
	  		win.setTitle("Reporte por Estado de la Tarea");
	  		winReportes.detach();
	} 
	
	public void onClick$buttonAdministrador(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("Reportes/ReporteAdministrador.zul", centro, null );
	  		win.setTitle("Reporte General");
	  		winReportes.detach();
	} 
}
