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
import sped.negocio.BDL.IL.BDL_C_SFParteOcurrenciaLocal;
import sped.negocio.BDL.IR.BDL_C_SFParteOcurrenciaRemote;
import sped.negocio.entidades.admin.ParteOcurrencia;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanProblemaProfesor;

/**
 * Metodo de Logica de Base de Datos de SELECTs para la entidad ParteOcurrencia - admpaoc
 * @author dfloresgonz
 * @since 26.02.2014
 */
@Stateless(name = "BDL_C_SFParteOcurrencia", mappedName = "mapBDL_C_SFParteOcurrencia")
public class BDL_C_SFParteOcurrenciaBean implements BDL_C_SFParteOcurrenciaRemote,
                                                       BDL_C_SFParteOcurrenciaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFParteOcurrenciaBean() {
    }

    /** <code>select o from ParteOcurrencia o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ParteOcurrencia> getParteOcurrenciaFindAll() {
        return em.createNamedQuery("ParteOcurrencia.findAll", ParteOcurrencia.class).getResultList();
    }
    
    public List<BeanParteOcurrencia> getListaPartesOcurrencia_BDL(Date fechaMin,
                                                                   Date fechaMax,
                                                                   Integer nidProblema,
                                                                   String nombreProfesor,
                                                                   Integer nidSede,
                                                                   Integer nidUsuario){
       try {
           String attr = "po.comentario,po.fechaRegistro,p.desc_problema,u.nombres,m.profesor.apellidos,m.profesor.nombres,m.curso.descripcionCurso," +
                         "m.aula.sede.descripcionSede,m.curso.areaAcademica.descripcionAreaAcademica," +
                         "m.aula.descripcionAula,po.nidUsuario,po.nidProblema,po.nidParte,po.nidMain,m.profesor.dniProfesor";
           String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanParteOcurrencia("+attr+") " +
                             "FROM ParteOcurrencia po, " +
                             "     Main m," +
                             "     Problema p," +
                             "     Usuario u " +
                             "WHERE 1 = 1 " +
                             "AND po.nidMain = m.nidMain " +
                             "AND po.nidProblema = p.nidProblema " +
                             "AND po.nidUsuario = u.nidUsuario ";
           if (fechaMin != null && fechaMax != null) {
               qlString = qlString.concat(" AND ( CAST(po.fechaRegistro AS date) BETWEEN :fechaMin AND :fechaMax ) ");
           } else {
               if (fechaMin != null || fechaMax != null) {
                   qlString = qlString.concat(" AND CAST(po.fechaRegistro AS date) = :fechaMin ");
               }
           }
           if(nidProblema != null){
               if(nidProblema != 0){
                   qlString = qlString.concat(" AND po.nidProblema = :nidProblema ");
               }
           }
           if (nombreProfesor != null) {
               qlString = qlString.concat(" AND upper(CONCAT(m.profesor.nombres ,' ' ," +
                                          " m.profesor.apellidos)) like upper(:nombreProfesor) ");
           }
           if (nidSede != null){
               if (nidSede != 0) {
                    qlString = qlString.concat(" AND m.aula.sede.nidSede = :nidSede ");
               }
           }
           if (nidUsuario != null){
               if (nidUsuario != 0) {
                    qlString = qlString.concat(" AND po.nidUsuario = :nidUsuario ");
               }
           }
           qlString = qlString.concat(" ORDER BY po.fechaRegistro DESC ");
           Query query = em.createQuery(qlString);
           
           if (fechaMin != null && fechaMax != null) {
               query.setParameter("fechaMin", fechaMin).setParameter("fechaMax",fechaMax);
           } else {
               if (fechaMin != null || fechaMax != null) {
                   query.setParameter("fechaMin", fechaMin).setParameter("fechaMin",fechaMin);
               }
           }
           if(nidProblema != null){
               if(nidProblema != 0){
                   query.setParameter("nidProblema", nidProblema);
               }
           }
           if (nombreProfesor != null) {
               query.setParameter("nombreProfesor", "%"+nombreProfesor+"%");
           }
           if (nidSede != null){
               if (nidSede != 0) {
                    query.setParameter("nidSede", nidSede);
               }
           }
           if (nidUsuario != null){
               if (nidUsuario != 0) {
                   query.setParameter("nidUsuario", nidUsuario);
               }
           }
           
           List<BeanParteOcurrencia> lstBeanParteOcurrencia = query.getResultList();
           int size = lstBeanParteOcurrencia == null ? 0 : lstBeanParteOcurrencia.size();
           if (size > 0) {
               return lstBeanParteOcurrencia;
           } else {
               return new ArrayList<BeanParteOcurrencia>();
           }
       } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanProblemaProfesor> getListaProblemas_ByProfesor_BDL(Date fechaMin,
                                                                        Date fechaMax,
                                                                        String dniProfesor,
                                                                        Integer nidSede,
                                                                        Integer nidUsuario){
       try {
           String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanProblemaProfesor(p.desc_problema, " +
                             "                                                             m.profesor.apellidos, " +
                             "                                                             m.profesor.nombres) " +
                             "FROM ParteOcurrencia po, " +
                             "     Main m," +
                             "     Problema p," +
                             "     Usuario u " +
                             "WHERE 1 = 1 " +
                             "AND po.nidMain = m.nidMain " +
                             "AND po.nidProblema = p.nidProblema " +
                             "AND po.nidUsuario = u.nidUsuario "+
                             "AND m.profesor.dniProfesor = :dniProfesor ";
           if (fechaMin != null && fechaMax != null) {
               qlString = qlString.concat(" AND ( CAST(po.fechaRegistro AS date) BETWEEN :fechaMin AND :fechaMax ) ");
           } else {
               if (fechaMin != null || fechaMax != null) {
                   qlString = qlString.concat(" AND CAST(po.fechaRegistro AS date) = :fechaMin ");
               }
           }
           if (nidSede != null){
               if (nidSede != 0) {
                    qlString = qlString.concat(" AND m.aula.sede.nidSede = :nidSede ");
               }
           }
           if (nidUsuario != null){
               if (nidUsuario != 0) {
                    qlString = qlString.concat(" AND po.nidUsuario = :nidUsuario ");
               }
           }
           qlString = qlString.concat(" ORDER BY po.fechaRegistro DESC ");
           Query query = em.createQuery(qlString);
           query.setParameter("dniProfesor",dniProfesor);
           if (fechaMin != null && fechaMax != null) {
               query.setParameter("fechaMin", fechaMin).setParameter("fechaMax",fechaMax);
           } else {
               if (fechaMin != null || fechaMax != null) {
                   query.setParameter("fechaMin", fechaMin).setParameter("fechaMin",fechaMin);
               }
           }
           if (nidSede != null){
               if (nidSede != 0) {
                    query.setParameter("nidSede", nidSede);
               }
           }
           if (nidUsuario != null){
               if (nidUsuario != 0) {
                   query.setParameter("nidUsuario", nidUsuario);
               }
           }
           
           List<BeanProblemaProfesor> lstProblemasProf = (List<BeanProblemaProfesor>)query.getResultList();
           int size = lstProblemasProf == null ? 0 : lstProblemasProf.size();
           if (size > 0) {
               return lstProblemasProf;
           } else {
               return new ArrayList<BeanProblemaProfesor>();
           }
       } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}