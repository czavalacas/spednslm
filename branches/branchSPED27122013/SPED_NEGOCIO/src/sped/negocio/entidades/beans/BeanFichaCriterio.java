package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

public class BeanFichaCriterio implements Serializable {
    @SuppressWarnings("compatibility:-1710919342373990512")
    private static final long serialVersionUID = 1L;

    private BeanCriterio criterio;
    private BeanFicha ficha;
    private List<BeanResultadoCriterio> LstresultadoCriterio;
    private List<BeanCriterioIndicador> LstcriterioIndicador;

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

    public void setLstresultadoCriterio(List<BeanResultadoCriterio> LstresultadoCriterio) {
        this.LstresultadoCriterio = LstresultadoCriterio;
    }

    public List<BeanResultadoCriterio> getLstresultadoCriterio() {
        return LstresultadoCriterio;
    }

    public void setLstcriterioIndicador(List<BeanCriterioIndicador> LstcriterioIndicador) {
        this.LstcriterioIndicador = LstcriterioIndicador;
    }

    public List<BeanCriterioIndicador> getLstcriterioIndicador() {
        return LstcriterioIndicador;
    }
}
