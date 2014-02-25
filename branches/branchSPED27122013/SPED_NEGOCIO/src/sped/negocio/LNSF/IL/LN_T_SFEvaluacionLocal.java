package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanIndicadorValorWS;
import sped.negocio.entidades.eval.Evaluacion;

@Local
public interface LN_T_SFEvaluacionLocal {
    String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal,
                                     Integer nidEvaluacion,
                                     Integer nidUsuario,
                                     Integer nidLog,
                                     String comentarioEvaluador);
    String registrarEvaluacion_LN(Evaluacion eva); 
    String removerEvaluacion_LN(Evaluacion eva);
}
