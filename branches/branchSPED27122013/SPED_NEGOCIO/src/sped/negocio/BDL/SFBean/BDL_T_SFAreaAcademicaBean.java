package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFAreaAcademicaLocal;
import sped.negocio.BDL.IR.BDL_T_SFAreaAcademicaRemoto;
import sped.negocio.entidades.admin.AreaAcademica;

@Stateless(name = "BDL_T_SFAreaAcademica", mappedName = "map-BDL_T_SFAreaAcademica")
public class BDL_T_SFAreaAcademicaBean implements BDL_T_SFAreaAcademicaRemoto, BDL_T_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFAreaAcademicaBean() {
    }

    public AreaAcademica persistAreaAcademica(AreaAcademica areaAcademica) {
        em.persist(areaAcademica);
        return areaAcademica;
    }

    public AreaAcademica mergeAreaAcademica(AreaAcademica areaAcademica) {
        return em.merge(areaAcademica);
    }

    public void removeAreaAcademica(AreaAcademica areaAcademica) {
        areaAcademica = em.find(AreaAcademica.class, areaAcademica.getNidAreaAcademica());
        em.remove(areaAcademica);
    }
}
