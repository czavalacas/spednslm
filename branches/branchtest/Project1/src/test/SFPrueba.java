package test;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import test._SFPrueba.SFTestLocal;
import test._SFPrueba.SFTestRemoto;

import test.entidades.Evmeval;

@Stateless(name = "SFTest", mappedName = "mapSFTest")
public class SFPrueba implements SFTestRemoto, SFTestLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Project1")
    private EntityManager em;

    public SFPrueba() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Evmeval persistEvmeval(Evmeval evmeval) {
        em.persist(evmeval);
        return evmeval;
    }

    public Evmeval mergeEvmeval(Evmeval evmeval) {
        return em.merge(evmeval);
    }

    public void removeEvmeval(Evmeval evmeval) {
        evmeval = em.find(Evmeval.class, evmeval.getNidEvaluacion());
        em.remove(evmeval);
    }

    /** <code>select o from Evmeval o</code> */
    public List<Evmeval> getEvmevalFindAll() {
        return em.createNamedQuery("Evmeval.findAll").getResultList();
    }
}
