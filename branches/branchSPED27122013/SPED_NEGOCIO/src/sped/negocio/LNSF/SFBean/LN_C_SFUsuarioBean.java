package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import net.sf.dozer.util.mapping.MappingException;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_T_SFLogLocal;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanUsuario;

/**
 * C;ase de Logica de Negocio que implementa los metodos relacionados con la Entidad Usuario: admusua
 * @author dfloresgonz
 */
@Stateless(name = "LN_C_SFUsuario", mappedName = "mapLN_C_SFUsuario")
public class LN_C_SFUsuarioBean implements LN_C_SFUsuarioRemote, 
                                              LN_C_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
    @EJB
    private LN_T_SFLogLocal ln_T_SFLogLocal;
    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFUsuarioBean() {
    }
    
    public BeanUsuario autenticarUsuarioLN(String usuario,String clave){
        BeanUsuario beanUsuario = new BeanUsuario();
        BeanError beanError = new BeanError();
        String msj = "";
        try{
            Map mapa = bdL_C_SFUsuarioLocal.autenticarUsuarioBDL(usuario, clave);
            Usuario user = (Usuario) mapa.get("USUARIO");
            msj   = (String) mapa.get("MSJ");
            if(user != null){
                if(!ln_C_SFPermisosLocal.hasPermisos(user.getNidUsuario(),user.getRol().getNidRol(),"0")){
                    msj = "SPED-00005";
                }
                if(msj.equals("000")){
                    beanUsuario = (BeanUsuario)mapper.map(user, BeanUsuario.class);
                    if(beanUsuario.getFoto() != null){
                        String encoded = Base64.encodeBase64String(beanUsuario.getFoto());
                        //String encoded = MyBase64.encode(beanUsuario.getFoto());
                        beanUsuario.setImg(encoded);
                    }
                }
            }
        }catch(Exception e){
            msj = "SPED-00001";
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(msj);
            beanUsuario.setError(beanError);
            return beanUsuario;
        }
    }
    
    public BeanUsuario autenticarUsuarioLN_WS(String usuario,String clave,String cadenaPhoneData){
        BeanUsuario beanUsuario = new BeanUsuario();
        BeanError beanError = new BeanError();
        String msj = "";
        try{
            Map mapa = bdL_C_SFUsuarioLocal.autenticarUsuarioBDL(usuario, clave);
            Usuario user = (Usuario) mapa.get("USUARIO");
            msj   = (String) mapa.get("MSJ");
            if(user != null){
                if(!ln_C_SFPermisosLocal.hasPermisos(user.getNidUsuario(),user.getRol().getNidRol(),"1")){
                    msj = "SPED-00005";
                }
                if(msj.equals("000")){
                    beanUsuario = (BeanUsuario)mapper.map(user,BeanUsuario.class);
                    if(beanUsuario.getFoto() != null){
                        String encoded = Base64.encodeBase64String(beanUsuario.getFoto());
                        //String encoded = MyBase64.encode(beanUsuario.getFoto());
                        beanUsuario.setImg(encoded);
                    }
                    beanUsuario.setNidLog(ln_T_SFLogLocal.grabarLogLogInWS_LN(cadenaPhoneData,user.getNidUsuario()));
                }
            }
        }catch(Exception e){
            msj = "SPED-00001";
        }finally{
            beanError = ln_C_SFErrorLocal.getCatalogoErrores(msj);
            beanUsuario.setError(beanError);
            return beanUsuario;
        }
    }
    
    /**Metodo LN 
     * creador: czavalacas**/
    public List<BeanUsuario> getEvaluadores(String nidAreaAcademica){      
        List<Usuario>listaEvaluadores = bdL_C_SFUsuarioLocal.getEvaluadores(nidAreaAcademica); 
        List<BeanUsuario> listaBean = new ArrayList<BeanUsuario>();
        Iterator it = listaEvaluadores.iterator();
        while(it.hasNext()){
            Usuario entida = (Usuario) it.next();
            BeanUsuario bean = (BeanUsuario)mapper.map(entida,BeanUsuario.class);
            listaBean.add(bean);
        }
        return listaBean;
    }

    /** Metodo que trae a todos los usuarios del sistema
     *  CUS: Gestionar Usuario
     * @return
     */
    public List<BeanUsuario> getUsuarioByEstadoLN() {
        try{
            return transformLstUsuario(bdL_C_SFUsuarioLocal.getUsuarioByEstadoBDL());
        }catch(Exception e){
            return new ArrayList<BeanUsuario>();
        }
    }
    
    public boolean countUsuarioByDniLN(String dni){
        boolean valida = false;
        if(bdL_C_SFUsuarioLocal.countUsuarioByDniBDL(dni) != 0){
            valida = true;
        }
        return valida;
    }
    
    public boolean countUsuarioByNomUsuarioLN(String usuario){
        boolean valida = false;
        if(bdL_C_SFUsuarioLocal.countUsuarioByNomUsuarioBDL(usuario) != 0){
            valida = true;
        }
        return valida;
    }
    
    public List<BeanUsuario> getUsuariobyByAttrLN(String nombres,
                                                  String usuario,
                                                  String dni,
                                                  int nidAreaAcademica,
                                                  int nidRol,
                                                  int estadoUsuario,
                                                  int nidSede,
                                                  int nidNivel){
        try{
            BeanUsuario beanUsuario = new BeanUsuario();
            beanUsuario.setNombres(nombres);
            beanUsuario.setUsuario(usuario);
            beanUsuario.setDni(dni);
            beanUsuario.setNidRol(nidRol);
            beanUsuario.setNidAreaAcademica(nidAreaAcademica);
            beanUsuario.setNidSede(nidSede);
            beanUsuario.setNidNivel(nidNivel);
            beanUsuario.setEstadoUsuario(estadoUsuario != 0 ? (estadoUsuario-1)+"" : null);
            return transformLstUsuario(bdL_C_SFUsuarioLocal.getUsuariobyByAttrBDL(beanUsuario));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<BeanUsuario>();
        }    
    }
    
    public List<BeanUsuario> getUsuariobyNidRolLN(int nidRol){
        try{
            return transformLstUsuario(bdL_C_SFUsuarioLocal.getUsuariobyNidRolBDL(nidRol));
        }catch(Exception e){
            return new ArrayList<BeanUsuario>();
        }
    }
    
    public List<BeanUsuario> transformLstUsuario(List<Usuario> lstUsurio){
        try{
            List<BeanUsuario> lstBean = new ArrayList();
            for(Usuario u : lstUsurio){
                BeanUsuario beanUsuario = (BeanUsuario) mapper.map(u, BeanUsuario.class);
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("estado_usuario", "admusua", beanUsuario.getEstadoUsuario());
                beanUsuario.setDescripcionEstadoUsuario(constr.getDescripcionAMostrar());
                lstBean.add(beanUsuario);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }        
    }
    
    public BeanUsuario findConstrainByIdLN(int id){
        try{
            BeanUsuario bean = (BeanUsuario)mapper.map(bdL_C_SFUsuarioLocal.findConstrainById(id),BeanUsuario.class);
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public String getNombresUsuarioByNidUsuario_LN(int nidUsuario){
        try{
            return bdL_C_SFUsuarioLocal.getNombresUsuarioByNidUsuario(nidUsuario);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }      
    }
    
    public List<BeanUsuario> getListUsuarioNoAdminLN(){
        try{
            return transformLstUsuario(bdL_C_SFUsuarioLocal.getListUsuarioNoAdmin());
        }catch(Exception e){
            return new ArrayList<BeanUsuario>();
        }
    } 
    
    public List getEvaluadores_LN(){
        return bdL_C_SFUsuarioLocal.getEvaluadores();
    }
    
    public List getDniUsuarios_LN(){
        return bdL_C_SFUsuarioLocal.getDniUsuarios();
    }
    
    public List getNombresUsuarios_LN(int nidArea){
        return bdL_C_SFUsuarioLocal.getNombresUsuarios(nidArea);
    }   

    public List getUsuarioUsuarios_LN(int nidArea){
        return bdL_C_SFUsuarioLocal.getUsuarioUsuarios(nidArea);
    }
}
