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
@NamedQueries({ @NamedQuery(name = "GradoNivel.findAll", query = "select o from GradoNivel o") })
@Table(name = "\"adgrani\"")
@IdClass(GradoNivelPK.class)
public class GradoNivel implements Serializable {
    private static final long serialVersionUID = 1038457670638909320L;
    @OneToMany(mappedBy = "gradoNivel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Aula> aulaLista;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidNivel")
    private Nivel nivel;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidGrado")
    private Grado grado;

    public GradoNivel() {
    }

    public GradoNivel(Grado grado, Nivel nivel) {
        this.grado = grado;
        this.nivel = nivel;
    }


    public List<Aula> getAulaLista() {
        return aulaLista;
    }

    public void setAulaLista(List<Aula> aulaLista) {
        this.aulaLista = aulaLista;
    }

    public Aula addAula(Aula aula) {
        getAulaLista().add(aula);
        aula.setGradoNivel(this);
        return aula;
    }

    public Aula removeAula(Aula aula) {
        getAulaLista().remove(aula);
        aula.setGradoNivel(null);
        return aula;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
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
