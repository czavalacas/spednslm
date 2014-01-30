package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFResultadoCriterioLocal;
import sped.negocio.BDL.IR.BDL_C_SFResultadoCriterioRemote;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "BDL_C_SFResultadoCriterio", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFResultadoCriterio")
public class BDL_C_SFResultadoCriterioBean implements BDL_C_SFResultadoCriterioRemote, 
                                                      BDL_C_SFResultadoCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFResultadoCriterioBean() {
    }

    /** <code>select o from ResultadoCriterio o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ResultadoCriterio> getResultadoCriterioFindAll() {
        return em.createNamedQuery("ResultadoCriterio.findAll", ResultadoCriterio.class).getResultList();
    }
}
