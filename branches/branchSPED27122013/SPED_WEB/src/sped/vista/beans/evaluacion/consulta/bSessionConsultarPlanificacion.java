package sped.vista.beans.evaluacion.consulta;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionPlani;

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
    private List<BeanEvaluacionPlani> listaPlanificaciones=new ArrayList<BeanEvaluacionPlani>();
    private boolean estadoChoiceEvaluador=false;
    private boolean estadoChoiceSede=false;
    private boolean estadoChoiceArea=false;
    private String dniProfesor;
    private String fechaHoy;
    private List listaProblemas;
    private String nidProblema;
    private int nidEvaluacion;
    private int i_nidProblema;
    private BeanEvaluacionPlani evaSelect;
    private boolean columnProfesor = true;
    private String descripcionProblema;
    
    public bSessionConsultarPlanificacion() {
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setColumnProfesor(boolean columnProfesor) {
        this.columnProfesor = columnProfesor;
    }

    public boolean isColumnProfesor() {
        return columnProfesor;
    }


    public void setEvaSelect(BeanEvaluacionPlani evaSelect) {
        this.evaSelect = evaSelect;
    }

    public BeanEvaluacionPlani getEvaSelect() {
        return evaSelect;
    }

    public void setListaProblemas(List listaProblemas) {
        this.listaProblemas = listaProblemas;
    }

    public List getListaProblemas() {
        return listaProblemas;
    }

    public void setNidProblema(String nidProblema) {
        this.nidProblema = nidProblema;
    }

    public String getNidProblema() {
        return nidProblema;
    }

    public void setNidEvaluacion(int nidEvaluacion) {
        this.nidEvaluacion = nidEvaluacion;
    }

    public int getNidEvaluacion() {
        return nidEvaluacion;
    }

    public void setI_nidProblema(int i_nidProblema) {
        this.i_nidProblema = i_nidProblema;
    }

    public int getI_nidProblema() {
        return i_nidProblema;
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


    public void setListaPlanificaciones(List<BeanEvaluacionPlani> listaPlanificaciones) {
        this.listaPlanificaciones = listaPlanificaciones;
    }

    public List<BeanEvaluacionPlani> getListaPlanificaciones() {
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

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }
}
