package sped.negocio.LNSF.SFBean;

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
import sped.negocio.LNSF.IR.LN_T_SFEvaluacionRemote;
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

    public LN_T_SFEvaluacionBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String registrarEvaluacion_LN_WS(List<BeanIndicadorValorWS> lstBeanIndiVal, Integer nidEvaluacion,Integer nidUsuario){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            CriterioIndicador ci = null;
            Resultado resultado = null;
            Evaluacion eva = bdL_C_SFEvaluacionLocal.findEvaluacionById(nidEvaluacion);
            for (BeanIndicadorValorWS beanIV : lstBeanIndiVal) {
                ci = bdL_C_SFCriterioIndicadorLocal.findCriterioIndicadorById(beanIV.getNidCI());
                resultado = new Resultado();
                resultado.setCriterioIndicador(ci);
                resultado.setEvaluacion(eva);
                resultado.setValor((short) beanIV.getValor().intValue());
                bdL_T_SFResultadoLocal.persistResultado(resultado);
            }
            eva.setEstadoEvaluacion("EJECUTADO");
            eva.setNid_usuario_ws(nidUsuario);
            bdL_T_SFEvaluacionLocal.mergeEvaluacion(eva);
        }catch (Exception e) {
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
}