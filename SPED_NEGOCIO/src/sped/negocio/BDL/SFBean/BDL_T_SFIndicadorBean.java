package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFIndicadorLocal;
import sped.negocio.BDL.IR.BDL_T_SFIndicadorRemote;
import sped.negocio.entidades.eval.Indicador;

@Stateless(name = "BDL_T_SFIndicador", mappedName = "mapBDL_T_SFIndicador")
public class BDL_T_SFIndicadorBean implements BDL_T_SFIndicadorRemote, 
                                                 BDL_T_SFIndicadorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFIndicadorBean() {
    }

    public Indicador persistIndicador(Indicador indicador) {
        em.persist(indicador);
        return indicador;
    }

    public Indicador mergeIndicador(Indicador indicador) {
        return em.merge(indicador);
    }

    public void removeIndicador(Indicador indicador) {
        indicador = em.find(Indicador.class, indicador.getNidIndicador());
        em.remove(indicador);
    }
}