package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.LNSF.IL.LN_C_SFConfiguracionHorarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFConfiguracionHorarioRemote;

@Stateless(name = "LN_C_SFConfiguracionHorario", mappedName = "mapLN_C_SFConfiguracionHorario")
public class LN_C_SFConfiguracionHorarioBean implements LN_C_SFConfiguracionHorarioRemote,
                                                        LN_C_SFConfiguracionHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public LN_C_SFConfiguracionHorarioBean() {
    }
}
