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
    private int cantNotif;
    private int cantNotifEvas;
    private int cantNotifPO;

    public void setCantNotifEvas(int cantNotifEvas) {
        this.cantNotifEvas = cantNotifEvas;
    }

    public int getCantNotifEvas() {
        return cantNotifEvas;
    }

    public void setCantNotifPO(int cantNotifPO) {
        this.cantNotifPO = cantNotifPO;
    }

    public int getCantNotifPO() {
        return cantNotifPO;
    }

    public void setCantNotif(int cantNotif) {
        this.cantNotif = cantNotif;
    }

    public int getCantNotif() {
        return cantNotif;
    }

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