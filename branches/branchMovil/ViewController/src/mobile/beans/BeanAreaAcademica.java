package mobile.beans;

import java.io.Serializable;

public class BeanAreaAcademica implements Serializable {
    
    private String descripcionAreaAcademica;
    private Integer nidAreaAcademica;

    public void setDescripcionAreaAcademica(String descripcionAreaAcademica) {
        this.descripcionAreaAcademica = descripcionAreaAcademica;
    }

    public String getDescripcionAreaAcademica() {
        return descripcionAreaAcademica;
    }


    public void setNidAreaAcademica(Integer nidAreaAcademica) {
        this.nidAreaAcademica = nidAreaAcademica;
    }

    public Integer getNidAreaAcademica() {
        return nidAreaAcademica;
    }
}
