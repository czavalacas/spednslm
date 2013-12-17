package test.entidades;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries( { @NamedQuery(name = "Evmeval.findAll", query = "select o from Evmeval o") })
@Table(name = "evmeval")
public class Evmeval implements Serializable {
    @Column(name = "estado_evaluacion")
    private String estado_evaluacion;
    @Column(name = "fecha_hora")
    private Timestamp fecha_hora;
    @Id
    @Column(name = "nidEvaluacion", nullable = false)
    @TableGenerator( name = "stmcodi", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "evmeval.nidEvaluacion", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi" )
    private int nidEvaluacion;
    @Column(name = "nidMain", nullable = false)
    private int nidMain;

    public Evmeval() {
    }

    public Evmeval(String estado_evaluacion, Timestamp fecha_hora, int nidEvaluacion, int nidMain) {
        this.estado_evaluacion = estado_evaluacion;
        this.fecha_hora = fecha_hora;
        this.nidEvaluacion = nidEvaluacion;
        this.nidMain = nidMain;
    }

    public String getEstado_evaluacion() {
        return estado_evaluacion;
    }

    public void setEstado_evaluacion(String estado_evaluacion) {
        this.estado_evaluacion = estado_evaluacion;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidMain() {
        return nidMain;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }
}
