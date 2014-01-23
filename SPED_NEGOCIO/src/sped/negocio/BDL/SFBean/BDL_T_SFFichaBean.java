package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFFichaLocal;
import sped.negocio.BDL.IR.BDL_T_SFFichaRemote;
import sped.negocio.entidades.eval.Ficha;

@Stateless(name = "BDL_T_SFFicha", mappedName = "mapBDL_T_SFFicha")
public class BDL_T_SFFichaBean implements BDL_T_SFFichaRemote,
                                             BDL_T_SFFichaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFFichaBean() {
    }

    public Ficha persistFicha(Ficha ficha) {
        em.persist(ficha);
        return ficha;
    }

    public Ficha mergeFicha(Ficha ficha) {
        return em.merge(ficha);
    }

    public void removeFicha(Ficha ficha) {
        ficha = em.find(Ficha.class, ficha.getNidFicha());
        em.remove(ficha);
    }
}
