package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IL.BDL_T_SFCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFCriterioLocal;
import sped.negocio.LNSF.IR.LN_T_SFCriterioRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.eval.Criterio;

@Stateless(name = "LN_T_SFCriterio", mappedName = "mapLN_T_SFCriterio")
public class LN_T_SFCriterioBean implements LN_T_SFCriterioRemote,
                                               LN_T_SFCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFCriterioLocal bdL_T_SFCriterioLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;

    public LN_T_SFCriterioBean() {
    }
    
    public BeanCriterio registrarCriterio(String descCriterio){
        BeanError beanError = new BeanError();
        BeanCriterio bTipGasto = new BeanCriterio();
        String error = "000";
        try{
            int existe = bdL_C_SFUtilsLocal.findCountByProperty("descripcionCriterio",descCriterio,"Criterio",true,false);
            if(existe != 0){
                error = "SPED-00002";
            }else{
                Criterio criterio = new Criterio();
                criterio.setDescripcionCriterio(descCriterio);
                bdL_T_SFCriterioLocal.persistCriterio(criterio);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            bTipGasto.setBeanError(beanError);
            return bTipGasto;
        }
    }
}