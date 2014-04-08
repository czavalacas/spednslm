package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.BDL.IR.BDL_C_SFUtilsRemote;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;

/**
 * SESSION FACADE DE METODOS UTILITARIOS
 * @author dfloresgonz
 */
@Stateless(name = "BDL_C_SFUtils", mappedName = "mapBDL_C_SFUtils")
public class BDL_C_SFUtilsBean implements BDL_C_SFUtilsRemote,
                                          BDL_C_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    MapperIF mapper = new DozerBeanMapper();

    public BDL_C_SFUtilsBean() {
    }
    
    public BeanConstraint getCatalogoConstraints(String nombreCampo, 
                                                  String nombreTabla, 
                                                  String valorCampo){
        try{
            ConstraintPK id = new ConstraintPK(nombreCampo,nombreTabla,valorCampo);
            Constraint constraint = this.findConstraintById(id);
            if(constraint != null){
                BeanConstraint beanConstraint = (BeanConstraint) mapper.map(constraint, BeanConstraint.class);
                return beanConstraint;
            }else{
                Utiles.sysout("Constraint no encontrado");
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Constraint findConstraintById(ConstraintPK id) {
        try {
            Constraint instance = em.find(Constraint.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    /**
     * Trae una lista de la tabla Constraint
     * @param nombreCampo
     * @param nombreTabla
     * @return
     */
    public List<Constraint> getListaConstraintsBDL(String nombreCampo, String nombreTabla){
        try{
            String ejbQl = "SELECT o FROM Constraint o" +
                           " WHERE o.nombreCampo = :nombreC"+
                           " and o.nombreTabla = :nombreT";
            List<Constraint> lstConstraint = em.createQuery(ejbQl).setParameter("nombreC", nombreCampo).
                                                                   setParameter("nombreT", nombreTabla).getResultList();
            return lstConstraint;
        }catch(Exception e){
            return null;
        }
    }
    
    public int findCountByProperty(String atributoDesc, 
                                    Object atributoValor, 
                                    String entidad, 
                                    boolean changeCase,
                                    boolean isUpdate) {
         try {
             String queryString = "select count(model) " +
                                  "from "+entidad+" model " +
                                  "where 1 = 1 ";
             if(changeCase){
                 queryString = queryString.concat(" and upper(model."+ atributoDesc +") = upper(:propertyValue) ");
             }else{
                 queryString = queryString.concat(" and model."+ atributoDesc +" = :propertyValue ");
             }
             if(isUpdate){
                 queryString = queryString.concat(" and upper(model."+ atributoDesc +") <> :propertyValue ");
             }
             List lst = em.createQuery(queryString).setParameter("propertyValue",atributoValor).getResultList();
             if(lst.isEmpty()){
                 return 0;
             }else{
                 return Integer.parseInt(lst.get(0).toString());
             }
         } catch (Exception re) {
             re.printStackTrace();
             return 0;
         }
     }
    
    /**
     * Metodo que retorna los usuarios planificadores
     * @author dfloresgonz
     * @since 17.02.2014
     * @return
     */
    public List<BeanCombo> getPlanificadores_WS(String id, String desc){
        try{
            String qlString =  this.getSelectBasicoBeanCombo(id, desc, "Usuario")+
                               " WHERE e.rol.nidRol = 4 OR " +
                               " e.rol.nidRol = 5 ";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    /**
     * Metodo que retorna los usuarios evaluadores
     * @author dfloresgonz
     * @since 17.02.2014
     * @return
     */
    public List<BeanCombo> getEvaluadores_WS(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Usuario") +
                               " WHERE e.rol.nidRol = 2 OR " +
                               " e.rol.nidRol = 4 OR "+
                               " e.rol.nidRol = 5 " +
                               " ORDER BY e.nombres ASC ";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanComboString> getTipoVisita(String id,String desc){
        try{
            String qlString =  this.getSelectBasicoBeanComboString(id, desc, "Constraint")+
                               " WHERE e.nombreCampo = 'tipo_visita'";
            List<BeanComboString> lstConst = em.createQuery(qlString).getResultList(); 
            return lstConst;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanCombo> getAreas_WS(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "AreaAcademica")+
                              " ORDER BY e.descripcionAreaAcademica ASC ";
            List<BeanCombo> lstAreas = em.createQuery(qlString).getResultList();        
            return lstAreas;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getProblemas_WS(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Problema") + " WHERE e.estado = '1' ";
            List<BeanCombo> lstProblemas = em.createQuery(qlString).getResultList();        
            return lstProblemas;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getUsuarios_WS(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Usuario") + " WHERE e.estadoUsuario = '1' ";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getSedes(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Sede") +
                              " ORDER BY e.descripcionSede ASC ";
            List<BeanCombo> lstSedes = em.createQuery(qlString).getResultList();        
            return lstSedes;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getNiveles(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Nivel") +
                              " ORDER BY e.descripcionNivel ASC";
            List<BeanCombo> lstNivel = em.createQuery(qlString).getResultList();        
            return lstNivel;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanCombo> getRolEvaluadores(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Rol") +
                               " WHERE e.nidRol = 2 OR " +
                               " e.nidRol = 4 OR "+
                               " e.nidRol = 5 " +
                               "ORDER BY e.descripcionRol ASC";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getCursos(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Curso") +
                              " ORDER BY e.descripcionCurso ASC";
            List<BeanCombo> lstCurso = em.createQuery(qlString).getResultList();        
            return lstCurso;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanCombo> getGrados(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Grado") +
                              " ORDER BY e.descripcionGrado ASC";
            List<BeanCombo> lstGrado = em.createQuery(qlString).getResultList();        
            return lstGrado;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanComboString> getEstadosEvaluacion(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanComboString(id, desc, "Constraint") +
                              " WHERE e.nombreCampo = 'estado_evaluacion' "+
                              " ORDER BY e.descripcionAMostrar ASC";
            List<BeanComboString> lstEstado = em.createQuery(qlString).getResultList();        
            return lstEstado;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanCombo> getAulaByNidSedeNivel(String id, String desc, int nidSede, int nidNivel){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Aula") +
                              " WHERE e.sede.nidSede = :nidSede " +
                              " AND e.gradoNivel.nivel.nidNivel = :nidNivel" +
                              " ORDER BY e.descripcionAula ASC";
            List<BeanCombo> lstGrado = em.createQuery(qlString).setParameter("nidSede", nidSede)
                                                               .setParameter("nidNivel", nidNivel)
                                                               .getResultList();        
            return lstGrado;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanCombo> getCursosByArea(String id, String desc, int nidArea){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Curso") +
                              " WHERE e.areaAcademica.nidAreaAcademica = :nidArea"+
                              " ORDER BY e.descripcionCurso ASC";
            List<BeanCombo> lstCurso = em.createQuery(qlString).setParameter("nidArea", nidArea)
                                                               .getResultList();        
            return lstCurso;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    private String getSelectBasicoBeanCombo(String id,String desc, String entidad){
        return "SELECT NEW sped.negocio.entidades.beans.BeanCombo("+id+","+desc+") " +
                "FROM "+entidad+" e ";
    }
    
    private String getSelectBasicoBeanComboString(String id,String desc, String entidad){
        return "SELECT NEW sped.negocio.entidades.beans.BeanComboString("+id+","+desc+") " +
                "FROM "+entidad+" e ";
    }
}