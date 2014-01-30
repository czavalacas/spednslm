package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanResultadoCriterio implements Serializable {
    @SuppressWarnings("compatibility:8458195718273460025")
    private static final long serialVersionUID = 1L;
    
    private int nidResultadoCriterio;
    private double valor;
    private BeanEvaluacion evaluacion;
    private BeanFichaCriterio fichaCriterio;

    public void setNidResultadoCriterio(int nidResultadoCriterio) {
        this.nidResultadoCriterio = nidResultadoCriterio;
    }

    public int getNidResultadoCriterio() {
        return nidResultadoCriterio;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setEvaluacion(BeanEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public BeanEvaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setFichaCriterio(BeanFichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public BeanFichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }

}
