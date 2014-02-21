package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sped.negocio.entidades.beans.BeanFiltrosGraficos;

public class bSessionDesempenoProfesor implements Serializable {
    

    public String nidSede;
    public String nidArea;
    public String nidNivele;
    public String nidCurso;
    public String nidGrado;
    public String dniDocente;
    public String nidCriterio;
    public Date fechaInicio;
    public Date fechaFin;
    public List<BeanFiltrosGraficos> listaFiltros=new ArrayList<BeanFiltrosGraficos>();
    
    public bSessionDesempenoProfesor() {
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


    public void setListaFiltros(List<BeanFiltrosGraficos> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public List<BeanFiltrosGraficos> getListaFiltros() {
        return listaFiltros;
    }
}
