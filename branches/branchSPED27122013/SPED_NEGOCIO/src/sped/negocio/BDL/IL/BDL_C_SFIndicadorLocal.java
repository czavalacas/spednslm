package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.eval.Indicador;

@Local
public interface BDL_C_SFIndicadorLocal {
    
    List<Indicador> getIndicadoresByAttr_BD(BeanIndicador beanIndicador);
    List<Indicador> getIndicadoresByDescripcion(String Indicador);
    List getNombreIndicadores();
    Indicador getIndicadorByDescripcion(String descripcion);
}
