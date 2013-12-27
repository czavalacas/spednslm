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
@NamedQueries({ @NamedQuery(name = "Nivel.findAll", query = "select o from Nivel o") })
@Table(name = "\"admnive\"")
public class Nivel implements Serializable {
    private static final long serialVersionUID = -1748198043861578063L;
    @Column(name = "desc_nivel", nullable = false)
    private String descripcionNivel;
    @Id
    @Column(name = "nidNivel", nullable = false)
    private int nidNivel;
    @OneToMany(mappedBy = "nivel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SedeNivel> sedeNivelLista;
    @OneToMany(mappedBy = "nivel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<GradoNivel> gradoNivelLista;

    public Nivel() {
    }

    public Nivel(String descripcionNivel, int nidNivel) {
        this.descripcionNivel = descripcionNivel;
        this.nidNivel = nidNivel;
    }


    public String getDescripcionNivel() {
        return descripcionNivel;
    }

    public void setDescripcionNivel(String descripcionNivel) {
        this.descripcionNivel = descripcionNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public List<SedeNivel> getSedeNivelLista() {
        return sedeNivelLista;
    }

    public void setSedeNivelLista(List<SedeNivel> sedeNivelLista) {
        this.sedeNivelLista = sedeNivelLista;
    }

    public SedeNivel addSedeNivel(SedeNivel sedeNivel) {
        getSedeNivelLista().add(sedeNivel);
        sedeNivel.setNivel(this);
        return sedeNivel;
    }

    public SedeNivel removeSedeNivel(SedeNivel sedeNivel) {
        getSedeNivelLista().remove(sedeNivel);
        sedeNivel.setNivel(null);
        return sedeNivel;
    }

    public List<GradoNivel> getGradoNivelLista() {
        return gradoNivelLista;
    }

    public void setGradoNivelLista(List<GradoNivel> gradoNivelLista) {
        this.gradoNivelLista = gradoNivelLista;
    }

    public GradoNivel addGradoNivel(GradoNivel gradoNivel) {
        getGradoNivelLista().add(gradoNivel);
        gradoNivel.setNivel(this);
        return gradoNivel;
    }

    public GradoNivel removeGradoNivel(GradoNivel gradoNivel) {
        getGradoNivelLista().remove(gradoNivel);
        gradoNivel.setNivel(null);
        return gradoNivel;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionNivel=");
        buffer.append(getDescripcionNivel());
        buffer.append(',');
        buffer.append("nidNivel=");
        buffer.append(getNidNivel());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
