package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.eval.Indicador;

@Local
public interface BDL_T_SFIndicadorLocal {
    Indicador persistIndicador(Indicador indicador);

    Indicador mergeIndicador(Indicador indicador);

    void removeIndicador(Indicador indicador);
}
