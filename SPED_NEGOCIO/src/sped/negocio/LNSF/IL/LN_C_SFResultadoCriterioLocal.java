package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanResultadoCriterio;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Local
public interface LN_C_SFResultadoCriterioLocal {
    List<BeanResultadoCriterio> transformLstResultadoCriterio(List<ResultadoCriterio> lstResultado);
    BeanResultadoCriterio getResCriByFichaEvaLN(int nidEvaluacion, 
                                                FichaCriterio fichaCriterio);
    List<BeanResultadoCriterio> getResultadoCriterio_ByEvaluacion(Integer nidEvaluacion);
}
