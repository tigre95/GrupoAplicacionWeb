package com.tareas.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Center;
import org.zkoss.zul.East;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.personas;
import com.controlador.entidades.tareas;
import com.tareas.modelos.DBTareas;

public class ListaTareasEmpleadoControlador extends GenericForwardComposer<Component>{

	@Wire
	Listbox ListaTareasEmpleados;
	Window WinListaTareasEmpleado;
	Center centro= null;
	East este;
	Label labelNombreEmpleado = null;
	
	personas empleado = new personas();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinListaTareasEmpleado.getAttribute("centro");
		Session session = Sessions.getCurrent();
		empleado = (personas) session.getAttribute("empleado_seleccionado");
		labelNombreEmpleado.setValue(empleado.getNombres()+" "+empleado.getApellidos());
		//CargarListaTareas();
	}

	public void CargarListaTareasBusqueda(){
		ListModelList<tareas> lista_tareas;
		DBTareas dbtareas = new DBTareas();
		//lista_tareas = dbtareas.lista_tareas_busqueda(id_persona, estado)(1);
		//if(lista_tareas != null){
			//ListModelList<tareas> listmodel = new ListModelList<tareas>(lista_tareas);
			//ListaTareasEmpleados.setModel(listmodel);
			//refrescar la lista
			//ListaTareasEmpleados.renderAll();
		//}else{
			//alert("No existen datos");
		//}
	}

	
	public ListaTareasEmpleadoControlador() {
		// TODO Auto-generated constructor stub
	}

}
