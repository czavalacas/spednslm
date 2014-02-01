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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "FichaValor.findAll", query = "select o from FichaValor o") })
@Table(name = "\"evdfiva\"")
public class FichaValor implements Serializable {
    private static final long serialVersionUID = -6557091396121525959L;
    @Id
    @TableGenerator( name = "stmcodi.evdfiva", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evdfiva.nidFiva", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi.evdfiva" )
    @Column(name = "nidFiva", nullable = false)
    private int nidFichaValor;
    @ManyToOne
    @JoinColumn(name = "nidValoracion")
    private Valor valor;
    @OneToMany(mappedBy = "fichaValor", cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE })
    private List<Leyenda> leyendaLista;
    @ManyToOne
    @JoinColumn(name = "nidFicha")
    private Ficha ficha;

    public FichaValor() {
    }

    public FichaValor(Ficha ficha, int nidFichaValor, Valor valor) {
        this.ficha = ficha;
        this.nidFichaValor = nidFichaValor;
        this.valor = valor;
    }


    public int getNidFichaValor() {
        return nidFichaValor;
    }

    public void setNidFichaValor(int nidFichaValor) {
        this.nidFichaValor = nidFichaValor;
    }


    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public List<Leyenda> getLeyendaLista() {
        return leyendaLista;
    }

    public void setLeyendaLista(List<Leyenda> leyendaLista) {
        this.leyendaLista = leyendaLista;
    }

    public Leyenda addLeyenda(Leyenda leyenda) {
        getLeyendaLista().add(leyenda);
        leyenda.setFichaValor(this);
        return leyenda;
    }

    public Leyenda removeLeyenda(Leyenda leyenda) {
        getLeyendaLista().remove(leyenda);
        leyenda.setFichaValor(null);
        return leyenda;
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
        buffer.append("nidFichaValor=");
        buffer.append(getNidFichaValor());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
