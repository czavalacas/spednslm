package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanComboString implements Serializable {
    @SuppressWarnings("compatibility:-6616784476925373533")
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String descripcion;

    public BeanComboString(){
        
    }
    
    public BeanComboString(String id,String desc){
        this.id = id;
        this.descripcion = desc;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}