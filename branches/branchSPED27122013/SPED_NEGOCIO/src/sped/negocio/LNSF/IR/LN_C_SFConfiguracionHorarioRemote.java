package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanConfiguracionHorario;

@Remote
public interface LN_C_SFConfiguracionHorarioRemote {
    List<BeanConfiguracionHorario> getConfiguracionBySedeNivel(int nidSede,
                                                               int nidNivel);
}
