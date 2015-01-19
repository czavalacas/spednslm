package sped.negocio.entidades.admin;

import java.io.Serializable;

import java.sql.Time;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.TableGenerator;

import sped.negocio.entidades.eval.Evaluacion;

@Entity
@NamedQueries({ @NamedQuery(name = "Main.findAll", query = "select o from Main o") })
@Table(name = "\"addmain\"")
public class Main implements Serializable {
    @SuppressWarnings("compatibility:108003898024212995")
    private static final long serialVersionUID = 3546028881634228951L;
    @Column(name = "dia")
    private String dia;
    @Column(name = "estado")
    private String estado;
    @Column(name = "hora_fin")
    private Time horaFin;
    @Column(name = "hora_ini")
    private Time horaInicio;
    @Id        
    @Column(name = "nidMain", nullable = false)
    @TableGenerator( name = "stmcodi_addmain", table = "stmcodi", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "addmain.nidMain", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "stmcodi_addmain" )
    private int nidMain;
    @OneToMany(mappedBy = "main", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Evaluacion> evaluacionLista;
    @ManyToOne
    @JoinColumn(name = "nidAula")
    private Aula aula;
    @ManyToOne
    @JoinColumn(name = "nidCurso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "dniProfesor")
    private Profesor profesor;
    @Column(name = "nDia")
    private int nDia;    
    @Column(name = "nidLeccion")
    private int nidLeccion;

    public Main() {
    }

    public Main(String dia, Profesor profesor, String estado, Time horaFin, Time horaInicio, Aula aula, Curso curso,
                int nidMain, String tipoFicha) {
        this.dia = dia;
        this.profesor = profesor;
        this.estado = estado;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.aula = aula;
        this.curso = curso;
        this.nidMain = nidMain;
    }


    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }


    public int getNidMain() {
        return nidMain;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }

    public List<Evaluacion> getEvaluacionLista() {
        return evaluacionLista;
    }

    public void setEvaluacionLista(List<Evaluacion> evaluacionLista) {
        this.evaluacionLista = evaluacionLista;
    }

    public Evaluacion addEvaluacion(Evaluacion evaluacion) {
        getEvaluacionLista().add(evaluacion);
        evaluacion.setMain(this);
        return evaluacion;
    }

    public Evaluacion removeEvaluacion(Evaluacion evaluacion) {
        getEvaluacionLista().remove(evaluacion);
        evaluacion.setMain(null);
        return evaluacion;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }

    public void setNidLeccion(int nidLeccion) {
        this.nidLeccion = nidLeccion;
    }

    public int getNidLeccion() {
        return nidLeccion;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("dia=");
        buffer.append(getDia());
        buffer.append(',');
        buffer.append("estado=");
        buffer.append(getEstado());
        buffer.append(',');
        buffer.append("horaFin=");
        buffer.append(getHoraFin());
        buffer.append(',');
        buffer.append("horaInicio=");
        buffer.append(getHoraInicio());
        buffer.append(',');
        buffer.append("nidMain=");
        buffer.append(getNidMain());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}