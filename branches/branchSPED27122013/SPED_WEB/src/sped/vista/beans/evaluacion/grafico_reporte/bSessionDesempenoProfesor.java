package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sped.negocio.entidades.beans.BeanFiltrosGraficos;
import sped.negocio.entidades.beans.BeanIndicador;

public class bSessionDesempenoProfesor implements Serializable {
    

    public String nidSede;
    public String nidArea;
    public String nidNivele;
    public String nidCurso;
    public String nidGrado;
    public String dniDocente;
    public String nidCriterio;
    public String nidIndicador;
    public Date fechaInicio;
    public Date fechaFin;
    public List<BeanFiltrosGraficos> listaFiltros=new ArrayList<BeanFiltrosGraficos>();
    public BeanFiltrosGraficos beanFiltros=new BeanFiltrosGraficos();
    public BeanIndicador beanIndicador=new BeanIndicador();
    public int exec=0;
    
    private List listaSedesFiltro;
    private List listaAreasFiltro;
    private List listaNivelesFiltro;
    private List listaCursosFiltro;
    private List listaGradosFiltro;
    private List listaProfesoresFiltro;
    private List listaCriteriosFiltro;
    
    private List<BeanIndicador> listaIndicadoresFiltro=new ArrayList<BeanIndicador>();
    private boolean estadoTablaIndicadores=false;
    
    private transient List<Object[]> lstEvaBarChart;
    private transient List<Object[]> lstEvaAreasBarChart;
    private transient List<Object[]> lstEvaDocenteIndicadorBarChart;
    private transient List<Object[]> lstEvaLineGraph;
    private transient List<Object[]> lstEvaLineGlobalGraph;
    private transient List<Object[]> lstEvaDocenteEvaluacionBarChart;
    
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

    public void setBeanFiltros(BeanFiltrosGraficos beanFiltros) {
        this.beanFiltros = beanFiltros;
    }

    public BeanFiltrosGraficos getBeanFiltros() {
        return beanFiltros;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setListaSedesFiltro(List listaSedesFiltro) {
        this.listaSedesFiltro = listaSedesFiltro;
    }

    public List getListaSedesFiltro() {
        return listaSedesFiltro;
    }

    public void setListaAreasFiltro(List listaAreasFiltro) {
        this.listaAreasFiltro = listaAreasFiltro;
    }

    public List getListaAreasFiltro() {
        return listaAreasFiltro;
    }

    public void setListaNivelesFiltro(List listaNivelesFiltro) {
        this.listaNivelesFiltro = listaNivelesFiltro;
    }

    public List getListaNivelesFiltro() {
        return listaNivelesFiltro;
    }

    public void setListaCursosFiltro(List listaCursosFiltro) {
        this.listaCursosFiltro = listaCursosFiltro;
    }

    public List getListaCursosFiltro() {
        return listaCursosFiltro;
    }

    public void setListaGradosFiltro(List listaGradosFiltro) {
        this.listaGradosFiltro = listaGradosFiltro;
    }

    public List getListaGradosFiltro() {
        return listaGradosFiltro;
    }

    public void setListaProfesoresFiltro(List listaProfesoresFiltro) {
        this.listaProfesoresFiltro = listaProfesoresFiltro;
    }

    public List getListaProfesoresFiltro() {
        return listaProfesoresFiltro;
    }

    public void setListaCriteriosFiltro(List listaCriteriosFiltro) {
        this.listaCriteriosFiltro = listaCriteriosFiltro;
    }

    public List getListaCriteriosFiltro() {
        return listaCriteriosFiltro;
    }

    public void setListaIndicadoresFiltro(List<BeanIndicador> listaIndicadoresFiltro) {
        this.listaIndicadoresFiltro = listaIndicadoresFiltro;
    }

    public List<BeanIndicador> getListaIndicadoresFiltro() {
        return listaIndicadoresFiltro;
    }

    public void setEstadoTablaIndicadores(boolean estadoTablaIndicadores) {
        this.estadoTablaIndicadores = estadoTablaIndicadores;
    }

    public boolean isEstadoTablaIndicadores() {
        return estadoTablaIndicadores;
    }

    public void setNidIndicador(String nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public String getNidIndicador() {
        return nidIndicador;
    }

    public void setBeanIndicador(BeanIndicador beanIndicador) {
        this.beanIndicador = beanIndicador;
    }

    public BeanIndicador getBeanIndicador() {
        return beanIndicador;
    }

    public void setLstEvaBarChart(List<Object[]> lstEvaBarChart) {
        this.lstEvaBarChart = lstEvaBarChart;
    }

    public List<Object[]> getLstEvaBarChart() {
        return lstEvaBarChart;
    }

    public void setLstEvaAreasBarChart(List<Object[]> lstEvaAreasBarChart) {
        this.lstEvaAreasBarChart = lstEvaAreasBarChart;
    }

    public List<Object[]> getLstEvaAreasBarChart() {
        return lstEvaAreasBarChart;
    }

    public void setLstEvaDocenteIndicadorBarChart(List<Object[]> lstEvaDocenteIndicadorBarChart) {
        this.lstEvaDocenteIndicadorBarChart = lstEvaDocenteIndicadorBarChart;
    }

    public List<Object[]> getLstEvaDocenteIndicadorBarChart() {
        return lstEvaDocenteIndicadorBarChart;
    }

    public void setLstEvaLineGraph(List<Object[]> lstEvaLineGraph) {
        this.lstEvaLineGraph = lstEvaLineGraph;
    }

    public List<Object[]> getLstEvaLineGraph() {
        return lstEvaLineGraph;
    }

    public void setLstEvaLineGlobalGraph(List<Object[]> lstEvaLineGlobalGraph) {
        this.lstEvaLineGlobalGraph = lstEvaLineGlobalGraph;
    }

    public List<Object[]> getLstEvaLineGlobalGraph() {
        return lstEvaLineGlobalGraph;
    }

    public void setLstEvaDocenteEvaluacionBarChart(List<Object[]> lstEvaDocenteEvaluacionBarChart) {
        this.lstEvaDocenteEvaluacionBarChart = lstEvaDocenteEvaluacionBarChart;
    }

    public List<Object[]> getLstEvaDocenteEvaluacionBarChart() {
        return lstEvaDocenteEvaluacionBarChart;
    }
}
