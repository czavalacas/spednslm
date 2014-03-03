package mobile.beans;

import java.io.Serializable;

public class BeanBar implements Serializable {
    
    private String group;
    private String serie;
    private int cantidad;

    public BeanBar(){
        
    }
    
    public BeanBar(String group,String serie,int cantidad){
        this.group = group;
        this.serie = serie;
        this.cantidad = cantidad;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
}
