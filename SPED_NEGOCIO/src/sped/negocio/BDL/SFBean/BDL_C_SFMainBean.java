package sped.negocio.BDL.SFBean;

import java.sql.Time;

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
import sped.negocio.Utils.Utiles;
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
    
    /**
     * @author angeles
     * @param id
     * @return
     */
    public Main findMainById(int id){
        try{
            Main instance = em.find(Main.class, id);
            return instance;
        }catch(RuntimeException re){
            throw re;
        }    
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
                           " WHERE ma.estado = :estado " +
                           " AND ma.horaInicio != NULL " +
                           " AND ma.horaFin != NULL AND" ;
           if(beanMain.getDniProfesor() == null){
               ejbQl = ejbQl.concat(" ma.aula.nidAula = :nidAula ");
           }else{
               ejbQl = ejbQl.concat(" ma.profesor.dniProfesor = :dniProfesor ");
               String validacion = beanMain.isSelec() ? " = " : " != ";
               ejbQl = ejbQl.concat(" AND ma.aula.sede.nidSede "+validacion+" :nidSede ");
               ejbQl = ejbQl.concat(" AND ma.aula.gradoNivel.nivel.nidNivel "+validacion+" :nidNivel ");
           }           
           ejbQl = ejbQl.concat(" ORDER BY ma.horaInicio ASC, ma.nDia ASC");
           Query query = em.createQuery(ejbQl);
           query.setParameter("estado", "1");
           if(beanMain.getDniProfesor() == null){
               query.setParameter("nidAula", beanMain.getNidAula());
           }else{
               query.setParameter("dniProfesor", beanMain.getDniProfesor());
               query.setParameter("nidSede", beanMain.getNidSede());
               query.setParameter("nidNivel", beanMain.getNidNivel());
           }
           List<Main> lstMain = query.getResultList();
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
            List<Main> main = em.createQuery(ejbQl).getResultList(); 
            return main.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo que valida si un profesor se encuntra ocupado en un rango de horas
     * @param dniProfesor
     * @param dia
     * @param inicio
     * @param fin
     * @param nidMain
     * @return
     */
    public List<Main> countCruceLecionByProfesor(String dniProfesor, 
                                                 int dia,
                                                 Time inicio, 
                                                 Time fin,
                                                 int nidMain){   
        try{
            String ejbQl =  " SELECT ma FROM Main ma " +                            
                            " WHERE ma.profesor.dniProfesor= :dniProfesor " +
                            " AND ma.estado = :estado " +
                            " AND ma.nDia = :dia " +
                            " AND ( (ma.horaInicio < :inicio AND ma.horaFin > :fin) " +
                            " OR    (ma.horaInicio > :inicio AND ma.horaInicio < :fin) " +
                            " OR    (ma.horaFin > :inicio AND ma.horaFin < :fin) " +
                            " OR    (ma.horaInicio = :inicio AND ma.horaFin = :fin) ) ";
            if(nidMain != 0){
                ejbQl = ejbQl.concat(" AND ma.nidMain <> :nidMain ");
            }
            Query query = em.createQuery(ejbQl).setParameter("dniProfesor", dniProfesor)
                                               .setParameter("estado", "1")
                                               .setParameter("dia", dia)
                                               .setParameter("inicio", inicio)
                                               .setParameter("fin", fin);
            if(nidMain != 0){
                query.setParameter("nidMain", nidMain);
            }
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList();
        }          
    }
       
     public int findMainBySedeYNivel(int nidSede, 
                                     int nidNivel){
        String ejbQL = "select count(M) " +
                                  "from Main M " +
                                  "where M.aula.sede.nidSede = :nidSede " +
                                  "and M.aula.gradoNivel.nivel.nidNivel = :nidNivel";
        Object object = em.createQuery(ejbQL)
                                 .setParameter("nidSede", nidSede)
                                 .setParameter("nidNivel", nidNivel)
                                 .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
     public int countMainByNidsEstado(String nidCurso, String nidAula, String dni){
         String quer = "Select Count(1) " +
                       "  From Main m "+
                       " Where m.estado = 1 " + 
                       " and m.curso.nidCurso = :nidCurso "+
                       " and m.aula.nidAula   = :nidAula "+
                       " and m.profesor.dniProfesor = :dni";
         Object object = em.createQuery(quer)
                                  .setParameter("nidCurso", Integer.parseInt(nidCurso))
                                  .setParameter("nidAula", Integer.parseInt(nidAula)).setParameter("dni", dni)
                                  .getSingleResult();
         int cont = 0;
         if(object != null){
             cont = Integer.parseInt(object.toString());
         }
         return cont;
     }
    /**
     * Metodo para buscar todos las entidades main q se generaron de una leccion
     * @param nidLeccion
     * @return
     */
    public List<Main> getlstMainByNidLeccion(int nidLeccion){
        try {
            String ejbQl =  " SELECT ma FROM Main ma " +                            
                            " WHERE ma.nidLeccion= :nidLeccion";
            return em.createQuery(ejbQl).setParameter("nidLeccion", nidLeccion).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
    
    public List<Object[]> getMainActivos(Integer cidSede,Integer cidAula,
                                         String dniProf ,Integer cidCurso){
        try{
            String q = "Select m.nidMain,s.desc_sede,m.dniProfesor,m.nidAula,m.nidCurso,\n" + 
                       "       Concat(p.apellidos,' ',p.nombres) profesor, \n" + 
                       "       Concat(a.desc_aula,'   /   ',g.abvr,' ',n.abvr),\n" + 
                       "       Concat(c.desc_curso,' / ',ac.desc_area_academica) curso,\n" +
                       "       s.nidSede \n " + 
                       "  From addmain m, \n" + 
                       "       admaula a, \n" + 
                       "       admsede s, \n" + 
                       "       admprof p, \n" + 
                       "       admcurs c, \n" + 
                       "       admarac ac \n" + 
                       "       admgrad g,\n" + 
                       "       admnive n \n "+
                       "Where m.estado = 1 \n" + 
                       "  And m.nidAula     = a.nidAula\n" + 
                       "  And m.dniProfesor = p.dniProfesor\n" + 
                       "  And m.nidCurso    = c.nidCurso\n" + 
                       "  And a.nidSede     = a.nidSede \n "+
                       "  And a.nidGrado    = g.nidGrado \n " + 
                       "  And a.nidNivel    = n.nidNivel \n "+
                       "  And m.nidAula     = IfNull(?,m.nidAula)\n" + 
                       "  And m.dniProfesor = IfNull(?,m.dniProfesor)\n" + 
                       "  And m.nidCurso    = Ifnull(?,m.nidCurso)\n" + 
                       "  And a.nidSede     = IfNull(?,a.nidSede)\n" + 
                       "  And c.nidAreaAcademica = ac.nidAreaAcademica\n" + 
                       "  And a.nidSede          = s.nidSede\n" + 
                       "  And m.nidMain Not In (Select e.nidMain From evmeval e)\n" + 
                       "  Order By s.nidSede";
            return em.createNativeQuery(q).setParameter(1,cidAula)
                                          .setParameter(2,dniProf)
                                          .setParameter(3,cidCurso)
                                          .setParameter(4,cidSede).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<Object[]>();
        }}
    /*****Count Main en estado 0 By niAula**************************/
    
    public int countMainInactivosByAula(String nidAula){
        String quer = "Select Count(1) " +
                      " From Main m "+
                      " Where m.aula.nidAula = :nidAula " +
                      " and m.estado='1'";
        Object object = em.createQuery(quer)
                                 .setParameter("nidAula", Integer.parseInt(nidAula))
                                 .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
    /****Count Main por Aulas en EVMEval***/
    public int countMainByAulaForEval(String nidAula){
        String quer = "Select Count(1) " +
                      "  From Evaluacion e, Main m, Aula a "+
                      " Where e.main.aula.nidAula = :nidAula " + 
                      " and e.main.nidMain = m.nidMain "+
                      " and m.aula.nidAula   = a.nidAula";
        Object object = em.createQuery(quer)
                                 .setParameter("nidAula", Integer.parseInt(nidAula))
                                 .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
}