package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
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
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.entidades.admin.Constraint;
import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanConstraint;

/**
 * SESSION FACADE DE METODOS UTILITARIOS
 * @author dfloresgonz
 */
@Stateless(name = "LN_C_SFUtils", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFUtils")
public class LN_C_SFUtilsBean implements LN_C_SFUtilsRemote, 
                                         LN_C_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFUtilsBean() {
    }
    
    public List<BeanConstraint> getListaConstraintsLN(String nombreCampo,
                                                      String nombreTabla){
        List<BeanConstraint> lstbean = new ArrayList();
        List<Constraint> lstConstraint = bdL_C_SFUtilsLocal.getListaConstraintsBDL(nombreCampo, nombreTabla);
        for(Constraint c : lstConstraint){
            BeanConstraint bean = (BeanConstraint) mapper.map(c, BeanConstraint.class);
            lstbean.add(bean);
        }
        return lstbean;
    }
    
    public List<BeanComboString> getListaEstados(String nombreCampo, String nombreTabla){
        List<BeanComboString> list=new ArrayList<BeanComboString>();
        List<Constraint> listConstraint=bdL_C_SFUtilsLocal.getListaConstraintsBDL(nombreCampo, nombreTabla);
        Iterator it=listConstraint.iterator();
        while(it.hasNext()){
            Constraint entida=(Constraint)it.next();
            BeanComboString bean=new BeanComboString();           
            bean.setId(entida.getDescripcionAMostrar());//EL ID SERA EL MISMO QUE LA DESCRIPCION A MOSTRAR...
            bean.setDescripcion(entida.getDescripcionAMostrar());            
            list.add(bean);
        }            
        return list;
    }
    
    public List<BeanCombo> getPlanificadores_LN_WS(){
        return bdL_C_SFUtilsLocal.getPlanificadores_WS("e.nidUsuario", "e.nombres");
    }
    
    public List<BeanCombo> getEvaluadores_LN_WS(){
        return bdL_C_SFUtilsLocal.getEvaluadores_WS("e.nidUsuario", "e.nombres");
    }
    
    public List<BeanComboString> getTipoVisitaFromConstraint(){
        return bdL_C_SFUtilsLocal.getTipoVisita("e.valorCampo", "e.descripcionAMostrar");
    }
    
    public List<BeanCombo> getAreas_LN_WS(){
        return bdL_C_SFUtilsLocal.getAreas_WS("e.nidAreaAcademica", "e.descripcionAreaAcademica");
    }
    
    public List<BeanCombo> getProblemas_LN_WS(){
        return bdL_C_SFUtilsLocal.getProblemas_WS("e.nidProblema", "e.desc_problema");
    }
    
    public List<BeanCombo> getUsuarios_LN_WS(){
        return bdL_C_SFUtilsLocal.getUsuarios_WS("e.nidUsuario", "e.nombres");
    }
    
    public int findCountByProperty(String correo, 
                                   boolean changeCase, 
                                   boolean isUpdate){
        return bdL_C_SFUtilsLocal.findCountByProperty("correo", correo, "Usuario", changeCase, isUpdate);
    }
    
    public List<BeanCombo> getSedes_LN(){
        return bdL_C_SFUtilsLocal.getSedes("e.nidSede", "e.descripcionSede");
    }
    
    public List<BeanCombo> getNiveles_LN(){
        return bdL_C_SFUtilsLocal.getNiveles("e.nidNivel", "e.descripcionNivel");
    }
    
    public List<BeanCombo> getRolEvaluadores_LN(){
        return bdL_C_SFUtilsLocal.getRolEvaluadores("e.nidRol", "e.descripcionRol");
    }
    
    public List<BeanCombo> getCursos_LN(){
        return bdL_C_SFUtilsLocal.getCursos("e.nidCurso", "e.descripcionCurso");
    }
    
    public List<BeanCombo> getGrados_LN(){
        return bdL_C_SFUtilsLocal.getGrados("e.nidGrado", "e.descripcionGrado");
    }
    
    public List<BeanComboString> getEstadoEvaluacionFromConstraint(){
        return bdL_C_SFUtilsLocal.getEstadosEvaluacion("e.valorCampo", "e.descripcionAMostrar");
    }
    
    public List<BeanCombo> getAulaByNidSedeNivel(int nidSede, int nidNivel){
        return bdL_C_SFUtilsLocal.getAulaByNidSedeNivel("e.nidAula", "e.descripcionAula", nidSede, nidNivel);
    }
    
    public List<BeanCombo> getCursosByArea_LN(int nidArea){
        return bdL_C_SFUtilsLocal.getCursosByArea("e.nidCurso", "e.descripcionCurso", nidArea);
    }

    public List<BeanComboString> getProfesor_LN() {
        return bdL_C_SFUtilsLocal.getProfesor("e.dniProfesor", "e.nombres");
    }
    
    public List<BeanCombo> getRol_LN(){
        return bdL_C_SFUtilsLocal.getRolNoAdmin("e.nidRol", "e.descripcionRol");
    }
    
    public List<BeanCombo> getEvaluadoresByArea_LN(int nidArea){
        return bdL_C_SFUtilsLocal.getEvaluadoresByArea("e.nidUsuario", "e.nombres", nidArea);
    }
}
