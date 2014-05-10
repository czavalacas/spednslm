package sped.negocio.LNSF.IL;

import java.sql.Time;

import javax.ejb.Local;

@Local
public interface LN_T_SFDuracionHorarioLocal {
    String registrarDuracionHorario_LN(int nidSede, 
                                             int nidNivel, 
                                             Time hora_inicio, 
                                             Time duracion, 
                                             int maxBloque,
                                             int numBloque);
}
