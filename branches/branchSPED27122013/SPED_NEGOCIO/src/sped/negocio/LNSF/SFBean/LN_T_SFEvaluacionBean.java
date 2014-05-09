package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFCriterioIndicadorLocal;
import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_T_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_T_SFResultadoLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_T_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_T_SFEvaluacionRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanIndicadorValorWS;
import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Evaluacion;
import sped.negocio.entidades.eval.Resultado;

/** Clase que implementa la logica para poder invocar e insertar la evaluacion
 * @author dfloresgonz
 * @since 13.02.2014
 */
@Stateless(name = "LN_T_SFEvaluacion", mappedName = "mapLN_T_SFEvaluacion")
public class LN_T_SFEvaluacionBean implements LN_T_SFEvaluacionRemote,
                                              LN_T_SFEvaluacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFCriterioIndicadorLocal bdL_C_SFCriterioIndicadorLocal;
    @EJB
    private BDL_C_SFEvaluacionLocal bdL_C_SFEvaluacionLocal;
    @EJB
    private BDL_T_SFResultadoLocal bdL_T_SFResultadoLocal;
    @EJB
    private BDL_T_SFEvaluacionLocal bdL_T_SFEvaluacionLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    @EJB
    private LN_T_SFResultadoCriterioLocal ln_T_SFResultadoCriterioLocal;

    public LN_T_SFEvaluacionBean() {
    }
    
    private BeanCriterio getCriterioFromList(Integer nidCrit,List<BeanCriterio> lstBCrit){
        for(BeanCriterio bc : lstBCrit){
            if(bc.getNidCriterio().intValue() == nidCrit.intValue()){
                return bc;
            }
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String registrarEvaluacion_LN(Evaluacion eva) {
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            bdL_T_SFEvaluacionLocal.persistEvaluacion(eva);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String removerEvaluacion_LN(Evaluacion eva) {
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            bdL_T_SFEvaluacionLocal.removeEvaluacion(eva);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal,
                                            Integer nidEvaluacion,
                                            Integer nidUsuario,
                                            Integer nidLog,
                                            String comentarioEvaluador){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            List<BeanCriterio> lstBCrit = new ArrayList<BeanCriterio>();
            BeanCriterio bCrit = null;
            CriterioIndicador ci = null;
            Resultado resultado = null;
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(nidEvaluacion);
            for (BeanIndicadorValorWS beanIV : lstBeanIndiVal) {
                ci = bdL_C_SFCriterioIndicadorLocal.findCriterioIndicadorById(beanIV.getNidCI());
                bCrit = new BeanCriterio();
                bCrit.setNidCriterio(ci.getFichaCriterio().getCriterio().getNidCriterio());
                if(!lstBCrit.contains(bCrit)){
                    bCrit.setSumaNota(beanIV.getValor().intValue());
                    bCrit.setCantidadValoresWS(ci.getFichaCriterio().getFicha().getFichaValorLista().size());
                    bCrit.setFichaCriterioAUX(ci.getFichaCriterio());
                    bCrit.setNidCriterio(ci.getFichaCriterio().getCriterio().getNidCriterio());
                    int cantIndis = bdL_C_SFCriterioIndicadorLocal.cantidadIndicadoresByCriterio_BDL(bCrit.getNidCriterio(),ci.getFichaCriterio().getFicha().getNidFicha());
                    bCrit.setCantidadIndicadores(cantIndis);
                    lstBCrit.add(bCrit);
                }else{
                    bCrit = this.getCriterioFromList(bCrit.getNidCriterio(),lstBCrit);
                    bCrit.setSumaNota((bCrit.getSumaNota() + beanIV.getValor().intValue()));
                }
                resultado = new Resultado();
                resultado.setCriterioIndicador(ci);
                resultado.setEvaluacion(eva);
                resultado.setNidSede(eva.getMain().getAula().getSede().getNidSede());
                resultado.setValor((short) beanIV.getValor().intValue());
                resultado.setNotaVigecimal((beanIV.getValor().intValue() * 20) / bCrit.getCantidadValoresWS());
                // SI UN INDICADOR ES DESAPROBATORIO SE ENVIA EL 1 AL ATRIBUTO TONOTIFICACION Y EL TRIGGER LO ENVIARA A LA TABLA DE NOTIFICACIONES
                resultado.setToNotification(resultado.getNotaVigecimal() <= 10.49 || resultado.getNotaVigecimal() >= 17.00 ? "1" : "0");
                bdL_T_SFResultadoLocal.persistResultado(resultado);
            }
            ln_T_SFResultadoCriterioLocal.registrarResultadoCriterios_LN(lstBCrit,eva);
            eva.setEstadoEvaluacion("EJECUTADO");
            eva.setNid_usuario_ws(nidUsuario);
            eva.setNidLog(nidLog);
            eva.setNidProblema(null);
            eva.setComentario_evaluador(comentarioEvaluador);
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanError registrarEvaluacion_LN_Web(List<BeanCriterio> lstBeanIndiVal,
                                                Integer nidEvaluacion,
                                                Integer nidUsuario,
                                                String comentarioEvaluador){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            CriterioIndicador ci = null;
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(nidEvaluacion);
            for (BeanCriterio _beanIV : lstBeanIndiVal) {
                Resultado resultado = null;
                for(BeanCriterio beanIV : _beanIV.getLstIndicadores()){
                    ci = bdL_C_SFCriterioIndicadorLocal.findCriterioIndicadorById(beanIV.getNidCriterioIndicador());
                    _beanIV.setFichaCriterioAUX(ci.getFichaCriterio());
                    resultado = new Resultado();
                    resultado.setCriterioIndicador(ci);
                    resultado.setEvaluacion(eva);
                    resultado.setNidSede(eva.getMain().getAula().getSede().getNidSede());
                    resultado.setValor((short) beanIV.getValorSpinBox());
                    resultado.setNotaVigecimal((beanIV.getValorSpinBox() * 20) / ci.getFichaCriterio().getFicha().getFichaValorLista().size());
                    // SI UN INDICADOR ES DESAPROBATORIO SE ENVIA EL 1 AL ATRIBUTO TONOTIFICACION Y EL TRIGGER LO ENVIARA A LA TABLA DE NOTIFICACIONES
                    resultado.setToNotification(resultado.getNotaVigecimal() <= 10.49 || resultado.getNotaVigecimal() >= 17.00 ? "1" : "0");
                    bdL_T_SFResultadoLocal.persistResultado(resultado);
                }
            }
            ln_T_SFResultadoCriterioLocal.registrarResultadoCriterios_Web(lstBeanIndiVal,eva);
            eva.setEstadoEvaluacion("EJECUTADO");
            eva.setNid_usuario_ws(nidUsuario);
            eva.setNidProblema(null);
            eva.setComentario_evaluador(comentarioEvaluador);
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
        }
        beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
        return beanError;
    }
    
    public String updateEvaluacionbyComentarioProfesor(int idEvaluacion,
                                                       String comentario){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(idEvaluacion);
            eva.setComentario_profesor(comentario);
            eva.setNotificacionEvaluadorComentarioProfesor("1");
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    /**
     * Metodo para cambiar la nofiticacion a Leido cuando el usuario abre el popup para leer el comentario del profesor
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion
     * @return Codigo de error
     */
    public String updateEvaluacionbyComentarioProfesor_Leido_LN(int idEvaluacion){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(idEvaluacion);
            eva.setNotificacionEvaluadorComentarioProfesor("0");
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    /**
     * Metodo que registra el comentario del evaluador
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion - nidEvaluacion en evmeval
     * @param comentario - descripcion textual
     * @return -codigo de error
     */
    public String updateEvaluacionbyComentarioEvaluador(int idEvaluacion,
                                                        String comentario){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(idEvaluacion);
            eva.setComentario_evaluador(comentario);
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    /**
     * Metodo para actualizar el problema a una planificacion que no se realizo.
     * @author dfloresgonz
     * @since 04.05.2014
     * @param idEvaluacion
     * @param nidProblema
     * @param descProblema
     * @return
     */
    public String updateEvaluacionProblemaEvaluador(int idEvaluacion,
                                                    int nidProblema,
                                                    String descProblema){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(idEvaluacion);
            eva.setNidProblema(nidProblema);
            eva.setComentarioEvaluador(descProblema);
            eva.setEstadoEvaluacion("NO EJECUTADO");
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }

    public String grabarComentariosYJustificacionesDeEvaluacion(String nidDate, 
                                                                String comentEvalu, 
                                                                String descripOtros,
                                                                String nidProblema) {
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            Evaluacion eva = bdL_C_SFEvaluacionLocal.getEvaluacionById(nidDate);
            eva.setComentario_evaluador(comentEvalu);
            eva.setComentarioEvaluador(descripOtros);
            if (nidProblema != null) {
                eva.setNidProblema(Integer.parseInt(nidProblema));
            }
            eva.setEstadoEvaluacion("NO EJECUTADO");
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        } catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
}