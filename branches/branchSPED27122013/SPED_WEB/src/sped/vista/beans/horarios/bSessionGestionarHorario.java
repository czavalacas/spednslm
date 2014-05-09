package sped.vista.beans.horarios;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.beans.BeanDia;
import sped.negocio.entidades.beans.BeanMain;

public class bSessionGestionarHorario implements Serializable {
    @SuppressWarnings("compatibility:204896294902743203")
    private static final long serialVersionUID = 1L;

    private int exec;
    private String nidSede;
    private String nidNivel; 
    private String nidCurso;
    private String nidAula;
    private String nidProfesor;
    private String nidArea;
    private List lstSede;
    private List lstNivel;
    private List lstAula;
    private List lstProfesor;
    private List lstCurso;
    private List lstArea;
    private String horas[];
    private List<BeanMain> lstBeanMain;
    List<BeanDia> lstDia;
    BeanMain horario[][];    
    private int nroBloque;
    private int maxBloque;
    private String nombreProfesor;
    private String nombreCurso;
    private String nombreArea;

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
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

    public void setNidCurso(String nidCurso) {
        this.nidCurso = nidCurso;
    }

    public String getNidCurso() {
        return nidCurso;
    }

    public void setNidAula(String nidAula) {
        this.nidAula = nidAula;
    }

    public String getNidAula() {
        return nidAula;
    }

    public void setNidProfesor(String nidProfesor) {
        this.nidProfesor = nidProfesor;
    }

    public String getNidProfesor() {
        return nidProfesor;
    }

    public void setNidArea(String nidArea) {
        this.nidArea = nidArea;
    }

    public String getNidArea() {
        return nidArea;
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

    public void setLstCurso(List lstCurso) {
        this.lstCurso = lstCurso;
    }

    public List getLstCurso() {
        return lstCurso;
    }

    public void setLstAula(List lstAula) {
        this.lstAula = lstAula;
    }

    public List getLstAula() {
        return lstAula;
    }

    public void setLstProfesor(List lstProfesor) {
        this.lstProfesor = lstProfesor;
    }

    public List getLstProfesor() {
        return lstProfesor;
    }

    public void setLstArea(List lstArea) {
        this.lstArea = lstArea;
    }

    public List getLstArea() {
        return lstArea;
    }

    public void setHoras(String[] horas) {
        this.horas = horas;
    }

    public String[] getHoras() {
        return horas;
    }

    public void setLstBeanMain(List<BeanMain> lstBeanMain) {
        this.lstBeanMain = lstBeanMain;
    }

    public List<BeanMain> getLstBeanMain() {
        return lstBeanMain;
    }

    public void setHorario(BeanMain[][] horario) {
        this.horario = horario;
    }

    public BeanMain[][] getHorario() {
        return horario;
    }

    public void setNroBloque(int nroBloque) {
        this.nroBloque = nroBloque;
    }

    public int getNroBloque() {
        return nroBloque;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setLstDia(List<BeanDia> lstDia) {
        this.lstDia = lstDia;
    }

    public List<BeanDia> getLstDia() {
        return lstDia;
    }

    public void setMaxBloque(int maxBloque) {
        this.maxBloque = maxBloque;
    }

    public int getMaxBloque() {
        return maxBloque;
    }
}
