package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFEmailLocal;
import sped.negocio.BDL.IR.BDL_T_SFEmailRemote;
import sped.negocio.entidades.sist.Email;

@Stateless(name = "BDL_T_SFEmail", mappedName = "mapBDL_T_SFEmail")
public class BDL_T_SFEmailBean implements BDL_T_SFEmailRemote, 
                                          BDL_T_SFEmailLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFEmailBean() {
    }

    public Email persistEmail(Email email) {
        em.persist(email);
        return email;
    }

    public Email mergeEmail(Email email) {
        return em.merge(email);
    }
}
