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

import sped.negocio.BDL.IL.BDL_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IL.LN_C_SFAreaAcademicaLocal;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.beans.BeanAreaAcademica;

@Stateless(name = "LN_C_SFAreaAcademica", mappedName = "SPED_APP-SPED_NEGOCIO-LN_C_SFAreaAcademica")
public class LN_C_SFAreaAcademicaBean implements LN_C_SFAreaAcademicaRemote, 
                                                    LN_C_SFAreaAcademicaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFAreaAcademicaLocal bdL_C_SFAreaAcademicaLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFAreaAcademicaBean() {
    }
    
    public List<BeanAreaAcademica> getAreaAcademicaLN(){        
        List<BeanAreaAcademica> lstBean = new ArrayList();
        List<AreaAcademica> lstAreaAcd = bdL_C_SFAreaAcademicaLocal.getAreaAcademicaFindAll();
        System.out.println("SIZE AREAS  "+lstAreaAcd.size());
        for(AreaAcademica a : lstAreaAcd){
            BeanAreaAcademica bean = (BeanAreaAcademica) mapper.map(a, BeanAreaAcademica.class);
            lstBean.add(bean);
        }
        return lstBean;
    }
}
