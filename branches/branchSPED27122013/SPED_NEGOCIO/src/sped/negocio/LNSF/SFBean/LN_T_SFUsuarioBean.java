package sped.negocio.LNSF.SFBean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IL.BDL_C_SFRolLocal;
import sped.negocio.BDL.IL.BDL_C_SFSedeLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.BDL.IR.BDL_T_SFMainRemoto;
import sped.negocio.BDL.SFBean.BDL_C_SFProfesorBean;
import sped.negocio.BDL.SFBean.BDL_T_SFProfesorBean;
import sped.negocio.LNSF.IL.LN_C_SFCorreoLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IL.LN_T_SFLoggerLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Sede;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.sist.Rol;

@Stateless(name = "LN_T_SFUsuario", mappedName = "SPED_APP-SPED_NEGOCIO-LN_T_SFUsuario")
public class LN_T_SFUsuarioBean implements LN_T_SFUsuarioRemote, 
                                           LN_T_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    @EJB
    private BDL_C_SFAreaAcademicaLocal bdL_C_SFAreaAcademicaLocal;
    @EJB
    private BDL_C_SFSedeLocal bdL_C_SFSedeLocal;
    @EJB
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private LN_C_SFCorreoLocal ln_C_SFCorreoLocal;
    @EJB
    private LN_C_SFUsuarioPermisoLocal ln_C_SFUsuarioPermisoLocal;  
    @EJB
    private LN_T_SFLoggerLocal ln_T_SFLoggerLocal;
    private static final String CLASE = "sped.negocio.LNSF.SFBean.LN_T_SFUsuarioBean";

    public LN_T_SFUsuarioBean() {
    }
    
    /**
     * Metod gestionar usuario ingresar, modifcar y eliminacion logica
     * @author angeles
     * @param tipoEvento
     * @param nombres
     * @param dni
     * @param correo
     * @param nidRol
     * @param nidAreaA
     * @param usuario
     * @param idUsuario
     * @param rutaImagenes
     * @param rutaImg
     * @param nidSede
     * @param isSupervisor
     */
    public void gestionUsuarioLN(int tipoEvento,
                                 String nombres,
                                 String dni,
                                 String correo,
                                 int nidRol,
                                 int nidAreaA,
                                 String usuario,
                                 int idUsuario,
                                 String rutaImagenes,
                                 String rutaImg,
                                 int nidSede,
                                 boolean isSupervisor){
        try{
            Usuario u = new Usuario();
            Rol r = new Rol();
            if(tipoEvento > 1){
                u = bdL_C_SFUsuarioLocal.findConstrainById(idUsuario);
                if(tipoEvento == 2){
                    r = u.getRol();
                }
            }
            if(tipoEvento == 1 || tipoEvento == 2){
                Rol rol = bdL_C_SFRolLocal.findConstrainById(nidRol);
                AreaAcademica area = bdL_C_SFAreaAcademicaLocal.findEvaluadorById(nidAreaA);
                Sede sede = bdL_C_SFSedeLocal.findSedeById(nidSede);
                u.setSede(sede);
                u.setNombres(nombres);            
                u.setDni(dni);
                u.setCorreo(correo);
                u.setRol(rol);
                u.setAreaAcademica(area);
                u.setUsuario(usuario);   
                u.setIsSupervisor((rol.getNidRol() == 2 && isSupervisor) ? "1" : "0");
                u.setTipoFichaCurso(rol.getNidRol() == 2 ? "GE" : rol.getNidRol() == 4 ? "SD" : null);//dfloresgonz 21.08.2014
                if(rutaImg != null){
                    u.setFoto(Utiles.Imagen(rutaImg));
                }
                if(tipoEvento == 1){                
                    u.setEstadoUsuario("1");
                    u.setClave(usuario);
                    ln_C_SFCorreoLocal.recuperarClave(correo, 1, rutaImagenes);//envia correo por primera vez
                    u.setIsNuevo("1");                
                    bdL_T_SFUsuarioLocal.persistUsuario(u);
                    ln_C_SFUsuarioPermisoLocal.insertUsuarioPermisobyUsuario(u, null);
                    return;
                }            
            }
            if(tipoEvento == 3){
                u.setEstadoUsuario("0");
            }
            if(tipoEvento == 4){
                u.setEstadoUsuario("1");
            }
            if(tipoEvento > 1){
                bdL_T_SFUsuarioLocal.mergeUsuario(u);
                if(tipoEvento == 2){
                    ln_C_SFUsuarioPermisoLocal.updateUsuarioPermisobyUsuario(u);
                }            
            }
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(idUsuario, "TRA", "gestionUsuarioLN(...)", CLASE, 
                                                          "Metodo gestionar usuario: insertar, modificar, eliminacion logica", 
                                                          Utiles.getStack(e));
            e.printStackTrace();
        }        
    }
    
    /**
     * Metodo que modifica la configuracion del usuario : clave, correo, foto
     * @param nidUsuario
     * @param clave
     * @param correo
     * @param rutaImg
     */
    public void configuracionCuentaUsuario(int nidUsuario,
                                           String clave,
                                           String correo,
                                           String rutaImg){
        try{
            Usuario u = bdL_C_SFUsuarioLocal.findConstrainById(nidUsuario);
            if(clave != null){
                u.setClave(clave);
            }
            if(correo != null){
                u.setCorreo(correo);
            }
            if(rutaImg != null){
                u.setFoto(Utiles.Imagen(rutaImg));
            }        
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }catch(Exception e){
            e.printStackTrace();
            ln_T_SFLoggerLocal.registrarLogErroresSistema(nidUsuario, "TRA", "configuracionCuentaUsuario(...)", CLASE, 
                                                          "Metodo que modifica la configuracion del usuario : clave, correo, foto", 
                                                          Utiles.getStack(e));
        }
    }
    
    /**
     * Metodo que cambia la primera clave del usuario o cada cierto tiempo
     * @param nidUsuario
     * @param clave
     */
    public void cambiarPrimeraClave(int nidUsuario,
                                    String clave){
        try{
            Usuario u = bdL_C_SFUsuarioLocal.findConstrainById(nidUsuario);
            u.setClave(clave);
            u.setIsNuevo("0");
            bdL_T_SFUsuarioLocal.mergeUsuario(u);
        }catch(Exception e){
            ln_T_SFLoggerLocal.registrarLogErroresSistema(nidUsuario, "TRA", "cambiarPrimeraClave(...)", CLASE, 
                                                          "Metodo que cambia la primera clave del usuario o cada cierto tiempo", 
                                                          Utiles.getStack(e));
        }
    }

    public String cambiarEstadoUsuarioProfesores(List<BeanProfesor> listprofesores) {
        List<Usuario> listUsuarios = bdL_C_SFUsuarioLocal.getUsuarioTipoProfesor();
        for (int i = 0; i < listprofesores.size(); i++) {
            for (int j = 0; j < listUsuarios.size(); j++) {
                if (listprofesores.get(i).getDniProfesor().equals(listUsuarios.get(j).getDni())) {
                    Usuario usua = listUsuarios.get(j);
                    usua.setEstadoUsuario("1");
                    bdL_T_SFUsuarioLocal.mergeUsuario(usua);
                    listUsuarios.remove(j);
                }
            }
        }
        /**Cambia de estado a 0 en usuario a los docentes que no vienen en la nueva carga*/
        //dfloresgonz 04/04/2015 Se comenta porque se cargan docentes parcialmente
        /*for (int i = 0; i < listUsuarios.size(); i++) {
            Usuario usua = listUsuarios.get(i);
            usua.setEstadoUsuario("0");
            bdL_T_SFUsuarioLocal.mergeUsuario(usua);
            /**Cambiarle su estado en main a 0 a los docentes que ya no aparecen la nueva carga*/
            /*List<Main> listMain = bdl_C_SFMainRemote.getHorariosPorDocente(listUsuarios.get(i).getDni());
            if (listMain != null) {
                for (Main entida : listMain) {
                    entida.setEstado("0");
                    bdl_T_SFMainRemoto.mergeMain(entida);
                }
            }
        }*/
        return null;
    }
}
