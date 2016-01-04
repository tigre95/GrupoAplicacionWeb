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

public class submenutareasjefes extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevotareasjefe, buttonbusquedatareasjefe,buttonediciontareasjefe;
	Window wintareasjefe;
	Center centro= null;
	
	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	permisos permiso_tareas_jefes = null;
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPermisos dbpermisos = new DBPermisos();

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)wintareasjefe.getAttribute("centro"); 

		 Session session = Sessions.getCurrent();
		 usuario = (usuarios) session.getAttribute("usuario");
			if(usuario!=null){
				tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
				permiso_tareas_jefes = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "tareasjefe");
				if(permiso_tareas_jefes.getCrear()==0){
					buttonnuevotareasjefe.setVisible(false);
				}else{
					buttonnuevotareasjefe.setVisible(true);
				}
				if(permiso_tareas_jefes.getBuscar()==0){
					buttonbusquedatareasjefe.setVisible(false);
				}else{
					buttonbusquedatareasjefe.setVisible(true);
				}
				if(permiso_tareas_jefes.getEditar()==0){
					buttonediciontareasjefe.setVisible(false);
				}else{
					buttonediciontareasjefe.setVisible(true);
				}
			}else{	
				Executions.sendRedirect("login.zul");
			}
	}
    
	public void onClick$buttonnuevotareas(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/RegistroCategoria.zul", centro, null );
  		win.setTitle("Nueva Tarea");
  		wintareasjefe.detach();
   }             
                
   public void onClick$buttonbusquedatareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/BuscarCategoria.zul", centro, null );
  		win.setTitle("Busqueda Tarea"); 
  		wintareasjefe.detach();
   }  
	
   public void onClick$buttonediciontareas(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/ListarCategoria.zul", centro, null );  		
  		win.setTitle("Listar Tareas");
  		wintareasjefe.detach();
   }
}

