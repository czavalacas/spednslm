package sped.negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanEvaluacionPlani;
import sped.negocio.entidades.beans.BeanEvaluacionWS;
import sped.negocio.entidades.beans.BeanEvaluacion_DP;
import sped.negocio.entidades.beans.BeanFiltrosGraficos;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface LN_C_SFEvaluacionRemote {
    List<BeanEvaluacionPlani> getEvaluacionesByUsuarioLN(BeanUsuario beanUsuario,
                                                         int nidSede,
                                                         int nidNivel,
                                                         int nidArea,
                                                         int nidCurso,
                                                         int nidGrado,
                                                         String nomProfesor,
                                                         String nomEvaluador,
                                                         Date fechaPlanifiacion,
                                                         Date fechaPlanifiacionF,
                                                         Date fechaEvaluacion,
                                                         Date fachaEvaluacionF,
                                                         String tipEva);
    List<BeanEvaluacionPlani> getPlanificacion(BeanEvaluacion beanEvaluacion);
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
    List<BeanEvaluacionPlani> getDesempenoEvaluacionbyFiltroLN(int tipoBusqueda,
                                                               String nombre,
                                                               String estado,
                                                               String desProblema,
                                                               String desRol,
                                                               List lstnidRol,
                                                               List lstnidEva,
                                                               List lstnidSede,
                                                               List lstnidArea,
                                                               Date fechaPlanifiacion,
                                                               Date fechaPlanifiacionF,
                                                               Date fechaEvaluacion,
                                                               Date fachaEvaluacionF,
                                                               boolean estadoUsuario);
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
    BeanEvaluacionPlani getEvaluacionById_LN(String nidDate);
    List<BeanEvaluacion> getEvaluaciones_LN(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador,
                                            String dniProfesor, String nidCurso, Integer nidSede);
   /**
     * Metodo que trae la evaluacion consultada en el Movil - WS
     * @author dfloresgonz
     * @since 23.02.2014
     * @param nidEvaluacion
     * @return BeanEvaluacionWS
     */
    BeanEvaluacionWS getEvaluacionById_LN_WS(Integer nidEvaluacion);   
    List<BeanEvaluacion_DP> desempenoDocentePorEvaluacion(BeanFiltrosGraficos beanFiltros, String fechaHoy);
    double promedioGeneralPorFiltroDesempenoDocente(List<BeanEvaluacion_DP> listaEva_WS);
    double resultadoPromediodeIndicador(BeanFiltrosGraficos beanFiltros,Integer nidIndicador, String fechaHoy);
    List<BeanEvaluacion>getEvaluacionesEnRango(Date hoy, int nidMain);
}
