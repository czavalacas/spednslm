package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;

@Stateless(name = "LN_T_SFUsuario", mappedName = "SPED_APP-SPED_NEGOCIO-LN_T_SFUsuario")
public class LN_T_SFUsuarioBean implements LN_T_SFUsuarioRemote, LN_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
        
    @EJB
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;

    public LN_T_SFUsuarioBean() {
    }
    
    public void gestionUsuarioLN(int tipoEvento,
                                 String nombre,
                                 String apellido,
                                 String dni,
                                 String usuario,
                                 String clave,
                                 int idUsuario){
        Usuario u = new Usuario();
        if(tipoEvento == 1){
            
        }else if(tipoEvento == 2){
            u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
            u.setNombres(nombre+"%"+apellido);
            u.setDni(dni);
            u.setUsuario(usuario);
            u.setClave(clave);
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }else if(tipoEvento == 3){
            u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
            u.setEstadoUsuario("0");
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }
    }
}
