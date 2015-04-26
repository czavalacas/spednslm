package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFMainLocal;
import sped.negocio.BDL.IR.BDL_T_SFMainRemoto;
import sped.negocio.entidades.admin.Main;

@Stateless(name = "BDL_T_SFMain", mappedName = "map-BDL_T_SFMain")
public class BDL_T_SFMainBean implements BDL_T_SFMainRemoto, 
                                         BDL_T_SFMainLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFMainBean() {
    }

    
    public Main persistMain(Main main) {
        em.persist(main);
        return main;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Main mergeMain(Main main) {
        return em.merge(main);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeMain(Main main) {
        main = em.find(Main.class, main.getNidMain());
        em.remove(main);
    }
}
