package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.Date;

import sped.negocio.entidades.admin.Sede;

public class BeanFiltrosGraficos implements Serializable {
    @SuppressWarnings("compatibility:-7492010161869227280")
    private static final long serialVersionUID = 1L;
    
    //Filtros para Query//
    public String nidSede;
    public String nidArea;
    public String nidNivele;
    public String nidCurso;
    public String nidGrado;
    public String dniDocente;
    public String nidCriterio;
    public Date fechaInicio;
    public Date fechaFin;
    
    //Filtros para Tabla//
    public BeanSede nombreSede;
    public BeanAreaAcademica nombreArea;
    public BeanNivel nombreNivel;
    public BeanCurso nombreCurso;
    public BeanGrado nombreGrado;
    public BeanProfesor nombreProfesor;
    public BeanCriterio nombreCriterio;
        
    //Campo a Mostrar en tabla
    public String campoFiltroTrabla;
    
    //index
    public int index;
    
    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
    }

    public void setNidNivele(String nidNivele) {
        this.nidNivele = nidNivele;
    }

    public String getNidNivele() {
        return nidNivele;
    }

    public void setNidCurso(String nidCurso) {
        this.nidCurso = nidCurso;
    }

    public String getNidCurso() {
        return nidCurso;
    }

    public void setNidGrado(String nidGrado) {
        this.nidGrado = nidGrado;
    }

    public String getNidGrado() {
        return nidGrado;
    }

    public void setDniDocente(String dniDocente) {
        this.dniDocente = dniDocente;
    }

    public String getDniDocente() {
        return dniDocente;
    }

    public void setNidCriterio(String nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public String getNidCriterio() {
        return nidCriterio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setNombreSede(BeanSede nombreSede) {
        this.nombreSede = nombreSede;
    }

    public BeanSede getNombreSede() {
        return nombreSede;
    }

    public void setNombreArea(BeanAreaAcademica nombreArea) {
        this.nombreArea = nombreArea;
    }

    public BeanAreaAcademica getNombreArea() {
        return nombreArea;
    }

    public void setNombreNivel(BeanNivel nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public BeanNivel getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreCurso(BeanCurso nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public BeanCurso getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreGrado(BeanGrado nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public BeanGrado getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreProfesor(BeanProfesor nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public BeanProfesor getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreCriterio(BeanCriterio nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
    }

    public BeanCriterio getNombreCriterio() {
        return nombreCriterio;
    }

    public void setCampoFiltroTrabla(String campoFiltroTrabla) {
        this.campoFiltroTrabla = campoFiltroTrabla;
    }

    public String getCampoFiltroTrabla() {
        return campoFiltroTrabla;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
