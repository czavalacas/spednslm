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
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.admin.ConstraintPK;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboDouble;
import sped.negocio.entidades.beans.BeanComboDouble2;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.sist.Log;
import sped.negocio.entidades.sist.Logger;

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
    
    public Log findLogById(int id) {
        try {
            Log instance = em.find(Log.class, id);
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
    
    public List<BeanComboString> getSedesString(){
        try{
            String qlString = this.getSelectBasicoBeanComboString("e.nidSede", "e.descripcionSede", "Sede") +
                              " ORDER BY e.descripcionSede ASC ";
            List<BeanComboString> lstSedes = em.createQuery(qlString).getResultList();        
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
    
    /**
     * Metodo que retorna el rol de los evaluadores
     * @param id
     * @param desc
     * @return
     */
    public List<BeanCombo> getRolEvaluadores(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Rol") +
                               " WHERE e.nidRol = 2 OR " +
                               " e.nidRol = 4 " +
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
    
    public List<BeanComboString> getAulaActivas(int nidSede){
        try{
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanComboString(e.nidAula,e.descripcionAula,g.abvr,n.abvr) "+
                              " From Aula e,Grado g, Nivel n WHERE e.sede.nidSede = :nidSede " +
                              " And e.flgActi = 1 "+
                              " And e.gradoNivel.grado.nidGrado = g.nidGrado "+
                              " And e.gradoNivel.nivel.nidNivel = n.nidNivel "+
                              " ORDER BY e.descripcionAula ASC";
            List<BeanComboString> lstAulas = em.createQuery(qlString).setParameter("nidSede", nidSede)
                                            .getResultList();
            return lstAulas;       
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
    
    public List<BeanComboString> getCursosActivos(){
        try{
            String qlString = "Select New sped.negocio.entidades.beans.BeanComboString(c.nidCurso,c.descripcionCurso,a.descripcionAreaAcademica)"+
                              " From Curso c, AreaAcademica a "+
                              " Where c.flgActi = 1 "+
                              " And c.areaAcademica.nidAreaAcademica = a.nidAreaAcademica "+
                              " Order By c.descripcionCurso Asc";
            List<BeanComboString> lstCurso = em.createQuery(qlString).getResultList();        
            return lstCurso;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanComboString> getProfesor(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanComboString(id, desc, "Profesor") +
                              " Where e.flgActi = 1 ORDER BY e.nombres ASC";
            List<BeanComboString> lstCurso = em.createQuery(qlString).getResultList();        
            return lstCurso;       
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BeanComboString> getProfesorActivos(){
        try{
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanComboString(e.dniProfesor,e.nombres,e.apellidos)"+
                              " From Profesor e "+
                              " Where e.flgActi = 1 ORDER BY e.nombres ASC";
            List<BeanComboString> lstProf = em.createQuery(qlString).getResultList();        
            return lstProf;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo que retorna todos los Rol
     * @param id
     * @param desc
     * @return
     */
    public List<BeanCombo> getRolNoAdmin(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Rol") +
                               " WHERE e.nidRol <> 6 " +
                               "ORDER BY e.descripcionRol ASC";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    /**
     * Lista de los evaluadores de area por su nidArea
     * @param id
     * @param desc
     * @param nidArea
     * @return
     */
    public List<BeanCombo> getEvaluadoresByArea(String id, String desc, int nidArea){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Usuario") +
                               " WHERE e.rol.nidRol = 2 " +
                               " AND e.areaAcademica.nidAreaAcademica = " + nidArea +
                               " ORDER BY e.nombres ASC ";
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    /**
     * Matodo para consultar los evaluadores por su area y estado
     * @param id
     * @param desc
     * @param nidArea
     * @return
     */
    public List<BeanCombo> getEvaluadoresByAreaByEstado(String id, String desc, int nidArea, boolean estado){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Usuario") +
                               " WHERE e.rol.nidRol = 2 " +
                               " AND e.areaAcademica.nidAreaAcademica = " + nidArea ;
            if(estado){
                qlString = qlString.concat(" AND e.estadoUsuario = '1' ");
            }
            qlString = qlString.concat(" ORDER BY e.nombres ASC ");
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanCombo> getEvaluadoresByEstado(String id, String desc, boolean estado){
        try{
            String qlString = this.getSelectBasicoBeanCombo(id, desc, "Usuario") +
                               " WHERE ( e.rol.nidRol = 2 OR " +
                               " e.rol.nidRol = 4 OR "+
                               " e.rol.nidRol = 5 ) ";
            if(estado){
                qlString = qlString.concat(" AND e.estadoUsuario = '1' ");
            }
            qlString = qlString.concat(" ORDER BY e.nombres ASC ");
            List<BeanCombo> lstUsuarios = em.createQuery(qlString).getResultList();        
            return lstUsuarios;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanComboDouble> getPosibleListaValoresIndicador(int nidCritIndi){
        try{
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanComboDouble(v.valor,v.valor) " +
                              "FROM Valor v, Leyenda d " +
                              "WHERE d.fichaValor.valor.nidValoracion = v.nidValoracion " +
                              "AND   d.criterioIndicador.nidCriterioIndicador = :nidCritIndi ";
            List<BeanComboDouble> lstValores = em.createQuery(qlString).setParameter("nidCritIndi",nidCritIndi).getResultList();
            return lstValores;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanComboDouble2> getListaValores(){
        try{
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanComboDouble2(v.nidValoracion,v.valor) " +
                              "FROM Valor v ";
            List<BeanComboDouble2> lstValores = em.createQuery(qlString).getResultList();
            return lstValores;       
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }
    }
    
    public List<BeanComboString> getTiposFalta(String id, String desc){
        try{
            String qlString = this.getSelectBasicoBeanComboString(id, desc, "Constraint") +
                              " WHERE e.nombreCampo = 'tipoFalta' " +
                              " AND e.nombreTabla = 'addusca' "+
                              " ORDER BY e.descripcionAMostrar ASC ";
            List<BeanComboString> lstTiposFalta = em.createQuery(qlString).getResultList();        
            return lstTiposFalta;       
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
    
    public Integer[] getMinMaxEvasPorDiaConstraint_LN(int nidRole){
        try{
            Integer vec[] = new Integer[2];
            nidRole = nidRole == 7 ? 2 : nidRole;//El rol de directora academica que pase como Evaluador de area
            String qlString = "SELECT c.valorCampo " +
                              "FROM Constraint c " +
                              "WHERE c.nombreTabla = 'configuracion' " +
                              "AND c.nidRol = :nidRole " +
                              "ORDER BY c.valorCampo ASC ";
            List<String> lstCants = em.createQuery(qlString).setParameter("nidRole", nidRole).getResultList();
            vec[0] = Integer.parseInt(lstCants.get(0));
            vec[1] = Integer.parseInt(lstCants.get(1));
            return vec;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Constraint> getMinMaxEvasPorDiaConfigConstraint_LN(){
        try{
            String qlString = "SELECT c " +
                              "FROM Constraint c " +
                              "WHERE c.nombreTabla = 'configuracion' " +
                              "ORDER BY c.nombreCampo DESC ";
            return em.createQuery(qlString).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Constraint> getConstraintByAttr_LN(String tabla,String campo,String desc){
        try{
            String qlString = "SELECT c " +
                              "FROM Constraint c " +
                              "WHERE c.nombreTabla = :tabla " +
                              "AND c.nombreCampo = :campo " +
                              "AND c.descripcionAMostrar = :desc " +
                              "ORDER BY c.valorCampo ASC ";
            return em.createQuery(qlString).setParameter("tabla",tabla).setParameter("campo",campo).setParameter("desc",desc).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}