package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFResultadoCriterioLocal;
import sped.negocio.BDL.IR.BDL_T_SFResultadoCriterioRemote;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "BDL_T_SFResultadoCriterio", mappedName = "mapBDL_T_SFResultadoCriterio")
public class BDL_T_SFResultadoCriterioBean implements BDL_T_SFResultadoCriterioRemote,
                                                         BDL_T_SFResultadoCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFResultadoCriterioBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ResultadoCriterio persistResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        em.persist(resultadoCriterio);
        return resultadoCriterio;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ResultadoCriterio mergeResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        return em.merge(resultadoCriterio);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeResultadoCriterio(ResultadoCriterio resultadoCriterio) {
        resultadoCriterio = em.find(ResultadoCriterio.class, resultadoCriterio.getNidResultadoCriterio());
        em.remove(resultadoCriterio);
    }
}