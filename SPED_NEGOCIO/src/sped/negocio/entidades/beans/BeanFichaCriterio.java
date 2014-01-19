package sped.negocio.entidades.beans;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.Ficha;

public class BeanFichaCriterio implements Serializable {
    @SuppressWarnings("compatibility:-1710919342373990512")
    private static final long serialVersionUID = 1L;

    private BeanCriterio criterio;
    private BeanFicha ficha;

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
}
