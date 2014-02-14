package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanIndicadorValorWS implements Serializable {
    @SuppressWarnings("compatibility:8986908375743262572")
    private static final long serialVersionUID = 1L;
    
    private Integer nidCI;
    private Integer valor;
    
    public BeanIndicadorValorWS(){
        
    }
    
    public BeanIndicadorValorWS(Integer nidCI,Integer valor){
        this.nidCI = nidCI;
        this.valor = valor;
    }
    
    public void setNidCI(Integer nidCI) {
        this.nidCI = nidCI;
    }

    public Integer getNidCI() {
        return nidCI;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}
