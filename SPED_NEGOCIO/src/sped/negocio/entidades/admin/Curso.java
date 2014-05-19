package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Curso.findAll", query = "select o from Curso o") })
@Table(name = "\"admcurs\"")
public class Curso implements Serializable {
    private static final long serialVersionUID = -6660983497312486315L;
    @Column(name = "desc_curso", nullable = false)
    private String descripcionCurso;
    @Id
    @Column(name = "nidCurso", nullable = false)
    private int nidCurso;
    @OneToMany(mappedBy = "curso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Main> mainLista;
    @ManyToOne
    @JoinColumn(name = "nidAreaAcademica")
    private AreaAcademica areaAcademica;
    @Column(name = "nidAreaNativa")
    private int nidAreaNativa;
    @Column(name = "color")
    private String color;
    
    public Curso() {
    }

    public Curso(String descripcionCurso, AreaAcademica areaAcademica, int nidCurso) {
        this.descripcionCurso = descripcionCurso;
        this.areaAcademica = areaAcademica;
        this.nidCurso = nidCurso;
    }
    
    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }


    public int getNidCurso() {
        return nidCurso;
    }

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public List<Main> getMainLista() {
        return mainLista;
    }

    public void setMainLista(List<Main> mainLista) {
        this.mainLista = mainLista;
    }

    public Main addMain(Main main) {
        getMainLista().add(main);
        main.setCurso(this);
        return main;
    }

    public Main removeMain(Main main) {
        getMainLista().remove(main);
        main.setCurso(null);
        return main;
    }

    public AreaAcademica getAreaAcademica() {
        return areaAcademica;
    }

    public void setAreaAcademica(AreaAcademica areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionCurso=");
        buffer.append(getDescripcionCurso());
        buffer.append(',');
        buffer.append("nidCurso=");
        buffer.append(getNidCurso());
        buffer.append(']');
        return buffer.toString();
    }

    public void setNidAreaNativa(int nidAreaNativa) {
        this.nidAreaNativa = nidAreaNativa;
    }

    public int getNidAreaNativa() {
        return nidAreaNativa;
    }
}
