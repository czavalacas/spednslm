package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Remote
public interface BDL_C_SFResultadoCriterioRemote {
    List<ResultadoCriterio> getResultadoCriterioFindAll();
    ResultadoCriterio getResCriByFichaEvaBDL(int nidEvaluacion, 
                                             FichaCriterio fichaCriterio);
    List<ResultadoCriterio> getResultadoCriterio_ByEvaluacion(Integer nidEvaluacion);
    double getValorByFichaEvaluacionCriterio(int nidFicha,int nidEvaluacion,int nidCriterio);
}
