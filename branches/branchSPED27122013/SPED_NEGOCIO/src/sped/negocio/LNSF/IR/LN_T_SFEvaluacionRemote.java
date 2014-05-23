package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanIndicadorValorWS;
import sped.negocio.entidades.eval.Evaluacion;

@Remote
public interface LN_T_SFEvaluacionRemote {
    String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal, 
                                     Integer nidEvaluacion,
                                     Integer nidUsuario,
                                     Integer nidLog,
                                     String comentarioEvaluador);
    String registrarEvaluacion_LN(long s, 
                                  long c, 
                                  int nidMain, 
                                  int nidEvaluador, 
                                  String nidDat, 
                                  int nidPlanificador, 
                                  String tipoVisita);
    String removerEvaluacion_LN(int nidEvaluacion);
    String updateEvaluacionbyComentarioProfesor(int idEvaluacion,
                                                String comentario);
    String grabarComentariosYJustificacionesDeEvaluacion(String nidDate, 
                                                         String comentEvalu, 
                                                         String descripOtros, 
                                                         String nidProblema);
    BeanError registrarEvaluacion_LN_Web(List<BeanCriterio> lstBeanIndiVal,
                                         Integer nidEvaluacion,
                                         Integer nidUsuario,
                                         String comentarioEvaluador,
                                         int nidLog,
                                         boolean isPrimeraVezParcial);
    BeanError registrarEvaluacion_Parcial_LN_Web(List<BeanCriterio> lstBeanIndiVal,
                                                 Integer nidEvaluacion,
                                                 Integer nidUsuario,
                                                 String comentarioEvaluador,
                                                 int nidLog,
                                                 boolean isPrimeraVezParcial);
    /**
     * Metodo que registra el comentario del evaluador
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion - nidEvaluacion en evmeval
     * @param comentario - descripcion textual
     * @return -codigo de error
     */
    String updateEvaluacionbyComentarioEvaluador(int idEvaluacion,
                                                 String comentario);
    /**
     * Metodo para actualizar el problema a una planificacion que no se realizo.
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion
     * @param nidProblema
     * @param descProblema
     * @return
     */
    String updateEvaluacionProblemaEvaluador(int idEvaluacion,
                                             int nidProblema,
                                             String descProblema);
    /**
     * Metodo para cambiar la nofiticacion a Leido cuando el usuario abre el popup para leer el comentario del profesor
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion
     * @return Codigo de error
     */
    String updateEvaluacionbyComentarioProfesor_Leido_LN(int idEvaluacion);
}
