package sped.negocio.entidades.eval;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Resultado.findAll", query = "select o from Resultado o") })
@Table(name = "\"evdresu\"")
@IdClass(ResultadoPK.class)
public class Resultado implements Serializable {
    private static final long serialVersionUID = -8501344454150264445L;
    @Column(name = "valor", nullable = false)
    private short valor;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidCriterioIndicador")
    private CriterioIndicador criterioIndicador;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidEvaluacion")
    private Evaluacion evaluacion;

    public Resultado() {
    }

    public Resultado(CriterioIndicador criterioIndicador, Evaluacion evaluacion, short valor) {
        this.criterioIndicador = criterioIndicador;
        this.evaluacion = evaluacion;
        this.valor = valor;
    }

    public short getValor() {
        return valor;
    }

    public void setValor(short valor) {
        this.valor = valor;
    }

    public CriterioIndicador getCriterioIndicador() {
        return criterioIndicador;
    }

    public void setCriterioIndicador(CriterioIndicador criterioIndicador) {
        this.criterioIndicador = criterioIndicador;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("valor=");
        buffer.append(getValor());
        buffer.append(']');
        return buffer.toString();
    }
}
