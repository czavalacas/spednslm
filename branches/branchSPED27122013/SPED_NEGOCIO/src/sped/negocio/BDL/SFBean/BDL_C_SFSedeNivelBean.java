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

import sped.negocio.BDL.IL.BDL_C_SFSedeNivelLocal;
import sped.negocio.BDL.IR.BDL_C_SFSedeNivelRemote;
import sped.negocio.entidades.admin.SedeNivel;

@Stateless(name = "BDL_C_SFSedeNivel", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFSedeNivel")
public class BDL_C_SFSedeNivelBean implements BDL_C_SFSedeNivelRemote, 
                                              BDL_C_SFSedeNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFSedeNivelBean() {
    }

    /** <code>select o from SedeNivel o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SedeNivel> getSedeNivelFindAll() {
        return em.createNamedQuery("SedeNivel.findAll", SedeNivel.class).getResultList();
    }
    
    public List<SedeNivel> getSedeNivelbyNidSedeBDL(int nidSede){
        List<SedeNivel> lstSedeNivel = new ArrayList();
        try {
            String strQuery = "SELECT o " + 
                              "FROM SedeNivel o " + 
                              "WHERE o.sede.nidSede = :sn_nidSede ";
            lstSedeNivel = em.createQuery(strQuery).setParameter("sn_nidSede", nidSede).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSedeNivel;
    }    
    
}
