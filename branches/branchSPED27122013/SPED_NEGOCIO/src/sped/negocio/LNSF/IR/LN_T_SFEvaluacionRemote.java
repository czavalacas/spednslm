package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanIndicadorValorWS;
import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface LN_T_SFEvaluacionRemote {
    String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal, Integer nidEvaluacion,Integer nidUsuario,Integer nidLog);
    String registrarEvaluacion_LN(Evaluacion eva);
    String removerEvaluacion_LN(Evaluacion eva);
}
