package sped.vista.beans.bienvenida;

import java.util.List;
import sped.negocio.entidades.beans.BeanPermiso;

/** Clase de Sesion del Bean bMain.java
 * @author dfloresgonz 
 * @since 27.12.2013
 */
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