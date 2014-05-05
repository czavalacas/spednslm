package sped.negocio.BDL.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Remote
public interface BDL_C_SFNotificacionRemote {
    int getCantidadNotificacionesEvaluaciones_BDL(int nidUsuario);
    int getCantidadNotificacionesParteOcurrencia_BDL(int nidUsuario);
    List<BeanNotificacionEvaluacion> getListaNotificacionesByAttr_BDL(String docente,
                                                                      String indicador,
                                                                      Integer sede,
                                                                      String estadoLeido,
                                                                      Date fecMin,
                                                                      Date fecMax,
                                                                      Integer nidUsuario);
    
    List<BeanParteOcurrencia> getListaNotificacionesPartesOcurrenciaByAttr_BDL(String docente,
                                                                              Integer nidProblema,
                                                                              Integer sede,
                                                                              String estadoLeido,
                                                                              Date fecMin,
                                                                              Date fecMax,
                                                                              Integer nidUsuario);
    List<BeanNotificacionEvaluacion> getListaNotificaciones_Detalle_ByEval_ByAttr_BDL(int nidEvaluacion);
    int getCantidadNotificacionesEvaluacionesComentarioProfesor_BDL(int nidUsuario);
}
