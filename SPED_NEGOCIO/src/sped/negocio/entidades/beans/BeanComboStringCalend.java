package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanComboStringCalend implements Serializable {
    @SuppressWarnings("compatibility:-5347294862086632029")
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String descripcion;

    public BeanComboStringCalend(){
        
    }
    
    public BeanComboStringCalend(String id,String desc){
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
