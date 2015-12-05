package com.tareas.controlador;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TipoUsuarioControlador extends GenericForwardComposer<Component>{
	//enlace a los componentes de la interfaz
		@Wire
		Textbox  textbox_descripcion;
		Button button_Registrar;
		Window winNuevoTipoUsuario;
}