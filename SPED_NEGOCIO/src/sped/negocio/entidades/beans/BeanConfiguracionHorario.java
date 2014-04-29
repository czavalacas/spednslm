package sped.negocio.entidades.beans;

import java.sql.Time;

public class BeanConfiguracionHorario {
    
    private Time hora_fin;
    private Time hora_inicio;
    private int nidConfig;
    private int nidNivel;
    private int nidSede;
    private BeanConfiguracionEventoHorario eventoHorario;
    
    public BeanConfiguracionHorario() {
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setNidConfig(int nidConfig) {
        this.nidConfig = nidConfig;
    }

    public int getNidConfig() {
        return nidConfig;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setEventoHorario(BeanConfiguracionEventoHorario eventoHorario) {
        this.eventoHorario = eventoHorario;
    }

    public BeanConfiguracionEventoHorario getEventoHorario() {
        return eventoHorario;
    }
}
