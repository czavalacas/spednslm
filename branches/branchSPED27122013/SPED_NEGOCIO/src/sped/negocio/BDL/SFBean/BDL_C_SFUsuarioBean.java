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
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.FichaCriterio;

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
    
    public boolean testClave(int nidUsuario,
                             String clave){
        try {
            String strQuery = "SELECT COUNT(1) " +
                              "FROM Usuario o " +
                              "WHERE o.nidUsuario = :nidUsuario  "+
                              "AND o.clave = FUNC('AES_ENCRYPT',:clave,:clave) ";
            List lstClaves = em.createQuery(strQuery).setParameter("nidUsuario",nidUsuario).
                                                      setParameter("clave",clave).
                                                      getResultList();
           if(lstClaves.isEmpty()){
               return false;
           }else{
               return Integer.parseInt(lstClaves.get(0).toString()) > 0 ? true : false ;
           }
       } catch (Exception e) {
            e.printStackTrace();
            return false;
       }
    }
    
    public Map autenticarUsuarioBDL(String user, String clave){
        String error = "000";
        Map mapa = new HashMap();
        try{
            String strQuery = "SELECT o " +
                              "FROM Usuario o " +
                              "WHERE o.usuario = :usuario " +
                              "AND o.clave = FUNC('AES_ENCRYPT',:clave,:clave) " +
                              //"AND o.clave = :clave " +
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
            String strQuery = "SELECT ma FROM Usuario ma " +
                             " WHERE (ma.rol.nidRol = 2 Or ma.rol.nidRol = 7) " +
                             " And ma.estadoUsuario = '1' ";
            if (nidAreaAcademica!= null) {               
                strQuery = strQuery.concat(" AND ma.areaAcademica.nidAreaAcademica = "+nidAreaAcademica);  
            }
            List<Usuario> lstUsuarios = em.createQuery(strQuery).getResultList();
            int size = lstUsuarios == null ? 0 : lstUsuarios.size();
            if (size > 0) {
                return lstUsuarios;
            } else {
                return new ArrayList<Usuario>();
            }
        }catch(Exception e){
            e.printStackTrace();  
            return new ArrayList<Usuario>();
        }
    }
    
    public List<Usuario> getUsuarioByEstadoBDL() {
        try {
            String strQuery = "SELECT u " + 
                              "FROM Usuario u " + 
                              "ORDER BY u.estadoUsuario DESC , u.nombres ASC";
            List<Usuario> lstUsuarios = em.createQuery(strQuery).getResultList();
            int size = lstUsuarios == null ? 0 : lstUsuarios.size();
            if (size > 0) {
                return lstUsuarios;
            } else {
                return new ArrayList<Usuario>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Usuario>();
        }
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
        try {
            String ejbQL = "SELECT COUNT(1) FROM Usuario u " + 
                           "WHERE u.dni = :dni ";
            List lst = em.createQuery(ejbQL).setParameter("dni", dni).getResultList();
            if (lst.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(lst.get(0).toString());
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }
    
    public int countUsuarioByNomUsuarioBDL(String usuario){
        try {
            String ejbQL = "SELECT  COUNT(1) FROM Usuario u " +
                           "WHERE u.usuario = :usuario ";
            List lst = em.createQuery(ejbQL).setParameter("usuario", usuario).getResultList();
            if (lst.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(lst.get(0).toString());
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }
    
    public String getNombresUsuarioByNidUsuario(int nidUsuario){
        try {
            String ejbQL = "SELECT  u.nombres FROM Usuario u " + 
                           "WHERE u.nidUsuario = :nid_usuario ";
            List lstResult = em.createQuery(ejbQL).setParameter("nid_usuario", nidUsuario).getResultList();
            if (lstResult.isEmpty()) {
                return null;
            } else {
                return lstResult.get(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
                    strQuery = strQuery.concat(" AND u.sede.nidSede = :u_nidSede ");
                }
                if(beanUsuario.getNidNivel() != 0){
                    strQuery = strQuery.concat(" AND u.nivel.nidNivel = :u_nidNivel ");
                }
            }
            strQuery = strQuery.concat(" ORDER BY u.estadoUsuario DESC , u.nombres ASC ");
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
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> getUsuariobyNidRolBDL(int nidRol){
        try{
            String ejbQL = "SELECT u FROM Usuario u " +
                           "WHERE u.rol.nidRol = :nid_rol " +
                           "AND u.estadoUsuario = '1' " +
                           "ORDER BY u.nombres ASC";
            return (List<Usuario>)em.createQuery(ejbQL).setParameter("nid_rol", nidRol).getResultList();
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<Usuario> getListUsuarioNoAdmin(BeanUsuario usu) {
        List<Usuario> lstUsuario = null;
        try {
            String strQuery = "SELECT u " + 
                              "FROM Usuario u " + 
                              "WHERE u.estadoUsuario = '1' " +
                              "AND u.rol.nidRol <> 6 ";
            if(usu.getNombres() !=  null){
                strQuery = strQuery.concat(" AND upper(u.nombres) like :nombre");
            }
            if(usu.getUsuario() != null){
                strQuery = strQuery.concat(" AND upper(u.usuario) like :usuario ");
            }
            if(usu.getNidRol() != 0){
                strQuery = strQuery.concat(" AND u.rol.nidRol = :nidRol ");
            }
            strQuery = strQuery.concat(" ORDER BY u.nombres ASC ");
            Query query = em.createQuery(strQuery);
            if(usu.getNombres() !=  null){
                query.setParameter("nombre", "%"+usu.getNombres().toUpperCase()+"%");
            }
            if(usu.getUsuario() != null){
                query.setParameter("usuario", "%"+usu.getUsuario().toUpperCase()+"%");
            }
            if(usu.getNidRol() != 0){
                query.setParameter("nidRol", usu.getNidRol());
            } 
            lstUsuario = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstUsuario;
    }
    
    public int countCorreoBDL(String correo){
        try {
            String ejbQL = "SELECT  COUNT(1) FROM Usuario u " + 
                           "WHERE u.correo = :correo ";
            List lst = em.createQuery(ejbQL).setParameter("correo", correo).getResultList();
            if (lst.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(lst.get(0).toString());
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }
    
    public Usuario getUsuarioByCorreoBDL(String correo){
        try{
            String ejbQL = "SELECT u FROM Usuario u "+
                           "WHERE u.correo = :correo ";
            List<Usuario> lst = em.createQuery(ejbQL).setParameter("correo", correo).getResultList();
            if(lst != null){
                if (lst.isEmpty()) {
                    return null;
                } else {
                    return lst.get(0);
                }
            }else{
                return null;    
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    public Usuario getUsuarioByCorreo_Usuario_BDL(String correo,String usuario){
        try{
            String ejbQL = "SELECT u FROM Usuario u "+
                           " WHERE u.correo = :correo " +
                           " AND u.usuario = :usuario ";
            List<Usuario> lst = em.createQuery(ejbQL).setParameter("correo", correo).setParameter("usuario", usuario).getResultList();
            if(lst != null){
                if (lst.isEmpty()) {
                    return null;
                } else {
                    return lst.get(0);
                }
            }else{
                return null;    
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    
    /**
         * Metodo que retorna una lista con los nombres de los evaluadores
         * @return List
         */
        public List getEvaluadores(){
            try{
                String qlString = " Select e.nombres FROM Usuario e " +
                                  " WHERE e.rol.nidRol = 2 OR " +
                                  " e.rol.nidRol = 4 OR "+
                                  " e.rol.nidRol = 5 " +
                                  " ORDER BY e.nombres ASC ";
                return em.createQuery(qlString).getResultList();
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            }
        }
    
    /**
         * Metodo que retorna una lista con los dni de los usuarios
         * @return List
         */
        public List getDniUsuarios(int nidArea, int nidRol){
            try{
                String qlString = " Select e.dni FROM Usuario e " +
                                  " WHERE 1 = 1 ";
                if(nidArea != 0){
                    qlString = qlString.concat(" AND e.areaAcademica.nidAreaAcademica = :nidArea ");
                }
                if(nidRol != 0){
                    qlString = qlString.concat(" AND e.rol.nidRol = :nidRol");
                }
                qlString = qlString.concat(" ORDER BY e.dni ASC ");
                Query query = em.createQuery(qlString);
                if(nidArea != 0){
                    query.setParameter("nidArea", nidArea);
                }
                if(nidRol != 0){
                    query.setParameter("nidRol", nidRol);
                }
                List lstdni = query.getResultList();        
                return lstdni;       
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            }
        }
        
        /**
         * Metodo que retorna una lista con los nombres de los usuarios
         * @return List
         */
        public List getNombresUsuarios(int nidArea, int nidRol){
            try{
                String qlString = " Select e.nombres FROM Usuario e " +
                                  " WHERE 1 = 1 ";
                if(nidArea != 0){
                    qlString = qlString.concat(" AND e.areaAcademica.nidAreaAcademica = :nidArea ");
                }
                if(nidRol != 0){
                    qlString = qlString.concat(" AND e.rol.nidRol = :nidRol");
                }
                qlString = qlString.concat(" ORDER BY e.nombres ASC");
                Query query = em.createQuery(qlString);
                if(nidArea != 0){
                    query.setParameter("nidArea", nidArea);
                }
                if(nidRol != 0){
                    query.setParameter("nidRol", nidRol);
                }
                List lstnom = query.getResultList();        
                return lstnom;       
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            }
        }
        
        /**
         * Metodo que retorna una lista con los nombres de usuario de los Usuarios
         * @return List
         */
        public List getUsuarioUsuarios(int nidArea, int nidRol){
            try{
                String qlString = " Select e.usuario FROM Usuario e " +
                                  " WHERE 1 = 1 ";
                if(nidArea != 0){
                    qlString = qlString.concat(" AND e.areaAcademica.nidAreaAcademica = :nidArea ");
                }
                if(nidRol != 0){
                    qlString = qlString.concat(" AND e.rol.nidRol = :nidRol");
                }
                qlString = qlString.concat(" ORDER BY e.usuario ASC ");
                Query query = em.createQuery(qlString);
                if(nidArea != 0){
                    query.setParameter("nidArea", nidArea);
                }
                if(nidRol != 0){
                    query.setParameter("nidRol", nidRol);
                }
                List lstnom = query.getResultList();        
                return lstnom;       
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            }
        }
        
    public List<Usuario> getUsuarioTipoProfesor() {
        try {
            String strQuery = "SELECT u " + 
                              "FROM Usuario u " + 
                              "where u.rol.nidRol = 3";
            return em.createQuery(strQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Usuario>();
        }
    }
    
    public boolean getIsSupervisor(int nidUsuario){
        try {
            String strQuery = "SELECT u.isSupervisor " +
                              "FROM Usuario u " +
                              "WHERE u.nidUsuario = :nidUsuario ";
            List lstResult = em.createQuery(strQuery).setParameter("nidUsuario",nidUsuario).getResultList();
            if(lstResult.isEmpty()){
                return false;
            }else{
                return Integer.parseInt(lstResult.get(0).toString()) == 1 ? true : false ;
            }
        } catch (Exception e) {
             e.printStackTrace();
             return false;
        }
    }
    
    public String getTipoFichaCurso(int nidUsuario){
        try {
            String strQuery = "SELECT u.tipoFichaCurso " +
                              "FROM Usuario u " +
                              "WHERE u.nidUsuario = :nidUsuario ";
            List lstResult = em.createQuery(strQuery).setParameter("nidUsuario",nidUsuario).getResultList();
            if(lstResult.isEmpty()){
                return null;
            }else{
                return lstResult.get(0).toString();
            }
        } catch (Exception e) {
             e.printStackTrace();
             return null;
        }
    }
    
    /**
     * Metodo que trae el correo del usuario segun su ID.
     * @param dni - DNI del usuario
     * @author dfloresgonz
     * @since 20.05.2014
     * @return el correo del usuario
     */
    public String getCorreoByNidUsuario_BDL(String dni){
        try {
            String ejbQL = "SELECT u.correo " +
                           "FROM Usuario u " + 
                           "WHERE u.dni = :dni ";
            List lstResult = em.createQuery(ejbQL).setParameter("dni", dni).getResultList();
            if(lstResult.isEmpty()){
                return null;
            }else{
                return lstResult.get(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Trae el rol y el nombre del usuario concatenado
     * @author dfloresgonz
     * @since 20.05.2014
     * @param nidUsuario PK de admusua
     * @return String que contiene el rol y el nombre del usuario segun el nidUsuario
     */
    public String getRolNombreUsuario_BDL(int nidUsuario){
        try{
            String strQuery = "SELECT CONCAT(u.rol.descripcionRol,': ',u.nombres) " +
                              "FROM Usuario u " +
                              "WHERE u.nidUsuario = :nidUsuario ";
            List lstResult = em.createQuery(strQuery).setParameter("nidUsuario",nidUsuario).getResultList();
            if(lstResult.isEmpty()){
                return null;
            }else{
                return lstResult.get(0).toString();
            }
        }catch(Exception e){
            return null;
        }
    }
}