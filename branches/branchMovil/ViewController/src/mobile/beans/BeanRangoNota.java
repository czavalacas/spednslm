package mobile.beans;

import java.io.Serializable;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class BeanRangoNota implements Serializable {
    
    private String descRangoNota;
    private int cantEvas;
    private String colorRGB;
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public BeanRangoNota(){
        
    }

    public BeanRangoNota(String descRangoNota,int cantEvas,String colorRGB){
        this.descRangoNota = descRangoNota;
        this.cantEvas = cantEvas;
        this.colorRGB = colorRGB;
    }
    
    public void setDescRangoNota(String descRangoNota) {
        String olddescRangoNota = this.descRangoNota; 
        this.descRangoNota = descRangoNota;
        propertyChangeSupport.firePropertyChange("descRangoNota", olddescRangoNota, descRangoNota);
    }

    public String getDescRangoNota() {
        return descRangoNota;
    }

    public void setCantEvas(int cantEvas) {
        int oldcantEvas = this.cantEvas; 
        this.cantEvas = cantEvas;
        propertyChangeSupport.firePropertyChange("cantEvas", oldcantEvas, cantEvas);
    }

    public int getCantEvas() {
        return cantEvas;
    }
    
    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) { 
        this.propertyChangeSupport = propertyChangeSupport; 
    } 
    
    public PropertyChangeSupport getPropertyChangeSupport() { 
        return propertyChangeSupport; 
    }

    public void setColorRGB(String colorRGB) {
        String oldcolorRGB = this.colorRGB; 
        this.colorRGB = colorRGB;
        propertyChangeSupport.firePropertyChange("colorRGB", oldcolorRGB, colorRGB);
    }

    public String getColorRGB() {
        return colorRGB;
    }
}
