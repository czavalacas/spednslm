package sped.vista.beans.administrativo.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.LNSF.IR.LN_C_SFPermisosRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioPermisoRemote;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

import utils.system;

public class bGestionarPermisos {
    private bSessionGestionarPermisos sessionGestionarPermisos;
    @EJB
    private LN_C_SFPermisosRemote ln_C_SFPermisosRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_T_SFUsuarioPermisoRemote ln_T_SFUsuarioPermisoRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private RichTreeTable treePer;
    private ChildPropertyTreeModel permisosTree;
    private RichPopup popUsu;
    private RichPanelGroupLayout pgl1;
    private RichPopup popConf;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichSelectOneChoice choiceRol;
    private UISelectItems si1;
    private RichPanelFormLayout pfl2;
    private RichTable tusu;
    private RichDialog dialogU;

    public bGestionarPermisos() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarPermisos.getExec() == 0){
            sessionGestionarPermisos.setListRol(Utils.llenarCombo(ln_C_SFUtilsRemote.getRol_LN()));
            sessionGestionarPermisos.setItemNombre(Utils.llenarListItem(ln_C_SFUsuarioRemote.getNombresUsuarios_LN(0)));
            sessionGestionarPermisos.setItemUsuario(Utils.llenarListItem(ln_C_SFUsuarioRemote.getUsuarioUsuarios_LN(0)));
            buscarUsuarios_aux();
            sessionGestionarPermisos.setExec(1);
        }
    }
    
    public void buscarUsuarios_aux(){
        sessionGestionarPermisos.setLstUsuarios(ln_C_SFUsuarioRemote.getListUsuarioNoAdminLN(sessionGestionarPermisos.getNombreF(),
                                                                                             sessionGestionarPermisos.getUsuarioF(),
                                                                                             sessionGestionarPermisos.getNidRolF()));
    }
    
    public void permisoChecked(ValueChangeEvent vce) {
        try{
            Object val = vce.getNewValue();
            RichSelectBooleanCheckbox ckBox = (RichSelectBooleanCheckbox)vce.getComponent();
            sessionGestionarPermisos.encuentraCheck_aux(ckBox.getShortDesc().toString(), 
                                                        Boolean.parseBoolean(val.toString()));
            sessionGestionarPermisos.checkPadres(sessionGestionarPermisos.getPermisos());
            sessionGestionarPermisos.setValidaPermiso(0);
            Utils.addTarget(treePer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String mostrarUsuarios() {
        Utils.showPopUpMIDDLE(popUsu);
        return null;
    }
    
    public void confirmarSelecion(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            BeanUsuario usu = sessionGestionarPermisos.getBeanUsuario_aux();
            sessionGestionarPermisos.setBeanUsuario(usu);
            mostrarPermisos(usu);
        }
    }
    
    public void mostrarPermisos(BeanUsuario usu){
        sessionGestionarPermisos.setPermisos(ln_C_SFPermisosRemote.getCrearArbolNuevoGP(usu.getRol().getNidRol(),
                                                                                        usu.getNidUsuario()));
        validaSupervisorArea(usu, sessionGestionarPermisos.getPermisos());
        permisosTree = new ChildPropertyTreeModel(sessionGestionarPermisos.getPermisos(),"listaHijos");
        sessionGestionarPermisos.setPermisosTree(permisosTree);
        Utils.addTarget(pgl1);
    }
    
    public void seleccionarUsuario(SelectionEvent se) {
        RichTable t = (RichTable) se.getSource();
        sessionGestionarPermisos.setBeanUsuario_aux((BeanUsuario) t.getSelectedRowData());
        if(sessionGestionarPermisos.getTypepopUsu().compareTo("none") == 0){
            sessionGestionarPermisos.setTypepopUsu("okCancel");
            Utils.addTarget(dialogU);
        }                
    }
    
    public String resetPermisos() {
        mostrarPermisos(sessionGestionarPermisos.getBeanUsuario());
        Utils.addTarget(pgl1);        
        return null;
    }
    
    public String openPopConfrimarPermisos() {        
        Utils.showPopUpMIDDLE(popConf);
        return null;
    }
    
    public void dialogPermisosListener(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            ln_T_SFUsuarioPermisoRemote.gestionPermisoLN(sessionGestionarPermisos.getPermisos(),
                                                         sessionGestionarPermisos.getBeanUsuario().getNidUsuario());
            Utils.addTarget(pgl1);
            Utils.mostrarMensaje(ctx, 
                                 "Se modifico correctamente los permisos de "+
                                 sessionGestionarPermisos.getBeanUsuario().getNombres(), 
                                 "Operacion Correcta", 
                                 3);
        }
    }
    
    /**
     * Metodo que valida si el usuario pertenece es evaluador area y si no es supervisor le quita los permisos respecttivos
     * @param usuario
     * @param permisos
     */
    public void validaSupervisorArea(BeanUsuario usuario, BeanPermiso permisos){ 
        if(usuario.getRol().getNidRol() == 2 && usuario.getIsSupervisor().compareTo("0") == 0){
            validaSupervisorArea_aux(permisos);
        }
        
    }

    public void validaSupervisorArea_aux(BeanPermiso permisos){
        if(permisos.getListaHijos() != null){
            List<BeanPermiso> aux = new ArrayList();
            for(BeanPermiso p : permisos.getListaHijos()){
                if(encuentraPermisos(p)){
                    aux.add(p);                                 
                }else{
                    validaSupervisorArea_aux(p);
                }                
            }
            for(BeanPermiso p : aux){
                permisos.getListaHijos().remove(p);
            }
        }
    }
    
    public boolean encuentraPermisos(BeanPermiso permiso){
        int id = permiso.getNidPermiso();
        if(id == 1 || id == 4 || id == 12){         
            return true;
        }           
        return false;
    }
    
    public List<SelectItem> suggestNombre(String string) {        
        return Utils.getSuggestions(sessionGestionarPermisos.getItemNombre(), string);
    }

    public List<SelectItem> suggestUsuario(String string) {
        return Utils.getSuggestions(sessionGestionarPermisos.getItemUsuario(), string);
    }
    
    public void actionListenerBuscarUsuario(ActionEvent actionEvent) {
        buscarUsuarios_aux();
        Utils.addTarget(tusu);
    }

    public String limpiarFiltro() {
        sessionGestionarPermisos.setNombreF(null);
        sessionGestionarPermisos.setUsuarioF(null);
        sessionGestionarPermisos.setNidRolF(null);
        buscarUsuarios_aux();
        Utils.unselectFilas(tusu);
        sessionGestionarPermisos.setTypepopUsu("none");
        Utils.addTarget(popUsu);
        return null;
    }
    
    public void setSessionGestionarPermisos(bSessionGestionarPermisos sessionGestionarPermisos) {
        this.sessionGestionarPermisos = sessionGestionarPermisos;
    }

    public bSessionGestionarPermisos getSessionGestionarPermisos() {
        return sessionGestionarPermisos;
    }

    public void setTreePer(RichTreeTable treePer) {
        this.treePer = treePer;
    }

    public RichTreeTable getTreePer() {
        return treePer;
    }

    public void setLn_C_SFPermisosRemote(LN_C_SFPermisosRemote ln_C_SFPermisosRemote) {
        this.ln_C_SFPermisosRemote = ln_C_SFPermisosRemote;
    }

    public LN_C_SFPermisosRemote getLn_C_SFPermisosRemote() {
        return ln_C_SFPermisosRemote;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setPopUsu(RichPopup popUsu) {
        this.popUsu = popUsu;
    }

    public RichPopup getPopUsu() {
        return popUsu;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPopConf(RichPopup popConf) {
        this.popConf = popConf;
    }

    public RichPopup getPopConf() {
        return popConf;
    }

    public void setChoiceRol(RichSelectOneChoice choiceRol) {
        this.choiceRol = choiceRol;
    }

    public RichSelectOneChoice getChoiceRol() {
        return choiceRol;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setTusu(RichTable tusu) {
        this.tusu = tusu;
    }

    public RichTable getTusu() {
        return tusu;
    }

    public void setDialogU(RichDialog dialogU) {
        this.dialogU = dialogU;
    }

    public RichDialog getDialogU() {
        return dialogU;
    }
}
