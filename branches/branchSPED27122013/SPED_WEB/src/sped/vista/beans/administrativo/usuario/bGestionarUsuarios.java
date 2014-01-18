package sped.vista.beans.administrativo.usuario;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.imageio.ImageIO;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeNivelRemote;
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.beans.BeanSede;
import sped.negocio.entidades.beans.BeanSedeNivel;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bGestionarUsuarios {
    private RichButton b1;
    private bSessionGestionarUsuarios sessionGestionarUsuarios;     
    private RichTable t1;
    private RichPopup popGestionUsuario;
    private RichButton b2;
    private RichButton b3;
    private RichButton b4;
    private RichSelectOneChoice choiceTipoRol;
    private UISelectItems si1;
    private RichSelectOneChoice choiceTipoArea;
    private UISelectItems si2;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichPopup popConfirmar;
    private RichPanelFormLayout pfl1;
    private RichInputText itNombres;    
    private UISelectItems si3;
    private RichSelectOneChoice choiceFTipoEstado;
    private RichSelectOneChoice choiceFTipoRol;
    private UISelectItems si4;
    private RichSelectOneChoice choiceFTipoArea;
    private UISelectItems si5;
    private RichInputText itDni;
    private RichInputText itUsuario;
    private RichInputText itClave;
    private RichPanelGridLayout pgl2;    
    private RichPanelFormLayout pfl3;
    private RichSelectOneChoice choiceFTipoSede;
    private UISelectItems si6;
    private RichSelectOneChoice choiceFTipoNivel;
    private UISelectItems si7;
    private RichInputFile fileImg;
    private RichImage i1;    
    private int cont=0;
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_USUARIO = "mapLN_C_SFUsuario#sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote";
    @EJB
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFSedeRemote ln_C_SFSedeRemote;
    @EJB
    private LN_C_SFSedeNivelRemote ln_C_SFSedeNivelRemote;
    private RichSelectOneChoice choiceTipoSede;
    private UISelectItems si8;
    private RichSelectOneChoice choiceTipoNivel;
    private UISelectItems si9;

    public bGestionarUsuarios() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_USUARIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (sessionGestionarUsuarios.getExec() == 0) {
            sessionGestionarUsuarios.setExec(1);
            sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN("1"));
            sessionGestionarUsuarios.setLstRol(this.llenarComboRol());
            sessionGestionarUsuarios.setLstAreaAcademica(this.llenarComboAreaA());
            sessionGestionarUsuarios.setLstEstadoUsario(this.llenarComboEstado());
            sessionGestionarUsuarios.setLstSede(this.llenarComboSede());
        } else {
        }
    }

    private ArrayList llenarComboRol() {
        ArrayList unItems = new ArrayList();
        List<BeanRol> beanrol = ln_C_SFRolRemote.getRolLN();
        for (BeanRol r : beanrol) {
            unItems.add(new SelectItem(r.getNidRol(), 
                                       r.getDescripcionRol().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboAreaA() {
        ArrayList unItems = new ArrayList();
        List<BeanAreaAcademica> beanAreaA = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        for(BeanAreaAcademica a : beanAreaA){
            unItems.add(new SelectItem(a.getNidAreaAcademica(), 
                                       a.getDescripcionAreaAcademica().toString()));
        }
        return unItems;
    }
    
    private ArrayList llenarComboEstado(){
        ArrayList unItems = new ArrayList();
        List<BeanConstraint> lstbean = ln_C_SFUtilsRemote.getListaConstraintsLN("estado_usuario", "admusua");
        for(BeanConstraint bc : lstbean){
            int valorCampo = Integer.parseInt(bc.getValorCampo())+1;//(0 y 1)aumento +1 para poder validar el estado usuario(1 y 2)
            unItems.add(new SelectItem(valorCampo,
                                       bc.getDescripcionAMostrar()));
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
    
    private ArrayList llenarComboNivel(int nidSede){
        ArrayList unItems = new ArrayList();
        List<BeanSedeNivel> lstSedeNivel = ln_C_SFSedeNivelRemote.getSedeNivelbyNidSedeLN(nidSede);
        for(BeanSedeNivel sn : lstSedeNivel){            
            unItems.add(new SelectItem(sn.getNivel().getNidNivel(),
                                       sn.getNivel().getDescripcionNivel()));
        }
        return unItems;
    }

    public void selecionarUsuario(SelectionEvent se) {        
        b3.setDisabled(false);
        resetValues();
        RichTable t = (RichTable) se.getSource();
        BeanUsuario usuario = (BeanUsuario) t.getSelectedRowData();
        sessionGestionarUsuarios.setNidUsuario(usuario.getNidUsuario());
        sessionGestionarUsuarios.setNombres(usuario.getNombres());
        sessionGestionarUsuarios.setDni(usuario.getDni());
        sessionGestionarUsuarios.setUsuario(usuario.getUsuario());
        sessionGestionarUsuarios.setClave(usuario.getClave());
        sessionGestionarUsuarios.setNidRol(usuario.getRol().getNidRol());
        if(usuario.getFoto() != null){
            sessionGestionarUsuarios.setRenderImg(true);
        }
        if(usuario.getAreaAcademica() != null){
            sessionGestionarUsuarios.setNidAreaAcademica(usuario.getAreaAcademica().getNidAreaAcademica());
            sessionGestionarUsuarios.setRenderAreaAcdemica(true);
        }
        if(usuario.getSedeNivel()!=null){
            sessionGestionarUsuarios.setNidSede(usuario.getSedeNivel().getSede().getNidSede());
            sessionGestionarUsuarios.setLstNivel(llenarComboNivel(usuario.getSedeNivel().getSede().getNidSede()));
            sessionGestionarUsuarios.setNidNivel(usuario.getSedeNivel().getNivel().getNidNivel());
            sessionGestionarUsuarios.setRenderNivel(true);
            sessionGestionarUsuarios.setRenderSede(true);
        }
        if(i1!=null){
            i1.setSource("/imageservlet?nomusuario="+usuario.getUsuario());
        }
        if(Integer.parseInt(usuario.getEstadoUsuario()) != 0){
            b3.setText("Anular");
            b2.setDisabled(false);
        }else{
            b3.setText("Activar");
            b2.setDisabled(true);
        }
        Utils.addTargetMany(b2, b3);
    }

    public void nuevoUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        sessionGestionarUsuarios.setRenderActualizar(true);
        sessionGestionarUsuarios.setTipoEvento(1);
        sessionGestionarUsuarios.setTitleDialogGestion("Registrar Usuario");
        sessionGestionarUsuarios.setNomBtnGestion("Registrar");        
        resetValues();
        Utils.showPopUpMIDDLE(popGestionUsuario);
        Utils.unselectFilas(t1);
        Utils.addTargetMany(b2, b3);
    }

    public void modificarUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        sessionGestionarUsuarios.setTipoEvento(2);
        sessionGestionarUsuarios.setRenderActualizar(false);
        sessionGestionarUsuarios.setTitleDialogGestion("Modificar usuario : "+
                                                       sessionGestionarUsuarios.getNombres());
        sessionGestionarUsuarios.setNomBtnGestion("Actualizar");
        Utils.unselectFilas(t1);        
        if(i1!=null){
            Utils.addTarget(i1);
        }
        Utils.showPopUpMIDDLE(popGestionUsuario);
        Utils.addTargetMany(b2, b3);
    }

    public void anularUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        if(b3.getText() != "Activar"){
            sessionGestionarUsuarios.setTipoEvento(3);
            Utils.showPopUpMIDDLE(popConfirmar);
            Utils.addTargetMany(b2, b3);
        }else{
            sessionGestionarUsuarios.setTipoEvento(4);
            btnGestionarUsuario_aux();
        }
        Utils.unselectFilas(t1);
    }
    
    public void confirmaAnular(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            btnGestionarUsuario_aux();
        }
    }

    public void btnGestionarUsuario(ActionEvent actionEvent) {
        btnGestionarUsuario_aux();
    }
    
    public void btnGestionarUsuario_aux(){
        ln_T_SFUsuarioRemote.gestionUsuarioLN(sessionGestionarUsuarios.getTipoEvento(), 
                                              sessionGestionarUsuarios.getNombres(),
                                              sessionGestionarUsuarios.getDni(), 
                                              sessionGestionarUsuarios.getNidRol(), 
                                              sessionGestionarUsuarios.getNidAreaAcademica(), 
                                              sessionGestionarUsuarios.getUsuario(), 
                                              sessionGestionarUsuarios.getClave(), 
                                              sessionGestionarUsuarios.getNidUsuario(),
                                              sessionGestionarUsuarios.getRutaImg(),
                                              sessionGestionarUsuarios.getNidSede(),
                                              sessionGestionarUsuarios.getNidNivel());
        String msj="";
        switch(sessionGestionarUsuarios.getTipoEvento()){
        case 1 : msj =  "Se registro al usuario "; break;
        case 2 : msj =  "Se modifico al usuario "; break;
        case 3 : msj =  "Se anulo al usuario "; break;
        case 4 : msj =  "Se activo al usuario "; break;
        } 
        Utils.mostrarMensaje(ctx, 
                             msj+sessionGestionarUsuarios.getUsuario(), 
                             "Operacion Correcta", 
                             3);
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN("1"));
        b2.setDisabled(true);
        b3.setDisabled(true);
        Utils.addTargetMany(b2, b3, t1);
        popGestionUsuario.hide();
    }
    
    public void tipoRolChangeListener(ValueChangeEvent valueChangeEvent) {
        try{
            String index = valueChangeEvent.getNewValue().toString();
            int nidrol = Integer.parseInt(index);
            if (ln_C_SFRolRemote.validaRolbyDescripcion(nidrol, "Evaluador")){
                sessionGestionarUsuarios.setRenderAreaAcdemica(true);          
                sessionGestionarUsuarios.setNidSede(0);
                sessionGestionarUsuarios.setRenderSede(false);
            }else if(ln_C_SFRolRemote.validaRolbyDescripcion(nidrol, "SubDirector")){
                sessionGestionarUsuarios.setRenderSede(true);   
                sessionGestionarUsuarios.setNidAreaAcademica(0);
                sessionGestionarUsuarios.setRenderAreaAcdemica(false);
            }else{
                sessionGestionarUsuarios.setNidAreaAcademica(0);
                sessionGestionarUsuarios.setRenderAreaAcdemica(false);
                sessionGestionarUsuarios.setNidSede(0);
                sessionGestionarUsuarios.setRenderSede(false);
            }
            Utils.addTargetMany(pfl1);
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    public void tipoRolFChangeListener(ValueChangeEvent valueChangeEvent) {
        try{            
            if(valueChangeEvent.getNewValue() != null){
                String index = valueChangeEvent.getNewValue().toString();                
                int nidrol = Integer.parseInt(index);
                if (ln_C_SFRolRemote.validaRolbyDescripcion(nidrol, "SubDirector")){
                    sessionGestionarUsuarios.setFbooleanSede(true);                                          
                }
                else{
                    sessionGestionarUsuarios.setFbooleanSede(false);
                    sessionGestionarUsuarios.setFbooleanNivel(false);
                    sessionGestionarUsuarios.setFNidNivel(0);
                    sessionGestionarUsuarios.setFNidSede(0);                        
                }
                Utils.addTarget(pfl3);
            }            
        }catch(Exception e){
            e.printStackTrace();
        }     
    }
    

    public void tipoSedeChangeListener(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            String index = valueChangeEvent.getNewValue().toString();                
            int nidSede = Integer.parseInt(index);
            sessionGestionarUsuarios.setLstNivel(llenarComboNivel(nidSede));
            if(sessionGestionarUsuarios.getLstNivel() != null){
                if(sessionGestionarUsuarios.getLstNivel().size() > 0){
                    sessionGestionarUsuarios.setRenderNivel(true);
                }
                else{
                    sessionGestionarUsuarios.setNidNivel(0);
                    sessionGestionarUsuarios.setRenderNivel(false);
                }                
            }
            Utils.addTarget(pfl1);
        }
    }
    
    public void tipoSedeFChangeListener(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null){
            String index = valueChangeEvent.getNewValue().toString();                
            int nidSede = Integer.parseInt(index);
            sessionGestionarUsuarios.setLstNivel(llenarComboNivel(nidSede));
            if(sessionGestionarUsuarios.getLstNivel() != null){
                if(sessionGestionarUsuarios.getLstNivel().size() > 0){
                    sessionGestionarUsuarios.setFbooleanNivel(true);
                }
                else{
                    sessionGestionarUsuarios.setFNidNivel(0);
                    sessionGestionarUsuarios.setFbooleanNivel(false);
                }                
            }
            Utils.addTarget(pfl3);
        }
    }

    public void buscarUsuarioFiltro(ActionEvent actionEvent) {
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuariobyByAttrLN(sessionGestionarUsuarios.getFNombres(), 
                                                                                         sessionGestionarUsuarios.getFUsuario(),
                                                                                         sessionGestionarUsuarios.getFDni(),
                                                                                         sessionGestionarUsuarios.getFNidAreaAcademica(),
                                                                                         sessionGestionarUsuarios.getFNidRol(),
                                                                                         sessionGestionarUsuarios.getFNidEstado(),
                                                                                         sessionGestionarUsuarios.getFNidSede(),
                                                                                         sessionGestionarUsuarios.getFNidNivel()));        
        Utils.unselectFilas(t1);
        b2.setDisabled(true);
        b3.setDisabled(true);
        Utils.addTargetMany(b2, b3, t1);
    }
    
    public void refrescarTabla(ActionEvent actionEvent) {
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN("1"));
        Utils.unselectFilas(t1);
        b2.setDisabled(true);
        b3.setDisabled(true);
        Utils.addTargetMany(b2, b3);
    }
    
    public void refrescarFiltro(ActionEvent actionEvent) {
        sessionGestionarUsuarios.setFNombres("");
        sessionGestionarUsuarios.setFUsuario("");
        sessionGestionarUsuarios.setFDni("");
        sessionGestionarUsuarios.setFNidRol(0);
        sessionGestionarUsuarios.setFNidAreaAcademica(0);
        sessionGestionarUsuarios.setFNidEstado(0);
        sessionGestionarUsuarios.setFbooleanSede(false);
        sessionGestionarUsuarios.setFbooleanNivel(false);
        sessionGestionarUsuarios.setFNidNivel(0);
        sessionGestionarUsuarios.setFNidSede(0); 
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN("1"));
        Utils.addTargetMany(pgl2,t1);
    }
    
    public void resetValues(){        
        sessionGestionarUsuarios.setNombres("");
        sessionGestionarUsuarios.setDni("");
        sessionGestionarUsuarios.setUsuario("");        
        sessionGestionarUsuarios.setClave("");
        sessionGestionarUsuarios.setRutaImg("");
        sessionGestionarUsuarios.setNidRol(0);        
        sessionGestionarUsuarios.setNidAreaAcademica(0);
        sessionGestionarUsuarios.setNidSede(0);
        sessionGestionarUsuarios.setNidNivel(0);
        sessionGestionarUsuarios.setRenderAreaAcdemica(false);
        sessionGestionarUsuarios.setRenderNivel(false);
        sessionGestionarUsuarios.setRenderSede(false);
        sessionGestionarUsuarios.setRenderImg(false);
        if(itNombres!=null){
            itNombres.resetValue();
            itDni.resetValue();
            choiceTipoArea.resetValue();
            choiceTipoRol.resetValue();
            itUsuario.resetValue();
            itClave.resetValue();
            fileImg.resetValue();
        }
    }    

    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();            
            long fileSize = file.getLength() / (1024 * 1024);//megabytes
            if(file.getLength() > 1048576){
                Utils.mostrarMensaje(ctx, "El archivo no puede ser de mas de 1 MB.", "Error", 4);
                return;
            }
            String extension = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
            if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")){
                String ext = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
                String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
                String rutaLocal = "";
                if(File.separator.equals("/")){
                    rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + "usuarios" + File.separator +  timePath + "." + ext;     
                }else{
                    rutaLocal = "recursos" + File.separator + "img" + File.separator + "usuarios" + File.separator + timePath + "." + ext;   
                }
                ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
                String imageDirPath = servletCtx.getRealPath("/");
                InputStream inputStream = file.getInputStream();
                String rutaImg = imageDirPath + rutaLocal;
                sessionGestionarUsuarios.setRenderImg(true);
                sessionGestionarUsuarios.setRutaImg(rutaImg);
                TransferFile(rutaImg, rutaLocal, inputStream);
            }else{
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo imagen suba un jpg/png", "Error", 1);
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void TransferFile(String ruta, String rutalocal, InputStream in) throws Exception {
        OutputStream out = new FileOutputStream(new File(ruta));
        resize(in, out, 175, 150);  
        i1.setSource(rutalocal);
        fileImg.resetValue();
        Utils.addTarget(pfl1);           
    }
    
    public void resize(InputStream input, OutputStream output, int width, int height) throws Exception {
            BufferedImage src = ImageIO.read(input);
            BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = dest.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((double) width / src.getWidth(), (double) height / src.getHeight());
            g.drawRenderedImage(src, at);
            ImageIO.write(dest, "JPG", output);
            output.close();
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setSessionGestionarUsuarios(bSessionGestionarUsuarios sessionGestionarUsuarios) {
        this.sessionGestionarUsuarios = sessionGestionarUsuarios;
    }

    public bSessionGestionarUsuarios getSessionGestionarUsuarios() {
        return sessionGestionarUsuarios;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setPopGestionUsuario(RichPopup popGestionUsuario) {
        this.popGestionUsuario = popGestionUsuario;
    }

    public RichPopup getPopGestionUsuario() {
        return popGestionUsuario;
    }

    public void setB2(RichButton b2) {
        this.b2 = b2;
    }

    public RichButton getB2() {
        return b2;
    }

    public void setB3(RichButton b3) {
        this.b3 = b3;
    }

    public RichButton getB3() {
        return b3;
    }

    public void setB4(RichButton b4) {
        this.b4 = b4;
    }

    public RichButton getB4() {
        return b4;
    }

    public void setChoiceTipoRol(RichSelectOneChoice choiceTipoRol) {
        this.choiceTipoRol = choiceTipoRol;
    }

    public RichSelectOneChoice getChoiceTipoRol() {
        return choiceTipoRol;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChoiceTipoArea(RichSelectOneChoice choiceTipoArea) {
        this.choiceTipoArea = choiceTipoArea;
    }

    public RichSelectOneChoice getChoiceTipoArea() {
        return choiceTipoArea;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setPopConfirmar(RichPopup popConfirmar) {
        this.popConfirmar = popConfirmar;
    }

    public RichPopup getPopConfirmar() {
        return popConfirmar;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setItNombres(RichInputText itNombres) {
        this.itNombres = itNombres;
    }

    public RichInputText getItNombres() {
        return itNombres;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setChoiceFTipoEstado(RichSelectOneChoice choiceFTipoEstado) {
        this.choiceFTipoEstado = choiceFTipoEstado;
    }

    public RichSelectOneChoice getChoiceFTipoEstado() {
        return choiceFTipoEstado;
    }

    public void setChoiceFTipoRol(RichSelectOneChoice choiceFTipoRol) {
        this.choiceFTipoRol = choiceFTipoRol;
    }

    public RichSelectOneChoice getChoiceFTipoRol() {
        return choiceFTipoRol;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setChoiceFTipoArea(RichSelectOneChoice choiceFTipoArea) {
        this.choiceFTipoArea = choiceFTipoArea;
    }

    public RichSelectOneChoice getChoiceFTipoArea() {
        return choiceFTipoArea;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }   

    public void setItDni(RichInputText itDni) {
        this.itDni = itDni;
    }

    public RichInputText getItDni() {
        return itDni;
    }

    public void setItUsuario(RichInputText itUsuario) {
        this.itUsuario = itUsuario;
    }

    public RichInputText getItUsuario() {
        return itUsuario;
    }

    public void setItClave(RichInputText itClave) {
        this.itClave = itClave;
    }

    public RichInputText getItClave() {
        return itClave;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setChoiceFTipoSede(RichSelectOneChoice choiceFTipoSede) {
        this.choiceFTipoSede = choiceFTipoSede;
    }

    public RichSelectOneChoice getChoiceFTipoSede() {
        return choiceFTipoSede;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setChoiceFTipoNivel(RichSelectOneChoice choiceFTipoNivel) {
        this.choiceFTipoNivel = choiceFTipoNivel;
    }

    public RichSelectOneChoice getChoiceFTipoNivel() {
        return choiceFTipoNivel;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setFileImg(RichInputFile fileImg) {
        this.fileImg = fileImg;
    }

    public RichInputFile getFileImg() {
        return fileImg;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setChoiceTipoSede(RichSelectOneChoice choiceTipoSede) {
        this.choiceTipoSede = choiceTipoSede;
    }

    public RichSelectOneChoice getChoiceTipoSede() {
        return choiceTipoSede;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setChoiceTipoNivel(RichSelectOneChoice choiceTipoNivel) {
        this.choiceTipoNivel = choiceTipoNivel;
    }

    public RichSelectOneChoice getChoiceTipoNivel() {
        return choiceTipoNivel;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }
}
