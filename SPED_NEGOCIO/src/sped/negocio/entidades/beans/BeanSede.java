package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanSede implements Serializable {
    @SuppressWarnings("compatibility:-678576333470706426")
    private static final long serialVersionUID = 1L;
    
    private String descripcionSede;
    private int nidSede;

    public void setDescripcionSede(String descripcionSede) {
        this.descripcionSede = descripcionSede;
    }

    public String getDescripcionSede() {
        return descripcionSede;
    }

    public void setNidSede(int nidSede) {
        this.nidSede = nidSede;
    }

    public int getNidSede() {
        return nidSede;
    }
}
