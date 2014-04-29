package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.ConfiguracionHorario;

@Remote
public interface BDL_C_SFConfiguracionHorarioRemote {
    List<ConfiguracionHorario> getConfiguracionBySedeNivel(int nidSede,
                                                           int nidNivel);
}
