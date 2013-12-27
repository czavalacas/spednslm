package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "SedeNivel.findAll", query = "select o from SedeNivel o") })
@Table(name = "\"addseni\"")
@IdClass(SedeNivelPK.class)
public class SedeNivel implements Serializable {
    private static final long serialVersionUID = 774119624389561388L;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidNivel")
    private Nivel nivel;
    @OneToMany(mappedBy = "sedeNivel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Usuario> usuarioLista;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidSede")
    private Sede sede;

    public SedeNivel() {
    }

    public SedeNivel(Nivel nivel, Sede sede) {
        this.nivel = nivel;
        this.sede = sede;
    }


    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Usuario> getUsuarioLista() {
        return usuarioLista;
    }

    public void setUsuarioLista(List<Usuario> usuarioLista) {
        this.usuarioLista = usuarioLista;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarioLista().add(usuario);
        usuario.setSedeNivel(this);
        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarioLista().remove(usuario);
        usuario.setSedeNivel(null);
        return usuario;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
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
