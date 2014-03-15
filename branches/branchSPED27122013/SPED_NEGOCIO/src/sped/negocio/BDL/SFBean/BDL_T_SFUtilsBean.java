package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFUtilsLocal;
import sped.negocio.BDL.IR.BDL_T_SFUtilsRemote;

@Stateless(name = "BDL_T_SFUtils", mappedName = "mapBDL_T_SFUtils")
public class BDL_T_SFUtilsBean implements BDL_T_SFUtilsRemote, 
                                             BDL_T_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFUtilsBean() {
    }
    
    public void cambiarALeidoNotificacion(String tabla,Integer nidUsuario){
        try {
            String sql = "UPDATE "+tabla+ " " +
                         "t SET t.leido = '1' " +
                         "WHERE t.nidUsuario = "+nidUsuario;
            em.createNativeQuery(sql).executeUpdate();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }
}