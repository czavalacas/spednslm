package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanIndicadorValorWS implements Serializable {
    @SuppressWarnings("compatibility:8986908375743262572")
    private static final long serialVersionUID = 1L;
    
    private Integer nidCI;
    private Integer valor;
    //AUXILIARES PARA CONSULTAR EVALUACION - MOVIL
    private String descripcionIndicador;
    private String leyenda;
    private int indice;
    
    public BeanIndicadorValorWS(){
        
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
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
