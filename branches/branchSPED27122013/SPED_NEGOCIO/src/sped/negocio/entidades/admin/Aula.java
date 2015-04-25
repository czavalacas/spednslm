package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "Aula.findAll", query = "select o from Aula o") })
@Table(name = "\"admaula\"")
public class Aula implements Serializable {
    private static final long serialVersionUID = -1772511384553910320L;
    @Column(name = "desc_aula", nullable = false)
    private String descripcionAula;
    @Id
    @Column(name = "nidAula", nullable = false)
    @TableGenerator( name = "stmcodi_admaula", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "admaula.nidAula", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_admaula" )
    private int nidAula;
    @ManyToOne
    @JoinColumn(name = "nidSede")
    private Sede sede;
    @ManyToOne
    @JoinColumns({
                 @JoinColumn(name = "nidGrado", referencedColumnName = "nidGrado"),
                 @JoinColumn(name = "nidNivel", referencedColumnName = "nidNivel")
        })
    private GradoNivel gradoNivel;
    @OneToMany(mappedBy = "aula", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Main> mainLista;
    @Column(name = "flg_acti")
    private int flgActi;
    
    public Aula() {
    }

    public Aula(String descripcionAula, int nidAula, GradoNivel gradoNivel, Sede sede) {
        this.descripcionAula = descripcionAula;
        this.nidAula = nidAula;
        this.gradoNivel = gradoNivel;
        this.sede = sede;
    }


    public String getDescripcionAula() {
        return descripcionAula;
    }

    public void setDescripcionAula(String descripcionAula) {
        this.descripcionAula = descripcionAula;
    }

    public int getNidAula() {
        return nidAula;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }


    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public GradoNivel getGradoNivel() {
        return gradoNivel;
    }

    public void setGradoNivel(GradoNivel gradoNivel) {
        this.gradoNivel = gradoNivel;
    }

    public List<Main> getMainLista() {
        return mainLista;
    }

    public void setMainLista(List<Main> mainLista) {
        this.mainLista = mainLista;
    }

    public Main addMain(Main main) {
        getMainLista().add(main);
        main.setAula(this);
        return main;
    }

    public Main removeMain(Main main) {
        getMainLista().remove(main);
        main.setAula(null);
        return main;
    }

    public void setFlgActi(int flgActi) {
        this.flgActi = flgActi;
    }

    public int getFlgActi() {
        return flgActi;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionAula=");
        buffer.append(getDescripcionAula());
        buffer.append(',');
        buffer.append("nidAula=");
        buffer.append(getNidAula());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
