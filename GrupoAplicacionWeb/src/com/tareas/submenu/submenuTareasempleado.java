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
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBTiposUsuarios;

public class submenuTareasempleado extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevotareas, buttonbusquedatareas,buttonediciontareas;
	Window wintareas;
	Center centro= null;
	
	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	permisos permiso_tareas_empleados = null;
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPermisos dbpermisos = new DBPermisos();

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wintareas.getAttribute("centro"); 

		 Session session = Sessions.getCurrent();
		 usuario = (usuarios) session.getAttribute("usuario");
			if(usuario!=null){
				tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
				permiso_tareas_empleados = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "empleadostareas");
				if(permiso_tareas_empleados.getCrear()==0){
					buttonnuevotareas.setVisible(false);
				}else{
					buttonnuevotareas.setVisible(true);
				}
				if(permiso_tareas_empleados.getBuscar()==0){
					buttonbusquedatareas.setVisible(false);
				}else{
					buttonbusquedatareas.setVisible(true);
				}
				if(permiso_tareas_empleados.getEditar()==0){
					buttonediciontareas.setVisible(false);
				}else{
					buttonediciontareas.setVisible(true);
				}
			}else{	
				Executions.sendRedirect("login.zul");
			}
	}
    
	public void onClick$buttonnuevotareas(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("TareasEmpleadoFormulario/DatosTarea.zul", centro, null );
  		win.setTitle("Nueva Tarea");
  		wintareas.detach();
   }             
                
   public void onClick$buttonbusquedatareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("", centro, null );
  		win.setTitle("Busqueda Tarea"); 
  		wintareas.detach();
   }  
	
   public void onClick$buttonediciontareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("", centro, null );  		
  		win.setTitle("Listar Tareas");
  		wintareas.detach();
   }
}

