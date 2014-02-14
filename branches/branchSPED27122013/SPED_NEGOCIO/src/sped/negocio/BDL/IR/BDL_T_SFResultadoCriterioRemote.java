package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.ResultadoCriterio;

@Remote
public interface BDL_T_SFResultadoCriterioRemote {
    ResultadoCriterio persistResultadoCriterio(ResultadoCriterio resultadoCriterio);

    ResultadoCriterio mergeResultadoCriterio(ResultadoCriterio resultadoCriterio);

    void removeResultadoCriterio(ResultadoCriterio resultadoCriterio);
}
