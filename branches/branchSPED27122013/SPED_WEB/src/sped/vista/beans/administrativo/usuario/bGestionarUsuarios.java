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
import sped.negocio.LNSF.IR.LN_C_SFSedeRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.beans.BeanSede;
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
    private RichPanelGridLayout pgl2;    
    private RichPanelFormLayout pfl3;
    private RichSelectOneChoice choiceFTipoSede;
    private UISelectItems si6;
    private UISelectItems si7;
    private RichInputFile fileImg;
    private RichImage i1;    
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private RichSelectOneChoice choiceTipoSede;
    private UISelectItems si8;
    private UISelectItems si9;
    private RichInputText itCor;

    public bGestionarUsuarios() {
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (sessionGestionarUsuarios.getExec() == 0) {
            sessionGestionarUsuarios.setExec(1);            
            sessionGestionarUsuarios.setLstRol(this.llenarComboRol(1));
            sessionGestionarUsuarios.setLstRolf(this.llenarComboRol(2));
            sessionGestionarUsuarios.setLstAreaAcademica(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
            sessionGestionarUsuarios.setLstEstadoUsario(this.llenarComboEstado());
            sessionGestionarUsuarios.setLstSede(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));                    
            validaUsuario();            
            buscarUsuarioFiltro_aux();
        } 
    }

    /**
     * Metodo que valida al usuario si es supervisor y llena la los suggestedItems
     */
    public void validaUsuario(){
        if(beanUsuario.getRol().getNidRol() == 2 && "1".compareTo(beanUsuario.getIsSupervisor()) == 0){
            sessionGestionarUsuarios.setFNidAreaAcademica(beanUsuario.getAreaAcademica().getNidAreaAcademica()+"");
            sessionGestionarUsuarios.setFNidRol(2);
            sessionGestionarUsuarios.setDisableFArea(true);
            sessionGestionarUsuarios.setDisableFRol(true);            
        }
        llenarSuggest();
    }
    
    /**
     * Metodo que llena la lista para los suggest, esta separado para volver a llenarlas cuando se resgitre un nuevo usuario
     */
    public void llenarSuggest(){
        int nidArea = 0;
        int nidRol = 0;
        if(beanUsuario.getRol().getNidRol() == 2 && "1".compareTo(beanUsuario.getIsSupervisor()) == 0){
            nidArea = beanUsuario.getAreaAcademica().getNidAreaAcademica();
            nidRol = beanUsuario.getRol().getNidRol();
        }
        sessionGestionarUsuarios.setItemNombre(Utils.llenarListItem(ln_C_SFUsuarioRemote.getNombresUsuarios_LN(nidArea, nidRol)));
        sessionGestionarUsuarios.setItemUsuario(Utils.llenarListItem(ln_C_SFUsuarioRemote.getUsuarioUsuarios_LN(nidArea, nidRol)));
        sessionGestionarUsuarios.setItemDni(Utils.llenarListItem(ln_C_SFUsuarioRemote.getDniUsuarios_LN(nidArea, nidRol)));    
    }
    
    /**
     * Metodo que llena comboRol si encuentra al profesor lo pone en disable
     * @param tipo
     * @return
     */
    private ArrayList llenarComboRol(int tipo) {
        ArrayList unItems = new ArrayList();
        List<BeanRol> beanrol = ln_C_SFRolRemote.getRolLN();
        for (BeanRol r : beanrol) {
            SelectItem se = new SelectItem(r.getNidRol(), r.getDescripcionRol().toString());
            if(tipo == 2 && r.getNidRol() == 3){//1= ComboFiltro 2=Combo gestion usuario
                se.setDisabled(true);
            }
            if(beanUsuario.getRol().getNidRol() == 2 && "1".compareTo(beanUsuario.getIsSupervisor()) == 0){
                if(r.getNidRol() == 2){
                    unItems.add(se);
                }
            }else{
                unItems.add(se);    
            }            
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

    public void selecionarUsuario(SelectionEvent se) {        
        b3.setDisabled(false);
        resetValues();
        RichTable t = (RichTable) se.getSource();
        BeanUsuario usuario = (BeanUsuario) t.getSelectedRowData();
        sessionGestionarUsuarios.setNidUsuario(usuario.getNidUsuario());
        sessionGestionarUsuarios.setNombres(usuario.getNombres());
        sessionGestionarUsuarios.setCorreo(usuario.getCorreo());
        Utils.putSession("Correo", usuario.getCorreo());
        sessionGestionarUsuarios.setDni(usuario.getDni());        
        sessionGestionarUsuarios.setUsuario(usuario.getUsuario());
        sessionGestionarUsuarios.setNidRol(usuario.getRol().getNidRol());
        if(usuario.getFoto() != null){
            sessionGestionarUsuarios.setRenderImg(true);
        }
        if(usuario.getAreaAcademica() != null){
            sessionGestionarUsuarios.setNidAreaAcademica(usuario.getAreaAcademica().getNidAreaAcademica().toString());
            sessionGestionarUsuarios.setSupervisorboolean("1".compareTo(usuario.getIsSupervisor()) == 0 ? true : false);
            sessionGestionarUsuarios.setRenderAreaAcdemica(true);
        }
        if(usuario.getSede() != null){
            sessionGestionarUsuarios.setNidSede(usuario.getSede().getNidSede().toString());
            sessionGestionarUsuarios.setRenderSede(true);
        }
        if(usuario.getRol().getNidRol() == 3){
            sessionGestionarUsuarios.setDisableRol(true);
        }
        if(i1!=null){
            i1.setSource("/imageservlet?nomusuario="+usuario.getNidUsuario());
        }        
        if("1".compareTo(usuario.getEstadoUsuario()) == 0){
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
        Utils.removeSession("Correo");
        sessionGestionarUsuarios.setDisabledActualizar(false);
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
        sessionGestionarUsuarios.setDisabledActualizar(true);
        sessionGestionarUsuarios.setTitleDialogGestion("Modificar usuario : "+sessionGestionarUsuarios.getNombres());
        sessionGestionarUsuarios.setNomBtnGestion("Actualizar");
        Utils.unselectFilas(t1);        
        if(i1 != null){
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
        int nidSede = sessionGestionarUsuarios.getNidSede() == null ? 0 : Integer.parseInt(sessionGestionarUsuarios.getNidSede());
        int nidArea = sessionGestionarUsuarios.getNidAreaAcademica() == null ? 0 : Integer.parseInt(sessionGestionarUsuarios.getNidAreaAcademica());
        ln_T_SFUsuarioRemote.gestionUsuarioLN(sessionGestionarUsuarios.getTipoEvento(), 
                                              sessionGestionarUsuarios.getNombres(),
                                              sessionGestionarUsuarios.getDni(),
                                              sessionGestionarUsuarios.getCorreo(),
                                              sessionGestionarUsuarios.getNidRol(), 
                                              nidArea, 
                                              sessionGestionarUsuarios.getUsuario(), 
                                              sessionGestionarUsuarios.getNidUsuario(),
                                              Utils.rutaImagenes(),
                                              sessionGestionarUsuarios.getRutaImg(),
                                              nidSede,
                                              sessionGestionarUsuarios.isSupervisorboolean());
        String msj="";
        switch(sessionGestionarUsuarios.getTipoEvento()){
            case 1 : msj =  "Se registro al usuario "; llenarSuggest(); break;
            case 2 : msj =  "Se modifico al usuario "; llenarSuggest(); break;
            case 3 : msj =  "Se anulo al usuario "; break;
            case 4 : msj =  "Se activo al usuario "; break;
        }         
        Utils.mostrarMensaje(ctx, 
                             msj+sessionGestionarUsuarios.getUsuario(), 
                             "Operacion Correcta", 
                             3);
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN());
        b2.setDisabled(true);
        b3.setDisabled(true);
        Utils.addTargetMany(b2, b3, t1);
        popGestionUsuario.hide();
    }
    
    public void tipoRolChangeListener(ValueChangeEvent valueChangeEvent) {
        try{
            String index = valueChangeEvent.getNewValue().toString();
            int nidrol = Integer.parseInt(index);
            sessionGestionarUsuarios.setRenderAreaAcdemica(false);
            sessionGestionarUsuarios.setRenderSede(false);
            sessionGestionarUsuarios.setNidAreaAcademica(null);
            sessionGestionarUsuarios.setNidSede(null);
            sessionGestionarUsuarios.setNidNivel(0);
            if (nidrol == 2){
                sessionGestionarUsuarios.setRenderAreaAcdemica(true); 
            }else if(nidrol == 4){
                sessionGestionarUsuarios.setRenderSede(true);   
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
                if (nidrol == 4){
                    sessionGestionarUsuarios.setFbooleanSede(true);                                          
                }
                else{
                    sessionGestionarUsuarios.setFbooleanSede(false);
                    sessionGestionarUsuarios.setFNidNivel(0);
                    sessionGestionarUsuarios.setFNidSede(null);                        
                }
                Utils.addTarget(pfl3);
            }            
        }catch(Exception e){
            e.printStackTrace();
        }     
    }

    public void buscarUsuarioFiltro(ActionEvent actionEvent) {
        buscarUsuarioFiltro_aux();
    }
    
    public void buscarUsuarioFiltro_aux(){
        int nidArea = sessionGestionarUsuarios.getFNidAreaAcademica() == null ? 0 : Integer.parseInt(sessionGestionarUsuarios.getFNidAreaAcademica());
        int nidSede = sessionGestionarUsuarios.getFNidSede() == null ? 0 : Integer.parseInt(sessionGestionarUsuarios.getFNidSede());
        sessionGestionarUsuarios.setLstUsuario(ln_C_SFUsuarioRemote.getUsuariobyByAttrLN(sessionGestionarUsuarios.getFNombres(), 
                                                                                         sessionGestionarUsuarios.getFUsuario(),
                                                                                         sessionGestionarUsuarios.getFDni(),
                                                                                         nidArea,
                                                                                         sessionGestionarUsuarios.getFNidRol(),
                                                                                         sessionGestionarUsuarios.getFNidEstado(),
                                                                                         nidSede,
                                                                                         sessionGestionarUsuarios.getFNidNivel())); 
        if(t1 != null){
            Utils.unselectFilas(t1);
            b2.setDisabled(true);
            b3.setDisabled(true);
            Utils.addTargetMany(b2, b3, t1);
        }        
    }
    
    public void refrescarFiltro(ActionEvent actionEvent) {
        sessionGestionarUsuarios.setFNombres("");
        sessionGestionarUsuarios.setFUsuario("");
        sessionGestionarUsuarios.setFDni("");  
        sessionGestionarUsuarios.setFNidEstado(0);
        sessionGestionarUsuarios.setFbooleanSede(false);
        sessionGestionarUsuarios.setFNidNivel(0);
        sessionGestionarUsuarios.setFNidSede(null); 
        if(beanUsuario.getRol().getNidRol() != 2 && beanUsuario.getIsSupervisor().compareTo("1") != 0){
            sessionGestionarUsuarios.setFNidRol(0);
            sessionGestionarUsuarios.setFNidAreaAcademica(null);
        }
        buscarUsuarioFiltro_aux();
        Utils.addTargetMany(pgl2,t1);
    }
    
    public void resetValues(){        
        sessionGestionarUsuarios.setNombres("");
        sessionGestionarUsuarios.setDni("");
        sessionGestionarUsuarios.setUsuario("");     
        sessionGestionarUsuarios.setCorreo("");
        sessionGestionarUsuarios.setRutaImg("");
        sessionGestionarUsuarios.setNidRol(0);        
        sessionGestionarUsuarios.setNidAreaAcademica(null);
        sessionGestionarUsuarios.setNidSede(null);
        sessionGestionarUsuarios.setNidNivel(0);
        sessionGestionarUsuarios.setRenderAreaAcdemica(false);
        sessionGestionarUsuarios.setRenderSede(false);
        sessionGestionarUsuarios.setRenderImg(false);
        sessionGestionarUsuarios.setDisableRol(false);
        sessionGestionarUsuarios.setSupervisorboolean(false);
        if(itNombres!=null){
            itNombres.resetValue();
            itDni.resetValue();
            itCor.resetValue();
            choiceTipoArea.resetValue();
            choiceTipoRol.resetValue();
            itUsuario.resetValue();
            fileImg.resetValue();
        }
    }    

    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();            
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

    public List<SelectItem> suggestNombre(String string) {
        return Utils.getSuggestions(sessionGestionarUsuarios.getItemNombre(), string);
    }

    public List<SelectItem> suggestUsuario(String string) {
        return Utils.getSuggestions(sessionGestionarUsuarios.getItemUsuario(), string);
    }

    public List<SelectItem> suggestDni(String string) {
        return Utils.getSuggestions(sessionGestionarUsuarios.getItemDni(), string);
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

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setItCor(RichInputText itCor) {
        this.itCor = itCor;
    }

    public RichInputText getItCor() {
        return itCor;
    }
    
}
