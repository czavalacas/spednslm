package mobile.beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanTipoXRol implements Serializable {
    private String descripcionTipoXRol;
    private Integer nidRol;

    public BeanTipoXRol(String desc,Integer rol){
        this.descripcionTipoXRol = desc;
        this.nidRol = rol;
    }

    public void setDescripcionTipoXRol(String descripcionTipoXRol) {
        this.descripcionTipoXRol = descripcionTipoXRol;
    }

    public String getDescripcionTipoXRol() {
        return descripcionTipoXRol;
    }

    public void setNidRol(Integer nidRol) {
        this.nidRol = nidRol;
    }

    public Integer getNidRol() {
        return nidRol;
    }
}
