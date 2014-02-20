package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;

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
    private List selectedRol_aux;
    private List selectedEvaluador_aux;
    private List selectedSede_aux;
    private List selectedArea_aux;
    private Date fechaPI;
    private Date fechaPF;
    private Date fechaEI;
    private Date fechaEF;
    private Date fechaPI_axu;
    private Date fechaPF_aux;
    private Date fechaEI_aux;
    private Date fechaEF_aux;
    private List<BeanEvaluacion> lstEvaTable;
    private List<BeanEvaluacion> lstEvaDetalle;
    private transient List<Object[]> lstEvaBarChart;
    private transient List<Object[]> lstEvaBarChartRol;
    private transient List<Object[]> lstEvaLineG;
    private transient List<Object[]> lstEvaPieG;
    private BeanUsuario evaluador;
    private String estado;
    private boolean renderSede;
    private boolean renderNivel;
    private boolean renderArea;
    
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

    public void setLstEvaTable(List<BeanEvaluacion> lstEvaTable) {
        this.lstEvaTable = lstEvaTable;
    }

    public List<BeanEvaluacion> getLstEvaTable() {
        return lstEvaTable;
    }

    public void setSelectedRol_aux(List selectedRol_aux) {
        this.selectedRol_aux = selectedRol_aux;
    }

    public List getSelectedRol_aux() {
        return selectedRol_aux;
    }

    public void setSelectedEvaluador_aux(List selectedEvaluador_aux) {
        this.selectedEvaluador_aux = selectedEvaluador_aux;
    }

    public List getSelectedEvaluador_aux() {
        return selectedEvaluador_aux;
    }

    public void setSelectedSede_aux(List selectedSede_aux) {
        this.selectedSede_aux = selectedSede_aux;
    }

    public List getSelectedSede_aux() {
        return selectedSede_aux;
    }

    public void setSelectedArea_aux(List selectedArea_aux) {
        this.selectedArea_aux = selectedArea_aux;
    }

    public List getSelectedArea_aux() {
        return selectedArea_aux;
    }

    public void setFechaPI_axu(Date fechaPI_axu) {
        this.fechaPI_axu = fechaPI_axu;
    }

    public Date getFechaPI_axu() {
        return fechaPI_axu;
    }

    public void setFechaPF_aux(Date fechaPF_aux) {
        this.fechaPF_aux = fechaPF_aux;
    }

    public Date getFechaPF_aux() {
        return fechaPF_aux;
    }

    public void setFechaEI_aux(Date fechaEI_aux) {
        this.fechaEI_aux = fechaEI_aux;
    }

    public Date getFechaEI_aux() {
        return fechaEI_aux;
    }

    public void setFechaEF_aux(Date fechaEF_aux) {
        this.fechaEF_aux = fechaEF_aux;
    }

    public Date getFechaEF_aux() {
        return fechaEF_aux;
    }

    public void setLstEvaDetalle(List<BeanEvaluacion> lstEvaDetalle) {
        this.lstEvaDetalle = lstEvaDetalle;
    }

    public List<BeanEvaluacion> getLstEvaDetalle() {
        return lstEvaDetalle;
    }

    public void setLstEvaBarChart(List<Object[]> lstEvaBarChart) {
        this.lstEvaBarChart = lstEvaBarChart;
    }

    public List<Object[]> getLstEvaBarChart() {
        return lstEvaBarChart;
    }

    public void setEvaluador(BeanUsuario evaluador) {
        this.evaluador = evaluador;
    }

    public BeanUsuario getEvaluador() {
        return evaluador;
    }

    public void setRenderSede(boolean renderSede) {
        this.renderSede = renderSede;
    }

    public boolean isRenderSede() {
        return renderSede;
    }

    public void setRenderNivel(boolean renderNivel) {
        this.renderNivel = renderNivel;
    }

    public boolean isRenderNivel() {
        return renderNivel;
    }

    public void setRenderArea(boolean renderArea) {
        this.renderArea = renderArea;
    }

    public boolean isRenderArea() {
        return renderArea;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setLstEvaLineG(List<Object[]> lstEvaLineG) {
        this.lstEvaLineG = lstEvaLineG;
    }

    public List<Object[]> getLstEvaLineG() {
        return lstEvaLineG;
    }

    public void setLstEvaPieG(List<Object[]> lstEvaPieG) {
        this.lstEvaPieG = lstEvaPieG;
    }

    public List<Object[]> getLstEvaPieG() {
        return lstEvaPieG;
    }

    public void setLstEvaBarChartRol(List<Object[]> lstEvaBarChartRol) {
        this.lstEvaBarChartRol = lstEvaBarChartRol;
    }

    public List<Object[]> getLstEvaBarChartRol() {
        return lstEvaBarChartRol;
    }
}
