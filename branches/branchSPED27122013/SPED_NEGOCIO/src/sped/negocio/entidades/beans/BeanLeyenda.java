package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanLeyenda implements Serializable {
    @SuppressWarnings("compatibility:-9166708465177607483")
    private static final long serialVersionUID = 1L;
    
    private String descripcionLeyenda;
    private BeanCriterioIndicador criterioIndicador;
    private BeanFichaValor fichaValor;
    private String header;

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setDescripcionLeyenda(String descripcionLeyenda) {
        this.descripcionLeyenda = descripcionLeyenda;
    }

    public String getDescripcionLeyenda() {
        return descripcionLeyenda;
    }

    public void setCriterioIndicador(BeanCriterioIndicador criterioIndicador) {
        this.criterioIndicador = criterioIndicador;
    }

    public BeanCriterioIndicador getCriterioIndicador() {
        return criterioIndicador;
    }

    public void setFichaValor(BeanFichaValor fichaValor) {
        this.fichaValor = fichaValor;
    }

    public BeanFichaValor getFichaValor() {
        return fichaValor;
    }
}