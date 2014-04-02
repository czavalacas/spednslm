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
@NamedQueries({ @NamedQuery(name = "Horario.findAll", query = "select o from Horario o") })
@Table(name = "\"stmhora\"")
public class Horario implements Serializable {
    private static final long serialVersionUID = 2215615158515643434L;
    @Column(name = "duracion", nullable = false)
    private Time duracion;
    @Column(name = "hora_fin", nullable = false)
    private Time hora_fin;
    @Column(name = "hora_ini", nullable = false)
    private Time hora_ini;
    @Id
    @Column(name = "nidHorario", nullable = false)
    private int nidHorario;
    @Column(name = "nroBloque", nullable = false)
    private int nroBloque;

    public Horario() {
    }

    public Horario(Time duracion, Time hora_fin, Time hora_ini, int nidHorario, int nroBloque) {
        this.duracion = duracion;
        this.hora_fin = hora_fin;
        this.hora_ini = hora_ini;
        this.nidHorario = nidHorario;
        this.nroBloque = nroBloque;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(Time hora_ini) {
        this.hora_ini = hora_ini;
    }

    public int getNidHorario() {
        return nidHorario;
    }

    public void setNidHorario(int nidHorario) {
        this.nidHorario = nidHorario;
    }

    public int getNroBloque() {
        return nroBloque;
    }

    public void setNroBloque(int nroBloque) {
        this.nroBloque = nroBloque;
    }
}
