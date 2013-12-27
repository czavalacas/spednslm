package sped.negocio.entidades.admin;

import java.io.Serializable;

public class GradoNivelPK implements Serializable {
    public int grado;
    public int nivel;

    public GradoNivelPK() {
    }

    public GradoNivelPK(int grado, int nivel) {
        this.grado = grado;
        this.nivel = nivel;
    }

    public boolean equals(Object other) {
        if (other instanceof GradoNivelPK) {
            final GradoNivelPK otherGradoNivelPK = (GradoNivelPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }


    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getGrado() {
        return grado;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
