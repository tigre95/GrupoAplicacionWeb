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

import com.controlador.entidades.Departamento;
import com.tareas.modelos.DBdepartamento;

public class EditarDepartamento extends GenericForwardComposer<Component>{
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

	public void actualizarLista(String criterio1){
		DBdepartamento dbcategorias = new DBdepartamento();
		ArrayList<Departamento> lista = dbcategorias.buscardepartamento(criterio1);
		ListModelList<Departamento> modeloDeDatos= new ListModelList<Departamento>(lista);
		listboxdept.setModel(modeloDeDatos);
		
	}

	public void onClick$toolbarButtonNuevo(){
	
		
		Window win=(Window) Executions.createComponents("/ConfiguracionesDepartamento/RegistrarDepartamento.zul", null, null );
	
		win.setTitle("Registrar Nueva categoria");
		win.doModal();
		actualizarLista("");
		
	}

	
	public void onClick$toolbarButtonEditar(){
		Departamento tipoSeleccionado = null;	
		if(listboxdept.getSelectedItem() != null){
			tipoSeleccionado =  listboxdept.getSelectedItem().getValue();
			alert("tipoSeleccionado");
		}else{
			alert ("Por favor seleccione un tipo de usuario de la lista");
			return;
		}
		Window win= (Window) Executions.createComponents("/ConfiguracionesDepartamento/RegistrarDepartamento.zul", null, null);
		win.setTitle("Editar tipo");
		win.setAttribute("Departamento", tipoSeleccionado);
		actualizarLista("");
		win.doModal();	
		
		actualizarLista("");
	}
	


	public void onClick$buttonBuscar(){
			actualizarLista(textboxBuscar.getValue());
	}

	public void onClick$buttonBusca(){
		actualizarLista("");
		textboxBuscar.setValue("");
	}



	public void onClick$toolbarButtonEliminacion(){
		Departamento categoriaSeleccionado = null;
		if(listboxdept.getSelectedItem() != null){
			categoriaSeleccionado =  listboxdept.getSelectedItem().getValue();
				if(Messagebox.show("Esta seguro de eliminar?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
				return;
				}			
				EliminarLogico(categoriaSeleccionado);
				actualizarLista("");
			
		}else{
			alert("Seleccione la Categoria a eliminar de la lista");
			return;
		}
	}

	public void EliminarLogico(Departamento categorias){
		categorias.setEstado("I");
		DBdepartamento dbcategorias = new DBdepartamento();
		boolean resultado= false;
		resultado= dbcategorias.EliminardepartamentoLogico(categorias);			
		if(resultado){
			alert("Eliminado Exitosamente");		
		}else{
			alert("Error al Eliminar la Categoria");
		}	
	}

	}


