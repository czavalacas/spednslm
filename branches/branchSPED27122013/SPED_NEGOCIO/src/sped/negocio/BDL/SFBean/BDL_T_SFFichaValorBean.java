package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFFichaValorLocal;
import sped.negocio.BDL.IR.BDL_T_SFFichaValorRemote;
import sped.negocio.entidades.eval.FichaValor;

@Stateless(name = "BDL_T_SFFichaValor", mappedName = "mapBDL_T_SFFichaValor")
public class BDL_T_SFFichaValorBean implements BDL_T_SFFichaValorRemote,
                                                  BDL_T_SFFichaValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFFichaValorBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public FichaValor persistFichaValor(FichaValor fichaValor) {
        em.persist(fichaValor);
        return fichaValor;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public FichaValor mergeFichaValor(FichaValor fichaValor) {
        return em.merge(fichaValor);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeFichaValor(FichaValor fichaValor) {
        fichaValor = em.find(FichaValor.class, fichaValor.getNidFichaValor());
        em.remove(fichaValor);
    }
}
