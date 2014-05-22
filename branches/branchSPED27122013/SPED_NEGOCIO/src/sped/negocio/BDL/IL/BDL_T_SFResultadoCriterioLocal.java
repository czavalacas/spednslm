package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.ResultadoCriterio;

@Local
public interface BDL_T_SFResultadoCriterioLocal {
    ResultadoCriterio persistResultadoCriterio(ResultadoCriterio resultadoCriterio);

    ResultadoCriterio mergeResultadoCriterio(ResultadoCriterio resultadoCriterio);

    void removeResultadoCriterio(ResultadoCriterio resultadoCriterio);
    int removerResultadoCriterioByEvaluacion(int nidEvaluacion);
}
