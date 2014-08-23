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
@NamedQueries({ @NamedQuery(name = "Grado.findAll", query = "select o from Grado o") })
@Table(name = "\"admgrad\"")
public class Grado implements Serializable {
    private static final long serialVersionUID = -7116529041844702952L;
    @Column(name = "desc_grado")
    private String descripcionGrado;
    @Id
    @Column(name = "nidGrado", nullable = false)
    private int nidGrado;
    @OneToMany(mappedBy = "grado", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<GradoNivel> gradoNivelLista;
    @Column(name = "abvr", nullable = false)
    private String abvr;

    public Grado() {
    }

    public void setAbvr(String abvr) {
        this.abvr = abvr;
    }

    public String getAbvr() {
        return abvr;
    }

    public Grado(String descripcionGrado, int nidGrado) {
        this.descripcionGrado = descripcionGrado;
        this.nidGrado = nidGrado;
    }


    public String getDescripcionGrado() {
        return descripcionGrado;
    }

    public void setDescripcionGrado(String descripcionGrado) {
        this.descripcionGrado = descripcionGrado;
    }

    public int getNidGrado() {
        return nidGrado;
    }

    public void setNidGrado(int nidGrado) {
        this.nidGrado = nidGrado;
    }

    public List<GradoNivel> getGradoNivelLista() {
        return gradoNivelLista;
    }

    public void setGradoNivelLista(List<GradoNivel> gradoNivelLista) {
        this.gradoNivelLista = gradoNivelLista;
    }

    public GradoNivel addGradoNivel(GradoNivel gradoNivel) {
        getGradoNivelLista().add(gradoNivel);
        gradoNivel.setGrado(this);
        return gradoNivel;
    }

    public GradoNivel removeGradoNivel(GradoNivel gradoNivel) {
        getGradoNivelLista().remove(gradoNivel);
        gradoNivel.setGrado(null);
        return gradoNivel;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionGrado=");
        buffer.append(getDescripcionGrado());
        buffer.append(',');
        buffer.append("nidGrado=");
        buffer.append(getNidGrado());
        buffer.append(']');
        return buffer.toString();
    }
}
