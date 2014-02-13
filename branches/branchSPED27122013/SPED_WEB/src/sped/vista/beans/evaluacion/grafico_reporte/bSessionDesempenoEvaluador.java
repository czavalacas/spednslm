package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class bSessionDesempenoEvaluador implements Serializable {
    @SuppressWarnings("compatibility:-8661660304648975906")
    private static final long serialVersionUID = 1L;
    private int exec;
    private List lstRol;
    private List lstSede;
    private List lstArea;
    private List lstEvaArea;
    private List lstEvaSede;
    private List lstEvaGeneral;
    private List selectedRol;
    private List selectedEvaluador;
    private List selectedSede;
    private List selectedArea;
    private Date fechaPI;
    private Date fechaPF;
    private Date fechaEI;
    private Date fechaEF;

    public bSessionDesempenoEvaluador() {
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

    public void setLstRol(List lstRol) {
        this.lstRol = lstRol;
    }

    public List getLstRol() {
        return lstRol;
    }

    public void setLstEvaArea(List lstEvaArea) {
        this.lstEvaArea = lstEvaArea;
    }

    public List getLstEvaArea() {
        return lstEvaArea;
    }

    public void setLstEvaSede(List lstEvaSede) {
        this.lstEvaSede = lstEvaSede;
    }

    public List getLstEvaSede() {
        return lstEvaSede;
    }

    public void setLstEvaGeneral(List lstEvaGeneral) {
        this.lstEvaGeneral = lstEvaGeneral;
    }

    public List getLstEvaGeneral() {
        return lstEvaGeneral;
    }

    public void setSelectedRol(List selectedRol) {
        this.selectedRol = selectedRol;
    }

    public List getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedEvaluador(List selectedEvaluador) {
        this.selectedEvaluador = selectedEvaluador;
    }

    public List getSelectedEvaluador() {
        return selectedEvaluador;
    }

    public void setFechaPI(Date fechaPI) {
        this.fechaPI = fechaPI;
    }

    public Date getFechaPI() {
        return fechaPI;
    }

    public void setFechaPF(Date fechaPF) {
        this.fechaPF = fechaPF;
    }

    public Date getFechaPF() {
        return fechaPF;
    }

    public void setFechaEI(Date fechaEI) {
        this.fechaEI = fechaEI;
    }

    public Date getFechaEI() {
        return fechaEI;
    }

    public void setFechaEF(Date fechaEF) {
        this.fechaEF = fechaEF;
    }

    public Date getFechaEF() {
        return fechaEF;
    }
}
