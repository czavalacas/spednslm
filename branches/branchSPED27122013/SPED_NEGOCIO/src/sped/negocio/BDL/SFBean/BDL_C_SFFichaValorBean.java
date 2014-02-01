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

import sped.negocio.BDL.IL.BDL_C_SFFichaValorLocal;
import sped.negocio.BDL.IR.BDL_C_SFFichaValorRemote;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.FichaValor;
import sped.negocio.entidades.sist.Rol;

@Stateless(name = "BDL_C_SFFichaValor", mappedName = "mapBDL_C_SFFichaValor")
public class BDL_C_SFFichaValorBean implements BDL_C_SFFichaValorRemote,
                                                  BDL_C_SFFichaValorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFFichaValorBean() {
    }

    /** <code>select o from FichaValor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FichaValor> getFichaValorFindAll() {
        return em.createNamedQuery("FichaValor.findAll", FichaValor.class).getResultList();
    }
    
    public FichaValor findFichaValorById(int id){
        try{
            FichaValor instance = em.find(FichaValor.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public List<FichaValor> getFichaValorByFicha(int nidFicha){
        try{
            String strQuery = "SELECT fv FROM " +
                              "FichaValor fv " + 
                              "WHERE fv.ficha.nidFicha = :nidFicha ";
            List<FichaValor> lstFichasValor = em.createQuery(strQuery).setParameter("nidFicha",nidFicha).getResultList();
            int size = lstFichasValor == null ? 0 : lstFichasValor.size();
            if (size > 0) {
                return lstFichasValor;
            } else {
                return new ArrayList<FichaValor>();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}