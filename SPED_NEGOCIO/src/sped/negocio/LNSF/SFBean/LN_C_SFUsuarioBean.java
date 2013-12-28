package sped.negocio.LNSF.SFBean;

import java.util.Map;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanError;
import sped.negocio.entidades.beans.BeanUsuario;

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
    private LN_C_SFErrorLocal ln_C_SFErrorLocal;
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
                if(msj.equals("000")){
                    beanUsuario = (BeanUsuario)mapper.map(user, BeanUsuario.class);
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
}