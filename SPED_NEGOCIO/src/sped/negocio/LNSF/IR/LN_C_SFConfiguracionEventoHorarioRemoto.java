package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;

@Remote
public interface LN_C_SFConfiguracionEventoHorarioRemoto {
    List<BeanCombo> getAllEventosDeHorario();
    BeanConfiguracionEventoHorario getEventoHorarioByID(int id);
}
