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

import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class buscarDepartamento extends GenericForwardComposer<Component>{
	@Wire
	Listbox listboxdept;
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
	DBdepartamento dbcategorias = new DBdepartamento();
	ArrayList<Departamento> lista = dbcategorias.buscardepartamento(criterio1);
	ListModelList<Departamento> modeloDeDatos= new ListModelList<Departamento>(lista);
	listboxdept.setModel(modeloDeDatos);
	
	
}


}
