package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanCriterioValor implements Serializable {
    @SuppressWarnings("compatibility:2396745260449794512")
    private static final long serialVersionUID = 1L;
    
    private int nidCriterioValor;
    private String descripcionValor;
    private int idValoracion;
    /*private int nidCriterio1;
    private int nidFicha1;*/
    private double valor;
    private BeanFichaCriterio fichaCriterio;
    private Integer orden;

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setNidCriterioValor(int nidCriterioValor) {
        this.nidCriterioValor = nidCriterioValor;
    }

    public int getNidCriterioValor() {
        return nidCriterioValor;
    }

    public void setDescripcionValor(String descripcionValor) {
        this.descripcionValor = descripcionValor;
    }

    public String getDescripcionValor() {
        return descripcionValor;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getIdValoracion() {
        return idValoracion;
    }
/*
    public void setNidCriterio1(int nidCriterio) {
        this.nidCriterio1 = nidCriterio;
    }

    public int getNidCriterio1() {
        return nidCriterio1;
    }

    public void setNidFicha1(int nidFicha) {
        this.nidFicha1 = nidFicha;
    }

    public int getNidFicha1() {
        return nidFicha1;
    }
*/
    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setFichaCriterio(BeanFichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public BeanFichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }
}
