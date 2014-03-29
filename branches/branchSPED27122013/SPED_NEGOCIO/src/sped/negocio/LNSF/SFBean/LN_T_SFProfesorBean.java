package sped.negocio.LNSF.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_T_SFCursoLocal;
import sped.negocio.BDL.IL.BDL_T_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_T_SFProfesorLocal;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.sist.Rol;

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

    public LN_T_SFProfesorBean() {
    }
    
    public String grabarProfesoresNuevos(List<BeanProfesor> listaProfesores){
        BeanError beanError = new BeanError();
        String error = "000";
        try {            
            for(int i=0; i<listaProfesores.size(); i++){
            Profesor prof=new Profesor();
                prof.setDniProfesor(listaProfesores.get(i).getDniProfesor());
                prof.setNombres(listaProfesores.get(i).getNombres());
                prof.setApellidos(listaProfesores.get(i).getApellidos());
                bdl_T_SFProfesorLocal.persistProfesor(prof);
                
                
           Usuario usua=new Usuario();
                Rol role=new Rol();
                role.setNidRol(3);
                usua.setRol(role);
                usua.setNombres(listaProfesores.get(i).getNombres()+" "+listaProfesores.get(i).getApellidos());
                usua.setDni(listaProfesores.get(i).getDniProfesor());
                usua.setUsuario(listaProfesores.get(i).getDniProfesor());
                usua.setEstadoUsuario("1");
                usua.setClave("123");
                bdl_T_SFUsuarioLocal.persistUsuario(usua);
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
}
