package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFProblemaLocal;
import sped.negocio.BDL.IR.BDL_T_SFProblemaRemote;
import sped.negocio.entidades.admin.Problema;

@Stateless(name = "BDL_T_SFProblema", mappedName = "map-BDL_T_SFProblema")
public class BDL_T_SFProblemaBean implements BDL_T_SFProblemaRemote, 
                                             BDL_T_SFProblemaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFProblemaBean() {
    }

    public Problema persistProblema(Problema problema) {
        em.persist(problema);
        return problema;
    }

    public void removeProblema(Problema problema) {
        problema = em.find(Problema.class, problema.getNidProblema());
        em.remove(problema);
    }
}
