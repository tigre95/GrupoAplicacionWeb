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

import com.controlador.entidades.tiposusuarios;
import com.tareas.modelos.DBTiposUsuarios;

public class EditarTipoUsuario extends GenericForwardComposer<Component>{
	@Wire
	Listbox listboxTipoUsuarios;
	Textbox textboxBuscar;
	Button buttonListar,buttonBuscar,buttonBusca;
	Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;
	String criterio1;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		DBTiposUsuarios dbtipo = new DBTiposUsuarios();
		ArrayList<tiposusuarios> lista = dbtipo.buscartipoUsuarionormal();
		ListModelList<tiposusuarios> modeloDeDatos= new ListModelList<tiposusuarios>(lista);
		listboxTipoUsuarios.setModel(modeloDeDatos);
	}


	public void onClick$toolbarButtonNuevo(){
		//lol
		Window win=(Window) Executions.createComponents("/ConfiguracionesTipoUsuarios/RegistroTipoUsuario.zul", null, null );
		win.setTitle("Registrar Nuevo tipo usuario");
		win.doModal();
		DBTiposUsuarios dbtipo = new DBTiposUsuarios();
		ArrayList<tiposusuarios> lista = dbtipo.buscartipoUsuarionormal();
		ListModelList<tiposusuarios> modeloDeDatos= new ListModelList<tiposusuarios>(lista);
		listboxTipoUsuarios.setModel(modeloDeDatos);
	}

	public void onClick$toolbarButtonEditar(){
		tiposusuarios tipoSeleccionado = null;	
		if(listboxTipoUsuarios.getSelectedItem() != null){
			tipoSeleccionado =  listboxTipoUsuarios.getSelectedItem().getValue();
		}else{
			alert ("Por favor seleccione un tipo de usuario de la lista");
			return;
		}
		Window win= (Window) Executions.createComponents("/ConfiguracionesTipoUsuarios/RegistroTipoUsuario.zul", null, null);
		win.setTitle("Editar tipo");
		win.setAttribute("tiposusuarios", tipoSeleccionado);
		win.doModal();		
		DBTiposUsuarios dbtipo = new DBTiposUsuarios();
		ArrayList<tiposusuarios> lista = dbtipo.buscartipoUsuarionormal();
		ListModelList<tiposusuarios> modeloDeDatos= new ListModelList<tiposusuarios>(lista);
		listboxTipoUsuarios.setModel(modeloDeDatos);
	}

	public void onClick$buttonBuscar(){
			actualizarLista(textboxBuscar.getValue());
	}

	public void onClick$buttonBusca(){
		
		textboxBuscar.setValue("");
		actualizarLista("");
	}

	public void actualizarLista(String criterio1){
		DBTiposUsuarios dbtipo = new DBTiposUsuarios();
		ArrayList<tiposusuarios> lista = dbtipo.buscartipoUsuario(criterio1);
		ListModelList<tiposusuarios> modeloDeDatos= new ListModelList<tiposusuarios>(lista);
		listboxTipoUsuarios.setModel(modeloDeDatos);
		
	}

	public void onClick$toolbarButtonEliminacion(){
		tiposusuarios tipoSeleccionado = null;
		if(listboxTipoUsuarios.getSelectedItem() != null){
			tipoSeleccionado =  listboxTipoUsuarios.getSelectedItem().getValue();
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

	public void EliminarLogico(tiposusuarios tipoSeleccionadousu){
		tipoSeleccionadousu.setEstado("I");
		DBTiposUsuarios dbcategorias = new DBTiposUsuarios();
		boolean resultado= false;
		resultado= dbcategorias.EliminarTipoUSuario(tipoSeleccionadousu);			
		if(resultado){
			alert("Eliminado Exitosamente");	
			 actualizarLista("");
		}else{
			alert("Error al Eliminar el tipo de usuario");
		}
		 
	}
	
	
	

	}
