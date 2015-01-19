package sped.negocio.LNSF.IL;

import java.sql.Time;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanRestriccionHorario;

@Local
public interface LN_C_SFRestriccionHorarioLocal {
    List<BeanRestriccionHorario> listRestriccionHorarioByNidExcepcion(String tipo, String nid);
    List<BeanRestriccionHorario> countCruceRestriccionHorario(String codigo, 
                                                              int dia,
                                                              Time inicio, 
                                                              Time fin);
}
