package sped.negocio.entidades.eval;

import java.io.Serializable;

public class FichaCriterioPK implements Serializable {
    public int criterio;
    public int ficha;

    public FichaCriterioPK() {
    }

    public FichaCriterioPK(int criterio, int ficha) {
        this.criterio = criterio;
        this.ficha = ficha;
    }

    public boolean equals(Object other) {
        if (other instanceof FichaCriterioPK) {
            final FichaCriterioPK otherFichaCriterioPK = (FichaCriterioPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }

    public int getCriterio() {
        return criterio;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public int getFicha() {
        return ficha;
    }
}
