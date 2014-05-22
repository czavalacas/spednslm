package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Resultado;

@Remote
public interface BDL_T_SFResultadoRemote {
    Resultado persistResultado(Resultado resultado);

    Resultado mergeResultado(Resultado resultado);

    void removeResultado(Resultado resultado);
    int removerResultadosByEvaluacion(int nidEvaluacion);
}
