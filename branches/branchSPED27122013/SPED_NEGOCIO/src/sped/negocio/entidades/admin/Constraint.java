package sped.negocio.entidades.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Constraint.findAll", query = "select o from Constraint o") })
@Table(name = "\"admcons\"")
@IdClass(ConstraintPK.class)
public class Constraint implements Serializable {
    private static final long serialVersionUID = 8174943411046598906L;
    @Column(name = "descripcion")
    private String descripcionAMostrar;
    @Id
    @Column(name = "campo", nullable = false)
    private String nombreCampo;
    @Id
    @Column(name = "tabla", nullable = false)
    private String nombreTabla;
    @Id
    @Column(name = "valor", nullable = false)
    private String valorCampo;

    public Constraint() {
    }

    public Constraint(String descripcionAMostrar, String nombreCampo, String nombreTabla, String valorCampo) {
        this.descripcionAMostrar = descripcionAMostrar;
        this.nombreCampo = nombreCampo;
        this.nombreTabla = nombreTabla;
        this.valorCampo = valorCampo;
    }


    public String getDescripcionAMostrar() {
        return descripcionAMostrar;
    }

    public void setDescripcionAMostrar(String descripcionAMostrar) {
        this.descripcionAMostrar = descripcionAMostrar;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionAMostrar=");
        buffer.append(getDescripcionAMostrar());
        buffer.append(',');
        buffer.append("nombreCampo=");
        buffer.append(getNombreCampo());
        buffer.append(',');
        buffer.append("nombreTabla=");
        buffer.append(getNombreTabla());
        buffer.append(',');
        buffer.append("valorCampo=");
        buffer.append(getValorCampo());
        buffer.append(']');
        return buffer.toString();
    }
}
