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
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.personas;
import com.tareas.modelos.DBPersonas;

public class ListaEmpleadosControlador extends GenericForwardComposer<Component>{

	@Wire
	Listbox ListaEmpleados;
	Window WinListaEmpleados;
	Center centro= null;
	Label labelNombreEmpleado = null;
	Button buttonVerHistorial = null;
	
	Departamento departamento = null;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinListaEmpleados.getAttribute("centro");
		labelNombreEmpleado = (Label)WinListaEmpleados.getAttribute("labelNombreEmpleado");
		buttonVerHistorial = (Button)WinListaEmpleados.getAttribute("buttonVerHistorial");
		Session session = Sessions.getCurrent();
		departamento = (Departamento) session.getAttribute("departamento");
		CargarListaPersonas();
	}

	public void CargarListaPersonas(){
		ListModelList<personas> lista_personas;
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

	public void onSelect$ListaEmpleados(){
		//if(centro.getFirstChild()!=null){
		//	centro.removeChild(centro.getFirstChild());
		//}
			personas empleado  = ListaEmpleados.getSelectedItem().getValue();
			Session session;
			session=Sessions.getCurrent();
			session.setAttribute("empleado_seleccionado", empleado);
			//Window win = (Window)Executions.createComponents("TareasJefeFormulario/AsignarTarea.zul", centro, null);
			labelNombreEmpleado.setValue(empleado.getNombres()+" "+empleado.getApellidos());
			buttonVerHistorial.setDisabled(false);
			WinListaEmpleados.detach();
	}
	
	public ListaEmpleadosControlador() {
		// TODO Auto-generated constructor stub
		
	}

}
