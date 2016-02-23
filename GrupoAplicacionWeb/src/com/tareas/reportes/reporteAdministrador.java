package com.tareas.reportes;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.controlador.entidades.Departamento;
import com.controlador.entidades.NivelTareas;
import com.controlador.entidades.personas;
import com.controlador.entidades.tareasReporte;
import com.controlador.entidades.usuarios;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tareas.modelos.DBPersonas;
import com.tareas.modelos.DBReportes;
import com.tareas.modelos.DBdepartamento;
import com.tareas.modelos.DBnivelTarea;

public class reporteAdministrador extends GenericForwardComposer<Component>{
	@Wire
	Label labelA, labelR, labelP, labelT;
	Groupbox gpb_nivel_estados,gpb_mes,gpb_fechas,gpb_lista,gpb_anio;
	Window win_reporteadministrador;
	Button buttonAceptaru,buttonAceptarAnio,buttonAceptarMes,buttonAceptarFechas,buttonDeshacer,
	button_porcentajes,buttonImprimir;
	Combobox cmb_nivel,cmb_tiempo,cmb_mes,cmb_anio,cmb_aniomes,cmb_estados,cmb_departamento;
	Datebox txtFechaLlegada,txtFechaSalida;
	Listbox listatareas;
	int id_departamento = 0;
	usuarios usuario;
	personas persona;
	Departamento departamento;
	DBPersonas dbpersonas = new DBPersonas();
	DBdepartamento dbdepartamento = new DBdepartamento();
	
	ListModelList<Departamento> departamentos;
	DBReportes dbr=new DBReportes();
	ArrayList<tareasReporte> lista;
	
	public void obtener_porcentajes(){
		float contg = 0, contA = 0, contP = 0, contT = 0, contR = 0;
		float porcA = 0, porcP = 0, porcT = 0, porcR = 0;
		ListModelList<tareasReporte> modeloDeDatos= new ListModelList<tareasReporte>(lista);
		Iterator<tareasReporte> it = modeloDeDatos.iterator();
		while(it.hasNext()){
			tareasReporte tr = (tareasReporte)it.next();
			if(tr.getEstado().equals("A")){
				contA = contA + 1;
			}
			if(tr.getEstado().equals("P")){
				contP = contP + 1;			
			}
			if(tr.getEstado().equals("R")){
				contR = contR + 1;
			}
			if(tr.getEstado().equals("T")){
				contT = contT + 1;
			}
			contg = contg + 1;
		}
		porcA = (contA/contg)*100;
		porcP = (contP/contg)*100;
		porcT = (contT/contg)*100;
		porcR = (contR/contg)*100;
		labelA.setValue(""+porcA+" %");
		labelR.setValue(""+porcR+" %"); 
		labelP.setValue(""+porcP+" %");
		labelT.setValue(""+porcT+" %");
	}
	
	public void onClick$button_porcentajes(){
		obtener_porcentajes();
	}
	
