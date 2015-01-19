package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Profesor.findAll", query = "select o from Profesor o") })
@Table(name = "\"admprof\"")
public class Profesor implements Serializable {
    private static final long serialVersionUID = 4796127452609205974L;
    @Column(name = "apellidos")
    private String apellidos;
    @Id
    @Column(name = "dniProfesor", nullable = false)
    private String dniProfesor;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "color")
    private String color;
    @OneToMany(mappedBy = "profesor", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Main> mainLista;

    public Profesor() {
    }

    public Profesor(String apellidos, String dniProfesor, String nombres) {
        this.apellidos = apellidos;
        this.dniProfesor = dniProfesor;
        this.nombres = nombres;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public List<Main> getMainLista() {
        return mainLista;
    }

    public void setMainLista(List<Main> mainLista) {
        this.mainLista = mainLista;
    }

    public Main addMain(Main main) {
        getMainLista().add(main);
        main.setProfesor(this);
        return main;
    }

    public Main removeMain(Main main) {
        getMainLista().remove(main);
        main.setProfesor(null);
        return main;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("apellidos=");
        buffer.append(getApellidos());
        buffer.append(',');
        buffer.append("dniProfesor=");
        buffer.append(getDniProfesor());
        buffer.append(',');
        buffer.append("nombres=");
        buffer.append(getNombres());
        buffer.append(']');
        return buffer.toString();
    }
}
