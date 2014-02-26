package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanResultadoCriterio;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Remote
public interface LN_C_SFResultadoCriterioRemote {
    List<BeanResultadoCriterio> transformLstResultadoCriterio(List<ResultadoCriterio> lstResultado);
    BeanResultadoCriterio getResCriByFichaEvaLN(int nidEvaluacion, 
                                                       FichaCriterio fichaCriterio);
    List<BeanResultadoCriterio> getResultadoCriterio_ByEvaluacion(Integer nidEvaluacion);
}
