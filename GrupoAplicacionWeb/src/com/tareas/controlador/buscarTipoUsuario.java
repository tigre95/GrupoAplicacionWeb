package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.controlador.entidades.tiposusuarios;
import com.tareas.modelos.DBTiposUsuarios;

public class buscarTipoUsuario extends GenericForwardComposer<Component>{
	@Wire
	Listbox listboxTipoUsuarios;
	Textbox textboxBuscar;
	Button buttonListar,buttonBuscar,buttonBusca;
	Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
actualizarLista("");
		
	}

	public void onClick$buttonBuscar(){
			actualizarLista(textboxBuscar.getValue());
	}

	public void onClick$buttonBusca(){
		actualizarLista("");
		textboxBuscar.setValue("");
	}


	public void actualizarLista(String criterio1){
		DBTiposUsuarios dbcategorias = new DBTiposUsuarios();
		ArrayList<tiposusuarios> lista = dbcategorias.buscartipoUsuario(criterio1);
		ListModelList<tiposusuarios> modeloDeDatos= new ListModelList<tiposusuarios>(lista);
		listboxTipoUsuarios.setModel(modeloDeDatos);
		
		
	}


	}
