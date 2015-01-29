package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanComboInteger implements Serializable {
    @SuppressWarnings("compatibility:-2467335972819068062")
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer descripcion;

    public BeanComboInteger(){
        
    }
    
    public BeanComboInteger(Integer id,Integer desc){
        this.id = id;
        this.descripcion = desc;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDescripcion() {
        return descripcion;
    }
}