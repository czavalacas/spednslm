package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
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

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanUsuario;

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
                              "AND o.estadoUsuario = 1 ";
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
    
    /**Metodo para traer a los Evaluadores Siendo nidRol=2 el de evaluador Area**/
    public List<Usuario> getEvaluadores(String nidAreaAcademica){
        try{
            String ejbQl = "SELECT ma FROM Usuario ma" +
                           " WHERE ma.rol.nidRol=2";
            if (nidAreaAcademica!= null) {               
                ejbQl = ejbQl.concat(" and ma.areaAcademica.nidAreaAcademica= "+nidAreaAcademica);  
            }
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
            String strQuery = "SELECT u " + 
                              "FROM Usuario u " + 
                              "WHERE u.estadoUsuario = :estado " +
                              "ORDER BY u.nidUsuario DESC";
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
    
    public String getNombresUsuarioByNidUsuario(int nidUsuario){
        String ejbQL = "SELECT  u.nombres FROM Usuario u " 
                       + "WHERE u.nidUsuario = :nid_usuario ";
        Object object = em.createQuery(ejbQL).setParameter("nid_usuario", nidUsuario)
                          .getSingleResult();
        String nombreUsuario = "";
        if(object != null){
            nombreUsuario = object.toString();
        }
        return nombreUsuario;
    }
    
    public List<Usuario> getUsuariobyByAttrBDL(BeanUsuario beanUsuario){
        try {
            String strQuery = "SELECT u " +
                              "FROM Usuario u " +
                              "WHERE 1 = 1";
            if(beanUsuario!=null){
                if(beanUsuario.getNombres() != null){
                    strQuery = strQuery.concat(" AND upper(u.nombres) like :u_nombres ");
                }
                if(beanUsuario.getUsuario() != null){
                    strQuery = strQuery.concat(" AND upper(u.usuario) like :u_usuario ");
                }
                if(beanUsuario.getDni() != null){
                    strQuery = strQuery.concat(" AND u.dni like :u_dni ");
                }
                if(beanUsuario.getNidRol() != 0){
                    strQuery = strQuery.concat(" AND u.rol.nidRol = :u_nidRol ");
                }
                if(beanUsuario.getNidAreaAcademica() != 0){
                    strQuery = strQuery.concat(" AND u.areaAcademica.nidAreaAcademica = :u_nidAreaAcademica ");
                }
                if(beanUsuario.getEstadoUsuario() != null){
                    strQuery = strQuery.concat(" AND u.estadoUsuario = :u_estadoUsuario ");
                }
                if(beanUsuario.getNidSede() != 0){
                    strQuery = strQuery.concat(" AND u.sedeNivel.sede.nidSede = :u_nidSede ");
                }
                if(beanUsuario.getNidNivel() != 0){
                    strQuery = strQuery.concat(" AND u.sedeNivel.nivel.nidNivel = :u_nidNivel ");
                }
            }
            strQuery = strQuery.concat(" ORDER BY u.nidUsuario DESC ");
            Query query = em.createQuery(strQuery);
            if(beanUsuario!=null){
                if(beanUsuario.getNombres() != null){
                    query.setParameter("u_nombres", "%"+beanUsuario.getNombres().toUpperCase()+"%");
                }
                if(beanUsuario.getUsuario() != null){
                    query.setParameter("u_usuario", "%"+beanUsuario.getUsuario().toUpperCase()+"%");
                }
                if(beanUsuario.getDni() != null){
                    query.setParameter("u_dni", "%"+beanUsuario.getDni()+"%");
                }
                if(beanUsuario.getNidRol() != 0){
                    query.setParameter("u_nidRol", beanUsuario.getNidRol());
                }
                if(beanUsuario.getNidAreaAcademica() != 0){
                    query.setParameter("u_nidAreaAcademica", beanUsuario.getNidAreaAcademica());
                }
                if(beanUsuario.getEstadoUsuario() != null){
                    query.setParameter("u_estadoUsuario", beanUsuario.getEstadoUsuario());
                }
                if(beanUsuario.getNidSede() != 0){
                    query.setParameter("u_nidSede", beanUsuario.getNidSede());
                }
                if(beanUsuario.getNidNivel() != 0){
                    query.setParameter("u_nidNivel", beanUsuario.getNidNivel());
                }
            }
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Usuario> getUsuariobyNidRolBDL(int nidRol){
        try{
            String ejbQL = "SELECT  u FROM Usuario u " +
                           "WHERE u.rol.nidRol = :nid_rol " +
                           "AND u.estadoUsuario = '1' " +
                           "ORDER BY u.nombres ASC";
            return (List<Usuario>)em.createQuery(ejbQL).setParameter("nid_rol", nidRol)
                                                       .getResultList();
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<Usuario> getListUsuarioNoAdmin() {
        List<Usuario> lstUsuario = null;
        try {
            String strQuery = "SELECT u " + 
                              "FROM Usuario u " + 
                              "WHERE u.estadoUsuario = '1' " +
                              "AND u.rol.nidRol <> 6 " +
                              "ORDER BY u.nombres ASC";
            lstUsuario = em.createQuery(strQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstUsuario;
    }
    
    public int countCorreoBDL(String correo){
        String ejbQL = "SELECT  count(u) FROM Usuario u " 
                       + "WHERE u.correo = :correo ";
        Object object = em.createQuery(ejbQL).setParameter("correo", correo)
                            .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
    public Usuario getUsuarioByCorreoBDL(String correo){
        try{
            String ejbQL = "SELECT  u FROM Usuario u " 
                           + "WHERE u.correo = :correo ";
             List<Usuario> lstUsuario =em.createQuery(ejbQL).setParameter("correo", correo)
                                                                .getResultList();
             return lstUsuario.get(0);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
}