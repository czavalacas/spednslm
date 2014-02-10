package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanCriterioIndicadorWS implements Serializable {
    @SuppressWarnings("compatibility:-4304090315206733549")
    private static final long serialVersionUID = 1L;
    
    private int nidCriterioIndicador;
    private int orden;
    private String descripcionIndicador;
    private Integer nidIndicador;

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    private int valor;

    public void setNidCriterioIndicador(int nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }

    public int getNidCriterioIndicador() {
        return nidCriterioIndicador;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setNidIndicador(Integer nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public Integer getNidIndicador() {
        return nidIndicador;
    }
}
