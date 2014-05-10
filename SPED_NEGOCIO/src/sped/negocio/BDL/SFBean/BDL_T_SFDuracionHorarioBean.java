package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFDuracionHorarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFDuracionHorarioRemoto;
import sped.negocio.entidades.sist.DuracionHorario;

@Stateless(name = "BDL_T_SFDuracionHorario", mappedName = "map-BDL_T_SFDuracionHorario")
public class BDL_T_SFDuracionHorarioBean implements BDL_T_SFDuracionHorarioRemoto, 
                                                    BDL_T_SFDuracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFDuracionHorarioBean() {
    }

    public DuracionHorario persistDuracionHorario(DuracionHorario duracionHorario) {
        em.persist(duracionHorario);
        return duracionHorario;
    }

    public DuracionHorario mergeDuracionHorario(DuracionHorario duracionHorario) {
        return em.merge(duracionHorario);
    }

    public void removeDuracionHorario(DuracionHorario duracionHorario) {
        duracionHorario = em.find(DuracionHorario.class, duracionHorario.getNidDura());
        em.remove(duracionHorario);
    }
}
