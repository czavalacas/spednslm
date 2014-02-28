package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.entidades.beans.BeanPermiso;

public class bSessionGestionarPermisos implements Serializable {
    @SuppressWarnings("compatibility:6805958588450758732")
    private static final long serialVersionUID = 1L;
    
    private ChildPropertyTreeModel permisosTree;
    private int exec;

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }
}
