package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFAulaLocal;
import sped.negocio.BDL.IR.BDL_T_SFAulaRemoto;
import sped.negocio.entidades.admin.Aula;

@Stateless(name = "BDL_T_SFAula", mappedName = "map-BDL_T_SFAula")
public class BDL_T_SFAulaBean implements BDL_T_SFAulaRemoto, 
                                         BDL_T_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFAulaBean() {
    }

    public Aula persistAula(Aula aula) {
        em.persist(aula);
        return aula;
    }

    public Aula mergeAula(Aula aula) {
        return em.merge(aula);
    }

    public void removeAula(Aula aula) {
        aula = em.find(Aula.class, aula.getNidAula());
        em.remove(aula);
    }
}
