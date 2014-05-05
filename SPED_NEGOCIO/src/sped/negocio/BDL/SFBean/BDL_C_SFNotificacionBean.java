package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFNotificacionLocal;
import sped.negocio.BDL.IR.BDL_C_SFNotificacionRemote;
import sped.negocio.entidades.beans.BeanNotificacionEvaluacion;
import sped.negocio.entidades.beans.BeanParteOcurrencia;

/**
 * Clase Session Facade que maneja los queries de consulta para las notificaciones de usuario
 * @author dfloresgonz
 * @since 07.03.2014
 */
@Stateless(name = "BDL_C_SFNotificacion", mappedName = "mapBDL_C_SFNotificacion")
public class BDL_C_SFNotificacionBean implements BDL_C_SFNotificacionRemote,
                                                 BDL_C_SFNotificacionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFNotificacionBean(){
    }
    
    public int getCantidadNotificacionesEvaluaciones_BDL(int nidUsuario){
        try {
            String qlString = "SELECT count(n.cidNotificacion) " +
                              "FROM NotificacionEvaluacion n " +
                              "WHERE n.leido = '0' " +
                              "AND n.nidUsuario = :nidUsuario " +
                              "GROUP BY n.nidEvaluacion ";
           List lst = em.createQuery(qlString).setParameter("nidUsuario",nidUsuario).getResultList();
           if(lst.isEmpty()){
               return 0;
           }else{
               return lst.size();
           }
       } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getCantidadNotificacionesParteOcurrencia_BDL(int nidUsuario){
        try {
            String qlString = "SELECT count(n.cidNotificacion) " +
                              "FROM NotificacionParteOcurrencia n " +
                              "WHERE n.leido = '0' " +
                              "AND n.nidUsuario = :nidUsuario ";
           List lst = em.createQuery(qlString).setParameter("nidUsuario",nidUsuario).getResultList();
           if(lst.isEmpty()){
               return 0;
           }else{
               return Integer.parseInt(lst.get(0).toString());
           }
       } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<BeanNotificacionEvaluacion> getListaNotificacionesByAttr_BDL(String docente,
                                                                             String indicador,
                                                                             Integer sede,
                                                                             String estadoLeido,
                                                                             Date fecMin,
                                                                             Date fecMax,
                                                                             Integer nidUsuario){
        try{
            String attr = "n.nidEvaluacion,r.criterioIndicador.fichaCriterio.ficha.descripcionVersion,r.criterioIndicador.fichaCriterio.ficha.nidFicha," +
                          "r.evaluacion.startDate,r.evaluacion.main.profesor.apellidos,r.evaluacion.main.profesor.nombres," +
                          "r.evaluacion.main.profesor.dniProfesor,r.evaluacion.main.curso.descripcionCurso,r.evaluacion.main.aula.sede.descripcionSede," +
                          "r.evaluacion.main.curso.areaAcademica.descripcionAreaAcademica,r.evaluacion.main.aula.descripcionAula," +
                          "(SELECT u.nombres FROM Usuario u WHERE u.nidUsuario = r.evaluacion.nidEvaluador )," +
                          "n.cidNotificacion ";
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanNotificacionEvaluacion("+attr+") " +
                              "FROM NotificacionEvaluacion n," +
                              "     Resultado r "+
                              "WHERE 1 = 1 " +
                              "AND r.criterioIndicador.nidCriterioIndicador = n.nidCriterioIndicador " +
                              "AND r.evaluacion.nidEvaluacion = n.nidEvaluacion "+
                              "AND n.nidUsuario = :nidUsuario ";
            if(docente != null){
                qlString = qlString.concat(" AND UPPER(CONCAT(r.evaluacion.main.profesor.nombres ,' ' ,r.evaluacion.main.profesor.apellidos)) like :docente ");
            }
            if(indicador != null){
                qlString = qlString.concat(" AND UPPER(r.criterioIndicador.indicador.descripcionIndicador) like :indicador ");
            }
            if(sede != null){
                if(sede != 0){
                    qlString = qlString.concat(" AND r.evaluacion.main.aula.sede.nidSede = :sede ");
                }
            }
            if(fecMin != null && fecMax != null){
                qlString = qlString.concat(" AND n.fechaRegistro BETWEEN :fecMin AND :fecMax ");
            }else{
                if(fecMin != null || fecMax != null){
                    qlString = qlString.concat(" AND CAST(n.fechaRegistro AS date) = :fec ");
                }
            }
            if(estadoLeido != null){
                qlString = qlString.concat(" AND n.leido = :est ");
            }else{
                qlString = qlString.concat(" AND n.leido = '0' ");
            }
            qlString = qlString.concat(" GROUP BY n.nidEvaluacion ORDER BY n.fechaRegistro DESC ");
            Query query = em.createQuery(qlString);
            if(docente != null){
                query.setParameter("docente","%"+docente+"%");
            }
            if(indicador != null){
                query.setParameter("indicador","%"+indicador+"%");
            }
            if(sede != null){
                if(sede != 0){
                    query.setParameter("sede",sede);
                }
            }
            if(fecMin != null && fecMax != null){
                query.setParameter("fecMin",fecMin).setParameter("fecMax",fecMax);
            }else{
                if(fecMin != null){
                    query.setParameter("fec",fecMin);
                }
                if(fecMax != null){
                    query.setParameter("fec",fecMax);
                }
            }
            if(estadoLeido != null){
                query.setParameter("est",estadoLeido);
            }
            query.setParameter("nidUsuario",nidUsuario);
            List<BeanNotificacionEvaluacion> lstBeanNotificacionEvaluacion = query.getResultList();
            int size = lstBeanNotificacionEvaluacion == null ? 0 : lstBeanNotificacionEvaluacion.size();
            if (size > 0) {
                return lstBeanNotificacionEvaluacion;
            } else {
                return new ArrayList<BeanNotificacionEvaluacion>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanNotificacionEvaluacion> getListaNotificaciones_Detalle_ByEval_ByAttr_BDL(int nidEvaluacion){
        try{
            String attr = "r.evaluacion.main.profesor.apellidos,r.evaluacion.main.profesor.nombres," +
                          "r.criterioIndicador.indicador.descripcionIndicador," +
                          "r.criterioIndicador.fichaCriterio.criterio.descripcionCriterio,n.valor,n.notaVigecimal,n.cidNotificacion ";
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanNotificacionEvaluacion("+attr+") " +
                              "FROM NotificacionEvaluacion n," +
                              "     Resultado r "+
                              "WHERE 1 = 1 " +
                              "AND n.nidEvaluacion = :nidEvaluacion "+
                              "AND r.criterioIndicador.nidCriterioIndicador = n.nidCriterioIndicador " +
                              "AND r.evaluacion.nidEvaluacion = n.nidEvaluacion "+
                              "ORDER BY n.fechaRegistro DESC ";
            Query query = em.createQuery(qlString);
            query.setParameter("nidEvaluacion",nidEvaluacion);
            List<BeanNotificacionEvaluacion> lstBeanNotificacionEvaluacion = query.getResultList();
            int size = lstBeanNotificacionEvaluacion == null ? 0 : lstBeanNotificacionEvaluacion.size();
            if (size > 0) {
                return lstBeanNotificacionEvaluacion;
            } else {
                return new ArrayList<BeanNotificacionEvaluacion>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanParteOcurrencia> getListaNotificacionesPartesOcurrenciaByAttr_BDL(String docente,
                                                                                      Integer nidProblema,
                                                                                      Integer sede,
                                                                                      String estadoLeido,
                                                                                      Date fecMin,
                                                                                      Date fecMax,
                                                                                      Integer nidUsuario){
        try{
            String attr = "po.comentario,po.fechaRegistro,p.desc_problema,u.nombres,m.profesor.apellidos,m.profesor.nombres,m.curso.descripcionCurso," +
                          "m.aula.sede.descripcionSede,m.curso.areaAcademica.descripcionAreaAcademica," +
                          "m.aula.descripcionAula,po.nidUsuario,po.nidProblema,po.nidParte,po.nidMain,m.profesor.dniProfesor,n.cidNotificacion";
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanParteOcurrencia("+attr+") " +
                              "FROM ParteOcurrencia po, " +
                              "     Main m," +
                              "     Problema p," +
                              "     Usuario u," +
                              "     NotificacionParteOcurrencia n " +
                              "WHERE 1 = 1 " +
                              "AND po.nidMain = m.nidMain " +
                              "AND po.nidProblema = p.nidProblema " +
                              "AND po.nidUsuario = u.nidUsuario " +
                              "AND n.nidParte = po.nidParte " +
                              "AND n.nidUsuario = :nidUsuario ";
            if(docente != null){
                qlString = qlString.concat(" AND UPPER(CONCAT(m.profesor.nombres ,' ' ," +
                                           " m.profesor.apellidos)) like UPPER(:docente) ");
            }
            if(nidProblema != null){
                if(nidProblema != 0){
                    qlString = qlString.concat(" AND po.nidProblema = :nidProblema ");   
                }
            }
            if(sede != null){
                if(sede != 0){
                    qlString = qlString.concat(" AND m.aula.sede.nidSede = :sede ");
                }
            }
            if(fecMin != null && fecMax != null){
                qlString = qlString.concat(" AND n.fechaRegistro BETWEEN :fecMin AND :fecMax ");
            }else{
                if(fecMin != null || fecMax != null){
                    qlString = qlString.concat(" AND CAST(n.fechaRegistro AS date) = :fec ");
                }
            }
            if(estadoLeido != null){
                qlString = qlString.concat(" AND n.leido = :est ");
            }else{
                qlString = qlString.concat(" AND n.leido = '0' ");
            }
            qlString = qlString.concat(" ORDER BY n.fechaRegistro DESC ");
            Query query = em.createQuery(qlString);
            if(docente != null){
                query.setParameter("docente","%"+docente+"%");
            }
            if(nidProblema != null){
                if(nidProblema != 0){
                    query.setParameter("nidProblema", nidProblema);
                }
            }
            if(sede != null){
                if(sede != 0){
                    query.setParameter("sede",sede);
                }
            }
            if(fecMin != null && fecMax != null){
                query.setParameter("fecMin",fecMin).setParameter("fecMax",fecMax);
            }else{
                if(fecMin != null){
                    query.setParameter("fec",fecMin);
                }
                if(fecMax != null){
                    query.setParameter("fec",fecMax);
                }
            }
            if(estadoLeido != null){
                query.setParameter("est",estadoLeido);
            }
            query.setParameter("nidUsuario",nidUsuario);
            List<BeanParteOcurrencia> lstBeanParteOcurrencia = query.getResultList();
            int size = lstBeanParteOcurrencia == null ? 0 : lstBeanParteOcurrencia.size();
            if (size > 0) {
                return lstBeanParteOcurrencia;
            } else {
                return new ArrayList<BeanParteOcurrencia>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}