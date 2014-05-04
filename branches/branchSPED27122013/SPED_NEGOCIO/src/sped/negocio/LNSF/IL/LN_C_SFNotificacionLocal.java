package sped.negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

@Local
public interface LN_C_SFNotificacionLocal {
    int[] getCantidadAMostrarNotificaciones(int nidUsuario,boolean verNotifEvas,boolean verNotifPOs);
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
    List<BeanNotificacionEvaluacion> getListaNotificaciones_Detalle_ByEval_ByAttr_LN(int nidEvaluacion);
}
