package sped.negocio.LNSF.SFBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IL.BDL_C_SFRolLocal;
import sped.negocio.BDL.IL.BDL_C_SFSedeLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_T_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioPermisoLocal;
import sped.negocio.LNSF.IL.LN_T_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Sede;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanError;
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
    private BDL_T_SFUsuarioLocal bdL_T_SFUsuarioLocal;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    @EJB
    private BDL_C_SFAreaAcademicaLocal bdL_C_SFAreaAcademicaLocal;
    @EJB
    private BDL_C_SFSedeLocal bdL_C_SFSedeLocal;
    @EJB
    private LN_C_SFUsuarioPermisoLocal ln_C_SFUsuarioPermisoLocal;   

    public LN_T_SFUsuarioBean() {
    }
    
    public void gestionUsuarioLN(int tipoEvento,
                                 String nombres,
                                 String dni,
                                 String correo,
                                 int nidRol,
                                 int nidAreaA,
                                 String usuario,
                                 String clave,
                                 int idUsuario,
                                 String rutaImg,
                                 int nidSede){
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
            if(correo != null && correo.length() == 0){
                correo = null;
            }
            u.setCorreo(correo);
            u.setRol(rol);
            u.setAreaAcademica(area);
            u.setUsuario(usuario);
            u.setClave(clave);
            if(rutaImg != null){
                Imagen(rutaImg, u);
            }
            if(tipoEvento == 1){
                u.setEstadoUsuario("1");
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
            if(r != u.getRol()){
                ln_C_SFUsuarioPermisoLocal.updateUsuarioPermisobyUsuario(u);
            }            
        }
        
    }
    
    public static byte[] extractBytes(String ImageName) throws IOException {
        File archivo = new File(ImageName);
        byte[] aBytes = null;
        long tamanoA = archivo.length(); 
        aBytes = new byte[(int) tamanoA];
        try{
            FileInputStream docu = new FileInputStream(archivo);
            int numBytes = docu.read(aBytes);
            docu.close();
            archivo.delete();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return aBytes;
    }
    
    public void Imagen(String rutaImg, Usuario u){
        try{
            if(rutaImg != null){
                if(!rutaImg.equals("")){
                    byte[] byt = extractBytes(rutaImg);
                    if(byt != null){
                        u.setFoto(byt);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void configuracionCuentaUsuario(int nidUsuario,
                                           String clave,
                                           String correo,
                                           String rutaImg){
        Usuario u = bdL_C_SFUsuarioLocal.findConstrainById(nidUsuario);
        if(clave != null){
            u.setClave(clave);
        }
        if(correo != null){
            u.setCorreo(correo);
        }
        if(rutaImg != null){
            Imagen(rutaImg, u);
        }        
        bdL_T_SFUsuarioLocal.mergeUsuario(u);
    }
    
    public String cambiarEstadoUsuarioProfesores(List<BeanProfesor> listprofesores){
        List<Usuario> listUsuarios=bdL_C_SFUsuarioLocal.getUsuarioTipoProfesor();
        for(int i=0; i<listprofesores.size(); i++){
        for(int j=0; j<listUsuarios.size(); j++){            
            if(listprofesores.get(i).getDniProfesor().equals(listUsuarios.get(j).getDni())){
                    Usuario usua=listUsuarios.get(j);
                    usua.setEstadoUsuario("1");
                    bdL_T_SFUsuarioLocal.mergeUsuario(usua);
                    listUsuarios.remove(j);                     
                }
        }
        }
        
        for(int i=0;i<listUsuarios.size(); i++){
            Usuario usua=listUsuarios.get(i);
            usua.setEstadoUsuario("0");
            bdL_T_SFUsuarioLocal.mergeUsuario(usua);    
        }
        return null;
    }
}
