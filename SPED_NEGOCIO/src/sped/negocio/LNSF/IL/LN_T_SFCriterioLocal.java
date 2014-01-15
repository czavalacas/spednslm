package sped.negocio.LNSF.IL;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;

@Local
public interface LN_T_SFCriterioLocal {
    BeanCriterio registrarCriterio(String descCriterio);
}
