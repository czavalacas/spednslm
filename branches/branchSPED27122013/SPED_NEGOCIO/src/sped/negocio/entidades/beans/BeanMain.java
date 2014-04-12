package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

import java.util.Date;
import java.util.List;

import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Profesor;

public class BeanMain implements Serializable {
    @SuppressWarnings("compatibility:-8366719624247402844")
    private static final long serialVersionUID = 1L;
    
    private String dia;
    private String estado;
    private Time horaFin;
    private Time horaInicio;
    private int nidMain;
    private List<BeanEvaluacion> evaluacionLista;
    private BeanAula aula;
    private BeanCurso curso;
    private BeanProfesor profesor;
    private int nDia;
    ////////////////
    private String dniProfesor;
    private int nidAula;
    private int nidCurso;
    private int nroHoras;
    private String nombreProfesor;
    private String nombreCurso;
    private String nombreArea;
    private String tipoFicha;

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

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraInicio() {
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

    public void setNDia(int nDia) {
        this.nDia = nDia;
    }

    public int getNDia() {
        return nDia;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }

    public int getNidAula() {
        return nidAula;
    }

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public int getNidCurso() {
        return nidCurso;
    }

    public void setNroHoras(int nroHoras) {
        this.nroHoras = nroHoras;
    }

    public int getNroHoras() {
        return nroHoras;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }
}
