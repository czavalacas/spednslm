package sped.negocio.LNSF.IR;

import java.sql.Time;

import javax.ejb.Remote;

@Remote
public interface LN_T_SFMainRemote {
    void gestionarMain_LN(int tipoEvento,
                          int nidMain,
                          String dniProfesor,
                          int nidAula,
                          int nidCurso,
                          int nDia,
                          Time horaInicio,
                          Time horaFin);
}
