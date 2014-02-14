package sped.negocio.entidades.sist;

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
@NamedQueries({ @NamedQuery(name = "Evento.findAll", query = "select o from Evento o") })
@Table(name = "\"stmeven\"")
public class Evento implements Serializable {
    private static final long serialVersionUID = 4745406162860003744L;
    @Column(name = "desc_evento")
    private String descripcionEvento;
    @Id
    @Column(name = "nid_evento", nullable = false)
    private short nidEvento;
    /*@OneToMany(mappedBy = "evento", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Log> logLista;*/

    public Evento() {
    }

    public Evento(String descripcionEvento, short nidEvento) {
        this.descripcionEvento = descripcionEvento;
        this.nidEvento = nidEvento;
    }


    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public short getNidEvento() {
        return nidEvento;
    }

    public void setNidEvento(short nidEvento) {
        this.nidEvento = nidEvento;
    }
/*
    public List<Log> getLogLista() {
        return logLista;
    }

    public void setLogLista(List<Log> logLista) {
        this.logLista = logLista;
    }
*/
   /* public Log addLog(Log log) {
        getLogLista().add(log);
        log.setEvento(this);
        return log;
    }

    public Log removeLog(Log log) {
        getLogLista().remove(log);
        log.setEvento(null);
        return log;
    }*/

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionEvento=");
        buffer.append(getDescripcionEvento());
        buffer.append(',');
        buffer.append("nidEvento=");
        buffer.append(getNidEvento());
        buffer.append(']');
        return buffer.toString();
    }
}
