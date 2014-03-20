package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFCursoLocal;
import sped.negocio.BDL.IR.BDL_T_SFCursoRemoto;
import sped.negocio.entidades.admin.Curso;

@Stateless(name = "BDL_T_SFCurso", mappedName = "map-BDL_T_SFCurso")
public class BDL_T_SFCursoBean implements BDL_T_SFCursoRemoto, BDL_T_SFCursoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFCursoBean() {
    }

    public Curso persistCurso(Curso curso) {
        em.persist(curso);
        return curso;
    }

    public Curso mergeCurso(Curso curso) {
        return em.merge(curso);
    }

    public void removeCurso(Curso curso) {
        curso = em.find(Curso.class, curso.getNidCurso());
        em.remove(curso);
    }
}
