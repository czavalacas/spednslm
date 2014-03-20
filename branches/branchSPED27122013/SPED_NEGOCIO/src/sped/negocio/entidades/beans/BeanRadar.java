package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanRadar implements Serializable {
    @SuppressWarnings("compatibility:6831397561817251933")
    private static final long serialVersionUID = 1L;
    
    private String arista;
    private String serie;
    private int cantidad;

    public void setArista(String arista) {
        this.arista = arista;
    }

    public String getArista() {
        return arista;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
}