package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.eval.FichaCriterio;

@Local
public interface LN_T_SFResultadoCriterioLocal {
    void registrarResultadoCriterios_LN(List<BeanCriterio> lstBCrit,Evaluacion evaluacion);
}
