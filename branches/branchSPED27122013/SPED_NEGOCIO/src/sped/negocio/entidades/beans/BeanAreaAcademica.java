package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanAreaAcademica implements Serializable {
    @SuppressWarnings("compatibility:8537437121961504324")
    private static final long serialVersionUID = 1L;
    
    private String descripcionAreaAcademica;
    private int nidAreaAcademica;

    public void setDescripcionAreaAcademica(String descripcionAreaAcademica) {
        this.descripcionAreaAcademica = descripcionAreaAcademica;
    }

    public String getDescripcionAreaAcademica() {
        return descripcionAreaAcademica;
    }

    public void setNidAreaAcademica(int nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public int getNidAreaAcademica() {
        return nidAreaAcademica;
    }
}
