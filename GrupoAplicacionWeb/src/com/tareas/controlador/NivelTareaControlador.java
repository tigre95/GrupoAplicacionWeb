package com.tareas.controlador;
import java.util.ArrayList;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.NivelTareas;
import com.tareas.modelos.DBnivelTarea;

public class NivelTareaControlador extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
	@Wire
	Textbox  textbox_descripcion;
	Button button_Registrar;
	Window winNuevoNivelTarea;
	int idtipo;
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public void onCreate$winNuevoNivelTarea(){
		idtipo=0;
		NivelTareas tiposusuarios = (NivelTareas) winNuevoNivelTarea.getAttribute("NivelTareas");
		if(tiposusuarios != null){
			idtipo=tiposusuarios.getId_tipotarea();
			textbox_descripcion.setText(tiposusuarios.getDescripcion());
					}
	}
		
	public void onClick$button_Registrar(){
		NivelTareas tiposusuarios = (NivelTareas) winNuevoNivelTarea.getAttribute("NivelTareas");		
		if(tiposusuarios !=null ){
			guardar(tiposusuarios,1);			
		}else{
			tiposusuarios = new NivelTareas();
			guardar(tiposusuarios,2);		
		}
	}
	
		
	public void guardar(NivelTareas NivelTareas, int opcion){
		DBnivelTarea dbcategorias = new DBnivelTarea();
		ArrayList<NivelTareas> validar = dbcategorias.Validarniveltarea(textbox_descripcion.getText(),idtipo);
		if(validar.size()>=1){
			alert("Error!! Tipo Usuario ya existe!!");
		}else{
			NivelTareas.setDescripcion(textbox_descripcion.getText());
			boolean resultado= false;		
			if(opcion == 1){
				resultado= dbcategorias.editarNivelTarea(NivelTareas);			
			}else if(opcion==2){
				resultado= dbcategorias.crearNivelTarea(NivelTareas);
			}
			
			if(resultado){
				alert("Guardado Exitosamente");
				winNuevoNivelTarea.detach();
			}else{
				alert("Error al guardar usuario");
			}
		}
	}
	
}
