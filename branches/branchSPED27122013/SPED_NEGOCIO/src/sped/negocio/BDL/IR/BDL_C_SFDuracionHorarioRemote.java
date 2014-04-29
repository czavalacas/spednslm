package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.DuracionHorario;

@Remote
public interface BDL_C_SFDuracionHorarioRemote {
    DuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                  int nidNivel);
}
