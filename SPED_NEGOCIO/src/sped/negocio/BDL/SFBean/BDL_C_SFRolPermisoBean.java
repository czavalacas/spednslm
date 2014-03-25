package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFRolPermisoLocal;
import sped.negocio.BDL.IR.BDL_C_SFRolPermisoRemote;
import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;
import sped.negocio.entidades.sist.RolPermisoPK;

@Stateless(name = "BDL_C_SFRolPermiso", mappedName = "mapBDL_C_SFRolPermiso")
public class BDL_C_SFRolPermisoBean implements BDL_C_SFRolPermisoRemote, 
                                               BDL_C_SFRolPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFRolPermisoBean() {
    }
    
    public List<RolPermiso> getPermisosByRolBDL(Rol rol){
        List<RolPermiso> lst = null; 
        try{
            String strsql = "SELECT r " +
                            "FROM RolPermiso r " +
                            "WHERE r.rol = :rol " +
                            "ORDER BY r.permiso.nidPermiso";
            return (List<RolPermiso>) em.createQuery(strsql).setParameter("rol", rol)
                                                            .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public RolPermiso findRolPermisoById(int nidRol,
                                         int nidPermiso){
        try{
            RolPermisoPK id = new RolPermisoPK();
            id.setPermiso(nidPermiso);
            id.setRol(nidRol);
            RolPermiso instance = em.find(RolPermiso.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
}
