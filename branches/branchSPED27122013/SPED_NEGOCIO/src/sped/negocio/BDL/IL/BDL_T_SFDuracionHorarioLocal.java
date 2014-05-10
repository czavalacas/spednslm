package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.DuracionHorario;

@Local
public interface BDL_T_SFDuracionHorarioLocal {
    DuracionHorario persistDuracionHorario(DuracionHorario duracionHorario);

    DuracionHorario mergeDuracionHorario(DuracionHorario duracionHorario);

    void removeDuracionHorario(DuracionHorario duracionHorario);
}
