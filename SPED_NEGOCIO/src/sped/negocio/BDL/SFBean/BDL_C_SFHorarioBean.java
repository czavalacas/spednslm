package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFHorarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFHorarioRemote;
import sped.negocio.entidades.sist.Horario;

@Stateless(name = "BDL_C_SFHorario", mappedName = "mapBDL_C_SFHorario")
public class BDL_C_SFHorarioBean implements BDL_C_SFHorarioRemote, 
                                            BDL_C_SFHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFHorarioBean() {
    }

    /** <code>select o from Horario o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Horario> getHorarioFindAll() {
        return em.createNamedQuery("Horario.findAll", Horario.class).getResultList();
    }
}
