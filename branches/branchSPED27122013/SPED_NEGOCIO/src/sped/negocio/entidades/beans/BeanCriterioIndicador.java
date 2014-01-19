package sped.negocio.entidades.beans;

import java.io.Serializable;

public class BeanCriterioIndicador implements Serializable {
    @SuppressWarnings("compatibility:4617439608295435702")
    private static final long serialVersionUID = 1L;
    private int nidCriterioIndicador;
    private BeanFichaCriterio fichaCriterio;
    //private List<Leyenda> leyendaLista;
    private BeanIndicador indicador;
    //private List<Resultado> resultadoLista;
    
    public void setNidCriterioIndicador(int nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }

    public int getNidCriterioIndicador() {
        return nidCriterioIndicador;
    }

    public void setFichaCriterio(BeanFichaCriterio fichaCriterio) {
        this.fichaCriterio = fichaCriterio;
    }

    public BeanFichaCriterio getFichaCriterio() {
        return fichaCriterio;
    }

    public void setIndicador(BeanIndicador indicador) {
        this.indicador = indicador;
    }

    public BeanIndicador getIndicador() {
        return indicador;
    }
}
