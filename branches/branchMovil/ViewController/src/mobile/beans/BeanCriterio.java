package mobile.beans;

import java.io.Serializable;

public class BeanCriterio implements Serializable {
    
    private String descripcionCriterio;
    private Integer nidCriterio;
    private BeanIndicador[] indicadoresVec = null;
    private double nota;
    
    public BeanCriterio(){
        
    }

    public BeanCriterio(String descripcionCriterio,Integer nidCriterio,BeanIndicador[] indicadoresVec,double nota){
        this.descripcionCriterio = descripcionCriterio;
        this.nidCriterio = nidCriterio;
        this.indicadoresVec = indicadoresVec;
        this.nota = nota;
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


    public void setIndicadoresVec(BeanIndicador[] indicadoresVec) {
        this.indicadoresVec = indicadoresVec;
    }

    public BeanIndicador[] getIndicadoresVec() {
        return indicadoresVec;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }
}
