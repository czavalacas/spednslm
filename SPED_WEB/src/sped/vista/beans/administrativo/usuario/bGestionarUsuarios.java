package sped.vista.beans.administrativo.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFRolRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanRol;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bGestionarUsuarios {
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_USUARIO = "mapLN_C_SFUsuario#sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote";
    @EJB
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    private RichButton b1;
    private bSessionGestionarUsuarios sessionGestionarUsuarios;
    private List<BeanUsuario> lstUsuario;
    private List lstRol;
    private List lstAreaAcademica;
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
    private RichPanelFormLayout pf2;

    public bGestionarUsuarios() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_USUARIO);
            this.setLstUsuario(ln_C_SFUsuarioRemote.getUsuarioByEstadoLN("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (sessionGestionarUsuarios.getExec() == 0) {
            sessionGestionarUsuarios.setExec(1);
            this.setLstRol(this.llenarComboRol());
            this.setLstAreaAcademica(this.llenarComboAreaA());
        } else {
            sessionGestionarUsuarios.setExec(1);
            this.setLstRol(this.llenarComboRol());
            this.setLstAreaAcademica(this.llenarComboAreaA());
        }
    }

    private ArrayList llenarComboRol() {
        ArrayList unItems = new ArrayList();
        List<BeanRol> beanrol = ln_C_SFRolRemote.getRolLN();
        for (BeanRol r : beanrol) {
            unItems.add(new SelectItem(r.getNidRol(), r.getDescripcionRol().toString()));
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

    public void selecionarUsuario(SelectionEvent se) {
        b2.setDisabled(false);
        b3.setDisabled(false);
        RichTable t = (RichTable) se.getSource();
        BeanUsuario usuario = (BeanUsuario) t.getSelectedRowData();
        sessionGestionarUsuarios.setNidUsuario(usuario.getNidUsuario());
        sessionGestionarUsuarios.setNombre(usuario.getNombre());      
        sessionGestionarUsuarios.setApellido(usuario.getApellidos());
        sessionGestionarUsuarios.setDni(usuario.getDni());
        sessionGestionarUsuarios.setUsuario(usuario.getUsuario());
        sessionGestionarUsuarios.setClave(usuario.getClave());
        sessionGestionarUsuarios.setNidRol(usuario.getRol().getNidRol());
        if(usuario.getAreaAcademica() != null){
            sessionGestionarUsuarios.setNidAreaAcademica(usuario.getAreaAcademica().getNidAreaAcademica());
            sessionGestionarUsuarios.setRenderAreaAcdemica(true);
        }else{
            sessionGestionarUsuarios.setNidAreaAcademica(0);
            sessionGestionarUsuarios.setRenderAreaAcdemica(false);
        }
        Utils.addTargetMany(b2, b3);
    }

    public void nuevoUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        sessionGestionarUsuarios.setTipoEvento(1);
        sessionGestionarUsuarios.setTitleDialogGestion("Registrar Usuario");
        sessionGestionarUsuarios.setNomBtnGestion("Registrar");
        sessionGestionarUsuarios.setNombre("");
        sessionGestionarUsuarios.setApellido("");
        sessionGestionarUsuarios.setDni("");
        sessionGestionarUsuarios.setNidRol(0);
        sessionGestionarUsuarios.setNidAreaAcademica(0);
        sessionGestionarUsuarios.setRenderAreaAcdemica(false);
        sessionGestionarUsuarios.setUsuario("");
        sessionGestionarUsuarios.setClave("");
        Utils.showPopUpMIDDLE(popGestionUsuario);
        Utils.addTargetMany(b2, b3);
    }

    public void modificarUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        sessionGestionarUsuarios.setTipoEvento(2);
        sessionGestionarUsuarios.setTitleDialogGestion("Modificar usuario : "+
                                                       sessionGestionarUsuarios.getNombre());
        sessionGestionarUsuarios.setNomBtnGestion("Actualizar");
        Utils.unselectFilas(t1);
        Utils.showPopUpMIDDLE(popGestionUsuario);
        Utils.addTargetMany(b2, b3);
    }

    public void anularUsuario(ActionEvent actionEvent) {
        b2.setDisabled(true);
        b3.setDisabled(true);
        sessionGestionarUsuarios.setTipoEvento(3);
        Utils.showPopUpMIDDLE(popConfirmar);
        Utils.addTargetMany(b2, b3);
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
                                              sessionGestionarUsuarios.getNombre(), 
                                              sessionGestionarUsuarios.getApellido(), 
                                              sessionGestionarUsuarios.getDni(), 
                                              sessionGestionarUsuarios.getNidRol(), 
                                              sessionGestionarUsuarios.getNidAreaAcademica(), 
                                              sessionGestionarUsuarios.getUsuario(), 
                                              sessionGestionarUsuarios.getClave(), 
                                              sessionGestionarUsuarios.getNidUsuario());
        String msj="";
        switch(sessionGestionarUsuarios.getTipoEvento()){
        case 1 : msj =  "Se registro al usuario "; break;
        case 2 : msj =  "Se modifico al usuario "; break;
        case 3 : msj =  "Se anulo al usuario "; break;
        } 
        Utils.mostrarMensaje(ctx, 
                             msj+sessionGestionarUsuarios.getUsuario(), 
                             "Operacion Correcta", 
                             3);
        b2.setDisabled(true);
        b3.setDisabled(true);
        Utils.addTargetMany(b2, b3, t1);
        popGestionUsuario.hide();
    }
    
    public void tipoRolChangeListener(ValueChangeEvent valueChangeEvent) {
        try{
            String index = valueChangeEvent.getNewValue().toString();
            System.out.println("error2"+index);
            int nidrol = Integer.parseInt(index);
            if (ln_C_SFRolRemote.validaRolbyDescripcion(nidrol, "Evaluador")){
                sessionGestionarUsuarios.setRenderAreaAcdemica(true);
            }else{
                sessionGestionarUsuarios.setNidAreaAcademica(0);
                sessionGestionarUsuarios.setRenderAreaAcdemica(false);
            }
            Utils.addTargetMany(pf2);
        }catch(Exception e){
            e.printStackTrace();
        }        
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

    public void setLstUsuario(List<BeanUsuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<BeanUsuario> getLstUsuario() {
        return lstUsuario;
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

    public void setLstRol(List lstRol) {
        this.lstRol = lstRol;
    }

    public List getLstRol() {
        return lstRol;
    }

    public void setLstAreaAcademica(List lstAreaAcademica) {
        this.lstAreaAcademica = lstAreaAcademica;
    }

    public List getLstAreaAcademica() {
        return lstAreaAcademica;
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

    public void setPf2(RichPanelFormLayout pf2) {
        this.pf2 = pf2;
    }

    public RichPanelFormLayout getPf2() {
        return pf2;
    }
}
