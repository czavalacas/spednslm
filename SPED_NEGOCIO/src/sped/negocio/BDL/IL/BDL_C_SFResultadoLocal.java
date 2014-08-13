package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Resultado;

@Local
public interface BDL_C_SFResultadoLocal {
    List<Resultado> getResultadoFindAll();
    boolean fichaUsadaEnEvaluacion(int nidFicha);
    Resultado findResultadoById(int criterioIndicador,
                                int evaluacion);
    List<Resultado> getResultadoByEvaluacionCriterio_BDL(int nidEvaluacion,int nidCriterio);
    double getValorResultadoByNidCriterioIndicador_Evaluacion(int nidCriterioIndicador,
                                                           int nidEvaluacion);
}
