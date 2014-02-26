package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Usuario;
/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 30.12.2013
 */
@Stateless(name = "BDL_C_SFAreaAcademica", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFAreaAcademica")
public class BDL_C_SFAreaAcademicaBean implements BDL_C_SFAreaAcademicaRemote, 
                                                  BDL_C_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFAreaAcademicaBean() {
    }

    /** <code>select o from AreaAcademica o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AreaAcademica> getAreaAcademicaFindAll() {
        return em.createNamedQuery("AreaAcademica.findAll", AreaAcademica.class).getResultList();
    }
    
    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public AreaAcademica findEvaluadorById(int id) {
         try {
             AreaAcademica instance = em.find(AreaAcademica.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
    
    
    public List<AreaAcademica> findAreasPorSede_ByOrden(String nidSede) {
        try {
            String ejbQl =    " SELECT distinct area from AreaAcademica area, Sede sed, Aula au, Curso cur, Main ma" +
                              " where 1=1 ";
            if (nidSede != null) {               
                    ejbQl = ejbQl.concat(" and area.nidAreaAcademica=cur.areaAcademica.nidAreaAcademica " +
                        " and ma.curso.nidCurso=cur.nidCurso " +
                        " and ma.aula.nidAula=au.nidAula " +
                        " and au.sede.nidSede=sed.nidSede" +
                        " and sed.nidSede="+nidSede);               
            }
            
            ejbQl = ejbQl.concat(" ORDER by area.descripcionAreaAcademica");
            
            List<AreaAcademica> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    public Curso findCursoById(int id) {
        try {
            Curso instance = em.find(Curso.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
