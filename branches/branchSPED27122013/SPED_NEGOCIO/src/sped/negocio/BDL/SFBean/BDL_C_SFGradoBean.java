package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFGradoLocal;
import sped.negocio.BDL.IR.BDL_C_SFGradoRemote;
import sped.negocio.entidades.admin.Grado;

@Stateless(name = "BDL_C_SFGrado", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFGrado")
public class BDL_C_SFGradoBean implements BDL_C_SFGradoRemote, 
                                          BDL_C_SFGradoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFGradoBean() {
    }

    /** <code>select o from Grado o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Grado> getGradoFindAll() {
        return em.createNamedQuery("Grado.findAll", Grado.class).getResultList();
    }
}
