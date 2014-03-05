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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.event.ClickEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;

import oracle.adf.view.rich.component.rich.input.RichTextEditor;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetail;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.dss.dataView.Attributes;
import oracle.dss.dataView.ComponentHandle;

import oracle.dss.dataView.DataComponentHandle;

import oracle.dss.dataView.ImageView;
import oracle.dss.dataView.SeriesComponentHandle;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.vista.Utils.Utils;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanUsuario;

public class bDesempenoEvaluador {
    private bSessionDesempenoEvaluador sessionDesempenoEvaluador;
    private RichSelectManyChoice choiceFSede;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFEvaluacionLocal ln_C_SFEvaluacionLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    BeanUsuario beanUsu;
    private String nombreUsuario;
    private RichSelectManyChoice choiceFArea;
    private RichSelectManyChoice choiceFRol;
    private RichSelectManyChoice choiceFEva;
    private RichShowDetail sd1;
    private RichShowDetail sd2;
    private RichSubform s2;
    private RichPanelFormLayout pfl1;
    private RichPanelDashboard pdash1;
    private RichPopup popDetalle;
    private RichPopup popDetPie;
    private RichPopup popCorreo;
    private RichPanelGroupLayout pgl2;
    Calendar cal= new GregorianCalendar();
    private RichTextEditor rte1;
    private String mensaje;
    private RichPanelFormLayout pfl4;
    private RichShowDetail sd3;
    private RichSelectBooleanCheckbox booleanCheckRol;
    private RichSelectBooleanCheckbox booleanCheckEva;
    private RichSelectBooleanCheckbox booleanCheckLine;
    private RichSelectBooleanCheckbox booleanCheckPie;
    private UIGraph grol;
    private UIGraph geva;
    private UIGraph gline;
    private UIGraph gpie;
    private RichPopup popKey;
    private String clave;

