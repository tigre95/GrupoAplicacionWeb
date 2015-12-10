package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class DepartamentoControlador extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
		@Wire
		Textbox   textbox_descripcion;
		Button button_Registrar;
		Window winNuevdepartamento;
		int idtipo;
			
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);
			
		}
		public void onCreate$winNuevdepartamento(){
			idtipo=0;
			Departamento Departamento = (Departamento) winNuevdepartamento.getAttribute("Departamento");
			if(Departamento != null){
				idtipo=Departamento.getId_tipodepartamento();
				textbox_descripcion.setText(Departamento.getDescripcion());
					}
		}
			
		public void onClick$button_Registrar(){
			Departamento Departamento = (Departamento) winNuevdepartamento.getAttribute("Departamento");		
			if(Departamento !=null ){
				guardar(Departamento,1);			
			}else{
				Departamento = new Departamento();
				guardar(Departamento,2);		
			}
			
		}
		
			
		public void guardar(Departamento Departamento, int opcion){
		DBdepartamento dbcategorias = new DBdepartamento();
			ArrayList<Departamento> validar = dbcategorias.ValidarDepartamento(textbox_descripcion.getText(),idtipo);
			if(validar.size()>=1){
				alert("Error!! Categoría ya existe!!");
			}else{
				Departamento.setDescripcion(textbox_descripcion.getText());
				boolean resultado= false;		
				if(opcion == 1){
					resultado= dbcategorias.editardepartamento(Departamento);			
				}else if(opcion==2){
					resultado= dbcategorias.creardepartamento(Departamento);
				}
				
				if(resultado){
					alert("Guardado Exitosamente");
					winNuevdepartamento.detach();
				}else{
					alert("Error al guardar usuario");
				}
			}
		}
		
		
	}



