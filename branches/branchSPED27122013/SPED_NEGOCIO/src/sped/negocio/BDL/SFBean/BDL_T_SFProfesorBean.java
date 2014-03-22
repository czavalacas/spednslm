package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFProfesorLocal;
import sped.negocio.BDL.IR.BDL_T_SFProfesorRemoto;
import sped.negocio.entidades.admin.Profesor;

@Stateless(name = "BDL_T_SFProfesor", mappedName = "map-BDL_T_SFProfesor")
public class BDL_T_SFProfesorBean implements BDL_T_SFProfesorRemoto, BDL_T_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFProfesorBean() {
    }

    public Profesor persistProfesor(Profesor profesor) {
        em.persist(profesor);
        return profesor;
    }

    public Profesor mergeProfesor(Profesor profesor) {
        return em.merge(profesor);
    }

    public void removeProfesor(Profesor profesor) {
        profesor = em.find(Profesor.class, profesor.getDniProfesor());
        em.remove(profesor);
    }
}
