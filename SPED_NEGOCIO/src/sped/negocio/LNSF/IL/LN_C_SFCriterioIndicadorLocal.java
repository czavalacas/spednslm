package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanCriterioIndicadorWS;
import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface LN_C_SFCriterioIndicadorLocal {
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
