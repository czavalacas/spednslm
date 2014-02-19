package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.List;

public class bSessionDesempenoProfesor implements Serializable {
    
    public List listaValoresPieGraph;
    public List nidSedes;
    public List nidAreas;
    public List nidNiveles;
    
    public bSessionDesempenoProfesor() {
    }


    public void setListaValoresPieGraph(List listaValoresPieGraph) {
        this.listaValoresPieGraph = listaValoresPieGraph;
    }

    public List getListaValoresPieGraph() {
        return listaValoresPieGraph;
    }

    public void setNidSedes(List nidSedes) {
        this.nidSedes = nidSedes;
    }

    public List getNidSedes() {
        return nidSedes;
    }

    public void setNidAreas(List nidAreas) {
        this.nidAreas = nidAreas;
    }

    public List getNidAreas() {
        return nidAreas;
    }

    public void setNidNiveles(List nidNiveles) {
        this.nidNiveles = nidNiveles;
    }

    public List getNidNiveles() {
        return nidNiveles;
    }

}
