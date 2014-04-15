package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFImagenLocal;
import sped.negocio.BDL.IR.BDL_T_SFImagenRemote;
import sped.negocio.entidades.sist.Imagen;

@Stateless(name = "BDL_T_SFImagen", mappedName = "mapBDL_T_SFImagen")
public class BDL_T_SFImagenBean implements BDL_T_SFImagenRemote, 
                                           BDL_T_SFImagenLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFImagenBean() {
    }

    public Imagen persistImagen(Imagen imagen) {
        em.persist(imagen);
        return imagen;
    }

    public Imagen mergeImagen(Imagen imagen) {
        return em.merge(imagen);
    }
}
