package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanBar implements Serializable {
    @SuppressWarnings("compatibility:608911327329576460")
    private static final long serialVersionUID = 1L;
    
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
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanBar){
            BeanBar bar = (BeanBar) obj;
            return this.getSerie().equalsIgnoreCase(bar.getSerie()) && this.getGroup().equalsIgnoreCase(bar.getGroup());
        }else{
            return false;
        }
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
