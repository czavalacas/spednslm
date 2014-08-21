package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanComboDouble2 implements Serializable {
    @SuppressWarnings("compatibility:-6644415486935511150")
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Double descripcion;

    public BeanComboDouble2(){
        
    }
    
    public BeanComboDouble2(Integer id, Double desc){
        this.id = id;
        this.descripcion = desc;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDescripcion(Double descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDescripcion() {
        return descripcion;
    }
}