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
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.personas;
import com.controlador.entidades.tareas;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTareas;
import com.tareas.modelos.DBUsuarios;
public class ListaTareasControlador extends GenericForwardComposer<Component>{

	@Wire
	Listbox ListaTareas;
	Window WinListaTareas;
	Center centro= null;
	East este = null;
	Button buttonBuscar;
	Combobox comboEstadoBusqueda, comboEstadoGuardar, comboEstadoActualizar; 
	
	Label labelFechaInicio;
	Label labelFechaFin;
	Textbox textboxDescripcion;
	personas empleado = null;
	usuarios usuario = null;
	tareas tarea = null;
	DBPersonas dbpersonas = new DBPersonas();
	DBUsuarios dbusuarios = new DBUsuarios();
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
		opcion = (String)session.getAttribute("OpcionTareasEmpleado");
		usuario = (usuarios)session.getAttribute("usuario");
		empleado = dbpersonas.mostrarpersona(usuario.getId_persona());
		if(opcion.equals("Guardar")){
	   	 	labelFechaInicio = (Label)WinListaTareas.getAttribute("labelFechaInicio");
	   	 	labelFechaFin = (Label)WinListaTareas.getAttribute("labelFechaFin");
	   	 	labelFechaFin = (Label)WinListaTareas.getAttribute("labelFechaFin");
	   	 	textboxDescripcion = (Textbox)WinListaTareas.getAttribute("textboxDescripcion");
			comboEstadoBusqueda.setDisabled(true);
			comboEstadoGuardar.setDisabled(false);
			comboEstadoBusqueda.setVisible(false);
			comboEstadoGuardar.setVisible(true);
			comboEstadoActualizar.setDisabled(true);
			comboEstadoActualizar.setVisible(false);
			CargarListaTareasGuardar();
		}else{
			if(opcion.equals("Buscar")){
				comboEstadoBusqueda.setDisabled(false);
				comboEstadoGuardar.setDisabled(true);
				comboEstadoBusqueda.setVisible(true);
				comboEstadoGuardar.setVisible(false);
				comboEstadoActualizar.setDisabled(true);
				comboEstadoActualizar.setVisible(false);
				CargarListaTareasBusqueda();
			}else{
				comboEstadoBusqueda.setDisabled(true);
				comboEstadoGuardar.setDisabled(true);
				comboEstadoBusqueda.setVisible(false);
				comboEstadoGuardar.setVisible(false);
				comboEstadoActualizar.setDisabled(false);
				comboEstadoActualizar.setVisible(true);
				CargarListaTareasActualizar();
			}
		}
	}
	
	public void onSelect$comboEstadoBusqueda(){
		estado = comboEstadoBusqueda.getText();
	}
	
	public void onSelect$comboEstadoGuardar(){
		estado = comboEstadoGuardar.getText();
	}
	
	public void onSelect$comboEstadoActualizar(){
		estado = comboEstadoActualizar.getText();
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
	
	public void CargarListaTareasGuardar(){
		DBTareas dbtareas = new DBTareas();
		lista_tareas = dbtareas.lista_tareas_guardar(empleado.getId_persona(), comboEstadoGuardar.getText());
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
	
	public void CargarListaTareasActualizar(){
		DBTareas dbtareas = new DBTareas();
		lista_tareas = dbtareas.lista_tareas_edicion(empleado.getId_persona(), comboEstadoActualizar.getText());
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
			CargarListaTareasGuardar();
		}else{
			if(opcion.equals("Buscar")){
				CargarListaTareasBusqueda();
			}else{
				CargarListaTareasActualizar();
			}
		}
	}

	public void onSelect$ListaTareas(){
		tarea = (tareas)lista_tareas.get(ListaTareas.getSelectedIndex());
		if(opcion.equals("Guardar")){
			Session session = Sessions.getCurrent();
			session.setAttribute("tarea_seleccionada", tarea);
			labelFechaInicio.setValue(tarea.getFecha_inicio());
			labelFechaFin.setValue(tarea.getFecha_fin());
			textboxDescripcion.setText(tarea.getDescripcion());
			WinListaTareas.detach();
		}else{
			if(opcion.equals("Buscar")){
				if(centro.getFirstChild()!=null){
					centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("tarea_seleccionada", tarea);
				Window win=(Window) Executions.createComponents("TareasEmpleadoFormulario/DatosTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}else{
				if(centro.getFirstChild()!=null){
					centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("tarea_seleccionada", tarea);
				Window win=(Window) Executions.createComponents("TareasEmpleadoFormulario/DatosTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}
		}
	}
	
	public ListaTareasControlador() {
		// TODO Auto-generated constructor stub
	}

}
