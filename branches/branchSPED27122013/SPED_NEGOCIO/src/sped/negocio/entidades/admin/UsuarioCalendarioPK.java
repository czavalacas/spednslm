package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.Date;

public class UsuarioCalendarioPK implements Serializable {
    public Date nidFecha;
    public int nidUsuario;

    public UsuarioCalendarioPK() {
    }

    public UsuarioCalendarioPK(Date nidFecha, int nidUsuario) {
        this.nidFecha = nidFecha;
        this.nidUsuario = nidUsuario;
    }

    public boolean equals(Object other) {
        if (other instanceof UsuarioCalendarioPK) {
            final UsuarioCalendarioPK otherUsuarioCalendarioPK = (UsuarioCalendarioPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Date getNidFecha() {
        return nidFecha;
    }

    public void setNidFecha(Date nidFecha) {
        this.nidFecha = nidFecha;
    }

    public int getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(int nidUsuario) {
        this.nidUsuario = nidUsuario;
    }
}
