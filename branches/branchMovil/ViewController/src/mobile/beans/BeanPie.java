package mobile.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class BeanPie implements Serializable {
    
    private String serie;
    private int cantSlice;
    private String colorRGB;
    private BeanBar[] lstBar = null;

    public BeanPie(){
        
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

    public void setLstBar(BeanBar[] lstBar) {
        this.lstBar = lstBar;
    }

    public BeanBar[] getLstBar() {
        return lstBar;
    }
}
