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
@NamedQueries({ @NamedQuery(name = "Sede.findAll", query = "select o from Sede o ORDER BY o.descripcionSede ASC") })
@Table(name = "\"admsede\"")
public class Sede implements Serializable {
    @SuppressWarnings("compatibility:-7167946567470289288")
    private static final long serialVersionUID = 4786028708705973709L;
    @Column(name = "desc_sede", nullable = false)
    private String descripcionSede;
    @Id
    @Column(name = "nidSede", nullable = false)
    private int nidSede;
    @OneToMany(mappedBy = "sede", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Aula> aulaLista;

    public Sede() {
    }

    public Sede(String descripcionSede, int nidSede) {
        this.descripcionSede = descripcionSede;
        this.nidSede = nidSede;
    }


    public String getDescripcionSede() {
        return descripcionSede;
    }

    public void setDescripcionSede(String descripcionSede) {
        this.descripcionSede = descripcionSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public List<Aula> getAulaLista() {
        return aulaLista;
    }

    public void setAulaLista(List<Aula> aulaLista) {
        this.aulaLista = aulaLista;
    }

    public Aula addAula(Aula aula) {
        getAulaLista().add(aula);
        aula.setSede(this);
        return aula;
    }

    public Aula removeAula(Aula aula) {
        getAulaLista().remove(aula);
        aula.setSede(null);
        return aula;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionSede=");
        buffer.append(getDescripcionSede());
        buffer.append(',');
        buffer.append("nidSede=");
        buffer.append(getNidSede());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
