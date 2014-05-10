package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.ConfiguracionHorario;

@Remote
public interface BDL_T_SFConfiguracionHorarioRemoto {
    ConfiguracionHorario persistConfiguracionHorario(ConfiguracionHorario configuracionHorario);

    ConfiguracionHorario mergeConfiguracionHorario(ConfiguracionHorario configuracionHorario);

    void removeConfiguracionHorario(ConfiguracionHorario configuracionHorario);
}
