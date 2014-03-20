package sped.vista.beans.administrativo.parte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sped.negocio.entidades.beans.BeanParteOcurrencia;

public class bSessionParteOcurrencia {
    
    private int exec = 0;
    private List lstSedes;
    private List lstProblemas;
    private boolean enableSedes;
    private String docentePO;
    private String cidProblema;
    private String cidSedePO;
    private Date fecMinPO;   
    private Date fecMaxPO;
    private List<BeanParteOcurrencia> lstNotifPOs = new ArrayList<BeanParteOcurrencia>();
    private transient List<Object[]> lstPOsPieG;
    private transient List<Object[]> lstPOsBarChart;
    private transient List<Object[]> lstPOsRadarChart;
    private boolean pie3D;
    private boolean renderedPie;
    private boolean renderedBar;
    private boolean renderedRadar;
    //DragNDroop
    private List<String> lstProfesoresRadar = new ArrayList<String>();
    private List<String> lstProblemasRadar = new ArrayList<String>();

    public void setLstPOsRadarChart(List<Object[]> lstPOsRadarChart) {
        this.lstPOsRadarChart = lstPOsRadarChart;
    }

    public List<Object[]> getLstPOsRadarChart() {
        return lstPOsRadarChart;
    }

    public void setLstProfesoresRadar(List<String> lstProfesoresRadar) {
        this.lstProfesoresRadar = lstProfesoresRadar;
    }

    public List<String> getLstProfesoresRadar() {
        return lstProfesoresRadar;
    }

    public void setLstProblemasRadar(List<String> lstProblemasRadar) {
        this.lstProblemasRadar = lstProblemasRadar;
    }

    public List<String> getLstProblemasRadar() {
        return lstProblemasRadar;
    }

    public void setRenderedPie(boolean renderedPie) {
        this.renderedPie = renderedPie;
    }

    public boolean isRenderedPie() {
        return renderedPie;
    }

    public void setRenderedBar(boolean renderedBar) {
        this.renderedBar = renderedBar;
    }

    public boolean isRenderedBar() {
        return renderedBar;
    }

    public void setRenderedRadar(boolean renderedRadar) {
        this.renderedRadar = renderedRadar;
    }

    public boolean isRenderedRadar() {
        return renderedRadar;
    }

    public void setLstPOsPieG(List<Object[]> lstPOsPieG) {
        this.lstPOsPieG = lstPOsPieG;
    }

    public List<Object[]> getLstPOsPieG() {
        return lstPOsPieG;
    }

    public void setLstPOsBarChart(List<Object[]> lstPOsBarChart) {
        this.lstPOsBarChart = lstPOsBarChart;
    }

    public List<Object[]> getLstPOsBarChart() {
        return lstPOsBarChart;
    }

    public void setPie3D(boolean pie3D) {
        this.pie3D = pie3D;
    }

    public boolean isPie3D() {
        return pie3D;
    }

    public void setLstSedes(List lstSedes) {
        this.lstSedes = lstSedes;
    }

    public List getLstSedes() {
        return lstSedes;
    }

    public void setLstProblemas(List lstProblemas) {
        this.lstProblemas = lstProblemas;
    }

    public List getLstProblemas() {
        return lstProblemas;
    }

    public void setEnableSedes(boolean enableSedes) {
        this.enableSedes = enableSedes;
    }

    public boolean isEnableSedes() {
        return enableSedes;
    }

    public void setDocentePO(String docentePO) {
        this.docentePO = docentePO;
    }

    public String getDocentePO() {
        return docentePO;
    }

    public void setCidProblema(String cidProblema) {
        this.cidProblema = cidProblema;
    }

    public String getCidProblema() {
        return cidProblema;
    }

    public void setCidSedePO(String cidSedePO) {
        this.cidSedePO = cidSedePO;
    }

    public String getCidSedePO() {
        return cidSedePO;
    }

    public void setFecMinPO(Date fecMinPO) {
        this.fecMinPO = fecMinPO;
    }

    public Date getFecMinPO() {
        return fecMinPO;
    }

    public void setFecMaxPO(Date fecMaxPO) {
        this.fecMaxPO = fecMaxPO;
    }

    public Date getFecMaxPO() {
        return fecMaxPO;
    }

    public void setLstNotifPOs(List<BeanParteOcurrencia> lstNotifPOs) {
        this.lstNotifPOs = lstNotifPOs;
    }

    public List<BeanParteOcurrencia> getLstNotifPOs() {
        return lstNotifPOs;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
