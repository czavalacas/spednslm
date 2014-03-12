package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFEmailLocal;
import sped.negocio.BDL.IR.BDL_C_SFEmailRemote;
import sped.negocio.entidades.sist.Email;

@Stateless(name = "BDL_C_SFEmail", mappedName = "mapBDL_C_SFEmail")
public class BDL_C_SFEmailBean implements BDL_C_SFEmailRemote, 
                                          BDL_C_SFEmailLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFEmailBean() {
    }

    /** <code>select o from Email o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Email> getEmailFindAll() {
        return em.createNamedQuery("Email.findAll", Email.class).getResultList();
    }
    
    public Email getEmail(){
        List<Email> lstEmail = getEmailFindAll();
        return lstEmail.get(0);
    }
}
