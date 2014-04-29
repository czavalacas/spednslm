package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.sist.DuracionHorario;

@Local
public interface BDL_C_SFDuracionHorarioLocal {
    DuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                  int nidNivel);
}
