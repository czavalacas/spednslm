package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.eval.Indicador;

@Remote
public interface BDL_C_SFIndicadorRemote {
    
    List<Indicador> getIndicadoresByAttr_BD(BeanIndicador beanIndicador);
}
