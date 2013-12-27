package sped.negocio.entidades.sist;

import java.io.Serializable;

public class RolPermisoPK implements Serializable {
    public int permiso;
    public int rol;

    public RolPermisoPK() {
    }

    public RolPermisoPK(int permiso, int rol) {
        this.permiso = permiso;
        this.rol = rol;
    }

    public boolean equals(Object other) {
        if (other instanceof RolPermisoPK) {
            final RolPermisoPK otherRolPermisoPK = (RolPermisoPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }

    public int getPermiso() {
        return permiso;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getRol() {
        return rol;
    }
}
