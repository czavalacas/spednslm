package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IR.BDL_C_SFUsuarioPermisoRemote;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioPermisoRemote;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioPermisoRemote;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.Permiso;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Stateless(name = "LN_T_SFUsuarioPermiso", mappedName = "map-LN_T_SFUsuarioPermiso")
public class LN_T_SFUsuarioPermisoBean implements LN_T_SFUsuarioPermisoRemote, 
                                                  LN_T_SFUsuarioPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUsuarioPermisoRemote bdL_C_SFUsuarioPermisoRemote;
    @EJB
    private BDL_T_SFUsuarioPermisoRemote bdl_T_SFUsuarioPermisoRemote;

    public LN_T_SFUsuarioPermisoBean() {
    }
    
    public void gestionPermisoLN(BeanPermiso permiso){
        if(permiso.getListaHijos() != null){
            for(int i=0; i<permiso.getListaHijos().size(); i++){
                UsuarioPermiso p = bdL_C_SFUsuarioPermisoRemote.findConstrainById(
                                                                permiso.getListaHijos().get(i).getNidPermisoUsuario());
                p.setEstado(permiso.getListaHijos().get(i).isEstado() ? "1" : "0");
                bdl_T_SFUsuarioPermisoRemote.mergeUsuarioPermiso(p);
                gestionPermisoLN(permiso.getListaHijos().get(i));                
            }
        }        
    }
}
