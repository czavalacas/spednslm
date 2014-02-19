package sped.vista.beans.evaluacion.grafico_reporte;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.faces.bi.model.GraphDataModel;

import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;

import oracle.dss.graph.GraphModel;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.poi.xslf.usermodel.PieChartDemo;

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;

import sped.negocio.entidades.beans.BeanCurso;

import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;

import utils.system;

public class bDesempenoProfesor {
    
    bSessionDesempenoProfesor sessionDesempenoProfesor;
    
   
    private List listaBarPrueba;
    private UIGraph pieGraph;
    private UIGraph barGraph;
    private GraphDataModel graphDataPie;
    private GraphDataModel graphDataBar;
    private GraphDataModel graphDataBarHorizontal;
    private GraphDataModel graphDataLine;
    private GraphDataModel graphDataBarNiveles;
    
    private ChildPropertyTreeModel permisosTree;
    
    
    private List listaSedesFiltro;
    private List listaAreasFiltro;
    private List listaNivelesFiltro;
    
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFNivelRemote ln_C_SFNivelRemote;
    
    
    
    private RichSelectManyCheckbox checkBoxSedes;
    private RichSelectManyCheckbox checkBoxAreas;
    private RichSelectManyCheckbox checkBoxNiveles;

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
    this.setListaSedesFiltro(llenarSedesFiltro());
    this.setListaAreasFiltro(llenarAreasFiltro());
    this.setListaNivelesFiltro(llenarNivelesFiltro());
        System.out.println("SE EJECUTO");           
         
    }  
    
    public ArrayList llenarSedesFiltro() {
        ArrayList unItems = new ArrayList();
        List<BeanSede> roles = ln_C_SFSedeRemote.getSedeLN();
        System.out.println("TAMAÑO SEDES " + roles.size());
        for (BeanSede r : roles) {          
            unItems.add(new SelectItem(r.getNidSede().toString(), r.getDescripcionSede().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarAreasFiltro() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> roles = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        System.out.println("TAMAÑO Areas " + roles.size());
        for (BeanAreaAcademica r : roles) {          
            unItems.add(new SelectItem(r.getNidAreaAcademica().toString(), r.getDescripcionAreaAcademica().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarNivelesFiltro() {
        ArrayList unItems = new ArrayList();
        List<BeanNivel> roles = ln_C_SFNivelRemote.getNivelLN();
        System.out.println("TAMAÑO Sedes " + roles.size());
        for (BeanNivel r : roles) {          
            unItems.add(new SelectItem(r.getNidNivel().toString(), r.getDescripcionNivel().toString()));
        }
        return unItems;
    }
    
    
    
   /* public void llenarArbolAreaAcademicaYCursos(){
        
        List<BeanAreaAcademica> areaAca=new ArrayList<BeanAreaAcademica>();
        List<BeanCurso> curso=new ArrayList<BeanCurso>();
        curso.set(0, )
        
        areaAca.setCursosLista(cursosLista);
        permisosTree = new ChildPropertyTreeModel(ln_C_SFPermisosBeanRemote.getCrearArbolNuevoAllPermisos(beanSessionScopedAdministrarPermisos.getPermisosUser()),"listaHijos");
       
    }
*/
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

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setListaSedesFiltro(List listaSedesFiltro) {
        this.listaSedesFiltro = listaSedesFiltro;
    }

    public List getListaSedesFiltro() {
        return listaSedesFiltro;
    }

    public void setListaAreasFiltro(List listaAreasFiltro) {
        this.listaAreasFiltro = listaAreasFiltro;
    }

    public List getListaAreasFiltro() {
        return listaAreasFiltro;
    }

    public void valoresChoiceSede(ValueChangeEvent valueChangeEvent) {
        if(sessionDesempenoProfesor.getNidSedes()!=null){
            System.out.println("SIZE LISTA SEDES SESS"+ sessionDesempenoProfesor.getNidSedes().size());
        }
        
        System.out.println("SIZE LISTA SEDES VAL"+ checkBoxSedes.getValue());
        
        System.out.println("SIZE LISTA AREAS VAL"+ checkBoxAreas.getValue());
        System.out.println("SIZE LISTA NIVELES VAL"+ checkBoxNiveles.getValue());
       
    }

    public void setCheckBoxSedes(RichSelectManyCheckbox checkBoxSedes) {
        this.checkBoxSedes = checkBoxSedes;
    }

    public RichSelectManyCheckbox getCheckBoxSedes() {
        return checkBoxSedes;
    }

    public void setCheckBoxAreas(RichSelectManyCheckbox checkBoxAreas) {
        this.checkBoxAreas = checkBoxAreas;
    }

    public RichSelectManyCheckbox getCheckBoxAreas() {
        return checkBoxAreas;
    }

    public void setListaNivelesFiltro(List listaNivelesFiltro) {
        this.listaNivelesFiltro = listaNivelesFiltro;
    }

    public List getListaNivelesFiltro() {
        return listaNivelesFiltro;
    }

    public void setCheckBoxNiveles(RichSelectManyCheckbox checkBoxNiveles) {
        this.checkBoxNiveles = checkBoxNiveles;
    }

    public RichSelectManyCheckbox getCheckBoxNiveles() {
        return checkBoxNiveles;
    }
}
