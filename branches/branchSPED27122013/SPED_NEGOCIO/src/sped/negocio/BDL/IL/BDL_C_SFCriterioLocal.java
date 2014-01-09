package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;

@Local
public interface BDL_C_SFCriterioLocal {
    List<Criterio> getCriteriosByAttr_BDL(BeanCriterio beanCriterio);
}
