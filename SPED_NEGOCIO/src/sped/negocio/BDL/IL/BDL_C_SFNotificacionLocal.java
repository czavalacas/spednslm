package sped.negocio.BDL.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Local
public interface BDL_C_SFNotificacionLocal {
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
}
