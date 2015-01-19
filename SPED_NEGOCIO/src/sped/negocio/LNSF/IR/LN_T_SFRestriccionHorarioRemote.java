package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanRestriccionHorario;

@Remote
public interface LN_T_SFRestriccionHorarioRemote {
    void grabarRestricciones(List<BeanRestriccionHorario> list);
}
