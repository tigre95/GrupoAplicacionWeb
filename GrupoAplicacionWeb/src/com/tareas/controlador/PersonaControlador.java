package com.tareas.controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
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

public class PersonaControlador extends GenericForwardComposer<Component>{
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Wire
	Window winNuevoUsuario;
	Textbox textbox_Usuario,textbox_password,textbox_Cedula,textbox_Nombres,textbox_Apellidos,
	textbox_Direccion,textbox_Email;
	Button button_Registrar,button_Cancelar;
	Combobox Combobox_TipoUsuario,Combobox_TipoDept;
	Label label_validar_correo, label_validar_cedula;
						
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
	else if(label_validar_cedula.getValue().equals("cédula incorrecta")){
		alert("cédula incorrecta");
	}
	else if(label_validar_correo.getValue().equals("correo incorrecto")){
		alert("correo incorrecto");
	}else if(textbox_Usuario.getText().equals("")==true || textbox_password.getText().equals("")==true ||
			textbox_Cedula.getText().equals("")==true || textbox_Nombres.getText().equals("")==true ||
			textbox_Apellidos.getText().equals("")==true || textbox_Direccion.getText().equals("")==true ||
			textbox_Email.getText().equals("")==true || Combobox_TipoDept.getText().equals("")==true ||
			Combobox_TipoUsuario.getText().equals("")==true){
		alert("llenar todos los campos");
	}else{
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
	
public void onChange$textbox_Cedula(){
		String resultado;
		resultado = validacedula(textbox_Cedula.getValue());
		label_validar_cedula.setValue(resultado);	
}
	
public void onChange$textbox_Email(){
		String resultado;
		resultado = validateEmail(textbox_Email.getValue());
		label_validar_correo.setValue(resultado);
}
	
}
