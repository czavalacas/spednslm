package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanIndicador;

@Local
public interface LN_C_SFIndicadorLocal {
    
    List<BeanIndicador> getIndicadoresByAttr_LN(String descIndicador, 
                                                int nidIndicador);
}
