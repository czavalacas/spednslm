package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFConfiguracionHorarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFConfiguracionHorarioRemoto;
import sped.negocio.entidades.sist.ConfiguracionHorario;

@Stateless(name = "BDL_T_SFConfiguracionHorario", mappedName = "map-BDL_T_SFConfiguracionHorario")
public class BDL_T_SFConfiguracionHorarioBean implements BDL_T_SFConfiguracionHorarioRemoto,
                                                         BDL_T_SFConfiguracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFConfiguracionHorarioBean() {
    }

    public ConfiguracionHorario persistConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
        em.persist(configuracionHorario);
        return configuracionHorario;
    }

    public ConfiguracionHorario mergeConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
        return em.merge(configuracionHorario);
    }

    public void removeConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
        configuracionHorario = em.find(ConfiguracionHorario.class, configuracionHorario.getNidConfig());
        em.remove(configuracionHorario);
    }
}
