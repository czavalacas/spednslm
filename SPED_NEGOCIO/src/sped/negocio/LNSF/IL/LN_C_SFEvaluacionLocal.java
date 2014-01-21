package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Local
public interface LN_C_SFEvaluacionLocal {
    List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario);
    List<BeanEvaluacion> transformLstEvaluacion(List<Evaluacion> lstEvaluacion);
}
