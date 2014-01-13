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
import sped.negocio.entidades.beans.BeanConstraint;

@Stateless(name = "LN_C_SFUtils", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFUtils")
public class LN_C_SFUtilsBean implements LN_C_SFUtilsRemote, 
                                         LN_C_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
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
}
