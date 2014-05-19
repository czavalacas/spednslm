package sped.negocio.LNSF.IL;

import java.sql.Time;

import javax.ejb.Local;

@Local
public interface LN_T_SFMainLocal {
    void gestionarMain_LN(int tipoEvento,
                          int nidMain,
                          String dniProfesor,
                          int nidAula,
                          int nidCurso,
                          int nDia,
                          Time horaInicio,
                          Time horaFin);
    void eliminarMain_LN(int nidMain);
}
