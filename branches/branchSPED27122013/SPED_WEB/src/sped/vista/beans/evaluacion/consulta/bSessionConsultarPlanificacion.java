package sped.vista.beans.evaluacion.consulta;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import sped.negocio.entidades.beans.BeanEvaluacion;

public class bSessionConsultarPlanificacion {
    private int exec=0;
    private String nidEvaluadorChoice;
    private String nidSedeChoice;
    private String nidNivelChoice;
    private String apellidosDocente;
    private Date fechaMinPlanificacion;
    private Date fechaMaxPlanificacion;
    private String nidAreaAcademicaChoice;
    private String nidEstadoPlanificacion;
    private List<BeanEvaluacion> listaPlanificaciones;
    private boolean estadoChoiceEvaluador=false;
    private boolean estadoChoiceSede=false;
    private boolean estadoChoiceArea=false;
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

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setListaPlanificaciones(List<BeanEvaluacion> listaPlanificaciones) {
        this.listaPlanificaciones = listaPlanificaciones;
    }

    public List<BeanEvaluacion> getListaPlanificaciones() {
        return listaPlanificaciones;
    }

    public void setEstadoChoiceEvaluador(boolean estadoChoiceEvaluador) {
        this.estadoChoiceEvaluador = estadoChoiceEvaluador;
    }

    public boolean isEstadoChoiceEvaluador() {
        return estadoChoiceEvaluador;
    }

    public void setEstadoChoiceSede(boolean estadoChoiceSede) {
        this.estadoChoiceSede = estadoChoiceSede;
    }

    public boolean isEstadoChoiceSede() {
        return estadoChoiceSede;
    }

    public void setEstadoChoiceArea(boolean estadoChoiceArea) {
        this.estadoChoiceArea = estadoChoiceArea;
    }

    public boolean isEstadoChoiceArea() {
        return estadoChoiceArea;
    }

    public void setNidAreaAcademicaChoice(String nidAreaAcademicaChoice) {
        this.nidAreaAcademicaChoice = nidAreaAcademicaChoice;
    }

    public String getNidAreaAcademicaChoice() {
        return nidAreaAcademicaChoice;
    }

    public void setNidEstadoPlanificacion(String nidEstadoPlanificacion) {
        this.nidEstadoPlanificacion = nidEstadoPlanificacion;
    }

    public String getNidEstadoPlanificacion() {
        return nidEstadoPlanificacion;
    }
}
