package sped.negocio.entidades.beans;

import java.io.Serializable;
import java.sql.Time;

/** Clase BeanRestriccionHorario.java
 * @author dangeles
 * @since 27.08.2014
 */
public class BeanRestriccionHorario implements Serializable{
    @SuppressWarnings("compatibility:-1167126816830161641")
    private static final long serialVersionUID = 1L;

    private String estado;
    private Time hora_fin;
    private Time hora_ini;
    private int nDia;
    private String nid;
    private int nidReho;
    private String tipoRestr;

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_ini(Time hora_ini) {
        this.hora_ini = hora_ini;
    }

    public Time getHora_ini() {
        return hora_ini;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNid() {
        return nid;
    }

    public void setNidReho(int nidReho) {
        this.nidReho = nidReho;
    }

    public int getNidReho() {
        return nidReho;
    }

    public void setTipoRestr(String tipoRestr) {
        this.tipoRestr = tipoRestr;
    }

    public String getTipoRestr() {
        return tipoRestr;
    }

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }
}
