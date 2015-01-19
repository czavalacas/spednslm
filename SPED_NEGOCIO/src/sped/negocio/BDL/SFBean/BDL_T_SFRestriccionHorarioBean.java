package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFRestriccionHorarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFRestriccionHorarioRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.sist.RestriccionHorario;

@Stateless(name = "BDL_T_SFRestriccionHorario", mappedName = "mapBDL_T_SFRestriccionHorario")
public class BDL_T_SFRestriccionHorarioBean implements BDL_T_SFRestriccionHorarioRemote,
                                                       BDL_T_SFRestriccionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFRestriccionHorarioBean() {
    }

    public RestriccionHorario persistRestriccionHorario(RestriccionHorario restriccionHorario) {
        em.persist(restriccionHorario);
        return restriccionHorario;
    }

    public RestriccionHorario mergeRestriccionHorario(RestriccionHorario restriccionHorario) {
        return em.merge(restriccionHorario);
    }
    
    public void removeRestriccionHorario(RestriccionHorario restriccionHorario) {
        restriccionHorario = em.find(RestriccionHorario.class, restriccionHorario.getNidReho());
        em.remove(restriccionHorario);
    }
    
    
}
