package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanIndicador implements Serializable {
    @SuppressWarnings("compatibility:273036889079562424")
    private static final long serialVersionUID = 1L;
    
    private String descripcionIndicador;
    private int nidIndicador;

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setNidIndicador(int nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public int getNidIndicador() {
        return nidIndicador;
    }
}
