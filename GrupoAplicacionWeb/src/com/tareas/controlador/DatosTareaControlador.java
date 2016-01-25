package com.tareas.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
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
import org.zkoss.zul.East;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.permisos;
import com.controlador.entidades.personas;
import com.controlador.entidades.tarearealizada;
import com.controlador.entidades.tareas;
import com.controlador.entidades.usuarios;
import com.tareas.archivos.Util;
import com.tareas.modelos.DBPermisos;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBTarearealizada;
import com.tareas.modelos.DBTareas;
import com.tareas.modelos.DBdepartamento;

public class DatosTareaControlador extends GenericForwardComposer<Component>{

	@Wire
	Window WinDatosTareas;
	Center centro= null;
	East este = null;
	Button buttonTareas,buttonDescargarTarea, buttonSubirTarea,
	buttonOpcion, buttonEliminar, buttonCancelar;
	Label labelFechaInicio, labelFechaFin, labelArchivo,labelFechaSubida;
	Textbox textboxDescripcion,textboxComentario;
	
	DBTarearealizada dbtarearealizada = new DBTarearealizada();
	int id_ultima_tarea_realizada = dbtarearealizada.ultima_tarea_realizada();
	usuarios usuario=new usuarios();
	personas empleado = new personas();
	tareas tarea_seleccionada = new tareas();
	tarearealizada tarear_seleccionada = null;
	Departamento departamento=new Departamento();
	DBPersonas dbpersonas = new DBPersonas();
	DBTareas dbtareas = new DBTareas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	String opcion;
	String fecha_creacion;
	org.zkoss.util.media.Media media = null;
	permisos permiso_tareasr = null;
	DBPermisos dbpermisos = new DBPermisos();
	String nombre_archivo = "";
	String dir_raiz = System.getProperty("user.home").replace("\\", "/")+"/ArchivosTareas/";
	
