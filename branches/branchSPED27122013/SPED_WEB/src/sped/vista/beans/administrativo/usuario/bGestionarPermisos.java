package sped.vista.beans.administrativo.usuario;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import sped.negocio.LNSF.IR.LN_C_SFPermisosRemote;
import sped.negocio.entidades.beans.BeanPermiso;

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
            List<BeanPermiso> bean = ln_C_SFPermisosRemote.getCrearArbolNuevo(1, 4);
            BeanPermiso aux = new BeanPermiso();
            aux.setDescripcionPermiso("SPED");
            aux.setListaHijos(bean);
            permisosTree = new ChildPropertyTreeModel(aux,"listaHijos");
            sessionGestionarPermisos.setPermisosTree(permisosTree);
            sessionGestionarPermisos.setExec(1);
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
}
