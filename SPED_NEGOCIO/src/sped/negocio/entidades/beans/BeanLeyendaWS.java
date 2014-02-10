package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanLeyendaWS implements Serializable {
    @SuppressWarnings("compatibility:-7658456690538197834")
    private static final long serialVersionUID = 1L;
    
    private String descripcionLeyenda;
    private int valor;

    public void setDescripcionLeyenda(String descripcionLeyenda) {
        this.descripcionLeyenda = descripcionLeyenda;
    }

    public String getDescripcionLeyenda() {
        return descripcionLeyenda;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}