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

import utils.system;

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
    
    public UsuarioPermiso findConstrainById(int id){
        try{
            UsuarioPermiso instance = em.find(UsuarioPermiso.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public UsuarioPermiso getUsuarioPermisoByPermiso(int nidUsuario, int nidPermiso){
        String strsql = "SELECT up " +
                        "FROM UsuarioPermiso up " +
                        "WHERE up.usuario.nidUsuario = :nidUsuario " +
                        "AND up.rolPermiso.permiso.nidPermiso = :nidPermiso";
        List<UsuarioPermiso> lstObject = em.createQuery(strsql)
                                           .setParameter("nidUsuario", nidUsuario)
                                           .setParameter("nidPermiso", nidPermiso)
                                           .getResultList();
        UsuarioPermiso up = null;
        if (lstObject.size() > 0) {
            up = lstObject.get(0);            
        }
        return up;            
    }
    
    
}
