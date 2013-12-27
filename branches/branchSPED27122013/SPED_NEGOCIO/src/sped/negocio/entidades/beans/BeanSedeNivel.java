package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanSedeNivel implements Serializable {
    @SuppressWarnings("compatibility:9063844433539636253")
    private static final long serialVersionUID = 1L;
    
    private BeanNivel nivel;
    private BeanSede sede;

    public void setNivel(BeanNivel nivel) {
        this.nivel = nivel;
    }

    public BeanNivel getNivel() {
        return nivel;
    }

    public void setSede(BeanSede sede) {
        this.sede = sede;
    }

    public BeanSede getSede() {
        return sede;
    }
}