    public bDesempenoEvaluador() {
        nombreUsuario = beanUsuario.getNombres();
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionDesempenoEvaluador.getExec() == 0) {
            sessionDesempenoEvaluador.setLstRol(llenarComboRol());
            sessionDesempenoEvaluador.setLstEvaArea(llenarComboEvaArea());
            sessionDesempenoEvaluador.setLstEvaSede(llenarComboEvaSede());
            sessionDesempenoEvaluador.setLstEvaGeneral(llenarComboEvaGeneral());
            sessionDesempenoEvaluador.setLstSede(llenarComboSede());
            sessionDesempenoEvaluador.setLstArea(llenarComboAreaA());            
            sessionDesempenoEvaluador.setFechaActual(Utils.removeTime(cal.getTime()));
            cal.add(Calendar.MONTH, -1);
            sessionDesempenoEvaluador.setFechaAnterior(Utils.removeTime(cal.getTime()));                      
            sessionDesempenoEvaluador.setFechaEI(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEF(sessionDesempenoEvaluador.getFechaActual());
            sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaActual());
            setListEvaFiltro_aux();            
            sessionDesempenoEvaluador.setExec(1);
        }        
    }
    
    private ArrayList llenarComboRol(){
        ArrayList unItems = new ArrayList();
        List<BeanRol> lstbean = ln_C_SFRolRemote.getListRolbyNombreLN("Evaluador");
        for(BeanRol r : lstbean){
            unItems.add(new SelectItem(r.getNidRol(),
                                       r.getDescripcionRol().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaArea(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(2);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaSede(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(4);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEvaGeneral(){
        ArrayList unItems = new ArrayList();
        List<BeanUsuario> lstbean = ln_C_SFUsuarioLocal.getUsuariobyNidRolLN(5);
        for(BeanUsuario u : lstbean){
            unItems.add(new SelectItem(u.getNidUsuario(),
                                       u.getNombres().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboSede(){
        ArrayList unItems = new ArrayList();
        List<BeanSede> lstbean = ln_C_SFSedeRemote.getSedeLN();
        for(BeanSede b : lstbean){
            unItems.add(new SelectItem(b.getNidSede(),
                                       b.getDescripcionSede().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboAreaA() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> beanAreaA = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        for(BeanAreaAcademica a : beanAreaA){
            SelectItem se = new SelectItem(a.getNidAreaAcademica(), 
                                           a.getDescripcionAreaAcademica().toString());
            unItems.add(se);
        }
        return unItems;
    }
    
    public void limpiarFiltro(ActionEvent actionEvent) {
        sessionDesempenoEvaluador.setFechaEF(sessionDesempenoEvaluador.getFechaActual());
        sessionDesempenoEvaluador.setFechaEI(sessionDesempenoEvaluador.getFechaAnterior());
        sessionDesempenoEvaluador.setFechaPF(null);
        sessionDesempenoEvaluador.setFechaPI(null);
        sessionDesempenoEvaluador.setSelectedArea(null);
        sessionDesempenoEvaluador.setSelectedEvaluador(null);
        sessionDesempenoEvaluador.setSelectedRol(null);
        sessionDesempenoEvaluador.setSelectedSede(null);
        //valores auxiliares//
        sessionDesempenoEvaluador.setSelectedRol_aux(null);
        sessionDesempenoEvaluador.setSelectedEvaluador_aux(null);
        sessionDesempenoEvaluador.setSelectedSede_aux(null);
        sessionDesempenoEvaluador.setSelectedArea_aux(null);
        sessionDesempenoEvaluador.setFechaPI_axu(null);
        sessionDesempenoEvaluador.setFechaPF_aux(null);
        sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaAnterior());
        sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaActual());
        setListEvaFiltro_aux();
        Utils.addTargetMany(pfl1,sd1,sd2,pgl2); 
    }
    
    public void buscarByFiltro(ActionEvent actionEvent) {
        setListEvaFiltro_aux();
        sessionDesempenoEvaluador.setSelectedRol_aux(sessionDesempenoEvaluador.getSelectedRol());
        sessionDesempenoEvaluador.setSelectedEvaluador_aux(sessionDesempenoEvaluador.getSelectedEvaluador());
        sessionDesempenoEvaluador.setSelectedSede_aux(sessionDesempenoEvaluador.getSelectedSede());
        sessionDesempenoEvaluador.setSelectedArea_aux(sessionDesempenoEvaluador.getSelectedArea());
        sessionDesempenoEvaluador.setFechaPI_axu(sessionDesempenoEvaluador.getFechaPI());
        sessionDesempenoEvaluador.setFechaPF_aux(sessionDesempenoEvaluador.getFechaPF());
        sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaEI());
        sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaEF());        
    }
    
    public void Prueba(ActionEvent actionEvent) {
        String rutaPdf = "";
        String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";  
        String rutaLocal = "";
        if(File.separator.equals("/")){
            rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + 
                        "usuarios" + File.separator + timePath + ".pdf";     
        }else{
            rutaLocal = "recursos" + File.separator + "img" + File.separator + 
                        "usuarios" + File.separator + timePath + ".pdf";   
        }
        ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
        String imageDirPath = servletCtx.getRealPath("/");
        rutaPdf = imageDirPath + rutaLocal;
        rutaLocal = rutaPdf;
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
                if(sessionDesempenoEvaluador.isRGrafRolA()){
                    addImagenes(document, "Grafico Rol", exportGrafPNG(grol, "GR"));
                }
                if(sessionDesempenoEvaluador.isRGrafEvaA()){
                    addImagenes(document, "Grafico Evaluador", exportGrafPNG(geva, "GE"));
                }
                if(sessionDesempenoEvaluador.isRGrafLineA()){
                    addImagenes(document, "Grafico linea de Tiempo", exportGrafPNG(gline, "GL"));
                }
                if(sessionDesempenoEvaluador.isRGrafPieA()){
                    addImagenes(document, "Grafico Problemas Frecuentes", exportGrafPNG(gpie, "GP"));
                }
                document.close();
                fos.close();                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }                        
        }catch(Exception e){
            e.printStackTrace();
        }
        /////////////////////////////////  
    }
    
    public void exportPDF(FacesContext facesContext, java.io.OutputStream outputStream) {
        Document document = new Document();
        try{            
            PdfWriter.getInstance(document, outputStream);
            document.open();
            //////////////////////////////
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
            ////////////////////////////
            Image img = Image.getInstance(rutaImg+"cabecera.png");
            img.scalePercent(60);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            addSelectFiltro(document);
            int cont = 0;
            if(sessionDesempenoEvaluador.isRGrafRolA()){
                addImagenes(document, "Grafico Rol", exportGrafPNG(grol, rutaSave+"GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if(sessionDesempenoEvaluador.isRGrafEvaA()){
                addImagenes(document, "Grafico Evaluador", exportGrafPNG(geva, rutaSave+"GE.png"));
                cont++;
                addEspacio(cont, document);
            }
            if(sessionDesempenoEvaluador.isRGrafLineA()){
                addImagenes(document, "Grafico linea de Tiempo", exportGrafPNG(gline, rutaSave+"GL.png"));
                cont++;
                addEspacio(cont, document);
            }
            if(sessionDesempenoEvaluador.isRGrafPieA()){
                addImagenes(document, "Grafico Problemas Frecuentes", exportGrafPNG(gpie, rutaSave+"GP.png"));
            }
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    public void addEspacio(int i,Document document) throws DocumentException {
        if(i == 1 || i == 3){
            document.newPage();
        }
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
        document.add(img);
    }
    
    public void addSelectFiltro(Document document) throws DocumentException {
        Paragraph  paragraph =new Paragraph("Filtro",
                       FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLDITALIC));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dateE = "Fecha Evaluacion :";
        if(sessionDesempenoEvaluador.getFechaEI_aux() == null && sessionDesempenoEvaluador.getFechaEF_aux() == null ){
            dateE = dateE.concat("hasta "+formato.format(sessionDesempenoEvaluador.getFechaActual()));
        }else if(sessionDesempenoEvaluador.getFechaEI_aux() == null || sessionDesempenoEvaluador.getFechaEF_aux() == null ){
            if(sessionDesempenoEvaluador.getFechaEI_aux() != null){
                dateE = dateE.concat(formato.format(sessionDesempenoEvaluador.getFechaEI_aux().toString()));
            }
            if(sessionDesempenoEvaluador.getFechaEF_aux() != null ){
                dateE = dateE.concat(formato.format(sessionDesempenoEvaluador.getFechaEF_aux().toString()));
            }
        }else{
            dateE = dateE.concat(formato.format(sessionDesempenoEvaluador.getFechaEI_aux())
                                 +" - "+formato.format(sessionDesempenoEvaluador.getFechaEF_aux()));
        }
        mostrarFiltro(document, dateE,null);
        String dateP = "Fecha Planificacion :";
        if(sessionDesempenoEvaluador.getFechaPI_axu() == null && sessionDesempenoEvaluador.getFechaPF_aux() == null ){
            dateP = dateP.concat("hasta "+formato.format(sessionDesempenoEvaluador.getFechaActual()));
        }else if(sessionDesempenoEvaluador.getFechaPI_axu() == null || sessionDesempenoEvaluador.getFechaPF_aux() == null ){
            if(sessionDesempenoEvaluador.getFechaPI_axu() != null){
                dateP = dateP.concat(formato.format(sessionDesempenoEvaluador.getFechaPI_axu().toString()));
            }
            if(sessionDesempenoEvaluador.getFechaPF_aux() != null ){
                dateP = dateP.concat(formato.format(sessionDesempenoEvaluador.getFechaPF_aux().toString()));
            }
        }else{
            dateP = dateP.concat(formato.format(sessionDesempenoEvaluador.getFechaPI_axu())+
                                 " - "+formato.format(sessionDesempenoEvaluador.getFechaPF_aux()));
        }
        mostrarFiltro(document, dateP,null);
        if(sessionDesempenoEvaluador.getSelectedRol_aux() != null){
            String rol = "";
            int cont=0;
            int size = sessionDesempenoEvaluador.getSelectedRol_aux().size();
            for(Object o : sessionDesempenoEvaluador.getLstRol()){                
                if(size == cont){
                    break;
                }
                SelectItem si = (SelectItem)o;
                if(si.getValue() == sessionDesempenoEvaluador.getSelectedRol_aux().get(cont)){
                    rol = rol.concat(si.getLabel());
                    cont++;
                    if(size != cont){
                        rol = rol.concat(",");
                    }
                }
            }
            mostrarFiltro(document, "Rol",rol);
        }
        if(sessionDesempenoEvaluador.getSelectedEvaluador_aux() != null){
            List evaluadores = new ArrayList();
            evaluadores.addAll(sessionDesempenoEvaluador.getLstEvaArea());
            evaluadores.addAll(sessionDesempenoEvaluador.getLstEvaSede());
            evaluadores.addAll(sessionDesempenoEvaluador.getLstEvaGeneral());
            String eva = "";
            int cont=0;
            int size = sessionDesempenoEvaluador.getSelectedEvaluador_aux().size();
            for(Object o : evaluadores){                
                if(size == cont){
                    break;
                }
                SelectItem si = (SelectItem)o;
                if(si.getValue() == sessionDesempenoEvaluador.getSelectedEvaluador_aux().get(cont)){
                    eva = eva.concat(si.getLabel());
                    cont++;
                    if(size != cont){
                        eva = eva.concat(",");
                    }
                }
            }
            mostrarFiltro(document, "Evaluador",eva);
        }      
        if(sessionDesempenoEvaluador.getSelectedSede_aux() != null){
            String sede = "";
            int cont=0;
            int size = sessionDesempenoEvaluador.getSelectedSede_aux().size();
            for(Object o : sessionDesempenoEvaluador.getLstSede()){                
                if(size == cont){
                    break;
                }
                SelectItem si = (SelectItem)o;
                if(si.getValue() == sessionDesempenoEvaluador.getSelectedSede_aux().get(cont)){
                    sede = sede.concat(si.getLabel());
                    cont++;
                    if(size != cont){
                        sede = sede.concat(",");
                    }
                }
            }
            mostrarFiltro(document, "Sede",sede);
        }
        if(sessionDesempenoEvaluador.getSelectedArea_aux() != null){
            String area = "";
            int cont=0;
            int size = sessionDesempenoEvaluador.getSelectedArea_aux().size();
            for(Object o : sessionDesempenoEvaluador.getLstArea()){ 
                if(size == cont){
                    break;
                }
                SelectItem si = (SelectItem)o;
                if(si.getValue().toString().compareTo(sessionDesempenoEvaluador.getSelectedArea_aux().get(cont).toString()) == 0 ){
                    area = area.concat(si.getLabel());
                    cont++;
                    if(size != cont){
                        area = area.concat(",");
                    }
                }
            }
            mostrarFiltro(document, "Area",area);
        }
    }
    
    public void mostrarFiltro(Document document,
                              String titulo,
                              String selecionado) throws DocumentException {
        Paragraph  paragraph =new Paragraph(titulo,
                       FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD));
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph);
        if(selecionado != null){
            Paragraph  paragraph2 =new Paragraph(selecionado,
                           FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL));
            paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(paragraph2);
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
    
    public void setListEvaFiltro_aux(){
        int cont = 0;
        List <BeanEvaluacion> lst = desempenoFiltro(1, null, null, null,null);
        sessionDesempenoEvaluador.setLstEvaTable(lst);
        sessionDesempenoEvaluador.setRGrafEvaA(sessionDesempenoEvaluador.isRGrafEva());
        sessionDesempenoEvaluador.setRGrafRolA(sessionDesempenoEvaluador.isRGrafRol());
        sessionDesempenoEvaluador.setRGrafLineA(sessionDesempenoEvaluador.isRGrafLine());
        sessionDesempenoEvaluador.setRGrafPieA(sessionDesempenoEvaluador.isRGrafPie());
        if(sessionDesempenoEvaluador.isRGrafEva() == true){
            setListEvabarChart(lst);
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafRol() == true){
            List <BeanEvaluacion> lstBarRol = desempenoFiltro(5, null, null, null,null);
            setListEvabarChartRol(lstBarRol);
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafLine() == true){
            List <BeanEvaluacion> lstDate = desempenoFiltro(3, null, null, null,null);
            setListLinegraph(lstDate);
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafPie() == true){
            List <BeanEvaluacion> lstPie = desempenoFiltro(4, null, null, null,null);
            setListPiegraph(lstPie);
            cont++;
        }
        sessionDesempenoEvaluador.setColumnsDashboard(cont == 1 ? 1 : 2);
        sessionDesempenoEvaluador.setRowHeightDashboard(cont > 2 ? "350px" : "700px");
        if(pgl2 != null){
            Utils.addTargetMany(pgl2);
        }
    }
    
    public void selectBCheck(ValueChangeEvent vce) {
        RichSelectBooleanCheckbox ckBox = (RichSelectBooleanCheckbox)vce.getComponent();
        String texto = ckBox.getText().toString();
        int GrafEva = sessionDesempenoEvaluador.isRGrafEva() == false ? 1 : 0;
        int GrafRol = sessionDesempenoEvaluador.isRGrafRol() == false ? 1 : 0;
        int GrafLine = sessionDesempenoEvaluador.isRGrafLine() == false ? 1 : 0;
        int GrafPie = sessionDesempenoEvaluador.isRGrafPie() == false ? 1 : 0;
        if((GrafEva+GrafRol+GrafLine+GrafPie) >= 3){
            if(texto.compareTo("Rol(s)") == 0){
                booleanCheckRol.setSelected(true);
            }
            if(texto.compareTo("Evaluador(s)") == 0){
                booleanCheckEva.setSelected(true);
            }
            if(texto.compareTo("Linea de Tiempo") == 0){
                booleanCheckLine.setSelected(true);
            }
            if(texto.compareTo("Evaluaciones Justificadas") == 0){
                booleanCheckPie.setSelected(true);
            }
            Utils.addTargetMany(sd3);
        }
    }
    
    public List<BeanEvaluacion> desempenoFiltro(int tipoEvento,
                                                String nombre,
                                                String estado,
                                                String desProb,
                                                String desRol){      
       return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb, desRol,
                                                                      sessionDesempenoEvaluador.getSelectedRol(),
                                                                      sessionDesempenoEvaluador.getSelectedEvaluador(),
                                                                      sessionDesempenoEvaluador.getSelectedSede(),
                                                                      sessionDesempenoEvaluador.getSelectedArea(),
                                                                      sessionDesempenoEvaluador.getFechaPI(),
                                                                      sessionDesempenoEvaluador.getFechaPF(),
                                                                      sessionDesempenoEvaluador.getFechaEI(),
                                                                      sessionDesempenoEvaluador.getFechaEF());     
    }
    
    public List<BeanEvaluacion> desempenoFiltro_Aux(int tipoEvento,
                                                    String nombre,
                                                    String estado,
                                                    String desProb,
                                                    String desRol){
        return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb,desRol,
                                                                       sessionDesempenoEvaluador.getSelectedRol_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedEvaluador_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedSede_aux(),
                                                                       sessionDesempenoEvaluador.getSelectedArea_aux(),
                                                                       sessionDesempenoEvaluador.getFechaPI_axu(),
                                                                       sessionDesempenoEvaluador.getFechaPF_aux(),
                                                                       sessionDesempenoEvaluador.getFechaEI_aux(),
                                                                       sessionDesempenoEvaluador.getFechaEF_aux());
    }
    
    public void setListEvabarChart(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        String nombreEvaluador;
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){            
            nombreEvaluador = lst.get(i).getNombreEvaluador();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { nombreEvaluador, "Ejecutado", contEjecutados};
            Object[] obj2 = { nombreEvaluador, "Pendiente", contPendiente};
            Object[] obj3 = { nombreEvaluador, "No ejecutado", contNoEje};
            Object[] obj4 = { nombreEvaluador, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaBarChart(lstEva);        
    }
    
    public void setListLinegraph(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){
            Date date = lst.get(i).getEndDate();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { date, "Ejecutado", contEjecutados};
            Object[] obj2 = { date, "Pendiente", contPendiente};
            Object[] obj3 = { date, "No ejecutado", contNoEje};
            Object[] obj4 = { date, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaLineG(lstEva);
    }
    
    public void setListEvabarChartRol(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        String descripcionRol;
        int contEjecutados, contPendiente, contNoEje, contNoEjeJ;
        for(int i=0; i<lst.size(); i++){            
            descripcionRol = lst.get(i).getDescripcion();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEje = lst.get(i).getCantNoEjecutado();
            contNoEjeJ = lst.get(i).getCantNoJEjecutado();
            Object[] obj1 = { descripcionRol, "Ejecutado", contEjecutados};
            Object[] obj2 = { descripcionRol, "Pendiente", contPendiente};
            Object[] obj3 = { descripcionRol, "No ejecutado", contNoEje};
            Object[] obj4 = { descripcionRol, "No Justifico", contNoEjeJ};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
        }
        sessionDesempenoEvaluador.setLstEvaBarChartRol(lstEva);        
    }
    
    public void setListPiegraph(List <BeanEvaluacion> lst){
        List<Object[]> lstEva = new ArrayList();
        for(int i=0; i<lst.size(); i++){
            BeanEvaluacion eva = lst.get(i);
            Object[] obj1 = { "Problemas frecuentes", eva.getDescProblema(), eva.getCantProblema()};
            lstEva.add(obj1);
        }
        sessionDesempenoEvaluador.setLstEvaPieG(lstEva);
    }
    
    public void clickListenerGraph1(ClickEvent clickEvent) {  
        ComponentHandle handle = clickEvent.getComponentHandle();
        String nombre = null;
        String estado = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] groupInfo = dhandle.getGroupAttributes();
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (groupInfo != null) {
              for (Attributes attrs : groupInfo) {
                nombre = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
              for (Attributes attrs : seriesInfo) {
                  estado = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(nombre != null && estado != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, nombre, estado, null, null);
            sessionDesempenoEvaluador.setLstEvaDetalle(lst);
            beanUsu = ln_C_SFUsuarioLocal.findConstrainByIdLN(lst.get(0).getNidEvaluador());            
            sessionDesempenoEvaluador.setEvaluador(beanUsu);
            sessionDesempenoEvaluador.setEstado(estado);
            estadoEvaluacion(estado);            
            renderRol(beanUsu.getRol().getNidRol());
            renderMensaje(estado);
            Utils.showPopUpMIDDLE(popDetalle); 
        }        
    }
    
    public void clickListenerHBar(ClickEvent clickEvent) {           
        ComponentHandle handle = clickEvent.getComponentHandle();
        String rol = null;
        String estado = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] groupInfo = dhandle.getGroupAttributes();
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (groupInfo != null) {
              for (Attributes attrs : groupInfo) {
                rol = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
              for (Attributes attrs : seriesInfo) {
                estado = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(rol != null && estado != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, estado, null, rol);
            if(lst.size() > 0){
                renderRol(lst.get(0).getUsuario().getRol().getNidRol());
                estadoEvaluacion(estado);
                sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
                sessionDesempenoEvaluador.setTitleDialog("Detalle "+rol+" - "+estado);
                Utils.showPopUpMIDDLE(popDetPie);
            }            
        }
    }
    
    public void bSendM(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popCorreo);
    }
    
    public void confirmarCorreo(DialogEvent dialogEvent) {
        Utils.showPopUpMIDDLE(popKey);
    }
    
    public void confirmarEnvio(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            //String root = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            //String fpath = root+"/recursos/reportes/"+getSessionBeanConsultarPregrado().getTiempoFile()+"_reporte.pdf";
            //Utiles.sysout("fpath: "+fpath);
            String[] data = new String[4];
            data[0] = "kaka";
            data[1] = "C:/Users/David/Desktop/Scan.jpg";
            data[2] = mensaje;
            data[3] = "davidangeleshuaman@gmail.com";
            String msj = ln_C_SFCorreoRemote.enviarCorreo(data);
            //throwError(ctx,msj);
            popKey.hide();
            popCorreo.hide();
        }
    }
    
    public void clickListenerGraphPie(ClickEvent clickEvent) {
        ComponentHandle handle = clickEvent.getComponentHandle();
        String serie = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle)handle;
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (seriesInfo != null) {
              for (Attributes attrs : seriesInfo) {
                serie = (String)attrs.getValue(Attributes.LABEL_VALUE);
              }
            }
        }
        if(serie != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, null, serie,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            sessionDesempenoEvaluador.setTitleDialog("Problema "+serie);
            renderRol(0);
            sessionDesempenoEvaluador.setRenderProblema(false);
            sessionDesempenoEvaluador.setRenderComentario(true);
            Utils.showPopUpMIDDLE(popDetPie);
        }        
    }
    
    public void clickListenerGraphLine(ClickEvent event) {
        ComponentHandle handle = event.getComponentHandle();
        String serie = null;
        if (handle instanceof SeriesComponentHandle) {
            Attributes [] seriesInfo = ((SeriesComponentHandle)handle).getSeriesAttributes();            
            if(seriesInfo != null) {
                for(Attributes attrs: seriesInfo) {
                    serie = (String)attrs.getValue(Attributes.LABEL_VALUE);
                }
            }
        }
        if(serie != null){
            List <BeanEvaluacion> lst = desempenoFiltro_Aux(2, null, serie, null,null);
            sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
            estadoEvaluacion(serie);
            renderRol(0);
            sessionDesempenoEvaluador.setTitleDialog("Evaluacion(s) "+serie);
            Utils.showPopUpMIDDLE(popDetPie);
        }
    }

    
    public void estadoEvaluacion(String estado){
        sessionDesempenoEvaluador.setRenderProblema(false);
        sessionDesempenoEvaluador.setRenderComentario(false);
        if(estado.compareTo("No ejecutado") == 0){
            sessionDesempenoEvaluador.setRenderProblema(true);
            sessionDesempenoEvaluador.setRenderComentario(true);
        }
    }
    
    public void renderRol(int nidRol){
        sessionDesempenoEvaluador.setRenderNivel(true);
        sessionDesempenoEvaluador.setRenderSede(true);
        sessionDesempenoEvaluador.setRenderArea(true);
        if(nidRol == 4){
            sessionDesempenoEvaluador.setRenderArea(false);            
        }
        if(nidRol == 2){
            sessionDesempenoEvaluador.setRenderNivel(false);
            sessionDesempenoEvaluador.setRenderSede(false);
        }        
    }
    
    public void renderMensaje(String estado){
        if(beanUsuario.getCorreo() != null &&  beanUsu.getCorreo() != null && estado.compareTo("No Justifico") == 0){
            sessionDesempenoEvaluador.setRenderMensaje(true);
        }else{
            sessionDesempenoEvaluador.setRenderMensaje(false);
        }
    }
    
    public void setSessionDesempenoEvaluador(bSessionDesempenoEvaluador sessionDesempenoEvaluador) {
        this.sessionDesempenoEvaluador = sessionDesempenoEvaluador;
    }

    public bSessionDesempenoEvaluador getSessionDesempenoEvaluador() {
        return sessionDesempenoEvaluador;
    }

    public void setChoiceFSede(RichSelectManyChoice choiceFSede) {
        this.choiceFSede = choiceFSede;
    }

    public RichSelectManyChoice getChoiceFSede() {
        return choiceFSede;
    }

    public void setChoiceFArea(RichSelectManyChoice choiceFArea) {
        this.choiceFArea = choiceFArea;
    }

    public RichSelectManyChoice getChoiceFArea() {
        return choiceFArea;
    }

    public void setChoiceFRol(RichSelectManyChoice choiceFRol) {
        this.choiceFRol = choiceFRol;
    }

    public RichSelectManyChoice getChoiceFRol() {
        return choiceFRol;
    }

    public void setChoiceFEva(RichSelectManyChoice choiceFEva) {
        this.choiceFEva = choiceFEva;
    }

    public RichSelectManyChoice getChoiceFEva() {
        return choiceFEva;
    }

    public void setSd1(RichShowDetail sd1) {
        this.sd1 = sd1;
    }

    public RichShowDetail getSd1() {
        return sd1;
    }

    public void setSd2(RichShowDetail sd2) {
        this.sd2 = sd2;
    }

    public RichShowDetail getSd2() {
        return sd2;
    }

    public void setS2(RichSubform s2) {
        this.s2 = s2;
    }

    public RichSubform getS2() {
        return s2;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPdash1(RichPanelDashboard pdash1) {
        this.pdash1 = pdash1;
    }

    public RichPanelDashboard getPdash1() {
        return pdash1;
    }

    public void setPopDetalle(RichPopup popDetalle) {
        this.popDetalle = popDetalle;
    }

    public RichPopup getPopDetalle() {
        return popDetalle;
    }

    public void setPopDetPie(RichPopup popDetPie) {
        this.popDetPie = popDetPie;
    }

    public RichPopup getPopDetPie() {
        return popDetPie;
    }

    public void setPopCorreo(RichPopup popCorreo) {
        this.popCorreo = popCorreo;
    }

    public RichPopup getPopCorreo() {
        return popCorreo;
    }    

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setBeanUsu(BeanUsuario beanUsu) {
        this.beanUsu = beanUsu;
    }

    public BeanUsuario getBeanUsu() {
        return beanUsu;
    }

    public void setRte1(RichTextEditor rte1) {
        this.rte1 = rte1;
    }

    public RichTextEditor getRte1() {
        return rte1;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }

    public void setSd3(RichShowDetail sd3) {
        this.sd3 = sd3;
    }

    public RichShowDetail getSd3() {
        return sd3;
    }

    public void setBooleanCheckRol(RichSelectBooleanCheckbox booleanCheckRol) {
        this.booleanCheckRol = booleanCheckRol;
    }

    public RichSelectBooleanCheckbox getBooleanCheckRol() {
        return booleanCheckRol;
    }

    public void setBooleanCheckEva(RichSelectBooleanCheckbox booleanCheckEva) {
        this.booleanCheckEva = booleanCheckEva;
    }

    public RichSelectBooleanCheckbox getBooleanCheckEva() {
        return booleanCheckEva;
    }

    public void setBooleanCheckLine(RichSelectBooleanCheckbox booleanCheckLine) {
        this.booleanCheckLine = booleanCheckLine;
    }

    public RichSelectBooleanCheckbox getBooleanCheckLine() {
        return booleanCheckLine;
    }

    public void setBooleanCheckPie(RichSelectBooleanCheckbox booleanCheckPie) {
        this.booleanCheckPie = booleanCheckPie;
    }

    public RichSelectBooleanCheckbox getBooleanCheckPie() {
        return booleanCheckPie;
    }

    public void setGrol(UIGraph grol) {
        this.grol = grol;
    }

    public UIGraph getGrol() {
        return grol;
    }

    public void setGeva(UIGraph geva) {
        this.geva = geva;
    }

    public UIGraph getGeva() {
        return geva;
    }

    public void setGline(UIGraph gline) {
        this.gline = gline;
    }

    public UIGraph getGline() {
        return gline;
    }

    public void setGpie(UIGraph gpie) {
        this.gpie = gpie;
    }

    public UIGraph getGpie() {
        return gpie;
    }

    public void setPopKey(RichPopup popKey) {
        this.popKey = popKey;
    }

    public RichPopup getPopKey() {
        return popKey;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }
}
