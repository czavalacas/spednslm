package sped.negocio.BDL.SFBean;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFNivelLocal;
import sped.negocio.BDL.IR.BDL_C_SFNivelRemote;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Grado;
import sped.negocio.entidades.admin.Nivel;

@Stateless(name = "BDL_C_SFNivel", mappedName = "SPED_APP-SPED_NEGOCIO-BDL_C_SFNivel")
public class BDL_C_SFNivelBean implements BDL_C_SFNivelRemote, 
                                          BDL_C_SFNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFNivelBean() {
    }

    /** <code>select o from Nivel o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Nivel> getNivelFindAll() {
        return em.createNamedQuery("Nivel.findAll", Nivel.class).getResultList();
    }
    
    public List<Nivel> findGradpPorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct niv FROM Main ma, " +
                              " Curso cur , " +
                              " Profesor prof," +
                              " Niel niv," +
                              " AreaAcademica ac" +
                              " WHERE ma.curso.nidCurso=cur.nidCurso " +
                              " and cur.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica " +
                              " and prof.dniProfesor=ma.profesor.dniProfesor "+
                              " and ma.aula.gradoNivel.nivel.nidNivel=niv.nidNivel";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0) {
                    ejbQl = ejbQl.concat(" and ac.nidAreaAcademica=" + nidAreaAcademica);
                }
            }

            if (dia != null) {
                ejbQl = ejbQl.concat(" and ma.dia='" + dia + "'");
            }

            List<Nivel> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    
    public Nivel findNivelById(int id) {
         try {
             Nivel instance = em.find(Nivel.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
    
    public List<Nivel> findNivelesPorSede_ByOrden(String nidSede, String nidArea, String nidCurso) {
        try {
            String  ejbQl =   " SELECT distinct niv from Nivel niv," +
                              " Sede sed, SedeNivel seni " +
             /**nuevo */      " ,Main ma, Aula au, Curso cu, AreaAcademica aca "+
                              " where  niv.nidNivel=seni.nivel.nidNivel and seni.sede.nidSede=sed.nidSede " +
                              " and au.sede.nidSede=sed.nidSede and ma.aula.nidAula=au.nidAula" +
                              " and ma.curso.nidCurso=cu.nidCurso and cu.areaAcademica.nidAreaAcademica=aca.nidAreaAcademica ";
            
            if (nidSede != null) {               
                
                    ejbQl = ejbQl.concat(" and sed.nidSede="+nidSede);               
            }
            
            if (nidArea != null) {               
                
                    ejbQl = ejbQl.concat(" and aca.nidAreaAcademica="+nidArea);               
            }
            
            if (nidCurso != null) {               
                
                    ejbQl = ejbQl.concat(" and cu.nidCurso="+nidCurso);               
            }
                
                    ejbQl = ejbQl.concat(" ORDER by niv.descripcionNivel");
            
            List<Nivel> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
            }
}
