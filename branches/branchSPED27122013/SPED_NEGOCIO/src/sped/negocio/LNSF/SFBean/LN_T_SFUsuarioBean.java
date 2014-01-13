package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IL.BDL_C_SFRolLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.sist.Rol;

@Stateless(name = "LN_T_SFUsuario", mappedName = "SPED_APP-SPED_NEGOCIO-LN_T_SFUsuario")
public class LN_T_SFUsuarioBean implements LN_T_SFUsuarioRemote, 
                                           LN_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
        
    @EJB
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    @EJB
    private BDL_C_SFAreaAcademicaLocal bdL_C_SFAreaAcademicaLocal;

    public LN_T_SFUsuarioBean() {
    }
    
    public void gestionUsuarioLN(int tipoEvento,
                                 String nombres,
                                 String dni,
                                 int nidRol,
                                 int nidAreaA,
                                 String usuario,
                                 String clave,
                                 int idUsuario){
        Usuario u = new Usuario();
        if(tipoEvento == 1){
            Rol rol = bdL_C_SFRolLocal.findConstrainById(nidRol);
            AreaAcademica area = bdL_C_SFAreaAcademicaLocal.findEvaluadorById(nidAreaA);
            u.setNombres(nombres);            
            u.setDni(dni);
            u.setRol(rol);
            u.setAreaAcademica(area);
            u.setEstadoUsuario("1");
            u.setUsuario(usuario);
            u.setClave(clave);
            bdL_T_SFUsuarioLocal.persistUsuario(u);
        }else if(tipoEvento == 2){
            u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
            Rol rol = bdL_C_SFRolLocal.findConstrainById(nidRol);
            AreaAcademica area = bdL_C_SFAreaAcademicaLocal.findEvaluadorById(nidAreaA);
            u.setNombres(nombres);            
            u.setDni(dni);
            u.setRol(rol);
            u.setAreaAcademica(area);
            u.setUsuario(usuario);
            u.setClave(clave);
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }else if(tipoEvento == 3){
            u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
            u.setEstadoUsuario("0");
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }else if(tipoEvento == 4){
            u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
            u.setEstadoUsuario("1");
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }
    }
}
