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
}
