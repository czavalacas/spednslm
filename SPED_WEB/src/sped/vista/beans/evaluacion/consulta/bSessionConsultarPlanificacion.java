package sped.vista.beans.evaluacion.consulta;

import java.io.Serializable;

import java.util.Date;

public class bSessionConsultarPlanificacion {
    private String nidEvaluadorChoice;
    private String nidSedeChoice;
    private String nidNivelChoice;
    private String apellidosDocente;
    private Date fechaMinPlanificacion;
    private Date fechaMaxPlanificacion;
    public bSessionConsultarPlanificacion() {
    }


    public void setNidEvaluadorChoice(String nidEvaluadorChoice) {
        this.nidEvaluadorChoice = nidEvaluadorChoice;
    }

    public void setFechaMinPlanificacion(Date fechaMinPlanificacion) {
        this.fechaMinPlanificacion = fechaMinPlanificacion;
    }

    public Date getFechaMinPlanificacion() {
        return fechaMinPlanificacion;
    }

    public void setFechaMaxPlanificacion(Date fechaMaxPlanificacion) {
        this.fechaMaxPlanificacion = fechaMaxPlanificacion;
    }

    public Date getFechaMaxPlanificacion() {
        return fechaMaxPlanificacion;
    }

    public String getNidEvaluadorChoice() {
        return nidEvaluadorChoice;
    }

    public void setNidSedeChoice(String nidSedeChoice) {
        this.nidSedeChoice = nidSedeChoice;
    }

    public String getNidSedeChoice() {
        return nidSedeChoice;
    }

    public void setNidNivelChoice(String nidNivelChoice) {
        this.nidNivelChoice = nidNivelChoice;
    }

    public String getNidNivelChoice() {
        return nidNivelChoice;
    }

    public void setApellidosDocente(String apellidosDocente) {
        this.apellidosDocente = apellidosDocente;
    }

    public String getApellidosDocente() {
        return apellidosDocente;
    }
}
