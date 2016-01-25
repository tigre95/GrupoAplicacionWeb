package com.tareas.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.personas;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBdepartamento;

public class ListaEmpleadosControlador extends GenericForwardComposer<Component>{

	@Wire
	Listbox ListaEmpleados;
	Window WinListaEmpleados;
	Center centro= null;
	Textbox textboxNombres;
	Textbox textboxApellidos;
	Textbox textboxCedula;
	Button button_Buscar;
	
	Label labelNombreEmpleado = null;
	usuarios usuario=new usuarios();
	personas empleado = new personas();
	personas jefe = new personas();
	Departamento departamento = null;
	DBPersonas dbpersonas = new DBPersonas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	String opcion;
	ListModelList<personas> lista_personas;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinListaEmpleados.getAttribute("centro");
		labelNombreEmpleado = (Label)WinListaEmpleados.getAttribute("labelNombreEmpleado");
		Session session = Sessions.getCurrent();
		opcion = (String)session.getAttribute("OpcionTareasJefe");
		usuario = (usuarios) session.getAttribute("usuario");
		jefe = dbpersonas.mostrarpersona(usuario.getId_persona());
		departamento = dbdepartamento.mostrardepartamento(jefe.getId_departamento());
		CargarListaPersonas();
	}

	public void CargarListaPersonas(){
		
		DBPersonas dbpersonas = new DBPersonas();
		lista_personas = dbpersonas.cargarpersona(departamento.getId_tipodepartamento());
		if(lista_personas != null){
			ListModelList<personas> listmodel = new ListModelList<personas>(lista_personas);
			ListaEmpleados.setModel(listmodel);
			//refrescar la lista
			ListaEmpleados.renderAll();
		}else{
			alert("No existen datos");
		}
	}
	
	public void CargarListaPersonasfiltrada(){
		
		DBPersonas dbpersonas = new DBPersonas();
		lista_personas = dbpersonas.cargarpersonafiltrado(departamento.getId_tipodepartamento(), textboxCedula.getText(),
				textboxNombres.getText(), textboxApellidos.getText());
		if(lista_personas != null){
			ListModelList<personas> listmodel = new ListModelList<personas>(lista_personas);
			ListaEmpleados.setModel(listmodel);
			//refrescar la lista
			ListaEmpleados.renderAll();
		}else{
			alert("No existen datos");
		}
	}

	public void onSelect$ListaEmpleados(){
		empleado = (personas)lista_personas.get(ListaEmpleados.getSelectedIndex());
		if(opcion.equals("Guardar")){
			labelNombreEmpleado.setValue(empleado.getNombres()+" "+empleado.getApellidos());
			Session session = Sessions.getCurrent();
			session.setAttribute("empleado_seleccionado", empleado);
			WinListaEmpleados.detach();
		}else{
			if(opcion.equals("Buscar")){
				if(centro.getFirstChild()!=null){
				  	 centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("empleado_seleccionado", empleado);
				Window win=(Window) Executions.createComponents("TareasJefeFormulario/BuscarTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}else{
				if(centro.getFirstChild()!=null){
				  	 centro.removeChild(centro.getFirstChild());
				}
				Session session = Sessions.getCurrent();
				session.setAttribute("empleado_seleccionado", empleado);
				Window win=(Window) Executions.createComponents("TareasJefeFormulario/BuscarTarea.zul", centro, null );  		
		  		win.setTitle("Lista de Tareas");
			}
		}
		
		
	}
	
	public void onClick$button_Buscar(){
		CargarListaPersonasfiltrada();
	}
	
	public ListaEmpleadosControlador() {
		// TODO Auto-generated constructor stub
		
	}

}
