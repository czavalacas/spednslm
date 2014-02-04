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

import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanEvaluacion;
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
    
    public List<Evaluacion> getPlanificacion(BeanEvaluacion beanEvaluacion){
        try{
            String ejbQl = "SELECT ev FROM Evaluacion ev WHERE 1=1";
                           
            if(beanEvaluacion.getFechaMaxPlanificacion() != null && beanEvaluacion.getFechaMinPlanificacion() != null){
                ejbQl = ejbQl.concat(" AND ev.fechaPlanificacion BETWEEN :min AND :max ");
            }
            
            if(beanEvaluacion.getNidEvaluador() != null){
                               if(beanEvaluacion.getNidEvaluador() != 0){
                               ejbQl = ejbQl.concat(" AND ev.nidEvaluador ="+beanEvaluacion.getNidEvaluador());
                 }
            }
                if(beanEvaluacion.getNidNivel() != null){
                    if(beanEvaluacion.getNidNivel() != 0){
                    ejbQl = ejbQl.concat(" AND ev.main.aula.gradoNivel.nivel.nidNivel ="+beanEvaluacion.getNidNivel());
                    }
                }
              
                if(beanEvaluacion.getNidSede() != null){
                    if(beanEvaluacion.getNidSede() != 0){
                        ejbQl = ejbQl.concat(" AND ev.main.aula.sede.nidSede="+beanEvaluacion.getNidSede());
                    }
                }
          
                if(beanEvaluacion.getApellidosDocentes() != null){
                        ejbQl = ejbQl.concat(" AND upper(ev.main.profesor.apellidos) like '%"+beanEvaluacion.getApellidosDocentes().toUpperCase()+"%' ");
                }   
            if(beanEvaluacion.getFechaMaxPlanificacion() != null && beanEvaluacion.getFechaMinPlanificacion() != null){
                List<Evaluacion> lstEvaluaciones = em.createQuery(ejbQl)
                                             .setParameter("min", beanEvaluacion.getFechaMinPlanificacion(), TemporalType.DATE)
                                             .setParameter("max", beanEvaluacion.getFechaMaxPlanificacion(), TemporalType.DATE).getResultList();
                return lstEvaluaciones;
            }else{}             
            List<Evaluacion> lstaEvaluaciones = em.createQuery(ejbQl).getResultList();
                return lstaEvaluaciones;            
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
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
    
    public List<Evaluacion> getEvaluaciones(String fechaHoy, Integer nidAreaAcademica, Integer nidEvaluador, String dniProfesor, String nidCurso, Integer nidSede) {
        try{   
             String ejbQl = "SELECT ev FROM Evaluacion ev, Main ma, Curso cu, AreaAcademica ac" +
                                   " WHERE ev.startDate like '%"+fechaHoy+"%'" +
                                   " AND ma.nidMain=ev.main.nidMain" +
                                   " AND ma.curso.nidCurso=cu.nidCurso" +
                                   " AND cu.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica"+                                  
                                   " AND ev.nidEvaluador="+nidEvaluador ;
            
            
            if (nidAreaAcademica!= null) {    
                if(nidAreaAcademica!=0){
                    ejbQl =
                        ejbQl.concat(" AND ac.nidAreaAcademica="+nidAreaAcademica);
                    System.out.println("REPO nidAreaAcademica : "+nidAreaAcademica);
                }
            }
            if (nidSede!= null) {     
                if(nidSede!=0){
                    ejbQl =
                        ejbQl.concat(" AND ma.aula.sede.nidSede="+nidSede);
                    System.out.println("REPO nidSede : "+nidSede);
                }
            }
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
    
    public List<Evaluacion> getEvaluacionesByUsuarioBDL(BeanUsuario beanUsuario,
                                                        BeanEvaluacion beanFiltroEva){
            List<Evaluacion> listEvaluacion = new ArrayList();
            try{                 
                if(beanUsuario != null){
                    String strQuery = "SELECT eva " +
                                      "FROM Evaluacion eva, Usuario usu " +
                                      "WHERE eva.nidEvaluador=usu.nidUsuario ";                    
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("SUBDIRECTOR") == 0){
                        strQuery = strQuery.concat(" AND eva.main.aula.sede.nidSede = :nid_sede " +
                                                   " AND eva.main.aula.gradoNivel.nivel.nidNivel = :nid_nivel ");
                        beanFiltroEva.setNidSede(0);
                        beanFiltroEva.setNidNivel(0);
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("EVALUADOR") == 0){
                        strQuery = strQuery.concat(" AND eva.nidEvaluador = :nid_evaluador ");
                        strQuery = strQuery.concat(" AND eva.main.curso.areaAcademica.nidAreaAcademica = :nid_area ");
                        beanFiltroEva.setNidArea(0);
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("PROFESOR") == 0){
                        strQuery = strQuery.concat(" AND eva.main.profesor.dniProfesor = :dni_profesor ");
                        //strQuery = strQuery.concat(" AND upper(eva.estadoEvaluacion) = 'REALIZADA' ");
                    }                    
                    if(beanFiltroEva != null){
                        if(beanFiltroEva.getNidSede() != 0){
                            strQuery = strQuery.concat(" AND eva.main.aula.sede.nidSede = :nidf_sede ");                            
                        }
                        if(beanFiltroEva.getNidNivel() != 0){
                            strQuery = strQuery.concat(" AND eva.main.aula.gradoNivel.nivel.nidNivel = :nidf_nivel ");
                        }
                        if(beanFiltroEva.getNidArea() != 0){
                            strQuery = strQuery.concat(" AND eva.main.curso.areaAcademica.nidAreaAcademica = :nidf_area ");
                        }
                        if(beanFiltroEva.getNidCurso() != 0){
                            strQuery = strQuery.concat(" AND eva.main.curso.nidCurso = :nidf_curso ");
                        }
                        if(beanFiltroEva.getNidGrado() != 0){
                            strQuery = strQuery.concat(" AND eva.main.aula.gradoNivel.grado.nidGrado = :nidf_grado ");
                        }
                        if(beanFiltroEva.getEstadoEvaluacion() != null){
                            strQuery = strQuery.concat(" AND upper(eva.estadoEvaluacion) = :eva_estado ");
                        }
                        if(beanFiltroEva.getApellidosDocentes() != null){
                            strQuery = strQuery.concat(" AND upper(CONCAT(eva.main.profesor.nombres ,' ' ," +
                                                       " eva.main.profesor.apellidos)) like :eva_profesor ");
                        }
                        if(beanFiltroEva.getNombreEvaluador() != null){
                            strQuery = strQuery.concat(" AND usu.nombres like :eva_nomEva ");
                        }
                        if(beanFiltroEva.getFechaMinEvaluacion() != null && beanFiltroEva.getFechaMaxEvaluacion() != null){
                            strQuery = strQuery.concat(" AND ( CAST(eva.endDate AS date) BETWEEN :eva_dateEva1 AND :eva_dateEva2 ) ");
                        }else{
                            if(beanFiltroEva.getFechaMinEvaluacion() != null || beanFiltroEva.getFechaMaxEvaluacion() != null){
                                strQuery = strQuery.concat(" AND CAST(eva.endDate AS date) = :eva_dateEva1 ");
                            }
                        }
                        if(beanFiltroEva.getFechaMinPlanificacion() != null && beanFiltroEva.getFechaMaxPlanificacion() != null){
                            strQuery = strQuery.concat(" AND ( CAST(eva.fechaPlanificacion AS date) BETWEEN :eva_datePla1 AND :eva_datePla2 ) ");
                        }else{
                            if(beanFiltroEva.getFechaMinPlanificacion() != null || beanFiltroEva.getFechaMaxPlanificacion() != null){
                                strQuery = strQuery.concat(" AND CAST(eva.fechaPlanificacion AS date) = :eva_datePla1 ");
                            }
                        }
                    }
                    strQuery = strQuery.concat(" ORDER BY eva.estadoEvaluacion DESC ");
                    Query query = em.createQuery(strQuery);      
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("SUBDIRECTOR") == 0){
                        query.setParameter("nid_sede", beanUsuario.getSedeNivel().getSede().getNidSede());
                        query.setParameter("nid_nivel", beanUsuario.getSedeNivel().getNivel().getNidNivel());
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("EVALUADOR") == 0){
                        query.setParameter("nid_evaluador", beanUsuario.getNidUsuario());
                        query.setParameter("nid_area", beanUsuario.getAreaAcademica().getNidAreaAcademica());
                    }
                    if(beanUsuario.getRol().getDescripcionRol().toUpperCase().compareTo("PROFESOR") == 0){
                        query.setParameter("dni_profesor", beanUsuario.getDni());
                    }
                    if(beanFiltroEva != null){
                        if(beanFiltroEva.getNidSede() != 0){ 
                            query.setParameter("nidf_sede", beanFiltroEva.getNidSede());
                        }
                        if(beanFiltroEva.getNidNivel() != 0){
                            query.setParameter("nidf_nivel", beanFiltroEva.getNidNivel());
                        }
                        if(beanFiltroEva.getNidArea() != 0){
                            query.setParameter("nidf_area", beanFiltroEva.getNidArea());
                        }
                        if(beanFiltroEva.getNidCurso() != 0){
                            query.setParameter("nidf_curso", beanFiltroEva.getNidCurso());
                        }
                        if(beanFiltroEva.getNidGrado() != 0){
                            query.setParameter("nidf_grado", beanFiltroEva.getNidGrado());
                        }
                        if(beanFiltroEva.getEstadoEvaluacion() != null){
                            query.setParameter("eva_estado", beanFiltroEva.getEstadoEvaluacion().toUpperCase());
                        }
                        if(beanFiltroEva.getApellidosDocentes() != null){
                            query.setParameter("eva_profesor", "%"+beanFiltroEva.getApellidosDocentes().toUpperCase()+"%");
                        }
                        if(beanFiltroEva.getNombreEvaluador() != null){
                            query.setParameter("eva_nomEva", "%"+beanFiltroEva.getNombreEvaluador()+"%");
                        }
                        if(beanFiltroEva.getStartDate() != null){
                            query.setParameter("end_date",beanFiltroEva.getStartDate());
                        }
                        if(beanFiltroEva.getFechaMinEvaluacion() != null && beanFiltroEva.getFechaMaxEvaluacion()!=null){
                            query.setParameter("eva_dateEva1",beanFiltroEva.getFechaMinEvaluacion());
                            query.setParameter("eva_dateEva2",beanFiltroEva.getFechaMaxEvaluacion());
                        }else{
                            if(beanFiltroEva.getFechaMinEvaluacion() != null){
                                query.setParameter("eva_dateEva1",beanFiltroEva.getFechaMinEvaluacion());
                            }
                            if(beanFiltroEva.getFechaMaxEvaluacion() != null){
                                query.setParameter("eva_dateEva1",beanFiltroEva.getFechaMaxEvaluacion());
                            }
                        }
                        if(beanFiltroEva.getFechaMinPlanificacion() != null && beanFiltroEva.getFechaMaxPlanificacion() != null){
                            query.setParameter("eva_datePla1",beanFiltroEva.getFechaMinPlanificacion());
                            query.setParameter("eva_datePla2",beanFiltroEva.getFechaMaxPlanificacion());
                        }else{
                            if(beanFiltroEva.getFechaMinPlanificacion() != null){
                                query.setParameter("eva_datePla1",beanFiltroEva.getFechaMinPlanificacion());
                            }
                            if(beanFiltroEva.getFechaMaxPlanificacion() != null){
                                query.setParameter("eva_datePla1",beanFiltroEva.getFechaMaxPlanificacion());
                            }
                        }
                    }
                    listEvaluacion = query.getResultList();
                }
                return listEvaluacion;
            }catch(Exception e){
                e.printStackTrace();  
                return null;
            } 
        }
    public List<Constraint> getTipoVisita() {
        try{
            String ejbQl = "SELECT ma FROM Constraint ma" +
                           " WHERE ma.nombreCampo='tipo_visita'";   
                List<Constraint> cons = em.createQuery(ejbQl).getResultList();           
                return cons;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    public Constraint getTipoVisitaByValor(String valor) {
        try{
            String ejbQl = "SELECT ma FROM Constraint ma" +
                           " WHERE ma.valorCampo='"+valor+"'";   
                Constraint cons = (Constraint)em.createQuery(ejbQl).getSingleResult();           
                return cons;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
}
