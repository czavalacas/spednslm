package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.eval.FichaCriterio;

@Remote
public interface LN_T_SFResultadoCriterioRemote {
    
    void registrarResultadoCriterios_LN(List<BeanCriterio> lstBCrit,Evaluacion evaluacion);
}
