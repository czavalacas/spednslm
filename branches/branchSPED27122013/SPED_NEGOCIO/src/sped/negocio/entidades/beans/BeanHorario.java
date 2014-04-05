package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

public class BeanHorario implements Serializable {
    @SuppressWarnings("compatibility:-2617463507345312458")
    private static final long serialVersionUID = 1L;
    private Time duracion;
    private Time hora_fin;
    private Time hora_ini;
    private int nidHorario;
    private int nroBloque;
    private int maxBloque;

    public BeanHorario(){}

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_ini(Time hora_ini) {
        this.hora_ini = hora_ini;
    }

    public Time getHora_ini() {
        return hora_ini;
    }

    public void setNidHorario(int nidHorario) {
        this.nidHorario = nidHorario;
    }

    public int getNidHorario() {
        return nidHorario;
    }

    public void setNroBloque(int nroBloque) {
        this.nroBloque = nroBloque;
    }

    public int getNroBloque() {
        return nroBloque;
    }

    public void setMaxBloque(int maxBloque) {
        this.maxBloque = maxBloque;
    }

    public int getMaxBloque() {
        return maxBloque;
    }
}
