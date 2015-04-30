package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_C_SFRolPermisoLocal;
import sped.negocio.BDL.IL.BDL_T_SFCursoLocal;
import sped.negocio.BDL.IL.BDL_T_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioPermisoLocal;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFProfesorLocal;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.sist.Permiso;
import sped.negocio.entidades.sist.Rol;
import sped.negocio.entidades.sist.RolPermiso;
import sped.negocio.entidades.sist.UsuarioPermiso;

@Stateless(name = "LN_T_SFProfesor", mappedName = "map-LN_T_SFProfesor")
public class LN_T_SFProfesorBean implements LN_T_SFProfesorRemoto, 
                                              LN_T_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB 
    private BDL_T_SFProfesorLocal bdl_T_SFProfesorLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    @EJB
    private BDL_T_SFUsuarioLocal bdl_T_SFUsuarioLocal;
    @EJB
    private BDL_C_SFRolPermisoLocal bdl_C_SFRolPermisoLocal;
    @EJB
    private BDL_T_SFUsuarioPermisoLocal bdl_T_SFUsuarioPermisoLocal;    
    @EJB
    private BDL_C_SFProfesorLocal bdl_C_SFProfesorLocal;
    @EJB
    private LN_C_SFCorreoLocal ln_C_SFCorreoLocal;

    public LN_T_SFProfesorBean() {
    }
    
    public String grabarProfesoresNuevos(List<BeanProfesor> listaProfesores){
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            for(int i=0; i<listaProfesores.size(); i++){
                /** Agrega los profesores nuevos a admprof*/
                Profesor prof=new Profesor();
                prof.setDniProfesor(listaProfesores.get(i).getDniProfesor());
                prof.setNombres(listaProfesores.get(i).getNombres());
                prof.setApellidos(listaProfesores.get(i).getApellidos());
                prof.setFlgActi(1);
                bdl_T_SFProfesorLocal.persistProfesor(prof);
                /** Crea usuario nuevos de rol Profesor*/
                Usuario usua = new Usuario();
                Rol role = new Rol();
                role.setNidRol(3);
                usua.setRol(role);
                usua.setNombres(listaProfesores.get(i).getNombres()+" "+listaProfesores.get(i).getApellidos());
                usua.setDni(listaProfesores.get(i).getDniProfesor());
                String usuario = listaProfesores.get(i).getCorreo().substring(0, listaProfesores.get(i).getCorreo().indexOf("@") );
                usua.setUsuario(usuario);
                usua.setEstadoUsuario("1");
                usua.setClave(usuario);
                usua.setIsNuevo("1");
                usua.setIsSupervisor("0");
                usua.setCorreo(listaProfesores.get(i).getCorreo());
                bdl_T_SFUsuarioLocal.persistUsuario(usua);
                String data[] = new String[]{usua.getNombres(),usuario,usua.getCorreo()};
                ln_C_SFCorreoLocal.enviarNotifCreacionUsuarioDocente(data);
                /** Agrega los permisos correspondientes de rol profesor*/
                Rol rol = new Rol();
                rol.setNidRol(3);
                List<RolPermiso> lstPermXRoL = bdl_C_SFRolPermisoLocal.getPermisosByRolBDL(rol);
                for(int j = 0; j<lstPermXRoL.size(); j++){ 
                    UsuarioPermiso usrpe = new UsuarioPermiso();
                    Usuario usu=new Usuario();
                    usu.setNidUsuario(usua.getNidUsuario());
                    usrpe.setUsuario(usu);
                    usrpe.setEstado("1");
                    RolPermiso rope = new RolPermiso();
                    Rol rol2 = new Rol();
                    rol2.setNidRol(lstPermXRoL.get(j).getRol().getNidRol());
                    Permiso perm = new Permiso();
                    perm.setNidPermiso(lstPermXRoL.get(j).getPermiso().getNidPermiso());
                    rope.setRol(rol2);
                    rope.setPermiso(perm);                
                    usrpe.setRolPermiso(rope);
                    bdl_T_SFUsuarioPermisoLocal.persistUsuarioPermiso(usrpe); 
                }
            }
        }catch (Exception e) {            
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String grabarProfesor(BeanProfesor profesor){
        BeanError beanError = new BeanError();
        String error = "000";
        try {      
            Profesor prof=new Profesor();
            prof.setDniProfesor(profesor.getDniProfesor());
            prof.setNombres(profesor.getNombres());
            prof.setApellidos(profesor.getApellidos());
            bdl_T_SFProfesorLocal.persistProfesor(prof);         
        }catch (Exception e) {            
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
    
    public String grabarColorProfesor(String dni, String color){
        BeanError beanError = new BeanError();
        String error = "000";
        try {      
            Profesor prof = bdl_C_SFProfesorLocal.getProfesorBydni(dni);
            prof.setColor(color);
            bdl_T_SFProfesorLocal.mergeProfesor(prof);
        }catch (Exception e) {            
            e.printStackTrace();
            error = "111";
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(error);
            error = beanError.getDescripcionError();
        }
        return error;
    }
}