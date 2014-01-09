package sped.negocio.BDL.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;

@Remote
public interface BDL_C_SFCriterioRemote {
    
    List<Criterio> getCriteriosByAttr_BDL(BeanCriterio beanCriterio);
}
