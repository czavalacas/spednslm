package test.negocio.entidades;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "Actor.findAll", query = "select o from Actor o") })
@Table(name = "\"actor\"")
public class Actor implements Serializable {
    private static final long serialVersionUID = -2781788655616950560L;
    @Id
    @Column(name = "actor_id", nullable = false)
    @TableGenerator( name = "stmcodi", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evmeval.nidEvaluacion", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi" )
    private short actor_id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Column(name = "last_update", nullable = false)
    private Timestamp last_update;
    @OneToMany(mappedBy = "actor", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FilmActor> filmActorList;

    public Actor() {
    }

    public Actor(short actor_id, String first_name, String last_name, Timestamp last_update) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }


    public short getActor_id() {
        return actor_id;
    }

    public void setActor_id(short actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public List<FilmActor> getFilmActorList() {
        return filmActorList;
    }

    public void setFilmActorList(List<FilmActor> filmActorList) {
        this.filmActorList = filmActorList;
    }

    public FilmActor addFilmActor(FilmActor filmActor) {
        getFilmActorList().add(filmActor);
        filmActor.setActor(this);
        return filmActor;
    }

    public FilmActor removeFilmActor(FilmActor filmActor) {
        getFilmActorList().remove(filmActor);
        filmActor.setActor(null);
        return filmActor;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("actor_id=");
        buffer.append(getActor_id());
        buffer.append(',');
        buffer.append("first_name=");
        buffer.append(getFirst_name());
        buffer.append(',');
        buffer.append("last_name=");
        buffer.append(getLast_name());
        buffer.append(',');
        buffer.append("last_update=");
        buffer.append(getLast_update());
        buffer.append(']');
        return buffer.toString();
    }
}
