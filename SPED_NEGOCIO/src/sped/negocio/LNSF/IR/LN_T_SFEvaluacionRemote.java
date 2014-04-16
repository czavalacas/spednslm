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
    String registrarEvaluacion_LN(Evaluacion eva);
    String removerEvaluacion_LN(Evaluacion eva);
    String updateEvaluacionbyComentarioProfesor(int idEvaluacion,
                                                String comentario);
    String grabarComentariosYJustificacionesDeEvaluacion(String nidDate, 
                                                         String comentEvalu, 
                                                         String descripOtros, 
                                                         String nidProblema);
    BeanError registrarEvaluacion_LN_Web(List<BeanCriterio> lstBeanIndiVal,
                                         Integer nidEvaluacion,
                                         Integer nidUsuario,
                                         String comentarioEvaluador);
}
