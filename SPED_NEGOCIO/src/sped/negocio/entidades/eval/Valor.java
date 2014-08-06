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
@NamedQueries({ @NamedQuery(name = "Valor.findAll", query = "select o from Valor o") })
@Table(name = "\"evmvalo\"")
public class Valor implements Serializable {
    private static final long serialVersionUID = 3718688308774643098L;
    @Column(name = "desc_valor", nullable = false)
    private String descripcionValor;
    @Id
    @Column(name = "idValoracion", nullable = false)
    private int nidValoracion;
    @Column(name = "valor", nullable = false)
    private double valor;
    @OneToMany(mappedBy = "valor", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FichaValor> fichaValorLista;

    public Valor() {
    }

    public Valor(String descripcionValor, int nidValoracion, double valor) {
        this.descripcionValor = descripcionValor;
        this.nidValoracion = nidValoracion;
        this.valor = valor;
    }


    public String getDescripcionValor() {
        return descripcionValor;
    }

    public void setDescripcionValor(String descripcionValor) {
        this.descripcionValor = descripcionValor;
    }

    public int getNidValoracion() {
        return nidValoracion;
    }

    public void setNidValoracion(int nidValoracion) {
        this.nidValoracion = nidValoracion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<FichaValor> getFichaValorLista() {
        return fichaValorLista;
    }

    public void setFichaValorLista(List<FichaValor> fichaValorLista) {
        this.fichaValorLista = fichaValorLista;
    }

    public FichaValor addFichaValor(FichaValor fichaValor) {
        getFichaValorLista().add(fichaValor);
        fichaValor.setValor(this);
        return fichaValor;
    }

    public FichaValor removeFichaValor(FichaValor fichaValor) {
        getFichaValorLista().remove(fichaValor);
        fichaValor.setValor(null);
        return fichaValor;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionValor=");
        buffer.append(getDescripcionValor());
        buffer.append(',');
        buffer.append("nidValoracion=");
        buffer.append(getNidValoracion());
        buffer.append(',');
        buffer.append("valor=");
        buffer.append(getValor());
        buffer.append(']');
        return buffer.toString();
    }
}
