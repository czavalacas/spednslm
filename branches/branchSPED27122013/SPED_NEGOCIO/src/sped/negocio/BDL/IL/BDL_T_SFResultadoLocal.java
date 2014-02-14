package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Resultado;

@Local
public interface BDL_T_SFResultadoLocal {
    Resultado persistResultado(Resultado resultado);

    Resultado mergeResultado(Resultado resultado);

    void removeResultado(Resultado resultado);
}
