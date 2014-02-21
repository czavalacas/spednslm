package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

import sped.negocio.entidades.admin.Curso;

/** Clase BeanAreaAcademica.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class BeanAreaAcademica implements Serializable {
    @SuppressWarnings("compatibility:8537437121961504324")
    private static final long serialVersionUID = 1L;
    
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
