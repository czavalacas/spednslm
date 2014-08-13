package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Resultado;

@Remote
public interface BDL_C_SFResultadoRemote {
    List<Resultado> getResultadoFindAll();
    boolean fichaUsadaEnEvaluacion(int nidFicha);
    Resultado findResultadoById(int criterioIndicador,
                                int evaluacion);
    List<Resultado> getResultadoByEvaluacionCriterio_BDL(int nidEvaluacion,int nidCriterio);
    double getValorResultadoByNidCriterioIndicador_Evaluacion(int nidCriterioIndicador,
                                                           int nidEvaluacion);
}
