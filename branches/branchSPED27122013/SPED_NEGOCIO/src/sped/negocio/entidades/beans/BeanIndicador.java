package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.HashSet;
import java.util.List;

public class BeanIndicador implements Serializable {
    @SuppressWarnings("compatibility:273036889079562424")
    private static final long serialVersionUID = 1L;
    
    private String descripcionIndicador;
    private Integer nidIndicador;
    private boolean selected = false;
    List<Integer> lstCritsArbol;
    private BeanError beanError = new BeanError();
    private Integer orden;
    private boolean noMostrarDown;

    @Override
    public int hashCode(){
        if(this.getNidIndicador() != 0){
            return this.getNidIndicador().hashCode();
        }else{
            return 0;    
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanIndicador){
            BeanIndicador crit = (BeanIndicador) obj;
            return this.getNidIndicador().equals(crit.getNidIndicador());
        }else{
            return false;
        }
    }

    public int compareTo(Object obj){
        BeanIndicador item = (BeanIndicador) obj;
        Integer ordenObj = item.getOrden();
        Integer ordenThis = this.getOrden();
        return (ordenThis.compareTo(ordenObj));
    }

    public void setNoMostrarDown(boolean noMostrarDown) {
        this.noMostrarDown = noMostrarDown;
    }

    public boolean isNoMostrarDown() {
        return noMostrarDown;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setLstCritsArbol(List<Integer> lstCritsArbol) {
        this.lstCritsArbol = lstCritsArbol;
    }

    public List<Integer> getLstCritsArbol() {
        return lstCritsArbol;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setNidIndicador(Integer nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public Integer getNidIndicador() {
        return nidIndicador;
    }
}
