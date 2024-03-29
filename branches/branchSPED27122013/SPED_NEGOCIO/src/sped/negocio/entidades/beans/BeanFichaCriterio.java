package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanFichaCriterio implements Serializable {
    @SuppressWarnings("compatibility:-1710919342373990512")
    private static final long serialVersionUID = 1L;

    private BeanCriterio criterio;
    private BeanFicha ficha;
    private BeanResultadoCriterio resultadoCriterio;
    private List<BeanCriterioIndicador> LstcriterioIndicador;
    private String descripcionCriterio;
    private int nidCriterio;//dfloresgonz 07.08.2014 Variable auxiliar usada en LN_C_SFFichaCriterioBean.getLstFichaCriterioByEvaluacion
    private double maxValCriterio;//dfloresgonz 07.08.2014 Variable auxiliar usada en LN_C_SFFichaCriterioBean.getLstFichaCriterioByEvaluacion
    private double maxSumaFicha;//dfloresgonz 07.08.2014 igual valor en todo dependiendo de la ficha
    private List<BeanCriterioValor> criterioValoresLista;

    public void setCriterioValoresLista(List<BeanCriterioValor> criterioValoresLista) {
        this.criterioValoresLista = criterioValoresLista;
    }

    public List<BeanCriterioValor> getCriterioValoresLista() {
        return criterioValoresLista;
    }

    public void setMaxSumaFicha(double maxSumaFicha) {
        this.maxSumaFicha = maxSumaFicha;
    }

    public double getMaxSumaFicha() {
        return maxSumaFicha;
    }

    public void setMaxValCriterio(double maxValCriterio) {
        this.maxValCriterio = maxValCriterio;
    }

    public double getMaxValCriterio() {
        return maxValCriterio;
    }

    public void setNidCriterio(int nidCriterio) {
        this.nidCriterio = nidCriterio;
    }

    public int getNidCriterio() {
        return nidCriterio;
    }

    public void setCriterio(BeanCriterio criterio) {
        this.criterio = criterio;
    }

    public BeanCriterio getCriterio() {
        return criterio;
    }

    public void setFicha(BeanFicha ficha) {
        this.ficha = ficha;
    }

    public BeanFicha getFicha() {
        return ficha;
    }

    public void setResultadoCriterio(BeanResultadoCriterio resultadoCriterio) {
        this.resultadoCriterio = resultadoCriterio;
    }

    public BeanResultadoCriterio getResultadoCriterio() {
        return resultadoCriterio;
    }

    public void setLstcriterioIndicador(List<BeanCriterioIndicador> LstcriterioIndicador) {
        this.LstcriterioIndicador = LstcriterioIndicador;
    }

    public List<BeanCriterioIndicador> getLstcriterioIndicador() {
        return LstcriterioIndicador;
    }

    public void setDescripcionCriterio(String descripcionCriterio) {
        this.descripcionCriterio = descripcionCriterio;
    }

    public String getDescripcionCriterio() {
        return descripcionCriterio;
    }
}
