package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanDuracionHorario;

@Remote
public interface LN_C_SFDuracionHorarioRemote {
    BeanDuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                      int nidNivel);
}
