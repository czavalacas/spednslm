package mobile.beans;

import java.io.Serializable;

public class BeanSedeNivel implements Serializable {
    
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
