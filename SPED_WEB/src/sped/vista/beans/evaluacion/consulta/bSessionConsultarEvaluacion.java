package sped.vista.beans.evaluacion.consulta;

import java.util.List;

import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.beans.BeanNivel;
import sped.negocio.entidades.beans.BeanSede;

public class bSessionConsultarEvaluacion {
    private int exec;
    private List<BeanEvaluacion> lstBeanEvaluacion;
    private boolean columnArea = true;
    private boolean columnEvaluador = true;
    private boolean columnProfesor = true;
    private boolean columnSede = true;
    private boolean columnNivel = true;
    private List lstSede;
    private List lstNivel;
    private List lstArea;
    private List lstCurso;
    private List lstGrado;
    private String nombreProfesor;
    private int nidSede;
    private int nidNivel;
    private int nidArea;
    private int nidCurso;
    private int nidGrado;
        
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

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }

    public void setNidNivel(int nidNivel) {
        this.nidNivel = nidNivel;
    }

    public int getNidNivel() {
        return nidNivel;
    }

    public void setNidArea(int nidArea) {
        this.nidArea = nidArea;
    }

    public int getNidArea() {
        return nidArea;
    }

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public int getNidCurso() {
        return nidCurso;
    }

    public void setNidGrado(int nidGrado) {
        this.nidGrado = nidGrado;
    }

    public int getNidGrado() {
        return nidGrado;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }
}
