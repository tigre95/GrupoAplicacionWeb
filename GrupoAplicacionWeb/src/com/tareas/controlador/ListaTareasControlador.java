package com.tareas.controlador;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	Combobox comboEstadoBusqueda, comboEstadoGuardar; 
	
	Label labelFechaInicio;
	Label labelFechaFin;
	Textbox textboxDescripcion;
	personas empleado = null;
	usuarios usuario = null;
	tareas tarea = null;
	DBPersonas dbpersonas = new DBPersonas();
	DBUsuarios dbusuarios = new DBUsuarios();
	DBTareas dbtareas = new DBTareas();
	String opcion;
	String estado = "";
	String fecha_creacion;
	ListModelList<tareas> lista_tareas;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Calendar c1 = new GregorianCalendar();
		fecha_creacion = ""+c1.get(Calendar.YEAR)+"-"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DATE);
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
			CargarListaTareasGuardar();
		}else{
			if(opcion.equals("Buscar")){
				comboEstadoBusqueda.setDisabled(false);
				comboEstadoGuardar.setDisabled(true);
				comboEstadoBusqueda.setVisible(true);
				comboEstadoGuardar.setVisible(false);
				CargarListaTareasBusqueda();
			}else{
				comboEstadoBusqueda.setDisabled(true);
				comboEstadoGuardar.setDisabled(true);
				comboEstadoBusqueda.setVisible(false);
				comboEstadoGuardar.setVisible(false);
				buttonBuscar.setVisible(false);
				CargarListaTareasActualizar();
			}
		}
	}
	
	public void onSelect$comboEstadoBusqueda(){
		if(comboEstadoBusqueda.getText().equals("Activa")){
			estado = "A";
		}else{
			if(comboEstadoBusqueda.getText().equals("Pendiente")){
				estado = "P";
			}else{
				if(comboEstadoBusqueda.getText().equals("Realizada")){
					estado = "R";
				}else{
					if(comboEstadoBusqueda.getText().equals("Atrasada")){
						estado = "T";
					}
				}
			}
		}
	}
	
	public void onSelect$comboEstadoGuardar(){
		if(comboEstadoGuardar.getText().equals("Activa")){
			estado = "A";
		}else{
			if(comboEstadoGuardar.getText().equals("Pendiente")){
				estado = "P";
			}
		}
	}
	
	public void CargarListaTareasBusqueda(){
		DBTareas dbtareas = new DBTareas();
		lista_tareas = dbtareas.lista_tareas_busqueda(empleado.getId_persona(), estado);
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
		lista_tareas = dbtareas.lista_tareas_guardar(empleado.getId_persona(), estado);
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
		lista_tareas = dbtareas.lista_tareas_edicion_empleado(empleado.getId_persona(),fecha_creacion, estado);
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
		String resultado ="";
		tarea = (tareas)lista_tareas.get(ListaTareas.getSelectedIndex());
		if(tarea.getEstado().equals("P")){
			try {
				resultado = dbtareas.actualizarestadotareas(tarea, "A");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
