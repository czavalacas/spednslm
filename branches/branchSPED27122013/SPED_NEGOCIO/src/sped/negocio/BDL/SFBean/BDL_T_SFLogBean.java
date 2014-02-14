package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_T_SFLogRemote;
import sped.negocio.BDL.IR.BDL_T_SFLogLocal;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.sist.Log;

@Stateless(name = "BDL_T_SFLog", mappedName = "mapBDL_T_SFLog")
public class BDL_T_SFLogBean implements BDL_T_SFLogRemote,
                                           BDL_T_SFLogLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFLogBean() {
    }
 
  //  @TransactionAttribute(TransactionAttributeType.MANDATORY) SE COMENTO PORQUE NO SE COGIA EL ID GENERADO 14.02.2014 dfloresgonz
    public Log persistLog(Log log) {
        em.persist(log);
        em.flush();
        return log;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Log mergeLog(Log log) {
        return em.merge(log);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeLog(Log log) {
        log = em.find(Log.class, log.getNidLog());
        em.remove(log);
    }
}