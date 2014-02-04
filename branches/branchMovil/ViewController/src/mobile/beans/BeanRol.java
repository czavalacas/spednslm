package mobile.beans;

import java.io.Serializable;

public class BeanRol implements Serializable{

    private String descripcionRol;
    private int nidRol;

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setNidRol(int nidRol) {
        this.nidRol = nidRol;
    }

    public int getNidRol() {
        return nidRol;
    }
}
