package sped.vista.beans.evaluacion.grafico_reporte;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionPlani;
import sped.negocio.entidades.beans.BeanUsuario;

public class bSessionDesempenoEvaluador implements Serializable {
    @SuppressWarnings("compatibility:-8661660304648975906")
    private static final long serialVersionUID = 1L;
    private int exec;
    private List lstRol;
    private List lstSede;
    private List lstArea;
    private List lstEvaluador;
    private List selectedRol;
    private List selectedEvaluador;
    private List selectedSede;
    private List selectedArea;
    private List selectedRol_aux;
    private List selectedEvaluador_aux;
    private List selectedSede_aux;
    private List selectedArea_aux;
    private List<String> lstCorreo = new ArrayList();
    private String correo;
    private Date fechaPI;
    private Date fechaPF;
    private Date fechaEI;
    private Date fechaEF;
    private Date fechaPI_axu;
    private Date fechaPF_aux;
    private Date fechaEI_aux;
    private Date fechaEF_aux;
    private Date fechaActual;
    private Date fechaAnterior;
    private List<BeanEvaluacionPlani> lstEvaTable;
    private List<BeanEvaluacionPlani> lstEvaDetalle;
    private List<BeanEvaluacionPlani> lstEvaDetallePie;
    private transient List<Object[]> lstEvaBarChart;
    private transient List<Object[]> lstEvaBarChartRol;
    private transient List<Object[]> lstEvaLineG;
    private transient List<Object[]> lstEvaPieG;
    private BeanUsuario evaluador;
    private String titleDialog;
    private String estado;
    private boolean renderSede;
    private boolean renderNivel;
    private boolean renderArea;
    private boolean renderMensaje;
    private boolean renderComentario;
    private boolean renderProblema;
    private boolean rGrafRol = true;
    private boolean rGrafEva = true;
    private boolean rGrafLine = true;
    private boolean rGrafPie = true;
    private boolean rGrafRolA = true;
    private boolean rGrafEvaA = true;
    private boolean rGrafLineA = true;
    private boolean rGrafPieA = true;
    private String rowHeightDashboard = "350px";
    private int columnsDashboard = 2;
    private String mensaje;
    private String asunto;
    private String typePopUpCorreo = "none";
    private boolean renderFRol;
    private boolean renderFSede;
    private boolean renderFArea;
    private boolean renderFEvaluador;
    private boolean renderExcel;
    
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

    public void setLstEvaTable(List<BeanEvaluacionPlani> lstEvaTable) {
        this.lstEvaTable = lstEvaTable;
    }

    public List<BeanEvaluacionPlani> getLstEvaTable() {
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

    public void setLstEvaDetalle(List<BeanEvaluacionPlani> lstEvaDetalle) {
        this.lstEvaDetalle = lstEvaDetalle;
    }

    public List<BeanEvaluacionPlani> getLstEvaDetalle() {
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

    public void setLstEvaDetallePie(List<BeanEvaluacionPlani> lstEvaDetallePie) {
        this.lstEvaDetallePie = lstEvaDetallePie;
    }

    public List<BeanEvaluacionPlani> getLstEvaDetallePie() {
        return lstEvaDetallePie;
    }

    public void setTitleDialog(String titleDialog) {
        this.titleDialog = titleDialog;
    }

    public String getTitleDialog() {
        return titleDialog;
    }

    public void setRenderMensaje(boolean renderMensaje) {
        this.renderMensaje = renderMensaje;
    }

    public boolean isRenderMensaje() {
        return renderMensaje;
    }

    public void setRenderProblema(boolean renderProblema) {
        this.renderProblema = renderProblema;
    }

    public boolean isRenderProblema() {
        return renderProblema;
    }

    public void setRenderComentario(boolean renderComentario) {
        this.renderComentario = renderComentario;
    }

    public boolean isRenderComentario() {
        return renderComentario;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaAnterior(Date fechaAnterior) {
        this.fechaAnterior = fechaAnterior;
    }

    public Date getFechaAnterior() {
        return fechaAnterior;
    }

    public void setRGrafRol(boolean rGrafRol) {
        this.rGrafRol = rGrafRol;
    }

    public boolean isRGrafRol() {
        return rGrafRol;
    }

    public void setRGrafEva(boolean rGrafEva) {
        this.rGrafEva = rGrafEva;
    }

    public boolean isRGrafEva() {
        return rGrafEva;
    }

    public void setRGrafLine(boolean rGrafLine) {
        this.rGrafLine = rGrafLine;
    }

    public boolean isRGrafLine() {
        return rGrafLine;
    }

    public void setRGrafPie(boolean rGrafPie) {
        this.rGrafPie = rGrafPie;
    }

    public boolean isRGrafPie() {
        return rGrafPie;
    }

    public void setRowHeightDashboard(String rowHeightDashboard) {
        this.rowHeightDashboard = rowHeightDashboard;
    }

    public String getRowHeightDashboard() {
        return rowHeightDashboard;
    }

    public void setColumnsDashboard(int columnsDashboard) {
        this.columnsDashboard = columnsDashboard;
    }

    public int getColumnsDashboard() {
        return columnsDashboard;
    }

    public void setRGrafRolA(boolean rGrafRolA) {
        this.rGrafRolA = rGrafRolA;
    }

    public boolean isRGrafRolA() {
        return rGrafRolA;
    }

    public void setRGrafEvaA(boolean rGrafEvaA) {
        this.rGrafEvaA = rGrafEvaA;
    }

    public boolean isRGrafEvaA() {
        return rGrafEvaA;
    }

    public void setRGrafLineA(boolean rGrafLineA) {
        this.rGrafLineA = rGrafLineA;
    }

    public boolean isRGrafLineA() {
        return rGrafLineA;
    }

    public void setRGrafPieA(boolean rGrafPieA) {
        this.rGrafPieA = rGrafPieA;
    }

    public boolean isRGrafPieA() {
        return rGrafPieA;
    }

    public void setLstCorreo(List<String> lstCorreo) {
        this.lstCorreo = lstCorreo;
    }

    public List<String> getLstCorreo() {
        return lstCorreo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setTypePopUpCorreo(String typePopUpCorreo) {
        this.typePopUpCorreo = typePopUpCorreo;
    }

    public String getTypePopUpCorreo() {
        return typePopUpCorreo;
    }

    public void setLstEvaluador(List lstEvaluador) {
        this.lstEvaluador = lstEvaluador;
    }

    public List getLstEvaluador() {
        return lstEvaluador;
    }

    public void setRenderFRol(boolean renderFRol) {
        this.renderFRol = renderFRol;
    }

    public boolean isRenderFRol() {
        return renderFRol;
    }

    public void setRenderFSede(boolean renderFSede) {
        this.renderFSede = renderFSede;
    }

    public boolean isRenderFSede() {
        return renderFSede;
    }

    public void setRenderFArea(boolean renderFArea) {
        this.renderFArea = renderFArea;
    }

    public boolean isRenderFArea() {
        return renderFArea;
    }

    public void setRenderFEvaluador(boolean renderFEvaluador) {
        this.renderFEvaluador = renderFEvaluador;
    }

    public boolean isRenderFEvaluador() {
        return renderFEvaluador;
    }

    public void setRenderExcel(boolean renderExcel) {
        this.renderExcel = renderExcel;
    }

    public boolean isRenderExcel() {
        return renderExcel;
    }
}
