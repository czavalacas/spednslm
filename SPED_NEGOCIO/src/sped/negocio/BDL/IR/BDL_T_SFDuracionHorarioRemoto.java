package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.DuracionHorario;

@Remote
public interface BDL_T_SFDuracionHorarioRemoto {
    DuracionHorario persistDuracionHorario(DuracionHorario duracionHorario);

    DuracionHorario mergeDuracionHorario(DuracionHorario duracionHorario);

    void removeDuracionHorario(DuracionHorario duracionHorario);
}
