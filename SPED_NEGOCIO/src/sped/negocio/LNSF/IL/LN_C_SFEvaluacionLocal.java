package sped.negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Local
public interface LN_C_SFEvaluacionLocal {
    List<BeanEvaluacion> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario,
                                                    int nidSede,
                                                    int nidNivel,
                                                    int nidArea,
                                                    int nidCurso,
                                                    int nidGrado,
                                                    String estado,
                                                    String nomProfesor,
                                                    String nomEvaluador,
                                                    Date fechaPlanifiacion,
                                                    Date fechaPlanifiacionF,
                                                    Date fechaEvaluacion,
                                                    Date fachaEvaluacionF);
    List<BeanEvaluacion> getPlanificacion(BeanEvaluacion beanEvaluacion);
    /**
     * Metodo de Logica que retorna las planificaciones para el usuario Movil (WS)
     * @param nidRol
     * @param nidSede
     * @param nidAreaAcademica
     * @param nidUsuario
     * @param nombresProfesor
     * @param curso
     * @param nidSedeFiltro
     * @param nidAAFiltro
     * @author dfloresgonz
     * @since 04.02.2014
     * @return List<BeanEvaluacion>
     */
    List<BeanEvaluacionWS> getPlanificaciones_LN_WS(int nidRol,
                                                  int nidSede,
                                                  int nidAreaAcademica,
                                                  int nidUsuario,
                                                  String nombresProfesor,
                                                  String curso,
                                                  int nidSedeFiltro,
                                                  int nidAAFiltro);
    List<BeanEvaluacion> getDesempenoEvaluacionbyFiltroLN(int tipoBusqueda,
                                                          String parametro,
                                                          String parametro2,
                                                          List lstnidRol,
                                                          List lstnidEva,
                                                          List lstnidSede,
                                                          List lstnidArea,
                                                          Date fechaPlanifiacion,
                                                          Date fechaPlanifiacionF,
                                                          Date fechaEvaluacion,
                                                          Date fachaEvaluacionF);
    List<BeanEvaluacionWS> getEvaluaciones_LN_WS(int nidRol,
                                                int nidSede,
                                                int nidAreaAcademica,
                                                int nidUsuario,
                                                String nombresProfesor,
                                                String curso,
                                                int nidSedeFiltro,
                                                int nidAAFiltro,
                                                String estado,
                                                Date fechaMin,
                                                Date fechaMax,
                                                String tipoVisita,
                                                Integer nidPlanificador,
                                                Integer nidEvaluador);
    List<BeanConstraint> getTipoVisitaLN();
    BeanConstraint getTipoVisita_ByValorLN(String valor);
    BeanEvaluacion getEvaluacionById_LN(String nidDate);
    List<BeanEvaluacion> getEvaluaciones_LN(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador,
                                                       String dniProfesor, String nidCurso, Integer nidSede);
    
}
