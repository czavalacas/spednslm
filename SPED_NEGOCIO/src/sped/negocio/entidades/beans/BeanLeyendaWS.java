package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanLeyendaWS implements Serializable {
    @SuppressWarnings("compatibility:-7658456690538197834")
    private static final long serialVersionUID = 1L;
    
    private String descripcionLeyenda;
    private Double valor;

    public void setDescripcionLeyenda(String descripcionLeyenda) {
        this.descripcionLeyenda = descripcionLeyenda;
    }

    public String getDescripcionLeyenda() {
        return descripcionLeyenda;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }
}