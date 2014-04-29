package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.ConfiguracionHorario;

@Local
public interface BDL_C_SFConfiguracionHorarioLocal {
    List<ConfiguracionHorario> getConfiguracionBySedeNivel(int nidSede,
                                                           int nidNivel);
}
