package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface BDL_T_SFEvaluacionRemoto {
   
    Evaluacion persistEvaluacion(Evaluacion evaluacion);

    Evaluacion mergeEvaluacion(Evaluacion evaluacion);

    void removeEvaluacion(Evaluacion evaluacion);
}
