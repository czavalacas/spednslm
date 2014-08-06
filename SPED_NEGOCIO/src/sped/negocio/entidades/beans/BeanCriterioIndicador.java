package sped.negocio.entidades.beans;

import java.io.Serializable;

import sped.negocio.entidades.eval.FichaCriterio;

public class BeanCriterioIndicador implements Serializable {
    @SuppressWarnings("compatibility:4617439608295435702")
    private static final long serialVersionUID = 1L;
    private Integer nidCriterioIndicador;
    private BeanFichaCriterio fichaCriterio;
    //private List<Leyenda> leyendaLista;
    private int orden;
    private BeanIndicador indicador;
    private BeanResultado resultadoEvaluacion;
    private BeanLeyenda leyenda;
    private double maxValor;

   /* @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BeanCriterioIndicador){
            BeanCriterioIndicador crit = (BeanCriterioIndicador) obj;
            return this.getNidCriterioIndicador().equals(crit.getNidCriterioIndicador());
        }else{
            return false;
        }
    }
*/
    public void setMaxValor(double maxValor) {
        this.maxValor = maxValor;
    }

    public double getMaxValor() {
        return maxValor;
    }

    public void setNidCriterioIndicador(Integer nidCriterioIndicador) {
        this.nidCriterioIndicador = nidCriterioIndicador;
    }

    public Integer getNidCriterioIndicador() {
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

    public void setResultadoEvaluacion(BeanResultado resultadoEvaluacion) {
        this.resultadoEvaluacion = resultadoEvaluacion;
    }

    public BeanResultado getResultadoEvaluacion() {
        return resultadoEvaluacion;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }
    
    public void setLeyenda(BeanLeyenda leyenda) {
        this.leyenda = leyenda;
    }

    public BeanLeyenda getLeyenda() {
        return leyenda;
    }
}
