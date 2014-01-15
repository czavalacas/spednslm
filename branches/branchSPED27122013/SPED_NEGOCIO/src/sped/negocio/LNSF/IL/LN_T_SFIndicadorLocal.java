package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanIndicador;

@Local
public interface LN_T_SFIndicadorLocal {
    BeanIndicador registrarIndicador(String descIndicador);
}
