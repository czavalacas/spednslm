package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sped.negocio.entidades.admin.Usuario;

@Entity
@NamedQueries({ @NamedQuery(name = "Rol.findAll", query = "select o from Rol o") })
@Table(name = "\"stmrole\"")
public class Rol implements Serializable {
    private static final long serialVersionUID = -4836497265716306188L;
    @Column(name = "desc_rol")
    private String descripcionRol;
    @Id
    @Column(name = "nidRol", nullable = false)
    private int nidRol;
    @OneToMany(mappedBy = "rol", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Usuario> usuarioLista;
    @OneToMany(mappedBy = "rol", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<RolPermiso> rolPermisoLista;

    public Rol() {
    }

    public Rol(String descripcionRol, int nidRol) {
        this.descripcionRol = descripcionRol;
        this.nidRol = nidRol;
    }


    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public int getNidRol() {
        return nidRol;
    }

    public void setNidRol(int nidRol) {
        this.nidRol = nidRol;
    }

    public List<Usuario> getUsuarioLista() {
        return usuarioLista;
    }

    public void setUsuarioLista(List<Usuario> usuarioLista) {
        this.usuarioLista = usuarioLista;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarioLista().add(usuario);
        usuario.setRol(this);
        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarioLista().remove(usuario);
        usuario.setRol(null);
        return usuario;
    }

    public List<RolPermiso> getRolPermisoLista() {
        return rolPermisoLista;
    }

    public void setRolPermisoLista(List<RolPermiso> rolPermisoLista) {
        this.rolPermisoLista = rolPermisoLista;
    }

    public RolPermiso addRolPermiso(RolPermiso rolPermiso) {
        getRolPermisoLista().add(rolPermiso);
        rolPermiso.setRol(this);
        return rolPermiso;
    }

    public RolPermiso removeRolPermiso(RolPermiso rolPermiso) {
        getRolPermisoLista().remove(rolPermiso);
        rolPermiso.setRol(null);
        return rolPermiso;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionRol=");
        buffer.append(getDescripcionRol());
        buffer.append(',');
        buffer.append("nidRol=");
        buffer.append(getNidRol());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
