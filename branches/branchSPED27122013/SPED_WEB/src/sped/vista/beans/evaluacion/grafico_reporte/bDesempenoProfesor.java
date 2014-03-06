package sped.vista.beans.evaluacion.grafico_reporte;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.faces.bi.model.GraphDataModel;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.dss.dataView.ImageView;
import oracle.dss.graph.GraphModel;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.poi.xslf.usermodel.PieChartDemo;

import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFIndicadorRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCurso;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanEvaluacion_DP;
import sped.negocio.entidades.beans.BeanFiltrosGraficos;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;

import sped.negocio.entidades.beans.BeanUsuario;

import sped.negocio.entidades.eval.Evaluacion;

import sped.vista.Utils.Utils;

import utils.system;

public class bDesempenoProfesor {
    
    
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_C_SFGradoRemote ln_C_SFGradoRemote;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_C_SFCriterioRemote ln_C_SFCriterioRemote;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFIndicadorRemote ln_C_SFIndicadorRemote;
 
    private bSessionDesempenoProfesor sessionDesempenoProfesor;   
    FacesContext ctx = FacesContext.getCurrentInstance();
    private List listaBarPrueba;
    private UIGraph pieGraph;
    private UIGraph barGraph;
    private GraphDataModel graphDataPie;
    private GraphDataModel graphDataBar;
    private GraphDataModel graphDataBarHorizontal;
    private GraphDataModel graphDataLine;
    private GraphDataModel graphDataBarNiveles;
    

    
  
    
    private List<BeanFiltrosGraficos> listBean=new ArrayList();    
  
  
    private RichSelectOneChoice choiceSedes;
    private RichSelectOneChoice choiceNiveles;
    private RichSelectOneChoice choiceAreas;
    private RichSelectOneChoice choiceCursos;
    private RichSelectOneChoice choiceGrados;
    private RichSelectOneChoice choiceDocente;
    private RichSelectOneChoice choiceCriterios;
    private RichButton btnAgregarFiltro;
    private RichTable tbFiltrosBusqueda;
    private RichInputDate inputFechaInicio;
    private RichInputDate inputFechaFin;
    private RichTable tbIndicadoresFiltro;
    private RichInputText inputIndicador;
    private UIGraph barAreaGraph;
    private UIGraph barDocIndicadorGraph;
    private UIGraph lineaDesempenoGlobal;
    private UIGraph lineDesempeñoProf;
    private UIGraph barDocenteEvalu;


