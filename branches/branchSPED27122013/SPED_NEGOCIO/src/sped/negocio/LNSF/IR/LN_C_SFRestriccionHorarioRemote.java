package sped.negocio.LNSF.IR;

import java.sql.Time;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanRestriccionHorario;

@Remote
public interface LN_C_SFRestriccionHorarioRemote {
    List<BeanRestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid);
    List<BeanRestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                              int dia,
                                                              Time inicio, 
                                                              Time fin);
}
