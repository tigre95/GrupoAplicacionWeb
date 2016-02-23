package com.tareas.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

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
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.controlador.entidades.permisos;
import com.controlador.entidades.personas;
import com.controlador.entidades.tareas;
import com.controlador.entidades.usuarios;
import com.tareas.archivos.Util;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTareas;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class AsignarTareaControlador extends GenericForwardComposer<Component>{

	@Wire
	Window WinAsignarTarea;
	Button buttonVer,buttonArchivoAdjunto,
	buttonOpcion,buttonCancelar,buttonEliminar;
	Label labelNombreEmpleado,labelDatosUsuario,labelFechaInicio,
	labelArchivo;
	Textbox textboxDescripcion,textboxComentario;
	Datebox dateboxFechaFin;
	Combobox comboNivelTarea;
	Center centro= null;
	East este=null;
	DBTareas dbtareas = new DBTareas();
	int id_ultima_tarea = dbtareas.ultima_tarea();
	
	usuarios usuario=new usuarios();
	personas persona=new personas();
	Departamento departamento=new Departamento();
	personas empleado = new personas();
	tareas tarea_seleccionada = new tareas();
	DBPersonas dbpersonas = new DBPersonas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	String opcion;
	String fecha_creacion;
	int id_nivel_tarea = 0;
	String nombre_archivo = "";
	String dir_raiz = System.getProperty("user.home").replace("\\", "/")+"/ArchivosTareas/";
	org.zkoss.util.media.Media media = null;
	permisos permiso_tareas = null;
	DBPermisos dbpermisos = new DBPermisos();
	ListModelList<NivelTareas> niveltareas;
	DBnivelTarea dbniveltarea = new DBnivelTarea();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		cargarComboNivelTareas();
		centro = (Center)WinAsignarTarea.getAttribute("centro"); 
		este = (East)WinAsignarTarea.getAttribute("este");
		Session session = Sessions.getCurrent();
		opcion = (String)session.getAttribute("OpcionTareasJefe");
		usuario = (usuarios) session.getAttribute("usuario");
		permiso_tareas = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "tareasjefe");
		persona = dbpersonas.mostrarpersona(usuario.getId_persona());
		departamento = dbdepartamento.mostrardepartamento(persona.getId_departamento());
		labelDatosUsuario.setValue(persona.getNombres()+" "+persona.getApellidos()+" "+
		departamento.getDescripcion());
		if(opcion.equals("Guardar")){
			buttonOpcion.setLabel("Guardar");
		}else{
			if(opcion.equals("Buscar")){
				
				buttonOpcion.setVisible(false);
				buttonArchivoAdjunto.setDisabled(true);
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				textboxDescripcion.setText(tarea_seleccionada.getDescripcion());
				textboxComentario.setText(tarea_seleccionada.getComentario());
				labelFechaInicio.setValue(tarea_seleccionada.getFecha_inicio());
				dateboxFechaFin.setText(tarea_seleccionada.getFecha_fin());
				labelArchivo.setValue("tarea_asignada"+tarea_seleccionada.getId_tarea()+".docx");
				media = null;
				empleado =  dbpersonas.mostrarpersona(tarea_seleccionada.getId_persotarea());
				id_nivel_tarea = tarea_seleccionada.getId_tipotarea();
				labelNombreEmpleado.setValue(empleado.getNombres()+" "+empleado.getApellidos());
				buttonArchivoAdjunto.setVisible(false);
				buttonVer.setVisible(false);
				Iterator<NivelTareas> it = niveltareas.iterator();
				while(it.hasNext()) {
					 
					NivelTareas niveltarea = it.next();
					if (niveltarea.getId_tipotarea()==id_nivel_tarea) {
					 comboNivelTarea.setText(niveltarea.getDescripcion());
					}
					}
				
			}else{
				buttonOpcion.setLabel("Actualizar");
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				if(permiso_tareas.getEliminar()==1){
					buttonEliminar.setVisible(true);
				}
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				textboxDescripcion.setText(tarea_seleccionada.getDescripcion());
				textboxComentario.setText(tarea_seleccionada.getComentario());
				labelFechaInicio.setValue(tarea_seleccionada.getFecha_inicio());
				dateboxFechaFin.setText(tarea_seleccionada.getFecha_fin());
				labelArchivo.setValue("tarea_asignada"+tarea_seleccionada.getId_tarea()+".docx");
				media = null;
				empleado =  dbpersonas.mostrarpersona(tarea_seleccionada.getId_persotarea());
				id_nivel_tarea = tarea_seleccionada.getId_tipotarea();
				labelNombreEmpleado.setValue(empleado.getNombres()+" "+empleado.getApellidos());
				Iterator<NivelTareas> it = niveltareas.iterator();
				while(it.hasNext()) {
					 
					NivelTareas niveltarea = it.next();
					if (niveltarea.getId_tipotarea()==id_nivel_tarea) {
					 comboNivelTarea.setText(niveltarea.getDescripcion());
					}
					}
			}
		}
		Calendar c1 = new GregorianCalendar();
		fecha_creacion = ""+c1.get(Calendar.YEAR)+"-"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DATE);
		labelFechaInicio.setValue(fecha_creacion);
		
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
		   	   	//WinAsignarTarea.detach();
	}
	
	public void onClick$buttonVerHistorial(){
		if(este.getFirstChild()!=null){
		  	 este.removeChild(este.getFirstChild());
		  	 }
		   	   	Window win=(Window) Executions.createComponents("TareasJefeFormulario/ListaTareasEmpleado.zul", este, null );
	}
	
	public void onUpload$buttonArchivoAdjunto(org.zkoss.zk.ui.event.UploadEvent event){
		if(opcion.equals("Guardar")){
			int id_aux = id_ultima_tarea +1;
			media = event.getMedia();
			nombre_archivo = "tarea_asignada_"+id_aux+"."+media.getFormat();
			labelArchivo.setValue(nombre_archivo);
		}else{
			media = event.getMedia();
			nombre_archivo = "tarea_asignada_"+tarea_seleccionada.getId_tarea()+"."+media.getFormat();
			labelArchivo.setValue(nombre_archivo);
		}
	}
	
	public boolean validar_campos(){
		boolean respuesta = false;
		Session session = Sessions.getCurrent();
		empleado = (personas)session.getAttribute("empleado_seleccionado");
		if(empleado != null && labelArchivo.getValue().equals("")==false && 
				textboxDescripcion.getText().equals("")==false &&
				textboxComentario.getText().equals("")==false && id_nivel_tarea != 0){
			respuesta = true;
		}
		return respuesta;
	}
	
	public void onClick$buttonOpcion(){
		Util util = new Util();
		boolean respuesta = false;
		if(validar_campos()){
			if(opcion.equals("Guardar")){
				id_ultima_tarea = id_ultima_tarea +1 ;
				String ruta_tarea = dir_raiz+departamento.getDescripcion()+"/";
				File directorio=new File(ruta_tarea); 
				directorio.mkdir(); 
				respuesta = util.uploadFile(media,ruta_tarea,nombre_archivo);
				if(respuesta == true){
					alert("Tarea Guardada!!");
					Session session = Sessions.getCurrent();
					empleado = (personas)session.getAttribute("empleado_seleccionado");
					tareas tarea = new tareas();
					tarea.setId_tarea(0);
					tarea.setId_tipotarea((int)comboNivelTarea.getSelectedItem().getValue());
					tarea.setId_persona(usuario.getId_persona());
					tarea.setId_persotarea(empleado.getId_persona());
					tarea.setDescripcion(textboxDescripcion.getText());
					tarea.setArchivo(ruta_tarea+""+nombre_archivo);
					tarea.setComentario(textboxComentario.getText());
					tarea.setFecha_inicio(labelFechaInicio.getValue());
					tarea.setFecha_fin(dateboxFechaFin.getText());
					tarea.setEstado("P");
					tarea.setEstado_tarea(0);
					try {
						dbtareas.guardartareas(tarea);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					empleado = null;
					labelArchivo.setValue("");
					textboxComentario.setText("");
					textboxDescripcion.setText("");
					media = null;
					dateboxFechaFin.setText("");
					comboNivelTarea.setSelectedItem(null);
				}else{
					alert("error");
				}
			}else{
				if(opcion.equals("Buscar")){
					buttonOpcion.setVisible(false);
				}else{
					buttonOpcion.setLabel("Actualizar");
					String ruta_tarea = dir_raiz+departamento.getDescripcion()+"/";
					File directorio=new File(ruta_tarea); 
					directorio.mkdir();
					if(media!=null){
						respuesta = util.uploadFile(media,ruta_tarea,nombre_archivo);
					}
					if(respuesta == true || media == null){
						alert("archivo subido correctamente");
						Session session = Sessions.getCurrent();
						empleado = (personas)session.getAttribute("empleado_seleccionado");
						tareas tarea = new tareas();
						tarea.setId_tarea(tarea_seleccionada.getId_tarea());
						tarea.setId_tipotarea(id_nivel_tarea);
						tarea.setId_persona(usuario.getId_persona());
						tarea.setId_persotarea(empleado.getId_persona());
						tarea.setDescripcion(textboxDescripcion.getText());
						if(media!=null){
							tarea.setArchivo(tarea_seleccionada.getArchivo());
						}else{
							tarea.setArchivo(ruta_tarea+""+nombre_archivo);
						}
						tarea.setComentario(textboxComentario.getText());
						tarea.setFecha_inicio(labelFechaInicio.getValue());
						tarea.setFecha_fin(dateboxFechaFin.getText());
						tarea.setEstado("P");
						tarea.setEstado_tarea(0);
						try {
							dbtareas.actualizartareas(tarea);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						empleado = null;
						labelArchivo.setValue("");
						textboxComentario.setText("");
						textboxDescripcion.setText("");
						media = null;
						dateboxFechaFin.setText("");
						comboNivelTarea.setSelectedItem(null);
					}else{
						alert("error");
					}
				}
			}
		}else{
			alert("No se han llenado todos los campos");
		}
	}
	
	public void onClick$buttonEliminar(){
		try {
			dbtareas.eliminartareas(tarea_seleccionada.getId_tarea());
			alert("Tarea Eliminada");
			empleado = null;
			labelArchivo.setValue("");
			textboxComentario.setText("");
			textboxDescripcion.setText("");
			media = null;
			dateboxFechaFin.setText("");
			comboNivelTarea.setSelectedItem(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert("datos incorrectos");
		}
	}
	
	public void onSelect$comboNivelTarea(){
		id_nivel_tarea = (Integer)comboNivelTarea.getSelectedItem().getValue();
	}
	
	public void onClick$buttonCancelar(){
		//File file_aux = new File(dir_raiz+departamento.getDescripcion()+"/"+media.getName()+"."+media.getContentType());
		//alert(file_aux.getAbsolutePath());
		//file_aux.delete();
		WinAsignarTarea.detach();
	}
	
	public void onClick$labelArchivo(){
		if(opcion.equals("Guardar")==false){
			File file = new File(tarea_seleccionada.getArchivo());
			if(file != null)
				try {
					Filedownload.save(file, null);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	//alert(Executions.getCurrent().getContextPath());
			/*
			File file = new File("C:/Ejercicios/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/"
					+ "wtpwebapps/GrupoAplicacionWeb/ArchivosAplicacionWeb/Eduardo Ignacio Tigrero"
					+ " Departamento de Sistemas/pruebas_tareas.docx");
			if(file != null)
				try {
					Filedownload.save(file, null);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	
}
