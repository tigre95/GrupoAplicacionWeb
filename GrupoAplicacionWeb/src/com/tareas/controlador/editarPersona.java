package com.tareas.controlador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.cambio.contraseña.Encriptacion;
import com.controlador.entidades.DatosUsuarioss;
import com.controlador.entidades.Departamento;
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.controlador.entidades.datosusuarios;
import com.controlador.entidades.permisos;
import com.controlador.entidades.tiposusuarios;
import com.controlador.entidades.usuarios;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBTiposUsuarios;
import com.tareas.modelos.DBUsuarios;

public class editarPersona extends GenericForwardComposer<Component>{
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Wire
	Textbox textboxBuscar,txtNombres,txtApellidos,txtced,txtEmail,txtDireccion,textbox_Usuario,textbox_password,txtUsuarioedit;
	Textbox txtNuevoPassword,txtConfPassword;
	Button buttonBuscar,buttonListar,btnCancelarreU,btnGuardarreU,btnCancelarreC,btnCancelarre,btnGuardarre;
	Listbox listboxUsuarios;
	Combobox cbbTipoUsuario,Combobox_TipoDept;
	Toolbar toolOpciones;
	Toolbarbutton toolbarbuttonEliminar,toolbarbuttonEditar,toolbarbuttonContrasena;
	Groupbox gpb_lista,gpb_buscar;
	Grid grilla;
	Window win_editarUsuario;
	Label label_validar_correo, label_validar_cedula;
	
	Usuariodb usuario_seleccionado;
	usuarios usuario=null;
	tiposusuarios tipousuario = null;
	permisos permiso_personas = null;
	DBTiposUsuarios dbtiposusuarios = new DBTiposUsuarios();
	DBPermisos dbpermisos = new DBPermisos();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		grilla.setVisible(false);
		buscarUsuarios("");
		
