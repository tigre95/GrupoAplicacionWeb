package com.tareas.controlador;
import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.controlador.entidades.NivelTareas;
import com.tareas.modelos.DBnivelTarea;

public class EditarNivelTarea  extends GenericForwardComposer<Component>{
	@Wire
	Listbox listboxNivelTarea;
	Textbox textboxBuscar;
	Button buttonListar,buttonBuscar,buttonBusca;
	Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		actualizarLista("");
		
	}
	
	public void onClick$toolbarButtonNuevo(){
		
		Window win=(Window) Executions.createComponents("/ConfiguracionesNivelTareas/RegistroNivelimportanciaTarea.zul", null, null );
		win.setTitle("Registrar Nuevo nivel tarea");
		win.doModal();
		actualizarLista("");
		
	}

	public void onClick$toolbarButtonEditar(){
		NivelTareas tipoSeleccionados = null;	
		if(listboxNivelTarea.getSelectedItem() != null){
			tipoSeleccionados =  listboxNivelTarea.getSelectedItem().getValue();
		}else{
			alert ("Por favor seleccione el nivel de tarea en la lista");
			return;
		}
		Window win= (Window) Executions.createComponents("/ConfiguracionesNivelTareas/RegistroNivelimportanciaTarea.zul", null, null);
		win.setTitle("Editar tipo");
		win.setAttribute("NivelTareas", tipoSeleccionados);
		win.doModal();	
		actualizarLista("");
	
	}

	public void onClick$buttonBuscar(){
			actualizarLista(textboxBuscar.getValue());
	}

	public void onClick$buttonBusca(){
		
		textboxBuscar.setValue("");
		actualizarLista("");
	}

	public void actualizarLista(String criterio1){
		DBnivelTarea dbtipo = new DBnivelTarea();
		ArrayList<NivelTareas> lista = dbtipo.buscarNivelTarea(criterio1);
		ListModelList<NivelTareas> modeloDeDatos= new ListModelList<NivelTareas>(lista);
		listboxNivelTarea.setModel(modeloDeDatos);
		
	}

	public void onClick$toolbarButtonEliminacion(){
		NivelTareas tipoSeleccionado = null;
		if(listboxNivelTarea.getSelectedItem() != null){
			tipoSeleccionado =  listboxNivelTarea.getSelectedItem().getValue();
				if(Messagebox.show("Esta seguro de eliminar?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
				return;
				}			
				EliminarLogico(tipoSeleccionado);
				actualizarLista("");
			
		}else{
			alert("Seleccione la tipo usuario a eliminar de la lista");
			return;
		}
	}

	public void EliminarLogico(NivelTareas tipoSeleccionadousu){
		tipoSeleccionadousu.setEstado("I");
		DBnivelTarea dbcategorias = new DBnivelTarea();
		boolean resultado= false;
		resultado= dbcategorias.EliminarNivelTarea(tipoSeleccionadousu);			
		if(resultado){
			alert("Eliminado Exitosamente");	
			 actualizarLista("");
		}else{
			alert("Error al Eliminar el tipo de usuario");
		}
		 
	}

	
	
	}


