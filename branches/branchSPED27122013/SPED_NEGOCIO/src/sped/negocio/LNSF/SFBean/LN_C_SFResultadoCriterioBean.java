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

import net.sf.dozer.util.mapping.MappingException;

import sped.negocio.BDL.IL.BDL_C_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFResultadoCriterioLocal;
import sped.negocio.LNSF.IR.LN_C_SFResultadoCriterioRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanResultadoCriterio;
import sped.negocio.entidades.eval.FichaCriterio;
import sped.negocio.entidades.eval.ResultadoCriterio;

@Stateless(name = "LN_C_SFResultadoCriterio", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFResultadoCriterio")
public class LN_C_SFResultadoCriterioBean implements LN_C_SFResultadoCriterioRemote, 
                                                     LN_C_SFResultadoCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFResultadoCriterioLocal bdL_C_SFResultadoCriterioLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFResultadoCriterioBean() {
    }

    public List<BeanResultadoCriterio> transformLstResultadoCriterio(List<ResultadoCriterio> lstResultado) {
        try {
            List<BeanResultadoCriterio> lstBeanRes = new ArrayList();
            for (ResultadoCriterio rc : lstResultado) {
                BeanResultadoCriterio beanRes = (BeanResultadoCriterio) mapper.map(rc, BeanResultadoCriterio.class);
                lstBeanRes.add(beanRes);
            }
            return lstBeanRes;
        } catch (MappingException me) {
            me.printStackTrace();
            return null;
        }
    }
    
    public BeanResultadoCriterio getResCriByFichaEvaLN(int nidEvaluacion, 
                                                       FichaCriterio fichaCriterio){
        ResultadoCriterio resCri = bdL_C_SFResultadoCriterioLocal.getResCriByFichaEvaBDL(nidEvaluacion, 
                                                                                         fichaCriterio);        
        BeanResultadoCriterio beanResCri = new BeanResultadoCriterio();
        if(resCri != null){            
            beanResCri.setValor(resCri.getValor());
        }
        return beanResCri;
    } 
    
    public List<BeanResultadoCriterio> getResultadoCriterio_ByEvaluacion(Integer nidEvaluacion){        
        List<BeanResultadoCriterio> lstBean = new ArrayList();
        List<ResultadoCriterio> lstAreaAcd = bdL_C_SFResultadoCriterioLocal.getResultadoCriterio_ByEvaluacion(nidEvaluacion);       
        for(ResultadoCriterio a : lstAreaAcd){
            BeanResultadoCriterio bean = (BeanResultadoCriterio) mapper.map(a, BeanResultadoCriterio.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
 
    
}
