package test.negocio.entidades;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "FilmActor.findAll", query = "select o from FilmActor o") })
@Table(name = "\"film_actor\"")
@IdClass(FilmActorPK.class)
public class FilmActor implements Serializable {
    private static final long serialVersionUID = 6103092084882193948L;
    @Column(name = "last_update", nullable = false)
    private Timestamp last_update;
    @ManyToOne
    @Id
    @JoinColumn(name = "actor_id")
    private Actor actor;
    @ManyToOne
    @Id
    @JoinColumn(name = "film_id")
    private Film film;

    public FilmActor() {
    }

    public FilmActor(Actor actor, Film film, Timestamp last_update) {
        this.actor = actor;
        this.film = film;
        this.last_update = last_update;
    }


    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("last_update=");
        buffer.append(getLast_update());
        buffer.append(']');
        return buffer.toString();
    }
}
