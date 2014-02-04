package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface BDL_C_SFEvaluacionRemoto {
    List<Evaluacion> getEvaluacionFindAll();
    Evaluacion getEvaluacionById(String nidDate);
    List<Evaluacion> getEvaluaciones(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador, String dniProfesor, String nidCurso, Integer nidSede);
    List<Evaluacion> getEvaluacionesByUsuarioBDL(BeanUsuario beanUsuario,
                                                 BeanEvaluacion beanFiltroEva);
    List<Evaluacion> getPlanificacion(BeanEvaluacion beanEvaluacion);
    List<Constraint> getTipoVisita();
    Constraint getTipoVisitaByValor(String valor);
}
