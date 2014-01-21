package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanMain;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;
/** Clase SFBDL SFMainBean.java
 * @author czavalacas
 * @since 31.12.2013
 */
@Stateless(name = "BDL_C_SFEvaluacion", mappedName = "map-BDL_C_SFEvaluacion")
public class BDL_C_SFEvaluacionBean implements BDL_C_SFEvaluacionRemoto, 
                                               BDL_C_SFEvaluacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFEvaluacionBean() {
    }

    /** <code>select o from Evaluacion o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Evaluacion> getEvaluacionFindAll() {
        return em.createNamedQuery("Evaluacion.findAll", Evaluacion.class).getResultList();
    }
    
    public Evaluacion getEvaluacionById(String nidDate) {
        try{
            String ejbQl = "SELECT ma FROM Evaluacion ma" +
                           " WHERE ma.nidDate='"+nidDate+"'";   
                Evaluacion eva = (Evaluacion)em.createQuery(ejbQl).getSingleResult();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Evaluacion> getEvaluaciones(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador, String dniProfesor, String nidCurso) {
        try{   
             String ejbQl = "SELECT ev FROM Evaluacion ev, Main ma, Curso cu, AreaAcademica ac" +
                                   " WHERE ev.startDate like '%"+fechaHoy+"%'" +
                                   " AND ma.nidMain=ev.main.nidMain" +
                                   " AND ma.curso.nidCurso=cu.nidCurso" +
                                   " AND cu.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica"+
                                   " AND ac.nidAreaAcademica="+nidAreaAcademica+
                                   " AND ev.nidEvaluador="+nidEvaluador ;
            
            if (dniProfesor!= null) {                
                    ejbQl =
                        ejbQl.concat(" and ma.profesor.dniProfesor='" +
                                     dniProfesor+"'");
                    System.out.println("REPO DNI PROFE : "+dniProfesor);
                
            }
            if (nidCurso!= null) {              
                    ejbQl =
                        ejbQl.concat(" and ma.curso.nidCurso=" +
                                     nidCurso);
                    System.out.println("REPO nidCurso: "+nidCurso);                
                
            }
            
            
                List<Evaluacion> eva = em.createQuery(ejbQl).getResultList();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Evaluacion> getEvaluacionesByUsuarioBDL(BeanUsuario beanUsuario){
            try{                 
                if(beanUsuario!=null){
                    String strQuery = "SELECT eva " +
                                   "FROM Evaluacion eva " +
                                   "WHERE 1=1"; 
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("SUBDIRECTOR") == 0){
                        strQuery = strQuery.concat(" AND eva.main.aula.sede.nidSede = :nid_sede " +
                                                   " AND eva.main.aula.gradoNivel.nivel.nidNivel = :nid_nivel ");
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("EVALUADOR") == 0){
                        strQuery = strQuery.concat(" AND eva.nidEvaluador = :nid_evaluador ");
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("PROFESOR") == 0){
                        strQuery = strQuery.concat(" AND eva.main.profesor.dniProfesor = :dni_profesor ");
                    }
                    
                    Query query = em.createQuery(strQuery);
                    
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("SUBDIRECTOR") == 0){
                        query.setParameter("nid_sede", beanUsuario.getSedeNivel().getSede().getNidSede());
                        query.setParameter("nid_nivel", beanUsuario.getSedeNivel().getNivel().getNidNivel());
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("EVALUADOR") == 0){
                        query.setParameter("nid_evaluador", beanUsuario.getNidUsuario());
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("PROFESOR") == 0){
                        query.setParameter("dni_profesor", beanUsuario.getDni());
                    }
                    return query.getResultList();
                }else{
                    return new ArrayList();
                }
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            }   
        }
}