		Session session = Sessions.getCurrent();
		usuario = (usuarios) session.getAttribute("usuario");
		if(usuario!=null){
			tipousuario = dbtiposusuarios.mostrartipousuarios(usuario.getId_tipousuario());
			permiso_personas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "personas");
			if(permiso_personas.getEditar()==0){
				toolbarbuttonEditar.setVisible(false);
			}else{
				toolbarbuttonEditar.setVisible(true);
			}
			if(permiso_personas.getEliminar()==0){
				toolbarbuttonEliminar.setVisible(false);
			}else{
				toolbarbuttonEliminar.setVisible(true);
			}
		}else{	
			Executions.sendRedirect("login.zul");
		}
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
			resultado=dbu.modificarContrasena(usuario_seleccionado.getId_persona(), txtUsuarioedit.getValue(), txtConfPassword.getValue());
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
		Usuariodb us2=dbu.logonear(textbox_Usuario.getValue(), textbox_password.getValue());
		if(us2.getId_tipousuario()==2){
			if (us2.getCedula().equals(u.getUsuario().getPersona().getCedula())){
				grilla.setVisible(false);
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(false);
				gpb_lista.setVisible(false);
				txtUsuarioedit.setValue(usuario_seleccionado.getAlias());
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
		usuario_seleccionado=listboxUsuarios.getSelectedItem().getValue();
		DBUsuarios du=new DBUsuarios();
		DBTiposUsuarios dbu=new DBTiposUsuarios();
		usuario_seleccionado.setId_persona(du.ObtenerIdPersona(usuario_seleccionado.getCedula()));
		usuario_seleccionado.setId_tipousuario(dbu.ObtenerIdTipoUs(usuario_seleccionado.getDescripcionTU()));
		usuario_seleccionado.setIdTipoDepartamento(dbu.ObtenerIdTipodept(usuario_seleccionado.getDescripciondep()));
	}
	
	public void onClick$toolbarbuttonEliminar(){
		if(listboxUsuarios.getSelectedItem() != null){
			if(usuario_seleccionado.getId_tipousuario()==3){
				DBUsuarios dbu=new DBUsuarios();
				if(dbu.validarEliminacion()){
					if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario_seleccionado.getNombres()+" "+usuario_seleccionado.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
						return;
					}			
					EditarEliminarUsuario(usuario_seleccionado, 2,"Eliminado","Eliminar");	
					buscarUsuarios("");
				}
				else{
					alert("No se puede Eliminar Usuario de Tipo Administrador");
					buscarUsuarios("");
				}
			}
			else{
				if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario_seleccionado.getNombres()+" "+usuario_seleccionado.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
					return;
				}			
				EditarEliminarUsuario(usuario_seleccionado, 2,"Eliminado","Eliminar");	
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
			cbbTipoUsuario.setText(usuario_seleccionado.getDescripcionTU());
			cbbTipoUsuario.setReadonly(true);
			Combobox_TipoDept.setText(usuario_seleccionado.getDescripciondep());
			Combobox_TipoDept.setReadonly(true);
			
			txtNombres.setValue(usuario_seleccionado.getNombres());
			txtApellidos.setValue(usuario_seleccionado.getApellidos());
			txtDireccion.setValue(usuario_seleccionado.getDireccion());
			txtEmail.setValue(usuario_seleccionado.getEmail());
			txtced.setValue(usuario_seleccionado.getCedula());
			textbox_Usuario.setValue(usuario_seleccionado.getAlias());
			textbox_password.setValue(usuario_seleccionado.getDpasssword());
	
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
	
	public String EncriptarPassword(String dpassword){
		Encriptacion e=new Encriptacion("Encriptar");
		String passwordEncriptado=e.encrypt(dpassword);
		return passwordEncriptado;
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
		us2.setId_persona(usuario_seleccionado.getId_persona());
		us2.setId_tipousuario(dbu2.ObtenerIdTipoUs(us2.getDescripcionTU()));
		us2.setIdTipoDepartamento(dbu2.ObtenerIdTipodept(us2.getDescripciondep()));
		us2.setAlias(textbox_Usuario.getValue());
		us2.setDpasssword(EncriptarPassword(textbox_password.getValue()));
		if(txtNombres.getText().equals("")==false && txtApellidos.getText().equals("")==false && 
			txtced.getText().equals("")==false && txtEmail.getText().equals("")==false && 
			txtDireccion.getText().equals("")==false && textbox_Usuario.getText().equals("")==false && 
			textbox_password.getText().equals("")==false && Combobox_TipoDept.getText().equals("")==false &&
			cbbTipoUsuario.getText().equals("")==false){
			if(usuario_seleccionado.getId_tipousuario()==1){
				if(usuario_seleccionado.getId_tipousuario()==us2.getId_tipousuario()){
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
		}else{
			alert("LLene todos los campos");
		}
	}
	
	public static String validacedula(String x){
	    int suma=0;
	    if(x.length()==9){
	      //System.out.println("Ingrese su cedula de 10 digitos");
	      return "cédula incorrecta";
	    }else{
	      int a[]=new int [x.length()/2];
	      int b[]=new int [(x.length()/2)];
	      int c=0;
	      int d=1;
	      for (int i = 0; i < x.length()/2; i++) {
	        a[i]=Integer.parseInt(String.valueOf(x.charAt(c)));
	        c=c+2;
	        if (i < (x.length()/2)-1) {
	          b[i]=Integer.parseInt(String.valueOf(x.charAt(d)));
	          d=d+2;
	        }
	      }
	      for (int i = 0; i < a.length; i++) {
	        a[i]=a[i]*2;
	        if (a[i] >9){
	          a[i]=a[i]-9;
	        }
	        suma=suma+a[i]+b[i];
	      } 
	      int aux=suma/10;
	      int dec=(aux+1)*10;
	      if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length()-1))))
	        return "cédula correcta";
	      else
	        if(suma%10==0 && x.charAt(x.length()-1)=='0'){
	          return "cédula correcta";
	        }else{
	          return "cédula incorrecta";
	        }
	    }
	}
	  
	public static String validateEmail(String email) {
		boolean respuesta;  
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
	    Matcher matcher = pattern.matcher(email);
	    respuesta = matcher.matches();
	    if(respuesta==true){
	    	return "correo correcto";
	    }else{
	    	return "correo incorrecto";
	    }
	}
	
	public void onChange$txtced(){
		String resultado;
		resultado = validacedula(txtced.getValue());
		label_validar_cedula.setValue(resultado);
		
	}
	
	public void onChange$txtEmail(){
		String resultado;
		resultado = validateEmail(txtEmail.getValue());
		label_validar_correo.setValue(resultado);
		
	}
	
}
