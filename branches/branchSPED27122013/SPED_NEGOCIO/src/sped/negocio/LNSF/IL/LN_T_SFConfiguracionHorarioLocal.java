package sped.negocio.LNSF.IL;

import java.sql.Time;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanConfiguracionHorario;

@Local
public interface LN_T_SFConfiguracionHorarioLocal {
    String registrarConfiguracionHorario_LN(int nidSede, 
                                  int nidNivel, 
                                  Time hora_inicio, 
                                  Time hora_fin, 
                                  int nidConfev);
    String actualizarConfiguracionHorario_LN(BeanConfiguracionHorario beanConfi, 
                                                        int accion);
}
