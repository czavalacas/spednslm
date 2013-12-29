package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import sped.negocio.entidades.admin.Aula;

public class BeanMain implements Serializable {
    @SuppressWarnings("compatibility:-8366719624247402844")
    private static final long serialVersionUID = 1L;
    
    private String dia;
    private String estado;
    private Date horaFin;
    private Date horaInicio;
    private int nidMain;
    private List<BeanEvaluacion> evaluacionLista;
    private BeanAula aula;
    private BeanCurso curso;
    private BeanProfesor profesor;

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }

    public int getNidMain() {
        return nidMain;
    }

    public void setEvaluacionLista(List<BeanEvaluacion> evaluacionLista) {
        this.evaluacionLista = evaluacionLista;
    }

    public List<BeanEvaluacion> getEvaluacionLista() {
        return evaluacionLista;
    }

    public void setAula(BeanAula aula) {
        this.aula = aula;
    }

    public BeanAula getAula() {
        return aula;
    }

    public void setCurso(BeanCurso curso) {
        this.curso = curso;
    }

    public BeanCurso getCurso() {
        return curso;
    }

    public void setProfesor(BeanProfesor profesor) {
        this.profesor = profesor;
    }

    public BeanProfesor getProfesor() {
        return profesor;
    }
}
