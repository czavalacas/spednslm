package sped.negocio.BDL.IL;

import java.sql.Time;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.sist.RestriccionHorario;

@Local
public interface BDL_C_SFRestriccionHorarioLocal {
    List<RestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid);
    RestriccionHorario findRestriccionHorarioById(int nid);
    List<RestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                          int dia,
                                                          Time inicio, 
                                                          Time fin);
}
