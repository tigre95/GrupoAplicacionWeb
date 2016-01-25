package com.tareas.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.East;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.personas;
import com.controlador.entidades.tareas;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTareas;

public class BuscarTareaControlador extends GenericForwardComposer<Component>{

	@Wire
	Listbox ListaTareas;
	Window WinListaTareas;
	Center centro= null;
	East este = null;
	Button buttonBuscar;
	Combobox comboEstadoBusqueda, comboEstadoEdicion; 
	
	personas empleado = null;
	tareas tarea = null;
	DBPersonas dbpersonas = new DBPersonas();
	String opcion;
	String estado = "";
	ListModelList<tareas> lista_tareas;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinListaTareas.getAttribute("centro"); 
		este = (East)WinListaTareas.getAttribute("este");
		Session session = Sessions.getCurrent();
		opcion = (String)session.getAttribute("OpcionTareasJefe");
		empleado = (personas)session.getAttribute("empleado_seleccionado");
		if(opcion.equals("Guardar")){
		}else{
			if(opcion.equals("Buscar")){
				comboEstadoBusqueda.setDisabled(false);
				comboEstadoEdicion.setDisabled(true);
				comboEstadoBusqueda.setVisible(true);
				comboEstadoEdicion.setVisible(false);
				CargarListaTareasBusqueda();
			}else{
				comboEstadoBusqueda.setDisabled(true);
				comboEstadoEdicion.setDisabled(false);
				comboEstadoBusqueda.setVisible(false);
				comboEstadoEdicion.setVisible(true);
				CargarListaTareasEdicion();
			}
		}
	}
	
	public void onSelect$comboEstadoBusqueda(){
		estado = comboEstadoBusqueda.getText();
	}
	
	public void onSelect$comboEstadoEdicion(){
		estado = comboEstadoEdicion.getText();
	}

	public void CargarListaTareasBusqueda(){
		DBTareas dbtareas = new DBTareas();
		lista_tareas = dbtareas.lista_tareas_busqueda(empleado.getId_persona(), comboEstadoBusqueda.getText());
		if(lista_tareas != null){
			ListModelList<tareas> listmodel = new ListModelList<tareas>(lista_tareas);
			ListaTareas.setModel(listmodel);
			//refrescar la lista
			ListaTareas.renderAll();
		}else{
			ListModelList<tareas> listmodel = new ListModelList<tareas>();
			ListaTareas.setModel(listmodel);
			//refrescar la lista
			ListaTareas.renderAll();
			alert("No existen datos");
		}
	}
	
	public void CargarListaTareasEdicion(){
		DBTareas dbtareas = new DBTareas();
		lista_tareas = dbtareas.lista_tareas_edicion(empleado.getId_persona(), comboEstadoEdicion.getText());
		if(lista_tareas != null){
			ListModelList<tareas> listmodel = new ListModelList<tareas>(lista_tareas);
			ListaTareas.setModel(listmodel);
			//refrescar la lista
			ListaTareas.renderAll();
		}else{
			ListModelList<tareas> listmodel = new ListModelList<tareas>();
			ListaTareas.setModel(listmodel);
			//refrescar la lista
			ListaTareas.renderAll();
			alert("No existen datos");
		}
	}
	
	public void onClick$buttonBuscar(){
		if(opcion.equals("Guardar")){
		}else{
			if(opcion.equals("Buscar")){
				CargarListaTareasBusqueda();
			}else{
				CargarListaTareasEdicion();
			}
		}
	}

	public void onSelect$ListaTareas(){
		tarea = (tareas)lista_tareas.get(ListaTareas.getSelectedIndex());
		if(opcion.equals("Guardar")){
			Session session = Sessions.getCurrent();
			session.setAttribute("tarea_seleccionada", tarea);
			WinListaTareas.detach();
		}else{
			if(opcion.equals("Buscar")){
				if(centro.getFirstChild()!=null){
					centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("tarea_seleccionada", tarea);
				Window win=(Window) Executions.createComponents("TareasJefeFormulario/AsignarTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}else{
				if(centro.getFirstChild()!=null){
					centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("tarea_seleccionada", tarea);
				Window win=(Window) Executions.createComponents("TareasJefeFormulario/AsignarTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}
		}
	}
	
	public BuscarTareaControlador() {
		// TODO Auto-generated constructor stub
	}

}
