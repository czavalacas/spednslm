package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanIndicador;

@Remote
public interface LN_T_SFIndicadorRemote {
    BeanIndicador registrarIndicador(String descIndicador);
}
