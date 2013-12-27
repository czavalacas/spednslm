package sped.negocio.entidades.sist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Error.findAll", query = "select o from Error o") })
@Table(name = "\"stmerro\"")
public class Error implements Serializable {
    private static final long serialVersionUID = 5382944921983268335L;
    @Id
    @Column(name = "cid_error", nullable = false)
    private String cidError;
    @Column(name = "desc_error", nullable = false)
    private String descripcionError;
    @Column(name = "titulo_error", nullable = false)
    private String tituloError;

    public Error() {
    }

    public Error(String cidError, String descripcionError, String tituloError) {
        this.cidError = cidError;
        this.descripcionError = descripcionError;
        this.tituloError = tituloError;
    }


    public String getCidError() {
        return cidError;
    }

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getTituloError() {
        return tituloError;
    }

    public void setTituloError(String tituloError) {
        this.tituloError = tituloError;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("cidError=");
        buffer.append(getCidError());
        buffer.append(',');
        buffer.append("descripcionError=");
        buffer.append(getDescripcionError());
        buffer.append(',');
        buffer.append("tituloError=");
        buffer.append(getTituloError());
        buffer.append(']');
        return buffer.toString();
    }
}
