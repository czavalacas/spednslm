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

public class bMigrarHorarios {
    FacesContext ctx = FacesContext.getCurrentInstance();
    private bSessionMigrarHorarios sessionMigrarHorarios;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private RichSelectOneChoice soc1;
    private RichInputFile fxML;

    public bMigrarHorarios() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionMigrarHorarios.getExec() == 0){
            sessionMigrarHorarios.setLstsedes(llenarComboSede());
            sessionMigrarHorarios.setExec(1);
        }
    }
    
    private ArrayList llenarComboSede() {
        List<BeanCombo> lstsedes = ln_C_SFUtilsRemote.getSedes_LN();
        return transformLstSelectItem(lstsedes);
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
                NodeList lstDia = doc.getElementsByTagName("day");
                NodeList lstPeriodo = doc.getElementsByTagName("period");
                NodeList lstCurso = doc.getElementsByTagName("subject");
                NodeList lstProfesor = doc.getElementsByTagName("teacher");
                NodeList lstSalon = doc.getElementsByTagName("classroom");
            }else{
                
            }  
        }catch(Exception e){
            e.printStackTrace();
        }
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
