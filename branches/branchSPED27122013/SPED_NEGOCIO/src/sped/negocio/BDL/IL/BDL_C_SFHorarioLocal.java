package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.Horario;

@Local
public interface BDL_C_SFHorarioLocal {
    List<Horario> getHorarioFindAll();
}
