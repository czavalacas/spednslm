package sped.negocio.entidades.admin;

import java.io.Serializable;

public class SedeNivelPK implements Serializable {
    public int nivel;
    public int sede;

    public SedeNivelPK() {
    }

    public SedeNivelPK(int nivel, int sede) {
        this.nivel = nivel;
        this.sede = sede;
    }

    public boolean equals(Object other) {
        if (other instanceof SedeNivelPK) {
            final SedeNivelPK otherSedeNivelPK = (SedeNivelPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getSede() {
        return sede;
    }

}
