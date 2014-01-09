package sped.negocio.entidades.admin;

import java.io.Serializable;

public class ConstraintPK implements Serializable {
    public String nombreCampo;
    public String nombreTabla;
    public String valorCampo;

    public ConstraintPK() {
    }

    public ConstraintPK(String nombreCampo, String nombreTabla, String valorCampo) {
        this.nombreCampo = nombreCampo;
        this.nombreTabla = nombreTabla;
        this.valorCampo = valorCampo;
    }

    public boolean equals(Object other) {
        if (other instanceof ConstraintPK) {
            final ConstraintPK otherConstraintPK = (ConstraintPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
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
}
