package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

public class BeanMainWS implements Serializable {
    @SuppressWarnings("compatibility:2420317677722093689")
    private static final long serialVersionUID = 1L;

    private int nidMain;
    private String profesor;
    private String curso;
    private String sede;
    private String aula;
    private Date horaFin;
    private Date horaInicio;
    private String dia;
    private String gradoNivel;
    private String areaAcademica;
    private Integer nidSede;
    /** Carga nueva dfloresgonz 24.04.2015 */
    private String dniProfesor;
    private Integer nidAula;
    private Integer nidCurso;
    
    public BeanMainWS(){
        
    }

    public BeanMainWS(int nidMain,
                      String apellidos,
                      String nombres,
                      String curso,
                      String sede,
                      String aula,
                      Date horaFin,
                      Date horaInicio,
                      String dia,
                      String grado,
                      String nivel,
                      String areaAcademica,
                      Integer nidSede){
        this.nidMain = nidMain;
        this.profesor = apellidos+" "+nombres;
        this.curso = curso;
        this.sede = sede;
        this.aula = aula;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.dia = dia;
        this.gradoNivel = grado+ " de "+nivel;
        this.areaAcademica = areaAcademica;
        this.nidSede = nidSede;
    }
    
    public BeanMainWS(int nidMain,
                      String apellidos,
                      String nombres,
                      String curso,
                      String sede,
                      String aula,
                      String grado,
                      String nivel,
                      String areaAcademica,
                      Integer nidSede,
                      String dniProfesor,
                      Integer nidAula,
                      Integer nidCurso){
        this.nidMain = nidMain;
        this.dniProfesor = dniProfesor;
        this.profesor = apellidos+" "+nombres;
        this.curso = curso;
        this.nidCurso = nidCurso;
        this.nidSede = nidSede;
        this.sede = sede;
        this.nidAula = nidAula;
        this.aula = aula;
        this.gradoNivel = grado+ " de "+nivel;
        this.areaAcademica = areaAcademica;
    }

    public void setNidSede(Integer nidSede) {
        this.nidSede = nidSede;
    }

    public Integer getNidSede() {
        return nidSede;
    }

    public void setNidMain(int nidMain) {
        this.nidMain = nidMain;
    }

    public int getNidMain() {
        return nidMain;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
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

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }
    
    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setGradoNivel(String gradoNivel) {
        this.gradoNivel = gradoNivel;
    }

    public String getGradoNivel() {
        return gradoNivel;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setNidAula(Integer nidAula) {
        this.nidAula = nidAula;
    }

    public Integer getNidAula() {
        return nidAula;
    }

    public void setNidCurso(Integer nidCurso) {
        this.nidCurso = nidCurso;
    }

    public Integer getNidCurso() {
        return nidCurso;
    }
}
