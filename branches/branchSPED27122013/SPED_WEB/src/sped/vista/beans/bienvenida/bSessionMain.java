package sped.vista.beans.bienvenida;

import java.util.List;

import sped.negocio.entidades.beans.BeanPermiso;

public class bSessionMain {

    private int exec = 0;
    private List<BeanPermiso> lstPermisos;

    public void setLstPermisos(List<BeanPermiso> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<BeanPermiso> getLstPermisos() {
        return lstPermisos;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}