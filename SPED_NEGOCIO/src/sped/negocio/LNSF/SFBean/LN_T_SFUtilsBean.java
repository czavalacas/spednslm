package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_T_SFUtilsLocal;
import sped.negocio.LNSF.IR.LN_T_SFUtilsRemote;

@Stateless(name = "LN_T_SFUtils", mappedName = "mapLN_T_SFUtils")
public class LN_T_SFUtilsBean implements LN_T_SFUtilsRemote,
                                            LN_T_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFUtilsLocal bdL_T_SFUtilsLocal;

    public LN_T_SFUtilsBean() {
    }
    
    public void cambiarALeidoNotificacion_LN(String tabla,Integer nidUsuario){
        bdL_T_SFUtilsLocal.cambiarALeidoNotificacion(tabla, nidUsuario);
    }
}