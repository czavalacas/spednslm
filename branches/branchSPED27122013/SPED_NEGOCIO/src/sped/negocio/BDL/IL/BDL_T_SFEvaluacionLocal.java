package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Evaluacion;

@Local
public interface BDL_T_SFEvaluacionLocal {
    Evaluacion persistEvaluacion(Evaluacion evaluacion);

    Evaluacion mergeEvaluacion(Evaluacion evaluacion);

    void removeEvaluacion(Evaluacion evaluacion);
}
