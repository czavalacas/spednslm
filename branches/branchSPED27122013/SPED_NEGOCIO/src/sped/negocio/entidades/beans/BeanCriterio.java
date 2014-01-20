package sped.negocio.entidades.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanCriterio implements Serializable, Cloneable {
    @SuppressWarnings("compatibility:-4334476189667862718")
    private static final long serialVersionUID = 1L;

    private String descripcionCriterio;
    private Integer nidCriterio;
    private List<BeanCriterio> lstIndicadores = new ArrayList<BeanCriterio>();
    private boolean selected = false;
    private boolean mostrarBoton = false;
    private BeanError beanError = new BeanError();
    private List<BeanLeyenda> lstLeyenda = new ArrayList<BeanLeyenda>();
    private String display = "display:none;";
    private Integer orden;

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
    
    @Override
    public int hashCode(){
        if(this.getNidCriterio() != 0){
            return this.getNidCriterio().hashCode();
        }else{
            return 0;    
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanCriterio){
            BeanCriterio crit = (BeanCriterio) obj;
            return this.getNidCriterio().equals(crit.getNidCriterio());
        }else{
            return false;
        }
    }
    
    public int compareTo(Object obj){
        BeanCriterio item = (BeanCriterio) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }


    public void setLstLeyenda(List<BeanLeyenda> lstLeyenda) {
        this.lstLeyenda = lstLeyenda;
    }

    public List<BeanLeyenda> getLstLeyenda() {
        return lstLeyenda;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setMostrarBoton(boolean mostrarBoton) {
        this.mostrarBoton = mostrarBoton;
    }

    public boolean isMostrarBoton() {
        return mostrarBoton;
    }

    public void setDescripcionCriterio(String descripcionCriterio) {
        this.descripcionCriterio = descripcionCriterio;
    }

    public String getDescripcionCriterio() {
        return descripcionCriterio;
    }

    public void setNidCriterio(Integer nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public Integer getNidCriterio() {
        return nidCriterio;
    }

    public void setLstIndicadores(List<BeanCriterio> lstIndicadores) {
        this.lstIndicadores = lstIndicadores;
    }

    public List<BeanCriterio> getLstIndicadores() {
        return lstIndicadores;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
