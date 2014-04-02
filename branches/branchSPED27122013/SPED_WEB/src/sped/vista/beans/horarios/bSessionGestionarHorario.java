package sped.vista.beans.horarios;

import java.io.Serializable;

import java.util.List;

public class bSessionGestionarHorario implements Serializable {
    @SuppressWarnings("compatibility:6980576281228298030")
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

}
