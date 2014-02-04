package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanRol implements Serializable {
    @SuppressWarnings("compatibility:-7016026201578558946")
    private static final long serialVersionUID = 1L;
    
    private String descripcionRol;
    private int nidRol;

    public BeanRol(String desc,int nidRol){
        this.descripcionRol = desc;
        this.nidRol = nidRol;
    }
    
    public BeanRol(){
    }
    
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
