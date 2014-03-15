package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;

public class BeanNotificacionEvaluacion implements Serializable {
    @SuppressWarnings("compatibility:-7596078726604079139")
    private static final long serialVersionUID = 1L;
    
    private Integer nidEvaluacion;
    private String versionFicha;
    private Integer nidFicha;
    private Timestamp fechaEvaluacion;
    private String docente;
    private String nidDocente;
    private String curso;
    private String sede;
    private String area;
    private String aula;
    private String evaluador;
    private String indicador;
    private String criterio;
    private short puntaje;
    private double puntaje20;
    private String inlineStiloPuntaje;
    private String inlineStiloPuntaje20;
    private String cidNotificacion;
    
    public BeanNotificacionEvaluacion(){
        
    }
    
    public BeanNotificacionEvaluacion(Integer nidEvaluacion,
                                      String versionFicha,
                                      Integer nidFicha,
                                      Timestamp fechaEvaluacion,
                                      String apellidos,
                                      String nombres,
                                      String nidDocente,
                                      String curso,
                                      String sede,
                                      String area,
                                      String aula,
                                      Object evaluador,
                                      String indicador,
                                      String criterio,
                                      short puntaje,
                                      double puntaje20,
                                      String cidNotificacion){
        this.nidEvaluacion = nidEvaluacion;
        this.versionFicha = versionFicha;
        this.nidFicha = nidFicha;
        this.fechaEvaluacion = fechaEvaluacion;
        this.docente = apellidos+" "+nombres;
        this.nidDocente = nidDocente;
        this.curso = curso;
        this.sede = sede;
        this.area = area;
        this.aula = aula;
        this.evaluador = (String)evaluador;
        this.indicador = indicador;
        this.criterio = criterio;
        this.puntaje = puntaje;
        this.puntaje20 = puntaje20;
        this.cidNotificacion = cidNotificacion;
        if(puntaje20 <= 10.49){
            this.setInlineStiloPuntaje("background-color:red; color:white; font-weight:bold;text-align:center;");
            this.setInlineStiloPuntaje20("background-color:red; color:white; font-weight:bold;text-align:center;");
        }
        if(puntaje20 >= 17.00){
            this.setInlineStiloPuntaje("background-color:green; color:white; font-weight:bold;text-align:center;");
            this.setInlineStiloPuntaje20("background-color:green; color:white; font-weight:bold;text-align:center;");
        }
    }

    public void setCidNotificacion(String cidNotificacion) {
        this.cidNotificacion = cidNotificacion;
    }

    public String getCidNotificacion() {
        return cidNotificacion;
    }

    public void setInlineStiloPuntaje(String inlineStiloPuntaje) {
        this.inlineStiloPuntaje = inlineStiloPuntaje;
    }

    public String getInlineStiloPuntaje() {
        return inlineStiloPuntaje;
    }

    public void setInlineStiloPuntaje20(String inlineStiloPuntaje20) {
        this.inlineStiloPuntaje20 = inlineStiloPuntaje20;
    }

    public String getInlineStiloPuntaje20() {
        return inlineStiloPuntaje20;
    }

    public void setNidEvaluacion(Integer nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public Integer getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setVersionFicha(String versionFicha) {
        this.versionFicha = versionFicha;
    }

    public String getVersionFicha() {
        return versionFicha;
    }

    public void setNidFicha(Integer nidFicha) {
        this.nidFicha = nidFicha;
    }

    public Integer getNidFicha() {
        return nidFicha;
    }

    public void setFechaEvaluacion(Timestamp fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Timestamp getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getDocente() {
        return docente;
    }

    public void setNidDocente(String nidDocente) {
        this.nidDocente = nidDocente;
    }

    public String getNidDocente() {
        return nidDocente;
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

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    public String getEvaluador() {
        return evaluador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setPuntaje(short puntaje) {
        this.puntaje = puntaje;
    }

    public short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje20(double puntaje20) {
        this.puntaje20 = puntaje20;
    }

    public double getPuntaje20() {
        return puntaje20;
    }
}