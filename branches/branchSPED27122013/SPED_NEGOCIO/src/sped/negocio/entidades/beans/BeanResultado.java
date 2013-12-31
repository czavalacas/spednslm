package sped.negocio.entidades.beans;

import java.io.Serializable;

import sped.negocio.entidades.eval.Evaluacion;

public class BeanResultado implements Serializable {
    @SuppressWarnings("compatibility:-4633930993697628293")
    private static final long serialVersionUID = 1L;

    private short valor;
    private BeanCriterioIndicador criterioIndicador;
    private BeanEvaluacion evaluacion;

    public void setValor(short valor) {
        this.valor = valor;
    }

    public short getValor() {
        return valor;
    }

    public void setCriterioIndicador(BeanCriterioIndicador criterioIndicador) {
        this.criterioIndicador = criterioIndicador;
    }

    public BeanCriterioIndicador getCriterioIndicador() {
        return criterioIndicador;
    }

    public void setEvaluacion(BeanEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public BeanEvaluacion getEvaluacion() {
        return evaluacion;
    }

}
