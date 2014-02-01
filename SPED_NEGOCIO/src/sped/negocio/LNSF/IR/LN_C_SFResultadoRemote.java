package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

@Remote
public interface LN_C_SFResultadoRemote {
    boolean fichaUsadaEnEvaluacion_LN(int nidFicha);
}
