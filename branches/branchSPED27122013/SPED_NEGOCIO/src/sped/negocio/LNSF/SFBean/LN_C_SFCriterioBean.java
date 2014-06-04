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

import sped.negocio.BDL.IL.BDL_C_SFCriterioLocal;
import sped.negocio.LNSF.IL.LN_C_SFCriterioLocal;
import sped.negocio.LNSF.IR.LN_C_SFCriterioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanGrado;
import sped.negocio.entidades.eval.Criterio;

@Stateless(name = "LN_C_SFCriterio", mappedName = "mapLN_C_SFCriterio")
public class LN_C_SFCriterioBean implements LN_C_SFCriterioRemote, 
                                               LN_C_SFCriterioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    MapperIF mapper = new DozerBeanMapper();
    @EJB
    private BDL_C_SFCriterioLocal bdL_C_SFCriterioLocal;

    public LN_C_SFCriterioBean() {
    }
    
    public List<BeanCriterio> getCriteriosByAttr_LN(int nidCriterio,
                                                    String descCriterio){
        try{
            BeanCriterio beanCriterio = new BeanCriterio();
            beanCriterio.setDescripcionCriterio(descCriterio);
            beanCriterio.setNidCriterio(nidCriterio);
            return transformLstCriterios(bdL_C_SFCriterioLocal.getCriteriosByAttr_BDL(beanCriterio));
        }catch(Exception e){
            return new ArrayList<BeanCriterio>();
        }
    }
    
    public List<BeanCriterio> transformLstCriterios(List<Criterio> lstCriterios){
        try {
            BeanCriterio beanCriterio = new BeanCriterio();
            List<BeanCriterio> lstBeanCriterios = new ArrayList<BeanCriterio>();
            for (Criterio criterio : lstCriterios) {
                beanCriterio = (BeanCriterio) mapper.map(criterio, BeanCriterio.class);
                lstBeanCriterios.add(beanCriterio);
            }
            return lstBeanCriterios;
        } catch (MappingException me) {
            me.printStackTrace();
            return null;
        }
    }
    
    public BeanCriterio findConstrainByIdLN(int id){
        try{
            BeanCriterio bean =new BeanCriterio();
            Criterio crite=bdL_C_SFCriterioLocal.findCriterioById(id);
            bean.setDescripcionCriterio(crite.getDescripcionCriterio());
            bean.setNidCriterio(crite.getNidCriterio());
            return bean;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
}