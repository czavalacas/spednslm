package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFUsuarioPermisoLocal;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioPermisoRemote;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Stateless(name = "BDL_T_SFUsuarioPermiso", mappedName = "mapBDL_T_SFUsuarioPermiso")
public class BDL_T_SFUsuarioPermisoBean implements BDL_T_SFUsuarioPermisoRemote, 
                                                   BDL_T_SFUsuarioPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFUsuarioPermisoBean() {
    }

    public UsuarioPermiso persistUsuarioPermiso(UsuarioPermiso usuarioPermiso) {
        em.persist(usuarioPermiso);
        return usuarioPermiso;
    }

    public UsuarioPermiso mergeUsuarioPermiso(UsuarioPermiso usuarioPermiso) {
        return em.merge(usuarioPermiso);
    }

    public void removeUsuarioPermiso(UsuarioPermiso usuarioPermiso) {
        System.out.println(usuarioPermiso.getNidPermisoUsuario());
        usuarioPermiso = em.find(UsuarioPermiso.class, usuarioPermiso.getNidPermisoUsuario());
        em.remove(usuarioPermiso);
    }
}
