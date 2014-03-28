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
    private boolean verNotificaciones;
    private boolean verNotificacionesEvas;
    private boolean verNotificacionesPOs;
    private int cantNotifAux;
    private String imagenNoti =  "../recursos/img/usuarios/ojosU.png";

    public void setImagenNoti(String imagenNoti) {
        this.imagenNoti = imagenNoti;
    }

    public String getImagenNoti() {
        return imagenNoti;
    }

    public void setCantNotifAux(int cantNotifAux) {
        this.cantNotifAux = cantNotifAux;
    }

    public int getCantNotifAux() {
        return cantNotifAux;
    }

    public void setVerNotificacionesEvas(boolean verNotificacionesEvas) {
        this.verNotificacionesEvas = verNotificacionesEvas;
    }

    public boolean isVerNotificacionesEvas() {
        return verNotificacionesEvas;
    }

    public void setVerNotificacionesPOs(boolean verNotificacionesPOs) {
        this.verNotificacionesPOs = verNotificacionesPOs;
    }

    public boolean isVerNotificacionesPOs() {
        return verNotificacionesPOs;
    }

    public void setVerNotificaciones(boolean verNotificaciones) {
        this.verNotificaciones = verNotificaciones;
    }

    public boolean isVerNotificaciones() {
        return verNotificaciones;
    }

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