package sped.negocio.LNSF.IL;

import javax.ejb.Local;

@Local
public interface LN_C_SFResultadoLocal {
    boolean fichaUsadaEnEvaluacion_LN(int nidFicha);
}
