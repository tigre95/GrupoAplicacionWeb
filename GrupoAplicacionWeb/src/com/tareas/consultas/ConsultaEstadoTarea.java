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
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.tareas.modelos.DBConsultas;
import com.tareas.modelos.DBUsuarios;
import com.tareas.modelos.DBnivelTarea;

public class ConsultaEstadoTarea extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar;
	Button buttonBuscar,buttonListar;
	Listbox listboxtareas;
	Window win_tareas_asig;
	Combobox comboboxTipo;
	Combobox comboNivelTarea;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		buscartareas2();
		DBnivelTarea dbu=new DBnivelTarea();
		comboboxTipo.setText("Seleccione");
		comboNivelTarea.setText("Seleccione");
	}
	
	public void onCreate$win_tareas_asig(){
		buscartareas2();
		buttonListar.setDisabled(true);
	}
		
	public void buscartareas(String criterio){
		int id_tipo= comboNivelTarea.getSelectedIndex();
		int estado= comboboxTipo.getSelectedIndex();
		
		DBConsultas dbu= new DBConsultas();
		ArrayList<TareasAsignadas> lista = dbu.buscarytareasRealizada(criterio,id_tipo,estado);
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
		ArrayList<TareasAsignadas> lista = dbu.buscarytareasRealizada2();
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
		buscartareas2();
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscartareas(textboxBuscar.getValue());
		buttonListar.setDisabled(false);
	}
}