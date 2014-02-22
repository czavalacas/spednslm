package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFProblemaLocal;
import sped.negocio.BDL.IR.BDL_C_SFProblemaRemote;
import sped.negocio.entidades.admin.Problema;

@Stateless(name = "BDL_C_SFProblema", mappedName = "mapBDL_C_SFProblema")
public class BDL_C_SFProblemaBean implements BDL_C_SFProblemaRemote, 
                                             BDL_C_SFProblemaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFProblemaBean() {
    }

    /** <code>select o from Problema o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Problema> getProblemaFindAll() {
        return em.createNamedQuery("Problema.findAll", Problema.class).getResultList();
    }
    
    public String getDescripcionProblemaById(int idProblema){
        String ejbQL = "SELECT  p.desc_problema FROM Problema p " 
                       + "WHERE p.nidProblema = :nid_problema ";
        Object object = em.createQuery(ejbQL).setParameter("nid_problema", idProblema)
                          .getSingleResult();
        String descripcion = "";
        if(object != null){
            descripcion = object.toString();
        }
        return descripcion;
    }
    
    public int getNidProblemaByDescripcion(String descripcion){
        String ejbQL = "SELECT  p.nidProblema FROM Problema p " 
                       + "WHERE p.desc_problema = :descripcion ";
        Object object = em.createQuery(ejbQL).setParameter("descripcion", descripcion)
                          .getSingleResult();
        int nidProblema = 0;
        if(object != null){
            nidProblema = Integer.parseInt(object.toString());
        }
        return nidProblema;
    }
}
