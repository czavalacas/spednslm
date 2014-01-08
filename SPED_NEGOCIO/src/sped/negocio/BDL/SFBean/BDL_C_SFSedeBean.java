package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFSedeLocal;
import sped.negocio.BDL.IR.BDL_C_SFSedeRemote;
import sped.negocio.entidades.admin.Sede;

@Stateless(name = "BDL_C_SFSede", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFSede")
public class BDL_C_SFSedeBean implements BDL_C_SFSedeRemote, 
                                         BDL_C_SFSedeLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFSedeBean() {
    }

    /** <code>select o from Sede o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Sede> getSedeFindAll() {
        return em.createNamedQuery("Sede.findAll", Sede.class).getResultList();
    }
}
