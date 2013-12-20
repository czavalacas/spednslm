package test.view.backing.Actores;

import java.sql.Timestamp;

import test.negocio.entidades.Actor;

public class SessionScopedBeanConsultarActores {
    
    private Actor actor;
    private String first_name;
    private String last_name;
    private Timestamp last_update;
    private String nombreBoton;

    public void setTipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getTipoEvento() {
        return tipoEvento;
    }
    private int tipoEvento;

    public void setNombreBoton(String nombreBoton) {
        this.nombreBoton = nombreBoton;
    }

    public String getNombreBoton() {
        return nombreBoton;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Timestamp getLast_update() {
        return last_update;
    }
}
