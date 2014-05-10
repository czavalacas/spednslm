package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.ConfiguracionEventoHorario;

@Local
public interface BDL_C_SFConfiguracionEventoHorarioLocal {
    List<ConfiguracionEventoHorario> getConfiguracionEventoHorarioFindAll();
    ConfiguracionEventoHorario finEventoById(int id);
}
