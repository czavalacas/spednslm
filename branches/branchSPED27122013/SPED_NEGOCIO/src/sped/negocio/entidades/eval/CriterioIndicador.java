package sped.negocio.entidades.eval;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "CriterioIndicador.findAll", query = "select o from CriterioIndicador o") })
@Table(name = "\"evdcrin\"")
public class CriterioIndicador implements Serializable {
    private static final long serialVersionUID = -8838816706570217560L;
    @Id
    @TableGenerator( name = "stmcodi.evdcrin", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evdcrin.nidCriterioIndicador", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.evdcrin" )
    @Column(name = "nidCriterioIndicador", nullable = false)
    private int nidCriterioIndicador;
    @ManyToOne
    @JoinColumns({
                 @JoinColumn(name = "nidFicha", referencedColumnName = "nidFicha"),
                 @JoinColumn(name = "nidCriterio", referencedColumnName = "nidCriterio")
        })
    private FichaCriterio fichaCriterio;
    @OneToMany(mappedBy = "criterioIndicador", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Leyenda> leyendaLista;
    @ManyToOne
    @JoinColumn(name = "nidIndicador")
    private Indicador indicador;
    @OneToMany(mappedBy = "criterioIndicador", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Resultado> resultadoLista;

    public CriterioIndicador() {
    }

    public CriterioIndicador(int nidCriterioIndicador, FichaCriterio fichaCriterio, Indicador indicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
        this.fichaCriterio = fichaCriterio;
        this.indicador = indicador;
    }


    public int getNidCriterioIndicador() {
        return nidCriterioIndicador;
    }

    public void setNidCriterioIndicador(int nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }


    public FichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }

    public void setFichaCriterio(FichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public List<Leyenda> getLeyendaLista() {
        return leyendaLista;
    }

    public void setLeyendaLista(List<Leyenda> leyendaLista) {
        this.leyendaLista = leyendaLista;
    }

    public Leyenda addLeyenda(Leyenda leyenda) {
        getLeyendaLista().add(leyenda);
        leyenda.setCriterioIndicador(this);
        return leyenda;
    }

    public Leyenda removeLeyenda(Leyenda leyenda) {
        getLeyendaLista().remove(leyenda);
        leyenda.setCriterioIndicador(null);
        return leyenda;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public List<Resultado> getResultadoLista() {
        return resultadoLista;
    }

    public void setResultadoLista(List<Resultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public Resultado addResultado(Resultado resultado) {
        getResultadoLista().add(resultado);
        resultado.setCriterioIndicador(this);
        return resultado;
    }

    public Resultado removeResultado(Resultado resultado) {
        getResultadoLista().remove(resultado);
        resultado.setCriterioIndicador(null);
        return resultado;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("nidCriterioIndicador=");
        buffer.append(getNidCriterioIndicador());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
