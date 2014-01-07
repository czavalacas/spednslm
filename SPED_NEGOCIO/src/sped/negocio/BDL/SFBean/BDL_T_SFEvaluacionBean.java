package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFEvaluacionLocal;
import sped.negocio.BDL.IR.BDL_T_SFEvaluacionRemoto;
import sped.negocio.entidades.eval.Evaluacion;
/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 02.01.2014
 */
@Stateless(name = "BDL_T_SFEvaluacion", mappedName = "map-BDL_T_SFEvaluacion")
public class BDL_T_SFEvaluacionBean implements BDL_T_SFEvaluacionRemoto, 
                                               BDL_T_SFEvaluacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFEvaluacionBean() {
    }

    public Evaluacion persistEvaluacion(Evaluacion evaluacion) {
        em.persist(evaluacion);
        return evaluacion;
    }

    public Evaluacion mergeEvaluacion(Evaluacion evaluacion) {
        return em.merge(evaluacion);
    }

    public void removeEvaluacion(Evaluacion evaluacion) {
        evaluacion = em.find(Evaluacion.class, evaluacion.getNidEvaluacion());
        em.remove(evaluacion);
    }
}
