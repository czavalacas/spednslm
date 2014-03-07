package sped.vista.beans.administrativo.usuario;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.LNSF.IR.LN_C_SFPermisosRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
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
    private RichTreeTable treePer;
    private ChildPropertyTreeModel permisosTree;
    private RichPopup popUsu;
    private RichPanelGroupLayout pgl1;

    public bGestionarPermisos() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarPermisos.getExec() == 0){
            /* sessionGestionarPermisos.setPermisos(ln_C_SFPermisosRemote.getCrearArbolNuevoGP(1, 4));
            permisosTree = new ChildPropertyTreeModel(sessionGestionarPermisos.getPermisos(),"listaHijos");
            sessionGestionarPermisos.setPermisosTree(permisosTree);
            sessionGestionarPermisos.setPermisosTree_aux(permisosTree);
            sessionGestionarPermisos.setExec(1); */
        }
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
        if(sessionGestionarPermisos.getLstUsuarios() == null){
            sessionGestionarPermisos.setLstUsuarios(ln_C_SFUsuarioRemote.getListUsuarioNoAdminLN());
        }
        Utils.showPopUpMIDDLE(popUsu);
        return null;
    }
    
    public void confirmarSelecion(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){
            System.out.println("hola entre");
            BeanUsuario usu = sessionGestionarPermisos.getBeanUsuario_aux();
            sessionGestionarPermisos.setBeanUsuario(usu);
            sessionGestionarPermisos.setPermisos(ln_C_SFPermisosRemote.getCrearArbolNuevoGP(usu.getRol().getNidRol(),
                                                                                            usu.getNidUsuario()));
            permisosTree = new ChildPropertyTreeModel(sessionGestionarPermisos.getPermisos(),"listaHijos");
            sessionGestionarPermisos.setPermisosTree(permisosTree);
            sessionGestionarPermisos.setPermisosTree_aux(permisosTree);
            Utils.addTarget(pgl1);
        }
    }
    
    public void seleccionarUsuario(SelectionEvent se) {
        RichTable t = (RichTable) se.getSource();
        sessionGestionarPermisos.setBeanUsuario_aux((BeanUsuario) t.getSelectedRowData());
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
}
