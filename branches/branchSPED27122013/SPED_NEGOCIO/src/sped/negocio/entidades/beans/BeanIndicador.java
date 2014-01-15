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
        if(obj instanceof BeanCriterio){
            BeanCriterio crit = (BeanCriterio) obj;
            return this.getNidIndicador().equals(crit.getNidCriterio());
        }else{
            return false;
        }
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
