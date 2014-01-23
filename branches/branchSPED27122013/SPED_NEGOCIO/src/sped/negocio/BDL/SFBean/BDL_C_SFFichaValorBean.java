package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFFichaValorLocal;
import sped.negocio.BDL.IR.BDL_C_SFFichaValorRemote;
import sped.negocio.entidades.eval.FichaValor;

@Stateless(name = "BDL_C_SFFichaValor", mappedName = "mapBDL_C_SFFichaValor")
public class BDL_C_SFFichaValorBean implements BDL_C_SFFichaValorRemote,
                                                  BDL_C_SFFichaValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFFichaValorBean() {
    }

    /** <code>select o from FichaValor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FichaValor> getFichaValorFindAll() {
        return em.createNamedQuery("FichaValor.findAll", FichaValor.class).getResultList();
    }
}