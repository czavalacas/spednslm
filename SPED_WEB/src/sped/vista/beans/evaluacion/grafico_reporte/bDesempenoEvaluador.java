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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
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
import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.vista.Utils.Utils;
import sped.negocio.entidades.beans.BeanEvaluacionPlani;
import sped.negocio.entidades.beans.BeanUsuario;

public class bDesempenoEvaluador {
    private RichSelectManyChoice choiceFSede;        
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
    private RichTextEditor rte1;
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
    /***Mis Variables*****/    
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private static final String CLASE = "sped.vista.beans.evaluacion.grafico_reporte.bDesempenoEvaluador";
    Calendar cal= new GregorianCalendar();
    private bSessionDesempenoEvaluador sessionDesempenoEvaluador;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private String clave;
    private String nombreUsuario;
    private String correo;
    private String correoDelete;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    BeanUsuario beanUsu;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    @EJB
    private LN_C_SFEvaluacionLocal ln_C_SFEvaluacionLocal;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private RichSelectBooleanCheckbox chkUsu;

    public bDesempenoEvaluador() {        
        nombreUsuario = beanUsuario.getNombres();
        correo = beanUsuario.getCorreo();
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionDesempenoEvaluador.getExec() == 0) {
            sessionDesempenoEvaluador.setLstRol(Utils.llenarCombo(ln_C_SFUtilsRemote.getRolEvaluadores_LN()));
            sessionDesempenoEvaluador.setLstSede(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionDesempenoEvaluador.setLstArea(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));                
            sessionDesempenoEvaluador.setFechaActual(Utils.removeTime(cal.getTime()));
            cal.add(Calendar.MONTH, -1);
            sessionDesempenoEvaluador.setFechaAnterior(Utils.removeTime(cal.getTime()));                      
            sessionDesempenoEvaluador.setFechaEI(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEI_aux(sessionDesempenoEvaluador.getFechaAnterior());
            sessionDesempenoEvaluador.setFechaEF(sessionDesempenoEvaluador.getFechaActual());
            sessionDesempenoEvaluador.setFechaEF_aux(sessionDesempenoEvaluador.getFechaActual());            
            validarRol();
            setListEvaFiltro_aux();
            sessionDesempenoEvaluador.setExec(1);
        }        
    }
    
    /**
     * Metodo que valida los permisos por Rol
     * @author angeles
     */
    public void validarRol(){
        int nidRol = beanUsuario.getRol().getNidRol();      
        nidRol = beanUsuario.getIsSupervisor().compareTo("1") == 0 ? 1 : nidRol;        
        if(nidRol == 2 ){
            sessionDesempenoEvaluador.setLstEvaluador(
                Utils.llenarCombo(ln_C_SFUtilsRemote.getEvaluadoresByArea_LN(beanUsuario.getAreaAcademica().getNidAreaAcademica()))); 
        }else{
            sessionDesempenoEvaluador.setLstEvaluador(Utils.llenarCombo(ln_C_SFUtilsRemote.getEvaluadores_LN_WS()));
        }
        if(nidRol == 2 || nidRol == 4 || nidRol == 5){
            sessionDesempenoEvaluador.setRenderFRol(true);
            List lstrol = new ArrayList();
            lstrol.add(nidRol+"");            
            sessionDesempenoEvaluador.setSelectedRol(lstrol);
            List lstseni = new ArrayList();
            if(nidRol == 2){
                sessionDesempenoEvaluador.setRenderFArea(true);                
                lstseni.add(beanUsuario.getAreaAcademica().getNidAreaAcademica()+"");
                sessionDesempenoEvaluador.setSelectedArea(lstseni);
            }
            if(nidRol == 4){
                sessionDesempenoEvaluador.setRenderFSede(true);                
                lstseni.add(beanUsuario.getSede().getNidSede()+"");
                sessionDesempenoEvaluador.setSelectedSede(lstseni);
                sessionDesempenoEvaluador.setRenderFEvaluador(true);
            }            
            List lstEva = new ArrayList();
            lstEva.add(beanUsuario.getNidUsuario()+"");
            sessionDesempenoEvaluador.setSelectedEvaluador(lstEva);
        }
    }
    
    /**
     * Limpia el filtro de la vista 
     * @param actionEvent
     */
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
    
    /**
     * Metodo que llena los dash y pasa los filtros a variables auxiliares
     * @author angeles
     * @param actionEvent
     */
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
    
