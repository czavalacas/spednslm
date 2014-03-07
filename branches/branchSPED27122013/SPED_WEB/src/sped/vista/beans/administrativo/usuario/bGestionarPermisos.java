package sped.vista.beans.administrativo.usuario;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.LNSF.IR.LN_C_SFPermisosRemote;
import sped.negocio.entidades.beans.BeanPermiso;

import sped.vista.Utils.Utils;

public class bGestionarPermisos {
    private bSessionGestionarPermisos sessionGestionarPermisos;
    @EJB
    private LN_C_SFPermisosRemote ln_C_SFPermisosRemote;
    private RichTreeTable treePer;
    private ChildPropertyTreeModel permisosTree;

    public bGestionarPermisos() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionGestionarPermisos.getExec() == 0){
            sessionGestionarPermisos.setPermisos(ln_C_SFPermisosRemote.getCrearArbolNuevoGP(1, 4));
            permisosTree = new ChildPropertyTreeModel(sessionGestionarPermisos.getPermisos(),"listaHijos");
            sessionGestionarPermisos.setPermisosTree(permisosTree);
            sessionGestionarPermisos.setPermisosTree_aux(permisosTree);
            sessionGestionarPermisos.setExec(1);
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
}
