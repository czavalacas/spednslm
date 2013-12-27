package sped.negocio.entidades.eval;

import java.io.Serializable;

public class LeyendaPK implements Serializable {
    public int criterioIndicador;
    public int fichaValor;

    public LeyendaPK() {
    }

    public LeyendaPK(int criterioIndicador, int fichaValor) {
        this.criterioIndicador = criterioIndicador;
        this.fichaValor = fichaValor;
    }

    public boolean equals(Object other) {
        if (other instanceof LeyendaPK) {
            final LeyendaPK otherLeyendaPK = (LeyendaPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setCriterioIndicador(int criterioIndicador) {
        this.criterioIndicador = criterioIndicador;
    }

    public int getCriterioIndicador() {
        return criterioIndicador;
    }

    public void setFichaValor(int fichaValor) {
        this.fichaValor = fichaValor;
    }

    public int getFichaValor() {
        return fichaValor;
    }
}
