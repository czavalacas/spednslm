package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class bSessionDesempenoProfesor implements Serializable {
    @SuppressWarnings("compatibility:-8661660304648975906")
    private static final long serialVersionUID = 1L;
    private int exec;
    private List lstSede;
    private List lstArea;
    private List selectedSede;
    private List selectedArea;    

    public bSessionDesempenoProfesor() {
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstSede(List lstSede) {
        this.lstSede = lstSede;
    }

    public List getLstSede() {
        return lstSede;
    }

    public void setSelectedSede(List selectedSede) {
        this.selectedSede = selectedSede;
    }

    public List getSelectedSede() {
        return selectedSede;
    }

    public void setLstArea(List lstArea) {
        this.lstArea = lstArea;
    }

    public List getLstArea() {
        return lstArea;
    }

    public void setSelectedArea(List selectedArea) {
        this.selectedArea = selectedArea;
    }

    public List getSelectedArea() {
        return selectedArea;
    }
}
