package sped.negocio.LNSF.IR;

import java.sql.Time;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanMainWS;

@Remote
public interface LN_T_SFMainRemote {
    void gestionarMain_LN(int tipoEvento,
                          int nidMain,
                          String dniProfesor,
                          int nidAula,
                          int nidCurso,
                          int nDia,
                          int nidLeccion,
                          Time horaInicio,
                          Time horaFin);
    void eliminarMain_LN(int nidMain);
    void eliminarMainByAulaProfesor(String codigo, int nidSede, int nidNivel, boolean vista);
    void eliminarMainByLecc(int nidLeccion, int cantidad);
    String agregarMainMigracion(List<BeanMainWS> lstMains, String modo);
}