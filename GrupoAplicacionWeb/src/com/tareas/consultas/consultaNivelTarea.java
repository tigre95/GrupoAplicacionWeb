package com.tareas.consultas;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.NivelTareas;
import com.controlador.entidades.TareasAsignadas;
import com.tareas.modelos.DBConsultas;
import com.tareas.modelos.DBnivelTarea;

public class consultaNivelTarea extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar;
	Button buttonBuscar,buttonListar;
	Listbox listboxtareas;
	Window win_tareas_asig;
	
	Combobox comboNivelTarea;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		DBnivelTarea dbu=new DBnivelTarea();
		ArrayList <NivelTareas> listatusu = null;
	listatusu=dbu.listarTareasnivel();
		buscartareas2();
		if(listatusu != null)
		{
			ListModelList<NivelTareas> modelo= new ListModelList<NivelTareas>(listatusu);
			comboNivelTarea.setModel(modelo);
		}
		else
		{
			alert("No hay elementos que mostrar");
		}
	
	}
	
	
	public void onCreate$win_tareas_asig(){
		buscartareas("");
		buttonListar.setDisabled(true);
	}
		
	public void buscartareas(String criterio){
		Integer id_tipo = (Integer)comboNivelTarea.getSelectedItem().getValue();
		DBConsultas dbu= new DBConsultas();
		ArrayList<TareasAsignadas> lista = dbu.buscaryNivel(criterio,id_tipo);
		if(lista != null){
			ListModelList<TareasAsignadas> modeloDeDatos= new ListModelList<TareasAsignadas>(lista);
			listboxtareas.setModel(modeloDeDatos);
			//refrescar la lista
			listboxtareas.renderAll();
		}else{
			alert("lista no encontrado");
		}
	}
	
	public void buscartareas2(){
		DBConsultas dbu= new DBConsultas();
		ArrayList<TareasAsignadas> lista = dbu.buscaryNivel2();
		if(lista != null){
			ListModelList<TareasAsignadas> modeloDeDatos= new ListModelList<TareasAsignadas>(lista);
			listboxtareas.setModel(modeloDeDatos);
			//refrescar la lista
			listboxtareas.renderAll();
		}else{
			alert("lista no encontrado");
		}
	}
	
	public void onClick$buttonListar(){
		buscartareas("");
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscartareas(textboxBuscar.getValue());
		buttonListar.setDisabled(false);
	}
}