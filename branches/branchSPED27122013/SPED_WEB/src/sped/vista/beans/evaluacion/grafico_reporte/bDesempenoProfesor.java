package sped.vista.beans.evaluacion.grafico_reporte;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.faces.bi.model.GraphDataModel;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.dss.graph.GraphModel;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.poi.xslf.usermodel.PieChartDemo;

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCriterioRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFGradoRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCurso;

import sped.negocio.entidades.beans.BeanFiltrosGraficos;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;

import sped.negocio.entidades.beans.BeanUsuario;

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
    
    private bSessionDesempenoProfesor sessionDesempenoProfesor;   
   
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
   


    public bDesempenoProfesor() {
        try {
            initGraphDataModelPie();
            initGraphDataModelBarPorSede();
            initGraphDataModelBarPorArea();
            initGraphDataModelLinePorAño();
            initGraphDataModelBarPorNivel();
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
        
        String mensaje=""; String mensaje2=""; String mensaje3=""; String mensaje4=""; String mensaje5=""; String mensaje6=""; String mensaje7="";
        
        if(bean.getNidSede()!=null){
            bean.setNombreSede(ln_C_SFSedeRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidSede())));
            mensaje=bean.getNombreSede().getDescripcionSede()+" -";
        }
        if(bean.getNidArea()!=null){
            bean.setNombreArea(ln_C_SFAreaAcademicaRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidArea())));
            mensaje2=bean.getNombreArea().getDescripcionAreaAcademica()+" -";
        }
        if(bean.getNidCurso()!=null){
            bean.setNombreCurso(ln_C_SFCursoRemoto.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidCurso())));
            mensaje3=bean.getNombreCurso().getDescripcionCurso()+" -";
        }
        if(bean.getNidNivele()!=null){
            bean.setNombreNivel(ln_C_SFNivelRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidNivele())));
            mensaje4=bean.getNombreNivel().getDescripcionNivel()+" -";
        }
        if(bean.getNidGrado()!=null){
            bean.setNombreGrado(ln_C_SFGradoRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidGrado())));
            mensaje5=bean.getNombreGrado().getDescripcionGrado()+" -";
        }
        if(bean.getDniDocente()!=null){
            bean.setNombreProfesor(ln_C_SFProfesorRemote.findConstrainByDni(sessionDesempenoProfesor.getDniDocente()));
            mensaje6=bean.getNombreProfesor().getApellidos()+" "+bean.getNombreProfesor().getNombres()+" -";
        }
        if(bean.getNidCriterio()!=null){
            bean.setNombreCriterio(ln_C_SFCriterioRemote.findConstrainByIdLN(Integer.parseInt(sessionDesempenoProfesor.getNidCriterio())));
            mensaje7=bean.getNombreCriterio().getDescripcionCriterio()+" -";
        }  
        
        bean.setCampoFiltroTrabla(mensaje+mensaje2+mensaje3+mensaje4+mensaje5+mensaje6+mensaje7);                 
        sessionDesempenoProfesor.getListaFiltros().add(bean);   
        limpiarComboBoxs(); 
        if (tbFiltrosBusqueda != null) {
            Utils.unselectFilas(tbFiltrosBusqueda);
            tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
            Utils.addTarget(tbFiltrosBusqueda);
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
        llenarChoices();
        Utils.addTargetMany(choiceAreas,choiceCriterios,choiceCursos,choiceDocente,choiceGrados,choiceNiveles,choiceSedes,inputFechaInicio,inputFechaFin);
        return null;
    }
    
    public String btnLimpiarFiltros() {
         limpiarComboBoxs();
        sessionDesempenoProfesor.getListaFiltros().clear();
        if (tbFiltrosBusqueda != null) {
            Utils.unselectFilas(tbFiltrosBusqueda);
            tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
            Utils.addTarget(tbFiltrosBusqueda);
        }
        return null;
    }
    
    public String deleteFiltros() {
        if(sessionDesempenoProfesor.getBeanFiltros()!=null){
        sessionDesempenoProfesor.getListaFiltros().remove(sessionDesempenoProfesor.getBeanFiltros());
           if (tbFiltrosBusqueda != null) {
               Utils.unselectFilas(tbFiltrosBusqueda);
               tbFiltrosBusqueda.setValue(sessionDesempenoProfesor.getListaFiltros());
               Utils.addTarget(tbFiltrosBusqueda);
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
    public void initGraphDataModelPie() {
        String[] columnLabels = { "Porcentajes de Desempeño" };

        String[] seriesLabels =
        { "0 - 10", "11 - 15", "15 - 20"};
        // 6 labels , so 6 data row
        Object[][] data2 = new Object[1][3];

        data2[0][0] = new Double(15);
        data2[0][1] = new Double(35);
        data2[0][2] = new Double(50);

        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        graphDataPie = new oracle.adf.view.faces.bi.model.GraphDataModel();
        graphDataPie.setDataSource(ds);
    }
    
    public void initGraphDataModelBarPorSede() {
        String[] columnLabels = { "Industria","Ecologica","SEDE 3", "SEDE 4", "SEDE 5", "SEDE 6" };

        String[] seriesLabels =
        {"Desempeño"};
        // 1er[] Tamaño de columnas empieza de 1,2,3..
        // 2do[] Tamaño de Labels Por Columna empieza de 0,1,2,3...
        //Ejemplo si queremos 3 columas con 5 barras cada uno seria   Object[][] data2 = new Object[3][5];
        //Para este caso queremos 5 columnas con 1 barra cada una 
        
        Object[][] data2 = new Object[6][1];
        data2[0][0] = new Double(40);               
        data2[1][0] = new Double(70);
        data2[2][0] = new Double(20);               
        data2[3][0] = new Double(90);
        data2[4][0] = new Double(60);        
        data2[5][0] = new Double(90); 
    

        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        graphDataBar = new oracle.adf.view.faces.bi.model.GraphDataModel();
        graphDataBar.setDataSource(ds);
    }
    
    public void initGraphDataModelLinePorAño() {
        String[] columnLabels = { "Enero","Febrero","Marzo", "Abril", "Mayo", "Junio","Julio","Agosto","Setiembre", "Octubre", "Noviembre", "Diciembre"  };

        String[] seriesLabels =
        {"Sede 1", "Sede 2","Sede 3", "Sede 4","Sede 5","Sede 6"};
        // 1er[] Tamaño de columnas empieza de 1,2,3..
        // 2do[] Tamaño de Labels Por Columna empieza de 0,1,2,3...
        //Ejemplo si queremos 3 columas con 5 barras cada uno seria   Object[][] data2 = new Object[3][5];
        //Para este caso queremos 5 columnas con 1 barra cada una 
        
        Object[][] data2 = new Object[12][6];
        
        //MESES 
        
        
        data2[0][0] = new Double(40);    
        data2[1][0] = new Double(50);
        data2[2][0] = new Double(70); 
        data2[3][0] = new Double(80);  
        data2[4][0] = new Double(90);
        data2[5][0] = new Double(70);
        data2[6][0] = new Double(60);
        data2[7][0] = new Double(90);
        data2[8][0] = new Double(80); 
        data2[9][0] = new Double(70); 
        data2[10][0] = new Double(90);     
        data2[11][0] = new Double(95);
        
        data2[0][1] = new Double(60);
        data2[1][1] = new Double(50);
        data2[2][1] = new Double(80);
        data2[3][1] = new Double(40);
        data2[4][1] = new Double(90);
        data2[5][1] = new Double(40);
        data2[6][1] = new Double(70);
        data2[7][1] = new Double(60);
        data2[8][1] = new Double(80);
        data2[9][1] = new Double(90);
        data2[10][1] = new Double(85);
        data2[11][1] = new Double(95);
        
        data2[0][2] = new Double(40);               
        data2[1][2] = new Double(50);
        data2[2][2] = new Double(70);  
        data2[3][2] = new Double(50); 
        data2[4][2] = new Double(80); 
        data2[5][2] = new Double(90);
        data2[6][2] = new Double(70);
        data2[7][2] = new Double(75);
        data2[8][2] = new Double(85);   
        data2[9][2] = new Double(60); 
        data2[10][2] = new Double(70); 
        data2[11][2] = new Double(90);
        
        data2[0][3] = new Double(70);
        data2[1][3] = new Double(60);
        data2[2][3] = new Double(50);
        data2[3][3] = new Double(40);
        data2[4][3] = new Double(60);
        data2[5][3] = new Double(70);
        data2[6][3] = new Double(50);
        data2[7][3] = new Double(80);
        data2[8][3] = new Double(90);
        data2[9][3] = new Double(70);
        data2[10][3] = new Double(80);
        data2[11][3] = new Double(95);
        
        data2[0][4] = new Double(60); 
        data2[1][4] = new Double(80);
        data2[2][4] = new Double(70);
        data2[3][4] = new Double(60); 
        data2[4][4] = new Double(80);
        data2[5][4] = new Double(90);
        data2[6][4] = new Double(60);
        data2[7][4] = new Double(70);
        data2[8][4] = new Double(80); 
        data2[9][4] = new Double(90); 
        data2[10][4] = new Double(70); 
        data2[11][4] = new Double(90);
        
        data2[0][5] = new Double(30); 
        data2[1][5] = new Double(40);
        data2[2][5] = new Double(50);
        data2[3][5] = new Double(60); 
        data2[4][5] = new Double(80);              
        data2[5][5] = new Double(90);
        data2[6][5] = new Double(60);
        data2[7][5] = new Double(70); 
        data2[8][5] = new Double(90); 
        data2[9][5] = new Double(80);
        data2[10][5] = new Double(70);   
        data2[11][5] = new Double(95);       
  
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        graphDataLine = new oracle.adf.view.faces.bi.model.GraphDataModel();
        graphDataLine.setDataSource(ds);
    }
    
    
    public void initGraphDataModelBarPorArea() {
        String[] columnLabels = { "Area Academica" };

        String[] seriesLabels =
        {"Matematicas","Lenguaje","ciencias","Ingles","Educacion Fisica"};
        // 1er[] Tamaño de columnas empieza de 1,2,3..
        // 2do[] Tamaño de Labels Por Columna empieza de 0,1,2,3...
        //Ejemplo si queremos 3 columas con 5 barras cada uno seria   Object[][] data2 = new Object[3][5];
        //Para este caso queremos 5 columnas con 1 barra cada una 
        
        Object[][] data2 = new Object[1][5];
        data2[0][0] = new Double(40);               
        data2[0][1] = new Double(70);
        data2[0][2] = new Double(20);               
        data2[0][3] = new Double(90);
        data2[0][4] = new Double(60);        
    

        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        graphDataBarHorizontal = new oracle.adf.view.faces.bi.model.GraphDataModel();
        graphDataBarHorizontal.setDataSource(ds);
    }
    
    public void initGraphDataModelBarPorNivel() {
        String[] columnLabels = { "Niveles" };

        String[] seriesLabels =
        {"Inicial","Primaria","Secundaria"};
        // 1er[] Tamaño de columnas empieza de 1,2,3..
        // 2do[] Tamaño de Labels Por Columna empieza de 0,1,2,3...
        //Ejemplo si queremos 3 columas con 5 barras cada uno seria   Object[][] data2 = new Object[3][5];
        //Para este caso queremos 5 columnas con 1 barra cada una 
        
        Object[][] data2 = new Object[1][3];
        data2[0][0] = new Double(40);               
        data2[0][1] = new Double(70);
        data2[0][2] = new Double(20);               
              
    

        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        graphDataBarNiveles = new oracle.adf.view.faces.bi.model.GraphDataModel();
        graphDataBarNiveles.setDataSource(ds);
    }
    
    /*
    public List listaDeValoresParaBarGrap() {
        
        ArrayList data = new ArrayList();
        Object obj[] = new Object[3];
        obj[0] = "ACTUAL";
        obj[1] = "CENTRAL";
        obj[2] = 40;
        data.add(obj);

        Object obj2[] = new Object[3];
        obj2[0] = "ACTUAL";
        obj2[1] = "ECOLOGICO";
        obj2[2] = 30;
        data.add(obj2);
        
    
        Object obj3[] = new Object[3];
        obj[0] = "ACTUAL";
        obj[1] = "INDUSTRIAL";
        obj[2] =20;
        data.add(obj3);

        Object obj4[] = new Object[3];
        obj2[0] = "ACTUAL";
        obj2[1] = "INICIAL";
        obj2[2] = 90;
        data.add(obj4); 
        
        Object obj5[] = new Object[3];
        obj2[0] = "ACTUAL";
        obj2[1] = "INICIAL";
        obj2[2] = 70;
        data.add(obj5); 
        
        return data;
    }*/

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


    
}
