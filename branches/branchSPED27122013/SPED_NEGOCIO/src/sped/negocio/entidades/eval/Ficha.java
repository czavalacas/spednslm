package sped.negocio.entidades.eval;

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
@NamedQueries({ @NamedQuery(name = "Ficha.findAll", query = "select o from Ficha o") })
@Table(name = "\"evmfich\"")
public class Ficha implements Serializable {
    private static final long serialVersionUID = -6284619839469264073L;
    @Column(name = "desc_version")
    private String descripcionVersion;
    @Column(name = "estado_ficha")
    private String estadoFicha;
    @Id
    @Column(name = "nidFicha", nullable = false)
    private int nidFicha;
    @Column(name = "tipo_ficha")
    private String tipoFicha;
    @Column(name = "tipo_ficha_curso")
    private String tipoFichaCurso;
    @OneToMany(mappedBy = "ficha", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FichaValor> fichaValorLista;
    @OneToMany(mappedBy = "ficha", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FichaCriterio> fichaCriterioLista;

    public Ficha() {
    }

    public Ficha(String descripcionVersion, String estadoFicha, int nidFicha, String tipoFicha, String tipoFichaCurso) {
        this.descripcionVersion = descripcionVersion;
        this.estadoFicha = estadoFicha;
        this.nidFicha = nidFicha;
        this.tipoFicha = tipoFicha;
        this.tipoFichaCurso = tipoFichaCurso;
    }


    public String getDescripcionVersion() {
        return descripcionVersion;
    }

    public void setDescripcionVersion(String descripcionVersion) {
        this.descripcionVersion = descripcionVersion;
    }

    public String getEstadoFicha() {
        return estadoFicha;
    }

    public void setEstadoFicha(String estadoFicha) {
        this.estadoFicha = estadoFicha;
    }

    public int getNidFicha() {
        return nidFicha;
    }

    public void setNidFicha(int nidFicha) {
        this.nidFicha = nidFicha;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getTipoFichaCurso() {
        return tipoFichaCurso;
    }

    public void setTipoFichaCurso(String tipoFichaCurso) {
        this.tipoFichaCurso = tipoFichaCurso;
    }

    public List<FichaValor> getFichaValorLista() {
        return fichaValorLista;
    }

    public void setFichaValorLista(List<FichaValor> fichaValorLista) {
        this.fichaValorLista = fichaValorLista;
    }

    public FichaValor addFichaValor(FichaValor fichaValor) {
        getFichaValorLista().add(fichaValor);
        fichaValor.setFicha(this);
        return fichaValor;
    }

    public FichaValor removeFichaValor(FichaValor fichaValor) {
        getFichaValorLista().remove(fichaValor);
        fichaValor.setFicha(null);
        return fichaValor;
    }

    public List<FichaCriterio> getFichaCriterioLista() {
        return fichaCriterioLista;
    }

    public void setFichaCriterioLista(List<FichaCriterio> fichaCriterioLista) {
        this.fichaCriterioLista = fichaCriterioLista;
    }

    public FichaCriterio addFichaCriterio(FichaCriterio fichaCriterio) {
        getFichaCriterioLista().add(fichaCriterio);
        fichaCriterio.setFicha(this);
        return fichaCriterio;
    }

    public FichaCriterio removeFichaCriterio(FichaCriterio fichaCriterio) {
        getFichaCriterioLista().remove(fichaCriterio);
        fichaCriterio.setFicha(null);
        return fichaCriterio;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcionVersion=");
        buffer.append(getDescripcionVersion());
        buffer.append(',');
        buffer.append("estadoFicha=");
        buffer.append(getEstadoFicha());
        buffer.append(',');
        buffer.append("nidFicha=");
        buffer.append(getNidFicha());
        buffer.append(',');
        buffer.append("tipoFicha=");
        buffer.append(getTipoFicha());
        buffer.append(',');
        buffer.append("tipoFichaCurso=");
        buffer.append(getTipoFichaCurso());
        buffer.append(']');
        return buffer.toString();
    }
}
