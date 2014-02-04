package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface LN_C_SFCriterioIndicadorLocal {
    List<BeanCriterioIndicador> transformLstCriterioIndicador(List<CriterioIndicador> lstCrIn,
                                                              int nidEvaluacion);
}
