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
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Evaluacion;

import utils.system;

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
    
    public Evaluacion findEvaluacionById(int id) {
        try {
            Evaluacion instance = em.find(Evaluacion.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
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
            if(beanEvaluacion.getNidArea()!=null){
                if(beanEvaluacion.getNidArea()!=0){
                ejbQl = ejbQl.concat(" AND ev.main.curso.areaAcademica.nidAreaAcademica="+beanEvaluacion.getNidArea()); 
            }}
            
            if(beanEvaluacion.getNidEstadoEvaluacion()!=null){                
                System.out.println("ENTRO ...::::::::::::::::::::::::::::::");
                ejbQl = ejbQl.concat(" AND ev.estadoEvaluacion='"+beanEvaluacion.getNidEstadoEvaluacion()+"'"); 
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
                    int nidRol = beanUsuario.getRol().getNidRol();
                    if(nidRol == 2 || nidRol == 4 || nidRol == 5){
                        strQuery = strQuery.concat(" AND eva.nidEvaluador = :nid_evaluador ");
                    }
                    if(nidRol == 4){
                        strQuery = strQuery.concat(" AND eva.main.aula.sede.nidSede = :nid_sede " +
                                                   " AND eva.main.aula.gradoNivel.nivel.nidNivel = :nid_nivel ");
                        beanFiltroEva.setNidSede(0);
                        beanFiltroEva.setNidNivel(0);
                    }
                    if(nidRol == 2){
                        strQuery = strQuery.concat(" AND eva.main.curso.areaAcademica.nidAreaAcademica = :nid_area ");
                        beanFiltroEva.setNidArea(0);
                    }
                    if(nidRol == 3){
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
                    strQuery = strQuery.concat(" ORDER BY eva.startDate DESC ");
                    Query query = em.createQuery(strQuery);
                    if(nidRol == 2 || nidRol == 4 || nidRol == 5){
                        query.setParameter("nid_evaluador", beanUsuario.getNidUsuario());
                    }
                    if(nidRol == 4){
                        query.setParameter("nid_sede", beanUsuario.getSedeNivel().getSede().getNidSede());
                        query.setParameter("nid_nivel", beanUsuario.getSedeNivel().getNivel().getNidNivel());
                    }
                    if(nidRol == 2){                        
                        query.setParameter("nid_area", beanUsuario.getAreaAcademica().getNidAreaAcademica());
                    }
                    if(nidRol == 3){
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
    
    /**
     * Metodo utilizado para mostrar las planificaciones al aplicativo movil, dependiendo del rol y usuario
     * @param nidRol
     * @param nidSede
     * @param nidAreaAcademica
     * @param nidUsuario
     * @author dfloresgonz
     * @since 04.02.2014
     * @return
     */
    public List<Evaluacion> getPlanificaciones_BDL_WS(int nidRol,
                                                       int nidSede,
                                                       int nidAreaAcademica,
                                                       int nidUsuario,
                                                       String nombreProfesor,
                                                       String curso,
                                                       int nidSedeFiltro,
                                                       int nidAAFiltro){
        try{
            String qlString = "SELECT e " +
                              "FROM Evaluacion e " +
                              "WHERE e.estadoEvaluacion = 'PENDIENTE' ";
            if(nidRol == 4){//Evaluador x Sede
                qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSede ");
            }else if(nidRol == 2){//Evaluador x Area
                qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAreaAcademica AND e.nidEvaluador = :nidEvaluador ");
            }else if(nidRol == 1 || nidRol == 5){//Director || Evaluador General
                //TODOS LOS PERMISOS
            }
            if(nidSedeFiltro != 0){
                if(nidRol == 1 || nidRol == 2 || nidRol == 5){
                    qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSedeFiltro ");
                }
            }
            if(nidAAFiltro != 0){
                if(nidRol == 1 || nidRol == 4 || nidRol == 5){
                    qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAAFiltro ");
                }
            }
            if(nombreProfesor != null){
                qlString = qlString.concat(" AND upper(CONCAT(e.main.profesor.nombres ,' ' ," +
                                           " e.main.profesor.apellidos)) like upper(:nombreProfesor) ");
            }
            if(curso != null){
                qlString = qlString.concat(" AND upper(e.main.curso.descripcionCurso) like upper(:curso) ");
            }//AND CAST(e.startDate AS date) = CURRENT_DATE 
            qlString = qlString.concat(" ORDER BY e.startDate DESC ");Utiles.sysout("query:"+qlString);
            Query query = em.createQuery(qlString);
            if(nidRol == 4){//Evaluador x Sede
                query.setParameter("nidSede",nidSede);
            }else if(nidRol == 2){//Evaluador x Area
                query.setParameter("nidAreaAcademica",nidAreaAcademica).setParameter("nidEvaluador",nidUsuario);
            }
            if(nidSedeFiltro != 0){
                if(nidRol == 1 || nidRol == 2 || nidRol == 5){
                    query.setParameter("nidSedeFiltro",nidSedeFiltro);
                }
            }
            if(nidAAFiltro != 0){
                if(nidRol == 1 || nidRol == 4 || nidRol == 5){
                    query.setParameter("nidAAFiltro",nidAAFiltro);
                }
            }
            if(nombreProfesor != null){
                query.setParameter("nombreProfesor","%"+nombreProfesor+"%");
            }
            if(curso != null){
                query.setParameter("curso","%"+curso+"%");
            }
            List<Evaluacion> lstEvas = query.getResultList();
            int size = lstEvas == null ? 0 : lstEvas.size();
            if (size > 0) {
                return lstEvas;
            } else {
                return new ArrayList<Evaluacion>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List getDesempenoEvaluacionbyFiltroBDL(List lstnidRol,
                                                  List lstnidEvaluador,
                                                  List lstnidSede,
                                                  List lstnidArea,
                                                  BeanEvaluacion beanFEva){
        try{
            String strQuery = " FROM Evaluacion eva, Usuario usu " +
                              " WHERE eva.nidEvaluador=usu.nidUsuario ";                      
            if(lstnidRol != null){
                strQuery = strQuery.concat(" AND ( ");
                for(int i=0 ; i < lstnidRol.size(); i++){
                    if(i != 0){
                        strQuery = strQuery.concat(" OR ");
                    }
                    strQuery = strQuery.concat(" usu.rol.nidRol = :nid_Rol"+i+" ");                    
                }
                strQuery = strQuery.concat(" ) ");
            }
            if(lstnidEvaluador != null){
                strQuery = strQuery.concat(" AND ( ");
                for(int i=0 ; i < lstnidEvaluador.size(); i++){
                    if(i != 0){
                        strQuery = strQuery.concat(" OR ");
                    }
                    strQuery = strQuery.concat(" eva.nidEvaluador = :nid_evaluador"+i+" ");                 
                }
                strQuery = strQuery.concat(" ) ");
            }           
            if(lstnidSede != null){
                strQuery = strQuery.concat(" AND ( ");
                for(int i=0 ; i < lstnidSede.size(); i++){
                    if(i != 0){
                        strQuery = strQuery.concat(" OR ");
                    }
                    strQuery = strQuery.concat(" eva.main.aula.sede.nidSede = :nid_Sede"+i+" ");                    
                }
                strQuery = strQuery.concat(" ) ");
            }            
            if(lstnidArea != null){
                strQuery = strQuery.concat(" AND ( ");
                for(int i=0 ; i < lstnidArea.size(); i++){
                    if(i != 0){
                        strQuery = strQuery.concat(" OR ");
                    }
                    strQuery = strQuery.concat(" eva.main.curso.areaAcademica.nidAreaAcademica = :nid_Area"+i+" ");                    
                }
                strQuery = strQuery.concat(" ) ");
            }
            if(beanFEva != null){
                if(beanFEva.getFechaMinEvaluacion() != null && beanFEva.getFechaMaxEvaluacion() != null){
                    strQuery = strQuery.concat(" AND ( CAST(eva.endDate AS date) BETWEEN :eva_dateEva1 AND :eva_dateEva2 ) ");
                }else{
                    if(beanFEva.getFechaMinEvaluacion() != null || beanFEva.getFechaMaxEvaluacion() != null){
                        strQuery = strQuery.concat(" AND CAST(eva.endDate AS date) = :eva_dateEva1 ");
                    }
                }
                if(beanFEva.getFechaMinPlanificacion() != null && beanFEva.getFechaMaxPlanificacion() != null){
                    strQuery = strQuery.concat(" AND ( CAST(eva.fechaPlanificacion AS date) BETWEEN :eva_datePla1 AND :eva_datePla2 ) ");
                }else{
                    if(beanFEva.getFechaMinPlanificacion() != null || beanFEva.getFechaMaxPlanificacion() != null){
                        strQuery = strQuery.concat(" AND CAST(eva.fechaPlanificacion AS date) = :eva_datePla1 ");
                    }
                }
            }
            String group = " GROUP BY eva.nidEvaluador ";
            String strQuery2 = "SELECT eva.nidEvaluador AS id, usu, " +
                               "(SELECT COUNT(DISTINCT eva) "+strQuery+" AND eva.nidEvaluador = id AND eva.estadoEvaluacion = 'EJECUTADO' ) , " +
                               "(SELECT COUNT(DISTINCT eva) "+strQuery+" AND eva.nidEvaluador = id AND eva.estadoEvaluacion = 'PENDIENTE' ) , " +
                               "(SELECT COUNT(DISTINCT eva) "+strQuery+" AND eva.nidEvaluador = id AND eva.estadoEvaluacion = 'NO EVALUO' AND eva.comentarioEvaluador = NULL),  " +
                               "(SELECT COUNT(DISTINCT eva) "+strQuery+" AND eva.nidEvaluador = id AND eva.estadoEvaluacion = 'NO EVALUO' AND eva.comentarioEvaluador != NULL )  " +
                               "  "+strQuery; 
            strQuery2 = strQuery2.concat(group+" ORDER BY usu.rol.nidRol ASC , eva.nidEvaluador ASC, eva.nidEvaluacion ASC " +
                                         " ");
            Query query = em.createQuery(strQuery2);
            if(lstnidRol != null){
                for(int i=0 ; i < lstnidRol.size(); i++){
                    query.setParameter("nid_Rol"+i, Integer.parseInt(lstnidRol.get(i).toString()));                  
                }
            }
            if(lstnidEvaluador != null){
                for(int i=0 ; i < lstnidEvaluador.size(); i++){
                    query.setParameter("nid_evaluador"+i, Integer.parseInt(lstnidEvaluador.get(i).toString()));               
                }
            }
            if(lstnidSede != null){
                for(int i=0 ; i < lstnidSede.size(); i++){
                    query.setParameter("nid_Sede"+i, Integer.parseInt(lstnidSede.get(i).toString()));               
                }
            }
            if(lstnidArea != null){
                for(int i=0 ; i < lstnidArea.size(); i++){
                    query.setParameter("nid_Area"+i, Integer.parseInt(lstnidArea.get(i).toString()));                  
                }
            }
            if(beanFEva != null){
                if(beanFEva.getFechaMinEvaluacion() != null && beanFEva.getFechaMaxEvaluacion()!=null){
                    query.setParameter("eva_dateEva1",beanFEva.getFechaMinEvaluacion());
                    query.setParameter("eva_dateEva2",beanFEva.getFechaMaxEvaluacion());
                }else{
                    if(beanFEva.getFechaMinEvaluacion() != null){
                        query.setParameter("eva_dateEva1",beanFEva.getFechaMinEvaluacion());
                    }
                    if(beanFEva.getFechaMaxEvaluacion() != null){
                        query.setParameter("eva_dateEva1",beanFEva.getFechaMaxEvaluacion());
                    }
                }
                if(beanFEva.getFechaMinPlanificacion() != null && beanFEva.getFechaMaxPlanificacion() != null){
                    query.setParameter("eva_datePla1",beanFEva.getFechaMinPlanificacion());
                    query.setParameter("eva_datePla2",beanFEva.getFechaMaxPlanificacion());
                }else{
                    if(beanFEva.getFechaMinPlanificacion() != null){
                        query.setParameter("eva_datePla1",beanFEva.getFechaMinPlanificacion());
                    }
                    if(beanFEva.getFechaMaxPlanificacion() != null){
                        query.setParameter("eva_datePla1",beanFEva.getFechaMaxPlanificacion());
                    }
                }
            }
            List primitiva = query.getResultList();
            // 0 - nidEvaluador , 1 - Usuario, 2 - Ejecutado, 3 - PENDIENTE, 4 -  NO EVALUO, 5 - NO EVALUO 
            int size = primitiva == null ? 0 : primitiva.size();
            if (size > 0) {
                return primitiva;
            } else {
                return new ArrayList();
            }           
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
