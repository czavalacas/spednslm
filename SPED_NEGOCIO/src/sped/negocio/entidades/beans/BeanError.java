package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanError implements Serializable {
    @SuppressWarnings("compatibility:3980016418207361107")
    private static final long serialVersionUID = 1L;
    
    private String cidError;
    private String descripcionError;
    private String tituloError;

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getCidError() {
        return cidError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setTituloError(String tituloError) {
        this.tituloError = tituloError;
    }

    public String getTituloError() {
        return tituloError;
    }
}