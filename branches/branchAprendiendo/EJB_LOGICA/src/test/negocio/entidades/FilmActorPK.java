package test.negocio.entidades;

import java.io.Serializable;

public class FilmActorPK implements Serializable {
    public short actor;
    public short film;

    public FilmActorPK() {
    }

    public FilmActorPK(short actor, short film) {
        this.actor = actor;
        this.film = film;
    }

    public boolean equals(Object other) {
        if (other instanceof FilmActorPK) {
            final FilmActorPK otherFilmActorPK = (FilmActorPK) other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setActor(short actor) {
        this.actor = actor;
    }

    public short getActor() {
        return actor;
    }

    public void setFilm(short film) {
        this.film = film;
    }

    public short getFilm() {
        return film;
    }

}
