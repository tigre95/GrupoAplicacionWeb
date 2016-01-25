package com.tareas.controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.cambio.contraseña.Encriptacion;
import com.controlador.entidades.Departamento;
import com.controlador.entidades.TipoUsuario;
import com.controlador.entidades.TipoUsuarios;
import com.controlador.entidades.Usuariodb;
import com.tareas.modelos.DBTiposUsuarios;
import com.tareas.modelos.DBUsuarios;
;



public class PersonaControlador extends GenericForwardComposer<Component>{
	@Wire
	Window winNuevoUsuario;
	Textbox textbox_Usuario,textbox_password,textbox_Cedula,textbox_Nombres,textbox_Apellidos,textbox_Direccion,textbox_Email;
	Button button_Registrar,button_Cancelar;
	Combobox Combobox_TipoUsuario,Combobox_TipoDept;
						
@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	
	// TODO Auto-generated method stub
			super.doAfterCompose(comp);
			DBUsuarios dbu=new DBUsuarios();
			//array list para el combo de tipo usuario
			ArrayList <TipoUsuarios> listatusu = null;
			listatusu=dbu.listarTipousuario();
		
			if(listatusu != null)
			{
				ListModelList<TipoUsuarios> modelo= new ListModelList<TipoUsuarios>(listatusu);
				Combobox_TipoUsuario.setModel(modelo);
			}
			else
			{
				alert("No hay elementos que mostrar");
			}
			
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
		
	super.doAfterCompose(comp);
	
}

public void onCreate$winNuevoUsuario(){
}

public void vaciar(){
	textbox_Nombres.setValue("");
	textbox_Apellidos.setValue("");
	textbox_Cedula.setText("");
	textbox_Direccion.setValue("");
	textbox_Email.setValue("");
	textbox_Usuario.setValue("");
	textbox_password.setValue("");
	Combobox_TipoUsuario.setText("");
}

public void CargarTipoUsuarios(){
	DBTiposUsuarios dbtu= new DBTiposUsuarios();
	ArrayList<TipoUsuarios> lista = dbtu.CargarTipoUsuarios();
	ListModelList<TipoUsuarios> modeloDeDatos= new ListModelList<TipoUsuarios>(lista);
	Combobox_TipoUsuario.setModel(modeloDeDatos);
}



public String EncriptarPassword(String dpassword){
	Encriptacion e=new Encriptacion("Encriptar");
	String passwordEncriptado=e.encrypt(dpassword);
	return passwordEncriptado;
}
public void onClick$button_Registrar(){
	DBUsuarios dbu=new DBUsuarios();
	if(dbu.validarUsuario(textbox_Cedula.getValue(),textbox_Usuario.getValue())){
		alert("Usuario y/o Cedula ya existen");
	}
	else{
//		String password = textbox_password.getText();
//		MessageDigest md=null;
//		String encriptado=null; 
//		try {				
//			md=MessageDigest.getInstance("SHA-1");
//			md.update(password.getBytes());
//			byte[] mb = md.digest();
//			mb = md.digest();
//			encriptado=String.valueOf(Hex.encodeHex(mb));
//		} 
//		catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Usuariodb us=new Usuariodb();
		us.setNombres(textbox_Nombres.getValue());
		us.setApellidos(textbox_Apellidos.getValue());
		us.setCedula(textbox_Cedula.getText());
		us.setEmail(textbox_Email.getValue());
		us.setDireccion(textbox_Direccion.getValue());
		us.setAlias(textbox_Usuario.getValue());
		us.setDpasssword(EncriptarPassword(textbox_password.getValue()));
		us.setId_tipousuario((int) Combobox_TipoUsuario.getSelectedItem().getValue());
		us.setIdTipoDepartamento((int) Combobox_TipoDept.getSelectedItem().getValue());
		boolean resultado= false;
		resultado=dbu.CrearUsuario(us);
		if(resultado){
			alert("Guardado Exitosamente");
			winNuevoUsuario.detach();

		vaciar();
		
		}else{
			alert("Error al guardar usuario");
		}
	}
	
}
	


public void onClick$button_Cancelar(){
	winNuevoUsuario.detach();
}
}
