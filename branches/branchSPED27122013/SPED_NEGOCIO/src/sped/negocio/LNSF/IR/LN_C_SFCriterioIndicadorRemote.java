package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanCriterioIndicadorWS;
import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface LN_C_SFCriterioIndicadorRemote {
    List<BeanCriterioIndicador> transformLstCriterioIndicador(List<CriterioIndicador> lstCrIn,
                                                              int nidEvaluacion);
    /**
     * Metodo que retorna la lista de indicadores cuando se seleccione un criterio para el MOVIL
     * @param nidFicha
     * @param nidCriterio
     * @author dfloresgonz
     * @since 06.02.2014
     * @return
     */
    List<BeanCriterioIndicadorWS> getLstIndicadoresByFichaCriterio_LN_WS(int nidFicha, int nidCriterio);
}
