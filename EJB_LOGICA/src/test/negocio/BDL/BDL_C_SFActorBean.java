package test.negocio.BDL;

import java.sql.Timestamp;

import java.util.Date;
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

    public List<Actor> getActoresByNombre(String nombre) {
        String strQuery = "SELECT a " + 
                          "FROM Actor a " +
                          "WHERE a.first_name like :nombre ";
        return em.createQuery(strQuery).setParameter("nombre", "%" + nombre.toUpperCase() + "%").getResultList();
    }
    
    
    public Actor findConstraintById(short id) {
        try {
            Actor instance = em.find(Actor.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Actor persistActor(Actor actor) {
        em.persist(actor);
        return actor;
    }

    public Actor mergeActor(Actor actor) {
        return em.merge(actor);
    }

    public void removeActor(Actor actor) {
        actor = em.find(Actor.class, actor.getActor_id());
        em.remove(actor);
    }
    
    public void registrarActor(int tipoEvento,
                                String nombres,
                                String apellidos,
                                Date fecha,
                                short id){
        Actor actor = new Actor();
        if(tipoEvento == 1){
            actor.setFirst_name(nombres);
            actor.setLast_name(apellidos);
            actor.setLast_update(new Timestamp(fecha.getTime()));
            this.persistActor(actor);
            System.out.println("Registro el actor!");
        }else if(tipoEvento == 2){
            actor = this.findConstraintById(id);
            actor.setFirst_name(nombres);
            actor.setLast_name(apellidos);
            actor.setLast_update(new Timestamp(fecha.getTime()));
            this.mergeActor(actor);
            System.out.println("Se edito al actor!");
        }else if(tipoEvento == 3){
            actor = this.findConstraintById(id);
            this.removeActor(actor);
            System.out.println("Se borro al actor!");
        }
    }
}
