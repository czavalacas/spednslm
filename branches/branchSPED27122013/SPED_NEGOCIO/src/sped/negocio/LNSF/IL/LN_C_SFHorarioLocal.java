package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanHorario;

@Local
public interface LN_C_SFHorarioLocal {
    BeanHorario getHorario();
}
