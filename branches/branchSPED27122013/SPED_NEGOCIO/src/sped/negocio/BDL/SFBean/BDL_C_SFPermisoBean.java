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
                                          int nidRol){
        String  ejbQL = "select per " +
                        "from Permiso per, " +
                        "RolPermiso up "+
                        "where up.permiso.nidPermiso = per.nidPermiso "+
                        "and up.rol.nidRol = :nidRol "+
                        "and per.nidPadre = :nidPadre "+
                        "and up.permiso.estadoRegistro = 1";
        return em.createQuery(ejbQL)
                    .setParameter("nidRol", nidRol)
                    .setParameter("nidPadre", nidPadre)
                    .getResultList();
    }
}