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
@NamedQueries({ @NamedQuery(name = "Leyenda.findAll", query = "select o from Leyenda o") })
@Table(name = "\"evdleye\"")
@IdClass(LeyendaPK.class)
public class Leyenda implements Serializable {
    private static final long serialVersionUID = 638102796305271192L;
    @Column(name = "desc_leyenda", nullable = false)
    private String descripcionLeyenda;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidCriterioIndicador")
    private CriterioIndicador criterioIndicador;
    @ManyToOne
    @Id
    @JoinColumn(name = "nidFiva")
    private FichaValor fichaValor;

    public Leyenda() {
    }

    public Leyenda(String descripcionLeyenda, CriterioIndicador criterioIndicador, FichaValor fichaValor) {
        this.descripcionLeyenda = descripcionLeyenda;
        this.criterioIndicador = criterioIndicador;
        this.fichaValor = fichaValor;
    }


    public String getDescripcionLeyenda() {
        return descripcionLeyenda;
    }

    public void setDescripcionLeyenda(String descripcionLeyenda) {
        this.descripcionLeyenda = descripcionLeyenda;
    }


    public CriterioIndicador getCriterioIndicador() {
        return criterioIndicador;
    }

    public void setCriterioIndicador(CriterioIndicador criterioIndicador) {
        this.criterioIndicador = criterioIndicador;
    }

    public FichaValor getFichaValor() {
        return fichaValor;
    }

    public void setFichaValor(FichaValor fichaValor) {
        this.fichaValor = fichaValor;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionLeyenda=");
        buffer.append(getDescripcionLeyenda());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
