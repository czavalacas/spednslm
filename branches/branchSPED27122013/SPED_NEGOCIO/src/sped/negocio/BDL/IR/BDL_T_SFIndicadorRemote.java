package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.eval.Indicador;

@Remote
public interface BDL_T_SFIndicadorRemote {
    Indicador persistIndicador(Indicador indicador);

    Indicador mergeIndicador(Indicador indicador);

    void removeIndicador(Indicador indicador);
}
