package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.tiposusuarios;
import com.tareas.modelos.DBTiposUsuarios;

public class TipoUsuarioControlador extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
		@Wire
		Textbox  textbox_descripcion;
		Button button_Registrar;
		Window winNuevoTipoUsuario;
		
		int idtipo;
		
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);
		}

		public void onCreate$winNuevoTipoUsuario(){
			idtipo=0;
			tiposusuarios tiposusuarios = (tiposusuarios) winNuevoTipoUsuario.getAttribute("tiposusuarios");
			if(tiposusuarios != null){
				idtipo=tiposusuarios.getId_tipousuario();
				textbox_descripcion.setText(tiposusuarios.getDescripcion());
						}
		}
			
		public void onClick$button_Registrar(){
			tiposusuarios tiposusuarios = (tiposusuarios) winNuevoTipoUsuario.getAttribute("tiposusuarios");		
			if(tiposusuarios !=null ){
				guardar(tiposusuarios,1);			
			}else{
				tiposusuarios = new tiposusuarios();
				guardar(tiposusuarios,2);		
			}
		}
		
			
		public void guardar(tiposusuarios tiposusuarios, int opcion){
			DBTiposUsuarios dbcategorias = new DBTiposUsuarios();
			ArrayList<tiposusuarios> validar = dbcategorias.ValidarTipoUsuarios(textbox_descripcion.getText(),idtipo);
			if(validar.size()>=1){
				alert("Error!! Tipo Usuario ya existe!!");
			}else{
				tiposusuarios.setDescripcion(textbox_descripcion.getText());
				boolean resultado= false;		
				if(opcion == 1){
					resultado= dbcategorias.editarTipoUsuario(tiposusuarios);			
				}else if(opcion==2){
					resultado= dbcategorias.creartipousuario(tiposusuarios);
				}
				
				if(resultado){
					alert("Guardado Exitosamente");
					winNuevoTipoUsuario.detach();
				}else{
					alert("Error al guardar usuario");
				}
			}
		}
		
	}
