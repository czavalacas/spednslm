package sped.negocio.entidades.admin;

import java.io.Serializable;

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
@NamedQueries({ @NamedQuery(name = "Problema.findAll", query = "select o from Problema o") })
@Table(name = "\"admprob\"")
public class Problema implements Serializable {
    private static final long serialVersionUID = -3232307823450506728L;
    @Column(name = "desc_problema", nullable = false)
    private String desc_problema;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Id
    @Column(name = "nidProblema", nullable = false)
    @TableGenerator( name = "stmcodi_admprob", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "admprob.nidProblema", valueColumnName = "APP_SEQ_VALUE", initialValue = 7, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_admprob" )
    private int nidProblema;

    public Problema() {
    }

    public Problema(String desc_problema, String estado, int nidProblema) {
        this.desc_problema = desc_problema;
        this.estado = estado;
        this.nidProblema = nidProblema;
    }


    public String getDesc_problema() {
        return desc_problema;
    }

    public void setDesc_problema(String desc_problema) {
        this.desc_problema = desc_problema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("desc_problema=");
        buffer.append(getDesc_problema());
        buffer.append(',');
        buffer.append("estado=");
        buffer.append(getEstado());
        buffer.append(',');
        buffer.append("nidProblema=");
        buffer.append(getNidProblema());
        buffer.append(']');
        return buffer.toString();
    }
}
