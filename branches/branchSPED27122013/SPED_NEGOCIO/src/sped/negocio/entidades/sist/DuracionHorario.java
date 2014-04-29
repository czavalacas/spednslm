package sped.negocio.entidades.sist;

import java.io.Serializable;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "DuracionHorario.findAll", query = "select o from DuracionHorario o") })
@Table(name = "\"stmdura\"")
public class DuracionHorario implements Serializable {
    private static final long serialVersionUID = 1364554502108160251L;
    @Column(name = "duracion")
    private Time duracion;
    @Column(name = "hora_inicio")
    private Time hora_inicio;
    @Column(name = "max_bloque")
    private int max_bloque;
    @Id
    @Column(name = "nidDura", nullable = false)
    private int nidDura;
    @Column(name = "nidNivel")
    private int nidNivel;
    @Column(name = "nidSede")
    private int nidSede;

    public DuracionHorario() {
    }

    public DuracionHorario(Time duracion, Time hora_inicio, int max_bloque, int nidDura, int nidNivel, int nidSede) {
        this.duracion = duracion;
        this.hora_inicio = hora_inicio;
        this.max_bloque = max_bloque;
        this.nidDura = nidDura;
        this.nidNivel = nidNivel;
        this.nidSede = nidSede;
    }


    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getMax_bloque() {
        return max_bloque;
    }

    public void setMax_bloque(int max_bloque) {
        this.max_bloque = max_bloque;
    }

    public int getNidDura() {
        return nidDura;
    }

    public void setNidDura(int nidDura) {
        this.nidDura = nidDura;
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

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("duracion=");
        buffer.append(getDuracion());
        buffer.append(',');
        buffer.append("hora_inicio=");
        buffer.append(getHora_inicio());
        buffer.append(',');
        buffer.append("max_bloque=");
        buffer.append(getMax_bloque());
        buffer.append(',');
        buffer.append("nidDura=");
        buffer.append(getNidDura());
        buffer.append(',');
        buffer.append("nidNivel=");
        buffer.append(getNidNivel());
        buffer.append(',');
        buffer.append("nidSede=");
        buffer.append(getNidSede());
        buffer.append(']');
        return buffer.toString();
    }
}
