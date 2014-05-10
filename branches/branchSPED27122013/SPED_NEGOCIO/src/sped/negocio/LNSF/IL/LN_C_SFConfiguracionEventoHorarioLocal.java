package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanConfiguracionEventoHorario;

@Local
public interface LN_C_SFConfiguracionEventoHorarioLocal {
    List<BeanCombo> getAllEventosDeHorario();
    BeanConfiguracionEventoHorario getEventoHorarioByID(int id);
}
