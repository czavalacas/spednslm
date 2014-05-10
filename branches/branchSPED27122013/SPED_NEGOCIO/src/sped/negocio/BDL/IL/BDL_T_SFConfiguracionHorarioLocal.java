package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.ConfiguracionHorario;

@Local
public interface BDL_T_SFConfiguracionHorarioLocal {
    ConfiguracionHorario persistConfiguracionHorario(ConfiguracionHorario configuracionHorario);

    ConfiguracionHorario mergeConfiguracionHorario(ConfiguracionHorario configuracionHorario);

    void removeConfiguracionHorario(ConfiguracionHorario configuracionHorario);
}
