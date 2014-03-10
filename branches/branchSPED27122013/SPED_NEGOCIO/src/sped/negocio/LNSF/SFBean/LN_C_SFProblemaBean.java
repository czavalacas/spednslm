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

import sped.negocio.BDL.IL.BDL_C_SFProblemaLocal;
import sped.negocio.LNSF.IL.LN_C_SFProblemaLocal;
import sped.negocio.LNSF.IR.LN_C_SFProblemaRemote;
import sped.negocio.entidades.admin.Problema;
import sped.negocio.entidades.beans.BeanProblema;

@Stateless(name = "LN_C_SFProblema", mappedName = "mapLN_C_SFProblema")
public class LN_C_SFProblemaBean implements LN_C_SFProblemaRemote, 
                                            LN_C_SFProblemaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFProblemaLocal bdL_C_SFProblemaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFProblemaBean() {
    }
    
    public List<BeanProblema> getLstProblemaAllLN(){
        try{
            return transformLstProblema(bdL_C_SFProblemaLocal.getProblemaFindAll());
        }catch(Exception e){
            return new ArrayList<BeanProblema>();
        }
    }
    
    public List<BeanProblema> transformLstProblema(List<Problema> lstProblema){
        try{
            List<BeanProblema> lstBean = new ArrayList();
            for(Problema p : lstProblema){
                BeanProblema beanProblema = (BeanProblema) mapper.map(p, BeanProblema.class);
                lstBean.add(beanProblema);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }
    }
    
    
}
