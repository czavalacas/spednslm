package sped.negocio.BDL.SFBean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import sped.negocio.BDL.IL.BDL_C_SFCriterioLocal;
import sped.negocio.BDL.IR.BDL_C_SFCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;

import utils.system;

@Stateless(name = "BDL_C_SFCriterio", mappedName = "mapBDL_C_SFCriterio")
public class BDL_C_SFCriterioBean implements BDL_C_SFCriterioRemote,
                                                BDL_C_SFCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFCriterioBean() {
    }
    
    public List<Criterio> getCriteriosByAttr_BDL(BeanCriterio beanCriterio){
        try{
            String strQuery = "SELECT c " +
                              "FROM Criterio c " +
                              "WHERE 1 = 1 ";
            if(beanCriterio != null){
                if(beanCriterio.getDescripcionCriterio() != null){
                    strQuery = strQuery.concat(" AND upper(c.descripcionCriterio) like :desc_criterio ");
                }
                if(beanCriterio.getNidCriterio() != 0){
                    strQuery = strQuery.concat(" AND c.nidCriterio = :nidCriterio ");
                }
            }
            Query query = em.createQuery(strQuery);
            if(beanCriterio.getDescripcionCriterio() != null){
                query.setParameter("desc_criterio","%"+beanCriterio.getDescripcionCriterio().toUpperCase()+"%");
            }
            if(beanCriterio.getNidCriterio() != 0){
                query.setParameter("nidCriterio",beanCriterio.getNidCriterio());
            }
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}