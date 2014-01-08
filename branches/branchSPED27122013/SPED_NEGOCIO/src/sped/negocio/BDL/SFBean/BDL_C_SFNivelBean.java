package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFNivelLocal;
import sped.negocio.BDL.IR.BDL_C_SFNivelRemote;
import sped.negocio.entidades.admin.Nivel;

@Stateless(name = "BDL_C_SFNivel", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFNivel")
public class BDL_C_SFNivelBean implements BDL_C_SFNivelRemote, 
                                          BDL_C_SFNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFNivelBean() {
    }

    /** <code>select o from Nivel o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Nivel> getNivelFindAll() {
        return em.createNamedQuery("Nivel.findAll", Nivel.class).getResultList();
    }
}
