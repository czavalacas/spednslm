package sped.negocio.LNSF.IR;

import java.sql.Time;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFConfiguracionHorarioRemoto {
    String registrarConfiguracionHorario_LN(int nidSede, 
                                  int nidNivel, 
                                  Time hora_inicio, 
                                  Time hora_fin, 
                                  int nidConfev);
}
