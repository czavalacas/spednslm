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
@NamedQueries({ @NamedQuery(name = "Film.findAll", query = "select o from Film o") })
@Table(name = "\"film\"")
public class Film implements Serializable {
    private static final long serialVersionUID = -7714045192185565271L;
    @Column(name = "description")
    private String description;
    @Id
    @Column(name = "film_id", nullable = false)
    @TableGenerator( name = "stmcodi_film", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "film.film_id", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi" )
    private short film_id;
    @Column(name = "language_id", nullable = false)
    private byte language_id;
    @Column(name = "last_update", nullable = false)
    private Timestamp last_update;
    @Column(name = "length")
    private short length;
    @Column(name = "original_language_id")
    private byte original_language_id;
    @Column(name = "rating")
    private String rating;
    @Column(name = "release_year")
    private Timestamp release_year;
    @Column(name = "rental_duration", nullable = false)
    private byte rental_duration;
    @Column(name = "rental_rate", nullable = false)
    private Integer rental_rate;
    @Column(name = "replacement_cost", nullable = false)
    private Integer replacement_cost;
    @Column(name = "special_features")
    private String special_features;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "film", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FilmActor> filmActorList1;

    public Film() {
    }

    public Film(String description, short film_id, byte language_id, Timestamp last_update, short length,
                byte original_language_id, String rating, Timestamp release_year, byte rental_duration,
                Integer rental_rate, Integer replacement_cost, String special_features, String title) {
        this.description = description;
        this.film_id = film_id;
        this.language_id = language_id;
        this.last_update = last_update;
        this.length = length;
        this.original_language_id = original_language_id;
        this.rating = rating;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
        this.special_features = special_features;
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getFilm_id() {
        return film_id;
    }

    public void setFilm_id(short film_id) {
        this.film_id = film_id;
    }

    public byte getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(byte language_id) {
        this.language_id = language_id;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(byte original_language_id) {
        this.original_language_id = original_language_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Timestamp getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Timestamp release_year) {
        this.release_year = release_year;
    }

    public byte getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(byte rental_duration) {
        this.rental_duration = rental_duration;
    }

    public Integer getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Integer rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Integer getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Integer replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FilmActor> getFilmActorList1() {
        return filmActorList1;
    }

    public void setFilmActorList1(List<FilmActor> filmActorList1) {
        this.filmActorList1 = filmActorList1;
    }

    public FilmActor addFilmActor(FilmActor filmActor) {
        getFilmActorList1().add(filmActor);
        filmActor.setFilm(this);
        return filmActor;
    }

    public FilmActor removeFilmActor(FilmActor filmActor) {
        getFilmActorList1().remove(filmActor);
        filmActor.setFilm(null);
        return filmActor;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("description=");
        buffer.append(getDescription());
        buffer.append(',');
        buffer.append("film_id=");
        buffer.append(getFilm_id());
        buffer.append(',');
        buffer.append("language_id=");
        buffer.append(getLanguage_id());
        buffer.append(',');
        buffer.append("last_update=");
        buffer.append(getLast_update());
        buffer.append(',');
        buffer.append("length=");
        buffer.append(getLength());
        buffer.append(',');
        buffer.append("original_language_id=");
        buffer.append(getOriginal_language_id());
        buffer.append(',');
        buffer.append("rating=");
        buffer.append(getRating());
        buffer.append(',');
        buffer.append("release_year=");
        buffer.append(getRelease_year());
        buffer.append(',');
        buffer.append("rental_duration=");
        buffer.append(getRental_duration());
        buffer.append(',');
        buffer.append("rental_rate=");
        buffer.append(getRental_rate());
        buffer.append(',');
        buffer.append("replacement_cost=");
        buffer.append(getReplacement_cost());
        buffer.append(',');
        buffer.append("special_features=");
        buffer.append(getSpecial_features());
        buffer.append(',');
        buffer.append("title=");
        buffer.append(getTitle());
        buffer.append(']');
        return buffer.toString();
    }
}
