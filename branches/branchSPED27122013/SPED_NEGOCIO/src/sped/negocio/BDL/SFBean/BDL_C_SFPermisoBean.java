package sped.negocio.BDL.SFBean;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFPermisoLocal;
import sped.negocio.BDL.IR.BDL_C_SFPermisoRemote;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.Permiso;

@Stateless(name = "BDL_C_SFPermiso", mappedName = "mapBDL_C_SFPermiso")
public class BDL_C_SFPermisoBean implements BDL_C_SFPermisoRemote, 
                                               BDL_C_SFPermisoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFPermisoBean() {
    }
    
    public List<Permiso> getByNidPermiso(int nidPermiso){
        String ejbQL =  "select o " +
                        "from Permiso o " +
                        "where o.nidPadre = :nidPermiso "+
                        "and o.estadoRegistro = 1 ";
        return em.createQuery(ejbQL)
                          .setParameter("nidPermiso", nidPermiso)
                          .getResultList();
    }
    
    public int getNiveles(){
        String ejbQL =  "select count(distinct(o.nivel)) " +
                        "from Permiso o ";
        Object oNiveles = em.createQuery(ejbQL)
                            .getSingleResult();
        int iNiveles = 0;
        if(oNiveles != null){
            iNiveles = Integer.parseInt(oNiveles.toString());
        }
        return iNiveles;
    }
    
    public List<Permiso> getHijosByPadre(int nidPadre,
                                         int nidUsuario,
                                         int nidRol){
        String  ejbQL = "SELECT per " +
                        "FROM Permiso per, " +
                        "     RolPermiso up," +
                        "     UsuarioPermiso uspe "+
                        "WHERE up.permiso.nidPermiso = per.nidPermiso "+
                        "AND up.rol.nidRol = :nidRol "+
                        "AND per.nidPadre = :nidPadre " +
                        "AND uspe.rolPermiso.permiso.nidPermiso = per.nidPermiso " +
                        "AND uspe.rolPermiso.rol.nidRol = :nidRol " +
                        "AND uspe.usuario.nidUsuario = :nidUsuario " +
                        "AND uspe.estado = '1' "+
                        "AND per.isWS = '0' "+
                        "AND up.permiso.estadoRegistro = 1 " +
                        "ORDER BY per.descripcionPermiso ASC";
        return em.createQuery(ejbQL)
                    .setParameter("nidRol", nidRol)
                    .setParameter("nidPadre", nidPadre)
                    .setParameter("nidUsuario", nidUsuario)
                    .getResultList();
    }
    
    public List<Permiso> getHijosByPadre_WS(int nidUsuario,
                                             int nidRol){
        String  ejbQL = "SELECT per " +
                        "FROM Permiso per, " +
                        "     RolPermiso up," +
                        "     UsuarioPermiso uspe "+
                        "WHERE up.permiso.nidPermiso = per.nidPermiso "+
                        "AND uspe.usuario.nidUsuario = :nidUsuario "+
                        "AND up.rol.nidRol = :nidRol "+
                        "AND per.isWS = '1' "+
                        "AND uspe.rolPermiso.permiso.nidPermiso = per.nidPermiso " +
                        "AND uspe.rolPermiso.rol.nidRol = :nidRol " +
                        "AND uspe.estado = '1' "+
                        "AND up.permiso.estadoRegistro = 1";
        return em.createQuery(ejbQL)
                    .setParameter("nidRol", nidRol)
                    .setParameter("nidUsuario", nidUsuario)
                    .getResultList();
    }
    
    public List<Permiso> getHijosByPadreGP(int nidPadre,
                                  int nidRol){
        String  ejbQL = "SELECT per " +
                        "FROM Permiso per, " +
                        "     RolPermiso up " +
                        "WHERE up.permiso.nidPermiso = per.nidPermiso "+
                        "AND up.rol.nidRol = :nidRol "+
                        "AND per.nidPadre = :nidPadre " +
                        "AND up.permiso.estadoRegistro = 1 " +
                        "ORDER BY per.descripcionPermiso ASC";
        return em.createQuery(ejbQL)
                    .setParameter("nidRol", nidRol)
                    .setParameter("nidPadre", nidPadre)
                    .getResultList();
    }
}