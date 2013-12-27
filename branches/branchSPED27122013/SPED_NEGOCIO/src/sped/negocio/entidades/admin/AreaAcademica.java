package sped.negocio.entidades.admin;

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

@Entity
@NamedQueries({ @NamedQuery(name = "AreaAcademica.findAll", query = "select o from AreaAcademica o") })
@Table(name = "\"admarac\"")
public class AreaAcademica implements Serializable {
    private static final long serialVersionUID = -661026253930099559L;
    @Column(name = "desc_area_academica", nullable = false)
    private String descripcionAreaAcademica;
    @Id
    @Column(name = "nidAreaAcademica", nullable = false)
    private int nidAreaAcademica;
    @OneToMany(mappedBy = "areaAcademica", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Usuario> usuarioLista;
    @OneToMany(mappedBy = "areaAcademica", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Curso> cursosLista;

    public AreaAcademica() {
    }

    public AreaAcademica(String descripcionAreaAcademica, int nidAreaAcademica) {
        this.descripcionAreaAcademica = descripcionAreaAcademica;
        this.nidAreaAcademica = nidAreaAcademica;
    }


    public String getDescripcionAreaAcademica() {
        return descripcionAreaAcademica;
    }

    public void setDescripcionAreaAcademica(String descripcionAreaAcademica) {
        this.descripcionAreaAcademica = descripcionAreaAcademica;
    }

    public int getNidAreaAcademica() {
        return nidAreaAcademica;
    }

    public void setNidAreaAcademica(int nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public List<Usuario> getUsuarioLista() {
        return usuarioLista;
    }

    public void setUsuarioLista(List<Usuario> usuarioLista) {
        this.usuarioLista = usuarioLista;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarioLista().add(usuario);
        usuario.setAreaAcademica(this);
        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarioLista().remove(usuario);
        usuario.setAreaAcademica(null);
        return usuario;
    }

    public List<Curso> getCursosLista() {
        return cursosLista;
    }

    public void setCursosLista(List<Curso> cursosLista) {
        this.cursosLista = cursosLista;
    }

    public Curso addCurso(Curso curso) {
        getCursosLista().add(curso);
        curso.setAreaAcademica(this);
        return curso;
    }

    public Curso removeCurso(Curso curso) {
        getCursosLista().remove(curso);
        curso.setAreaAcademica(null);
        return curso;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionAreaAcademica=");
        buffer.append(getDescripcionAreaAcademica());
        buffer.append(',');
        buffer.append("nidAreaAcademica=");
        buffer.append(getNidAreaAcademica());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
