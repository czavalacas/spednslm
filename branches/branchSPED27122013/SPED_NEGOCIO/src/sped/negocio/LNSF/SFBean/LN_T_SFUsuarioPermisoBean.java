package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFRolPermisoLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioPermisoRemote;
import sped.negocio.BDL.IR.BDL_T_SFUsuarioPermisoRemote;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioPermisoRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.RolPermiso;
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
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFRolPermisoLocal bdL_C_SFRolPermisoLocal;
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;

    public LN_T_SFUsuarioPermisoBean() {
    }
    
    public void gestionPermisoLN(BeanPermiso permiso, int nidUsuario){
        try{
            if(permiso.getListaHijos() != null){
                for(int i=0; i<permiso.getListaHijos().size(); i++){
                    int nidPermisoUsuario = permiso.getListaHijos().get(i).getNidPermisoUsuario();
                    if(nidPermisoUsuario != 0){
                        UsuarioPermiso p = bdL_C_SFUsuarioPermisoRemote.findConstrainById(nidPermisoUsuario);
                        p.setEstado(permiso.getListaHijos().get(i).isEstado() ? "1" : "0");
                        bdl_T_SFUsuarioPermisoRemote.mergeUsuarioPermiso(p);
                    }else{
                        UsuarioPermiso p = new UsuarioPermiso();
                        Usuario u = bdL_C_SFUsuarioLocal.findConstrainById(nidUsuario);
                        RolPermiso rp = bdL_C_SFRolPermisoLocal.findRolPermisoById(u.getRol().getNidRol(), 
                                                                                   permiso.getListaHijos().get(i).getNidPermiso());
                        p.setUsuario(u);
                        p.setRolPermiso(rp);
                        p.setEstado(permiso.getListaHijos().get(i).isEstado() ? "1" : "0");
                        bdl_T_SFUsuarioPermisoRemote.persistUsuarioPermiso(p);
                    }
                    gestionPermisoLN(permiso.getListaHijos().get(i), nidUsuario);                
                }
            } 
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(nidUsuario, "TRA", "sped.negocio.LNSF.SFBean.LN_T_SFUsuarioPermisoBean", 
                                                          "gestionPermisoLN(BeanPermiso permiso, int nidUsuario)", 
                                                          "Error al insertar y modifcar los permisos del usuario indicado", 
                                                          Utiles.getStack(e));
        }
    }
}
