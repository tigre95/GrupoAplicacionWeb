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

import com.controlador.entidades.NivelTareas;
import com.tareas.modelos.DBnivelTarea;


public class buscarNivelTarea extends GenericForwardComposer<Component>{
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
	public void onClick$buttonBuscar(){
		actualizarLista(textboxBuscar.getValue());
}

public void onClick$buttonBusca(){
	actualizarLista("");
	textboxBuscar.setValue("");
}


public void actualizarLista(String criterio1){
	DBnivelTarea dbcategorias = new DBnivelTarea();
	ArrayList<NivelTareas> lista = dbcategorias.buscarNivelTarea(criterio1);
	ListModelList<NivelTareas> modeloDeDatos= new ListModelList<NivelTareas>(lista);
	listboxNivelTarea.setModel(modeloDeDatos);
	
	
}


}


