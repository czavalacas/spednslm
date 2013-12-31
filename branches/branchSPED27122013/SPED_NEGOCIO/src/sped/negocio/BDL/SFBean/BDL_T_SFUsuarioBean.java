package sped.negocio.BDL.SFBean;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;

@Stateless(name = "BDL_T_SFUsuario", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_T_SFUsuario")
public class BDL_T_SFUsuarioBean implements BDL_T_SFUsuarioRemote, 
                                            BDL_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_T_SFUsuarioBean() {
    }

    public Usuario persistUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        return em.merge(usuario);
    }
}
