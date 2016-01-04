package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Usuariodb;
import com.tareas.modelos.DBUsuarios;



public class buscarPersonaControlador extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar;
	Button buttonBuscar,buttonListar;
	Listbox listboxUsuarios;
	Window win_listaUsuarios;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	
	public void onCreate$win_listaUsuarios(){
		/*Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){*/
			   buscarUsuarios("");
				buttonListar.setDisabled(true);
		/*   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }*/
	}
		
	public void buscarUsuarios(String criterio){
		DBUsuarios dbu= new DBUsuarios();
		ArrayList<Usuariodb> lista = dbu.buscarUsuarios(criterio);
		ListModelList<Usuariodb> modeloDeDatos= new ListModelList<Usuariodb>(lista);
		listboxUsuarios.setModel(modeloDeDatos);
	}
	
	public void onClick$buttonListar(){
		buscarUsuarios("");
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscarUsuarios(textboxBuscar.getValue());
		buttonListar.setDisabled(false);
	}
	
}
