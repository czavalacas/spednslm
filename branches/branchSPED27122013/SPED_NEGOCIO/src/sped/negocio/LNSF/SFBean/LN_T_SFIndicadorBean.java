package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IL.BDL_T_SFIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFIndicadorLocal;
import sped.negocio.LNSF.IR.LN_T_SFIndicadorRemote;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanIndicador;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.Indicador;

@Stateless(name = "LN_T_SFIndicador", mappedName = "mapLN_T_SFIndicador")
public class LN_T_SFIndicadorBean implements LN_T_SFIndicadorRemote, 
                                                LN_T_SFIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFIndicadorLocal bdL_T_SFIndicadorLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;

    public LN_T_SFIndicadorBean() {
    }
    
    public BeanIndicador registrarIndicador(String descIndicador){
        BeanError beanError = new BeanError();
        BeanIndicador beanIndicador = new BeanIndicador();
        String error = "000";
        try{
            int existe = bdL_C_SFUtilsLocal.findCountByProperty("descripcionIndicador",descIndicador,"Indicador",true,false);
            if(existe != 0){
                error = "SPED-00003";
            }else{
                Indicador indicador = new Indicador();
                indicador.setDescripcionIndicador(descIndicador);
                bdL_T_SFIndicadorLocal.persistIndicador(indicador);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            beanIndicador.setBeanError(beanError);
            return beanIndicador;
        }
    }
}