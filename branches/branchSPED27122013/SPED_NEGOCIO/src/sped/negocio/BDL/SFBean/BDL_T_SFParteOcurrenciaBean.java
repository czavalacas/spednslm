package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFParteOcurrenciaLocal;
import sped.negocio.BDL.IR.BDL_T_SFParteOcurrenciaRemote;
import sped.negocio.entidades.admin.ParteOcurrencia;

/**
 * Metodo de Logica de Base de Datos que realiza el seteo de la entidad a persistir ParteOcurrencia - admpaoc
 * @author dfloresgonz
 * @since 26.02.2014
 */
@Stateless(name = "BDL_T_SFParteOcurrencia", mappedName = "mapBDL_T_SFParteOcurrencia")
public class BDL_T_SFParteOcurrenciaBean implements BDL_T_SFParteOcurrenciaRemote, 
                                                       BDL_T_SFParteOcurrenciaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFParteOcurrenciaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ParteOcurrencia persistParteOcurrencia(ParteOcurrencia parteOcurrencia) {
        em.persist(parteOcurrencia);
        return parteOcurrencia;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public ParteOcurrencia mergeParteOcurrencia(ParteOcurrencia parteOcurrencia) {
        return em.merge(parteOcurrencia);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeParteOcurrencia(ParteOcurrencia parteOcurrencia) {
        parteOcurrencia = em.find(ParteOcurrencia.class, parteOcurrencia.getNidParte());
        em.remove(parteOcurrencia);
    }
}
