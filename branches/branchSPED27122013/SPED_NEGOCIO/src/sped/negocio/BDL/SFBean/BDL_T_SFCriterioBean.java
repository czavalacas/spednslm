package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFCriterioLocal;
import sped.negocio.BDL.IR.BDL_T_SFCriterioRemote;
import sped.negocio.entidades.eval.Criterio;

@Stateless(name = "BDL_T_SFCriterio", mappedName = "mapBDL_T_SFCriterio")
public class BDL_T_SFCriterioBean implements BDL_T_SFCriterioRemote, 
                                                BDL_T_SFCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFCriterioBean() {
    }

    public Criterio persistCriterio(Criterio criterio) {
        em.persist(criterio);
        return criterio;
    }

    public Criterio mergeCriterio(Criterio criterio) {
        return em.merge(criterio);
    }

    public void removeCriterio(Criterio criterio) {
        criterio = em.find(Criterio.class, criterio.getNidCriterio());
        em.remove(criterio);
    }
}