package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanCombo implements Serializable {
    @SuppressWarnings("compatibility:3356671100999741782")
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String descripcion;
    
    public BeanCombo(){
        
    }
    
    public BeanCombo(Integer id, String desc){
        this.id = id;
        this.descripcion = desc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}