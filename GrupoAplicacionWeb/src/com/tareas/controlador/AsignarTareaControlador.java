package com.tareas.controlador;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.East;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.controlador.entidades.personas;
import com.controlador.entidades.usuarios;
import com.tareas.archivos.Util;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class AsignarTareaControlador extends GenericForwardComposer<Component>{

	@Wire
	Window WinAsignarTarea;
	Button buttonVer,buttonArchivoAdjunto,buttonVerHistorial,
	buttonOpcion,buttonCancelar;
	Label labelNombreEmpleado,labelDatosUsuario,labelFechaInicio;
	Textbox textboxDescripcion,textboxComentario;
	Datebox dateboxFechaFin;
	Combobox comboNivelTarea;
	Center centro= null;
	East este=null;
	
	
	usuarios usuario=null;
	personas persona=null;
	Departamento departamento=null;
	DBPersonas dbpersonas = new DBPersonas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	String opcion;
	String fecha_creacion;
	String dir_raiz="C:/ArchivosAplicacionWeb/";
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinAsignarTarea.getAttribute("centro"); 
		este = (East)WinAsignarTarea.getAttribute("este");
		Session session = Sessions.getCurrent();
		opcion = (String)session.getAttribute("OpcionTareasJefe");
		usuario = (usuarios) session.getAttribute("usuario");
		persona = dbpersonas.mostrarpersona(usuario.getId_persona());
		departamento = dbdepartamento.mostrardepartamento(persona.getId_departamento());
		labelDatosUsuario.setValue(persona.getNombres()+" "+persona.getApellidos()+" "+
		departamento.getDescripcion());
		if(opcion.equals("Guardar")){
			buttonOpcion.setLabel("Guardar");
		}else{
			if(opcion.equals("Buscar")){
				buttonOpcion.setVisible(false);
			}else{
				buttonOpcion.setLabel("Actualizar");
			}
		}
		Calendar c1 = new GregorianCalendar();
		fecha_creacion = ""+c1.get(Calendar.YEAR)+"-"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DATE);
		labelFechaInicio.setValue(fecha_creacion);
		cargarComboNivelTareas();
		File directorio=new File(dir_raiz); 
		directorio.mkdir(); 
	}
	
	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = (diferenciaEn_ms / (1000 * 60 * 60 * 24))+1;
		return (int) dias;
		}
	
	public void onChange$dateboxFechaFin(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date date = formatter.parse(fecha_creacion);
			if (diferenciaEnDias(dateboxFechaFin.getValue(),date)<1){
				alert("Fechas incoherentes");
				dateboxFechaFin.setText("");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public AsignarTareaControlador() {
		// TODO Auto-generated constructor stub
	}
	
	public void cargarComboNivelTareas(){
		ListModelList<NivelTareas> niveltareas;
		DBnivelTarea dbniveltarea = new DBnivelTarea();
		niveltareas = dbniveltarea.cargar_NivelTareas();
		if(niveltareas.isEmpty()==false){
			comboNivelTarea.setModel(niveltareas);
		}else{
			alert("no existen temas");
		}
	}
	
	public void onClick$buttonVer(){
		Session session = Sessions.getCurrent();
		session.setAttribute("departamento", departamento);
		if(este.getFirstChild()!=null){
		  	 este.removeChild(este.getFirstChild());
		  	 }
		   	   	Window win=(Window) Executions.createComponents("TareasJefeFormulario/ListaEmpleados.zul", este, null );
		   	   	win.setAttribute("labelNombreEmpleado", labelNombreEmpleado);
		   	   	win.setAttribute("buttonVerHistorial", buttonVerHistorial);
		   	   	//WinAsignarTarea.detach();
	}
	
	public void onClick$buttonVerHistorial(){
		if(este.getFirstChild()!=null){
		  	 este.removeChild(este.getFirstChild());
		  	 }
		   	   	Window win=(Window) Executions.createComponents("TareasJefeFormulario/ListaTareasEmpleado.zul", este, null );
		   	   	//WinAsignarTarea.detach();
	}
	
	public void onUpload$buttonArchivoAdjunto(org.zkoss.zk.ui.event.UploadEvent event){
		boolean respuesta;
		Util util = new Util();
		org.zkoss.util.media.Media media = event.getMedia();
		String ruta_tarea = dir_raiz+labelDatosUsuario.getValue()+"/";
		String nombre_tarea = "tarea1.docx";
		File directorio=new File(ruta_tarea); 
		directorio.mkdir(); 
		respuesta = util.uploadFile(media,ruta_tarea,nombre_tarea);
		if(respuesta == true){
			alert("archivo subido correctamente");
		}else{
			alert("error");
		}
	}
}