    /**
     * Metodo que se usa para guardar y obtener la ruta pdf
     * @return
     */
    public String rutaPdf() {
        String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
        String rutaPdf = Utils.rutaImagenes() + timePath + "-Reporte.pdf";     
        try{
            File file = null; 
            FileOutputStream fos;
            file = new File(rutaPdf); 
            fos = new FileOutputStream(file);
            generarPdf(fos);
            return rutaPdf;
        }catch(Exception e){
            e.printStackTrace();            
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "BAC", CLASE, 
                                                          "String rutaPdf()", "Error al obtener la ruta para guardar y leer pdf", 
                                                          Utils.getStack(e));
            return null;
        }
    } 
    
    public void exportPdf(FacesContext facesContext, java.io.OutputStream outputStream) {
        generarPdf(outputStream);
    }
    
    /**
     * Metodo que genera el pdf de los dash
     * @param fos
     */
    public void generarPdf(java.io.OutputStream fos) {
        String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
        String rutaImg = Utils.rutaImagenes();
        String rutaSave = rutaImg+timePath;
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();
            Image img = Image.getInstance(rutaImg+"reporgra.png");//cabecera.png
            img.scalePercent(24);
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
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "BAC", CLASE, 
                                                          "generarPdf(java.io.OutputStream fos)", 
                                                          "Error al generar el pdf en los dash", 
                                                          Utils.getStack(e));
        }
    }
    
    /**
     * Metodo auxiliar que aumenta una pagina extra para visualizar mejor el pdf de los dash
     * @author angeles
     * @param i
     * @param document
     * @throws DocumentException
     */
    public void addEspacio(int i,Document document) throws DocumentException {
        if(i == 1 || i == 3){
            document.newPage();
        }
    }
    
    /**
     * Metodo que agrega las imagenes a los pdf generados
     * @author angeles
     * @param document
     * @param titulo
     * @param rutaImg
     * @throws DocumentException
     * @throws MalformedURLException
     * @throws IOException
     */
    public void addImagenes(Document document, 
                            String titulo, 
                            String rutaImg) throws DocumentException, MalformedURLException, IOException {
        try{
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
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "BAC", CLASE, 
                                                          "addImagenes(Document document, String titulo, String rutaImg)", 
                                                          "Error al ingresar las imagenes al pdf", 
                                                          Utils.getStack(e));
        }
    }
    
    /**
     * Metodo que obtiene los valores de los filtros para agregarlos al pdf
     * @author angeles
     * @param document
     * @throws DocumentException
     */
    public void addSelectFiltro(Document document) throws DocumentException {
        Paragraph  paragraph =new Paragraph("Filtro",
                       FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLDITALIC));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);        
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
            String eva = "";
            int cont=0;
            int size = sessionDesempenoEvaluador.getSelectedEvaluador_aux().size();
            for(Object o : sessionDesempenoEvaluador.getLstEvaluador()){                
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
    
    /**
     * Metodo que muestra los filtros que se seleciono
     * @param document
     * @param titulo
     * @param selecionado
     * @throws DocumentException
     */
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
    
    /**
     * Metodo que convierte en imagen los dash
     * @author angeles
     * @param graph
     * @param ruta
     * @return
     */
    public String exportGrafPNG(UIGraph graph, String ruta){
        try{
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
        }catch(Exception e){
            return null;
        }                            
    }
    
    /**
     * Metodo que llena y muestra los dash dependiendo del filtro q se seleciono
     */
    public void setListEvaFiltro_aux(){
        List <BeanEvaluacionPlani> lst = desempenoFiltro(1, null, null, null,null);
        sessionDesempenoEvaluador.setRenderExcel(lst.size() == 0 ? false : true);
        sessionDesempenoEvaluador.setLstEvaTable(lst);
        sessionDesempenoEvaluador.setRGrafEvaA(sessionDesempenoEvaluador.isRGrafEva());
        sessionDesempenoEvaluador.setRGrafRolA(sessionDesempenoEvaluador.isRGrafRol());
        sessionDesempenoEvaluador.setRGrafLineA(sessionDesempenoEvaluador.isRGrafLine());
        sessionDesempenoEvaluador.setRGrafPieA(sessionDesempenoEvaluador.isRGrafPie());
        sessionDesempenoEvaluador.setREstadoUsuarioA(sessionDesempenoEvaluador.isREstadoUsuario());        
        if(sessionDesempenoEvaluador.isRGrafEva() == true){
            setListEvabarChart(lst);
        }
        if(sessionDesempenoEvaluador.isRGrafRol() == true){
            List <BeanEvaluacionPlani> lstBarRol = desempenoFiltro(5, null, null, null,null);
            setListEvabarChartRol(lstBarRol);
        }
        if(sessionDesempenoEvaluador.isRGrafLine() == true){
            List <BeanEvaluacionPlani> lstDate = desempenoFiltro(3, null, null, null,null);
            setListLinegraph(lstDate);
        }
        if(sessionDesempenoEvaluador.isRGrafPie() == true){
            List <BeanEvaluacionPlani> lstPie = desempenoFiltro(4, null, null, null,null);
            setListPiegraph(lstPie);
        }
        renderGraficos_Correo();
    }
    
    /**
     * Metodo que oculta o muestra los dash dependiendo el filtro
     * @param vce
     */
    public void selectBCheck(ValueChangeEvent vce) {
        RichSelectBooleanCheckbox ckBox = (RichSelectBooleanCheckbox)vce.getComponent();
        String texto = ckBox.getText().toString();
        int GrafEva = sessionDesempenoEvaluador.isRGrafEva() == true ? 1 : 0;
        int GrafRol = sessionDesempenoEvaluador.isRGrafRol() == true ? 1 : 0;
        int GrafLine = sessionDesempenoEvaluador.isRGrafLine() == true ? 1 : 0;
        int GrafPie = sessionDesempenoEvaluador.isRGrafPie() == true ? 1 : 0;
        int cont = GrafEva+GrafRol+GrafLine+GrafPie;
        ///valida para q no aya checkbox vacio
        if(cont <= 1){
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
        /////valida onde hace click para mostrar o ocultar el grafico
        if(texto.compareTo("Rol(s)") == 0){
            if(booleanCheckRol.isSelected()){
                List <BeanEvaluacionPlani> lstBarRol = desempenoFiltro_Aux(5, null, null, null,null);
                setListEvabarChartRol(lstBarRol);
                sessionDesempenoEvaluador.setRGrafRolA(true);
            }else{
                sessionDesempenoEvaluador.setRGrafRolA(false);
            }
        }
        if(texto.compareTo("Evaluador(s)") == 0){
            if(booleanCheckEva.isSelected()){
                List <BeanEvaluacionPlani> lst = desempenoFiltro_Aux(1, null, null, null,null);
                sessionDesempenoEvaluador.setLstEvaTable(lst);
                setListEvabarChart(lst);
                sessionDesempenoEvaluador.setRGrafEvaA(true);
            }else{
                sessionDesempenoEvaluador.setRGrafEvaA(false);
            }
        }
        if(texto.compareTo("Linea de Tiempo") == 0){
            if(booleanCheckLine.isSelected()){
                List <BeanEvaluacionPlani> lstDate = desempenoFiltro_Aux(3, null, null, null,null);
                setListLinegraph(lstDate);
                sessionDesempenoEvaluador.setRGrafLineA(true);
            }else{
                sessionDesempenoEvaluador.setRGrafLineA(false);
            }
        }
        if(texto.compareTo("Evaluaciones Justificadas") == 0){
            if(booleanCheckPie.isSelected()){
                List <BeanEvaluacionPlani> lstPie = desempenoFiltro_Aux(4, null, null, null,null);
                setListPiegraph(lstPie);
                sessionDesempenoEvaluador.setRGrafPieA(true);
            }else{
                sessionDesempenoEvaluador.setRGrafPieA(false);
            }
        }
        renderGraficos_Correo();     
    }
    
    public List<BeanEvaluacionPlani> desempenoFiltro(int tipoEvento,
                                                String nombre,
                                                String estado,
                                                String desProb,
                                                String desRol){  
        try{
            return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb, desRol,
                                                                           sessionDesempenoEvaluador.getSelectedRol(),
                                                                           sessionDesempenoEvaluador.getSelectedEvaluador(),
                                                                           sessionDesempenoEvaluador.getSelectedSede(),
                                                                           sessionDesempenoEvaluador.getSelectedArea(),
                                                                           sessionDesempenoEvaluador.getFechaPI(),
                                                                           sessionDesempenoEvaluador.getFechaPF(),
                                                                           sessionDesempenoEvaluador.getFechaEI(),
                                                                           sessionDesempenoEvaluador.getFechaEF(),
                                                                           sessionDesempenoEvaluador.isREstadoUsuario());
            
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "BAC", CLASE, 
                                                          "List<BeanEvaluacionPlani> desempenoFiltro(...)", 
                                                          "Error al obtener los datos del dash . Tipo de Evento "+tipoEvento, 
                                                          Utils.getStack(e));
            return null;
        }            
    }
    
    public List<BeanEvaluacionPlani> desempenoFiltro_Aux(int tipoEvento,
                                                    String nombre,
                                                    String estado,
                                                    String desProb,
                                                    String desRol){
        try{
            return ln_C_SFEvaluacionLocal.getDesempenoEvaluacionbyFiltroLN(tipoEvento,nombre,estado,desProb,desRol,
                                                                           sessionDesempenoEvaluador.getSelectedRol_aux(),
                                                                           sessionDesempenoEvaluador.getSelectedEvaluador_aux(),
                                                                           sessionDesempenoEvaluador.getSelectedSede_aux(),
                                                                           sessionDesempenoEvaluador.getSelectedArea_aux(),
                                                                           sessionDesempenoEvaluador.getFechaPI_axu(),
                                                                           sessionDesempenoEvaluador.getFechaPF_aux(),
                                                                           sessionDesempenoEvaluador.getFechaEI_aux(),
                                                                           sessionDesempenoEvaluador.getFechaEF_aux(),
                                                                           sessionDesempenoEvaluador.isREstadoUsuarioA());
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidUsuario(), "BAC", CLASE, 
                                                          "List<BeanEvaluacionPlani> desempenoFiltro_aux(...)", 
                                                          "Error al obtener los datos del dash . Tipo de Evento "+tipoEvento, 
                                                          Utils.getStack(e));
            e.printStackTrace();
            return null;
        }        
    }
    
    public void setListEvabarChart(List <BeanEvaluacionPlani> lst){
        List<Object[]> lstEva = new ArrayList();
        String nombreEvaluador;
        int contEjecutados, contPendiente, contNoEjecutado, contJustificado, contPorJustificar, contInjustificado;
        for(int i=0; i<lst.size(); i++){            
            nombreEvaluador = lst.get(i).getNombreEvaluador();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEjecutado = lst.get(i).getCantNoEjecutado();
            contJustificado = lst.get(i).getCantJustificado();
            contPorJustificar = lst.get(i).getCantPorJustificar();
            contInjustificado = lst.get(i).getCantInjustificado();
            Object[] obj1 = { nombreEvaluador, "Ejecutado", contEjecutados};
            Object[] obj2 = { nombreEvaluador, "Pendiente", contPendiente};
            Object[] obj3 = { nombreEvaluador, "No ejecutado", contNoEjecutado};
            Object[] obj4 = { nombreEvaluador, "Justificado", contJustificado};
            Object[] obj5 = { nombreEvaluador, "Por Justificar", contPorJustificar};
            Object[] obj6 = { nombreEvaluador, "Injustificado", contInjustificado};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj3);
            lstEva.add(obj4);
            lstEva.add(obj5);
            lstEva.add(obj6);
        }        
        sessionDesempenoEvaluador.setLstEvaBarChart(lstEva);        
    }
    
    public void setListLinegraph(List <BeanEvaluacionPlani> lst){
        List<Object[]> lstEva = new ArrayList();
        int cont = 0;
        int contEjecutados, contPendiente, contNoEjecutado, contJustificado, contPorJustificar, contInjustificado;
        for(int i=0; i<lst.size(); i++){
            Date date = lst.get(i).getEndDate();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEjecutado = lst.get(i).getCantNoEjecutado();
            contJustificado = lst.get(i).getCantJustificado();
            contPorJustificar = lst.get(i).getCantPorJustificar();
            contInjustificado = lst.get(i).getCantInjustificado();
            Object[] obj1 = { date, "Ejecutado", contEjecutados};
            Object[] obj2 = { date, "Pendiente", contPendiente};
            Object[] obj3 = { date, "No ejecutado", contNoEjecutado};
            Object[] obj4 = { date, "Justificado", contJustificado};
            Object[] obj5 = { date, "Por Justificar", contPorJustificar};
            Object[] obj6 = { date, "Injustificado", contInjustificado};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj4);
            lstEva.add(obj5);
            lstEva.add(obj6);
            cont++;
        }
        if(gline != null){
            if(cont < 2 && lstEva.size() != 0){
                lstEva.clear();
                gline.setEmptyText("No hay suficientes datos para mostrar este Grafico");
            }else{
                gline.setEmptyText("No hay ning\u00FAn dato que mostrar");
            }
        }
        sessionDesempenoEvaluador.setLstEvaLineG(lstEva);
    }
    
    public void setListEvabarChartRol(List <BeanEvaluacionPlani> lst){
        List<Object[]> lstEva = new ArrayList();
        String descripcionRol;
        int contEjecutados, contPendiente, contNoEjecutado, contJustificado, contPorJustificar, contInjustificado;
        for(int i=0; i<lst.size(); i++){            
            descripcionRol = lst.get(i).getDescripcion();
            contEjecutados = lst.get(i).getCantEjecutado();
            contPendiente = lst.get(i).getCantPendiente();
            contNoEjecutado = lst.get(i).getCantNoEjecutado();
            contJustificado = lst.get(i).getCantJustificado();
            contPorJustificar = lst.get(i).getCantPorJustificar();
            contInjustificado = lst.get(i).getCantInjustificado();
            Object[] obj1 = { descripcionRol, "Ejecutado", contEjecutados};
            Object[] obj2 = { descripcionRol, "Pendiente", contPendiente};
            Object[] obj3 = { descripcionRol, "No ejecutado", contNoEjecutado};
            Object[] obj4 = { descripcionRol, "Justificado", contJustificado};
            Object[] obj5 = { descripcionRol, "Por Justificar", contPorJustificar};
            Object[] obj6 = { descripcionRol, "Injustificado", contInjustificado};
            lstEva.add(obj1);
            lstEva.add(obj2);
            lstEva.add(obj3);
            lstEva.add(obj4);
            lstEva.add(obj5);
            lstEva.add(obj6);
        }
        sessionDesempenoEvaluador.setLstEvaBarChartRol(lstEva);        
    }
    
    public void setListPiegraph(List <BeanEvaluacionPlani> lst){
        List<Object[]> lstEva = new ArrayList();
        for(int i=0; i<lst.size(); i++){
            BeanEvaluacionPlani eva = lst.get(i);
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
            List <BeanEvaluacionPlani> lst = desempenoFiltro_Aux(2, nombre, estado, null, null);
            sessionDesempenoEvaluador.setLstEvaDetalle(lst);
            beanUsu = ln_C_SFUsuarioLocal.findConstrainByIdLN(lst.get(0).getNidEvaluador());
            sessionDesempenoEvaluador.setEvaluador(beanUsu);
            sessionDesempenoEvaluador.setEstado(estado);
            estadoEvaluacion(estado);            
            renderRol(beanUsu.getRol().getNidRol());
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
            List <BeanEvaluacionPlani> lst = desempenoFiltro_Aux(2, null, estado, null, rol);
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
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();        
        if(outcome == DialogEvent.Outcome.ok){
            Utils.showPopUpMIDDLE(popKey);   
        }
        if(outcome == DialogEvent.Outcome.no){
            Utils.showPopUpMIDDLE(popKey);   
            sessionDesempenoEvaluador.setTypePopUpCorreo("none");
            sessionDesempenoEvaluador.setLstCorreo(new ArrayList());
            sessionDesempenoEvaluador.setCorreo("");
            sessionDesempenoEvaluador.setAsunto(""); 
            sessionDesempenoEvaluador.setMensaje("");
        }
    }
    
    public void confirmarEnvio(DialogEvent dialogEvent) {
        String correos = "";
        int size = sessionDesempenoEvaluador.getLstCorreo().size();
        for(int i = 0 ; i < size; i++){
            correos = correos.concat(sessionDesempenoEvaluador.getLstCorreo().get(i));
            if(i != size-1){
                correos = correos.concat(";");
            }
        }
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();        
        if(outcome == DialogEvent.Outcome.ok){
            String rutapdf = rutaPdf();
            if(rutapdf != null){
                String[] data = new String[8];
                data[0] = formato.format(sessionDesempenoEvaluador.getFechaActual());
                data[1] = rutapdf;
                data[2] = sessionDesempenoEvaluador.getAsunto();
                data[3] = correos;
                data[4] = sessionDesempenoEvaluador.getMensaje();
                data[5] = correo;
                data[6] = clave;
                data[7] = "1";
                boolean valida = ln_C_SFCorreoRemote.enviarCorreoHTML(data);  
                if(valida){
                    Utils.mostrarMensaje(ctx, "Se envio el correo", "Operacion Correcta", 3);
                    sessionDesempenoEvaluador.setTypePopUpCorreo("none");
                    sessionDesempenoEvaluador.setLstCorreo(new ArrayList());
                    sessionDesempenoEvaluador.setCorreo("");
                    sessionDesempenoEvaluador.setAsunto(""); 
                    sessionDesempenoEvaluador.setMensaje("");
                }else{
                    Utils.mostrarMensaje(ctx, "Ocurrio un error inesperado", "Operacion Incorrecta", 2);
                    Utils.showPopUpMIDDLE(popCorreo);
                }                
            }            
        }
        if(outcome == DialogEvent.Outcome.no){
            Utils.showPopUpMIDDLE(popCorreo);
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
            List <BeanEvaluacionPlani> lst = desempenoFiltro_Aux(2, null, null, serie,null);
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
            List <BeanEvaluacionPlani> lst = desempenoFiltro_Aux(2, null, serie, null,null);
            if(lst.size() > 0){
                sessionDesempenoEvaluador.setLstEvaDetallePie(lst);
                estadoEvaluacion(serie);
                renderRol(0);
                sessionDesempenoEvaluador.setTitleDialog("Evaluacion(s) "+serie);
                Utils.showPopUpMIDDLE(popDetPie);
            }            
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
    
    public void agregarCorreo(ActionEvent actionEvent) {
        int cont = 0;
        if(sessionDesempenoEvaluador.getCorreo() != null){
            for(String correo : sessionDesempenoEvaluador.getLstCorreo()){
                if(correo.compareTo(sessionDesempenoEvaluador.getCorreo()) == 0){
                    cont = 1;
                    break;
                }
            }
            if(cont != 1){
                sessionDesempenoEvaluador.setTypePopUpCorreo("okCancel");                
                sessionDesempenoEvaluador.getLstCorreo().add(sessionDesempenoEvaluador.getCorreo());
                sessionDesempenoEvaluador.setCorreo("");
                Utils.addTarget(popCorreo);
            }
        }        
        
    }
    
    public void removeCorreo(ActionEvent actionEvent) {
        if(sessionDesempenoEvaluador.getLstCorreo() != null){
            sessionDesempenoEvaluador.getLstCorreo().remove(correoDelete);
            if(sessionDesempenoEvaluador.getLstCorreo().size() == 0){
                sessionDesempenoEvaluador.setTypePopUpCorreo("none"); 
            }
            Utils.addTarget(popCorreo);
        } 
    }
    
    public void renderGraficos_Correo(){
        int cont = 0;
        sessionDesempenoEvaluador.setRenderMensaje(false);
        if(sessionDesempenoEvaluador.isRGrafEvaA() == true){
            if(sessionDesempenoEvaluador.getLstEvaBarChart().size() > 0){
                sessionDesempenoEvaluador.setRenderMensaje(true);
            }
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafRolA() == true){
            if(sessionDesempenoEvaluador.getLstEvaBarChartRol().size() > 0){
                sessionDesempenoEvaluador.setRenderMensaje(true);
            }
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafLineA() == true){
            if(sessionDesempenoEvaluador.getLstEvaLineG().size() > 0){
                sessionDesempenoEvaluador.setRenderMensaje(true);
            }
            cont++;
        }
        if(sessionDesempenoEvaluador.isRGrafPieA() == true){
            if(sessionDesempenoEvaluador.getLstEvaPieG().size() > 0){
                sessionDesempenoEvaluador.setRenderMensaje(true);
            }
            cont++;
        }
        sessionDesempenoEvaluador.setColumnsDashboard(cont == 1 ? 1 : 2);
        sessionDesempenoEvaluador.setRowHeightDashboard(cont > 2 ? "350px" : "700px");
        if(pgl2 != null){
            Utils.addTargetMany(pgl2);
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreoDelete(String correoDelete) {
        this.correoDelete = correoDelete;
    }

    public String getCorreoDelete() {
        return correoDelete;
    }

    public void setChkUsu(RichSelectBooleanCheckbox chkUsu) {
        this.chkUsu = chkUsu;
    }

    public RichSelectBooleanCheckbox getChkUsu() {
        return chkUsu;
    }
}
