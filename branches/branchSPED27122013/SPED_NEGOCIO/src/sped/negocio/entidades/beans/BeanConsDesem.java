package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class BeanConsDesem implements Serializable {
    @SuppressWarnings("compatibility:2902554203044690765")
    private static final long serialVersionUID = 1L;
    
    private Integer nidIndicador;
    private Double valor;
    private Integer nidFicha;
    private String descFicha;
    private Integer count;
    private Double porcValor;
    private List<Object[]> lstVals = new ArrayList<Object[]>();

    public BeanConsDesem(){
        
    }
    
    public BeanConsDesem(Integer nidIndicador,
                         Double valor,
                         Integer nidFicha,
                         String tipoFicha,
                         String tipoFichaCurso,
                         Integer count,
                         Double porcValor){
        this.nidIndicador = nidIndicador;
        this.valor = valor;
        this.nidFicha = nidFicha;
        this.descFicha = tipoFicha+ " - "+tipoFichaCurso;
        this.count = count;
        this.porcValor = porcValor;
    }

    public void setLstVals(List<Object[]> lstVals) {
        this.lstVals = lstVals;
    }

    public List<Object[]> getLstVals() {
        return lstVals;
    }

    public void setNidIndicador(Integer nidIndicador) {
        this.nidIndicador = nidIndicador;
    }

    public Integer getNidIndicador() {
        return nidIndicador;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setNidFicha(Integer nidFicha) {
        this.nidFicha = nidFicha;
    }

    public Integer getNidFicha() {
        return nidFicha;
    }

    public void setDescFicha(String descFicha) {
        this.descFicha = descFicha;
    }

    public String getDescFicha() {
        return descFicha;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setPorcValor(Double porcValor) {
        this.porcValor = porcValor;
    }

    public Double getPorcValor() {
        return porcValor;
    }
}
