package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "RestriccionHorario.findAll", query = "select o from RestriccionHorario o") })
@Table(name = "\"stmreho\"")
public class RestriccionHorario implements Serializable {
    private static final long serialVersionUID = -4933553491795380852L;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "hora_fin", nullable = false)
    private Time hora_fin;
    @Column(name = "hora_ini", nullable = false)
    private Time hora_ini;
    @Column(name = "nid", nullable = false)
    private String nid;
    @Id
    @Column(name = "nidReho", nullable = false)
    @TableGenerator( name = "stmcodi_stmreho", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "stmreho.nidReho", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_stmreho" )
    private int nidReho;
    @Column(name = "tipoRestr", nullable = false)        
    private String tipoRestr;
    @Column(name = "nDia", nullable = false)
    private int nDia;

    public RestriccionHorario() {
    }

    public RestriccionHorario(String estado, Time hora_fin, Time hora_ini, String nid, int nidReho, String tipoRestr) {
        this.estado = estado;
        this.hora_fin = hora_fin;
        this.hora_ini = hora_ini;
        this.nid = nid;
        this.nidReho = nidReho;
        this.tipoRestr = tipoRestr;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(Time hora_ini) {
        this.hora_ini = hora_ini;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public int getNidReho() {
        return nidReho;
    }

    public void setNidReho(int nidReho) {
        this.nidReho = nidReho;
    }

    public String getTipoRestr() {
        return tipoRestr;
    }

    public void setTipoRestr(String tipoRestr) {
        this.tipoRestr = tipoRestr;
    }

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("estado=");
        buffer.append(getEstado());
        buffer.append(',');
        buffer.append("hora_fin=");
        buffer.append(getHora_fin());
        buffer.append(',');
        buffer.append("hora_ini=");
        buffer.append(getHora_ini());
        buffer.append(',');
        buffer.append("nid=");
        buffer.append(getNid());
        buffer.append(',');
        buffer.append("nidReho=");
        buffer.append(getNidReho());
        buffer.append(',');
        buffer.append("tipoRestr=");
        buffer.append(getTipoRestr());
        buffer.append(']');
        return buffer.toString();
    }
}