	public DatosTareaControlador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)WinDatosTareas.getAttribute("centro"); 
		este = (East)WinDatosTareas.getAttribute("este");
		Session session = Sessions.getCurrent();
		opcion = (String)session.getAttribute("OpcionTareasEmpleado");
		usuario = (usuarios)session.getAttribute("usuario");
		permiso_tareasr = dbpermisos.mostrarpermisos(usuario.getId_tipousuario(), "empleadostareas");
		empleado = dbpersonas.mostrarpersona(usuario.getId_persona());
		departamento = dbdepartamento.mostrardepartamento(empleado.getId_departamento());
		if(opcion.equals("Guardar")){
			buttonOpcion.setLabel("Guardar");
			buttonEliminar.setVisible(false);
		}else{
			if(opcion.equals("Buscar")){
				buttonOpcion.setVisible(false);
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				labelFechaInicio.setValue(tarea_seleccionada.getFecha_inicio());
				labelFechaFin.setValue(tarea_seleccionada.getFecha_fin());
				textboxDescripcion.setText(tarea_seleccionada.getDescripcion());
				buttonTareas.setVisible(false);
				buttonOpcion.setVisible(false);
				buttonEliminar.setVisible(false);
				tarear_seleccionada = dbtarearealizada.tareasxid_tarea(tarea_seleccionada.getId_tarea());
				buttonSubirTarea.setVisible(false);
				labelArchivo.setValue("tarea_realizada_"+tarear_seleccionada.getId_Tarea_realizada());
				textboxComentario.setText(tarear_seleccionada.getDescipcion());
			}else{
				if(permiso_tareasr.getEditar()==1){
					buttonOpcion.setLabel("Actualizar");
					buttonOpcion.setVisible(true);
				}else{
					buttonOpcion.setVisible(false);
				}
				if(permiso_tareasr.getEliminar()==1){
					buttonEliminar.setVisible(true);
				}else{
					buttonEliminar.setVisible(false);
				}
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				labelFechaInicio.setValue(tarea_seleccionada.getFecha_inicio());
				labelFechaFin.setValue(tarea_seleccionada.getFecha_fin());
				textboxDescripcion.setText(tarea_seleccionada.getDescripcion());
				buttonTareas.setVisible(false);
				tarear_seleccionada = dbtarearealizada.tareasxid_tarea(tarea_seleccionada.getId_tarea());
				labelArchivo.setValue("tarea_realizada_"+tarear_seleccionada.getId_Tarea_realizada());
				textboxComentario.setText(tarear_seleccionada.getDescipcion());
			}
		}
		Calendar c1 = new GregorianCalendar();
		if((c1.get(Calendar.MONTH)+1)<10){
			fecha_creacion = ""+c1.get(Calendar.YEAR)+"-0"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DATE);
		}else{
			fecha_creacion = ""+c1.get(Calendar.YEAR)+"-"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DATE);
		}
		labelFechaSubida.setValue(fecha_creacion);
		
		File directorio=new File(dir_raiz); 
		directorio.mkdir();
	}

	public void onClick$buttonTareas(){
		if(este.getFirstChild()!=null){
		  	 este.removeChild(este.getFirstChild());
		  	 }
		   	   	Window win=(Window) Executions.createComponents("TareasEmpleadoFormulario/ListaTareas.zul", este, null );
		   	   	win.setAttribute("labelFechaInicio", labelFechaInicio);
		   	 	win.setAttribute("labelFechaFin", labelFechaFin);
		   	 	win.setAttribute("textboxDescripcion", textboxDescripcion);		   	
	}
	
	public void onClick$buttonDescargarTarea(){
		Session session = Sessions.getCurrent();
		tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
		if(tarea_seleccionada !=null){
			File file = new File(tarea_seleccionada.getArchivo());
			if(file != null)
				try {
					Filedownload.save(file, null);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{
			alert("Seleccione una tarea antes");
		}
	}
	
	public void onUpload$buttonSubirTarea(org.zkoss.zk.ui.event.UploadEvent event){
		if(opcion.equals("Guardar")){
			int id_aux = id_ultima_tarea_realizada +1;
			media = event.getMedia();
			nombre_archivo = "tarea_realizada_"+id_aux+"."+media.getFormat();
			labelArchivo.setValue(nombre_archivo);
		}else{
			media = event.getMedia();
			nombre_archivo = "tarea_realizada_"+tarear_seleccionada.getId_Tarea_realizada()+"."+media.getFormat();
			labelArchivo.setValue(nombre_archivo);
		}
	}
	
	public void onClick$buttonCancelar(){
		WinDatosTareas.detach();
	}
	
	public boolean validar_campos(){
		boolean respuesta = false;
		if(tarea_seleccionada != null && textboxComentario.getText().equals("") == false
				&& labelArchivo.getValue().equals("") == false){
			respuesta = true;
		}
		return respuesta;
	}
	
	public void onClick$buttonOpcion(){
		Util util = new Util();
		boolean respuesta = false;
		if(validar_campos()){
			if(opcion.equals("Guardar")){
				Session session = Sessions.getCurrent();
				tarea_seleccionada = (tareas)session.getAttribute("tarea_seleccionada");
				id_ultima_tarea_realizada = id_ultima_tarea_realizada +1 ;
				String ruta_tarea = dir_raiz+departamento.getDescripcion()+"/";
				File directorio=new File(ruta_tarea); 
				directorio.mkdir(); 
				respuesta = util.uploadFile(media,ruta_tarea,nombre_archivo);
				if(respuesta == true){
					alert("archivo subido correctamente");
					empleado = (personas)session.getAttribute("empleado_seleccionado");
					tarearealizada tarear = new tarearealizada();
					tarear.setId_Tarea_realizada(0);
					tarear.setId_tarea(tarea_seleccionada.getId_tarea());
					tarear.setDescipcion(textboxComentario.getText());
					tarear.setFecha_fin(labelFechaSubida.getValue());
					tarear.setArchivo_env(ruta_tarea+""+nombre_archivo);
					int diferencia_dias = 0;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date_creacion = formatter.parse(labelFechaSubida.getValue());
						Date date_tarea = formatter.parse(tarea_seleccionada.getFecha_fin());
						diferencia_dias = diferenciaEnDias(date_tarea,date_creacion);
						System.out.println(""+diferencia_dias);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(diferencia_dias>=0){
						try {
							dbtareas.actualizarestadotareas(tarea_seleccionada, "R");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	else{
						try {
							dbtareas.actualizarestadotareas(tarea_seleccionada, "T");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					tarear.setEstado("A");
					
					try {
						alert(tarea_seleccionada.getFecha_fin().substring(0, 10));
						dbtarearealizada.guardartarearealizada(tarear);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					empleado = null;
					tarea_seleccionada=null;
					labelArchivo.setValue("");
					textboxComentario.setText("");
					textboxDescripcion.setText("");
					media = null;
					labelFechaInicio.setValue("");
					labelFechaFin.setValue("");
				}else{
					alert("error");
				}
			}else{
				if(opcion.equals("Buscar")){
					buttonOpcion.setVisible(false);
				}else{
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
						tarearealizada tarear = new tarearealizada();
						tarear.setId_Tarea_realizada(tarear_seleccionada.getId_Tarea_realizada());
						tarear.setId_tarea(tarea_seleccionada.getId_tarea());
						tarear.setDescipcion(textboxComentario.getText());
						tarear.setFecha_fin(labelFechaSubida.getValue());
						int diferencia_dias = 0;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date date_creacion = formatter.parse(labelFechaSubida.getValue());
							Date date_tarea = formatter.parse(tarea_seleccionada.getFecha_fin());
							diferencia_dias = diferenciaEnDias(date_tarea,date_creacion);
							System.out.println(""+diferencia_dias);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(diferencia_dias>=0){
							try {
								dbtareas.actualizarestadotareas(tarea_seleccionada, "R");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	else{
							try {
								dbtareas.actualizarestadotareas(tarea_seleccionada, "T");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						tarear.setEstado("A");
						if(media!=null){
							tarear.setArchivo_env(ruta_tarea+""+nombre_archivo);
						}else{
							tarear.setArchivo_env(tarear_seleccionada.getArchivo_env());
						}
						try {
							dbtarearealizada.actualizartarearealizada(tarear);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						empleado = null;
						tarea_seleccionada=null;
						labelArchivo.setValue("");
						textboxComentario.setText("");
						textboxDescripcion.setText("");
						media = null;
						labelFechaInicio.setValue("");
						labelFechaFin.setValue("");
						tarear_seleccionada = null;
					}else{
						alert("error");
					}
				}
			}
		}else{
			alert("No se han llenado todos los campos");
		}
	}
	
	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = (diferenciaEn_ms / (1000 * 60 * 60 * 24))+1;
		return (int) dias;
		}
	
	
	public void onClick$buttonEliminar(){
		try {
			dbtarearealizada.eliminartarearealizada(tarear_seleccionada);
			dbtareas.actualizarestadotareas(tarea_seleccionada, "P");
			alert("Archivo eliminado");
			empleado = null;
			tarea_seleccionada=null;
			labelArchivo.setValue("");
			textboxComentario.setText("");
			textboxDescripcion.setText("");
			media = null;
			labelFechaInicio.setValue("");
			labelFechaFin.setValue("");
			tarear_seleccionada = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert("datos incorrectos");
		}
	}
	
}
