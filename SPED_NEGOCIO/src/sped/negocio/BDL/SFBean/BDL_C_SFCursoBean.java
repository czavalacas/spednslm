package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFCursoLocal;
import sped.negocio.BDL.IR.BDL_C_SFCursoRemoto;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.eval.Criterio;

/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 30.12.2013
 */
@Stateless(name = "BDL_C_SFCurso", mappedName = "map-BDL_C_SFCurso")
public class BDL_C_SFCursoBean implements BDL_C_SFCursoRemoto,
                                          BDL_C_SFCursoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFCursoBean() {
    }

    /** <code>select o from Curso o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Curso> getCursoFindAll() {
        return em.createNamedQuery("Curso.findAll", Curso.class).getResultList();
    }
     
    public List<Curso> findCursosPorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct cur FROM Main ma, " +
                              " Curso cur , " +
                              " Profesor prof," +
                              " AreaAcademica ac" +
                              " WHERE ma.curso.nidCurso=cur.nidCurso " +
                              " and cur.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica " +
                              " and prof.dniProfesor=ma.profesor.dniProfesor";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    ejbQl = ejbQl.concat(" and ac.nidAreaAcademica=" + nidAreaAcademica);
                }
            }

            if (dia != null) {
                ejbQl = ejbQl.concat(" and ma.dia='" + dia + "'");
            }

            List<Curso> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    
    public List<Curso> findCursosPorAreaAcademica_ByOrden(String nidAreaAcademica, String nidSede) {
        try {
            String ejbQl =    " SELECT distinct cur from Curso cur, Main ma, Aula au, Sede sed" +
                              " where 1=1 ";
            if (nidAreaAcademica != null) {               
                    ejbQl = ejbQl.concat(" and cur.areaAcademica.nidAreaAcademica="+nidAreaAcademica);
               
            }
            
            if (nidSede != null) {               
                    ejbQl = ejbQl.concat(" and cur.nidCurso=ma.curso.nidCurso and ma.aula.nidAula=au.nidAula" +
                                         " and au.sede.nidSede=sed.nidSede and sed.nidSede="+nidSede);
               
            }            
            
            ejbQl = ejbQl.concat(" ORDER by cur.descripcionCurso");
            
            List<Curso> lstMain = em.createQuery(ejbQl).getResultList();
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
    
    public int getNidCursoByDescripcion(String descripcion){
        String ejbQL = "SELECT c.nidCurso FROM Curso c " + 
                       "WHERE c.descripcionCurso = :descripcion ";
        List<Object> lstObject = em.createQuery(ejbQL).setParameter("descripcion", descripcion).getResultList();
        int nidCurso = 0;
        if(lstObject != null){
            nidCurso = Integer.parseInt(lstObject.get(0).toString());
        }
        return nidCurso;
    }
}