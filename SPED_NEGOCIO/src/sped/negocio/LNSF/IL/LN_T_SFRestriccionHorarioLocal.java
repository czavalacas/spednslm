package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanRestriccionHorario;

@Local
public interface LN_T_SFRestriccionHorarioLocal {
    void grabarRestricciones(List<BeanRestriccionHorario> list);
}
