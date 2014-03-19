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
    private transient List<Object[]> lstEvaPieG;
    private boolean pie3D;

    public void setPie3D(boolean pie3D) {
        this.pie3D = pie3D;
    }

    public boolean isPie3D() {
        return pie3D;
    }

    public void setLstEvaPieG(List<Object[]> lstEvaPieG) {
        this.lstEvaPieG = lstEvaPieG;
    }

    public List<Object[]> getLstEvaPieG() {
        return lstEvaPieG;
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
