package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFConfiguracionEventoHorarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFConfiguracionEventoHorarioRemoto;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.sist.ConfiguracionEventoHorario;

@Stateless(name = "BDL_C_SFConfiguracionEventoHorario",
           mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFConfiguracionEventoHorario")
public class BDL_C_SFConfiguracionEventoHorarioBean implements BDL_C_SFConfiguracionEventoHorarioRemoto,
                                                               BDL_C_SFConfiguracionEventoHorarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFConfiguracionEventoHorarioBean() {
    }

    /** <code>select o from ConfiguracionEventoHorario o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ConfiguracionEventoHorario> getConfiguracionEventoHorarioFindAll() {
        return em.createNamedQuery("ConfiguracionEventoHorario.findAll",
                                   ConfiguracionEventoHorario.class).getResultList();
    }
    
    public ConfiguracionEventoHorario finEventoById(int id) {
         try {
             ConfiguracionEventoHorario instance = em.find(ConfiguracionEventoHorario.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
}
