package sped.negocio.LNSF.IR;

import java.util.HashSet;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanIndicador;

@Remote
public interface LN_C_SFIndicadorRemote {
    
    List<BeanIndicador> getIndicadoresByAttr_LN(String descIndicador, 
                                                int nidIndicador,
                                                List<BeanCriterio> lstCritsArbol);
    List<BeanIndicador> getIndicadoresByDescripcion_LN(String Indicador);
    List getNombreIndicadores_LN();
    BeanIndicador getIndicadorByDescripcion(String descripcion);
    List<Object[]> getNombreIndicadoresNew_LN();
}
