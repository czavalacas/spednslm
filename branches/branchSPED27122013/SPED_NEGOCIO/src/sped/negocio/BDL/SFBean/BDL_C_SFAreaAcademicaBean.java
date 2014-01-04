package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Usuario;

@Stateless(name = "BDL_C_SFAreaAcademica", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFAreaAcademica")
public class BDL_C_SFAreaAcademicaBean implements BDL_C_SFAreaAcademicaRemote, 
                                                  BDL_C_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFAreaAcademicaBean() {
    }

    /** <code>select o from AreaAcademica o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AreaAcademica> getAreaAcademicaFindAll() {
        return em.createNamedQuery("AreaAcademica.findAll", AreaAcademica.class).getResultList();
    }
    
    public Usuario findEvaluadorById(int id) {
         try {
             Usuario instance = em.find(Usuario.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
}
