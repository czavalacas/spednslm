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
}