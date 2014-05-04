package sped.vista.beans.notificaciones;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

public class sessionNotificaciones implements Serializable{
    @SuppressWarnings("compatibility:456486613994770413")
    private static final long serialVersionUID = 1L;

    private List<BeanNotificacionEvaluacion> lstNotifEvaluaciones = new ArrayList<BeanNotificacionEvaluacion>();
    private List<BeanNotificacionEvaluacion> lstNotifEvaluacionesDetalle = new ArrayList<BeanNotificacionEvaluacion>();
    private int exec = 0;
    private boolean mostrarNotifEvas;
    private boolean mostrarNotifPOs;
    private String _evas;
    private String _pos;
    private List lstSedes;
    private List lstProblemas;
    private boolean enableSedes;
    //FILTROS DE TABLA DE EVALUACIONES
    private String docente;
    private String indicador;
    private String cidSede;
    private String estadoLeido;
    private Date fecMin;   
    private Date fecMax;
    //FIN FILTROS DE TABLA DE EVALUACIONES
    private List<BeanParteOcurrencia> lstNotifPOs = new ArrayList<BeanParteOcurrencia>();
    private String docentePO;
    private String cidProblema;
    private String cidSedePO;
    private String estadoLeidoPO;
    private Date fecMinPO;   
    private Date fecMaxPO;
    private int nidEvaluacionSelected;
    
    public sessionNotificaciones(){
        
    }

    public void setLstNotifEvaluacionesDetalle(List<BeanNotificacionEvaluacion> lstNotifEvaluacionesDetalle) {
        this.lstNotifEvaluacionesDetalle = lstNotifEvaluacionesDetalle;
    }

    public List<BeanNotificacionEvaluacion> getLstNotifEvaluacionesDetalle() {
        return lstNotifEvaluacionesDetalle;
    }

    public void setNidEvaluacionSelected(int nidEvaluacionSelected) {
        this.nidEvaluacionSelected = nidEvaluacionSelected;
    }

    public int getNidEvaluacionSelected() {
        return nidEvaluacionSelected;
    }

    public void setLstProblemas(List lstProblemas) {
        this.lstProblemas = lstProblemas;
    }

    public List getLstProblemas() {
        return lstProblemas;
    }

    public void setLstNotifPOs(List<BeanParteOcurrencia> lstNotifPOs) {
        this.lstNotifPOs = lstNotifPOs;
    }

    public List<BeanParteOcurrencia> getLstNotifPOs() {
        return lstNotifPOs;
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

    public void setEstadoLeidoPO(String estadoLeidoPO) {
        this.estadoLeidoPO = estadoLeidoPO;
    }

    public String getEstadoLeidoPO() {
        return estadoLeidoPO;
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

    public void setEnableSedes(boolean enableSedes) {
        this.enableSedes = enableSedes;
    }

    public boolean isEnableSedes() {
        return enableSedes;
    }

    public void setLstSedes(List lstSedes) {
        this.lstSedes = lstSedes;
    }

    public List getLstSedes() {
        return lstSedes;
    }
    
    public void setEstadoLeido(String estadoLeido) {
        this.estadoLeido = estadoLeido;
    }

    public String getEstadoLeido() {
        return estadoLeido;
    }

    public void setFecMin(Date fecMin) {
        this.fecMin = fecMin;
    }

    public Date getFecMin() {
        return fecMin;
    }

    public void setFecMax(Date fecMax) {
        this.fecMax = fecMax;
    }

    public Date getFecMax() {
        return fecMax;
    }

    public void setEvas(String _evas) {
        this._evas = _evas;
    }

    public String getEvas() {
        return _evas;
    }

    public void setPos(String _pos) {
        this._pos = _pos;
    }

    public String getPos() {
        return _pos;
    }

    public void setMostrarNotifEvas(boolean mostrarNotifEvas) {
        this.mostrarNotifEvas = mostrarNotifEvas;
    }

    public boolean isMostrarNotifEvas() {
        return mostrarNotifEvas;
    }

    public void setMostrarNotifPOs(boolean mostrarNotifPOs) {
        this.mostrarNotifPOs = mostrarNotifPOs;
    }

    public boolean isMostrarNotifPOs() {
        return mostrarNotifPOs;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstNotifEvaluaciones(List<BeanNotificacionEvaluacion> lstNotifEvaluaciones) {
        this.lstNotifEvaluaciones = lstNotifEvaluaciones;
    }

    public List<BeanNotificacionEvaluacion> getLstNotifEvaluaciones() {
        return lstNotifEvaluaciones;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getDocente() {
        return docente;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setCidSede(String cidSede) {
        this.cidSede = cidSede;
    }

    public String getCidSede() {
        return cidSede;
    }
}
