package sped.negocio.LNSF.IR;

import java.sql.Time;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanDuracionHorario;

@Remote
public interface LN_T_SFDuracionHorarioRemoto {
    String registrarDuracionHorario_LN(int nidSede, 
                                             int nidNivel, 
                                             Time hora_inicio, 
                                             Time duracion, 
                                             int maxBloque,
                                             int numBloque);
    String actualizarDuracionHorario_LN(BeanDuracionHorario beanDura);
}
