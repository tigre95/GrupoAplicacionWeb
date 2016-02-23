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

public class submenuConsultas extends GenericForwardComposer<Component>{
	@Wire
	Button buttontareasA,buttonRepGeneral,buttontareasR,buttontareasN,buttontareasG,
	buttonTareaEliminada,buttonTareaAtrasada,buttonAdministrador;
	Center centro= null;
	Window winconsultas;
	int roles;
	
	usuarios usuario;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winconsultas.getAttribute("centro");
		Session session = Sessions.getCurrent();
		usuario = (usuarios) session.getAttribute("usuario");
		if(usuario.getId_tipousuario()==1){
			buttonAdministrador.setVisible(true);
			buttontareasR.setVisible(false);
			buttontareasN.setVisible(false);
		}else{
			buttonAdministrador.setVisible(false);
			buttontareasR.setVisible(true);
			buttontareasN.setVisible(true);
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
	  		win.setTitle("Cosulta por nivel de importancia");
	  		winconsultas.detach();
   }
   
   public void onClick$buttonAdministrador(){
	   	 if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   	   	Window win=(Window) Executions.createComponents("Consultas/ConsultaAdministrador.zul", centro, null );
	  		win.setTitle("Consulta General");
	  		winconsultas.detach();
	}
}
