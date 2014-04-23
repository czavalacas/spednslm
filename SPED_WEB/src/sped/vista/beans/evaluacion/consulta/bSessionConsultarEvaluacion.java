package sped.vista.beans.evaluacion.consulta;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.model.AutoSuggestUIHints;

import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;

public class bSessionConsultarEvaluacion {
    private int exec;
    private List<BeanEvaluacion> lstBeanEvaluacion;
    private BeanEvaluacion evaSelect; // evaluacion para el comentario profesor
    private boolean columnArea = true;
    private boolean columnEvaluador = true;
    private boolean columnProfesor = true;
    private boolean columnSede = true;
    private boolean columnNivel = true;
    private boolean renderExcel;
    private List lstSede;
    private List lstNivel;
    private List lstArea;
    private List lstCurso;
    private List lstGrado;
    private List lstEstadoEvaluacion;
    private String nombreProfesor;
    private String nombreEvaluador;
    private Date fechaP;
    private Date fechaPf;
    private Date fechaF;
    private Date fechaFf;
    private String descripcionEstadoEvaluacion;
    private String nidSede;
    private String nidNivel;
    private String nidArea;
    private String nidCurso;
    private String nidGrado;
    private int estadoEvaluacion;
    private BeanEvaluacion evaluacion;
    private List<SelectItem> itemProfesor;
    private List<SelectItem> itemEvaluador;
        
    public bSessionConsultarEvaluacion() {
    }
    
    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstBeanEvaluacion(List<BeanEvaluacion> lstBeanEvaluacion) {
        this.lstBeanEvaluacion = lstBeanEvaluacion;
    }

    public List<BeanEvaluacion> getLstBeanEvaluacion() {
        return lstBeanEvaluacion;
    }

    public void setColumnArea(boolean columnArea) {
        this.columnArea = columnArea;
    }

    public boolean isColumnArea() {
        return columnArea;
    }

    public void setColumnEvaluador(boolean columnEvaluador) {
        this.columnEvaluador = columnEvaluador;
    }

    public boolean isColumnEvaluador() {
        return columnEvaluador;
    }

    public void setColumnProfesor(boolean columnProfesor) {
        this.columnProfesor = columnProfesor;
    }

    public boolean isColumnProfesor() {
        return columnProfesor;
    }

    public void setColumnSede(boolean columnSede) {
        this.columnSede = columnSede;
    }

    public boolean isColumnSede() {
        return columnSede;
    }

    public void setColumnNivel(boolean columnNivel) {
        this.columnNivel = columnNivel;
    }

    public boolean isColumnNivel() {
        return columnNivel;
    }

    public void setLstSede(List lstSede) {
        this.lstSede = lstSede;
    }

    public List getLstSede() {
        return lstSede;
    }

    public void setLstNivel(List lstNivel) {
        this.lstNivel = lstNivel;
    }

    public List getLstNivel() {
        return lstNivel;
    }

    public void setLstArea(List lstArea) {
        this.lstArea = lstArea;
    }

    public List getLstArea() {
        return lstArea;
    }

    public void setLstCurso(List lstCurso) {
        this.lstCurso = lstCurso;
    }

    public List getLstCurso() {
        return lstCurso;
    }

    public void setLstGrado(List lstGrado) {
        this.lstGrado = lstGrado;
    }

    public List getLstGrado() {
        return lstGrado;
    }
    
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setFechaP(Date fechaP) {
        this.fechaP = fechaP;
    }

    public Date getFechaP() {
        return fechaP;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaPf(Date fechaPf) {
        this.fechaPf = fechaPf;
    }

    public Date getFechaPf() {
        return fechaPf;
    }

    public void setFechaFf(Date fechaFf) {
        this.fechaFf = fechaFf;
    }

    public Date getFechaFf() {
        return fechaFf;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setLstEstadoEvaluacion(List lstEstadoEvaluacion) {
        this.lstEstadoEvaluacion = lstEstadoEvaluacion;
    }

    public List getLstEstadoEvaluacion() {
        return lstEstadoEvaluacion;
    }

    public void setDescripcionEstadoEvaluacion(String descripcionEstadoEvaluacion) {
        this.descripcionEstadoEvaluacion = descripcionEstadoEvaluacion;
    }

    public String getDescripcionEstadoEvaluacion() {
        return descripcionEstadoEvaluacion;
    }

    public void setEstadoEvaluacion(int estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public int getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEvaluacion(BeanEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public BeanEvaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaSelect(BeanEvaluacion evaSelect) {
        this.evaSelect = evaSelect;
    }

    public BeanEvaluacion getEvaSelect() {
        return evaSelect;
    }

    public void setItemProfesor(List<SelectItem> itemProfesor) {
        this.itemProfesor = itemProfesor;
    }

    public List<SelectItem> getItemProfesor() {
        return itemProfesor;
    }

    public void setItemEvaluador(List<SelectItem> itemEvaluador) {
        this.itemEvaluador = itemEvaluador;
    }

    public List<SelectItem> getItemEvaluador() {
        return itemEvaluador;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setNidNivel(String nidNivel) {
        this.nidNivel = nidNivel;
    }

    public String getNidNivel() {
        return nidNivel;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
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

    public void setRenderExcel(boolean renderExcel) {
        this.renderExcel = renderExcel;
    }

    public boolean isRenderExcel() {
        return renderExcel;
    }
}
