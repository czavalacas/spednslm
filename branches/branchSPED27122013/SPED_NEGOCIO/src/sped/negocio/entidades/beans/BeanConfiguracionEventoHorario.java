package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanConfiguracionEventoHorario implements Serializable {
    @SuppressWarnings("compatibility:-3637455508338985716")
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private int nidConfev;
    
    public BeanConfiguracionEventoHorario() {
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNidConfev(int nidConfev) {
        this.nidConfev = nidConfev;
    }

    public int getNidConfev() {
        return nidConfev;
    }
    
}
