package sped.negocio.entidades.sist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "RolPermiso.findAll", query = "select o from RolPermiso o") })
@Table(name = "\"stdrope\"")
@IdClass(RolPermisoPK.class)
public class RolPermiso implements Serializable {
    private static final long serialVersionUID = 7326032912926102222L;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidRol")
    private Rol rol;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidPermiso")
    private Permiso permiso;

    public RolPermiso() {
    }

    public RolPermiso(Permiso permiso, Rol rol) {
        this.permiso = permiso;
        this.rol = rol;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append(']');
        return buffer.toString();
    }
}
