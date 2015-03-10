package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Time;

import sped.negocio.entidades.sist.RestriccionHorario;

public class BeanMain implements Serializable {
    @SuppressWarnings("compatibility:6865430999452328518")
    private static final long serialVersionUID = 1L;

    private String dia;
    private String estado;
    private Time horaFin;
    private Time horaInicio;
    private int nidMain;
    private BeanAula aula;
    private BeanCurso curso;
    private BeanProfesor profesor;
    private int nDia;
    private int nidLecc;
    ////////////////
    private int nidLeccRef;
    private String dniProfesor;
    private int nidAula;
    private int nidCurso;
    private int nroHoras;
    private int nroHoras_aux;
    private int nroHorasReal;
    private String nombreProfesor;
    private String nombreProfesorAula;
    private String nombreCurso;
    private String nombreArea;
    private String nombreAula;
    private String tipoFicha;
    private String color;
    private String color_prof;
    private boolean selec;
    private BeanRestriccionHorario restric;
    private int nidSede;
    private int nidNivel;

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

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setSelec(boolean selec) {
        this.selec = selec;
    }

    public boolean isSelec() {
        return selec;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNroHoras_aux(int nroHoras_aux) {
        this.nroHoras_aux = nroHoras_aux;
    }

    public int getNroHoras_aux() {
        return nroHoras_aux;
    }

    public void setNroHorasReal(int nroHorasReal) {
        this.nroHorasReal = nroHorasReal;
    }

    public int getNroHorasReal() {
        return nroHorasReal;
    }

    public void setRestric(BeanRestriccionHorario restric) {
        this.restric = restric;
    }

    public BeanRestriccionHorario getRestric() {
        return restric;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNombreProfesorAula(String nombreProfesorAula) {
        this.nombreProfesorAula = nombreProfesorAula;
    }

    public String getNombreProfesorAula() {
        return nombreProfesorAula;
    }

    public void setColor_prof(String color_prof) {
        this.color_prof = color_prof;
    }

    public String getColor_prof() {
        return color_prof;
    }

    public void setNidLecc(int nidLecc) {
        this.nidLecc = nidLecc;
    }

    public int getNidLecc() {
        return nidLecc;
    }

    public void setNidLeccRef(int nidLeccRef) {
        this.nidLeccRef = nidLeccRef;
    }

    public int getNidLeccRef() {
        return nidLeccRef;
    }
}
