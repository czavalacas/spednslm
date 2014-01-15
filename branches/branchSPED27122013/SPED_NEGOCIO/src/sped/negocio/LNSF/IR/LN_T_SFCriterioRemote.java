package sped.negocio.LNSF.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;

@Remote
public interface LN_T_SFCriterioRemote {
    
    BeanCriterio registrarCriterio(String descCriterio);
}
