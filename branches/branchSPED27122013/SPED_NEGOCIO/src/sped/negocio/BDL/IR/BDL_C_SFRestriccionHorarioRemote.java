package sped.negocio.BDL.IR;

import java.sql.Time;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.sist.RestriccionHorario;

@Remote
public interface BDL_C_SFRestriccionHorarioRemote {
    List<RestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid);
    RestriccionHorario findRestriccionHorarioById(int nid);
    List<RestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                          int dia,
                                                          Time inicio, 
                                                          Time fin);
}
