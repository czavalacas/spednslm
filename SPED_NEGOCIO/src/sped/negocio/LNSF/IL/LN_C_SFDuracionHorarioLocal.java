package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanDuracionHorario;

@Local
public interface LN_C_SFDuracionHorarioLocal {
    BeanDuracionHorario getDuracionHorarioBySedeNivel(int nidSede,
                                                      int nidNivel);
}