    public bDesempenoProfesor() {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {           
        if(sessionDesempenoProfesor.getExec()==0){                  
            Utils.sysout("1 POST::>> "+sessionDesempenoProfesor.getExec()); 
            llenarChoices();
            sessionDesempenoProfesor.setExec(1);              
            }
  
    }  
    
    public String llenarChoices(){
        sessionDesempenoProfesor.setListaSedesFiltro(llenarSedesFiltro());
        sessionDesempenoProfesor.setListaAreasFiltro(llenarAreasFiltro(ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN()));
        sessionDesempenoProfesor.setListaNivelesFiltro(llenarNivelesFiltro(ln_C_SFNivelRemote.getNivelLN()));
        sessionDesempenoProfesor.setListaCursosFiltro(llenarCursosFiltro(ln_C_SFCursoRemoto.getlistaCursos()));
        sessionDesempenoProfesor.setListaGradosFiltro(null);
        sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN()));
        sessionDesempenoProfesor.setListaCriteriosFiltro(llenarCriteriosFiltro());
        return null;
    }
    
    public void valoresChoiceGrado(ValueChangeEvent valueChangeEvent) {
        sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(choiceSedes.getValue(), choiceAreas.getValue(), choiceCursos.getValue(),choiceNiveles.getValue(),choiceGrados.getValue())));
        Utils.addTargetMany(choiceDocente);
    }
    
    public void valoresChoiceCursos(ValueChangeEvent valueChangeEvent) {
        sessionDesempenoProfesor.setListaNivelesFiltro(llenarNivelesFiltro(ln_C_SFNivelRemote.getNivelLNPorSede_ByOrden(choiceSedes.getValue(), choiceAreas.getValue(), choiceCursos.getValue())));
        sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(choiceSedes.getValue(), choiceAreas.getValue(), choiceCursos.getValue(),choiceNiveles.getValue(),choiceGrados.getValue())));
        Utils.addTargetMany(choiceNiveles,choiceDocente);
    }
    public void valoresChoiceSede(ValueChangeEvent valueChangeEvent) {  
        sessionDesempenoProfesor.setListaNivelesFiltro(llenarNivelesFiltro(ln_C_SFNivelRemote.getNivelLNPorSede_ByOrden(choiceSedes.getValue().toString(),null,null)));
        sessionDesempenoProfesor.setListaAreasFiltro(llenarAreasFiltro(ln_C_SFAreaAcademicaRemote.getAreaAcademicaLNPorSede_byOrden(choiceSedes.getValue().toString())));
        sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(choiceSedes.getValue().toString(),null,null,null,null)));
        sessionDesempenoProfesor.setListaCursosFiltro(llenarCursosFiltro(ln_C_SFCursoRemoto.findCursosPorAreaAcademica_ByOrden(null,choiceSedes.getValue().toString())));  
       
        Utils.addTargetMany(choiceAreas,choiceNiveles,choiceCursos,choiceDocente);
        
    }
    public void valoresChoiceArea(ValueChangeEvent valueChangeEvent) {
        if(choiceSedes.getValue()==null){      
            sessionDesempenoProfesor.setListaCursosFiltro(llenarCursosFiltro(ln_C_SFCursoRemoto.findCursosPorAreaAcademica_ByOrden(choiceAreas.getValue().toString(),null)));
            sessionDesempenoProfesor.setListaNivelesFiltro(llenarNivelesFiltro(ln_C_SFNivelRemote.getNivelLNPorSede_ByOrden(null, choiceAreas.getValue().toString(),null)));
            sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(null, choiceAreas.getValue().toString(),null,null,null)));
        }else{
            sessionDesempenoProfesor.setListaCursosFiltro(llenarCursosFiltro(ln_C_SFCursoRemoto.findCursosPorAreaAcademica_ByOrden(choiceAreas.getValue().toString(),choiceSedes.getValue().toString())));  
            sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(choiceSedes.getValue().toString(), choiceAreas.getValue().toString(),null,null,null)));
            sessionDesempenoProfesor.setListaNivelesFiltro(llenarNivelesFiltro(ln_C_SFNivelRemote.getNivelLNPorSede_ByOrden(choiceSedes.getValue().toString(), choiceAreas.getValue().toString(),null)));
        }
        Utils.addTargetMany(choiceCursos,choiceNiveles,choiceDocente);
    }
    
    public void valoresChoiceNivel(ValueChangeEvent valueChangeEvent) {        
        sessionDesempenoProfesor.setListaGradosFiltro(llenarGradosFiltro(ln_C_SFGradoRemote.getGradoLN_PorNivelByOrden(choiceNiveles.getValue().toString())));
        sessionDesempenoProfesor.setListaProfesoresFiltro(llenarProfesoresFiltro(ln_C_SFProfesorRemote.getProfesoresLN_PorSede_ByOrden(choiceSedes.getValue(), choiceAreas.getValue(), choiceCursos.getValue(),choiceNiveles.getValue(),choiceGrados.getValue())));
        Utils.addTargetMany(choiceGrados,choiceDocente);
    }
    
    
    public ArrayList llenarCursosFiltro(List<BeanCurso> listaCursos) {
        ArrayList unItems = new ArrayList();       
        List<BeanCurso> roles= listaCursos;;    
        for (BeanCurso r : roles) {          
            unItems.add(new SelectItem(r.getNidCurso().toString(), r.getDescripcionCurso().toString()));
        }
        return unItems;
    }
    
    
    public ArrayList llenarProfesoresFiltro(List<BeanProfesor> listProf) {
        ArrayList unItems = new ArrayList();
        List<BeanProfesor> roles = listProf;            
        for (BeanProfesor r : roles) {                
        String nombreCompleto=r.getApellidos()+" "+r.getNombres();
            unItems.add(new SelectItem(r.getDniProfesor().toString(), nombreCompleto.toString()));
        }
        return unItems;
    }
    public ArrayList llenarCriteriosFiltro() {
        ArrayList unItems = new ArrayList();
        List<BeanCriterio> roles = ln_C_SFCriterioRemote.getCriteriosByAttr_LN(0,null);       
        for (BeanCriterio r : roles) {               
            unItems.add(new SelectItem(r.getNidCriterio().toString(), r.getDescripcionCriterio().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarSedesFiltro() {
        ArrayList unItems = new ArrayList();
        List<BeanSede> roles = ln_C_SFSedeRemote.getSedeLN();        
        for (BeanSede r : roles) {          
            unItems.add(new SelectItem(r.getNidSede().toString(), r.getDescripcionSede().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarGradosFiltro(List<BeanGrado> listaGrados) {
        ArrayList unItems = new ArrayList();
        List<BeanGrado> roles = listaGrados;
        for (BeanGrado r : roles) {          
            unItems.add(new SelectItem(r.getNidGrado().toString(), r.getDescripcionGrado().toString()));
        }
        return unItems;
    }
 
    public ArrayList llenarAreasFiltro(List<BeanAreaAcademica> listaAreas) {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> roles = listaAreas;
                        //ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        for (BeanAreaAcademica r : roles) {          
            unItems.add(new SelectItem(r.getNidAreaAcademica().toString(), r.getDescripcionAreaAcademica().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarNivelesFiltro(List<BeanNivel> listaNiveles) {
        ArrayList unItems = new ArrayList();
        List<BeanNivel> roles = listaNiveles;
        for (BeanNivel r : roles) {          
            unItems.add(new SelectItem(r.getNidNivel().toString(), r.getDescripcionNivel().toString()));
        }
        return unItems;
    }

    public String addFiltros(){    
        BeanFiltrosGraficos bean=new BeanFiltrosGraficos();  
        bean.setFechaFin(sessionDesempenoProfesor.getFechaFin());
        bean.setFechaInicio(sessionDesempenoProfesor.getFechaInicio());
        
        bean.setNidArea(sessionDesempenoProfesor.getNidArea());
        bean.setNidSede(sessionDesempenoProfesor.getNidSede()); 
        bean.setNidGrado(sessionDesempenoProfesor.getNidGrado());
        bean.setNidCriterio(sessionDesempenoProfesor.getNidCriterio());
        bean.setNidCurso(sessionDesempenoProfesor.getNidCurso());        
        bean.setNidNivele(sessionDesempenoProfesor.getNidNivele());
        bean.setDniDocente(sessionDesempenoProfesor.getDniDocente());
        bean.setFechaInicio(sessionDesempenoProfesor.getFechaInicio());
        bean.setFechaFin(sessionDesempenoProfesor.getFechaFin());
        if(sessionDesempenoProfesor.getBeanIndicador()!=null){
            if(sessionDesempenoProfesor.getBeanIndicador().getNidIndicador()!=null){
                if(sessionDesempenoProfesor.getBeanIndicador().getNidIndicador()!=0){
        bean.setNidIndicador(sessionDesempenoProfesor.getBeanIndicador().getNidIndicador().toString());}
            }}
        String mensaje=""; String mensaje2=""; String mensaje3=""; String mensaje4=""; String mensaje5=""; String mensaje6=""; String mensaje7=""; String mensaje8=""; String mensaje9="";
        
        if(bean.getNidSede()!=null){
            bean.setNombreSede(ln_C_SFSedeRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidSede())));
            mensaje=bean.getNombreSede().getDescripcionSede()+", ";
        }
        if(bean.getNidArea()!=null){
            bean.setNombreArea(ln_C_SFAreaAcademicaRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidArea())));
            mensaje2=bean.getNombreArea().getDescripcionAreaAcademica()+", ";
        }
        if(bean.getNidCurso()!=null){
            bean.setNombreCurso(ln_C_SFCursoRemoto.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidCurso())));
            mensaje3=bean.getNombreCurso().getDescripcionCurso()+", ";
        }
        if(bean.getNidNivele()!=null){
            bean.setNombreNivel(ln_C_SFNivelRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidNivele())));
            mensaje4=bean.getNombreNivel().getDescripcionNivel()+", ";
        }
        if(bean.getNidGrado()!=null){
            bean.setNombreGrado(ln_C_SFGradoRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidGrado())));
            mensaje5=bean.getNombreGrado().getDescripcionGrado()+", ";
        }
        if(bean.getDniDocente()!=null){
            bean.setNombreProfesor(ln_C_SFProfesorRemote.findConstrainByDni(sessionDesempenoProfesor.getDniDocente()));
            mensaje6=bean.getNombreProfesor().getApellidos()+" "+bean.getNombreProfesor().getNombres()+", ";
        }
        if(bean.getNidCriterio()!=null){
            bean.setNombreCriterio(ln_C_SFCriterioRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidCriterio())));
            mensaje7=bean.getNombreCriterio().getDescripcionCriterio()+", ";
        }  
        if(bean.getNidIndicador()!=null){
            bean.setNombreIndicador(sessionDesempenoProfesor.getBeanIndicador());
            mensaje8=bean.getNombreIndicador().getDescripcionIndicador()+", ";
        }  
        if(bean.getFechaInicio()!=null && bean.getFechaFin()!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fechaIni = bean.fechaInicio;
            Date fechaFin = bean.fechaFin;
            String fechaConFormato = sdf.format(fechaIni);
            String fechaConFormato2 = sdf.format(fechaFin);
            mensaje9=fechaConFormato+" - "+fechaConFormato2;
        }
        if(bean.getNidSede()==null && bean.getNidArea()==null && bean.getNidCurso()==null &&bean.getNidNivele()==null&& bean.getNidGrado()==null&&bean.getDniDocente()==null&&bean.getNidCriterio()==null&&bean.getNidIndicador()==null){
            Utils.sysout("NO HAY ELEJIDO FILTROS");
        }else{
            bean.setCampoFiltroTrabla(mensaje+mensaje2+mensaje3+mensaje4+mensaje5+mensaje6+mensaje7+mensaje8+mensaje9);                 
            sessionDesempenoProfesor.getListaFiltros().add(bean);   
            if(bean.getNidSede()!=null){//ESTE IF EVITA ACTUALIZAR EL GRAFICO DE SEDES SI NO HAY UN FILTRO DE SEDE ELEGIDO
                llenarDatadeGrafBarrasSedes();      
                }
            if(bean.getNidArea()!=null){//ESTE IF EVITA ACTUALIZAR EL GRAFICO DE Areas SI NO HAY UN FILTRO DE SEDE ELEGIDO
                llenarDatadeGrafBarrasSedesYAreas();      
                }
            if(bean.getNidIndicador()!=null){
                llenarDatadeGrafBarrasDocenteIndicador();
                llenarDatadeLineaUltimoAñoDocenteIndicador();
            }
            if(bean.getDniDocente()!=null && bean.getNidIndicador()==null){
                llenarDatadeLineaUltimoAñoDocenteGlobal();
                llenarDatadeGrafBarrasDocenteEvaluacion();
            }
            
            limpiarComboBoxs(); 
            if (tbFiltrosBusqueda != null) {
                Utils.unselectFilas(tbFiltrosBusqueda);
                tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
                Utils.addTarget(tbFiltrosBusqueda);
            }         
       
       
        }          
        return null;
    }
    
    public String limpiarComboBoxs(){
        sessionDesempenoProfesor.setNidSede(null);
        sessionDesempenoProfesor.setNidArea(null);
        sessionDesempenoProfesor.setNidCriterio(null);
        sessionDesempenoProfesor.setNidCurso(null);
        sessionDesempenoProfesor.setNidGrado(null);
        sessionDesempenoProfesor.setNidNivele(null);
        sessionDesempenoProfesor.setDniDocente(null);
        sessionDesempenoProfesor.setFechaInicio(null);
        sessionDesempenoProfesor.setFechaFin(null);   
        sessionDesempenoProfesor.setNidIndicador(null);
        sessionDesempenoProfesor.setBeanIndicador(null); 
        inputIndicador.resetValue();
        llenarChoices();
        Utils.addTargetMany(choiceAreas,choiceCursos,choiceDocente,choiceGrados,choiceNiveles,choiceSedes,inputFechaInicio,inputFechaFin);
        //,choiceCriterios
        limpiarTablaIndicadores();
        return null;
    }
    public String limpiarTablaIndicadores(){       
        inputIndicador.setValue(null);        
        sessionDesempenoProfesor.getListaIndicadoresFiltro().clear();
        sessionDesempenoProfesor.setEstadoTablaIndicadores(false);
        if (tbIndicadoresFiltro != null) {
            Utils.unselectFilas(tbIndicadoresFiltro);
            tbIndicadoresFiltro.setValue(sessionDesempenoProfesor.getListaIndicadoresFiltro());
            tbIndicadoresFiltro.setVisible(sessionDesempenoProfesor.isEstadoTablaIndicadores());                
            Utils.addTarget(tbIndicadoresFiltro);
        }        
       Utils.addTarget(inputIndicador);
    return null;}
    
    public String btnLimpiarFiltros() {
       limpiarComboBoxs();
       sessionDesempenoProfesor.getListaFiltros().clear();
       if( sessionDesempenoProfesor.getLstEvaBarChart()!=null){
           sessionDesempenoProfesor.getLstEvaBarChart().clear();
       }
        if(  sessionDesempenoProfesor.getLstEvaAreasBarChart()!=null){
            sessionDesempenoProfesor.getLstEvaAreasBarChart().clear();
        }
        if(   sessionDesempenoProfesor.getLstEvaDocenteIndicadorBarChart()!=null){
            sessionDesempenoProfesor.getLstEvaDocenteIndicadorBarChart().clear();
        }   
        if(   sessionDesempenoProfesor.getLstEvaLineGlobalGraph()!=null){
            sessionDesempenoProfesor.getLstEvaLineGlobalGraph().clear();
        }
        if(   sessionDesempenoProfesor.getLstEvaLineGraph()!=null){
            sessionDesempenoProfesor.getLstEvaLineGraph().clear();
        }      
        if(   sessionDesempenoProfesor.getLstEvaDocenteEvaluacionBarChart()!=null){
            sessionDesempenoProfesor.getLstEvaDocenteEvaluacionBarChart().clear();
        } 
       
        if (tbFiltrosBusqueda != null) {
            Utils.unselectFilas(tbFiltrosBusqueda);
            tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
            Utils.addTarget(tbFiltrosBusqueda);
        }
       Utils.addTargetMany(barAreaGraph, barGraph,barDocIndicadorGraph,lineDesempeñoProf,lineaDesempenoGlobal,barDocenteEvalu);
       limpiarTablaIndicadores();
        return null;
    }
    
    public void valoresInputTexIndicador(ValueChangeEvent valueChangeEvent) {
        if(inputIndicador.getValue()!=null){
           sessionDesempenoProfesor.setListaIndicadoresFiltro(ln_C_SFIndicadorRemote.getIndicadoresByDescripcion_LN(inputIndicador.getValue().toString()));
           sessionDesempenoProfesor.setEstadoTablaIndicadores(true);
            if (tbIndicadoresFiltro != null) {
                Utils.unselectFilas(tbIndicadoresFiltro);
                tbIndicadoresFiltro.setValue(sessionDesempenoProfesor.getListaIndicadoresFiltro());
                tbIndicadoresFiltro.setVisible(sessionDesempenoProfesor.isEstadoTablaIndicadores());                
                Utils.addTarget(tbIndicadoresFiltro);
            }
        }
    }
    

    public void seleccionarIndicador(SelectionEvent selectionEvent) {
         
        RichTable t = (RichTable) selectionEvent.getSource(); 
        Object _selectedRowData = t.getSelectedRowData();
        BeanIndicador filtro = (BeanIndicador) _selectedRowData;
        if (filtro != null) {
           sessionDesempenoProfesor.setBeanIndicador(filtro);
           
        }
    }
    
    public String deleteFiltros() {
        if(sessionDesempenoProfesor.getBeanFiltros()!=null){
        sessionDesempenoProfesor.getListaFiltros().remove(sessionDesempenoProfesor.getBeanFiltros());
           if (tbFiltrosBusqueda != null) {
               Utils.unselectFilas(tbFiltrosBusqueda);
               tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
               Utils.addTarget(tbFiltrosBusqueda);
           }
           if(sessionDesempenoProfesor.getBeanFiltros()!=null){
           if(sessionDesempenoProfesor.getBeanFiltros().getNidSede()!=null){
              llenarDatadeGrafBarrasSedes();
              } 
                   }           
           if(sessionDesempenoProfesor.getBeanFiltros()!=null){
           if(sessionDesempenoProfesor.getBeanFiltros().getNidArea()!=null){
              llenarDatadeGrafBarrasSedesYAreas();
              } 
                   }                   
           if(sessionDesempenoProfesor.getBeanFiltros()!=null){
           if(sessionDesempenoProfesor.getBeanFiltros().getDniDocente()!=null){
              llenarDatadeLineaUltimoAñoDocenteGlobal();
              llenarDatadeGrafBarrasDocenteEvaluacion();
                       }
           }
           if(sessionDesempenoProfesor.getBeanFiltros()!=null){
           if(sessionDesempenoProfesor.getBeanFiltros().getNombreIndicador()!=null){
              llenarDatadeLineaUltimoAñoDocenteIndicador();
              llenarDatadeGrafBarrasDocenteIndicador();
                           }    
                   }
       }
        
        return null;
    }
    public void seleccionarFiltro(SelectionEvent selectionEvent) {
         
        RichTable t = (RichTable) selectionEvent.getSource(); 
        Object _selectedRowData = t.getSelectedRowData();
        BeanFiltrosGraficos filtro = (BeanFiltrosGraficos) _selectedRowData;
        if (filtro != null) {
           sessionDesempenoProfesor.setBeanFiltros(filtro);
           
        }
    }    
    
    public String llenarDatadeGrafBarrasDocenteIndicador(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getNombreIndicador()!=null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR INDICADORES  A COMPARAR         
           //UNA LISTA DE EVALUACIONES QUE TIENE
            BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
            if(listaFiltros.get(i).getNombreProfesor()!=null){
            bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
            }
            if(listaFiltros.get(i).getNombreProfesor()==null){
            bean.setProfesor("Global");
            }   
            
           if(listaFiltros.get(i).getFechaInicio()!=null && listaFiltros.get(i).getFechaFin()!=null){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date fechaIni = listaFiltros.get(i).getFechaInicio();
                        Date fechaFin = listaFiltros.get(i).getFechaFin();
                        String fechaConFormato = sdf.format(fechaIni);
                        String fechaConFormato2 = sdf.format(fechaFin);
                        String mensaje=fechaConFormato+" - "+fechaConFormato2;                
                        bean.setIndicador(listaFiltros.get(i).getNombreIndicador().getDescripcionIndicador()+" ("+mensaje+")");
           }else{
                        Date fechaFin=new Date();
                        Date fechaInicio= (Date)fechaFin.clone();
                        fechaInicio.setMonth(fechaFin.getMonth()-12);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");            
                        String fechaConFormato = sdf.format(fechaInicio);
                        String fechaConFormato2 = sdf.format(fechaFin);
                        String mensaje=fechaConFormato+" - "+fechaConFormato2;     
                        bean.setIndicador(listaFiltros.get(i).getNombreIndicador().getDescripcionIndicador()+" ("+mensaje+")");
               }
          
            double valor=ln_C_SFEvaluacionRemote.resultadoPromediodeIndicador(listaFiltros.get(i) ,listaFiltros.get(i).getNombreIndicador().getNidIndicador(),null);  
            bean.setNotaFinal(valor);
                listaParaGrfaicoDeBarrasSedes.add(bean);}
            }
        setListEvabarChartDocenteIndicador(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(barDocIndicadorGraph);
        return null;
    }
    
    public void setListEvabarChartDocenteIndicador(List <BeanEvaluacion_DP> lstEvaluaciones){
      
        List<Object[]> lstEva = new ArrayList();   
        for(int i=0; i<lstEvaluaciones.size(); i++){             
            
            Object[] obj  = { lstEvaluaciones.get(i).getProfesor(),lstEvaluaciones.get(i).getIndicador(),  lstEvaluaciones.get(i).getNotaFinal()}; 
            
            lstEva.add(obj);
        }
        sessionDesempenoProfesor.setLstEvaDocenteIndicadorBarChart(lstEva);        
    }            

    
    public String llenarDatadeGrafBarrasSedesYAreas(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getNombreArea()!=null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR SEDES  A COMPARAR
             List<BeanEvaluacion_DP> lstEvaluaciones=ln_C_SFEvaluacionRemote.desempenoDocentePorEvaluacion(listaFiltros.get(i),null);Utils.sysout("num Evalu "+lstEvaluaciones.size());
           //UNA LISTA DE EVALUACIONES QUE TIENE
            BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
            if(listaFiltros.get(i).getNombreSede()!=null){
            bean.setSede(listaFiltros.get(i).getNombreSede().getDescripcionSede());}
            if(listaFiltros.get(i).getNombreSede()==null){
            bean.setSede("Global");
            }   
            bean.setAreaAcademica(listaFiltros.get(i).getNombreArea().getDescripcionAreaAcademica());
            double valor=ln_C_SFEvaluacionRemote.promedioGeneralPorFiltroDesempeñoDocente(lstEvaluaciones);  
            bean.setNotaFinal(valor);
                listaParaGrfaicoDeBarrasSedes.add(bean);}
            }
        setListEvabarChartSedesYAreas(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(barAreaGraph);
        return null;
    }
    public void setListEvabarChartSedesYAreas(List <BeanEvaluacion_DP> lstEvaluaciones){
      
        List<Object[]> lstEva = new ArrayList();   
        for(int i=0; i<lstEvaluaciones.size(); i++){             
            
            Object[] obj  = { lstEvaluaciones.get(i).getSede(),lstEvaluaciones.get(i).getAreaAcademica(),  lstEvaluaciones.get(i).getNotaFinal()}; 
            
            lstEva.add(obj);
        }
        sessionDesempenoProfesor.setLstEvaAreasBarChart(lstEva);        
    }        
    
    public String llenarDatadeGrafBarrasDocenteEvaluacion(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getDniDocente()!=null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR DNI DOCENTE
            List<BeanEvaluacion_DP> lstEvaluaciones=ln_C_SFEvaluacionRemote.desempenoDocentePorEvaluacion(listaFiltros.get(i),null);Utils.sysout("num Evalu "+lstEvaluaciones.size());
           //UNA LISTA DE EVALUACIONES QUE TIENE
            BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
            if(listaFiltros.get(i).getNombreProfesor()!=null){
            bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
            }
            if(listaFiltros.get(i).getNombreProfesor()==null){
            bean.setProfesor("Global");
            }   
            if(listaFiltros.get(i).getFechaInicio()!=null && listaFiltros.get(i).getFechaFin()!=null){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaIni = listaFiltros.get(i).getFechaInicio();
                Date fechaFin = listaFiltros.get(i).getFechaFin();
                String fechaConFormato = sdf.format(fechaIni);
                String fechaConFormato2 = sdf.format(fechaFin);
                String mensaje=fechaConFormato+" - "+fechaConFormato2;                
                bean.setIndicador("Desempeno ("+mensaje+")");
            }else{
                Date fechaFin=new Date();
                Date fechaInicio= (Date)fechaFin.clone();
                fechaInicio.setMonth(fechaFin.getMonth()-12);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");            
                String fechaConFormato = sdf.format(fechaInicio);
                String fechaConFormato2 = sdf.format(fechaFin);
                String mensaje=fechaConFormato+" - "+fechaConFormato2;                
                bean.setIndicador("Desempeno ("+mensaje+")");
            }
            double valor=ln_C_SFEvaluacionRemote.promedioGeneralPorFiltroDesempeñoDocente(lstEvaluaciones);  
            bean.setNotaFinal(valor);
                listaParaGrfaicoDeBarrasSedes.add(bean);}
            }
        setListEvabarChartDocenteEvaluacion(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(barDocenteEvalu);
        return null;
    }
    
    public void setListEvabarChartDocenteEvaluacion(List <BeanEvaluacion_DP> lstEvaluaciones){
      
        List<Object[]> lstEva = new ArrayList();   
        for(int i=0; i<lstEvaluaciones.size(); i++){             
            
            Object[] obj  = { lstEvaluaciones.get(i).getProfesor(),lstEvaluaciones.get(i).getIndicador(),  lstEvaluaciones.get(i).getNotaFinal()}; 
            
            lstEva.add(obj);
        }
        sessionDesempenoProfesor.setLstEvaDocenteEvaluacionBarChart(lstEva);        
    }     

    
    public String llenarDatadeGrafBarrasSedes(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getNombreSede()!=null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR SEDES  A COMPARAR
             List<BeanEvaluacion_DP> lstEvaluaciones=ln_C_SFEvaluacionRemote.desempenoDocentePorEvaluacion(listaFiltros.get(i),null);Utils.sysout("num Evalu "+lstEvaluaciones.size());
           //UNA LISTA DE EVALUACIONES QUE TIENE
            BeanEvaluacion_DP bean=new BeanEvaluacion_DP();           
            bean.setSede(listaFiltros.get(i).getNombreSede().getDescripcionSede());
            double valor=ln_C_SFEvaluacionRemote.promedioGeneralPorFiltroDesempeñoDocente(lstEvaluaciones);  
            bean.setNotaFinal(valor);
                listaParaGrfaicoDeBarrasSedes.add(bean);}
            }
        setListEvabarChartSedes(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(barGraph);
        return null;
    }
    
              
              
              
    public void setListEvabarChartSedes(List <BeanEvaluacion_DP> lstEvaluaciones){
        List<Object[]> lstEva = new ArrayList();   
        for(int i=0; i<lstEvaluaciones.size(); i++){            
            Object[] obj  = {  lstEvaluaciones.get(i).getSede(), "Desempeño", lstEvaluaciones.get(i).getNotaFinal()};             
            lstEva.add(obj);
        }
        sessionDesempenoProfesor.setLstEvaBarChart(lstEva);        
    }        
    
    
    
    public String llenarDatadeLineaUltimoAñoDocenteIndicador(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getNombreIndicador()!=null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR INDICADORES  A COMPARAR
            if(listaFiltros.get(i).getFechaInicio()!=null && listaFiltros.get(i).getFechaFin()!=null){//SI HAY FILTROS FECHAS
                int num=((listaFiltros.get(i).getFechaFin().getYear()-listaFiltros.get(i).getFechaInicio().getYear())*12)+(listaFiltros.get(i).getFechaFin().getMonth()-listaFiltros.get(i).getFechaInicio().getMonth());
                System.out.println("num ::: "+num);
                for(int j=0; j<num+1; j++){               
                         Date hoy=listaFiltros.get(i).getFechaFin();
                         Date fecha1= (Date)hoy.clone();
                         fecha1.setMonth(hoy.getMonth()-((num)-j));
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                         Date fechaActual =fecha1;
                         String fechaConFormato = sdf.format(fechaActual);                 
                      
                 BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
                 if(listaFiltros.get(i).getNombreProfesor()!=null){
                 bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
                 }
                 if(listaFiltros.get(i).getNombreProfesor()==null){
                 bean.setProfesor("Global");
                 }   
                 bean.setFechaLineGraph(fecha1);     
                   
                 double valor=ln_C_SFEvaluacionRemote.resultadoPromediodeIndicador(listaFiltros.get(i) ,listaFiltros.get(i).getNombreIndicador().getNidIndicador(), fechaConFormato);    
                 bean.setNotaFinal(valor);
                     listaParaGrfaicoDeBarrasSedes.add(bean);}      
                
                
            }else{
           for(int j=0; j<13; j++){
               
                    Date hoy=new Date();
                    Date fecha1= (Date)hoy.clone();
                    fecha1.setMonth(hoy.getMonth()-(12-j));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    Date fechaActual =fecha1;
                    String fechaConFormato = sdf.format(fechaActual);
            
                 
            BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
            if(listaFiltros.get(i).getNombreProfesor()!=null){
            bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
            }
            if(listaFiltros.get(i).getNombreProfesor()==null){
            bean.setProfesor("Global");
            }   
            bean.setFechaLineGraph(fecha1);            
            double valor=ln_C_SFEvaluacionRemote.resultadoPromediodeIndicador(listaFiltros.get(i) ,listaFiltros.get(i).getNombreIndicador().getNidIndicador(), fechaConFormato);  
            bean.setNotaFinal(valor);
                listaParaGrfaicoDeBarrasSedes.add(bean);}
            }
            }
            }
        setListLinegraphDesempeñodocente(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(lineaDesempenoGlobal);
        return null;
    }
    
    public void setListLinegraphDesempeñodocente(List <BeanEvaluacion_DP> lstEvaluaciones){
        List<Object[]> lstEva = new ArrayList();        
        for(int i=0; i<lstEvaluaciones.size(); i++){
         
            Object[] obj1 = { lstEvaluaciones.get(i).getFechaLineGraph(), lstEvaluaciones.get(i).getProfesor(), lstEvaluaciones.get(i).getNotaFinal()};           
            lstEva.add(obj1);       
        }        
        sessionDesempenoProfesor.setLstEvaLineGraph(lstEva);
    }

    public String llenarDatadeLineaUltimoAñoDocenteGlobal(){
        List<BeanFiltrosGraficos> listaFiltros=sessionDesempenoProfesor.getListaFiltros();      //NUMERO DE FILTROS  
        List<BeanEvaluacion_DP> listaParaGrfaicoDeBarrasSedes=new ArrayList<BeanEvaluacion_DP>();    
        for(int i=0; i<listaFiltros.size(); i++){
            if(listaFiltros.get(i).getNombreProfesor()!=null && listaFiltros.get(i).getNombreIndicador()==null){//ESTE IF EVITA QUE SE CAIGA AL NO ENCONTRAR PROFESORES
                if(listaFiltros.get(i).getFechaInicio()!=null && listaFiltros.get(i).getFechaFin()!=null){//SI HAY FILTROS FECHAS
                    int num=((listaFiltros.get(i).getFechaFin().getYear()-listaFiltros.get(i).getFechaInicio().getYear())*12)+(listaFiltros.get(i).getFechaFin().getMonth()-listaFiltros.get(i).getFechaInicio().getMonth());
                    System.out.println("num ::: "+num);
                    for(int j=0; j<num+1; j++){               
                             Date hoy=listaFiltros.get(i).getFechaFin();
                             Date fecha1= (Date)hoy.clone();
                             fecha1.setMonth(hoy.getMonth()-((num)-j));
                             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                             Date fechaActual =fecha1;
                             String fechaConFormato = sdf.format(fechaActual);
                     
                          
                     BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
                     if(listaFiltros.get(i).getNombreProfesor()!=null){
                     bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
                     }
                     if(listaFiltros.get(i).getNombreProfesor()==null){
                     bean.setProfesor("Global");
                     }   
                     bean.setFechaLineGraph(fecha1);     
                     List<BeanEvaluacion_DP> lstEvaluaciones=ln_C_SFEvaluacionRemote.desempenoDocentePorEvaluacion(listaFiltros.get(i),fechaConFormato);   
                     double valor=ln_C_SFEvaluacionRemote.promedioGeneralPorFiltroDesempeñoDocente(lstEvaluaciones);  
                     bean.setNotaFinal(valor);
                         listaParaGrfaicoDeBarrasSedes.add(bean);}      
                    
                    
                }else{
                    for(int j=0; j<13; j++){               
                             Date hoy=new Date();
                             Date fecha1= (Date)hoy.clone();
                             fecha1.setMonth(hoy.getMonth()-(12-j));
                             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                             Date fechaActual =fecha1;
                             String fechaConFormato = sdf.format(fechaActual);
                     
                          
                     BeanEvaluacion_DP bean=new BeanEvaluacion_DP();  
                     if(listaFiltros.get(i).getNombreProfesor()!=null){
                     bean.setProfesor(listaFiltros.get(i).getNombreProfesor().getApellidos()+" "+listaFiltros.get(i).getNombreProfesor().getNombres());
                     }
                     if(listaFiltros.get(i).getNombreProfesor()==null){
                     bean.setProfesor("Global");
                     }   
                     bean.setFechaLineGraph(fecha1);     
                     List<BeanEvaluacion_DP> lstEvaluaciones=ln_C_SFEvaluacionRemote.desempenoDocentePorEvaluacion(listaFiltros.get(i),fechaConFormato);   
                     double valor=ln_C_SFEvaluacionRemote.promedioGeneralPorFiltroDesempeñoDocente(lstEvaluaciones);  
                     bean.setNotaFinal(valor);
                         listaParaGrfaicoDeBarrasSedes.add(bean);}    
                    
                }
        
            }
            }
        setListLinegraphDesempeñodocenteGlobal(listaParaGrfaicoDeBarrasSedes);    
        Utils.addTarget(lineDesempeñoProf);
        return null;
    }
    
    public void setListLinegraphDesempeñodocenteGlobal(List <BeanEvaluacion_DP> lstEvaluaciones){
        List<Object[]> lstEva = new ArrayList();        
        for(int i=0; i<lstEvaluaciones.size(); i++){         
            Object[] obj1 = { lstEvaluaciones.get(i).getFechaLineGraph(), lstEvaluaciones.get(i).getProfesor(), lstEvaluaciones.get(i).getNotaFinal()};           
            lstEva.add(obj1);       
        }        
        sessionDesempenoProfesor.setLstEvaLineGlobalGraph(lstEva);
    }
    
    public String rutaPdf() {
        String rutaLocal = "";
        if(File.separator.equals("/")){
            rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + 
                        "usuarios" + File.separator;     
        }else{
            rutaLocal = "recursos" + File.separator + "img" + File.separator + 
                        "usuarios" + File.separator;   
        }
        ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
        String imageDirPath = servletCtx.getRealPath("/");
        String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
        String rutaImg = imageDirPath + rutaLocal;
        String rutaSave = rutaImg+timePath; 
        String rutaPdf = rutaSave+"Reporte-"+timePath+".pdf";
        /////////////////////////////////        
        try{
            try{
                Document document = new Document();
                File file = null; 
                FileOutputStream fos;
                file = new File(rutaPdf); 
                fos = new FileOutputStream(file); 
                PdfWriter.getInstance(document, fos);
                document.open();
                Image img = Image.getInstance(rutaImg+"cabecera.png");
                img.scalePercent(60);
                img.setAlignment(Image.ALIGN_CENTER);
                document.add(img);
              // addSelectFiltro(document);
                int cont = 0;
             //   if(sessionDesempenoEvaluador.isRGrafRolA()){
                    addImagenes(document, "Grafico Evaluacion Docente", exportGrafPNG( barDocenteEvalu, rutaSave+"GR.png"));
                    cont++;
                    addEspacio(cont, document);
              //  }
               // if(sessionDesempenoEvaluador.isRGrafEvaA()){
                    addImagenes(document, "Grafico Desempeño Docente", exportGrafPNG(lineaDesempenoGlobal, rutaSave+"GE.png"));
                    cont++;
                    addEspacio(cont, document);
              //  }
            //    if(sessionDesempenoEvaluador.isRGrafLineA()){
                    addImagenes(document, "Grafico Evaluacion Docente Indicador", exportGrafPNG(barDocIndicadorGraph, rutaSave+"GL.png"));
                    cont++;
                    addEspacio(cont, document);
              //  }
              //  if(sessionDesempenoEvaluador.isRGrafPieA()){
                    addImagenes(document, "Grafico Desempeño Docente Indicador", exportGrafPNG(lineDesempeñoProf, rutaSave+"GP.png"));
             //   }
                document.close();
                fos.close(); System.out.println(rutaPdf);
                return rutaPdf;
            }catch(FileNotFoundException e){
                e.printStackTrace();
                return null;
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }                        
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    } 
    
    public String exportGrafPNG(UIGraph graph, String ruta){
        if(graph != null){
            UIGraph dvtgraph = graph; 
            ImageView imgView = dvtgraph.getImageView();
            imgView.setImageSize(new Dimension(600,400));
            try{
                File file = null; 
                FileOutputStream fos;
                file = new File(ruta); 
                fos = new FileOutputStream(file); 
                imgView.exportToPNG(fos); 
                fos.close(); 
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return ruta;                       
    }
    
    public void addImagenes(Document document, 
                            String titulo, 
                            String rutaImg) throws DocumentException, MalformedURLException, IOException {
        Paragraph  paragraph =new Paragraph("\n"+titulo+"\n",
                       FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLDITALIC));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        Image img = Image.getInstance(rutaImg);
        img.scalePercent(75);
        img.setAlignment(Image.ALIGN_CENTER);
        File archivo = new File(rutaImg);
        archivo.delete();
        document.add(img);
    }
    
    public void addEspacio(int i,Document document) throws DocumentException {
        if(i == 1 || i == 3){
            document.newPage();
        }
    }
    
    public void setSessionDesempenoProfesor(bSessionDesempenoProfesor sessionDesempenoProfesor) {
        this.sessionDesempenoProfesor = sessionDesempenoProfesor;
    }

    public bSessionDesempenoProfesor getSessionDesempenoProfesor() {
        return sessionDesempenoProfesor;
    }



    public void setPieGraph(UIGraph pieGraph) {
        this.pieGraph = pieGraph;
    }

    public UIGraph getPieGraph() {
        return pieGraph;
    }

    public void setListaBarPrueba(List listaBarPrueba) {
        this.listaBarPrueba = listaBarPrueba;
    }

    public List getListaBarPrueba() {
        return listaBarPrueba;
    }

    public void setBarGraph(UIGraph barGraph) {
        this.barGraph = barGraph;
    }

    public UIGraph getBarGraph() {
        return barGraph;
    }

    public void setGraphDataPie(GraphDataModel graphDataPie) {
        this.graphDataPie = graphDataPie;
    }

    public GraphDataModel getGraphDataPie() {
        return graphDataPie;
    }

    public void setGraphDataBar(GraphDataModel graphDataBar) {
        this.graphDataBar = graphDataBar;
    }

    public GraphDataModel getGraphDataBar() {
        return graphDataBar;
    }

    public void setGraphDataBarHorizontal(GraphDataModel graphDataBarHorizontal) {
        this.graphDataBarHorizontal = graphDataBarHorizontal;
    }

    public GraphDataModel getGraphDataBarHorizontal() {
        return graphDataBarHorizontal;
    }

    public void setGraphDataLine(GraphDataModel graphDataLine) {
        this.graphDataLine = graphDataLine;
    }

    public GraphDataModel getGraphDataLine() {
        return graphDataLine;
    }

    public void setGraphDataBarNiveles(GraphDataModel graphDataBarNiveles) {
        this.graphDataBarNiveles = graphDataBarNiveles;
    }

    public GraphDataModel getGraphDataBarNiveles() {
        return graphDataBarNiveles;
    }

  

    public void setChoiceSedes(RichSelectOneChoice choiceSedes) {
        this.choiceSedes = choiceSedes;
    }

    public RichSelectOneChoice getChoiceSedes() {
        return choiceSedes;
    }

    public void setChoiceNiveles(RichSelectOneChoice choiceNiveles) {
        this.choiceNiveles = choiceNiveles;
    }

    public RichSelectOneChoice getChoiceNiveles() {
        return choiceNiveles;
    }

    public void setChoiceAreas(RichSelectOneChoice choiceAreas) {
        this.choiceAreas = choiceAreas;
    }

    public RichSelectOneChoice getChoiceAreas() {
        return choiceAreas;
    }

    public void setChoiceCursos(RichSelectOneChoice choiceCursos) {
        this.choiceCursos = choiceCursos;
    }

    public RichSelectOneChoice getChoiceCursos() {
        return choiceCursos;
    }

    public void setChoiceGrados(RichSelectOneChoice choiceGrados) {
        this.choiceGrados = choiceGrados;
    }

    public RichSelectOneChoice getChoiceGrados() {
        return choiceGrados;
    }


    public void setChoiceDocente(RichSelectOneChoice choiceDocente) {
        this.choiceDocente = choiceDocente;
    }

    public RichSelectOneChoice getChoiceDocente() {
        return choiceDocente;
    }

    public void setChoiceCriterios(RichSelectOneChoice choiceCriterios) {
        this.choiceCriterios = choiceCriterios;
    }

    public RichSelectOneChoice getChoiceCriterios() {
        return choiceCriterios;
    }

    public void setBtnAgregarFiltro(RichButton btnAgregarFiltro) {
        this.btnAgregarFiltro = btnAgregarFiltro;
    }

    public RichButton getBtnAgregarFiltro() {
        return btnAgregarFiltro;
    }

    public void setTbFiltrosBusqueda(RichTable tbFiltrosBusqueda) {
        this.tbFiltrosBusqueda = tbFiltrosBusqueda;
    }

    public RichTable getTbFiltrosBusqueda() {
        return tbFiltrosBusqueda;
    }


    public void setInputFechaInicio(RichInputDate inputFechaInicio) {
        this.inputFechaInicio = inputFechaInicio;
    }

    public RichInputDate getInputFechaInicio() {
        return inputFechaInicio;
    }

    public void setInputFechaFin(RichInputDate inputFechaFin) {
        this.inputFechaFin = inputFechaFin;
    }

    public RichInputDate getInputFechaFin() {
        return inputFechaFin;
    }


    public void setTbIndicadoresFiltro(RichTable tbIndicadoresFiltro) {
        this.tbIndicadoresFiltro = tbIndicadoresFiltro;
    }

    public RichTable getTbIndicadoresFiltro() {
        return tbIndicadoresFiltro;
    }


    public void setInputIndicador(RichInputText inputIndicador) {
        this.inputIndicador = inputIndicador;
    }

    public RichInputText getInputIndicador() {
        return inputIndicador;
    }

    public void setBarAreaGraph(UIGraph barAreaGraph) {
        this.barAreaGraph = barAreaGraph;
    }

    public UIGraph getBarAreaGraph() {
        return barAreaGraph;
    }

    public void setBarDocIndicadorGraph(UIGraph barDocIndicadorGraph) {
        this.barDocIndicadorGraph = barDocIndicadorGraph;
    }

    public UIGraph getBarDocIndicadorGraph() {
        return barDocIndicadorGraph;
    }

    public void setLineaDesempenoGlobal(UIGraph lineaDesempenoGlobal) {
        this.lineaDesempenoGlobal = lineaDesempenoGlobal;
    }

    public UIGraph getLineaDesempenoGlobal() {
        return lineaDesempenoGlobal;
    }

    public void setLineDesempeñoProf(UIGraph lineDesempeñoProf) {
        this.lineDesempeñoProf = lineDesempeñoProf;
    }

    public UIGraph getLineDesempeñoProf() {
        return lineDesempeñoProf;
    }

    public void setBarDocenteEvalu(UIGraph barDocenteEvalu) {
        this.barDocenteEvalu = barDocenteEvalu;
    }

    public UIGraph getBarDocenteEvalu() {
        return barDocenteEvalu;
    }
}
