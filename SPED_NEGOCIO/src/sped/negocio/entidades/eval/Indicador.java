package sped.negocio.entidades.eval;

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
@NamedQueries({ @NamedQuery(name = "Indicador.findAll", query = "select o from Indicador o") })
@Table(name = "\"evmindi\"")
public class Indicador implements Serializable {
    private static final long serialVersionUID = -235608623166732674L;
    @Column(name = "desc_indicador")
    private String descripcionIndicador;
    @Id
    @Column(name = "nidIndicador", nullable = false)
    private int nidIndicador;
    @OneToMany(mappedBy = "indicador", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CriterioIndicador> criterioIndicadorLista;

    public Indicador() {
    }

    public Indicador(String descripcionIndicador, int nidIndicador) {
        this.descripcionIndicador = descripcionIndicador;
        this.nidIndicador = nidIndicador;
    }


    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public int getNidIndicador() {
        return nidIndicador;
    }

    public void setNidIndicador(int nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public List<CriterioIndicador> getCriterioIndicadorLista() {
        return criterioIndicadorLista;
    }

    public void setCriterioIndicadorLista(List<CriterioIndicador> criterioIndicadorLista) {
        this.criterioIndicadorLista = criterioIndicadorLista;
    }

    public CriterioIndicador addCriterioIndicador(CriterioIndicador criterioIndicador) {
        getCriterioIndicadorLista().add(criterioIndicador);
        criterioIndicador.setIndicador(this);
        return criterioIndicador;
    }

    public CriterioIndicador removeCriterioIndicador(CriterioIndicador criterioIndicador) {
        getCriterioIndicadorLista().remove(criterioIndicador);
        criterioIndicador.setIndicador(null);
        return criterioIndicador;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionIndicador=");
        buffer.append(getDescripcionIndicador());
        buffer.append(',');
        buffer.append("nidIndicador=");
        buffer.append(getNidIndicador());
        buffer.append(']');
        return buffer.toString();
    }
}
