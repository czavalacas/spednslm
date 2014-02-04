package mobile.beans;

import java.io.Serializable;

public class BeanNivel implements Serializable {
    
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
