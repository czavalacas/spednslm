package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanHorario;

@Remote
public interface LN_C_SFHorarioRemote {
    BeanHorario getHorario();
}
