package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFFichaCriterioLocal;
import sped.negocio.BDL.IR.BDL_C_SFFichaCriterioRemote;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.FichaCriterioPK;
import sped.negocio.entidades.eval.FichaValor;

@Stateless(name = "BDL_C_SFFichaCriterio", mappedName = "mapBDL_C_SFFichaCriterio")
public class BDL_C_SFFichaCriterioBean implements BDL_C_SFFichaCriterioRemote,
                                                     BDL_C_SFFichaCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFFichaCriterioBean() {
    }

    /** <code>select o from FichaCriterio o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FichaCriterio> getFichaCriterioFindAll() {
        return em.createNamedQuery("FichaCriterio.findAll", FichaCriterio.class).getResultList();
    }
    
    public List<FichaCriterio> getFichaCriteriosByFicha(int nidFicha){
        try {
            String strQuery = "SELECT fc FROM " +
                              "FichaCriterio fc " + 
                              "WHERE fc.ficha.nidFicha = :nidFicha " +
                              "ORDER BY fc.orden ASC ";
            List<FichaCriterio> lstFichasCriterio = em.createQuery(strQuery).setParameter("nidFicha",nidFicha).getResultList();
            int size = lstFichasCriterio == null ? 0 : lstFichasCriterio.size();
            if (size > 0) {
                return lstFichasCriterio;
            } else {
                return new ArrayList<FichaCriterio>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public FichaCriterio findFichaCriterioById(FichaCriterio fichaCriterio){
        try{
            FichaCriterio instance = em.find(FichaCriterio.class,new FichaCriterioPK(fichaCriterio.getCriterio().getNidCriterio(),
                                                                                      fichaCriterio.getFicha().getNidFicha()));
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
}