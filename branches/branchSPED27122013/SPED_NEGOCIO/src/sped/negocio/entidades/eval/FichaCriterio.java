package sped.negocio.entidades.eval;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "FichaCriterio.findAll", query = "select o from FichaCriterio o") })
@Table(name = "\"evdficr\"")
@IdClass(FichaCriterioPK.class)
public class FichaCriterio implements Serializable {
    private static final long serialVersionUID = 4361889253980138020L;
    @OneToMany(mappedBy = "fichaCriterio",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<CriterioIndicador> criterioIndicadorLista;
    /* @OneToMany(mappedBy = "fichaCriterio", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ResultadoCriterio> resultadoCriterioList; */
    @ManyToOne
    @Id
    @JoinColumn(name = "nidCriterio")
    private Criterio criterio;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidFicha")
    private Ficha ficha;
    @Column(name = "orden")
    private int orden;
    @OneToMany(mappedBy = "fichaCriterio",fetch = FetchType.EAGER ,cascade = { CascadeType.ALL })
    private List<CriterioValor> lstCriterioValores;

    public FichaCriterio() {
    }

    public FichaCriterio(Criterio criterio, Ficha ficha) {
        this.criterio = criterio;
        this.ficha = ficha;
    }

    public void setLstCriterioValores(List<CriterioValor> lstCriterioValores) {
        this.lstCriterioValores = lstCriterioValores;
    }

    public List<CriterioValor> getLstCriterioValores() {
        return lstCriterioValores;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public List<CriterioIndicador> getCriterioIndicadorLista() {
        return criterioIndicadorLista;
    }

    public void setCriterioIndicadorLista(List<CriterioIndicador> criterioIndicadorLista) {
        this.criterioIndicadorLista = criterioIndicadorLista;
    }

    public CriterioIndicador addCriterioIndicador(CriterioIndicador criterioIndicador) {
        getCriterioIndicadorLista().add(criterioIndicador);
        criterioIndicador.setFichaCriterio(this);
        return criterioIndicador;
    }

    public CriterioIndicador removeCriterioIndicador(CriterioIndicador criterioIndicador) {
        getCriterioIndicadorLista().remove(criterioIndicador);
        criterioIndicador.setFichaCriterio(null);
        return criterioIndicador;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
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
