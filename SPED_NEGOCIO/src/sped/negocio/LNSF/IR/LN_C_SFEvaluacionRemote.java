package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface LN_C_SFEvaluacionRemote {
    List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario);
    List<BeanEvaluacion> transformLstEvaluacion(List<Evaluacion> lstEvaluacion);
}
