package sped.negocio.BDL.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Local
public interface BDL_C_SFEvaluacionLocal {
    List<Evaluacion> getEvaluacionFindAll();
    Evaluacion getEvaluacionById(String nidDate);
    List<Evaluacion> getEvaluaciones(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador, String dniProfesor, String nidCurso);
    List<Evaluacion> getEvaluacionesByUsuarioBDL(BeanUsuario beanUsuario,
                                                 BeanEvaluacion beanFiltroEva);
    List<Evaluacion> getPlanificacion(BeanEvaluacion beanEvaluacion);
}
