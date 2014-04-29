package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "ConfiguracionHorario.findAll", query = "select o from ConfiguracionHorario o") })
@Table(name = "\"stdconfig\"")
public class ConfiguracionHorario implements Serializable {
    private static final long serialVersionUID = -8162479242119456607L;
    @Column(name = "hora_fin")
    private Time hora_fin;
    @Column(name = "hora_inicio")
    private Time hora_inicio;
    @Id
    @Column(name = "nidConfig", nullable = false)
    private int nidConfig;
    @Column(name = "nidNivel")
    private int nidNivel;
    @Column(name = "nidSede")
    private int nidSede;
    @ManyToOne
    @JoinColumn(name = "nidConfev")
    private ConfiguracionEventoHorario stmconfev;

    public ConfiguracionHorario() {
    }

    public ConfiguracionHorario(Time hora_fin, Time hora_inicio, ConfiguracionEventoHorario stmconfev, int nidConfig, int nidNivel,
                                int nidSede) {
        this.hora_fin = hora_fin;
        this.hora_inicio = hora_inicio;
        this.stmconfev = stmconfev;
        this.nidConfig = nidConfig;
        this.nidNivel = nidNivel;
        this.nidSede = nidSede;
    }


    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }


    public int getNidConfig() {
        return nidConfig;
    }

    public void setNidConfig(int nidConfig) {
        this.nidConfig = nidConfig;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public void setStmconfev(ConfiguracionEventoHorario stmconfev) {
        this.stmconfev = stmconfev;
    }

    public ConfiguracionEventoHorario getStmconfev() {
        return stmconfev;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("hora_fin=");
        buffer.append(getHora_fin());
        buffer.append(',');
        buffer.append("hora_inicio=");
        buffer.append(getHora_inicio());
        buffer.append(',');
        buffer.append("nidConfig=");
        buffer.append(getNidConfig());
        buffer.append(',');
        buffer.append("nidNivel=");
        buffer.append(getNidNivel());
        buffer.append(',');
        buffer.append("nidSede=");
        buffer.append(getNidSede());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
