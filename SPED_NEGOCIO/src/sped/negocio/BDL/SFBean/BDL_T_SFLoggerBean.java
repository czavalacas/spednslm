package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFLoggerLocal;
import sped.negocio.BDL.IR.BDL_T_SFLoggerRemote;
import sped.negocio.entidades.sist.Logger;

/**
 * Session Facade BDL transaccional para persistir los errores del sistema
 * @author dfloresgonz
 * @since 12.05.2014
 */
@Stateless(name = "BDL_T_SFLogger", mappedName = "mapBDL_T_SFLogger")
public class BDL_T_SFLoggerBean implements BDL_T_SFLoggerRemote, 
                                           BDL_T_SFLoggerLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFLoggerBean() {
    }

    public Logger persistLogger(Logger logger) {
        em.persist(logger);
        return logger;
    }

    public Logger mergeLogger(Logger logger) {
        return em.merge(logger);
    }

    public void removeLogger(Logger logger) {
        logger = em.find(Logger.class, logger.getNid_error());
        em.remove(logger);
    }
}