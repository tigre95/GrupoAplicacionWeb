package com.tareas.consultas;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.controlador.entidades.TareasAsignadas;
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.controlador.entidades.personas;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBConsultas;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBUsuarios;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class ConsultaEstadoTarea extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar;
	Button buttonBuscar,buttonListar;
	Listbox listboxtareas;
	Window win_tareas_asig;
	Combobox comboboxTipo, comboNivelTarea;
	
	usuarios usuario;
	personas persona;
	Departamento departamento;
	DBPersonas dbpersonas = new DBPersonas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	int id_departamento = 0;
	String nivel = "";
	String estado = "";
	String criterio = "";
	DBConsultas dbc= new DBConsultas();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Session session = Sessions.getCurrent();
		usuario = (usuarios) session.getAttribute("usuario");
		persona = dbpersonas.mostrarpersona(usuario.getId_persona());
		id_departamento = persona.getId_departamento();
		departamento = dbdepartamento.mostrardepartamento(id_departamento);
		comboboxTipo.setText("Todos");
		comboNivelTarea.setText("Todos");
		buscartareasnorealizadas();
	}
	
	public void onCreate$win_tareas_asig(){
		buscartareasnorealizadas();
	}
		
	public void buscartareasnorealizadas(){
		ArrayList<TareasAsignadas> lista = new ArrayList<TareasAsignadas>();
		criterio = textboxBuscar.getText();
		if(usuario.getId_tipousuario()==2){
			lista = dbc.consultastareasestado_norealizadas_empleado(persona.getId_persona(),estado, nivel, criterio, id_departamento);
		}else{
			lista = dbc.consultastareasestado_norealizadas(estado, nivel, criterio, id_departamento);
		}
		if(lista != null){
			ListModelList<TareasAsignadas> modeloDeDatos= new ListModelList<TareasAsignadas>(lista);
			listboxtareas.setModel(modeloDeDatos);
			//refrescar la lista
			listboxtareas.renderAll();
		}else{
			alert("lista no encontrado");
		}
	}
	
	public void buscartareasrealizadas(){
		ArrayList<TareasAsignadas> lista = new ArrayList<TareasAsignadas>();
		criterio = textboxBuscar.getText();
		if(usuario.getId_tipousuario()==2){
			lista = dbc.consultastareasestado_realizadas_empleado(persona.getId_persona(),estado, nivel, criterio, id_departamento);
		}else{
			lista = dbc.consultastareasestado_realizadas(estado, nivel, criterio, id_departamento);
		}
		if(lista != null){
			ListModelList<TareasAsignadas> modeloDeDatos= new ListModelList<TareasAsignadas>(lista);
			listboxtareas.setModel(modeloDeDatos);
			//refrescar la lista
			listboxtareas.renderAll();
		}else{
			alert("lista no encontrado");
		}
	}
	
	public void onSelect$comboboxTipo(){
		if(comboboxTipo.getText().equals("Todos")){
			estado = "";
		}else if(comboboxTipo.getText().equals("Activa")){
			estado = "A";
		}else if(comboboxTipo.getText().equals("Pendiente")){
			estado = "P";
		}else if(comboboxTipo.getText().equals("Realizada")){
			estado = "R";
		}else if(comboboxTipo.getText().equals("Atrasada")){
			estado = "T";
		}else if(comboboxTipo.getText().equals("Eliminada")){
			estado = "E";
		}
	}
	
	public void onSelect$comboNivelTarea(){
		if(comboNivelTarea.getText().equals("Todos")){
			nivel = "";
		}else if(comboNivelTarea.getText().equals("Alto")){
			nivel = "Alto";
		}else if(comboNivelTarea.getText().equals("Medio")){
			nivel = "Medio";
		}else if(comboNivelTarea.getText().equals("Bajo")){
			nivel = "Bajo";
		}
	}
	
	public void onClick$buttonListar(){
		nivel="";
		estado="";
		criterio="";
		buscartareasnorealizadas();
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		if(comboboxTipo.getText().equals("Todos")){
			buscartareasnorealizadas();
		}else if(comboboxTipo.getText().equals("Activa")){
			buscartareasnorealizadas();
		}else if(comboboxTipo.getText().equals("Pendiente")){
			buscartareasnorealizadas();
		}else if(comboboxTipo.getText().equals("Realizada")){
			buscartareasrealizadas();
		}else if(comboboxTipo.getText().equals("Atrasada")){
			buscartareasrealizadas();
		}else if(comboboxTipo.getText().equals("Eliminada")){
			buscartareasnorealizadas();
		}
	}
}