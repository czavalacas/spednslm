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

import sped.negocio.BDL.IL.BDL_C_SFNivelLocal;
import sped.negocio.LNSF.IL.LN_C_SFNivelLocal;
import sped.negocio.LNSF.IR.LN_C_SFNivelRemote;
import sped.negocio.entidades.admin.Nivel;
import sped.negocio.entidades.beans.BeanNivel;

@Stateless(name = "LN_C_SFNivel", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFNivel")
public class LN_C_SFNivelBean implements LN_C_SFNivelRemote, 
                                         LN_C_SFNivelLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFNivelLocal bdL_C_SFNivelLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFNivelBean() {
    }
    
    public List<BeanNivel> getNivelLN(){
        List<BeanNivel> lstBean = new ArrayList();
        List<Nivel> lstNivel = bdL_C_SFNivelLocal.getNivelFindAll();
        for(Nivel n : lstNivel){
            BeanNivel bean = (BeanNivel) mapper.map(n, BeanNivel.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
}
