package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFValorLocal;
import sped.negocio.LNSF.IL.LN_C_SFValorLocal;
import sped.negocio.LNSF.IR.LN_C_SFValorRemote;

@Stateless(name = "LN_C_SFValor", mappedName = "mapLN_C_SFValor")
public class LN_C_SFValorBean implements LN_C_SFValorRemote, 
                                         LN_C_SFValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFValorLocal bdL_C_SFValorLocal;

    public LN_C_SFValorBean() {
    }
    
    public String getRangoValorByFicha(int nidFicha){
        return bdL_C_SFValorLocal.getRangoValorByFicha(nidFicha);
    }
    
    public String getValoresPosiblesByCriterio(int nidCriterio,int nidFicha){
        return bdL_C_SFValorLocal.getValoresByCriterio(nidCriterio,nidFicha);
    }
}
