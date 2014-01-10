package sped.negocio.BDL.SFBean;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Usuario;

@Stateless(name = "BDL_C_SFUsuario", mappedName = "mapBDL_C_SFUsuario")
public class BDL_C_SFUsuarioBean implements BDL_C_SFUsuarioRemote, 
                                            BDL_C_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFUsuarioBean() {
    }

    /** <code>select o from Usuario o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> getUsuarioFindAll() {
        return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }
    
    public Map autenticarUsuarioBDL(String user, String clave){
        String error = "000";
        Map mapa = new HashMap();
        try{
            String strQuery = "SELECT o " +
                              "FROM Usuario o " +
                              "WHERE o.usuario = :usuario " +
                              "AND o.clave = :clave " +
                              "AND o.estadoUsuario = '1' ";
            Usuario usuario = (Usuario) em.createQuery(strQuery).setParameter("usuario",user).
                                                                 setParameter("clave",clave).
                                                                 getSingleResult();
            if(usuario != null){
                mapa.put("USUARIO",usuario);   
            }else{
                error = "SPED-00001";
            }
        }catch(Exception e){
            error = "SPED-00001";
        }
        mapa.put("MSJ",error);
        return mapa;
    }
    
    /**Metodo para traer a los Evaluadores Siendo nidRol=1 el de evaluador**/
    public List<Usuario> getEvaluadores(){
        try{
            String ejbQl = "SELECT ma FROM Usuario ma" +
                           " WHERE ma.rol.nidRol=1";
                List<Usuario> lstEval = em.createQuery(ejbQl).getResultList();        
                return lstEval;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<Usuario> getUsuarioByEstadoBDL(String estado) {
        List<Usuario> lstUsuario = null;
        try {
            String strQuery = "SELECT u " + "FROM Usuario u " + "WHERE u.estadoUsuario = :estado ";
            lstUsuario = em.createQuery(strQuery).setParameter("estado", estado).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstUsuario;
    }
    
    public Usuario findConstrainById(int id){
        try{
            Usuario instance = em.find(Usuario.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public int countUsuarioByDniBDL(String dni){
        String ejbQL = "SELECT  count(u) FROM Usuario u " 
                       + "WHERE u.dni = :dni ";
        Object object = em.createQuery(ejbQL).setParameter("dni", dni)
                            .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
    public int countUsuarioByNomUsuarioBDL(String usuario){
        String ejbQL = "SELECT  count(u) FROM Usuario u " 
                       + "WHERE u.usuario = :usuario ";
        Object object = em.createQuery(ejbQL).setParameter("usuario", usuario)
                            .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
}