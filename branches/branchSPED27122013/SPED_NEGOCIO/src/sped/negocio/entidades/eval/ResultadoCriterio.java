package sped.negocio.entidades.eval;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "ResultadoCriterio.findAll", query = "select o from ResultadoCriterio o") })
@Table(name = "\"evdrefc\"")
public class ResultadoCriterio implements Serializable {
    private static final long serialVersionUID = 3723001327582742186L;
    @Id
    @TableGenerator( name = "stmcodi.evdrefc", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evdrefc.nidResultadoCriterio", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.evdrefc" )
    @Column(name = "nidResultadoCriterio", nullable = false)
    private int nidResultadoCriterio;
    @Column(name = "valor")
    private double valor;
    @ManyToOne
    @JoinColumn(name = "nidEvaluacion")
    private Evaluacion evaluacion;
    @ManyToOne
    @JoinColumns({
                 @JoinColumn(name = "nidFicha", referencedColumnName = "nidFicha"),
                 @JoinColumn(name = "nidCriterio", referencedColumnName = "nidCriterio")
        })
    private FichaCriterio fichaCriterio;

    public ResultadoCriterio() {
    }

    public ResultadoCriterio(Evaluacion evaluacion, FichaCriterio fichaCriterio, int nidResultadoCriterio, double valor) {
        this.evaluacion = evaluacion;
        this.fichaCriterio = fichaCriterio;
        this.nidResultadoCriterio = nidResultadoCriterio;
        this.valor = valor;
    }


    public int getNidResultadoCriterio() {
        return nidResultadoCriterio;
    }

    public void setNidResultadoCriterio(int nidResultadoCriterio) {
        this.nidResultadoCriterio = nidResultadoCriterio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setFichaCriterio(FichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public FichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("nidResultadoCriterio=");
        buffer.append(getNidResultadoCriterio());
        buffer.append(',');
        buffer.append("valor=");
        buffer.append(getValor());
        buffer.append(']');
        return buffer.toString();
    }
}
