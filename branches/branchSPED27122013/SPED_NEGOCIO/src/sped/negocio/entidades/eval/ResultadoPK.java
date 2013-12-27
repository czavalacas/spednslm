package sped.negocio.entidades.eval;

import java.io.Serializable;

public class ResultadoPK implements Serializable {
    public int criterioIndicador;
    public int evaluacion;

    public ResultadoPK() {
    }

    public ResultadoPK(int criterioIndicador, int evaluacion) {
        this.criterioIndicador = criterioIndicador;
        this.evaluacion = evaluacion;
    }

    public boolean equals(Object other) {
        if (other instanceof ResultadoPK) {
            final ResultadoPK otherResultadoPK = (ResultadoPK) other;
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

    public void setEvaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    public int getEvaluacion() {
        return evaluacion;
    }
}
