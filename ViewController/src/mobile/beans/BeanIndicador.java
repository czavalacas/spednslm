package mobile.beans;

import java.io.Serializable;

public class BeanIndicador implements Serializable {
    
    private String descripcionIndicador;
    private Integer nidIndicador;
    private Double nota;
    private String leyenda;
    private Integer valor;

    public BeanIndicador(){
        
    }
    public BeanIndicador(String descripcionIndicador,Integer nidIndicador,Double nota,String leyenda,Integer valor){
        this.descripcionIndicador = descripcionIndicador;
        this.nidIndicador = nidIndicador;
        this.nota = nota;
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

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getNota() {
        return nota;
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
}
