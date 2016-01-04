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
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBTiposUsuarios;
import com.controlador.entidades.permisos;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;

public class submenuPermisos extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonedicionPermisos;
	Window winPermisos;
	Center centro= null;

	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	permisos permiso_permisos = null;
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPermisos dbpermisos = new DBPermisos();
	
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)winPermisos.getAttribute("centro");
		 
		 usuario = (usuarios) session.getAttribute("usuario");
			if(usuario!=null){
				tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
				permiso_permisos = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(),"permisos");
				
				if(permiso_permisos.getEditar()==0){
					buttonedicionPermisos.setVisible(false);
				}else{
					buttonedicionPermisos.setVisible(true);
				}
			}else{	
				Executions.sendRedirect("login.zul");
			}
	}
    
    public void onClick$buttonedicionPermisos(){
      	if(centro.getFirstChild()!=null){
      	 centro.removeChild(centro.getFirstChild());
      	 }
      		Session session = Sessions.getCurrent();
    	 	session.setAttribute("opcion_permisos", "editar");
       		Window win=(Window) Executions.createComponents("FormularioPermiso/nuevoPermiso2.zul", centro, null );  		
      		win.setTitle("Editar Permisos");
      		winPermisos.detach();
       }
  
}
