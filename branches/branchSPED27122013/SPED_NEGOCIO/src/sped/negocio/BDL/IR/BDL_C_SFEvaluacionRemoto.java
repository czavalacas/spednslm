package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface BDL_C_SFEvaluacionRemoto {
    List<Evaluacion> getEvaluacionFindAll();
    Evaluacion getEvaluacionById(String nidDate);
}
