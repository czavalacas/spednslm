package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanNivel implements Serializable {
    @SuppressWarnings("compatibility:3778582013062934877")
    private static final long serialVersionUID = 1L;
    private String descripcionNivel;
    private Integer nidNivel;

    public void setDescripcionNivel(String descripcionNivel) {
        this.descripcionNivel = descripcionNivel;
    }

    public String getDescripcionNivel() {
        return descripcionNivel;
    }

    public void setNidNivel(Integer nidNivel) {
        this.nidNivel = nidNivel;
    }

    public Integer getNidNivel() {
        return nidNivel;
    }
}
