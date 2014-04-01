package sped.vista.beans.horarios;

import java.io.Serializable;

import java.util.List;

public class bSessionGestionarHorario implements Serializable {
    @SuppressWarnings("compatibility:-2139419570145049600")
    private static final long serialVersionUID = 1L;

    private int exec;
    private int nidSede;
    private int nidNivel; 
    private int nidCurso;
    private int nidAula;
    private List lstSede;
    private List lstNivel;
    private List lstCurso;
    private List lstAula;

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
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

    public void setNidCurso(int nidCurso) {
        this.nidCurso = nidCurso;
    }

    public int getNidCurso() {
        return nidCurso;
    }

    public void setNidAula(int nidAula) {
        this.nidAula = nidAula;
    }

    public int getNidAula() {
        return nidAula;
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

}
