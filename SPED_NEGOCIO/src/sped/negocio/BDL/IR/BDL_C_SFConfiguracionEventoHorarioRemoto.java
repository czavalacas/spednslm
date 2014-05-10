package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.ConfiguracionEventoHorario;

@Remote
public interface BDL_C_SFConfiguracionEventoHorarioRemoto {
    List<ConfiguracionEventoHorario> getConfiguracionEventoHorarioFindAll();
    ConfiguracionEventoHorario finEventoById(int id);
}
