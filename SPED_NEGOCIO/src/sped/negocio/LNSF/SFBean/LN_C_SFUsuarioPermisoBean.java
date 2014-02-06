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
import sped.negocio.entidades.sist.Rol;
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
            for(RolPermiso rolPermiso : lstRP){
                UsuarioPermiso up = new UsuarioPermiso();
                up.setRolPermiso(rolPermiso);
                up.setUsuario(usuario);
                if(rolPermiso.getPermiso().getNidPermiso() == 7 ||
                   rolPermiso.getPermiso().getNidPermiso() == 9){
                    up.setIsWS("1");
                }else{
                    up.setIsWS("0");
                }
                up.setEstado("1");
                bdL_T_SFUsuarioPermisoLocal.persistUsuarioPermiso(up);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateUsuarioPermisobyUsuario(Usuario usuario){
        boolean valida = true;
        int cont = 0;
        if(usuario.getUsuarioPermisosLista().size() == 0){
            insertUsuarioPermisobyUsuario(usuario, null);
        }else{
            List<UsuarioPermiso> lstUsuarioPermiso = bdL_C_SFUsuarioPermisoLocal
                                                        .getUsuarioPermisoByUsuario(usuario);
            List<RolPermiso> lstRolPermiso = bdL_C_SFRolPermisoLocal
                                                        .getPermisosByRolBDL(usuario.getRol());
            List<UsuarioPermiso> lstUsuarioPermisoAux = new ArrayList();
            while(valida){                
                int nidPermisoUsuarioPermiso = lstUsuarioPermiso.get(0).getRolPermiso().getPermiso().getNidPermiso();
                int nidPermisoRolPermiso = lstRolPermiso.get(cont).getPermiso().getNidPermiso();
                if(nidPermisoUsuarioPermiso > nidPermisoRolPermiso){
                    cont++;
                }else{
                    if(nidPermisoUsuarioPermiso == nidPermisoRolPermiso){
                        UsuarioPermiso up = lstUsuarioPermiso.get(0);
                        up.setRolPermiso(lstRolPermiso.get(cont));
                        bdL_T_SFUsuarioPermisoLocal.mergeUsuarioPermiso(up);
                        lstRolPermiso.remove(lstRolPermiso.get(cont));
                    }
                    if(nidPermisoUsuarioPermiso < nidPermisoRolPermiso){
                        lstUsuarioPermisoAux.add(lstUsuarioPermiso.get(0));
                    }
                    lstUsuarioPermiso.remove(lstUsuarioPermiso.get(0));
                }
                if(cont == lstRolPermiso.size()){
                    for(int i = 0; i < lstUsuarioPermiso.size(); i++){
                        lstUsuarioPermisoAux.add(lstUsuarioPermiso.get(0));
                    }
                    valida = false;
                }
            }
            if(lstUsuarioPermisoAux.size() == 0){
                insertUsuarioPermisobyUsuario(usuario, lstRolPermiso);
                lstRolPermiso.clear();
            }else if(lstRolPermiso.size() == 0){
                for(int i = 0 ; i< lstUsuarioPermisoAux.size(); i++){
                    bdL_T_SFUsuarioPermisoLocal.removeUsuarioPermiso(lstUsuarioPermisoAux.get(i));
                }
                lstUsuarioPermisoAux.clear();
            }else{
                boolean valida2 = true;
                while(valida2){
                    UsuarioPermiso up = lstUsuarioPermisoAux.get(0);
                    RolPermiso rolPermiso = lstRolPermiso.get(0);
                    up.setRolPermiso(rolPermiso);
                    up.setUsuario(usuario);
                    if(rolPermiso.getPermiso().getNidPermiso() == 7 ||
                       rolPermiso.getPermiso().getNidPermiso() == 9){
                        up.setIsWS("1");
                    }else{
                        up.setIsWS("0");
                    }
                    up.setEstado("1");
                    bdL_T_SFUsuarioPermisoLocal.mergeUsuarioPermiso(up);
                    lstUsuarioPermisoAux.remove(lstUsuarioPermisoAux.get(0));
                    lstRolPermiso.remove(lstRolPermiso.get(0));
                    if(lstUsuarioPermisoAux.size() == 0 || lstRolPermiso.size() == 0){
                        valida2 = false;
                    }
                }
            }
            if(lstUsuarioPermisoAux.size() == 0 && lstRolPermiso.size() != 0){
                insertUsuarioPermisobyUsuario(usuario, lstRolPermiso);
            }else if(lstRolPermiso.size() == 0 && lstUsuarioPermisoAux.size() != 0){
                for(int i = 0 ; i < lstUsuarioPermisoAux.size(); i++){
                    System.out.println(i);
                    System.out.println(lstUsuarioPermisoAux.size());
                    System.out.println(lstUsuarioPermisoAux.get(i).getNidPermisoUsuario());
                    bdL_T_SFUsuarioPermisoLocal.removeUsuarioPermiso(lstUsuarioPermisoAux.get(i));
                }
            }
        }   
    }
    
    public void updateUsuarioPermisobyUsuario_Aux(Usuario usuario){
        
    }     
}
