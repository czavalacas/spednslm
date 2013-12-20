package test.negocio.BDL;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import test.negocio.BDL.IL.BDL_C_SFActorLocal;
import test.negocio.BDL.IR.BDL_C_SFActorRemote;
import test.negocio.entidades.Actor;

@Stateless(name = "BDL_C_SFActor", mappedName = "mapBDL_C_SFActor")
public class BDL_C_SFActorBean implements BDL_C_SFActorRemote, 
                                             BDL_C_SFActorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "EJB_LOGICA")
    private EntityManager em;

    public BDL_C_SFActorBean() {
    }

    /** <code>select o from Actor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Actor> getActorFindAll() {
        return em.createNamedQuery("Actor.findAll", Actor.class).getResultList();
    }
    
    public List<Actor> getActoresByNombre(String nombre){
        String strQuery = "SELECT a " +
                          "FROM Actor a "+
                          "WHERE a.first_name like :nombre ";
        return em.createQuery(strQuery).setParameter("nombre","%"+nombre.toUpperCase()+"%").getResultList();
    }
}