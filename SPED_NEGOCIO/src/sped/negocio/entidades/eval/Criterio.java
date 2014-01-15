package sped.negocio.entidades.eval;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "Criterio.findAll", query = "select o from Criterio o") })
@Table(name = "\"evmcrit\"")
public class Criterio implements Serializable {
    private static final long serialVersionUID = 3740841715982230877L;
    @Column(name = "desc_criterio")
    private String descripcionCriterio;
    @Id
    @TableGenerator( name = "stmcodi_evmcrit", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evmcrit.nidCriterio", valueColumnName = "APP_SEQ_VALUE", initialValue = 4, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_evmcrit" )
    @Column(name = "nidCriterio", nullable = false)
    private int nidCriterio;
    @OneToMany(mappedBy = "criterio", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FichaCriterio> fichaCriterioLista;

    public Criterio() {
    }

    public Criterio(String descripcionCriterio, int nidCriterio) {
        this.descripcionCriterio = descripcionCriterio;
        this.nidCriterio = nidCriterio;
    }


    public String getDescripcionCriterio() {
        return descripcionCriterio;
    }

    public void setDescripcionCriterio(String descripcionCriterio) {
        this.descripcionCriterio = descripcionCriterio;
    }

    public int getNidCriterio() {
        return nidCriterio;
    }

    public void setNidCriterio(int nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public List<FichaCriterio> getFichaCriterioLista() {
        return fichaCriterioLista;
    }

    public void setFichaCriterioLista(List<FichaCriterio> fichaCriterioLista) {
        this.fichaCriterioLista = fichaCriterioLista;
    }

    public FichaCriterio addFichaCriterio(FichaCriterio fichaCriterio) {
        getFichaCriterioLista().add(fichaCriterio);
        fichaCriterio.setCriterio(this);
        return fichaCriterio;
    }

    public FichaCriterio removeFichaCriterio(FichaCriterio fichaCriterio) {
        getFichaCriterioLista().remove(fichaCriterio);
        fichaCriterio.setCriterio(null);
        return fichaCriterio;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionCriterio=");
        buffer.append(getDescripcionCriterio());
        buffer.append(',');
        buffer.append("nidCriterio=");
        buffer.append(getNidCriterio());
        buffer.append(']');
        return buffer.toString();
    }
}
