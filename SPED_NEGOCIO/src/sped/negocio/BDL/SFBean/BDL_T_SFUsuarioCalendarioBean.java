package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFUsuarioCalendarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioCalendarioRemote;
import sped.negocio.entidades.admin.UsuarioCalendario;
import sped.negocio.entidades.admin.UsuarioCalendarioPK;

@Stateless(name = "BDL_T_SFUsuarioCalendario", mappedName = "mapBDL_T_SFUsuarioCalendario")
public class BDL_T_SFUsuarioCalendarioBean implements BDL_T_SFUsuarioCalendarioRemote, 
                                                      BDL_T_SFUsuarioCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFUsuarioCalendarioBean() {
    }

    public UsuarioCalendario persistUsuarioCalendario(UsuarioCalendario usuarioCalendario) {
        em.persist(usuarioCalendario);
        return usuarioCalendario;
    }

    public UsuarioCalendario mergeUsuarioCalendario(UsuarioCalendario usuarioCalendario) {
        return em.merge(usuarioCalendario);
    }

    public void removeUsuarioCalendario(UsuarioCalendario usuarioCalendario) {
        usuarioCalendario =
            em.find(UsuarioCalendario.class,
                    new UsuarioCalendarioPK(usuarioCalendario.getNidFecha(), usuarioCalendario.getNidUsuario()));
        em.remove(usuarioCalendario);
    }
}