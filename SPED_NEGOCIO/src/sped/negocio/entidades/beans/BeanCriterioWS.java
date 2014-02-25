package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanCriterioWS implements Serializable {
    @SuppressWarnings("compatibility:-853918080484962000")
    private static final long serialVersionUID = 1L;
    
    private String descripcionCriterio;
    private Integer nidCriterio;
    private Integer orden;
    private Integer cantidadValoresWS;
    private Integer nidFicha;
    private double nota;
    //auxiliares para consultar evaluacion movil
    private BeanIndicadorValorWS[] indicadoresVec;

    public void setIndicadoresVec(BeanIndicadorValorWS[] indicadoresVec) {
        this.indicadoresVec = indicadoresVec;
    }

    public BeanIndicadorValorWS[] getIndicadoresVec() {
        return indicadoresVec;
    }
    
    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
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

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setCantidadValoresWS(Integer cantidadValoresWS) {
        this.cantidadValoresWS = cantidadValoresWS;
    }

    public Integer getCantidadValoresWS() {
        return cantidadValoresWS;
    }

    public void setNidFicha(Integer nidFicha) {
        this.nidFicha = nidFicha;
    }

    public Integer getNidFicha() {
        return nidFicha;
    }

}
