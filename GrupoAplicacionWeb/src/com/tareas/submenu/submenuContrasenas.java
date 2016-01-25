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

import com.controlador.entidades.permisos;
import com.controlador.entidades.personas;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTiposUsuarios;
import com.controlador.entidades.usuarios;

public class submenuContrasenas extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonContraseñas;
	Window wincontraseñas;
	Center centro= null;
	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPersonas dbpersonas = new DBPersonas();
	personas persona = null;
	String tipo= "Usuario: ";
	
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wincontraseñas.getAttribute("centro");
		 
		 Session session = Sessions.getCurrent();
		 usuario = (usuarios) session.getAttribute("usuario");
			if(usuario!=null){
				tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
				persona = dbpersonas.mostrarpersonas(usuario.getId_persona());
				
				String nombrec= tipo+tipousuario.getDescripcion()+": " + persona.getNombres() +" "+ persona.getApellidos();
		
			}else{	
				Executions.sendRedirect("login.zul");
			}
	}
    
    
            
             
	public void onClick$buttonContraseñas(){
   	 if(centro.getFirstChild()!=null){
    centro.removeChild(centro.getFirstChild());
  	 }
   	Session session = Sessions.getCurrent();
	 	session.setAttribute("Opcioncontrasena", "cambio");
   	   	Window win=(Window) Executions.createComponents("CambioContrasena/Contrasena.zul", centro, null );
  		win.setTitle("Cambio Contraseñas");
  			wincontraseñas.detach();
   }             
                
  
}

