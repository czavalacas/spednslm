package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
import sped.negocio.entidades.admin.Main;
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
    
    /**Metodo LN 
     * creador: czavalacas**/
    public List<BeanUsuario> getEvaluadores(){      
        List<Usuario>listaEvaluadores=bdL_C_SFUsuarioLocal.getEvaluadores(); 
        System.out.println("ENTRO A GET EVALUADORES TAMAÑO DE LA ENTIDA : "+listaEvaluadores.size() );
        List<BeanUsuario> listaBean=new ArrayList<BeanUsuario>();
        MapperIF mapper = new DozerBeanMapper();
        Iterator it=listaEvaluadores.iterator();
        while(it.hasNext()){
            Usuario entida= (Usuario)it.next();
            BeanUsuario bean = (BeanUsuario)mapper.map(entida,BeanUsuario.class);
            listaBean.add(bean);
        }
          System.out.println("TAMAÑO DEL BEAN: "+listaBean.size() );
        return listaBean;
      }

    @Override
    public List<BeanUsuario> getUsuarioByEstadoLN(String estado) {
        List<BeanUsuario> lstUsuario = new ArrayList<BeanUsuario>();
        try{
            List<Usuario> lstBDL = bdL_C_SFUsuarioLocal.getUsuarioByEstadoBDL(estado);
            for(int i = 0; i < lstBDL.size(); i++){
                BeanUsuario beanUsuario = new BeanUsuario();
                beanUsuario = (BeanUsuario) mapper.map(lstBDL.get(i), BeanUsuario.class);
                String[] separarNombres = beanUsuario.getNombres().split("%");
                beanUsuario.setNombre(separarNombres[0]);
                beanUsuario.setApellidos(separarNombres[1]);
                lstUsuario.add(beanUsuario);
            }
            return lstUsuario;
        }catch(RuntimeException re){
            throw re;
        }
    }
}
