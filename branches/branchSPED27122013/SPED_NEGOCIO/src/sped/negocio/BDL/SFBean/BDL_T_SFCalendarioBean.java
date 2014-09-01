package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFCalendarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFCalendarioRemote;
import sped.negocio.entidades.sist.Calendario;

@Stateless(name = "BDL_T_SFCalendario", mappedName = "mapBDL_T_SFCalendario")
public class BDL_T_SFCalendarioBean implements BDL_T_SFCalendarioRemote, 
                                               BDL_T_SFCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFCalendarioBean() {
    }

    public Calendario persistCalendario(Calendario calendario) {
        em.persist(calendario);
        return calendario;
    }

    public Calendario mergeCalendario(Calendario calendario) {
        return em.merge(calendario);
    }

    public void removeCalendario(Calendario calendario) {
        calendario = em.find(Calendario.class, calendario.getNidFecha());
        em.remove(calendario);
    }
}