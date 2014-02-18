package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanProblema implements Serializable{
    @SuppressWarnings("compatibility:3748074096066339676")
    private static final long serialVersionUID = 1L;
    
    private String desc_problema;
    private String estado;
    private int nidProblema;

    public void setDesc_problema(String desc_problema) {
        this.desc_problema = desc_problema;
    }

    public String getDesc_problema() {
        return desc_problema;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setNidProblema(int nidProblema) {
        this.nidProblema = nidProblema;
    }

    public int getNidProblema() {
        return nidProblema;
    }
}
