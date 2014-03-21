package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.BDL.IR.BDL_C_SFAulaRemote;
import sped.negocio.entidades.beans.BeanAula;

import utils.system;

@Stateless(name = "BDL_C_SFAula", mappedName = "mapBDL_C_SFAula")
public class BDL_C_SFAulaBean implements BDL_C_SFAulaRemote, 
                                         BDL_C_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFAulaBean() {
    }
    
    public int getAulaByDescripcion(BeanAula beanAula){
        String ejbQL = "SELECT  a.nidAula FROM Aula a " + 
                       "WHERE a.descripcionAula = :descripcion " +
                       "AND a.sede.nidSede = :nidSede ";
        if(beanAula.getNidNivel() != 0){
            ejbQL = ejbQL.concat("AND a.gradoNivel.nivel.nidNivel = :nidNivel ");
        }
        Query query = em.createQuery(ejbQL);
        query.setParameter("descripcion", beanAula.getDescripcionAula())
             .setParameter("nidSede", beanAula.getNidSede());
        if(beanAula.getNidNivel() != 0){
            query.setParameter("nidNivel", beanAula.getNidNivel());
        }
        List<Object> lstObject = query.getResultList();
        int nid = 0;
        System.out.println(lstObject);
        System.out.println(lstObject.size());
        if(lstObject.size() > 0){
            nid = Integer.parseInt(lstObject.get(0).toString());
        }
        return nid;
    }
}
