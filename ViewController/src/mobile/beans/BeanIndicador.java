package mobile.beans;

import java.io.Serializable;

public class BeanIndicador implements Serializable {
    
    private String descripcionIndicador;
    private Integer nidIndicador;
    private Integer indice;
    private String leyenda;
    private Integer valor;

    public BeanIndicador(){
        
    }
    public BeanIndicador(String descripcionIndicador,Integer nidIndicador,Integer indice,String leyenda,Integer valor){
        this.descripcionIndicador = descripcionIndicador;
        this.nidIndicador = nidIndicador;
        this.indice = indice;
        this.leyenda = leyenda;
        this.valor = valor;
    }
    
    public BeanIndicador(String descripcionIndicador,Integer nidIndicador){
        this.descripcionIndicador = descripcionIndicador;
        this.nidIndicador = nidIndicador;   
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

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Integer getIndice() {
        return indice;
    }
}
