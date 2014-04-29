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
@NamedQueries({@NamedQuery(name = "ConfiguracionEventoHorario.findAll", query = "select o from ConfiguracionEventoHorario o") })
@Table(name = "\"stmconfev\"")
public class ConfiguracionEventoHorario implements Serializable {
    @SuppressWarnings("compatibility:4223069990974001111")
    private static final long serialVersionUID = -1903493362187541852L;
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @Column(name = "nidConfev", nullable = false)
    private int nidConfev;
    @OneToMany(mappedBy = "stmconfev", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ConfiguracionHorario> configuracionHorarioList;

    public ConfiguracionEventoHorario() {
    }

    public ConfiguracionEventoHorario(String descripcion, int nidConfev) {
        this.descripcion = descripcion;
        this.nidConfev = nidConfev;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNidConfev() {
        return nidConfev;
    }

    public void setNidConfev(int nidConfev) {
        this.nidConfev = nidConfev;
    }

    public List<ConfiguracionHorario> getConfiguracionHorarioList() {
        return configuracionHorarioList;
    }

    public void setConfiguracionHorarioList(List<ConfiguracionHorario> configuracionHorarioList) {
        this.configuracionHorarioList = configuracionHorarioList;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcion=");
        buffer.append(getDescripcion());
        buffer.append(',');
        buffer.append("nidConfev=");
        buffer.append(getNidConfev());
        buffer.append(']');
        return buffer.toString();
    }
}
