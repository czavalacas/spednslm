package sped.negocio.entidades.sist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import sped.negocio.entidades.admin.Usuario;

@Entity
@NamedQueries({ @NamedQuery(name = "UsuarioPermiso.findAll", query = "select o from UsuarioPermiso o") })
@Table(name = "\"stdusrp\"")
public class UsuarioPermiso implements Serializable {
    private static final long serialVersionUID = -2026256947990985958L;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Id
    @Column(name = "nidPermisoUsuario", nullable = false)
    private int nidPermisoUsuario;
    @ManyToOne
    @JoinColumn(name = "nidUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumns({
                 @JoinColumn(name = "nidRol", referencedColumnName = "nidRol"),
                 @JoinColumn(name = "nidPermiso", referencedColumnName = "nidPermiso")
        })
    private RolPermiso rolPermiso;

    public UsuarioPermiso() {
    }

    public void setRolPermiso(RolPermiso rolPermiso) {
        this.rolPermiso = rolPermiso;
    }

    public RolPermiso getRolPermiso() {
        return rolPermiso;
    }

    public UsuarioPermiso(String estado, int nidPermisoUsuario) {
        this.estado = estado;
        this.nidPermisoUsuario = nidPermisoUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNidPermisoUsuario() {
        return nidPermisoUsuario;
    }

    public void setNidPermisoUsuario(int nidPermisoUsuario) {
        this.nidPermisoUsuario = nidPermisoUsuario;
    }
}
