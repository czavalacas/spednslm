package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFAulaLocal;
import sped.negocio.BDL.IR.BDL_C_SFAulaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.beans.BeanAula;

@Stateless(name = "BDL_C_SFAula", mappedName = "map-BDL_C_SFAula")
public class BDL_C_SFAulaBean implements BDL_C_SFAulaRemote, 
                                         BDL_C_SFAulaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFAulaBean() {
    }

    /** <code>select o from Aula o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Aula> getAulaFindAll() {
        return em.createNamedQuery("Aula.findAll", Aula.class).getResultList();
    }
    
    /**
     * Metodo para buscar el id aula
     * @param beanAula
     * @return nidAula
     */
    public int getAulaByDescripcion(BeanAula beanAula) {
        String ejbQL =
            "SELECT  a.nidAula FROM Aula a " + "WHERE a.descripcionAula = :descripcion " +
            "AND a.sede.nidSede = :nidSede ";
        if (beanAula.getNidNivel() != 0) {
            ejbQL = ejbQL.concat("AND a.gradoNivel.nivel.nidNivel = :nidNivel ");
        }
        Query query = em.createQuery(ejbQL);
        query.setParameter("descripcion", beanAula.getDescripcionAula()).setParameter("nidSede", beanAula.getNidSede());
        if (beanAula.getNidNivel() != 0) {
            query.setParameter("nidNivel", beanAula.getNidNivel());
        }
        List<Object> lstObject = query.getResultList();
        int nid = 0;
        if (lstObject.size() > 0) {
            nid = Integer.parseInt(lstObject.get(0).toString());
        }
        return nid;
    }
    
    public List<Aula> getAulaPorSedeNivelYGrado(String nidSede, String nidGrado, String nidNivel) {
        try {
            String ejbQl =    " SELECT distinct au from Aula au" +
                              " where 1=1 ";
            if (nidSede != null) {               
                    ejbQl = ejbQl.concat(" and au.sede.nidSede="+nidSede);               
            }
            if (nidGrado != null) {               
                    ejbQl = ejbQl.concat(" and au.gradoNivel.grado.nidGrado="+nidGrado);               
            }
            if (nidNivel != null) {               
                    ejbQl = ejbQl.concat(" and au.gradoNivel.nivel.nidNivel="+nidNivel);               
            }
            
            ejbQl = ejbQl.concat(" ORDER by au.descripcionAula");
            
            List<Aula> lstAula = em.createQuery(ejbQl).getResultList();
            return lstAula;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
}
