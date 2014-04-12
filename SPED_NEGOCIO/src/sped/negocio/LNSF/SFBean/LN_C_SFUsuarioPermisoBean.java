package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFRolPermisoLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioPermisoLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioPermisoRemote;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.RolPermiso;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Stateless(name = "LN_C_SFUsuarioPermiso", mappedName = "mapLN_C_SFUsuarioPermiso")
public class LN_C_SFUsuarioPermisoBean implements LN_C_SFUsuarioPermisoRemote, 
                                                     LN_C_SFUsuarioPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFRolPermisoLocal bdL_C_SFRolPermisoLocal;
    @EJB
    private BDL_T_SFUsuarioPermisoLocal bdL_T_SFUsuarioPermisoLocal;
    @EJB
    private BDL_C_SFUsuarioPermisoLocal bdL_C_SFUsuarioPermisoLocal;

    public LN_C_SFUsuarioPermisoBean() {
    }
    
    public void insertUsuarioPermisobyUsuario(Usuario usuario, List<RolPermiso> lstRolPermiso){
        try{
            List<RolPermiso> lstRP = new ArrayList();
            if(lstRolPermiso == null){
                lstRP = bdL_C_SFRolPermisoLocal.getPermisosByRolBDL(usuario.getRol());
            }
            else{
                lstRP = lstRolPermiso;
            }
            validaSupervisorArea(usuario, lstRP);
            for(RolPermiso rolPermiso : lstRP){                             
                bdL_T_SFUsuarioPermisoLocal.persistUsuarioPermiso(setUsuarioPermiso(usuario, rolPermiso));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateUsuarioPermisobyUsuario(Usuario usuario){
        boolean valida = true;
        if(usuario.getUsuarioPermisosLista().size() == 0){
            insertUsuarioPermisobyUsuario(usuario, null);
        }else{
            List<UsuarioPermiso> lstUsuarioPermiso = bdL_C_SFUsuarioPermisoLocal
                                                        .getUsuarioPermisoByUsuario(usuario);
            List<RolPermiso> lstRolPermiso = bdL_C_SFRolPermisoLocal
                                                        .getPermisosByRolBDL(usuario.getRol()); 
            UsuarioPermiso up_aux = new UsuarioPermiso();
            up_aux.setUsuario(usuario);
            while(valida){
                int nidUsuarioPermiso = lstUsuarioPermiso.get(0).getRolPermiso().getPermiso().getNidPermiso();
                int nidRolPermiso = lstRolPermiso.get(0).getPermiso().getNidPermiso();
                if(nidUsuarioPermiso == nidRolPermiso){
                    UsuarioPermiso up = lstUsuarioPermiso.get(0);
                    up.setRolPermiso(lstRolPermiso.get(0));
                    bdL_T_SFUsuarioPermisoLocal.mergeUsuarioPermiso(up);
                    lstUsuarioPermiso.remove(lstUsuarioPermiso.get(0));
                    lstRolPermiso.remove(lstRolPermiso.get(0));                    
                }
                if(nidUsuarioPermiso < nidRolPermiso){
                    lstUsuarioPermiso.remove(lstUsuarioPermiso.get(0));
                }
                if(nidUsuarioPermiso > nidRolPermiso){
                    bdL_T_SFUsuarioPermisoLocal.persistUsuarioPermiso(setUsuarioPermiso(usuario, lstRolPermiso.get(0)));
                    lstRolPermiso.remove(lstRolPermiso.get(0)); 
                }
                if(lstRolPermiso.size() == 0){
                    valida = false;
                }
                if(lstUsuarioPermiso.size() == 0){
                    for(RolPermiso rolPermiso : lstRolPermiso){               
                        bdL_T_SFUsuarioPermisoLocal.persistUsuarioPermiso(setUsuarioPermiso(usuario, rolPermiso));
                    }
                    valida = false;
                }
            }
        }
    }
    
    public UsuarioPermiso setUsuarioPermiso(Usuario u, 
                                            RolPermiso rolPermiso){
        UsuarioPermiso up = new UsuarioPermiso();
        up.setUsuario(u);
        up.setRolPermiso(rolPermiso);
        up.setEstado("1");
        return up;
    }     
    
    public void validaSupervisorArea(Usuario usuario, List<RolPermiso> lstRP){
        if(usuario.getRol().getNidRol() == 2 &&  
           usuario.getIsSupervisor().compareTo("0") == 0){
            List<RolPermiso> lstRP_aux = bdL_C_SFRolPermisoLocal.getPermisosSupervisorUsuario();
            for(RolPermiso rp : lstRP_aux){
                lstRP.remove(rp);
            }
        }
    } 
}
