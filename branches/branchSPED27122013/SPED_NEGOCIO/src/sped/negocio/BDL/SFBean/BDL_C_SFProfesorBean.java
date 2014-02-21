package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IR.BDL_C_SFProfesorRemote;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.Evaluacion;

@Stateless(name = "BDL_C_SFProfesor", mappedName = "map-BDL_C_SFProfesor")
public class BDL_C_SFProfesorBean implements BDL_C_SFProfesorRemote, BDL_C_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFProfesorBean() {
    }

    /** <code>select o from Profesor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Profesor> getProfesorFindAll() {
        return em.createNamedQuery("Profesor.findAll", Profesor.class).getResultList();
    }
    
    public Profesor getProfesorBydni(String dni) {
        try{
            String ejbQl = " SELECT ma " +
                           " FROM Profesor ma " +
                           " WHERE ma.dniProfesor='"+dni+"'";   
                Profesor eva = (Profesor)em.createQuery(ejbQl).getSingleResult();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    

}
