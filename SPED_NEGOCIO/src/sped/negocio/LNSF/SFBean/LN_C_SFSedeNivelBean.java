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

import sped.negocio.BDL.IL.BDL_C_SFSedeNivelLocal;
import sped.negocio.LNSF.IL.LN_C_SFSedeNivelLocal;
import sped.negocio.LNSF.IR.LN_C_SFSedeNivelRemote;
import sped.negocio.entidades.admin.SedeNivel;
import sped.negocio.entidades.beans.BeanSedeNivel;

@Stateless(name = "LN_C_SFSedeNivel", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFSedeNivel")
public class LN_C_SFSedeNivelBean implements LN_C_SFSedeNivelRemote, 
                                             LN_C_SFSedeNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFSedeNivelLocal bdL_C_SFSedeNivelLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFSedeNivelBean() {
    }
    
    public List<BeanSedeNivel> getSedeNivelbyNidSedeLN(int nidSede){
        try{
            return transformLstSedeNivel(bdL_C_SFSedeNivelLocal.getSedeNivelbyNidSedeBDL(nidSede));
        }catch(Exception e){
            return new ArrayList<BeanSedeNivel>();
        }
    }
    
    public List<BeanSedeNivel> transformLstSedeNivel(List<SedeNivel> lstSedeNivel){
        try{
            List<BeanSedeNivel> lstBean = new ArrayList();
            for(SedeNivel sn : lstSedeNivel){
                BeanSedeNivel bean = (BeanSedeNivel) mapper.map(sn, BeanSedeNivel.class);
                lstBean.add(bean);
            }
            return lstBean;
        }catch(MappingException me){
            me.printStackTrace();
            return null;
        }   
    }
}
