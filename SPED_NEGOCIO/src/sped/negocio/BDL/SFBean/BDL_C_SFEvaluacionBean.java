package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import sped.negocio.BDL.IL.BDL_C_SFEvaluacionLocal;
import sped.negocio.BDL.IL.BDL_C_SFUsuarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFEvaluacionRemoto;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanFiltrosGraficos;
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
    @EJB
    private BDL_C_SFUsuarioLocal bdL_C_SFUsuarioLocal;

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
            List<Evaluacion> lstaEvaluaciones = new ArrayList<Evaluacion>();
            String ejbQl = "SELECT ev " +
                           "FROM Evaluacion ev " +
                           "WHERE 1 = 1 ";
            if(beanEvaluacion.getMain()!=null){
                if(beanEvaluacion.getMain().getProfesor() != null){
                    if(beanEvaluacion.getMain().getProfesor().getDniProfesor() != null){
                        ejbQl = ejbQl.concat(" AND ev.main.profesor.dniProfesor = :dniProfesor " +
                                             " AND ev.tipoVisita = 'OP' OR (ev.tipoVisita = 'SO' AND CAST(ev.startDate AS date) = CURRENT_DATE  ) ");//like '%"+fechaHoy+"%'
                    }
                }
            }
            if(beanEvaluacion.getFechaMaxPlanificacion() != null && beanEvaluacion.getFechaMinPlanificacion() != null){
                ejbQl = ejbQl.concat(" AND ev.fechaPlanificacion BETWEEN :min AND :max ");
            }
            if(beanEvaluacion.getNidEvaluador() != null){
                if(beanEvaluacion.getNidEvaluador() != 0){
                    ejbQl = ejbQl.concat(" AND ev.nidEvaluador = :nidEvaluador ");
                }
            }
            if(beanEvaluacion.getNidNivel() != null){
                if(beanEvaluacion.getNidNivel() != 0){
                    ejbQl = ejbQl.concat(" AND ev.main.aula.gradoNivel.nivel.nidNivel = :nidNivel ");
                }
            }
            if(beanEvaluacion.getNidArea() != null){
                if(beanEvaluacion.getNidArea() != 0){
                    ejbQl = ejbQl.concat(" AND ev.main.curso.areaAcademica.nidAreaAcademica = :nidAreaAcademica"); 
                }
            }
            if(beanEvaluacion.getNidEstadoEvaluacion() != null){        
                if("PENDIENTE".equals(beanEvaluacion.getNidEstadoEvaluacion())){
                    ejbQl = ejbQl.concat(" AND ev.flgEvaluar = '1' "); 
                }else{
                    ejbQl = ejbQl.concat(" AND ev.estadoEvaluacion = :nidEstadoEvaluacion "); 
                }                
           }
           if(beanEvaluacion.getNidSede() != null){
                if(beanEvaluacion.getNidSede() != 0){
                    ejbQl = ejbQl.concat(" AND ev.main.aula.sede.nidSede= :nidSede ");
                }
           }
           if(beanEvaluacion.getApellidosDocentes() != null){
                ejbQl = ejbQl.concat(" AND upper(ev.main.profesor.apellidos) like '% :apelidosDocente %' ");
           }Utiles.sysout("ejbQl: "+ejbQl);
           Query query = em.createQuery(ejbQl);
           if (beanEvaluacion.getFechaMaxPlanificacion() != null && beanEvaluacion.getFechaMinPlanificacion() != null) {
                 query.setParameter("min", beanEvaluacion.getFechaMinPlanificacion(),TemporalType.DATE);
                 query.setParameter("max",beanEvaluacion.getFechaMaxPlanificacion(),TemporalType.DATE).getResultList();
           }
           if(beanEvaluacion.getMain() != null){
               if(beanEvaluacion.getMain().getProfesor() != null){
                   if(beanEvaluacion.getMain().getProfesor().getDniProfesor() != null){
                     query.setParameter("dniProfesor", beanEvaluacion.getMain().getProfesor().getDniProfesor());    
                   }       
               }
           }
           if(beanEvaluacion.getNidEvaluador() != null){
                if(beanEvaluacion.getNidEvaluador() != 0){
                     query.setParameter("nidEvaluador",beanEvaluacion.getNidEvaluador());            
                }
           }
           if(beanEvaluacion.getNidNivel() != null){
                if(beanEvaluacion.getNidNivel() != 0){
                    query.setParameter("nidNivel",beanEvaluacion.getNidNivel());                      
                }
           }
           if(beanEvaluacion.getNidArea() != null){
                if(beanEvaluacion.getNidArea() != 0){
                    query.setParameter("nidAreaAcademica",beanEvaluacion.getNidArea());                       
                }
           }
           if(beanEvaluacion.getNidEstadoEvaluacion() != null){        
                if(!"PENDIENTE".equals(beanEvaluacion.getNidEstadoEvaluacion())){     
                    query.setParameter("nidEstadoEvaluacion",beanEvaluacion.getNidEstadoEvaluacion());                                                  
                }
           }
           if(beanEvaluacion.getNidSede() != null){
                if(beanEvaluacion.getNidSede() != 0){
                    query.setParameter("nidSede",beanEvaluacion.getNidSede());  
                }
           }
           if(beanEvaluacion.getApellidosDocentes() != null){
                query.setParameter("apelidosDocente",beanEvaluacion.getApellidosDocentes().toUpperCase());                    
           } 
           lstaEvaluaciones = query.getResultList();
           return lstaEvaluaciones;            
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }

    public Evaluacion getEvaluacionById(String nidDate) {
        try{
            String ejbQl = " SELECT ma " +
                           " FROM Evaluacion ma " +
                           " WHERE ma.nidDate='"+nidDate+"'";   
                Evaluacion eva = (Evaluacion)em.createQuery(ejbQl).getSingleResult();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Evaluacion> getEvaluaciones(String fechaHoy, 
                                            Integer nidAreaAcademica, 
                                            Integer nidEvaluador,
                                            String dniProfesor, 
                                            String nidCurso, 
                                            Integer nidSede) {
        try{   
            String ejbQl = "SELECT ev " +
                          " FROM Evaluacion ev, " +
                          "     Main ma, " +
                          "     Curso cu," +
                          "     AreaAcademica ac " +
                                   " WHERE CAST(ev.startDate AS date) = CURRENT_DATE " + //like '%"+fechaHoy+"%'"
                                   " AND ma.nidMain=ev.main.nidMain" +
                                   " AND ma.curso.nidCurso=cu.nidCurso" +
                                   " AND cu.areaAcademica.nidAreaAcademica=ac.nidAreaAcademica"+                                  
                                   " AND ev.nidEvaluador="+nidEvaluador ;
            if (nidAreaAcademica!= null) {    
                if(nidAreaAcademica!=0){
                    ejbQl = ejbQl.concat(" AND ac.nidAreaAcademica = " + nidAreaAcademica);
                }
            }
            if (nidSede!= null) {     
                if(nidSede!=0){
                    ejbQl = ejbQl.concat(" AND ma.aula.sede.nidSede = " + nidSede);
                }
            }
            if (dniProfesor!= null) {                
                ejbQl = ejbQl.concat(" and ma.profesor.dniProfesor = '" + dniProfesor + "'");
            }
            if (nidCurso!= null) {              
                ejbQl = ejbQl.concat(" and ma.curso.nidCurso = " + nidCurso);
            }
                List<Evaluacion> eva = em.createQuery(ejbQl).getResultList();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Evaluacion> getEvaluacionesByUsuarioBDL(BeanUsuario beanUsuario, BeanEvaluacion beanFiltroEva) {
            List<Evaluacion> listEvaluacion = new ArrayList();
            try{                 
                if(beanUsuario != null){
                    String strQuery = "SELECT eva " +
                                      " FROM Evaluacion eva, " +
                                      " Usuario usu " + 
                                      " WHERE eva.nidEvaluador=usu.nidUsuario " +
                                      " AND upper(eva.estadoEvaluacion) = 'EJECUTADO' ";
                    int nidRol = beanUsuario.getRol().getNidRol();
                    if((nidRol == 2 && "0".compareTo(beanUsuario.getIsNuevo()) == 0) || nidRol == 4){
                        strQuery = strQuery.concat(" AND eva.nidEvaluador = :nid_evaluador ");
                    }
                    if(nidRol == 4){                        
                        strQuery = strQuery.concat(" AND eva.main.aula.sede.nidSede = :nid_sede ");
                        beanFiltroEva.setNidSede(0);
                    }
                    if(nidRol == 2){
                        strQuery = strQuery.concat(" AND eva.main.curso.areaAcademica.nidAreaAcademica = :nid_area ");
                        beanFiltroEva.setNidArea(0);
                    }
                    if(nidRol == 3){
                        strQuery = strQuery.concat(" AND eva.main.profesor.dniProfesor = :dni_profesor ");
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
                        if(beanFiltroEva.getApellidosDocentes() != null){
                            strQuery =
                            strQuery.concat(" AND upper(CONCAT(eva.main.profesor.nombres ,' ' ," +
                                                       " eva.main.profesor.apellidos)) like :eva_profesor ");
                        }
                        if(beanFiltroEva.getNombreEvaluador() != null){
                            strQuery = strQuery.concat(" AND usu.nombres like :eva_nomEva ");
                        }
                    if (beanFiltroEva.getFechaMinEvaluacion() != null &&
                        beanFiltroEva.getFechaMaxEvaluacion() != null) {
                        strQuery =
                            strQuery.concat(" AND ( CAST(eva.endDate AS date) BETWEEN :eva_dateEva1 AND :eva_dateEva2 ) ");
                        }else{
                        if (beanFiltroEva.getFechaMinEvaluacion() != null ||
                            beanFiltroEva.getFechaMaxEvaluacion() != null) {
                                strQuery = strQuery.concat(" AND CAST(eva.endDate AS date) = :eva_dateEva1 ");
                            }
                        }
                    if (beanFiltroEva.getFechaMinPlanificacion() != null &&
                        beanFiltroEva.getFechaMaxPlanificacion() != null) {
                        strQuery =
                            strQuery.concat(" AND ( CAST(eva.fechaPlanificacion AS date) BETWEEN :eva_datePla1 AND :eva_datePla2 ) ");
                        }else{
                        if (beanFiltroEva.getFechaMinPlanificacion() != null ||
                            beanFiltroEva.getFechaMaxPlanificacion() != null) {
                                strQuery = strQuery.concat(" AND CAST(eva.fechaPlanificacion AS date) = :eva_datePla1 ");
                            }
                        }
                    }
                    strQuery = strQuery.concat(" ORDER BY eva.startDate DESC ");
                    Query query = em.createQuery(strQuery);
                        if((nidRol == 2 && beanUsuario.getIsNuevo().compareTo("0") == 0) || nidRol == 4){
                        query.setParameter("nid_evaluador", beanUsuario.getNidUsuario());
                    }
                    if(nidRol == 4){
                        query.setParameter("nid_sede", beanUsuario.getSede().getNidSede());
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
                        if(beanFiltroEva.getApellidosDocentes() != null){
                        query.setParameter("eva_profesor",
                                           "%" + beanFiltroEva.getApellidosDocentes().toUpperCase() + "%");
                        }
                        if(beanFiltroEva.getNombreEvaluador() != null){
                            query.setParameter("eva_nomEva", "%"+beanFiltroEva.getNombreEvaluador()+"%");
                        }
                        if(beanFiltroEva.getStartDate() != null){
                            query.setParameter("end_date",beanFiltroEva.getStartDate());
                        }
                    if (beanFiltroEva.getFechaMinEvaluacion() != null &&
                        beanFiltroEva.getFechaMaxEvaluacion() != null) {
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
                    if (beanFiltroEva.getFechaMinPlanificacion() != null &&
                        beanFiltroEva.getFechaMaxPlanificacion() != null) {
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
            String ejbQl = "SELECT ma " +
                           "FROM Constraint ma " +
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
            String ejbQl = "SELECT ma " +
                           "FROM Constraint ma " + 
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
     * @return Lista de Entidades de Evmeval para poder escojer una y evaluarla
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
            boolean isSupervisor = false;
            String qlString = "SELECT e " +
                              "FROM Evaluacion e " +
                              "WHERE e.flgEvaluar = '1' ";
            if(nidRol == 4){//Evaluador x Sede
                qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSede ");
            }else if(nidRol == 2){//Evaluador x Area
                isSupervisor = bdL_C_SFUsuarioLocal.getIsSupervisor(nidUsuario);
                /** dfloresgonz 20.05.2014 Si no es supervisor que busque x su area academica, de lo contrario, si es
                 * supervisor no le debe restringir el area academcia ya que puede evaluar a cualquiera. **/
                if(!isSupervisor){
                    if (nidAreaAcademica == 12 || nidAreaAcademica == 13) { //12 = Primer Ciclo 13 = Inicial
                        qlString = qlString.concat(" and e.main.curso.areaAcademica.nidAreaAcademica = :nidAreaAcademica ");
                    } else {
                        qlString = qlString.concat(" and e.main.curso.nidAreaNativa = :nidAreaAcademica ");
                    }
                }
                qlString = qlString.concat(" AND e.nidEvaluador = :nidEvaluador ");
            //    qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAreaAcademica AND e.nidEvaluador = :nidEvaluador ");
            }else if(nidRol == 1){//Director
                //TODOS LOS PERMISOS
            }
            if(nidSedeFiltro != 0){
                if(nidRol == 1 || nidRol == 2){
                    qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSedeFiltro ");
                }
            }
            if(nidAAFiltro != 0){
                if(nidRol == 1 || nidRol == 4){
                    qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAAFiltro ");
                }
            }
            if(nombreProfesor != null){
                qlString = qlString.concat(" AND upper(CONCAT(e.main.profesor.nombres ,' ' ," +
                                           " e.main.profesor.apellidos)) like upper(:nombreProfesor) ");
            }
            if(curso != null){
                qlString = qlString.concat(" AND upper(e.main.curso.descripcionCurso) like upper(:curso) ");
            }
           // qlString = qlString.concat(" AND CAST(e.startDate AS date) = CURRENT_DATE  ");//TODO Fecha de planificaciones solo hoy
            qlString = qlString.concat(" ORDER BY e.startDate ASC ");
            //Utiles.sysout("query:" + qlString);
            Query query = em.createQuery(qlString);
            if(nidRol == 4){//Evaluador x Sede
                query.setParameter("nidSede",nidSede);
            }else if(nidRol == 2){//Evaluador x Area
                if(!isSupervisor){
                    query.setParameter("nidAreaAcademica",nidAreaAcademica);
                }
                query.setParameter("nidEvaluador",nidUsuario);
            }
            if(nidSedeFiltro != 0){
                if(nidRol == 1 || nidRol == 2){
                    query.setParameter("nidSedeFiltro",nidSedeFiltro);
                }
            }
            if(nidAAFiltro != 0){
                if(nidRol == 1 || nidRol == 4){
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
            return new ArrayList<Evaluacion>();
        }
    }
    
    /**
     * Metodo para mostrar las evaluaciones realizadas por el usuario, expuesto como WS para el APP movil
     * @author dfloresgonz
     * @param nidRol - rol del usuario en consulta
     * @param nidSede - sede del usuario en consulta
     * @param nidAreaAcademica -area del usuario en consulta
     * @param nidUsuario -ID del usuario en consulta
     * @param nombreProfesor - filtro de profesor
     * @param curso - filtro de curso
     * @param nidSedeFiltro -filtro de sede
     * @param nidAAFiltro - filtro de area academica
     * @param estado - filtro de estado
     * @param fechaMin -filtro de fecha de inicio
     * @param fechaMax -filtro de fecha fin
     * @param tipoVisita -filtro de tipo de visita
     * @param nidPlanificador
     * @param nidEvaluador 
     * @return ArrayList<Evaluacion>()
     */
    public List<Evaluacion> getEvaluaciones_BDL_WS(int nidRol, 
                                                   int nidSede, 
                                                   int nidAreaAcademica,
                                                   int nidUsuario,
                                                   String nombreProfesor, 
                                                   String curso, 
                                                   int nidSedeFiltro,
                                                   int nidAAFiltro, 
                                                   String estado, 
                                                   Date fechaMin, 
                                                   Date fechaMax,
                                                   String tipoVisita, 
                                                   Integer nidPlanificador, 
                                                   Integer nidEvaluador) {
        try {
            String qlString = "SELECT e " + 
                              "FROM Evaluacion e " +
                              "WHERE e.estadoEvaluacion = '" + estado + "' ";
            if (nidRol == 4) { //Evaluador x Sede
                qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSede ");
            } else if (nidRol == 2) { //Evaluador x Area
                qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAreaAcademica AND e.nidEvaluador = :nidEvaluador ");
            } else if (nidRol == 1 || nidRol == 5) { //Director || Evaluador General
                //TODOS LOS PERMISOS
            }
            if (nidSedeFiltro != 0) {
                if (nidRol == 1 || nidRol == 2 || nidRol == 5) {
                    qlString = qlString.concat(" AND e.main.aula.sede.nidSede = :nidSedeFiltro ");
                }
            }
            if (nidAAFiltro != 0) {
                if (nidRol == 1 || nidRol == 4 || nidRol == 5) {
                    qlString = qlString.concat(" AND e.main.curso.areaAcademica.nidAreaAcademica = :nidAAFiltro ");
                }
            }
            if (nombreProfesor != null) {
                qlString = qlString.concat(" AND upper(CONCAT(e.main.profesor.nombres ,' ' ," +
                                           " e.main.profesor.apellidos)) like upper(:nombreProfesor) ");
            }
            if (curso != null) {
                qlString = qlString.concat(" AND upper(e.main.curso.descripcionCurso) like upper(:curso) ");
            }
            if (fechaMin != null && fechaMax != null) {
                qlString = qlString.concat(" AND ( CAST(e.endDate AS date) BETWEEN :fechaMin AND :fechaMax ) ");
            } else {
                if (fechaMin != null || fechaMax != null) {
                    qlString = qlString.concat(" AND CAST(e.endDate AS date) = :fechaMin ");
                }
            }
            if (tipoVisita != null) {
                qlString = qlString.concat(" AND e.tipoVisita = :tipoVisita ");
            }
            if (nidPlanificador != null) {
                if(nidPlanificador != 0){
                    qlString = qlString.concat(" AND e.nidPlanificador = :nidPlanificador ");
                }
            }
            if (nidEvaluador != null) {
                if(nidEvaluador != 0){
                    qlString = qlString.concat(" AND e.nidEvaluador = :nidEvaluador ");
                }
            }
            qlString = qlString.concat(" ORDER BY e.startDate DESC ");
            Query query = em.createQuery(qlString);
            if (nidRol == 4) { //Evaluador x Sede
                query.setParameter("nidSede", nidSede);
            } else if (nidRol == 2) { //Evaluador x Area
                query.setParameter("nidAreaAcademica", nidAreaAcademica).setParameter("nidEvaluador", nidUsuario);
            }
            if (nidSedeFiltro != 0) {
                if (nidRol == 1 || nidRol == 2 || nidRol == 5) {
                    query.setParameter("nidSedeFiltro", nidSedeFiltro);
                }
            }
            if (nidAAFiltro != 0) {
                if (nidRol == 1 || nidRol == 4 || nidRol == 5) {
                    query.setParameter("nidAAFiltro", nidAAFiltro);
                }
            }
            if (nombreProfesor != null) {
                query.setParameter("nombreProfesor", "%" + nombreProfesor + "%");
            }
            if (curso != null) {
                query.setParameter("curso", "%" + curso + "%");
            }
            if (fechaMin != null && fechaMax != null) {
                query.setParameter("fechaMin", fechaMin).setParameter("fechaMax", fechaMax);
            } else {
                if (fechaMin != null || fechaMax != null) {
                    query.setParameter("fechaMin", fechaMin);
                }
            }
            if (tipoVisita != null) {
                query.setParameter("tipoVisita", tipoVisita);
            }
            if (nidPlanificador != null) {
                if(nidPlanificador != 0){
                    query.setParameter("nidPlanificador", nidPlanificador);
                }
            }
            if (nidEvaluador != null) {
                if (nidEvaluador != 0){
                    query.setParameter("nidEvaluador", nidEvaluador);
                }
            }
            List<Evaluacion> lstEvas = query.getResultList();
            int size = lstEvas == null ? 0 : lstEvas.size();
            if (size > 0) {
                return lstEvas;
            } else {
                return new ArrayList<Evaluacion>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Evaluacion>();
        }
    }
    
    /**
     * Metodo que sirve para llenar los dash de evaluacion por evaluador
     * @param tipoBusqueda
     * @param lstnidRol
     * @param lstnidEvaluador
     * @param lstnidSede
     * @param lstnidArea
     * @param beanFEva
     * @return
     */
    public List getDesempenoEvaluacionbyFiltroBDL(int tipoBusqueda,
                                                  List lstnidRol,
                                                  List lstnidEvaluador,
                                                  List lstnidSede,
                                                  List lstnidArea,
                                                  BeanEvaluacion beanFEva){
        try{
            boolean  oneDate = false;//para saber si se seleciono una sola fecha
            String strQuery2 = " ";
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
                    strQuery = strQuery.concat(" eva.nidEvaluador = :nid_Evaluador"+i+" ");                 
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
                if(beanFEva.getNombreEvaluador() != null){
                    strQuery = strQuery.concat(" AND  usu.nombres = :nom_Eva ");
                }
                if(beanFEva.getEstadoEvaluacion() != null){
                    strQuery = strQuery.concat(" AND upper(eva.estadoEvaluacion) like upper(:estado) ");   
                }
                if(beanFEva.getFechaMinEvaluacion() != null && beanFEva.getFechaMaxEvaluacion() != null){
                    strQuery =
                        strQuery.concat(" AND ( CAST(eva.endDate AS date) BETWEEN :dateEva1 AND :dateEva2 ) ");
                }else{
                    if(beanFEva.getFechaMinEvaluacion() != null || beanFEva.getFechaMaxEvaluacion() != null){
                        strQuery = strQuery.concat(" AND CAST(eva.endDate AS date) = :dateEva1 ");
                        oneDate = true;
                    }
                }
                if(beanFEva.getFechaMinPlanificacion() != null && beanFEva.getFechaMaxPlanificacion() != null){
                    strQuery =
                        strQuery.concat(" AND ( CAST(eva.fechaPlanificacion AS date) BETWEEN :datePla1 AND :datePla2 ) ");
                }else{
                    if(beanFEva.getFechaMinPlanificacion() != null || beanFEva.getFechaMaxPlanificacion() != null){
                        strQuery = strQuery.concat(" AND CAST(eva.fechaPlanificacion AS date) = :datePla1 ");
                        oneDate = true;
                    }
                }
                if(beanFEva.getNidProblema() != 0){
                    strQuery = strQuery.concat(" AND  eva.nidProblema = :id_Prob ");
                }
                if(beanFEva.getDescRol() != null){
                    strQuery = strQuery.concat(" AND  usu.rol.descripcionRol = :des_Rol ");
                }
            }
            if(tipoBusqueda == 1 || tipoBusqueda == 3 || tipoBusqueda == 5){
                String subQuery = "(SELECT COUNT(1) " +
                                  strQuery.replaceAll("eva", "x");
                if(tipoBusqueda == 3){
                    subQuery = subQuery.concat(" AND CAST(x.endDate AS date) = CAST(eva.endDate AS date) ");
                }else{
                    subQuery = subQuery.concat(" AND x.nidEvaluador = eva.nidEvaluador ");
                }
                strQuery2 = "SELECT " +
                            subQuery+" AND x.estadoEvaluacion = 'EJECUTADO' ) eje, " +
                            subQuery+" AND x.estadoEvaluacion = 'PENDIENTE' ) , " +
                            subQuery+" AND x.estadoEvaluacion = 'NO EJECUTADO' ),  " +
                            subQuery+" AND x.estadoEvaluacion = 'JUSTIFICADO' ), " + 
                            subQuery+" AND x.estadoEvaluacion = 'POR JUSTIFICAR' ), " + 
                            subQuery+" AND x.estadoEvaluacion = 'INJUSTIFICADO' ) " ;
            }
            if(tipoBusqueda == 1){
                strQuery2 = strQuery2.concat(", usu "+strQuery+" GROUP BY eva.nidEvaluador ");
                strQuery2 = strQuery2.concat(" ORDER BY eje DESC "); 
            }            
            if(tipoBusqueda == 2 ){
                strQuery2 = strQuery2.concat("SELECT eva, usu " +strQuery);
                strQuery2 = strQuery2.concat(" ORDER BY eva.endDate DESC ");
            }          
            if(tipoBusqueda == 3){
                if(oneDate){
                    strQuery2 = strQuery2.concat(",eva.endDate "+strQuery+" GROUP BY eva.endDate ");
                }else{
                    strQuery2 = strQuery2.concat(",CAST(eva.endDate AS date) "+
                                                 strQuery+" GROUP BY CAST(eva.endDate AS date) ");
                }                
                strQuery2 = strQuery2.concat(" ORDER BY eva.endDate ASC ");
            }
            if(tipoBusqueda == 4){
                strQuery2 = strQuery2.concat("SELECT COUNT(DISTINCT eva) AS cont , eva.nidProblema" +strQuery);
                strQuery2 = strQuery2.concat(" AND eva.nidProblema != 0 ");//ultima restircion ingresa xq el nidproblema != 0
                strQuery2 = strQuery2.concat(" AND eva.nidProblema != NULL GROUP BY eva.nidProblema ORDER BY cont ASC ");
            }
            if(tipoBusqueda == 5){
                strQuery2 = strQuery2.concat(", usu.rol.descripcionRol "+strQuery+" GROUP BY usu.rol ");
                strQuery2 = strQuery2.concat(" ORDER BY eje DESC "); 
            }
            Query query = em.createQuery(strQuery2);
            if(lstnidRol != null){
                for(int i=0 ; i < lstnidRol.size(); i++){
                    query.setParameter("nid_Rol"+i, Integer.parseInt(lstnidRol.get(i).toString()));                  
                }
            }
            if(lstnidEvaluador != null){
                for(int i=0 ; i < lstnidEvaluador.size(); i++){
                    query.setParameter("nid_Evaluador"+i, Integer.parseInt(lstnidEvaluador.get(i).toString()));               
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
                if(beanFEva.getNombreEvaluador() != null){
                    query.setParameter("nom_Eva",beanFEva.getNombreEvaluador());
                }
                if(beanFEva.getEstadoEvaluacion() != null){
                    query.setParameter("estado", beanFEva.getEstadoEvaluacion());
                }
                if(beanFEva.getFechaMinEvaluacion() != null && beanFEva.getFechaMaxEvaluacion()!=null){
                    query.setParameter("dateEva1",beanFEva.getFechaMinEvaluacion());
                    query.setParameter("dateEva2",beanFEva.getFechaMaxEvaluacion());
                }else{
                    if(beanFEva.getFechaMinEvaluacion() != null){
                        query.setParameter("dateEva1",beanFEva.getFechaMinEvaluacion());
                    }
                    if(beanFEva.getFechaMaxEvaluacion() != null){
                        query.setParameter("dateEva1",beanFEva.getFechaMaxEvaluacion());
                    }
                }
                if(beanFEva.getFechaMinPlanificacion() != null && beanFEva.getFechaMaxPlanificacion() != null){
                    query.setParameter("datePla1",beanFEva.getFechaMinPlanificacion());
                    query.setParameter("datePla2",beanFEva.getFechaMaxPlanificacion());
                }else{
                    if(beanFEva.getFechaMinPlanificacion() != null){
                        query.setParameter("datePla1",beanFEva.getFechaMinPlanificacion());
                    }
                    if(beanFEva.getFechaMaxPlanificacion() != null){
                        query.setParameter("datePla1",beanFEva.getFechaMaxPlanificacion());
                    }
                }
                if(beanFEva.getNidProblema() != 0){
                    query.setParameter("id_Prob",beanFEva.getNidProblema());
                }
                if(beanFEva.getDescRol() != null){
                    query.setParameter("des_Rol",beanFEva.getDescRol());
                }
            }
            List primitiva = query.getResultList();
            // tipo135--> 0 - Ejecutado, 
            //            1 - PENDIENTE, 2 -  NO EVALUO, 3 - NO EVALUO 
            // tipo 1 --> 4 nombreEvaluador
            // tipo 2 --> 1 evaluacion, usuario
            // tipo 3 --> 4 fecha evaluacion
            // tipo 4 --> 0 - countProblema, 1 - idProblema
            // tipo 5 --> 4 rol evaluador
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

    public List<Evaluacion> getEvaluaciones_DeDocente(BeanFiltrosGraficos beanFiltros, String fechaHoy) {
        try{
            String ejbQl = " SELECT eva " +
                           " FROM Evaluacion eva, Main ma, Aula au"+
                           " WHERE eva.main.nidMain=ma.nidMain"+
                           " and ma.aula.nidAula=au.nidAula"+                         
                           " and eva.estadoEvaluacion='EJECUTADO'";                                    
            
            if(beanFiltros.getFechaInicio() != null && beanFiltros.getFechaFin() != null){
              ejbQl = ejbQl.concat(" and eva.startDate BETWEEN :min AND :max ");
            }
             
            if(beanFiltros.getNidSede() != null){                
              ejbQl = ejbQl.concat(" and au.sede.nidSede="+beanFiltros.getNidSede());
            }
            if(beanFiltros.getNidArea()!=null){
              ejbQl = ejbQl.concat(" and ma.curso.areaAcademica.nidAreaAcademica="+beanFiltros.getNidArea());
            }  
            if(beanFiltros.getNidCurso()!=null){
              ejbQl = ejbQl.concat(" and ma.curso.nidCurso="+beanFiltros.getNidCurso());
            } 
            if(beanFiltros.getNidNivele()!=null){
              ejbQl = ejbQl.concat(" and au.gradoNivel.nivel.nidNivel="+beanFiltros.getNidNivele());
            } 
            if(beanFiltros.getNidGrado()!=null){
              ejbQl = ejbQl.concat(" and au.gradoNivel.grado.nidGrado="+beanFiltros.getNidGrado());
            } 
            if(beanFiltros.getDniDocente() != null){          
              ejbQl = ejbQl.concat(" and ma.profesor.dniProfesor="+beanFiltros.getDniDocente());
            }
            if(fechaHoy != null){            
              ejbQl = ejbQl.concat(" and eva.startDate like '%"+fechaHoy+"%'");
            }            
            if (beanFiltros.getFechaFin() != null &&
                beanFiltros.getFechaInicio() != null) {
                List<Evaluacion> eva = em.createQuery(ejbQl).setParameter("min", beanFiltros.getFechaInicio(),
                                                   TemporalType.DATE).setParameter("max",beanFiltros.getFechaFin(),
                                                                                    TemporalType.DATE).getResultList();
                return eva;
            }else{
                
                
            }
                List<Evaluacion> eva = em.createQuery(ejbQl).getResultList();           
                return eva;         
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public int countNidProblema(int nidProblema){
        String ejbQL = "SELECT  count(e) FROM Evaluacion e " 
                       + "WHERE e.nidProblema = :nidProblema ";
        Object object = em.createQuery(ejbQL).setParameter("nidProblema", nidProblema)
                            .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
    
    public int countEvaluacionByNidMain(int nidMain){
        String ejbQL = "SELECT  count(e) FROM Evaluacion e " 
                       + "WHERE e.main.nidMain = :nidMain ";
        Object object = em.createQuery(ejbQL).setParameter("nidMain", nidMain)
                            .getSingleResult();
        int cont = 0;
        if(object != null){
            cont = Integer.parseInt(object.toString());
        }
        return cont;
    }
  
    public List<Evaluacion> getEvaluacionesEnrangoDeHoras(Date hoy, int nidMain){
        try{   
            
              String ejbQl = "SELECT ev " +
                             "FROM Evaluacion ev " +
                             "WHERE CAST(ev.startDate as date) = :horaInicio " +
                             "and ev.main.nidMain= :nidMain";
           List<Evaluacion> eva = em.createQuery(ejbQl)
                                   .setParameter("horaInicio", hoy,  TemporalType.DATE)
                                   .setParameter("nidMain", nidMain)                
                                   .getResultList();     
            Utiles.sysout(eva.size());
            return eva;  
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }                   
        
        }
}
