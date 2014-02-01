package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFFichaCriterioLocal;
import sped.negocio.BDL.IR.BDL_T_SFFichaCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.FichaCriterioPK;

@Stateless(name = "BDL_T_SFFichaCriterio", mappedName = "mapBDL_T_SFFichaCriterio")
public class BDL_T_SFFichaCriterioBean implements BDL_T_SFFichaCriterioRemote, 
                                                     BDL_T_SFFichaCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFFichaCriterioBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public FichaCriterio persistFichaCriterio(FichaCriterio fichaCriterio) {
        em.persist(fichaCriterio);
        em.flush();
        em.refresh(fichaCriterio);
        return fichaCriterio;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public FichaCriterio mergeFichaCriterio(FichaCriterio fichaCriterio) {
        return em.merge(fichaCriterio);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeFichaCriterio(FichaCriterio fichaCriterio) {
        fichaCriterio = em.find(FichaCriterio.class,new FichaCriterioPK(fichaCriterio.getCriterio().getNidCriterio(),
                                                                         fichaCriterio.getFicha().getNidFicha()));
        Utiles.sysout("remover:fichaCriterio.getCriterio().getNidCriterio():"+fichaCriterio.getCriterio().getNidCriterio()+"|fichaCriterio.getFicha().getNidFicha():"+fichaCriterio.getFicha().getNidFicha());
        em.remove(fichaCriterio);
        em.flush();
        Utiles.sysout("removio y flusheo");
    }
}