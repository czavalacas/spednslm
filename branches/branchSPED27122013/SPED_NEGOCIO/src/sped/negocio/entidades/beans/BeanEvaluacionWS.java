package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

public class BeanEvaluacionWS implements Serializable {
    @SuppressWarnings("compatibility:-5235138836280251799")
    private static final long serialVersionUID = 1L;

    private Integer nidEvaluacion;    
    private Integer nidEvaluador;
    private String profesor;
    private String evaluador;
    private String planificador;
    private String curso;
    private Date startDate;
    private String sede;
    private String areaAcademica;
    private String tipoVisita;
    private Date endDate;

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    public String getEvaluador() {
        return evaluador;
    }

    public void setPlanificador(String planificador) {
        this.planificador = planificador;
    }

    public String getPlanificador() {
        return planificador;
    }

    public void setNidEvaluacion(Integer nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public Integer getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setNidEvaluador(Integer nidEvaluador) {
        this.nidEvaluador = nidEvaluador;
    }

    public Integer getNidEvaluador() {
        return nidEvaluador;
    }
}
