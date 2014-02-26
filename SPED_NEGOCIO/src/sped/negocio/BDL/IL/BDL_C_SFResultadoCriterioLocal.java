package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Local
public interface BDL_C_SFResultadoCriterioLocal {
    List<ResultadoCriterio> getResultadoCriterioFindAll();
    ResultadoCriterio getResCriByFichaEvaBDL(int nidEvaluacion, 
                                             FichaCriterio fichaCriterio);
    List<ResultadoCriterio> getResultadoCriterio_ByEvaluacion(Integer nidEvaluacion);
}
