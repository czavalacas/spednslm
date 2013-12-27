package sped.negocio.entidades.eval;

import java.io.Serializable;

import java.sql.Timestamp;

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

import sped.negocio.entidades.admin.Main;

@Entity
@NamedQueries({ @NamedQuery(name = "Evaluacion.findAll", query = "select o from Evaluacion o") })
@Table(name = "\"evmeval\"")
public class Evaluacion implements Serializable {
    private static final long serialVersionUID = 240657949826477782L;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "end_Date", nullable = false)
    private Timestamp endDate;
    @Column(name = "estado_evaluacion")
    private String estadoEvaluacion;
    @Column(name = "nidDate", nullable = false)
    private String nidDate;
    @Id
    @Column(name = "nidEvaluacion", nullable = false)
    private int nidEvaluacion;
    @Column(name = "nid_evaluador", nullable = false)
    private int nidEvaluador;
    @Column(name = "start_Date", nullable = false)
    private Timestamp startDate;
    @ManyToOne
    @JoinColumn(name = "nidMain")
    private Main main;
    @OneToMany(mappedBy = "evaluacion", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Resultado> resultadoLista;

    public Evaluacion() {
    }

    public Evaluacion(String descripcion, Timestamp endDate, String estadoEvaluacion, String nidDate, int nidEvaluacion,
                      int nidEvaluador, Main main, Timestamp startDate) {
        this.descripcion = descripcion;
        this.endDate = endDate;
        this.estadoEvaluacion = estadoEvaluacion;
        this.nidDate = nidDate;
        this.nidEvaluacion = nidEvaluacion;
        this.nidEvaluador = nidEvaluador;
        this.main = main;
        this.startDate = startDate;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getNidDate() {
        return nidDate;
    }

    public void setNidDate(String nidDate) {
        this.nidDate = nidDate;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidEvaluador() {
        return nidEvaluador;
    }

    public void setNidEvaluador(int nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Resultado> getResultadoLista() {
        return resultadoLista;
    }

    public void setResultadoLista(List<Resultado> resultadoLista) {
        this.resultadoLista = resultadoLista;
    }

    public Resultado addResultado(Resultado resultado) {
        getResultadoLista().add(resultado);
        resultado.setEvaluacion(this);
        return resultado;
    }

    public Resultado removeResultado(Resultado resultado) {
        getResultadoLista().remove(resultado);
        resultado.setEvaluacion(null);
        return resultado;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("descripcion=");
        buffer.append(getDescripcion());
        buffer.append(',');
        buffer.append("endDate=");
        buffer.append(getEndDate());
        buffer.append(',');
        buffer.append("estadoEvaluacion=");
        buffer.append(getEstadoEvaluacion());
        buffer.append(',');
        buffer.append("nidDate=");
        buffer.append(getNidDate());
        buffer.append(',');
        buffer.append("nidEvaluacion=");
        buffer.append(getNidEvaluacion());
        buffer.append(',');
        buffer.append("nidEvaluador=");
        buffer.append(getNidEvaluador());
        buffer.append(',');
        buffer.append("startDate=");
        buffer.append(getStartDate());
        buffer.append(']');
        return buffer.toString();
    }
}
