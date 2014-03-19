package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFSedeLocal;
import sped.negocio.BDL.IR.BDL_C_SFSedeRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Sede;

@Stateless(name = "BDL_C_SFSede", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFSede")
public class BDL_C_SFSedeBean implements BDL_C_SFSedeRemote, 
                                         BDL_C_SFSedeLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFSedeBean() {
    }

    /** <code>select o from Sede o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Sede> getSedeFindAll() {
        return em.createNamedQuery("Sede.findAll", Sede.class).getResultList();
    }
    
    public List<Sede> findSedePorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct sed FROM Main ma, " +
                              " Curso cur , " +
                              " Profesor prof," +
                              " Sede sed," +
                              " AreaAcademica ac" +
                              " WHERE ma.curso.nidCurso=cur.nidCurso " +
                              " and cur.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica " +
                              " and prof.dniProfesor=ma.profesor.dniProfesor "+
                              " and ma.aula.sede.nidSede=sed.nidSede";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    ejbQl = ejbQl.concat(" and ac.nidAreaAcademica=" + nidAreaAcademica);
                }
            }
            if (dia != null) {
                ejbQl = ejbQl.concat(" and ma.dia='" + dia + "'");
            }
            List<Sede> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    
    public Sede findSedeById(int id) {
         try {
             Sede instance = em.find(Sede.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
}
