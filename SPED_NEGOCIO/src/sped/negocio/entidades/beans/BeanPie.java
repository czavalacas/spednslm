package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanPie implements Serializable {
    @SuppressWarnings("compatibility:-1668204839217609361")
    private static final long serialVersionUID = 1L;
    
    private String serie;
    private int cantSlice;
    private String colorRGB;
    private BeanBar[] lstBar;

    @Override
    public int hashCode(){
        if(this.getSerie() != null){
            return this.getSerie().hashCode();
        }else{
            return 0;    
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanPie){
            BeanPie pie = (BeanPie) obj;
            return this.getSerie().equalsIgnoreCase(pie.getSerie());
        }else{
            return false;
        }
    }
    
    public BeanPie(){
        
    }

    public void setLstBar(BeanBar[] lstBar) {
        this.lstBar = lstBar;
    }

    public BeanBar[] getLstBar() {
        return lstBar;
    }

    public BeanPie(String serie,int cantSlice,String colorRGB){
        this.serie = serie;
        this.cantSlice = cantSlice;
        this.colorRGB = colorRGB;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setCantSlice(int cantSlice) {
        this.cantSlice = cantSlice;
    }

    public int getCantSlice() {
        return cantSlice;
    }

    public void setColorRGB(String colorRGB) {
        this.colorRGB = colorRGB;
    }

    public String getColorRGB() {
        return colorRGB;
    }
}
