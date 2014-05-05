package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import sped.negocio.BDL.IL.BDL_C_SFMainLocal;
import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanMain;

import sped.negocio.entidades.beans.BeanMainWS;
import sped.negocio.entidades.eval.Evaluacion;

/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 29.12.2013
 */
@Stateless(name = "BDL_C_SFMain", mappedName = "map-BDL_C_SFMain")
public class BDL_C_SFMainBean implements BDL_C_SFMainRemote, 
                                            BDL_C_SFMainLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFMainBean() {
    }

    public List<Main> findHorariosByAttributes(BeanMain beanMain) {
        try {
            String ejbQl =  "SELECT ma FROM Main ma, " +
                            "Curso cur , " +
                            "Profesor prof, " +
                            "Aula au, " +
                            "Sede sed, " +
                            "Nivel niv, " +
                            "Grado gra" +
                            " WHERE ma.estado=1" + 
                            " and ma.curso.nidCurso=cur.nidCurso " + 
                            " and au.nidAula=ma.aula.nidAula " +
                            " and au.sede.nidSede=sed.nidSede" +
                            " and au.gradoNivel.nivel.nidNivel=niv.nidNivel" +
                            " and au.gradoNivel.grado.nidGrado=gra.nidGrado" + 
                            " and prof.dniProfesor=ma.profesor.dniProfesor";

            if (beanMain.getCurso().getAreaAcademica()!= null) {
                if (beanMain.getCurso().getAreaAcademica().getNidAreaAcademica() != 0) {
                    ejbQl =
                        ejbQl.concat(" and ma.curso.areaAcademica.nidAreaAcademica= " +
                                     beanMain.getCurso().getAreaAcademica().getNidAreaAcademica());                  
                }
            }
            if (beanMain.getDia() != null) {
                ejbQl = ejbQl.concat(" and ma.dia= '" + beanMain.getDia() + "'");
            }
            if (beanMain.getProfesor() != null) {
                ejbQl = ejbQl.concat(" and ma.profesor.dniProfesor= '" + beanMain.getProfesor().getDniProfesor() + "'");                
            }
            if (beanMain.getCurso().getNidCurso() != null) {
                if (beanMain.getCurso().getNidCurso() != 0) {
                    ejbQl = ejbQl.concat(" and ma.curso.nidCurso=" + beanMain.getCurso().getNidCurso());
                }
            }
            if (beanMain.getAula().getSede() != null) {
                if (beanMain.getAula().getSede().getNidSede() != 0) {
                    ejbQl = ejbQl.concat(" and ma.aula.sede.nidSede=" + beanMain.getAula().getSede().getNidSede());                    
                }
            }
            if (beanMain.getAula().getGradoNivel().getGrado() != null) {
                if (beanMain.getAula().getGradoNivel().getGrado().getNidGrado() != 0) {
                    ejbQl = ejbQl.concat(" and ma.aula.gradoNivel.grado.nidGrado=" + beanMain.getAula().getGradoNivel().getGrado().getNidGrado());               
                }
            }
            if (beanMain.getAula().getGradoNivel().getNivel() != null) {
                if (beanMain.getAula().getGradoNivel().getNivel().getNidNivel() != 0) {
                    ejbQl = ejbQl.concat(" and ma.aula.gradoNivel.nivel.nidNivel=" + beanMain.getAula().getGradoNivel().getNivel().getNidNivel());                    
                }
            }

            List<Main> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Profesor> findProfesoresPorAreaAcademica(Integer nidAreaAcademica, String dia) {
        try {
            String ejbQl =    "SELECT distinct prof FROM Main ma, " +
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

            List<Profesor> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /** <code>select o from Main o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Main> getMainFindAll() {
        return em.createNamedQuery("Main.findAll", Main.class).getResultList();
    }    
   
   /**
     * Metodo que no utiliza mapper sino mapea defrente al bean desde el mismo query, este metodo busca en la entidad 
     * Main, se usa para el WS, movil, para poder buscar profesores y registrarles un PARTE DE OCURRENCIA
     * @author dfloresgonz
     * @since 24.02.2014
     * @param nidSede
     * @param profesor
     * @param curso
     * @param aula
     * @return List<BeanMainWS>
     */
    public List<BeanMainWS> getMainByAttr_BDL_WS(Integer nidSede,
                                                  String profesor,
                                                  String curso,
                                                  String aula){
        try {
           String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanMainWS(m.nidMain," +
                                                                                 "m.profesor.apellidos," +
                                                                                 "m.profesor.nombres," +
                                                                                 "m.curso.descripcionCurso," +
                                                                                 "m.aula.sede.descripcionSede," +
                                                                                 "m.aula.descripcionAula," +
                                                                                 "m.horaFin," +
                                                                                 "m.horaInicio," +
                                                                                 "m.dia," +
                                                                                 "m.aula.gradoNivel.grado.descripcionGrado," +
                                                                                 "m.aula.gradoNivel.nivel.descripcionNivel," +
                                                                                 "m.curso.areaAcademica.descripcionAreaAcademica," +
                                                                                 "m.aula.sede.nidSede) \n" +
                              "FROM Main m " +
                              "WHERE 1 = 1 ";
           if(nidSede != null){
               if(nidSede != 0){
                   qlString = qlString.concat(" AND m.aula.sede.nidSede = :nidSede ");
               }
           }
           if(profesor != null){
               qlString = qlString.concat(" AND UPPER(CONCAT(m.profesor.apellidos,' ',m.profesor.nombres)) like UPPER(:profesor) ");
           }
           if(curso != null){
               qlString = qlString.concat(" AND UPPER(m.curso.descripcionCurso) like UPPER(:curso) ");
           }
           if(aula != null){
               qlString = qlString.concat(" AND UPPER(m.aula.descripcionAula) like UPPER(:aula) ");
           }
           qlString = qlString.concat(" ORDER BY m.profesor.apellidos ASC ");
           Query query = em.createQuery(qlString);
           if(nidSede != null){
               if(nidSede != 0){
                   query.setParameter("nidSede", nidSede);
               }
           }
           if(profesor != null){
               query.setParameter("profesor","%"+profesor+"%");
           }
           if(curso != null){
               query.setParameter("curso","%"+curso+"%");
           }
           if(aula != null){
               query.setParameter("aula","%"+aula+"%");
           }
           List<BeanMainWS> lstBeanMainWS = query.getResultList();
           int size = lstBeanMainWS == null ? 0 : lstBeanMainWS.size();
           if (size > 0) {
               return lstBeanMainWS;
           } else {
               return new ArrayList<BeanMainWS>();
           }
       } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
   /**
     * Metodo para consultar las leciones del aula
     * @author angeles
     * @param beanMain
     * @return LstMain
     */
   public List<Main> getLstMainByAttr_BDL(BeanMain beanMain){
       try{
           String ejbQl =  " SELECT ma FROM Main ma " +
                           " WHERE ma.aula.nidAula = :nidAula " +
                           " AND ma.estado = :estado";
           List<Main> lstMain = em.createQuery(ejbQl).setParameter("nidAula", beanMain.getNidAula())
                                                     .setParameter("estado", "1")
                                                     .getResultList();
           return lstMain;
       }catch(Exception e){
           e.printStackTrace();
           return new ArrayList();
}
    }
    public List<Main> getHorariosPorDocente(String dniDocente) {
           try {
               String ejbQl =  " SELECT ma FROM Main ma " +                            
                               " WHERE ma.profesor.dniProfesor='"+dniDocente+"'";
               List<Main> lstMain = em.createQuery(ejbQl).getResultList();
               return lstMain;
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
       }
    
       public Main getMainByAtrubutes(String nidAula, String nidCurso, String dniProfesor) {
           try {
               String ejbQl =  " SELECT ma FROM Main ma " +                            
                               " WHERE ma.profesor.dniProfesor='"+dniProfesor+"'" +
                               " and ma.aula.nidAula="+nidAula+"" +
                               " and ma.curso.nidCurso="+nidCurso;
               Main main = (Main)em.createQuery(ejbQl).getSingleResult(); 
               return main;
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
       }

}
