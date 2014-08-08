package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanValor implements Serializable {
    @SuppressWarnings("compatibility:-5794951019555880749")
    private static final long serialVersionUID = 1L;
    
    private String descripcionValor;
    private int nidValoracion;
    private double valor;
    //private List<FichaValor> fichaValorLista;

    public void setDescripcionValor(String descripcionValor) {
        this.descripcionValor = descripcionValor;
    }

    public String getDescripcionValor() {
        return descripcionValor;
    }

    public void setNidValoracion(int nidValoracion) {
        this.nidValoracion = nidValoracion;
    }

    public int getNidValoracion() {
        return nidValoracion;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
