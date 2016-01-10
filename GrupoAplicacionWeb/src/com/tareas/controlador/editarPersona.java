package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;


import com.controlador.entidades.DatosUsuarioss;
import com.controlador.entidades.Departamento;
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.controlador.entidades.datosusuarios;
import com.tareas.modelos.DBTiposUsuarios;
import com.tareas.modelos.DBUsuarios;


public class editarPersona extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar,txtNombres,txtApellidos,txtced,txtEmail,txtDireccion,txtUsuario,txtPassword,txtUsuarioedit;
	Textbox txtNuevoPassword,txtConfPassword;
	Button buttonBuscar,buttonListar,btnCancelarreU,btnGuardarreU,btnCancelarreC,btnCancelarre,btnGuardarre;
	Listbox listboxUsuarios;
	Combobox cbbTipoUsuario,Combobox_TipoDept;
	Toolbar toolOpciones;
	Toolbarbutton toolbarbuttonEliminar,toolbarbuttonEditar,toolbarbuttonContrasena;
	Groupbox gpb_lista,gpb_buscar;
	Grid grilla;
	Window win_editarUsuario;
	
	Usuariodb usuario;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
	}
	
	public void onClick$btnCancelarre(){
		toolOpciones.setVisible(false);
		gpb_buscar.setVisible(true);
		gpb_lista.setVisible(true);
		grilla.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onClick$btnGuardarre(){
		if(txtNuevoPassword.getValue().equals(txtConfPassword.getValue())){
			DBUsuarios dbu=new DBUsuarios();
			boolean resultado= false;
			resultado=dbu.modificarContrasena(usuario.getId_persona(), txtUsuarioedit.getValue(), txtConfPassword.getValue());
			if(resultado){
				alert("Contraseña Modificada!!");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
				buscarUsuarios("");
			}else{
				alert("Error al Modificar Contraseña!!");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
				buscarUsuarios("");
			}
		}
		else{
			alert("Contraseñas no coinciden!!");
		}
	}
	
	public void onClick$btnAceptar(){
		DatosUsuarioss u=null;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(DatosUsuarioss) s.getAttribute("DatosUsuarioss");
		DBUsuarios dbu=new DBUsuarios();
		Usuariodb us2=dbu.logonear(txtUsuario.getValue(), txtPassword.getValue());
		if(us2.getId_tipousuario()==15){
			if (us2.getCedula().equals(u.getUsuario().getPersona().getCedula())){
				grilla.setVisible(false);
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(false);
				gpb_lista.setVisible(false);
				txtUsuarioedit.setValue(usuario.getAlias());
			}
			else{
				alert("Usuario y/o Clave Incorrecta! Intente Nuevamente!");
			}
		}else{
			alert("Usuario y/o Clave Incorrecta! Intente Nuevamente!");
		}
	}
	
	
	public void onClick$toolbarbuttonContrasena(){
		if(listboxUsuarios.getSelectedItem() != null){
			alert("Necesita permisos de dministrador!");
			grilla.setVisible(false);
			toolOpciones.setVisible(false);
			gpb_buscar.setVisible(false);
			gpb_lista.setVisible(false);
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void onClick$btnCancelarreC(){
		toolOpciones.setVisible(false);
		gpb_buscar.setVisible(true);
		gpb_lista.setVisible(true);
		grilla.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onCreate$win_editarUsuario(){
	}
	
	public void CargarTipoUsuarios(){
		
		DBUsuarios dbu=new DBUsuarios();
		//array list para el combo de tipo usuario
		ArrayList <TipoUsuarios> listatusu = null;
		listatusu=dbu.listarTipousuario();
	
		if(listatusu != null)
		{
			ListModelList<TipoUsuarios> modelo= new ListModelList<TipoUsuarios>(listatusu);
			cbbTipoUsuario.setModel(modelo);
		}
		else
		{
			alert("No hay elementos que mostrar");
		}
		
	}
	
	public void CargarDepartamento(){
		DBUsuarios dbu=new DBUsuarios();
		
	ArrayList <Departamento> departamento = null;
	departamento=dbu.listarSucursal();
	if(departamento != null)
	{
		ListModelList<Departamento> mode= new ListModelList<Departamento>(departamento);
		Combobox_TipoDept.setModel(mode);
	}
	else
	{
		alert("No hay elementos que mostrar");
	}
	}
	
	
	public void buscarUsuarios(String criterio){
		DBUsuarios dbu= new DBUsuarios();
		ArrayList<Usuariodb> lista = dbu.buscarUsuarios(criterio);
		ListModelList<Usuariodb> modeloDeDatos= new ListModelList<Usuariodb>(lista);
		listboxUsuarios.setModel(modeloDeDatos);
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonListar(){
		buscarUsuarios("");
		toolOpciones.setVisible(false);
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscarUsuarios(textboxBuscar.getValue());
		toolOpciones.setVisible(false);
		buttonListar.setDisabled(false);
	}

	public void onSelect$listboxUsuarios(){
		toolOpciones.setVisible(true);
		usuario=listboxUsuarios.getSelectedItem().getValue();
		DBUsuarios du=new DBUsuarios();
		DBTiposUsuarios dbu=new DBTiposUsuarios();
		usuario.setId_persona(du.ObtenerIdPersona(usuario.getCedula()));
		usuario.setId_tipousuario(dbu.ObtenerIdTipoUs(usuario.getDescripcionTU()));
		usuario.setIdTipoDepartamento(dbu.ObtenerIdTipodept(usuario.getDescripciondep()));
	}
	
	public void onClick$toolbarbuttonEliminar(){
		if(listboxUsuarios.getSelectedItem() != null){
			if(usuario.getId_tipousuario()==16){
				DBUsuarios dbu=new DBUsuarios();
				if(dbu.validarEliminacion()){
					if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario.getNombres()+" "+usuario.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
						return;
					}			
					EditarEliminarUsuario(usuario, 2,"Eliminado","Eliminar");	
					buscarUsuarios("");
				}
				else{
					alert("No se puede Eliminar Usuario de Tipo Administrador");
					buscarUsuarios("");
				}
			}
			else{
				if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario.getNombres()+" "+usuario.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
					return;
				}			
				EditarEliminarUsuario(usuario, 2,"Eliminado","Eliminar");	
				buscarUsuarios("");
			}
			
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void EditarEliminarUsuario(Usuariodb us,int opcion,String mensaje1,String mensaje2){
		DBUsuarios dbu=new DBUsuarios();
		boolean resultado= false;
		resultado= dbu.editareliminarUsuario(us, opcion);			
		if(resultado){
			alert(""+mensaje1+" Exitosamente");		
		}else{
			alert("Error al "+mensaje2+" Usuario");
		}	
	}
	
	public void onClick$toolbarbuttonEditar(){
		if(listboxUsuarios.getSelectedItem() != null){
			toolOpciones.setVisible(false);
			gpb_lista.setVisible(false);
			gpb_buscar.setVisible(false);
			grilla.setVisible(true);
			CargarTipoUsuarios();
			CargarDepartamento();
			cbbTipoUsuario.setText(usuario.getDescripcionTU());
			cbbTipoUsuario.setReadonly(true);
			Combobox_TipoDept.setText(usuario.getDescripciondep());
			Combobox_TipoDept.setReadonly(true);
			
			txtNombres.setValue(usuario.getNombres());
			txtApellidos.setValue(usuario.getApellidos());
			txtDireccion.setValue(usuario.getDireccion());
			txtEmail.setValue(usuario.getEmail());
			txtced.setValue(usuario.getCedula());
	
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void onClick$btnCancelarreU(){
		toolOpciones.setVisible(false);
		gpb_lista.setVisible(true);
		gpb_buscar.setVisible(true);
		grilla.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onClick$btnGuardarreU(){
		Usuariodb us2=new Usuariodb();
		us2.setApellidos(txtApellidos.getValue());
		us2.setNombres(txtNombres.getValue());
		us2.setCedula(txtced.getValue());
		us2.setDireccion(txtDireccion.getValue());
		us2.setEmail(txtEmail.getValue());
		us2.setDescripcionTU(cbbTipoUsuario.getValue());
		us2.setDescripciondep(Combobox_TipoDept.getValue());
		
		DBUsuarios du2=new DBUsuarios();
		DBTiposUsuarios dbu2=new DBTiposUsuarios();
		us2.setId_persona(usuario.getId_persona());
		us2.setId_tipousuario(dbu2.ObtenerIdTipoUs(us2.getDescripcionTU()));
		us2.setIdTipoDepartamento(dbu2.ObtenerIdTipodept(us2.getDescripciondep()));
		
		if(usuario.getId_tipousuario()==14){
			if(usuario.getId_tipousuario()==us2.getId_tipousuario()){
				if(du2.validarEdicion(us2)){
					alert("No se puede Modificar! Usuario ya existe!");
				}
				else{
					if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
						return;
					}			
					EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
					toolOpciones.setVisible(false);
					gpb_buscar.setVisible(true);
					gpb_lista.setVisible(true);
					grilla.setVisible(false);
						buscarUsuarios("");
				}
			}
			else{
				DBUsuarios dbu=new DBUsuarios();
				if(dbu.validarEliminacion()){
					if(du2.validarEdicion(us2)){
						alert("No se puede Modificar! Usuario ya existe!");
					}
					else{
						if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
							return;
						}			
						EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
						toolOpciones.setVisible(false);
						gpb_buscar.setVisible(true);
						gpb_lista.setVisible(true);
						grilla.setVisible(false);
						buscarUsuarios("");
					}
				}
				else{
					
					alert("No se puede Modificar Usuario de Tipo Administrador");
					buscarUsuarios("");
					toolOpciones.setVisible(false);
					gpb_buscar.setVisible(true);
					gpb_lista.setVisible(true);
					grilla.setVisible(false);
						buscarUsuarios("");
				}
			}
			
		}
		else{
			if(du2.validarEdicion(us2)){
				alert("No se puede Modificar! Usuario ya existe!");
			}
			else{
				if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
					return;
				}			
				EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
					buscarUsuarios("");
			}
			
		}
		
	}
}
