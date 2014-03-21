package sped.vista.beans.migracion;

import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import org.apache.myfaces.trinidad.model.UploadedFile;

import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.entidades.beans.BeanCombo;

import sped.vista.Utils.Utils;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;

public class bMigrarHorarios {
    FacesContext ctx = FacesContext.getCurrentInstance();
    private bSessionMigrarHorarios sessionMigrarHorarios;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_C_SFAulaRemote ln_C_SFAulaRemote;
    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    private RichSelectOneChoice soc1;
    private RichInputFile fxML;

    public bMigrarHorarios() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionMigrarHorarios.getExec() == 0){
            sessionMigrarHorarios.setLstsedes(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionMigrarHorarios.setExec(1);
        }
    }

    private ArrayList llenarComboNivel(){        
        List<BeanCombo> lstnivel = ln_C_SFUtilsRemote.getNiveles_LN();
        return transformLstSelectItem(lstnivel);
    }
    
    private ArrayList transformLstSelectItem(List<BeanCombo> lstCombo){
        ArrayList unItems = new ArrayList();
        for(BeanCombo c : lstCombo){
            unItems.add(new SelectItem(c.getId(),
                                       c.getDescripcion().toString()));
        }
        return unItems;
    }
    
    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
            String ext = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
            if(ext.equalsIgnoreCase("xml")){
                String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
                String rutaLocal = "";
                if(File.separator.equals("/")){
                    rutaLocal = File.separator+"recursos" + File.separator + "xml" + File.separator + timePath + "." + ext;     
                }else{
                    rutaLocal = "recursos" + File.separator + "xml" + File.separator + timePath + "." + ext;   
                }
                ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
                String imageDirPath = servletCtx.getRealPath("/");
                InputStream inputStream = file.getInputStream();
                String rutaXml = imageDirPath + rutaLocal;
                TransferFile(rutaXml, inputStream);
                leerXML(rutaXml);
                System.out.println(rutaXml);
            }else{
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo xml", "Error", 1);
                fxML.resetValue();
                Utils.addTarget(fxML);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    public void TransferFile(String ruta, InputStream in) throws Exception {        
        OutputStream out = new FileOutputStream(new File(ruta));
        byte[] buf = new byte[2048];  
        int len;  
        while ((len = in.read(buf)) > 0) {  
            out.write(buf, 0, len);  
        }
        in.close();
        out.close();         
    }
    
    public void leerXML(String rutaXML){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(rutaXML));
            if(doc.getDocumentElement().getNodeName().toString().compareTo("timetable") == 0){
                doc.getDocumentElement().normalize();
                NodeList lstDia = doc.getElementsByTagName("daysdef");
                NodeList lstPeriodo = doc.getElementsByTagName("period");
                NodeList lstCurso = doc.getElementsByTagName("subject");
                NodeList lstProfesor = doc.getElementsByTagName("teacher");
                NodeList lstSalon = doc.getElementsByTagName("class");
                //nodelist maestras " "
                NodeList lstLeccion = doc.getElementsByTagName("lesson");
                NodeList lstCard = doc.getElementsByTagName("card");
                String error = validaNodeList(lstDia,lstPeriodo,lstCurso,lstProfesor,lstSalon,lstLeccion,lstCard);
                if(error.compareTo("") == 0){
                    String errorProf = leerProfesor(lstProfesor);
                    if(errorProf.compareTo("") == 0){
                        String errorSalon = leerSalon(lstSalon);
                        if(errorSalon.compareTo("") == 0){
                            String errorCurso = leerCurso(lstCurso);
                            if(errorCurso.compareTo("") == 0){
                                Utils.mostrarMensaje(ctx, null, "OK", 3);
                            }else{
                                Utils.mostrarMensaje(ctx, null, 
                                                     "Erro al leer campo curso : "
                                                     +errorCurso.substring(0, errorCurso.length()-1), 1);
                            }                            
                        }else{
                            Utils.mostrarMensaje(ctx, null, 
                                                 "Erro al leer campo salon : "
                                                 +errorSalon.substring(0, errorSalon.length()-1), 1);
                        }                        
                    }else{
                        Utils.mostrarMensaje(ctx, null, 
                                             "Erro al leer el dni : "
                                             +errorProf.substring(0, errorProf.length()-1), 1);
                    }                    
                }else{
                    Utils.mostrarMensaje(ctx, null, 
                                         "Faltan datos en el xml : "
                                         +error.substring(0, error.length()-1), 1);
                }
            }else{
                Utils.mostrarMensaje(ctx, null, "El archivo xml incorrecto. Cargue el archivo xml de horarios", 1);
            }  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String validaNodeList(NodeList lstDia, 
                                 NodeList lstPeriodo, 
                                 NodeList lstCurso, 
                                 NodeList lstProfesor, 
                                 NodeList lstSalon, 
                                 NodeList lstLeccion, 
                                 NodeList lstCard){
        String msj = "";
        if(lstDia.getLength() == 0){
            msj = msj.concat(" dias,");
        }
        if(lstPeriodo.getLength() == 0){
            msj = msj.concat(" periodos de horas,");
        }
        if(lstCurso.getLength() == 0){
            msj = msj.concat(" curso,");
        }
        if(lstProfesor.getLength() == 0){
            msj = msj.concat(" profesor,");
        }
        if(lstSalon.getLength() == 0){
            msj = msj.concat(" salon,");
        }
        if(lstLeccion.getLength() == 0){
            msj = msj.concat(" no hay horario establecido,");
        }
        return msj;
    }
    
    public String leerProfesor(NodeList lst) {
        String error = "";
        for (int i = 0; i < lst.getLength(); i++) {
            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;
                if(validaDni(elemento.getAttribute("email").toString()) != true){
                    error = error.concat(" "+elemento.getAttribute("name")+",");
                }else{
                    if(ln_C_SFProfesorRemote.exiteDni_LN(elemento.getAttribute("email").toString()) == false){
                        error = error.concat(" "+elemento.getAttribute("name")+",");
                    }
                }
            }
        }
        return error;            
    }
    
    public String leerSalon(NodeList lst) {
        String error = "";
        for (int i = 0; i < lst.getLength(); i++) {
            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;
                int nidAula = ln_C_SFAulaRemote.getAulaByDescripcion_LN(sessionMigrarHorarios.getNidSede(), 
                                                                        sessionMigrarHorarios.getNidNivel(), 
                                                                        elemento.getAttribute("name").toString());
                if(nidAula == 0){
                    error = error.concat(" "+elemento.getAttribute("name")+",");
                }else{
                    elemento.setAttribute("short", nidAula+"");
                }                
            }
        }
        return error;            
    }
    
    public String leerCurso(NodeList lst) {
        String error = "";
        for (int i = 0; i < lst.getLength(); i++) {
            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;
                int nidCurso = ln_C_SFCursoRemoto.getNidCursoByDescripcion_LN(elemento.getAttribute("name").toString()); 
                if(nidCurso == 0){
                    error = error.concat(" "+elemento.getAttribute("name")+",");
                }else{
                    elemento.setAttribute("short", nidCurso+"");
                }
            }
        }
        return error;            
    }
    
    public boolean validaDni(String dni){
        return Utils.isNumeric(dni) && dni.length() == 8 ? true : false;
    }    

    public void setSessionMigrarHorarios(bSessionMigrarHorarios sessionMigrarHorarios) {
        this.sessionMigrarHorarios = sessionMigrarHorarios;
    }

    public bSessionMigrarHorarios getSessionMigrarHorarios() {
        return sessionMigrarHorarios;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setFxML(RichInputFile fxML) {
        this.fxML = fxML;
    }

    public RichInputFile getFxML() {
        return fxML;
    }    
}
