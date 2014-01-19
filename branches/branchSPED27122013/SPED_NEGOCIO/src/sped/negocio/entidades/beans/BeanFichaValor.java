package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import sped.negocio.entidades.eval.Ficha;
import sped.negocio.entidades.eval.Leyenda;
import sped.negocio.entidades.eval.Valor;

public class BeanFichaValor implements Serializable {
    @SuppressWarnings("compatibility:1777642705275152527")
    private static final long serialVersionUID = 1L;
    
    private int nidFichaValor;
    private BeanValor valor;
    //private List<Leyenda> leyendaLista;
    private BeanFicha ficha;


    public void setNidFichaValor(int nidFichaValor) {
        this.nidFichaValor = nidFichaValor;
    }

    public int getNidFichaValor() {
        return nidFichaValor;
    }

    public void setValor(BeanValor valor) {
        this.valor = valor;
    }

    public BeanValor getValor() {
        return valor;
    }

    public void setFicha(BeanFicha ficha) {
        this.ficha = ficha;
    }

    public BeanFicha getFicha() {
        return ficha;
    }
}
