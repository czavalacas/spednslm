package sped.negocio.LNSF.SFBean;

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
}