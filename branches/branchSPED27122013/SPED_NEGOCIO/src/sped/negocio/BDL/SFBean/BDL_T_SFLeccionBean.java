package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFLeccionBeanLocal;
import sped.negocio.BDL.IR.BDL_T_SFLeccionBeanRemote;
import sped.negocio.entidades.sist.Leccion;

@Stateless(name = "BDL_T_SFLeccionBean", mappedName = "map-BDL_T_SFLeccionBean")
public class BDL_T_SFLeccionBean implements BDL_T_SFLeccionBeanRemote, 
                                            BDL_T_SFLeccionBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFLeccionBean() {
    }

    public Leccion persistLecciones(Leccion lecciones) {
        em.persist(lecciones);
        return lecciones;
    }

    public Leccion mergeLecciones(Leccion lecciones) {
        return em.merge(lecciones);
    }

    public void removeLecciones(Leccion lecciones) {
        lecciones = em.find(Leccion.class, lecciones.getNidLecc());
        em.remove(lecciones);
    }
}
