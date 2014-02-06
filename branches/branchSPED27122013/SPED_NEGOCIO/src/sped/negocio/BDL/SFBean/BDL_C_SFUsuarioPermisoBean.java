package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioPermisoLocal;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioPermisoRemote;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Stateless(name = "BDL_C_SFUsuarioPermiso", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFUsuarioPermiso")
public class BDL_C_SFUsuarioPermisoBean implements BDL_C_SFUsuarioPermisoRemote, 
                                                   BDL_C_SFUsuarioPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFUsuarioPermisoBean() {
    }
    
    public List<UsuarioPermiso> getUsuarioPermisoByUsuario(Usuario u){
        try{
            String strsql = "SELECT up " +
                            "FROM UsuarioPermiso up " +
                            "WHERE up.usuario = :usuario " +
                            "ORDER BY up.rolPermiso.permiso.nidPermiso";
            return (List<UsuarioPermiso>) em.createQuery(strsql)
                                            .setParameter("usuario", u)
                                            .getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
