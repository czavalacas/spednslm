package sped.negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFErrorLocal;
import sped.negocio.LNSF.IL.LN_C_SFErrorLocal;
import sped.negocio.LNSF.IR.LN_C_SFErrorRemote;
import sped.negocio.entidades.beans.BeanError;

@Stateless(name = "LN_C_SFError", mappedName = "mapLN_C_SFError")
public class LN_C_SFErrorBean implements LN_C_SFErrorRemote,
                                            LN_C_SFErrorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFErrorLocal bdL_C_SFErrorLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFErrorBean() {
    }
    
    public BeanError getCatalogoErrores(String cidError){
        BeanError beanError = new BeanError();
        try{
            sped.negocio.entidades.sist.Error error = bdL_C_SFErrorLocal.getErrorByCodigo(cidError);
            beanError = (BeanError) mapper.map(error, BeanError.class);
        }catch(Exception e){
            beanError.setDescripcionError("Error LN_C_SFErrorBean.getCatalogoErrores");
        }
        return beanError;
    }
}