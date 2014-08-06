package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanComboDouble implements Serializable {
    @SuppressWarnings("compatibility:-7869000880187053869")
    private static final long serialVersionUID = 1L;
    
    private double id;
    private double descripcion;

    public BeanComboDouble(){
        
    }
    
    public BeanComboDouble(double id, double desc){
        this.id = id;
        this.descripcion = desc;
    }
    
    public void setId(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }

    public void setDescripcion(double descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescripcion() {
        return descripcion;
    }
}