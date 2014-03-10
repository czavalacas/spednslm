package sped.vista.beans.administrativo.problemas;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.beans.BeanProblema;

public class bSessionGestionarProblema implements Serializable {
    @SuppressWarnings("compatibility:499416940767948350")
    private static final long serialVersionUID = 1L;
    private int exec;
    private List<BeanProblema> lstBeanProblema;
    private int nidProblema;
    private String Descripcion;
    private int evento;
    
    public bSessionGestionarProblema() {
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstBeanProblema(List<BeanProblema> lstBeanProblema) {
        this.lstBeanProblema = lstBeanProblema;
    }

    public List<BeanProblema> getLstBeanProblema() {
        return lstBeanProblema;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidProblema() {
        return nidProblema;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getEvento() {
        return evento;
    }
}
