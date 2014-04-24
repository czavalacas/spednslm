package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFRolLocal;
import sped.negocio.BDL.IR.BDL_C_SFRolRemote;
import sped.negocio.entidades.sist.Rol;

@Stateless(name = "BD_C_SFRol", mappedName = "SPED_APP-SPED_NEGOCIO-BD_C_SFRol")
public class BDL_C_SFRolBean implements BDL_C_SFRolRemote, 
                                           BDL_C_SFRolLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFRolBean() {
    }

    /** <code>select o from Rol o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Rol> getRolFindAll() {
        return em.createNamedQuery("Rol.findAll", Rol.class).getResultList();
    }
    
    public Rol findConstrainById(int id){
        try{
            Rol instance = em.find(Rol.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }
    }
    
    public int getIdbyDescripcion(String descripcion){
        String ejbQL =  "select o.nidRol " +
                        "from Rol o where upper(o.descripcionRol) = :descripcionRol ";
        Object oRol = em.createQuery(ejbQL)
                            .setParameter("descripcionRol", descripcion.toUpperCase())
                            .getSingleResult();
        int nidRol = 0;
        if(oRol != null){
            nidRol = Integer.parseInt(oRol.toString());
        }
        return nidRol;
    }
    
    public List<Rol> getListRolbyNombreBDL(String descripcion){
        try{
            String ejbQL =  "select o " +
                            "from Rol o " +
                            "where upper(o.descripcionRol) like :d_Rol ";
            return (List<Rol>) em.createQuery(ejbQL).setParameter("d_Rol", "%"+descripcion+"%")
                                                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }        
    }
    
    public boolean isSubDirectorByNidUsuario(Integer nidUsuario){
        try{
            String qlString = "SELECT r.nidRol " +
                              "FROM Rol r," +
                              "     Usuario u " +
                              "WHERE u.rol.nidRol = r.nidRol " +
                              "AND u.nidUsuario = :nidUsuario ";
            List<Object> nidRol = em.createQuery(qlString)
                                .setParameter("nidUsuario",nidUsuario)
                                .getResultList();
            if(nidRol != null){
                if(nidRol.size() > 0){
                    int rol = (Integer) nidRol.get(0);
                    if(rol == 4){
                        return true;
                    }
                }
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