	@Override	
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		cargarComboDepartamentos();
		Session session = Sessions.getCurrent();
		usuario = (usuarios) session.getAttribute("usuario");
		persona = dbpersonas.mostrarpersona(usuario.getId_persona());
	}
	
	public void cargarComboDepartamentos(){
		departamentos = dbdepartamento.cargar_departamentos();
		if(departamentos.isEmpty()==false){
			cmb_departamento.setModel(departamentos);
		}else{
			alert("no existen temas");
		}
		cmb_departamento.setText("Todos");
	}
	
	public void onClick$buttonImprimir(){
		String nombrepdf="";  
		obtener_porcentajes();
		String parrafo = "Atrasada:"+labelT.getValue()+"  Registrada:"+labelR.getValue()+
      		  "  Pendiente:"+labelP.getValue()+"  Activa:"+labelA.getValue();
		try {
			  Calendar cal = Calendar.getInstance();
			  Document document=new Document();
			  String nombre=""+(cal.getTime().getYear()+1900)+"_"+(cal.getTime().getMonth()+1)+"_"+
			  cal.getTime().getDate()+"_"+cal.getTime().getHours()+"_"+cal.getTime().getMinutes()+"_"+
					  cal.getTime().getSeconds();
			  Font miFuente = new Font();
	          miFuente.setStyle(Font.BOLD);
	          miFuente.setColor(Color.BLUE);
	          Font miFuente2 = new Font();
	          miFuente2.setSize(8);
	          miFuente2.setStyle(Font.BOLD);
	          miFuente2.setColor(Color.RED);
	          Font miFuente3 = new Font();
	          miFuente3.setSize(7);
	          Font miFuente4 = new Font();
	          miFuente4.setSize(5);
	          PdfPTable tabla;
	          PdfPCell celda;
	          PdfPCell c1;
	          tareasReporte rowlista;
	    			  tabla = new PdfPTable(8);
	    			  celda=new PdfPCell(new Phrase("Fecha/Año",miFuente2));
    		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
    		          tabla.addCell(celda);
    		          celda=new PdfPCell(new Phrase("Cantidad de tareas",miFuente2));
    		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
    		          tabla.addCell(celda);
    		          celda=new PdfPCell(new Phrase("Nombres",miFuente2));
    		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
    	      	      tabla.addCell(celda);
    	      	      celda=new PdfPCell(new Phrase("Apellidos",miFuente2));
  		              celda.setHorizontalAlignment(Element.ALIGN_CENTER);
  		              celda.setVerticalAlignment(Element.ALIGN_CENTER);
  		              tabla.addCell(celda);
    		          celda=new PdfPCell(new Phrase("Cédula",miFuente2));
    		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
    		          tabla.addCell(celda);
    		          celda=new PdfPCell(new Phrase("Descripcion jefe",miFuente2));
    		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
    	      	      tabla.addCell(celda);
	    	      	  celda=new PdfPCell(new Phrase("Descripcion empleado",miFuente2));
	  		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	  	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
	  	      	      tabla.addCell(celda);
		  	      	  celda=new PdfPCell(new Phrase("terminada",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
		      	      tabla.addCell(celda);
    		          for (int i = 0; i < lista.size(); i++)
    		          {
    		        	  rowlista=lista.get(i);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getFecha_inicio(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getCantidad(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getNombre(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getApellidos(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getCedula(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getDescripcion_jefe(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getDescripcion_empleado(),miFuente3));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
    		        	    c1= new PdfPCell(new Phrase(""+rowlista.getFecha_terminada(),miFuente4));
    		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
    		        	    tabla.addCell(c1);
			  }
			  nombrepdf=nombre+".pdf";
	          PdfWriter.getInstance(document,new FileOutputStream(nombrepdf));
	          document.open();
	          Paragraph Titulo=new Paragraph("Reporte de Administrador");
			  Titulo.setAlignment(Element.ALIGN_CENTER);
	          document.add(Titulo);
	          document.add(new Paragraph("Tipo de Reporte: "+cmb_tiempo.getText()));
	          if(cmb_tiempo.getText().equals("Por Fecha")){
	        	  document.add(new Paragraph("Desde: " +txtFechaLlegada.getText()+" Hasta: "+txtFechaSalida.getText()));
	          }
	          if(cmb_tiempo.getText().equals("Por Mes")){
	        	  document.add(new Paragraph("Mes: "+ cmb_mes.getText()));
	          }
	          if(cmb_tiempo.getText().equals("Por Año")){
	        	  document.add(new Paragraph("Año: "+ cmb_anio.getText()));
	          }
	          document.add(new Paragraph("Departamento:" + cmb_departamento.getText()));
	          document.add(new Paragraph("Nivel: " +cmb_nivel.getText()));
	          document.add(new Paragraph("Estado:" + cmb_estados.getText()));
	          document.add(new Paragraph("Usuario: "+persona.getNombres()+" "+persona.getApellidos()));
	          document.add(new Paragraph(parrafo));
	          document.add(new Paragraph(" "));
	          document.add(tabla);
	          document.close(); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  try {
				if ((new File(nombrepdf)).exists()) {
					Process p = Runtime
					   .getRuntime()
					   .exec("rundll32 url.dll,FileProtocolHandler "+nombrepdf);
					p.waitFor();
					buttonImprimir.setVisible(false);
				} else {
					alert("Error!");
				}
		  	  } catch (Exception ex) {
				ex.printStackTrace();
			  }
	}
	
	public void onClick$buttonDeshacer(){
		buttonImprimir.setVisible(false);
		txtFechaLlegada.setDisabled(false);
		txtFechaSalida.setDisabled(false);
		buttonDeshacer.setVisible(false);
		buttonAceptaru.setVisible(true);
		buttonAceptarAnio.setVisible(true);
		buttonAceptarMes.setVisible(true);
		buttonAceptarFechas.setVisible(true);
		buttonDeshacer.setDisabled(false);
		buttonAceptaru.setDisabled(false);
		buttonAceptarAnio.setDisabled(false);
		buttonAceptarMes.setDisabled(false);
		buttonAceptarFechas.setDisabled(false);
		cmb_tiempo.setDisabled(false);
		cmb_mes.setDisabled(false);
		cmb_anio.setDisabled(false);
		cmb_aniomes.setDisabled(false);
		cmb_nivel.setDisabled(false);
		gpb_nivel_estados.setVisible(true);
		gpb_mes.setVisible(false);gpb_fechas.setVisible(false);gpb_lista.setVisible(false);gpb_anio.setVisible(false);
		labelA.setValue("0%");
		labelR.setValue("0%"); 
		labelP.setValue("0%");
		labelT.setValue("0%");
		cmb_departamento.setText("Todos");
	}

	public void onCreate$win_reporteadministrador(){
		buttonImprimir.setVisible(false);
		Calendar c1 = Calendar.getInstance();
		gpb_mes.setVisible(false);
		gpb_nivel_estados.setVisible(true);
		gpb_fechas.setVisible(false);
		gpb_anio.setVisible(false);
		gpb_lista.setVisible(false);
		cmb_tiempo.setText("Por Año");
		cmb_nivel.setText("Alto");
		buttonDeshacer.setVisible(false);
		for (int i=(c1.get(Calendar.YEAR));i>=((c1.get(Calendar.YEAR))-10);i--){
			cmb_anio.appendItem(""+i);
			cmb_aniomes.appendItem(""+i);
		}
		cmb_anio.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio.setReadonly(true);
		cmb_aniomes.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_aniomes.setReadonly(true);
		cmb_tiempo.setReadonly(true);
		cmb_nivel.setText("Todos");
		cmb_nivel.setReadonly(true);
		cmb_mes.setText("Enero");
		cmb_mes.setReadonly(true);
		cmb_estados.setText("Todos");
		cmb_estados.setReadonly(true);
		
	}
	
	public void onClick$buttonAceptaru(){
		buttonImprimir.setVisible(false);
		buttonDeshacer.setVisible(true);
		if(cmb_tiempo.getText().equals("Por Año")){
			gpb_mes.setVisible(false);
			gpb_nivel_estados.setVisible(true);
			gpb_fechas.setVisible(false);
			buttonAceptaru.setVisible(false);
			cmb_tiempo.setDisabled(true);
			cmb_nivel.setDisabled(true);
			gpb_lista.setVisible(false);
			gpb_anio.setVisible(true);
			
		}else{
			if(cmb_tiempo.getText().equals("Por Mes")){
				gpb_mes.setVisible(true);
				gpb_nivel_estados.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				cmb_nivel.setDisabled(true);
				gpb_fechas.setVisible(false);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
				
			}else{
				gpb_mes.setVisible(false);
				gpb_nivel_estados.setVisible(true);
				gpb_fechas.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				cmb_nivel.setDisabled(true);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
				
			}
		}
	}
	
	public void onClick$buttonAceptarAnio(){
		buttonImprimir.setVisible(true);
		
		String nivel = cmb_nivel.getText();
		if(nivel.equals("Todos")){
			nivel = "";
		}
		if(cmb_departamento.getText().equals("Todos")==false){
			if(cmb_estados.getText().equals("Activo")){
				lista = dbr.ReportePorAnioNoRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "A", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Pendiente")){
				lista = dbr.ReportePorAnioNoRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "P", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Realizado")){
				lista = dbr.ReportePorAnioRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "R", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Atrasado")){
				lista = dbr.ReportePorAnioRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "T", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Eliminado")){
				lista = dbr.ReportePorAnioNoRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "E", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Todos")){
				lista = dbr.ReportePorAnioNoRealizadas(Integer.parseInt(cmb_anio.getText()), nivel, "", (int)cmb_departamento.getSelectedItem().getValue());
			}
		}else{
			if(cmb_estados.getText().equals("Activo")){
				lista = dbr.ReportePorAnioNoRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "A");
			}
			if(cmb_estados.getText().equals("Pendiente")){
				lista = dbr.ReportePorAnioNoRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "P");
			}
			if(cmb_estados.getText().equals("Realizado")){
				lista = dbr.ReportePorAnioRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "R");
			}
			if(cmb_estados.getText().equals("Atrasado")){
				lista = dbr.ReportePorAnioRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "T");
			}
			if(cmb_estados.getText().equals("Eliminado")){
				lista = dbr.ReportePorAnioNoRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "E");
			}
			if(cmb_estados.getText().equals("Todos")){
				lista = dbr.ReportePorAnioNoRealizadasAdministrador(Integer.parseInt(cmb_anio.getText()), nivel, "");
			}
		}
		ListModelList<tareasReporte> modeloDeDatos= new ListModelList<tareasReporte>(lista);
		listatareas.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarAnio.setVisible(false);
		cmb_anio.setDisabled(true);
	}
	
	public void onClick$buttonAceptarMes(){
		buttonImprimir.setVisible(true);
		String nivel = cmb_nivel.getText();
		if(nivel.equals("Todos")){
			nivel = "";
		}
		if(cmb_departamento.getText().equals("Todos")==false){
			if(cmb_estados.getText().equals("Activo")){
				lista = dbr.ReportePorMesNoRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "A", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Pendiente")){
				lista = dbr.ReportePorMesNoRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "P", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Realizado")){
				lista = dbr.ReportePorMesRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "R", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Atrasado")){
				lista = dbr.ReportePorMesRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "T", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Eliminado")){
				lista = dbr.ReportePorMesNoRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "E", (int)cmb_departamento.getSelectedItem().getValue());
			}
			if(cmb_estados.getText().equals("Todos")){
				lista = dbr.ReportePorMesNoRealizadas(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "", (int)cmb_departamento.getSelectedItem().getValue());
			}
		}else{
			if(cmb_estados.getText().equals("Activo")){
				lista = dbr.ReportePorMesNoRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "A");
			}
			if(cmb_estados.getText().equals("Pendiente")){
				lista = dbr.ReportePorMesNoRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "P");
			}
			if(cmb_estados.getText().equals("Realizado")){
				lista = dbr.ReportePorMesRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "R");
			}
			if(cmb_estados.getText().equals("Atrasado")){
				lista = dbr.ReportePorMesRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "T");
			}
			if(cmb_estados.getText().equals("Eliminado")){
				lista = dbr.ReportePorMesNoRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "E");
			}
			if(cmb_estados.getText().equals("Todos")){
				lista = dbr.ReportePorMesNoRealizadasAdministrador(Integer.parseInt(cmb_aniomes.getText()),cmb_mes.getSelectedIndex()+1, nivel, "");
			}
		}
		ListModelList<tareasReporte> modeloDeDatos= new ListModelList<tareasReporte>(lista);
		listatareas.setModel(modeloDeDatos);
		listatareas.renderAll();
		gpb_lista.setVisible(true);
		buttonAceptarMes.setVisible(false);
		cmb_mes.setDisabled(true);
		cmb_aniomes.setDisabled(true);
	}
	
	public void onClick$buttonAceptarFechas(){
		buttonImprimir.setVisible(true);
		if(txtFechaLlegada.getText().isEmpty() || txtFechaSalida.getText().isEmpty()){
			alert("Seleccione fechas!!");
		}
		else
		{
			String nivel = cmb_nivel.getText();
			if(nivel.equals("Todos")){
				nivel = "";
			}
			if(cmb_departamento.getText().equals("Todos")==false){
				if(cmb_estados.getText().equals("Activo")){
					lista = dbr.ReportePorFechaNoRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "A", (int)cmb_departamento.getSelectedItem().getValue());
				}
				if(cmb_estados.getText().equals("Pendiente")){
					lista = dbr.ReportePorFechaNoRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "P", (int)cmb_departamento.getSelectedItem().getValue());
				}
				if(cmb_estados.getText().equals("Realizado")){
					lista = dbr.ReportePorFechaRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "R", (int)cmb_departamento.getSelectedItem().getValue());
				}
				if(cmb_estados.getText().equals("Atrasado")){
					lista = dbr.ReportePorFechaRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "T", (int)cmb_departamento.getSelectedItem().getValue());
				}
				if(cmb_estados.getText().equals("Eliminado")){
					lista = dbr.ReportePorFechaNoRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "E", (int)cmb_departamento.getSelectedItem().getValue());
				}
				if(cmb_estados.getText().equals("Todos")){
					lista = dbr.ReportePorFechaNoRealizadas(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "", (int)cmb_departamento.getSelectedItem().getValue());
				}
			}else{
				if(cmb_estados.getText().equals("Activo")){
					lista = dbr.ReportePorFechaNoRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "A");
				}
				if(cmb_estados.getText().equals("Pendiente")){
					lista = dbr.ReportePorFechaNoRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "P");
				}
				if(cmb_estados.getText().equals("Realizado")){
					lista = dbr.ReportePorFechaRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "R");
				}
				if(cmb_estados.getText().equals("Atrasado")){
					lista = dbr.ReportePorFechaRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "T");
				}
				if(cmb_estados.getText().equals("Eliminado")){
					lista = dbr.ReportePorFechaNoRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "E");
				}
				if(cmb_estados.getText().equals("Todos")){
					lista = dbr.ReportePorFechaNoRealizadasAdministrador(txtFechaLlegada.getText(),txtFechaSalida.getText(), nivel, "");
				}
			}
			ListModelList<tareasReporte> modeloDeDatos= new ListModelList<tareasReporte>(lista);
			listatareas.setModel(modeloDeDatos);
			gpb_lista.setVisible(true);
			buttonAceptarFechas.setVisible(false);
			txtFechaLlegada.setDisabled(true);
			txtFechaSalida.setDisabled(true);
		}
	}

	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = (diferenciaEn_ms / (1000 * 60 * 60 * 24))+1;
		return (int) dias;
		}
	
	public void onChange$txtFechaSalida(){
			if (diferenciaEnDias(txtFechaSalida.getValue(),txtFechaLlegada.getValue())<1){
				alert("Fechas incoherentes");
				txtFechaSalida.setText("");
			}
	}
	
	public void onChange$txtFechaLlegada(){
			if (diferenciaEnDias(txtFechaSalida.getValue(),txtFechaLlegada.getValue())<1){
				alert("Fechas incoherentes");
				txtFechaLlegada.setText("");
			}
	}

}
